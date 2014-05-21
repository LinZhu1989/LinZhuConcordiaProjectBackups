package GUI.mapView;
/**
 * @author DanZhang
 * @author LinZhu
 * SOEN 6441 Team Project 
 * Winter 2014
 */
import java.awt.Image;

import javax.swing.ImageIcon;

import utility.FixedItemType;
import utility.InOutType;
import utility.LandType;
import utility.MonsterType;
import utility.RoleType;
/**
 * this is the class to define the map resource
 *
 */
public class MapResource {
	static public Image imgCursor;
	static public Image imgMapView;
	static public ImageIcon imgicon;
	static public Image imgSand;
	static public Image imgGrass;
	static public Image imgStone;
	static public Image imgWall_1;
	static public Image imgWall_2;
	static public Image imgMonster1_1;
	static public Image imgMonster1_2;
	static public Image imgMonster2_1;
	static public Image imgMonster2_2;
	static public Image imgMonster3_1;
	static public Image imgMonster3_2;
	static public Image imgMonster4_1;
	static public Image imgMonster4_2;
	static public Image imgMonster5_1;
	static public Image imgMonster5_2;
	static public Image imgRole_1_G;
	static public Image imgRole_2_G;
	static public Image imgRole_3_G;
	static public Image imgRole_4_G;
	static public Image imgTreasure_G;
	static public Image imgMerchant_G;
	static public Image imgRole_1_B;
	static public Image imgRole_2_B;
	static public Image imgRole_3_B;
	static public Image imgTreasure_B;
	static public Image imgMerchant_B;
	static public Image imgArrowDown;
	static public Image imgArrowUp;
	static public Image imgIn;
	static public Image imgOut;
	static public Image imgSelect;
	static public Image imgDoorOpen;
	static public Image imgDoorClose;
	static public int roleWidth_G;
	static public int roleHeight_G;
	static public int blockWidth;
	static public int blockHeight;
	static public int commandWidth;
	static public int commandHeight;
	static public int merchantWidth_G;
	static public int merchantHeight_G;
	static public int treasureWidth_G;
	static public int treasureHeight_G;

	static public int monster1_1X;
	static public int monster1_2X;
	static public int monster2_1X;
	static public int monster2_2X;
	static public int monster3_1X;
	static public int monster3_2X;
	static public int monster4_1X;
	static public int monster4_2X;
	static public int monster5_1X;
	static public int monster5_2X;
	static public int monster1_1Y;
	static public int monster1_2Y;
	static public int monster2_1Y;
	static public int monster2_2Y;
	static public int monster3_1Y;
	static public int monster3_2Y;
	static public int monster4_1Y;
	static public int monster4_2Y;
	static public int monster5_1Y;
	static public int monster5_2Y;
	static public int role_1_GX;
	static public int role_2_GX;
	static public int role_3_GX;
	static public int role_4_GX;
	static public int merchant_GX;
	static public int door_X;
	static public int door_Y;
	static public int treasure_GX;
	static public int merchant_GY;
	static public int treasure_GY;
	static public int sandX;
	static public int grassX;
	static public int stoneX;
	static public int wall_1X;
	static public int wall_2X;
	static public int sandY;
	static public int grassY;
	static public int stoneY;
	static public int wall_1Y;
	static public int wall_2Y;
	static public int role_1_GY;
	static public int role_2_GY;
	static public int role_3_GY;
	static public int role_4_GY;
	static public int firstX;
	
	static public int firstY;
	static public int rowStringX;
	static public int rowStringY;
	static public int columStringX;
	static public int columStringY;
	static public int rowArrowUpX;
	static public int rowArrowUpY;
	static public int columArrowUpX;
	static public int columArrowUpY;
	static public int arrowWidth;
	static public int arrowHeight;
	static public int rowArrowDownX;
	static public int rowArrowDownY;
	static public int columArrowDownX;
	static public int columArrowDownY;
	
	public MapInfor mapInfor;
	static public int inWidth;
	static public int inHeight;
	static public int inX;
	static public int inY;
	static public int outY;
	static public int outX;
	static public int selectX;
	static public int selectY;
	
	public boolean roleSelectDone;
	static public int rowNumberX;
	static public int rowNumberY;
	static public int columNumberX;
	static public int columNumberY;
	static public int inOut;
	
	public static MapRoleFactory mapRoleFactory = new MapRoleFactory();
	public static MapMonsterFactory mapMonsterFactory = new MapMonsterFactory();
	public static MapFixedItemFactory mapFixedItemFactory = new MapFixedItemFactory();
	
	/**
	 * constructor of Map Resource
	 */
	public MapResource() {
		initImg();
		initWidthHeight();
		mapInfor = new MapInfor();
		roleSelectDone = false;
	}
	
	/**
	 * initial width and height
	 */
	private void initWidthHeight() {
		roleWidth_G = 150;
		roleHeight_G = 150;
		blockWidth = 40;
		blockHeight = 40;
		commandWidth = 190;
		commandHeight = 40;
		merchantWidth_G = 70;
		merchantHeight_G = 70;
		treasureWidth_G = 70;
		treasureHeight_G = 70;
		

		sandX = 950;
		sandY = 50;
		grassX = 950;
		grassY = 90;
		stoneX = 950;
		stoneY = 130;
		wall_1X = 950;
		wall_1Y = 170;
		wall_2X = 950;
		wall_2Y = 210;

		monster1_1X = 920;
		monster1_1Y = 330;
		monster1_2X = 960;
		monster1_2Y = 330;

		monster2_1X = 920;
		monster2_1Y = 370;
		monster2_2X = 960;
		monster2_2Y = 370;

		monster3_1X = 920;
		monster3_1Y = 410;
		monster3_2X = 960;
		monster3_2Y = 410;

		monster4_1X = 920;
		monster4_1Y = 450;
		monster4_2X = 960;
		monster4_2Y = 450;

		monster5_1X = 920;
		monster5_1Y = 490;
		monster5_2X = 960;
		monster5_2Y = 490;

		role_1_GX = 50;
		role_2_GX = 200;
		role_3_GX = 350;
		role_4_GX = 500;
		role_1_GY = 610;
		role_2_GY = 610;
		role_3_GY = 610;
		role_4_GY = 610;

		rowStringX = role_4_GX + 150;
		rowStringY = role_1_GY + 20;
		columStringX = rowStringX;
		columStringY = role_1_GY + 50;
		rowArrowUpX = rowStringX + 100;
		rowArrowUpY = rowStringY - 25;
		columArrowUpX = columStringX + 100;
		columArrowUpY = columStringY - 25;

		rowArrowDownX = rowArrowUpX + 25;
		rowArrowDownY = rowArrowUpY;
		columArrowDownX = columArrowUpX + 25;
		columArrowDownY = columArrowUpY;
		arrowWidth = 25;
		arrowHeight = 25;

		inWidth = 40;
		inHeight = 40;
		inX = rowStringX;
		inY = columArrowDownY + 25;
		rowNumberX=inX+40;
		rowNumberY=inY+inHeight+30;
		
		columNumberX=inX+40;
		columNumberY=rowNumberY+30;
		outX = inX;
		outY = inY + inHeight;
		selectX = inX + inWidth;
		selectY = inY;
		
		merchant_GX = 800;
		door_X=875;
		treasure_GX = 950;
		merchant_GY = 530;
		door_Y=530;
		treasure_GY = 530;
	}
	
	/**
	 * initial image
	 */
	private void initImg() {
		imgicon = new ImageIcon("./res/GUI/sand.png"); 
		imgSand = imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/grass.png");
		imgGrass = imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/stone.png");
		imgStone = imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/wall_1.png");
		imgWall_1 = imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/wall_2.png");
		imgWall_2 = imgicon.getImage();
		
		imgicon = new ImageIcon("./res/GUI/doorOpen.png");
		imgDoorOpen = imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/doorClose.png");
		imgDoorClose = imgicon.getImage();
		
		imgicon = new ImageIcon("./res/GUI/MonsterLv1_1.png"); 
		imgMonster1_1 = imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/MonsterLv1_2.png"); 
		imgMonster1_2 = imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/MonsterLv2_1.png"); 
		imgMonster2_1 = imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/MonsterLv2_2.png"); 
		imgMonster2_2 = imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/MonsterLv3_1.png"); 
		imgMonster3_1 = imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/MonsterLv3_2.png"); 
		imgMonster3_2 = imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/MonsterLv4_1.png"); 
		imgMonster4_1 = imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/MonsterLv4_2.png"); 
		imgMonster4_2 = imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/MonsterLv5_1.png"); 
		imgMonster5_1 = imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/MonsterLv5_2.png"); 
		imgMonster5_2 = imgicon.getImage();

		imgicon = new ImageIcon("./res/GUI/dwarf_1.png");
		imgRole_1_G = imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/Elf_1.png");
		imgRole_2_G = imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/Witch_1.png");
		imgRole_3_G = imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/Role_0.png");
		imgRole_4_G = imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/Dwarf_B.png");
		imgRole_1_B = imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/Elf_B.png");
		imgRole_2_B = imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/Witch_B.png");
		imgRole_3_B = imgicon.getImage();

		imgicon = new ImageIcon("./res/GUI/treasure_G.png");
		imgTreasure_G = imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/merchant_G.png");
		imgMerchant_G = imgicon.getImage();

		imgicon = new ImageIcon("./res/GUI/treasure_B.png");
		imgTreasure_B = imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/merchant_B.png");
		imgMerchant_B = imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/mapView.png");
		imgMapView = imgicon.getImage();

		imgicon = new ImageIcon("./res/GUI/imgArrowUp.png"); 
		imgArrowUp = imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/imgArrowDown.png"); 
		imgArrowDown = imgicon.getImage();

		imgicon = new ImageIcon("./res/GUI/IN.png"); 
		imgIn = imgicon.getImage();
		imgicon = new ImageIcon("./res/GUI/out.png"); 
		imgOut = imgicon.getImage();

		imgicon = new ImageIcon("./res/GUI/imgSelect.png"); 
		imgSelect = imgicon.getImage();

	}
	/**
	 * get image by role type
	 * @param role
	 * @return
	 */
	public static Image getImageByRoleType(RoleType role) {
		switch (role) {
		case BULLY:
			return imgRole_1_B;
		case NIMBLE:
			return imgRole_2_B;
		case TANK:
			return imgRole_3_B;
		default:
			return imgRole_1_B;
		}
	}

	/**
	 * get the image by land type
	 * @param land
	 * @return
	 */
	public static Image getImageByLandType(LandType land) {
		switch (land) {
		case LAND_SAND:
			return imgSand;
		case LAND_GRASS:
			return imgGrass;
		case LAND_STONE:
			return imgStone;
		case LAND_HOR_WALL:
			return imgWall_1;
		case LAND_DOOR_OPENED:
			return imgDoorOpen;
		case LAND_DOOR_CLOSED:
			return imgDoorClose;
		default: //LAND_VER_WALL:
			return imgWall_2;
		//TODO: taking care of the IN/OUT
//		case 6:
//			return imgIn;
//		default:
//			return imgOut;
		}
	}

	/**
	 * get the image by monster type
	 * @param monster
	 * @return
	 */
	public static Image getImageByMonsterType(MonsterType monster) {
		switch (monster) {
		case MONSTER_1:
			return imgMonster1_1;
		case MONSTER_2:
			return imgMonster1_2;
		case MONSTER_3:
			return imgMonster2_1;
		case MONSTER_4:
			return imgMonster2_2;
		case MONSTER_5:
			return imgMonster3_1;
		case MONSTER_6:
			return imgMonster3_2;
		case MONSTER_7:
			return imgMonster4_1;
		case MONSTER_8:
			return imgMonster4_2;
		case MONSTER_9:
			return imgMonster5_1;
		default:
			return imgMonster5_2;
		}
	}
	
	/**
	 * get the image by in out type
	 * @param inOutType
	 * @return
	 */
	public static Image getImageByInOutType(InOutType inOutType) {
		switch (inOutType) {
		case IN:
			return imgIn;
		case OUT:
			return imgOut;
		default:
			return imgIn;
		}
	}	
	
	/**
	 * get the image by merchant type
	 * @param fixedType
	 * @return
	 */
	public static Image getImageByMerchantType(FixedItemType fixedType) {
		switch (fixedType) {
		case MERCHANT:
			return imgMerchant_B;
		default:
			return imgTreasure_B;
		}
	}
	
	/**
	 * this is the method help XML file to save the map information
	 * @param mapBlk
	 * @return
	 */
	public MapBlock updateImage(MapBlock mapBlk){
//		if (mapBlk.getBlockType() == 5) {
//			mapBlk.setImage(this.getImageByInOutType(mapBlk.getInOutType()));
//		} else if (mapBlk.getBlockType() == 4) {
//			mapBlk.setImage(this.getImageByMonsterType(mapBlk.getMonster()));
//		} else if (mapBlk.getBlockType() == 3) {
//			mapBlk.setImage(this.getImageByMerchantType(mapBlk.getmerchant()));
//		}else if (mapBlk.getBlockType() == 2) {
//			mapBlk.setImage(this.getImageByLandType(mapBlk.getLand()));
//		}else {
//			mapBlk.setImage(this.getImageByRoleType(mapBlk.getRoleType()));
//		}
		return mapBlk;
	}



}
