package GUI.battleView;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JOptionPane;

import utility.FightResultType;
import Data.ActionHelper.AttackHelper;
import Data.ActionHelper.MoveHelper;
import Data.Builder.DiceTool;
import Data.Character.GameFighter;
import Data.dataIO.CharacterSave;
import GUI.mapView.IMapRole;
import GUI.mapView.MapBlock;
import GUI.mapView.MapInfor;
import GUI.mapView.MapItem;
import GUI.mapView.MapItem.STATUS;
import GUI.mapView.MapMonsterFactory.MapMonster;
import GUI.mapView.MapRoleFactory.MapRole;

/**
 * this class is used to control the process of playing the game.
 * 
 * @author l_jingan
 * 
 */
public class BattleControl implements Runnable {
	private MapInfor m_MapInfor;
	private ArrayList<MapItem> m_Players;
	private BattleLog m_Log;
	private DiceTool dice20;
	private BattleDraw m_battleDraw;

	private Integer m_CurrentRound;
	private Integer m_CurrentPlayer;
	private Boolean m_EndPlay;
	private ArrayList<Integer> orderDice;
	private MoveHelper moveHelper;
	private boolean notKill;

	public BattleControl(BattleDraw bd) {
		m_Log = bd.battleLog;
		m_battleDraw = bd;
		m_CurrentRound = 1;
		m_CurrentPlayer = 0;
		m_EndPlay = false;
		notKill = true;
	}

	public void setMap(MapInfor mapInfor) {
		m_MapInfor = mapInfor;
		m_Players = new ArrayList<MapItem>();
	}

	private void randomMove(MapItem itemToMove) {
		moveHelper = new MoveHelper();
		Point iPosition = new Point(itemToMove.getOwnerBlock().getLocationX(),
				itemToMove.getOwnerBlock().getLocationY());

		m_battleDraw.R.moveResult = moveHelper.calOppMoveBlocks(
				m_battleDraw.R.mapInfor, iPosition, 1);
		m_battleDraw.bl.onPath = true;
		m_battleDraw.bl.onMove = true;
		m_battleDraw.R.moveDraw = true;
		m_battleDraw.bl.R.pathDraw = false;

		if (m_battleDraw.R.moveResult == null) {
			this.switchToNextPlayer();
			m_battleDraw.bl.onMove = false;
			m_battleDraw.bl.R.moveDraw = false;
			return;
		} else if (m_battleDraw.R.moveResult.size() == 0) {
			m_battleDraw.bl.onMove = false;
			m_battleDraw.bl.R.moveDraw = false;
			switchToNextPlayer();
			return;
		} else {
			int posiBlksNum = m_battleDraw.R.moveResult.size();

			DiceTool dice = new DiceTool(posiBlksNum);
			int aiChoice = dice.getValue() - 1;

			Point target = m_battleDraw.R.moveResult.get(aiChoice).getTarget();
			m_battleDraw.R.path = m_battleDraw.R.moveResult.get(aiChoice)
					.getPathSet();

			Thread t = m_battleDraw.bl.bbl.changeMove(target.x
					* m_battleDraw.bl.R.mapInfor.getBlockWidth(), target.y
					* m_battleDraw.bl.R.mapInfor.getBlockHeight(), itemToMove);
			if (t == null)
				return;

			m_Log.setLogInfor("---AI move");
			m_battleDraw.c.repaint();

			synchronized (m_battleDraw.R.moveLock) {
				try {
					t.start();
					m_battleDraw.R.moveLock.wait();
				} catch (InterruptedException e) {
					return;
				}
			}
			return;
		}
	}

	/**
	 * the movement for the AI to perform
	 * 
	 * @param itemToMove
	 */
	private void aiMove(MapItem itemToMove) {
		// m_battleDraw.R.aiMove = true;

		if (itemToMove.getStatus() == STATUS.DEFEND) {

			randomMove(itemToMove);
			return;
		}
		// Attack hero
		else {
			AttackHelper atkHelper = new AttackHelper();
			m_battleDraw.R.attackBlks = atkHelper.calAttackBlocks(
					m_battleDraw.R.mapInfor, new Point(itemToMove
							.getOwnerBlock().getLocationX(), itemToMove
							.getOwnerBlock().getLocationY()), false);
			if (m_battleDraw.bl.bbl.checkAttack(m_MapInfor.getPlayerX(),
					m_MapInfor.getPlayerY())) {

				FightResultType result = BattleOperation.battleFightOperation(
						m_battleDraw.bl.R.mapInfor.getBlock(itemToMove
								.getOwnerBlock().getLocationX(), itemToMove
								.getOwnerBlock().getLocationY()),
						m_battleDraw.bl.R.mapInfor.getBlock(new Point(
								m_MapInfor.getPlayerX(), m_MapInfor
										.getPlayerY())),
						m_battleDraw.bl.bd.battleLog, true);
				System.out.println("AI ATTACK");
				if (result.equals(FightResultType.FIGHT_KILL)) {
					((MapItem) m_battleDraw.bl.R.mapInfor.getBlock(
							new Point(m_MapInfor.getPlayerX(), m_MapInfor
									.getPlayerY())).getMapItem()).beHappy();
					;

					notKill = false;
					setIsEndPlay(true);
					return;
				}
				((MapItem) m_battleDraw.bl.R.mapInfor.getBlock(
						new Point(m_MapInfor.getPlayerX(), m_MapInfor
								.getPlayerY())).getMapItem()).beAttacked();
				switchToNextPlayer();
			} else {
				randomMove(itemToMove);
			}

			return;
		}

	}

	// m_battleDraw.R.aiMove = false;

	/**
	 * use the D20 rule to roll the dice.
	 * 
	 * @param value
	 * @return
	 */
	private int rollDice(int value) {
		int tempKey;
		do {
			tempKey = dice20.getValue(m_Log) + value;
		} while (orderDice.contains(tempKey));
		orderDice.add(tempKey);
		return tempKey;
	}

	/**
	 * decide the play sequence in each round
	 */
	private synchronized void decideFirstSequence() {
		m_Log.setLogInfor("%%%%%%init monster count = "
				+ m_MapInfor.getMonster().size());
		orderDice = new ArrayList<Integer>();
		dice20 = new DiceTool(20);
		Hashtable<Integer, MapItem> table = new Hashtable<Integer, MapItem>();

		// Throw the dices
		try {
			m_Log.setLogInfor("Round " + m_CurrentRound
					+ " >> Role throws dice:");
			int tempKey = rollDice(m_MapInfor.getRole().getGameCharacter()
					.getAbility().getDexterity());
			m_Log.setLogInfor("   Role initiative roll result: : " + tempKey);
			table.put(tempKey, m_MapInfor.getRole());
			System.out.println("table size " + table.size() + " after role");
			List<MapItem> mlist = m_MapInfor.getMonster();

			int n = 0;
			int m2 = 0;
			int r = 0;
			for (MapItem m : mlist) {
				if (m instanceof MapMonster) {
					m_Log.setLogInfor(">> Monster throws dice:");
					tempKey = rollDice(((MapMonster) m).getGameMonster()
							.getAbility().getDexterity());
					m_Log.setLogInfor("   Monster initiative roll result: : "
							+ tempKey);
					table.put(tempKey, m);
					m2++;
					// System.out.println("m2 "+table.size()+" tempKey "+tempKey+" m "+m.getClass());
				} else {
					m_Log.setLogInfor("Round " + m_CurrentRound
							+ " >> Enemy throws dice:");
					tempKey = rollDice(((MapRole) m).getGameCharacter()
							.getAbility().getDexterity());
					m_Log.setLogInfor("Round " + m_CurrentRound
							+ "   Enemy initiative roll result: : " + tempKey);
					table.put(tempKey, m);
					r++;
					// System.out.println("r "+table.size()+" tempKey "+tempKey+" m "+m.getClass());
				}
				n++;
			}
			System.out.println("table size " + table.size() + " monster size "
					+ n + " mlist " + mlist.size() + " m2 " + m2 + " r " + r);
			Set<Entry<Integer, MapItem>> keys = table.entrySet();
			// each time we need clean the list to accommodate the new sequence.
			synchronized (m_Players) {
				m_Players.clear();
			}
			m_Log.setLogInfor("***Have Key " + keys.size());

			String sequence = "Play sequence:";
			for (Entry<Integer, MapItem> e : keys) {
				m_Players.add(e.getValue());
				sequence += e.getValue() == m_MapInfor.getRole() ? "P " : "M ";
			}
			m_Log.setLogInfor(sequence);
			table.clear();

		} catch (Exception e) {
			System.out.println("1 " + e.toString());
		}
	}

	/**
	 * set the flag to show the whole play is end. that means the player is
	 * enter the out block.
	 */
	public synchronized void endPlay() {
		m_EndPlay = true;
	}

	/**
	 * let the player in the next sequence to play
	 */
	public synchronized void switchToNextPlayer() {
		++m_CurrentPlayer;
	}

	/**
	 * return true if the current round it controlled by the human player
	 * 
	 * @return
	 */
	private synchronized boolean isPlayerRound() {
		if (m_CurrentPlayer > m_Players.size() - 1)
			return false;

		return m_Players.get(m_CurrentPlayer) == m_MapInfor.getRole();
	}

	/**
	 * set the vth player as the current player.
	 * 
	 * @param v
	 */
	private synchronized void setCurrentPlayer(int v) {
		m_CurrentPlayer = v;
	}

	/**
	 * return the current player
	 * 
	 * @return
	 */
	private synchronized int getCurrentPlayer() {
		return m_CurrentPlayer;
	}

	/**
	 * increate the current round count.
	 */
	private synchronized void increaseRoundCount() {
		m_CurrentRound++;
	}

	/**
	 * return the end play flag
	 * 
	 * @return
	 */
	private synchronized boolean getIsEndPlay() {
		return m_EndPlay;
	}

	/**
	 * set the end play flag
	 * 
	 * @param v
	 */
	private synchronized void setIsEndPlay(boolean v) {
		m_EndPlay = v;
	}

	/**
	 * return the current round count
	 * 
	 * @return
	 */
	private synchronized int getCurrentRound() {
		return m_CurrentRound;
	}

	/**
	 * set the current round count
	 * 
	 * @param r
	 */
	private synchronized void setCurrentRound(int r) {
		m_CurrentRound = r;
	}

	/**
	 * this is the function to do the play in multi-thread.
	 */
	@Override
	public void run() {
		m_MapInfor.analysisMap();
		setCurrentRound(0);
		setCurrentPlayer(0);

		setIsEndPlay(false);

		while (!getIsEndPlay()) {
			// m_MapInfor.analysisMap();
			decideFirstSequence();
			m_Log.setLogInfor("Start round " + getCurrentRound());
			System.out.println("Start round " + getCurrentRound());
			System.out.println("==================");
			// for(MapItem i:m_Players){
			// System.out.println("ROLE  "+i.getOwnerBlock().toString());
			// }
			System.out.println("Size  " + m_Players.size());

			System.out.println("==================");
			// current round not finished
			while (getCurrentPlayer() < this.m_Players.size()) {
				if (isPlayerRound()) {

				} else {
					if (getCurrentPlayer() == this.m_Players.size())
						break;
					m_Log.setLogInfor("AI play at: " + getCurrentPlayer()
							+ this.getOrdinal(getCurrentPlayer()) + " place");
					System.out.println("Round " + getCurrentRound()
							+ " Monster " + getCurrentPlayer() + " did sth");
					int hp = 0;
					if (m_Players.get(getCurrentPlayer()) instanceof IMapRole) {
						hp = ((MapRole) m_Players.get(getCurrentPlayer()))
								.getGameCharacter().status.getCurrentHP();
					} else {
						hp = ((MapMonster) m_Players.get(getCurrentPlayer()))
								.getGameMonster().status.getCurrentHP();
					}

					if (hp <= 0) {

						switchToNextPlayer();

					} else {
						aiMove(m_Players.get(getCurrentPlayer()));
						if (!notKill)
							break;
					}

					// show this message here because if we show this
					// message in the isPlayerRound(), the message will always
					// be shown.
					if (isPlayerRound()) {
						System.out.println("Round " + getCurrentRound()
								+ " Player  did sth");
						m_Log.setLogInfor("Player play at: "
								+ getCurrentPlayer()
								+ this.getOrdinal(getCurrentPlayer())
								+ " place");
					}
				}
			}

			setCurrentPlayer(0);
			m_Log.setLogInfor("End round " + getCurrentRound());
			System.out.println("Round " + getCurrentRound() + " over");
			increaseRoundCount();
		}
		if (notKill) {
			JOptionPane.showMessageDialog(null, "Game finished.");
			System.out.println("OVER");
			m_battleDraw.R.mapInfor.getRole().getGameCharacter()
					.levelUpByOne(m_Log);
			CharacterSave save = new CharacterSave();
			save.saveCharacter(((MapRole) m_Players.get(0)).getGameCharacter());

			this.m_Players.clear();
			m_battleDraw.R.mapInfor.clear();
		} else {
			JOptionPane.showMessageDialog(null, "Game finished.");
			System.out.println("OVER");
			this.m_Players.clear();
			m_battleDraw.R.mapInfor.clear();
		}

	}

	/**
	 * add the proper suffix to the input integer to make the prompts more
	 * proper.
	 * 
	 * @param i
	 * @return
	 */
	private String getOrdinal(int i) {
		switch (i) {
		case 1:
			return "st";
		case 2:
			return "nd";
		case 3:
			return "rd";
		}
		return "th";
	}

	/**
	 * show the information the the information window.
	 * 
	 * @param blk
	 */
	public void showInformation(MapBlock blk) {
		if (blk == null) {
			return;
		}

		this.m_Log.setLogInfor(blk.toString());
	}
}
