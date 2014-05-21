package GUI.mapView;
/**
 * @author DanZhang
 * SOEN 6441 Team Project 
 * Winter 2014
 */
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import utility.IDrawableUI;
import utility.IMouseClickSupport;
import utility.IMouseMoveSupport;
import utility.LandType;
import utility.UserInterfaceType;
import GUI.mainView.MainCanvas;
/**
 * this is the class to draw the map
 *
 */
public class MapDraw implements IDrawableUI,IMouseClickSupport, IMouseMoveSupport{

	private MainCanvas c;
	private MapShowInfor showInfor;
	public MapListener eHandle;
	private MapResource R;
	private int mapLength;
	private int mapWidth;
	private Point playerLocation;
	/**
	 * constructor of MapDraw
	 * @param c
	 */
	public MapDraw(MainCanvas c) {
		this.mapLength = 20;
		this.mapWidth = 15;
		this.c = c;
		R = new MapResource();
		R.mapInfor.initBlocks(this.mapLength, this.mapWidth);		
		
		showInfor = new MapShowInfor(c);		
		eHandle = new MapListener(c, R, R.mapInfor.getBoard(), showInfor,this);
	}
	
	/**
	 * clear the board 
	 */
	public void clearBoard(){
		R.mapInfor.clearBoard();
	}
	
	/**
	 * draw the chess board
	 * @param g
	 */
	private void chessboardDraw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.white);

		for (int i = 0; i < R.mapInfor.getColumnCount(); i++)
			for (int j = 0; j < R.mapInfor.getRowCount(); j++) {

				MapBlock blk = R.mapInfor.getBlock(i, j);

				if (blk.blankBlock()) {
					g.drawImage(MapResource.imgGrass,
							0 + R.mapInfor.getBlockWidth() * i,
							0 + R.mapInfor.getBlockHeight() * j,
							R.mapInfor.getBlockWidth(),
							R.mapInfor.getBlockHeight(), c);
					blk.setLand(LandType.LAND_GRASS);

				} else {
					g.drawImage(blk.getImage(), 0 + R.mapInfor.getBlockWidth()
							* i, 0 + R.mapInfor.getBlockHeight() * j,
							R.mapInfor.getBlockWidth(),
							R.mapInfor.getBlockHeight(), c);
				}
				g2.setColor(Color.WHITE);
				g2.drawRect(R.mapInfor.getBlockWidth() * i,
						R.mapInfor.getBlockHeight() * j,
						R.mapInfor.getBlockWidth(),
						R.mapInfor.getBlockHeight());				
				
			}
	}
	
	/**
	 * draw the land according to the land type
	 * @param g
	 */
	private void landTypeDraw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawRect(820, 0, MapResource.commandWidth, MapResource.commandHeight);
		Font f = new Font("ss", Font.ITALIC, 30);
		g2.setFont(f);
		g2.drawString("Land   Type", 835, 30);
		g2.drawString("Sand", 800, 80);
		g2.drawString("Grass", 800, 120); // Draw Grass
		g2.drawString("Stone", 800, 160); // Draw Stone
		g2.drawString("Wall_1", 800, 200); // Draw Wall_1
		g2.drawString("Wall_2", 800, 240); // Draw Wall_2

		g.drawImage(MapResource.imgSand, MapResource.sandX, MapResource.sandY, MapResource.blockWidth, MapResource.blockHeight, c);
		g.drawImage(MapResource.imgGrass, MapResource.grassX, MapResource.grassY, MapResource.blockWidth,
				MapResource.blockHeight, c);
		g.drawImage(MapResource.imgStone, MapResource.stoneX, MapResource.stoneY, MapResource.blockWidth,
				MapResource.blockHeight, c);
		g.drawImage(MapResource.imgWall_1, MapResource.wall_1X, MapResource.wall_1Y, MapResource.blockWidth,
				MapResource.blockHeight, c);
		g.drawImage(MapResource.imgWall_2, MapResource.wall_2X, MapResource.wall_2Y, MapResource.blockWidth,
				MapResource.blockHeight, c);
	}
	
	/**
	 * draw the merchant
	 * @param g
	 */
	private void merchantDraw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawRect(820, 280, 190, 40);
		g2.drawString("Monster", 840, 310);
		g2.drawString("Lv1", 800, 360);
		g2.drawString("Lv2", 800, 400); // Draw Grass
		g2.drawString("Lv3", 800, 440); // Draw Stone
		g2.drawString("Lv4", 800, 480); // Draw Wall_1
		g2.drawString("Lv5", 800, 520); // Draw Wall_2

		g.drawImage(MapResource.imgMonster1_1, MapResource.monster1_1X, MapResource.monster1_1Y,
				MapResource.blockWidth, MapResource.blockHeight, c);
		g.drawImage(MapResource.imgMonster1_2, MapResource.monster1_2X, MapResource.monster1_2Y,
				MapResource.blockWidth, MapResource.blockHeight, c);
		g.drawImage(MapResource.imgMonster2_1, MapResource.monster2_1X, MapResource.monster2_1Y,
				MapResource.blockWidth, MapResource.blockHeight, c);
		g.drawImage(MapResource.imgMonster2_2, MapResource.monster2_2X, MapResource.monster2_2Y,
				MapResource.blockWidth, MapResource.blockHeight, c);
		g.drawImage(MapResource.imgMonster3_1, MapResource.monster3_1X, MapResource.monster3_1Y,
				MapResource.blockWidth, MapResource.blockHeight, c);
		g.drawImage(MapResource.imgMonster3_2, MapResource.monster3_2X, MapResource.monster3_2Y,
				MapResource.blockWidth, MapResource.blockHeight, c);
		g.drawImage(MapResource.imgMonster4_1, MapResource.monster4_1X, MapResource.monster4_1Y,
				MapResource.blockWidth, MapResource.blockHeight, c);
		g.drawImage(MapResource.imgMonster4_2, MapResource.monster4_2X, MapResource.monster4_2Y,
				MapResource.blockWidth, MapResource.blockHeight, c);
		g.drawImage(MapResource.imgMonster5_1, MapResource.monster5_1X, MapResource.monster5_1Y,
				MapResource.blockWidth, MapResource.blockHeight, c);
		g.drawImage(MapResource.imgMonster5_2, MapResource.monster5_2X, MapResource.monster5_2Y,
				MapResource.blockWidth, MapResource.blockHeight, c);
	}
	
	/**
	 * draw the monster
	 * @param g
	 */
	private void monsterDraw(Graphics g) {
		g.drawImage(MapResource.imgMerchant_G, MapResource.merchant_GX, MapResource.merchant_GY,
				MapResource.merchantWidth_G, MapResource.merchantHeight_G, c);
		g.drawImage(MapResource.imgTreasure_G, MapResource.treasure_GX, MapResource.merchant_GY,
				MapResource.treasureWidth_G, MapResource.treasureHeight_G, c);
		g.drawImage(MapResource.imgDoorClose, MapResource.door_X, MapResource.door_Y,
				MapResource.treasureWidth_G, MapResource.treasureHeight_G, c);
	}
	
	/**
	 * draw the command area
	 * @param g
	 */
	private void commandDraw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawString("Save Map", 840, 630);
		g2.drawString("Load Map", 840, 670);
		g2.drawString("Random", 840, 710);
		g2.drawString("Back Main", 840, 750);

		g2.drawRect(820, 600, MapResource.commandWidth, MapResource.commandHeight);
		g2.drawRect(820, 640, MapResource.commandWidth, MapResource.commandHeight);
		g2.drawRect(820, 680, MapResource.commandWidth, MapResource.commandHeight);
		g2.drawRect(820, 720, MapResource.commandWidth, MapResource.commandHeight);
	}
	
	/**
	 * draw the character 
	 * @param g
	 */
	private void roleDraw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawRect(10, 610, 30, 140);
		g2.drawString("R", 12, 640);
		g2.drawString("O", 12, 670);
		g2.drawString("L", 12, 700);
		g2.drawString("E", 12, 730);
		g.drawImage(MapResource.imgRole_1_G, MapResource.role_1_GX, MapResource.role_1_GY, MapResource.roleWidth_G,
				MapResource.roleHeight_G, c);
		g.drawImage(MapResource.imgRole_2_G, MapResource.role_2_GX, MapResource.role_2_GY, MapResource.roleWidth_G,
				MapResource.roleHeight_G, c);
		g.drawImage(MapResource.imgRole_3_G, MapResource.role_3_GX, MapResource.role_3_GY, MapResource.roleWidth_G,
				MapResource.roleHeight_G, c);
		g.drawImage(MapResource.imgRole_4_G, MapResource.role_4_GX, MapResource.role_4_GY, MapResource.roleWidth_G,
				MapResource.roleHeight_G, c);
		
		g2.drawString("Row", MapResource.rowStringX,  MapResource.rowStringY);
		g2.drawString("Column", MapResource.columStringX, MapResource.columStringY);
		g.drawImage(MapResource.imgArrowUp, MapResource.rowArrowUpX,MapResource.rowArrowUpY, MapResource.arrowWidth,
				MapResource.arrowHeight, c);
		g.drawImage(MapResource.imgArrowUp, MapResource.columArrowUpX,MapResource.columArrowUpY, MapResource.arrowWidth,
				MapResource.arrowHeight, c);
		g.drawImage(MapResource.imgArrowDown, MapResource.rowArrowDownX,MapResource.rowArrowDownY, MapResource.arrowWidth,
				MapResource.arrowHeight, c);
		g.drawImage(MapResource.imgArrowDown, MapResource.columArrowDownX,MapResource.columArrowDownY, MapResource.arrowWidth,
				MapResource.arrowHeight, c);
		g.drawImage(MapResource.imgIn, MapResource.inX,MapResource.inY, MapResource.inWidth,
				MapResource.inHeight, c);
		g.drawImage(MapResource.imgOut, MapResource.outX,MapResource.outY, MapResource.inWidth,
				MapResource.inHeight, c);
		g.drawImage(MapResource.imgSelect, MapResource.selectX,MapResource.selectY, MapResource.inWidth,
				MapResource.inHeight, c);
		g2.drawString("Col    "+R.mapInfor.getColumnCount(), MapResource.rowNumberX, MapResource.rowNumberY);
		g2.drawString("Row  "+R.mapInfor.getRowCount(), MapResource.columNumberX, MapResource.columNumberY);
		System.out.println("SSS "+R.roleSelectDone);
		if(R.mapInfor.isRoleSelectDone()){
			g2.setColor(Color.RED);
			g2.drawRect(R.mapInfor.getBlockWidth() * R.mapInfor.getPlayerX()+5, R.mapInfor.getBlockHeight() * R.mapInfor.getPlayerY()+5,
					R.mapInfor.getBlockWidth()-10, R.mapInfor.getBlockHeight()-10);
		}
	}
	
	/**
	 * draw the information
	 * @param g
	 */
	private void inforDraw(Graphics g) {
		if (showInfor.onShow()) {
			showInfor.drawInfor(g);
		}
	}
	
	/**
	 * paint the map
	 */
	public void paint(Graphics g) {

		g.drawImage(MapResource.imgMapView, 0, 0, 1024, 800, c);

		chessboardDraw(g);
		landTypeDraw(g);
		monsterDraw(g);
		merchantDraw(g);
		commandDraw(g);
		roleDraw(g);
		inforDraw(g);
	}

	/**
	 * return the block which can be used by XML file
	 * @param location
	 * @return
	 */
	public MapBlock getBlock(Point location) {
		return R.mapInfor.getBlock(location.x,location.y);
	}

	/**
	 * return the player location
	 * @return
	 */
	public Point getPlayerLocation(){
		return this.playerLocation;
		
	}	
	
	/**
	 * my mouse the adapter 
	 *
	 */
	class MyMouseAdapter extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			eHandle.mapPressedListener(e);
		}

		public void mouseReleased(MouseEvent e) {
			eHandle.mapReleasedLisener(e);
		}

		public void mouseClicked(MouseEvent e) {
			eHandle.mapListener(e);
		}
	}
	
	/**
	 * inner class for my mouse motion adapter	
	 *
	 */
	class MyMouseMotionAdapter extends MouseMotionAdapter {
		public void mouseDragged(MouseEvent e){
		eHandle.mapDraggerListener(e);
		}
	}
	
	@Override
	/**
	 * attach mouse listener to canvas
	 */
	public void AttachTo(Canvas mc) {
		for (MouseListener l : mc.getMouseListeners()) {
			mc.removeMouseListener(l);
		}		
		mc.addMouseListener(getMouseAdapter());
		
		for (MouseMotionListener l : mc.getMouseMotionListeners()) {
			mc.removeMouseMotionListener(l);
		}		
		mc.addMouseMotionListener(getMouseMotionAdapter());
	}

	@Override
	/**
	 * return mouse adapter 
	 */
	public MouseAdapter getMouseAdapter() {
		return new MyMouseAdapter();
	}
	
	@Override
	/**
	 * return mouse motion adapter
	 */
	public MouseMotionAdapter getMouseMotionAdapter() {		
		return new MyMouseMotionAdapter();
	}

	@Override
	public UserInterfaceType getInterfaceType() {
		return UserInterfaceType.MAP_UI;
	}

}
