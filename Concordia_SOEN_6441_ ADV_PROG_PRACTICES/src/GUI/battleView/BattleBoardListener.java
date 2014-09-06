package GUI.battleView;

/**
 * @author DanZhang
 * SOEN 6441 Team Project 
 * Winter 2014
 */
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Scanner;

import Data.ActionHelper.CheckAroundHelper;
import Data.ActionHelper.MovableTarget;
import Data.Character.GameMonster;
import GUI.mapView.MapBlock;
import GUI.mapView.MapFixedItemFactory.MapFixedItem;
import GUI.mapView.MapMonsterFactory.MapMonster;
import GUI.mapView.MapRoleFactory.MapRole;
import GUI.mapView.MapItem;
import GUI.mapView.MapResource;

import java.awt.Point;

import javax.swing.JOptionPane;

import utility.FightResultType;
import utility.InOutType;
import utility.LandType;
import utility.MapBlockType;
import utility.UserInterfaceType;

/**
 * this is the listener of the battle view
 * 
 */
public class BattleBoardListener implements Runnable {
	BattleListener bl;

	private int m_x;
	private int m_y;
	private MapItem m_MovingItem;

	enum BOARD_ACTION {
		ACTION_MOVE,
		ACTION_ATTACK,
		ACTION_CHECK_INFO
	}
	
	/**
	 * constructor of battle board listener
	 * @param bl
	 */
	public BattleBoardListener(BattleListener bl) {
		this.bl = bl;
		m_MovingItem = null;
	}

	/**
	 * handle the action of chess board
	 * 
	 * @param mx
	 * @param my
	 */
	public void boardAction(int mx, int my) {
		System.out.println("CHECK "+checkAction());
		this.bl.bd.showMonster=false;
		switch (checkAction()) {
		case ACTION_MOVE:
			Thread t = changeMove(mx, my, null);
		
			synchronized (bl.bd.R.moveLock) {
				try {
					t.start();
				} catch (Exception e) {

				}
			}
			break;
		case ACTION_ATTACK:
			attackAction(mx,my);
			break;
		case ACTION_CHECK_INFO:
			showInfo(mx,my);
			break;
		}
	}
	/**
	 * show the selected block information
	 * @param mx
	 * @param my
	 */
	private void showInfo(int mx, int my) {
		m_x = mx / bl.R.mapInfor.getBlockWidth();
		m_y = my / bl.R.mapInfor.getBlockHeight();
		
		MapBlock blk = bl.R.mapInfor.getBlock(m_x, m_y);
		if (blk == null) {
			assert(false);
			return;
		} 
		if(blk.getMapItem()!=null){
			if(blk.getMapItem().getBlockType()==MapBlockType.BLOCK_MONSTER){
				showMonsterInfo(blk);
				System.out.println("Show Monster Info");
			}
			
			if(blk.getMapItem().getBlockType()==MapBlockType.BLOCK_ROLE){
				showRoleInfo(blk);
				System.out.println("Show Role Info");
			}
		}
		
		
		bl.bd.m_BattleControl.showInformation(blk);
	}
	
	
	private void showMonsterInfo(MapBlock blk){
		bl.bd.intZone();
		bl.bd.monsterInfor.initMonsterInfoLabels(50, 50, 300,
				600, ((MapMonster)blk.getMapItem()).getGameMonster());
		bl.bd.monsterInfor.setTextColor(Color.RED);
		bl.bd.showMonster=true;
	}
	
	private void showRoleInfo(MapBlock blk){
		bl.bd.intZone();
		bl.bd.monsterInfor.initCharacterInfoLabels(50, 50, 300,
				600, ((MapRole)blk.getMapItem()).getGameCharacter());
		bl.bd.monsterInfor.setTextColor(Color.RED);
		bl.bd.showMonster=true;
	}

	/**
	 * given the certain point on map and check if it can be attack
	 * @param mx
	 * @param my
	 */
	private void attackAction(int mx, int my) {
		m_x = mx / bl.R.mapInfor.getBlockWidth();
		m_y = my / bl.R.mapInfor.getBlockHeight();
		System.out.println("555555" + m_x + "  " + m_y);
		
		
		if(checkAttack(m_x,m_y)){
			System.out.println("666666666");
			FightResultType result =BattleOperation.battleFightOperation(
					bl.R.mapInfor.getBlock(bl.R.mapInfor.getPlayerLocation()),
					bl.R.mapInfor.getBlock(new Point(m_x,m_y)),bl.bd.battleLog,true);
			System.out.println("7777777");
			if(result.equals(FightResultType.FIGHT_KILL)){

				((MapItem)bl.R.mapInfor.getBlock(bl.R.mapInfor.getPlayerLocation()).getMapItem()).beHappy();
				bl.R.mapInfor.getBlock(new Point(m_x,m_y)).clearItem();
				bl.R.mapInfor.analysisMap();
				if(bl.R.mapInfor.m_monsters.size()==0)
					bl.R.outDraw=true;
			}
			else
				((MapItem)bl.R.mapInfor.getBlock(new Point(m_x,m_y)).getMapItem()).beAttacked();
			bl.bd.m_BattleControl.switchToNextPlayer();

		}
		bl.bd.c.repaint();
	}

	/**
	 * new and return the move thread for the character. so that later on we can
	 * use this thread to move the role around.
	 * 
	 * @param mx
	 * @param my
	 * @return
	 */
	public Thread changeMove(int mx, int my, MapItem item) {
		m_MovingItem = item;
		m_x = mx / bl.R.mapInfor.getBlockWidth();
		m_y = my / bl.R.mapInfor.getBlockHeight();
		Thread t = new Thread(this);
		
		return t;
	}
	/**
	 * check the the certain block can not be attack
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean checkAttack(int x,int y){
		for (Point i : bl.R.attackBlks) {
			if (i.getX() == x && i.getY() == y) {
				return true;
			}
		}
		return false;
	}
	/**
	 * check the certain block can not be move  
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean checkMove(int x, int y) {
		for (MovableTarget i : bl.R.moveResult) {
			if (i.getTarget().x == x && i.getTarget().y == y) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * check the block action 
	 * @return
	 */
	private BOARD_ACTION checkAction() {

		if (bl.onMove) {
			return BOARD_ACTION.ACTION_MOVE;
		} else if(bl.onAttack) {
			return BOARD_ACTION.ACTION_ATTACK;
		} else {//now we are just click on any mapBlock on the map
			return BOARD_ACTION.ACTION_CHECK_INFO;
		}
	}

	/**
	 * left mouse button event handle
	 * 
	 * @param mx
	 * @param my
	 */
	public void boardActionLeftClick(int mx, int my) {

		switch (checkActionLeft()) {
		case 1:
			showPath(mx, my);
			break;
		}
	}

	/**
	 * show the move path
	 * 
	 * @param mx
	 * @param my
	 */
	public void showPath(int mx, int my) {

		int x = mx / bl.R.mapInfor.getBlockWidth();
		int y = my / bl.R.mapInfor.getBlockHeight();

		for (MovableTarget i : bl.R.moveResult) {
			if (i.getTarget().x == x && i.getTarget().y == y) {
				// System.out.println("");
				bl.R.path = i.getPathSet();
				bl.R.moveDraw = false;
				bl.R.pathDraw = true;
			}
		}

		bl.onPath = false;
		bl.bd.c.repaint();
		new Thread(this).start();
	}

	/**
	 * return the status of left click
	 * 
	 * @return
	 */
	private int checkActionLeft() {

		if (bl.onPath)
			return 1;
		return 0;
	}

	@Override
	/**
	 * new thread of each step of move 
	 */
	public void run() {
		CheckAroundHelper door = new CheckAroundHelper();
		synchronized (bl.bd.R.moveLock) {
			if (checkMove(m_x, m_y)) {

				bl.onMove = false;
				bl.R.moveDraw = false;
				for (MovableTarget i : bl.R.moveResult) {
					if (i.getTarget().x == m_x && i.getTarget().y == m_y) {
						bl.R.path = i.getPathSet();
						bl.R.moveDraw = false;
						bl.R.pathDraw = true;
					}
				}

				bl.onPath = true;
				Thread animationThread = new Thread() {
					@Override
					public void run() {
						bl.bd.c.repaint(); // Refresh the display

					}
				};
				animationThread.start();

				int node = 1;
				for (Point j : bl.R.path) {
					if (node != 1) {
						if (m_MovingItem == null) {
							bl.R.mapInfor.getBlock(bl.R.mapInfor.getPlayerX(),
									bl.R.mapInfor.getPlayerY()).moveRoleTo(
									bl.R.mapInfor.getBlock(j.x, j.y),
									bl.R.mapInfor);
						} else {
							m_MovingItem.getOwnerBlock().moveRoleTo(
									bl.R.mapInfor.getBlock(j.x, j.y), null);
						}
						bl.bd.c.repaint();
					}
					node++;

					try {
						Thread.sleep(100); // delay and yield to other threads
					} catch (InterruptedException ex) {
					}

					try {
						animationThread.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				// All over the point in the path
				bl.R.pathDraw = false;
				bl.bd.c.repaint();

				// inform the controller the user round is over.
				bl.bd.m_BattleControl.switchToNextPlayer();

				bl.bd.R.moveLock.notify();
				MapBlock block = bl.R.mapInfor.getBlock(m_x, m_y);
				
				if(block.getLand()==LandType.LAND_DOOR_CLOSED)
					block.setLand(LandType.LAND_DOOR_OPENED);
				else if(bl.R.mapInfor.getBlock(bl.R.mapInfor.getPlayerX(),
						bl.R.mapInfor.getPlayerY()).getFixedItem() != null){
					
					block = bl.R.mapInfor.getBlock(bl.R.mapInfor.getPlayerX(),bl.R.mapInfor.getPlayerY());
					if(((MapFixedItem) block.getFixedItem()).getBlockType()==MapBlockType.BLOCK_MERCHANT){
						bl.bd.m_BattleControl.showInformation(block);
						bl.bd.c.repaint();
					}
					else
					{
						bl.bd.m_BattleControl.showInformation(block);
						bl.bd.c.repaint();						
					}
				}
				if (bl.R.mapInfor.getBlock(m_x, m_y).getInOutType() == InOutType.OUT) {
					// quit the controller thread.
					bl.bd.m_BattleControl.endPlay();
				}				
			}
		}
	}

}
