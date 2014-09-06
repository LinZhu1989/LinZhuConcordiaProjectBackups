package GUI.battleView;

/**
 * @author DanZhang
 * SOEN 6441 Team Project 
 * Winter 2014
 */
import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import utility.InfoZone;
import utility.IDrawableUI;
import utility.IMouseClickSupport;
import utility.InOutType;
import utility.MapBlockType;
import utility.TextZone;
import utility.UserInterfaceType;
import Data.ActionHelper.MovableTarget;
import Data.Character.GameCharacter;
import Data.Character.GameFixedItem;

import java.awt.Point;

import javax.swing.ImageIcon;

import GUI.mainView.MainCanvas;
import GUI.mapView.IMapItem;
import GUI.mapView.IMapRole;
import GUI.mapView.MapFixedItemFactory.MapFixedItem;
import GUI.mapView.MapItem;
import GUI.mapView.MapItem.STATUS;
import GUI.mapView.MapResource;
import GUI.mapView.MapRoleFactory.MapRole;
import GUI.mapView.MapMonsterFactory.MapMonster;

/**
 * this is the class to draw the battle view
 * 
 */
public class BattleDraw implements IDrawableUI, IMouseClickSupport {
	protected MainCanvas c;
	protected BattleResource R;
	private MapResource mR;
	private boolean choiceFlag;
	protected BattleListener bl;
	protected BattleControl m_BattleControl;
	BattleLog battleLog;

	public boolean showMonster = false;
	public TextZone zone;
	public InfoZone monsterInfor;

	/**
	 * constructor of BattleDraw
	 * 
	 * @param mainCanvas
	 */
	public BattleDraw(MainCanvas mainCanvas) {
		this.c = mainCanvas;
		R = new BattleResource(this);
		setBattleMapR(new MapResource());
		bl = new BattleListener(this);
		choiceFlag = false;
		battleLog = new BattleLog(mainCanvas, 0, 580, 800, 790);
		m_BattleControl = new BattleControl(this);
		intZone();
	}

	public void intZone() {
		// TODO Auto-generated method stub
		zone = new TextZone(new Point(0, 0), 280, 800, null,
				"./res/GUI/Battle/ShowInfoBack.png");
		zone.setTextColor(Color.RED);
		monsterInfor = new InfoZone(zone);
	}

	/**
	 * get the battleMap
	 * 
	 * @return
	 */
	public MapResource getBattleMapR() {
		return mR;
	}

	public void setBattleMapR(MapResource mR) {
		this.mR = mR;
	}

	/**
	 * paint the battle view
	 */
	public void paint(Graphics g) {

		drawBackground(g);
		drawCommand(g);
		battleLog.draw(g);

		if (R.mapDraw) {
			drawChessboard(g);
		}

		if (showMonster) {
			zone.draw(g);
		}
	}

	/**
	 * draw chess board
	 * 
	 * @param g
	 */
	private void drawChessboard(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		for (int i = 0; i < R.mapInfor.getColumnCount(); i++)
			for (int j = 0; j < R.mapInfor.getRowCount(); j++) {
				if (R.mapInfor.getBlock(i, j).getInOutType() == InOutType.INVALIDOUT
						&& R.outDraw) {
					R.mapInfor.getBlock(i, j).setInOut(InOutType.OUT);
					System.out.println("CHANGE OUT");
				}

				g.drawImage(R.mapInfor.getBlock(i, j).getImage(), 0
						+ R.mapInfor.getBlockWidth() * i,
						0 + R.mapInfor.getBlockHeight() * j,
						R.mapInfor.getBlockWidth(),
						R.mapInfor.getBlockHeight(), c);

				IMapItem item = R.mapInfor.getBlock(i, j).getMapItem();

				if (item != null) {
					String strHP = "";
					Font f = new Font("ss", Font.ITALIC, 15);
					g2.setFont(f);
					if (item.getBlockType() == MapBlockType.BLOCK_ROLE) {
						MapRole role = (MapRole) item;
						strHP = strHP
								+ role.getGameCharacter().status.getCurrentHP();
						g2.drawString(strHP, R.mapInfor.getBlockWidth() * i, 0
								+ R.mapInfor.getBlockHeight() * j);
						
						double bld=R.mapInfor.getBlockWidth()*role.getGameCharacter().status
								.getCurrentHP()/role.getGameCharacter().getFixedMaxHP();
						g2.drawImage(R.blood,
								00 + R.mapInfor.getBlockWidth() * i, (int) (R.mapInfor.getBlockHeight()*0.75
										)+ R.mapInfor.getBlockHeight() * j,  
										(int)bld,(int) (R.mapInfor.getBlockHeight()*0.25),c);
						
						if (((MapItem) item).getStatus() == STATUS.ATTACK) {
							f = new Font("ss", Font.ITALIC, 15);
							g2.setFont(f);
							g2.drawImage(R.fire,
									0 + R.mapInfor.getBlockWidth() * i, 0
											+ R.mapInfor.getBlockHeight() * j,  (int) (R.mapInfor.getBlockWidth()/2),
											(int) (R.mapInfor.getBlockHeight()/2), c);
//							g2.drawString("A", R.mapInfor.getBlockWidth() * i, 20
//									+ R.mapInfor.getBlockHeight() * j);

						}else{
							g2.drawImage(R.happy,
									0 + R.mapInfor.getBlockWidth() * i, 0
											+ R.mapInfor.getBlockHeight() * j, (int) (R.mapInfor.getBlockWidth()/2),
											(int) (R.mapInfor.getBlockHeight()/2), c);
						}
					} else if (item.getBlockType() == MapBlockType.BLOCK_MONSTER) {
						MapMonster monster = (MapMonster) item;
						strHP = strHP
								+ monster.getGameMonster().status
										.getCurrentHP();
						g2.drawString(strHP, R.mapInfor.getBlockWidth() * i, 0
								+ R.mapInfor.getBlockHeight() * j);
						double bld=R.mapInfor.getBlockWidth()*monster.getGameMonster().status
								.getCurrentHP()/monster.getGameMonster().getFixedMaxHP();
						g2.drawImage(R.blood,
								00 + R.mapInfor.getBlockWidth() * i, (int) (R.mapInfor.getBlockHeight()*0.75
										)+ R.mapInfor.getBlockHeight() * j,  
										(int)bld,(int) (R.mapInfor.getBlockHeight()*0.25),c);
						
						if (((MapItem) item).getStatus() == STATUS.ATTACK) {
							f = new Font("ss", Font.ITALIC, 15);
							g2.setFont(f);
							g2.drawImage(R.fire,
									0 + R.mapInfor.getBlockWidth() * i, 0
											+ R.mapInfor.getBlockHeight() * j, (int) (R.mapInfor.getBlockWidth()*0.5),
											(int) (R.mapInfor.getBlockHeight()*0.5), c);
//							g2.drawString("A", R.mapInfor.getBlockWidth() * i, 20
//									+ R.mapInfor.getBlockHeight() * j);

						}else{
							g2.drawImage(R.happy,
									0 + R.mapInfor.getBlockWidth() * i, 0
											+ R.mapInfor.getBlockHeight() * j, (int) (R.mapInfor.getBlockWidth()*0.375),
											(int) (R.mapInfor.getBlockHeight()*0.375), c);
						}
					}
					

				}

				// g2.setColor(Color.WHITE);
				// g2.drawRect(R.mapInfor.getBlockWidth() * i,
				// R.mapInfor.getBlockHeight() * j,
				// R.mapInfor.getBlockWidth(),
				// R.mapInfor.getBlockHeight());
			}

		if (R.mapInfor.isRoleSelectDone()) {
			g2.setColor(Color.RED);
			g2.drawRect(R.mapInfor.getBlockWidth() * R.mapInfor.getPlayerX()
					+ 5, R.mapInfor.getBlockHeight() * R.mapInfor.getPlayerY()
					+ 5, R.mapInfor.getBlockWidth() - 10,
					R.mapInfor.getBlockHeight() - 10);
		}
		if (R.moveDraw) {
			for (MovableTarget i : R.moveResult) {
				// System.out.println(i.getTarget().x+"  "+i.getTarget().y);
				g2.drawRect(R.mapInfor.getBlockWidth() * i.getTarget().x + 5,
						R.mapInfor.getBlockHeight() * i.getTarget().y + 5,
						R.mapInfor.getBlockWidth() - 10,
						R.mapInfor.getBlockHeight() - 10);
			}

			R.moveDraw = false;
		}

		if (R.pathDraw) {
			for (Point i : R.path) {
				g2.drawRect(R.mapInfor.getBlockWidth() * i.x + 5,
						R.mapInfor.getBlockHeight() * i.y + 5,
						R.mapInfor.getBlockWidth() - 10,
						R.mapInfor.getBlockHeight() - 10);
			}
			R.pathDraw = false;
		}

		if (R.attackDraw) {
			for (Point i : R.attackBlks) {
				g2.drawRect(R.mapInfor.getBlockWidth() * i.x + 5,
						R.mapInfor.getBlockHeight() * i.y + 5,
						R.mapInfor.getBlockWidth() - 10,
						R.mapInfor.getBlockHeight() - 10);

			}
			R.attackDraw = false;
		}
	}

	/**
	 * draw command area
	 * 
	 * @param g
	 */
	private void drawCommand(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(5f, BasicStroke.CAP_SQUARE,
				BasicStroke.JOIN_ROUND));
		g2.setColor(Color.BLACK);
		Font f = new Font("ss", Font.BOLD, 25);
		g2.setFont(f);
		g2.drawRect(R.rectExitX, R.rectExitY, R.rectCommandWidth,
				R.rectCommandHeight);
		g2.drawRect(R.saveX, R.saveY, R.rectCommandWidth, R.rectCommandHeight);
		g2.drawRect(R.loadGameX, R.loadGameY, R.rectCommandWidth,
				2 * R.rectCommandHeight);
		g2.drawRect(R.loadMapX, R.loadMapY, R.rectCommandWidth,
				2 * R.rectCommandHeight);
		g2.drawRect(R.heroX, R.heroY, R.rectCommandWidth, R.rectCommandHeight);
		g2.drawRect(R.itemX, R.itemY, R.rectCommandWidth, R.rectCommandHeight);
		g2.drawRect(R.attackX, R.attackY, R.rectCommandWidth,
				R.rectCommandHeight);
		g2.drawRect(R.restX, R.restY, R.rectCommandWidth, R.rectCommandHeight);
		g2.drawRect(R.moveX, R.moveY, R.rectCommandWidth, R.rectCommandHeight);
		g2.drawRect(R.skillX, R.skillY, R.rectCommandWidth, R.rectCommandHeight);

		g2.drawImage(new ImageIcon("./res/GUI/Battle/button.png").getImage(), R.rectExitX, R.rectExitY, R.rectCommandWidth,
				R.rectCommandHeight, null);
		g2.drawImage(new ImageIcon("./res/GUI/Battle/button.png").getImage(), R.saveX, R.saveY, R.rectCommandWidth, R.rectCommandHeight, null);
		g2.drawImage(new ImageIcon("./res/GUI/Battle/button.png").getImage(),R.loadGameX, R.loadGameY, R.rectCommandWidth,
				2 * R.rectCommandHeight, null);
		g2.drawImage(new ImageIcon("./res/GUI/Battle/button.png").getImage(), R.loadMapX, R.loadMapY, R.rectCommandWidth,
				2 * R.rectCommandHeight, null);
		
		g2.drawImage(new ImageIcon("./res/GUI/Battle/button.png").getImage(), R.heroX, R.heroY, R.rectCommandWidth,
				R.rectCommandHeight, null);
		g2.drawImage(new ImageIcon("./res/GUI/Battle/button.png").getImage(), R.itemX, R.itemY, R.rectCommandWidth,
				R.rectCommandHeight, null);
		g2.drawImage(new ImageIcon("./res/GUI/Battle/button.png").getImage(), R.attackX, R.attackY, R.rectCommandWidth,
				R.rectCommandHeight, null);
		g2.drawImage(new ImageIcon("./res/GUI/Battle/button.png").getImage(), R.restX, R.restY, R.rectCommandWidth,
				R.rectCommandHeight, null);
		g2.drawImage(new ImageIcon("./res/GUI/Battle/button.png").getImage(), R.moveX, R.moveY, R.rectCommandWidth,
				R.rectCommandHeight, null);
		g2.drawImage(new ImageIcon("./res/GUI/Battle/button.png").getImage(), R.skillX, R.skillY, R.rectCommandWidth,
				R.rectCommandHeight, null);
		
		
		g2.setColor(Color.RED);
		g2.drawString(" Main", R.rectExitX, R.rectExitY
				+ R.rectCommandHeight - 10);
		g2.drawString(" Save", R.saveX , R.saveY + R.rectCommandHeight - 10);
		g2.drawString(" Load", R.loadMapX , R.loadMapY + R.rectCommandHeight
				- 10);
		g2.drawString(" Map", R.loadMapX , R.loadMapY + 2
				* R.rectCommandHeight - 15);
		g2.drawString(" Show", R.loadGameX , R.loadGameY
				+ R.rectCommandHeight - 10);
		g2.drawString(" Role", R.loadGameX , R.loadGameY + 2
				* R.rectCommandHeight - 15);
		g2.drawString(" OPEN", R.heroX, R.heroY + R.rectCommandHeight - 10);
		g2.drawString(" ITEM", R.itemX , R.itemY + R.rectCommandHeight - 10);
		g2.drawString(" MOVE", R.moveX , R.moveY + R.rectCommandHeight - 10);
		g2.drawString(" SKILL", R.skillX , R.skillY + R.rectCommandHeight
				- 10);

		g2.drawString(" REST", R.restX , R.restY + R.rectCommandHeight - 10);
		f = new Font("ss", Font.BOLD, 20);
		g2.setFont(f);
		g2.drawString("ATTACK", R.attackX , R.attackY + R.rectCommandHeight
				- 10);

		if (choiceFlag) {
			g2.drawRect(R.YesX, R.YesY, R.rectCommandWidth, R.choiceHeight);
			g2.drawRect(R.NoX, R.NoY, R.rectCommandWidth, R.choiceHeight);
			f = new Font("ss", Font.ITALIC, 15);
			g2.setFont(f);
			g2.drawString("Y  E  S", R.YesX + 5, R.YesY + R.choiceHeight - 5);
			g2.drawString(" N  O", R.NoX + 5, R.NoY + R.choiceHeight - 5);
		}

	}

	/**
	 * draw background area
	 * 
	 * @param g
	 */
	private void drawBackground(Graphics g) {
		g.drawImage(R.imgBoard, 0, 0, 800, 600, c); // DrawLeftPart
		g.drawImage(R.imgCommandBackground, 800, 0, 224, 800, c); // DrawLeftPart
		g.drawImage(R.imgLogBackground, 0, 600, 800, 200, c); // DrawLeftPart
		g.drawImage(R.imgFrameHorizonal, 0, 600, 800, 10, c); // DrawLeftPart
		g.drawImage(R.imgFrameHorizonal, 800, R.loadGameY - 15, 800, 10, c); // DrawLeftPart
		g.drawImage(R.imgFrameHorizonal, 800, R.heroY - 15, 800, 10, c);
		g.drawImage(R.imgFrameHorizonal, 800, R.attackY - 70, 800, 10, c);
		g.drawImage(R.imgFrameHorizonal, 800, 10, 800, 10, c);
	}

	class MyMouseAdapter extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			bl.mouseClickedListener(e);
		}
	}

	@Override
	/**
	 * attach mouse to listener
	 */
	public void AttachTo(Canvas mc) {
		for (MouseListener l : mc.getMouseListeners()) {
			mc.removeMouseListener(l);
		}

		mc.addMouseListener(getMouseAdapter());

		for (MouseMotionListener l : mc.getMouseMotionListeners()) {
			mc.removeMouseMotionListener(l);
		}
	}

	@Override
	/**
	 * attach mouse to listener
	 */
	public MouseAdapter getMouseAdapter() {
		return new MyMouseAdapter();
	}

	@Override
	public UserInterfaceType getInterfaceType() {
		return UserInterfaceType.BATTLE_UI;
	}

	public GameCharacter getCurrentCharacter() {
		MapRole role = (MapRole) this.R.mapInfor.getBlock(
				this.R.mapInfor.getPlayerLocation()).getMapItem();

		if (role == null)
			return null;

		return role.getGameCharacter();
	}

	public GameFixedItem getCurrentFixedItem() {
		MapFixedItem chest = (MapFixedItem) this.R.mapInfor.getBlock(
				this.R.mapInfor.getPlayerLocation()).getFixedItem();

		if (chest == null)
			return null;

		return chest.getGameFixedItem();
	}

}
