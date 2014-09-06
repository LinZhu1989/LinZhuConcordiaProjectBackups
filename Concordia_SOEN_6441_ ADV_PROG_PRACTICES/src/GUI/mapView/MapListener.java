package GUI.mapView;
/**
 * @author Jingang.Li
 * SOEN 6441 Team Project 
 * Winter 2014
 */
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;

import utility.FixedItemType;
import utility.InOutType;
import utility.LandType;
import utility.MapBlockType;
import utility.MonsterType;
import utility.RoleType;
import utility.UserInterfaceType;
import Data.dataIO.MapLoad;
import Data.dataIO.MapSave;
import GUI.mainView.MainCanvas;

public class MapListener {
	private MapRandom randomMap;
	private Component cp;
	private MapShowInfor showInfor;
	private MapResource R;
	private MapBlock board[][] = null;
	private boolean onDrag;
	private MainCanvas c;
	private MapDraw md;
	
	/**
	 * use this enum to describe the different 
	 * blocks selected by the user.
	 */
	enum CMD_TYPE {
		UNDEFINED,
		
		LAND_SAND,
		LAND_GRASS,
		LAND_STONE,
		LAND_HOR_WALL,
		LAND_VER_WALL,
		LAND_DOOR_OPEN,
		LAND_DOOR_ClOSE,
		ROLE_BULLY,
		ROLE_NIMBLE,
		ROLE_TANK,

		MONSTER_1,
		MONSTER_2,
		MONSTER_3,
		MONSTER_4,
		MONSTER_5,
		MONSTER_6,
		MONSTER_7,
		MONSTER_8,
		MONSTER_9,
		MONSTER_10,
		
		BLK_IN,
		BLK_OUT,
		
		BLK_MERCHANT,
		BLK_CHEST				
	}
	
	CMD_TYPE m_CurrentBlk;
	/**
	 * the constructor
	 * @param c
	 * @param R
	 * @param board
	 * @param showInfor
	 * @param md
	 */
	public  MapListener(MainCanvas c,MapResource R, MapBlock[][] board,MapShowInfor showInfor,MapDraw md){
		this.R=R;
		this.c=c;
		this.showInfor=showInfor;
		onDrag=false;
		this.board=board;
		randomMap= new MapRandom(R,R.mapInfor);
		this.md=md;
		m_CurrentBlk = CMD_TYPE.UNDEFINED;
		
	}
	/**
	 * main function of map listener
	 * @param e
	 */
	public void mapListener(MouseEvent e) {
		int mx=e.getX();
		int my=e.getY();
		cp=e.getComponent();
		if(e.getButton() == MouseEvent.BUTTON1){
			if(showInfor.onShow())
			showInfor.stopInfor();
			switch(checkActionArea(mx,my)){
			
			case 0: //In chessboard
					chessboardAction(mx,my);
					System.out.println("chessboard");
					break;
			case 1: //In land Type
					landAction(mx,my);
					System.out.println("land type");
					break;
			case 2: //In Role type
					roleAction(mx,my);
					System.out.println("Role");
					break;
			case 3: //In Command type
					commandAction(mx,my);
					System.out.println("Command");	
					break;
			case 4:// Merchant & Treasure
				    merchantAction(mx,my);
					System.out.println("Merchant");
					break;
			case 5://Monster
					monsterAction(mx,my);
					System.out.println("Monster");
			}	
		}
		else{
	switch(checkActionArea(mx,my)){
			
			case 0: //In chessboard
					chessboardInfor(mx,my);
					System.out.println("chessboard");
					break;
			case 1: //In land Type
					landinfor(mx,my);
					System.out.println("land type");
					break;
			case 2: //In Role type
					roleinfor(mx,my);
					System.out.println("Role");
					break;
			case 3: //In Command type
					commandinfor(mx,my);
					System.out.println("Command");	
					break;
			case 4:// Merchant & Treasure
				    merchantinfor(mx,my);
					System.out.println("Merchant");
					break;
			case 5://Monster
					monsterinfor(mx,my);
					
			}	
		}
	}
	
	/**
	 * method of map drag listener
	 * @param e
	 */
	public void mapDraggerListener(MouseEvent e) {
		int mx=e.getX();
		int my=e.getY();
		int x;
		int y;
		if(mx<800 && my<600 && R.imgCursor!=R.imgIn && R.imgCursor!=R.imgOut && R.imgCursor!=R.imgSelect) {
			x=mx/R.mapInfor.getBlockWidth();
			y=my/R.mapInfor.getBlockHeight();
			int minX;
			int maxX;
			int minY;
			int maxY;
			if(x<R.firstX/R.mapInfor.getBlockWidth())
			{
				minX=x;
				maxX=R.firstX/R.mapInfor.getBlockWidth();
			}				
			else
			{
				minX=R.firstX/R.mapInfor.getBlockWidth();
				maxX=x;
			}
				
			if(y<R.firstY/R.mapInfor.getBlockHeight())
			{
				minY=y;
				maxY=R.firstY/R.mapInfor.getBlockHeight();
			}				
			else
			{
				minY=R.firstY/R.mapInfor.getBlockHeight();
				maxY=y;
			}
			System.out.println("MinX "+minX+"MAXx "+maxX+"MinY "+minY+"MAXY "+maxY);
			for(int i=minX;i<=maxX;i++)
				for(int j=minY;j<=maxY;j++){					
					R.mapInfor.getBoard()[i][j].setLand(getCurrentLandType());
					R.mapInfor.getBoard()[i][j].clearItem();
				}
			cleanCursor();
			c.repaint();
		}
	}
	
	/**
	 * get the current land type
	 * @return
	 */
	private LandType getCurrentLandType() {
		switch (this.m_CurrentBlk) {
		case LAND_GRASS:
			return LandType.LAND_GRASS;
		case LAND_SAND:
			return LandType.LAND_SAND;
		case LAND_STONE:
			return LandType.LAND_STONE;
		case LAND_HOR_WALL:
			return LandType.LAND_HOR_WALL;
		default:
			return LandType.LAND_VER_WALL;
		}
	}
	
	/**
	 * method of map pressed listener
	 * @param e
	 */
	public void mapPressedListener(MouseEvent e) {
		int mx=e.getX();
		int my=e.getY();
		if(onDrag){
			if(mx<800&&my<600)
			{
				R.firstX=mx;
				R.firstY=my;
				System.out.println("F"+mx+" "+my);
				onDrag=false;
			}
		}		
	}
	
	/**
	 * this method is called when mouse release
	 * @param e
	 */
	public void mapReleasedLisener(MouseEvent e){
		onDrag=true;
	}

	/**
	 * show the monster information
	 * @param mx
	 * @param my
	 */
	private void monsterinfor(int mx, int my) {
		System.out.println("Monster Create");
		monsterAction(mx,my);
		showInfor.loadInfor(mx, my, "Monster\nH\nasdads\nwqrew\nsaa", R.imgCursor);
		c.repaint();
	}
	
	/**
	 * show the merchant information
	 * @param mx
	 * @param my
	 */
	private void merchantinfor(int mx, int my) {
		merchantAction(mx,my);
		showInfor.loadInfor(mx, my, "Monster\nH\nasdads\nwqrew\nsaa", R.imgCursor);
		c.repaint();
	}
	/**
	 * show the command information
	 * @param mx
	 * @param my
	 */
	private void commandinfor(int mx, int my) {
		
	}
	
	/**
	 * show the role information
	 * @param mx
	 * @param my
	 */
	private void roleinfor(int mx, int my) {
		roleAction(mx,my);
		showInfor.loadInfor(mx, my, "Monster\nH\nasdads\nwqrew\nsaa", R.imgCursor);
		c.repaint();
		
	}
	/**
	 * show the land information
	 * @param mx
	 * @param my
	 */
	private void landinfor(int mx, int my) {
		landAction(mx,my);
		showInfor.loadInfor(mx, my, "Monster\nH\nasdads\nwqrew\nsaa", R.imgCursor);
		c.repaint();
	}
	/**
	 * show the chessboard information
	 * @param mx
	 * @param my
	 */
	private void chessboardInfor(int mx, int my) {
		
	}
	
	/**
	 * check the current operation on chess board 
	 * @param mx
	 * @param my
	 */
	private void chessboardAction(int mx, int my) {
		int x;
		int y;
		x=mx/R.mapInfor.getBlockWidth();
		y=my/R.mapInfor.getBlockHeight();
	
		if(MapResource.imgCursor==MapResource.imgOut){
			R.mapInfor.getBoard()[x][y].setInOut(InOutType.INVALIDOUT);
			System.out.println("You set inOut Type:"+MapResource.inOut);
			cleanCursor();
			c.repaint();	
		}
		else if(R.imgCursor==R.imgSelect){
			System.out.println("Select "+R.mapInfor.getBoard()[x][y].getBlockType());
			if(R.mapInfor.getBoard()[x][y].getBlockType() == MapBlockType.BLOCK_ROLE){					
				R.mapInfor.setPlayerX(x);
				R.mapInfor.setPlayerY(y);
				R.mapInfor.setRoleSelectDone(true);
				cleanCursor();
				c.repaint();
			}
		}
		else{
			System.out.println("type "+m_CurrentBlk);
			switch(this.m_CurrentBlk){
			case ROLE_BULLY:
				R.mapInfor.getBoard()[x][y].setRole(MapResource.mapRoleFactory.create(RoleType.BULLY,R.mapInfor.getBoard()[x][y]));

				break;
			case ROLE_NIMBLE:
				R.mapInfor.getBoard()[x][y].setRole(MapResource.mapRoleFactory.create(RoleType.NIMBLE,R.mapInfor.getBoard()[x][y]));
				break;
			case ROLE_TANK:
				R.mapInfor.getBoard()[x][y].setRole(MapResource.mapRoleFactory.create(RoleType.TANK,R.mapInfor.getBoard()[x][y]));
				break;
				
			case MONSTER_1:				
				R.mapInfor.getBoard()[x][y].setMonster(MapResource.mapMonsterFactory.create(MonsterType.MONSTER_1,R.mapInfor.getBoard()[x][y]));
				break;
			case MONSTER_2:				
				R.mapInfor.getBoard()[x][y].setMonster(MapResource.mapMonsterFactory.create(MonsterType.MONSTER_2,R.mapInfor.getBoard()[x][y]));
				break;
			case MONSTER_3:				
				R.mapInfor.getBoard()[x][y].setMonster(MapResource.mapMonsterFactory.create(MonsterType.MONSTER_3,R.mapInfor.getBoard()[x][y]));
				break;
			case MONSTER_4:				
				R.mapInfor.getBoard()[x][y].setMonster(MapResource.mapMonsterFactory.create(MonsterType.MONSTER_4,R.mapInfor.getBoard()[x][y]));
				break;
			case MONSTER_5:				
				R.mapInfor.getBoard()[x][y].setMonster(MapResource.mapMonsterFactory.create(MonsterType.MONSTER_5,R.mapInfor.getBoard()[x][y]));
				break;
			case MONSTER_6:				
				R.mapInfor.getBoard()[x][y].setMonster(MapResource.mapMonsterFactory.create(MonsterType.MONSTER_6,R.mapInfor.getBoard()[x][y]));
				break;
			case MONSTER_7:				
				R.mapInfor.getBoard()[x][y].setMonster(MapResource.mapMonsterFactory.create(MonsterType.MONSTER_7,R.mapInfor.getBoard()[x][y]));
				break;
			case MONSTER_8:				
				R.mapInfor.getBoard()[x][y].setMonster(MapResource.mapMonsterFactory.create(MonsterType.MONSTER_8,R.mapInfor.getBoard()[x][y]));
				break;
			case MONSTER_9:				
				R.mapInfor.getBoard()[x][y].setMonster(MapResource.mapMonsterFactory.create(MonsterType.MONSTER_9,R.mapInfor.getBoard()[x][y]));
				break;
			case MONSTER_10:				
				R.mapInfor.getBoard()[x][y].setMonster(MapResource.mapMonsterFactory.create(MonsterType.MONSTER_10,R.mapInfor.getBoard()[x][y]));
				break;
				
			case BLK_IN:
				R.mapInfor.getBoard()[x][y].setInOut(InOutType.IN);
				System.out.println("You set inOut Type:"+InOutType.IN);
				break;
			case BLK_OUT:				
				R.mapInfor.getBoard()[x][y].setInOut(InOutType.OUT);
				System.out.println("You set inOut Type:"+InOutType.OUT);
				break;
				
			case BLK_MERCHANT:
				R.mapInfor.getBoard()[x][y].setFixedItem(MapResource.mapFixedItemFactory.create(FixedItemType.MERCHANT,R.mapInfor.getBoard()[x][y]));
				break;
			case BLK_CHEST:
				R.mapInfor.getBoard()[x][y].setFixedItem(MapResource.mapFixedItemFactory.create(FixedItemType.CHEST,R.mapInfor.getBoard()[x][y]));				
				break;			
			case LAND_DOOR_OPEN:
				R.mapInfor.getBoard()[x][y].setLand(LandType.LAND_DOOR_OPENED);;						
				break;	
			case LAND_DOOR_ClOSE:
				R.mapInfor.getBoard()[x][y].setLand(LandType.LAND_DOOR_CLOSED);;						
				break;	
			case LAND_SAND:
				R.mapInfor.getBoard()[x][y].setLand(LandType.LAND_SAND);;				
				break;
			case LAND_GRASS:
				R.mapInfor.getBoard()[x][y].setLand(LandType.LAND_GRASS);				
				break;
			case LAND_STONE:
				R.mapInfor.getBoard()[x][y].setLand(LandType.LAND_STONE);				
				break;
			case LAND_HOR_WALL:
				R.mapInfor.getBoard()[x][y].setLand(LandType.LAND_HOR_WALL);				
				break;
			case LAND_VER_WALL:
				R.mapInfor.getBoard()[x][y].setLand(LandType.LAND_VER_WALL);				
				break;
			}
			
			System.out.println("Set "+R.mapInfor.getBoard()[x][y].getBlockType());
			cleanCursor();
			c.repaint();	
				
			testMap();
		}
		
	}
	
	/**
	 * judge the monster action  
	 * @param mx
	 * @param my
	 */
	private void monsterAction(int mx, int my) {
		if(mx>R.monster1_1X&&mx<R.monster1_2X+R.blockWidth){
			if(my>R.monster1_1Y){
				if(my<R.monster2_1Y){
					if(mx<R.monster1_2X){
						m_CurrentBlk = CMD_TYPE.MONSTER_1;
						setCursor(R.imgMonster1_1);
					}
						
					else{
						m_CurrentBlk = CMD_TYPE.MONSTER_2;
						setCursor(R.imgMonster1_2);						
					}
				}
				else if(my<R.monster3_1Y){
					if(mx<R.monster2_2X){						
						m_CurrentBlk = CMD_TYPE.MONSTER_3;
						setCursor(R.imgMonster2_1);
					}
						
					else{
						m_CurrentBlk = CMD_TYPE.MONSTER_4;						
						setCursor(R.imgMonster2_2);
					}
						
				}
				else if(my<R.monster4_1Y){
					if(mx<R.monster3_2X){
						m_CurrentBlk = CMD_TYPE.MONSTER_5;
						setCursor(R.imgMonster3_1);
					}
						
					else{
						m_CurrentBlk = CMD_TYPE.MONSTER_6;
						setCursor(R.imgMonster3_2);
					}
						
				}
				else if(my<R.monster5_1Y){
					if(mx<R.monster5_2X){
						m_CurrentBlk = CMD_TYPE.MONSTER_7;						
						setCursor(R.imgMonster4_1);
					}
				
					else{
						m_CurrentBlk = CMD_TYPE.MONSTER_8;
						setCursor(R.imgMonster4_2);
					}
				}
				else
				{
					if(mx<R.monster5_2X){
						m_CurrentBlk = CMD_TYPE.MONSTER_9;						
						setCursor(R.imgMonster5_1);
					}
						
					else{
						m_CurrentBlk = CMD_TYPE.MONSTER_10;						
						setCursor(R.imgMonster5_2);
					}
						
				}
			}
		}	
	}
	
	/**
	 * set the image 
	 * @param img
	 */
	private void setCursor(Image img){
		Toolkit tk;
		Cursor cu;
		R.imgCursor =img;
		tk = Toolkit.getDefaultToolkit();
		 cu = tk.createCustomCursor(img, new Point(16, 16), "stick");
			cp.setCursor(cu);
	}
	
	/**
	 * clean cursor 
	 */
	private void cleanCursor(){
		cp.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	
	/**
	 * merchant action
	 * @param mx
	 * @param my
	 */
	private void merchantAction(int mx, int my) {
		if(mx>R.merchant_GX&&mx<R.merchant_GX+R.merchantWidth_G)
		{
			System.out.println("MM");
			if(my>R.merchant_GY&&my<R.merchant_GY+R.merchantHeight_G){
				m_CurrentBlk = CMD_TYPE.BLK_MERCHANT;
				setCursor(R.imgMerchant_G);
			}
		}
		else if(mx>R.treasure_GX&&mx<R.treasure_GX+R.treasureWidth_G)
		{
			if(my>R.treasure_GY&&my<R.treasure_GY+R.treasureHeight_G){
				System.out.println("TT");
				m_CurrentBlk = CMD_TYPE.BLK_CHEST;
				setCursor(R.imgTreasure_G);
			}
		}		
		
		else if(mx>R.door_X&&mx<R.door_X+R.treasureWidth_G)
		{
			if(my>R.door_Y&&my<R.door_Y+R.treasureHeight_G){
				System.out.println("TT");
				m_CurrentBlk = CMD_TYPE.LAND_DOOR_ClOSE;
				setCursor(R.imgDoorClose);
			}
		}	
	}
	
	/**
	 * land action
	 * @param mx
	 * @param my
	 */
	private void landAction(int mx, int my) {
		if(mx>R.sandX&&mx<R.sandX+R.blockWidth)
		{
			if(my>R.sandY&&my<R.sandY+R.blockHeight){				
				System.out.println("SS");
				m_CurrentBlk = CMD_TYPE.LAND_SAND;
				setCursor(R.imgSand);
			}
			else if(my>R.grassY&&my<R.grassY+R.blockHeight){
				System.out.println("GG");
				m_CurrentBlk = CMD_TYPE.LAND_GRASS;
				setCursor(R.imgGrass);
			}
			else if(my>R.stoneY&&my<R.stoneY+R.blockHeight){				
				System.out.println("ST");
				m_CurrentBlk = CMD_TYPE.LAND_STONE;
				setCursor(R.imgStone);
			}
			else if(my>R.wall_1Y&&my<R.wall_1Y+R.blockHeight){
				System.out.println("W1");
				m_CurrentBlk = CMD_TYPE.LAND_HOR_WALL;
				setCursor(R.imgWall_1);
			}
			else if(my>R.wall_2Y&&my<R.wall_2Y+R.blockHeight){
				System.out.println("W2");
				m_CurrentBlk = CMD_TYPE.LAND_VER_WALL;
				setCursor(R.imgWall_2);
			}				
		}
	}
	
	/**
	 * role action
	 * @param mx
	 * @param my
	 */
	private void roleAction(int mx, int my) {
		if(my>R.role_1_GY&&my<R.role_1_GY+R.roleHeight_G)
		{
			//
			if(mx>R.role_1_GX&&mx<R.role_1_GX+R.roleWidth_G){
				m_CurrentBlk = CMD_TYPE.ROLE_BULLY;
				setCursor(R.imgRole_1_G);
			}
			else if(mx>R.role_2_GX&&mx<R.role_2_GX+R.roleWidth_G){
				m_CurrentBlk = CMD_TYPE.ROLE_NIMBLE;
				setCursor(R.imgRole_2_G);
			}
			else if(mx>R.role_3_GX&&mx<R.role_3_GX+R.roleWidth_G){
				m_CurrentBlk = CMD_TYPE.ROLE_TANK;
				setCursor(R.imgRole_3_G);
			}
			else if(mx>R.role_4_GX&&mx<R.role_4_GX+R.roleWidth_G){
//				R.roleType=4;
//				setCursor(R.imgRole_4_G);
			}
			else if(mx>R.rowArrowUpX&&mx<R.rowArrowUpX+R.arrowWidth){
				
				 if(my>R.rowArrowUpY&&my<R.rowArrowUpY+R.arrowHeight){
					 R.mapInfor.rowNumberIncrease();
					 System.out.println("Row Up");
					 md.clearBoard();
					 c.repaint();
				 }
				else  if(my>R.columArrowUpY&&my<R.columArrowUpY+R.arrowHeight){
					 R.mapInfor.columNumberIncrease();
					 System.out.println("column Up");
					 md.clearBoard();
					 c.repaint();
				}
			}
			else if(mx>R.rowArrowDownX&&mx<R.rowArrowDownX+R.arrowWidth){
				
				 if(my>R.rowArrowDownY&&my<R.rowArrowDownY+R.arrowHeight){
					 R.mapInfor.rowNumberDecrease();
					 System.out.println("Row down");
					 md.clearBoard();
					 c.repaint();
				 }
				else  if(my>R.columArrowDownY&&my<R.columArrowDownY+R.arrowHeight){
					 R.mapInfor.columNumberDecrease();
					 System.out.println("column down");
					 md.clearBoard();
					 c.repaint();
					
				}
			}
			else if(mx>R.inX&&mx<R.inX+R.inWidth){
				if(my>R.inY&&my<R.inY+R.inHeight){
					m_CurrentBlk = CMD_TYPE.BLK_IN;
					setCursor(R.imgIn);
					System.out.println("You click IN");
				}
				else if(my>R.outY&&my<R.outY+R.inHeight){
					m_CurrentBlk = CMD_TYPE.BLK_OUT;
					setCursor(R.imgOut);
					System.out.println("You click OUT");
				}
			}
			else if(mx>R.selectX&&mx<R.selectX+R.inWidth){
				setCursor(R.imgSelect);
			}
		}
		
	}
	
	/**
	 * test map
	 */
	private void testMap() {
		for (int i = 0; i < this.R.mapInfor.getRowCount(); i++) {
			for (int j = 0; j <  this.R.mapInfor.getColumnCount(); j++) {
				MapBlock blk = R.mapInfor.getBlock(j,i);
				System.out.printf(" %s(%s)", blk.getLand(), blk.getInOutType());
			}
			System.out.println();
		}
	}
	
	/**
	 * the command action
	 * @param x
	 * @param y
	 */
	private void commandAction(int x, int y) {
		//Exit
		if(y>600&&y<640) {
			System.out.println("Save Map");
			try {
				MapSave ms = new MapSave();
				ms.save(this.R.mapInfor);
			} catch (Exception e) {
				return;
			}			
		} else if(y>640&&y<680) {
			System.out.println("Load Map");
			try {
					MapLoad ml = new MapLoad();
					
					ml.load(this.R.mapInfor);
			} catch (Exception e) {
				return;
			}
		} else if(y>680&&y<720)
		{
			System.out.println("Random Map");
			randomMap= new MapRandom(R,R.mapInfor);
			MapBlock[][] temp=randomMap.create();
			c.repaint();
			
			m_CurrentBlk = CMD_TYPE.UNDEFINED;
			for (int i = 0; i < R.mapInfor.getColumnCount(); i++){
				for (int j = 0; j <  R.mapInfor.getRowCount(); j++) {
					R.mapInfor.getBoard()[i][j]=temp[i][j];
				}
			}
		} else {
			System.out.println("Exit");			
			c.switchTo(UserInterfaceType.MAIN_UI,md);
		}
		
		c.repaint();
		
	}
	
	/**
	 * check action area
	 * @param mx
	 * @param my
	 * @return
	 */
	private int checkActionArea(int mx, int my) {
	if(mx<800)
	{
		if(my<600)
			return 0;
		else 
			return 2;
	}
	else{
		if(my<240)
			return 1;
			
		else if(my<R.merchant_GY)
			return 5;
		else if(my<600)
			return 4;
		else return 3;
	}
		
	}
	
}
