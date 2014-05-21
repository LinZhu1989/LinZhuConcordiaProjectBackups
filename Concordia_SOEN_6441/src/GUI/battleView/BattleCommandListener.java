package GUI.battleView;

/**
 * @author DanZhang
 * SOEN 6441 Team Project 
 * Winter 2014
 */

import java.util.ArrayList;

import Data.ActionHelper.AttackHelper;
import Data.ActionHelper.MoveHelper;
import Data.Builder.*;
import Data.Character.GameCharacter;
import Data.dataIO.CharacterLoad;
import Data.dataIO.MapLoad;
import GUI.mapView.MapBlock;
import GUI.mapView.MapInfor;
import GUI.mapView.MapRandom;
import GUI.mapView.MapResource;

import java.awt.Point;

import utility.UserInterfaceType;

public class BattleCommandListener {

	private BattleListener bl;
	private BattleResource R;
	private boolean onLoad;
	private MoveHelper moveHelper;
	private boolean judgeMapLoad = false;

	/**
	 * constructor of BattleCommandListener
	 * 
	 * @param bl
	 */
	public BattleCommandListener(BattleListener bl) {
		this.bl = bl;
		this.R = bl.R;
		onLoad = false;

		moveHelper = new MoveHelper();
	}

	void commandAction(int mx, int my) {
		System.out.println("Command");
		switch (checkCommandArea(mx, my)) {
		case 1:
			if (judgeMapLoad == true) {
				System.out.println("Attack");
				attackAction();
			}
			break;
		case 2:
			if (judgeMapLoad == true) {
				moveAction();
				System.out.println("Move");
			}
			break;
		case 3:

			if (judgeMapLoad == true) {
				System.out.println("Open");
				bl.bd.c.switchTo(UserInterfaceType.CHEST_UI, bl.bd);
				bl.bd.c.repaint();
			}
			break;
		case 4:

			if (judgeMapLoad == true) {
				bl.bd.battleLog.setLogInfor("Show Role");
				bl.bd.c.repaint();
				bl.bd.c.switchTo(UserInterfaceType.CHARACTER_UI, bl.bd);
				bl.bd.c.repaint();
			}
			System.out.println("Show Role");
			break;
		case 5:
			System.out.println("Save");
			break;
		case 6:
			System.out.println("Skill");
			break;
		case 7:
			System.out.println("Rest");
			break;
		case 8:

			if (judgeMapLoad == true) {
				System.out.println("Item");
				bl.bd.c.switchTo(UserInterfaceType.ITEM_UI, bl.bd);
			}

			break;
		case 9:
			System.out.println("Load Map");
			loadMapAction();
			break;
		case 10:
			System.out.println("Exit");
			bl.bd.c.switchTo(UserInterfaceType.MAIN_UI, bl.bd);
			bl.bd.c.repaint();
			judgeMapLoad = false;
			break;
		}
	}

	/**
	 * reaction to the click on the move menu
	 */
	private void moveAction() {
		if (onLoad) {
			R.moveResult = moveHelper.calMoveBlocks(R.mapInfor, 6);

			bl.onPath = true;
			bl.onMove = true;
			R.moveDraw = true;
			bl.R.pathDraw = false;

			bl.bd.c.repaint();
		}
	}

	/**
	 * reaction to the click on the attack menu
	 */
	private void attackAction() {
		AttackHelper atkHelper = new AttackHelper();
		R.attackBlks = atkHelper.calAttackBlocks(R.mapInfor,
				R.mapInfor.getPlayerLocation(), false);
		System.out.println("Blocks can be Attacked:");
		R.attackDraw = true;
		bl.onAttack = true;
		for (int i = 0; i < R.attackBlks.size(); i++) {
			System.out.println(R.attackBlks.get(i).x + ","
					+ R.attackBlks.get(i).y);
		}
		bl.bd.c.repaint();
	}

	/**
	 * random map
	 */
	private void useRandomMap() {
		try {
			MapResource r = new MapResource();
			MapRandom randomMap = new MapRandom(r, R.mapInfor);

			MapBlock[][] temp = randomMap.create();
			int colcount = temp.length;
			int rowcount = temp[0].length;

			R.mapInfor.setColumnCount(colcount);
			R.mapInfor.setRowCount(rowcount);

			R.mapInfor.initBlocks(R.mapInfor.getColumnCount(),
					R.mapInfor.getRowCount());

			for (int i = 0; i < R.mapInfor.getColumnCount(); i++) {
				for (int j = 0; j < R.mapInfor.getRowCount(); j++) {
					R.mapInfor.getBoard()[i][j] = null;
				}
			}

			for (int i = 0; i < R.mapInfor.getColumnCount(); i++) {
				for (int j = 0; j < R.mapInfor.getRowCount(); j++) {
					R.mapInfor.getBoard()[i][j] = temp[i][j];
				}
			}
			bl.bd.m_BattleControl.setMap(R.mapInfor);
			R.mapInfor.analysisMap();

			System.out.println("&&&&&&&&& " + R.mapInfor.getMonster().size());

		} catch (Exception e) {
			return;
		}
	}

	/**
	 * load map
	 */
	private void loadMapAction() {

		// useRandomMap();

		MapLoad ml = new MapLoad();
		judgeMapLoad = true;
		// R.mapInfor = new MapInfor();

		try {
			if (ml.load(R.mapInfor) == false)
				return;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// System.out.println(getBattleMapR().mapInfor.getColumnCount()+" "+getBattleMapR().mapInfor.getRowCount());
		CharacterLoad load = new CharacterLoad();
		GameCharacter newHero = new GameCharacter("No Items");

		int result = load.characterLoad(newHero);
		// System.out.println("Result "+result);
		if (result == -1) {

			R.mapInfor = null;
			return;
		} else if (result == 1) {
			int x = R.mapInfor.getPlayerX();
			int y = R.mapInfor.getPlayerY();

			MapBlock blk = R.mapInfor.getBlock(x, y);

			if (blk != null) {
				blk.setRole(newHero.ChaInfo.getType(), newHero);
			}

			this.updateMapAction();
			System.out.println("-> updateMapAction DONE!");
		}
		bl.bd.m_BattleControl.setMap(R.mapInfor);

		onLoad = true;
		R.mapDraw = true;
		R.moveDraw = false;
		bl.R.pathDraw = false;
		bl.R.attackDraw = false;
		bl.R.outDraw = false;
		bl.bd.c.repaint();

		if (m_PlayThread == null) {
			m_PlayThread = new Thread(bl.bd.m_BattleControl);
		} else {
			bl.bd.m_BattleControl.endPlay();
			try {
				m_PlayThread.join();
				m_PlayThread = null;
				m_PlayThread = new Thread(bl.bd.m_BattleControl);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (m_PlayThread != null)
			m_PlayThread.start();

	}

	/**
	 * load map
	 */
	private void updateMapAction() {
		System.out.println("-> updateMapAction!");
		MapDirector mapDir = new MapDirector();
		MapUpdatedBuilder mapUper = new MapUpdatedBuilder();
		mapDir.setMapBuilder(mapUper);
		mapDir.updatetMap(R.mapInfor, bl.bd.battleLog);
	}

	private Thread m_PlayThread = null;

	public void update() {

	}

	/**
	 * check commad area
	 * 
	 * @param mx
	 * @param my
	 * @return
	 */
	private int checkCommandArea(int mx, int my) {

		if (mx > R.attackX && mx < R.attackX + R.rectCommandWidth) {
			if (my > R.attackY && my < R.attackY + R.rectCommandHeight) {
				return 1;
			} else if (my > R.moveY && my < R.moveY + R.rectCommandHeight) {
				return 2;
			}

			else if (my > R.heroY && my < R.heroY + R.rectCommandHeight) {
				return 3;
			} else if (my > R.loadGameY
					&& my < R.loadGameY + 2 * R.rectCommandHeight) {
				return 4;
			}

			else if (my > R.saveY && my < R.saveY + R.rectCommandHeight) {
				return 5;
			}

		} else if (mx > R.skillX && mx < R.skillX + R.rectCommandWidth) {
			if (my > R.skillY && my < R.skillY + R.rectCommandHeight) {
				return 6;
			} else if (my > R.restY && my < R.restY + R.rectCommandHeight) {
				return 7;
			} else if (my > R.itemY && my < R.itemY + R.rectCommandHeight) {
				return 8;
			} else if (my > R.loadMapY
					&& my < R.loadMapY + 2 * R.rectCommandHeight) {
				return 9;
			} else if (my > R.rectExitY
					&& my < R.rectExitY + R.rectCommandHeight) {
				return 10;
			}

		}
		return 0;
	}

}
