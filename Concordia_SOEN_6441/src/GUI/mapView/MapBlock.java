package GUI.mapView;
/**
 * @author DanZhang
 * @author XinShao
 * SOEN 6441 Team Project 
 * Winter 2014
 */

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

import utility.FixedItemType;
import utility.IMovable;
import utility.InOutType;
import utility.LandType;
import utility.MapBlockType;
import utility.MonsterType;
import utility.RoleType;
import Data.Character.GameCharacter;
import Data.Character.GameFixedItem;
import GUI.mapView.MapFixedItemFactory.MapFixedItem;
import GUI.mapView.MapRoleFactory.MapRole;

/**
 * this is the class to define the block
 *
 */
public class MapBlock {
	//use this Integer to act as a shared lock
	//between each movement
	static Integer m_MoveLock = 0;
	
	private LandType m_LandType;	
	private InOutType m_InOutType;	

	public boolean found;
	public int foundValue;
	
	//the object that contained inside the MapBlock.
	private ArrayList < IMapItem > Items;
	private Point m_Position;

	/**
	 * constructor of MapBlock
	 */
	public MapBlock(int x, int y){
		found=false;
		
		m_LandType=LandType.LAND_UNDEFINED;
		m_InOutType=InOutType.NORMAL;
		
		Items = new ArrayList <IMapItem> ();
		m_Position = new Point(x,y);
	}
	
	/**
	 * show the block information
	 */
	public String toString(){
		String result = String.format("Block: (%d,%d)\nType = %s", m_Position.x,m_Position.y, getItemInfo());
		return result;		
	}
	
	/**
	 * show the item information
	 * @return
	 */
	private String getItemInfo() {		
		if (Items.size() < 1)
		return "";
		
		String result = "";
		for (IMapItem item : Items) {
			result += item.toString();
		}
		
		return result;
	}

	/**
	 * return chest or merchant 
	 * @return
	 */
	public IMapItem getFixedItem() {
		if (Items.isEmpty())
			return null;
		
		for (IMapItem fixed : Items) {
			if (fixed instanceof IMapFixedItem) {
				return (IMapFixedItem) fixed;
			}
		}		

		return null; 			
	}
	
	/**
	 * return map item 
	 * @return
	 */
	public IMapItem getMapItem(){
		if (Items.isEmpty())
			return null;
		
		IMapItem item = null;
		if (this.getRole() == null) {
			item = Items.get(0);
		}				
		else {
			item = (IMapItem) this.getRole();
		}		
		
		return item; 		
	}
	
	/**
	 * judge if the block is blank
	 * @return
	 */
	public boolean blankBlock(){
		return this.getLand() == LandType.LAND_UNDEFINED;
	}

	/**
	 * judge if the map is occupied
	 * @return
	 */
	public boolean isOccupied(){
		if(this.Items.isEmpty()){
			return false;
		}else if(getMapItem() instanceof IMovable){
			return true;
		}else{
			return false;
		}
		//return this.Items.isEmpty() == false;
	}
	
	/**
	 * judge the fixed item type is merchant or chest
	 * @param i
	 * @return
	 */
	public FixedItemType intToFixedItemType(int i) {
		switch(i) {
		case 1:
			return FixedItemType.MERCHANT;
		case 2:
			return FixedItemType.CHEST;
		default:
			return FixedItemType.CHEST;
		}
	}
	
	/**
	 * set the fixed item type 
	 * @param merchant
	 */
	public void setFixedItem(FixedItemType merchant){
		this.Items.clear();
		this.Items.add(MapResource.mapFixedItemFactory.create(merchant,this));
	}
	
	/**
	 * set the fixed item 
	 * @param item
	 */
	public void setFixedItem(GameFixedItem item) {
		this.Items.clear();
		this.Items.add(MapResource.mapFixedItemFactory.create(item, this));
	}
	
	/**
	 * get the land type
	 * @return
	 */
	public LandType getLand(){
		return m_LandType;
	}
	
	/**
	 * judge land type
	 * @param v
	 * @return
	 */
	public LandType intToLandType (int v) {
		switch (v) {
		case 1:
			return LandType.LAND_SAND;
		case 2:
			return LandType.LAND_GRASS;
		case 3:
			return LandType.LAND_STONE;
		case 4:
			return LandType.LAND_HOR_WALL;
		case 5:
			return LandType.LAND_VER_WALL;		
		case 6:
			return LandType.LAND_DOOR_OPENED;	
		case 7:
			return LandType.LAND_DOOR_CLOSED;	
		default:
			return LandType.LAND_GRASS;
		}
	}
	
	/**
	 * set land type
	 * @param land
	 */
	public void setLand(LandType land){
		this.m_LandType = land;
	}
	
	/**
	 * get block image
	 * @return
	 */
	public Image getImage(){
		if (this.blankBlock())
			return null;
		else {
			if (this.Items.isEmpty()) {
				if (this.m_InOutType == InOutType.NORMAL||
						this.m_InOutType == InOutType.INVALIDOUT	)
					return MapResource.getImageByLandType(this.getLand());
				else 
					return MapResource.getImageByInOutType(this.getInOutType());
			} else {
				IMapItem item = (IMapItem)this.getRole();				
				if (item == null)
					return this.Items.get(0).getImage();
				else 
					return item.getImage();
			}
		}		
	}

	/**
	 * judge if the block is a wall
	 * @return
	 */
	public boolean isWall() {
		LandType lType = this.getLand();
		return  lType == LandType.LAND_HOR_WALL || lType == LandType.LAND_VER_WALL ; 
	}
	
	/**
	 * transfer int type of InOutType to enum
	 * @param v
	 * @return
	 */
	public int InOutToInt(InOutType v) {
		switch(v) {
		case IN:
			return 1;
		case OUT:
			return 2;
		default:
			return -1;
		}
	}
	
	public InOutType getInOutType() {
		return this.m_InOutType;
	}
	
	int monsterTypeToInt(MonsterType m) {
		switch(m) {
		case MONSTER_1:
			return 1;
		case MONSTER_2:
			return 2;
		case MONSTER_3:
			return 3;
		case MONSTER_4:
			return 4;
		case MONSTER_5:
			return 5;
		case MONSTER_6:
			return 6;
		case MONSTER_7:
			return 7;
		case MONSTER_8:
			return 8;
		case MONSTER_9:
			return 9;
		case MONSTER_10:
			return 10;
		default:
			return 1;
		}		
	}
	
	/**
	 * transfer Monster from int type to monsterType
	 * @param i
	 * @return
	 */
	MonsterType intToMonsterType(int i) {
		switch(i) {
		case 1:
			return MonsterType.MONSTER_1;
		case 2:
			return MonsterType.MONSTER_2;
		case 3:
			return MonsterType.MONSTER_3;
		case 4:
			return MonsterType.MONSTER_4;
		case 5:
			return MonsterType.MONSTER_5;
		case 6:
			return MonsterType.MONSTER_6;
		case 7:
			return MonsterType.MONSTER_7;
		case 8:
			return MonsterType.MONSTER_8;
		case 9:
			return MonsterType.MONSTER_9;
		case 10:
			return MonsterType.MONSTER_10;
		default:		
			return MonsterType.MONSTER_1;
		}
	}
	
	/**
	 * set monster 
	 * @param type
	 */
	public void setMonster(int type) {
		this.Items.clear();
		this.Items.add(MapResource.mapMonsterFactory.create(intToMonsterType(type),this));
	}
	
	/**
	 * set In out type
	 * @param type
	 */
	public void setInOut(InOutType type) {
		this.m_InOutType = type;
	}
	
	/**
	 * transfer role type to int
	 * @param r
	 * @return
	 */
	int roleTypeToInt(RoleType r) {
		switch (r) {
		case BULLY:
			return 1;
		case NIMBLE:
			return 2;
		case TANK:
			return 3;
		default:
			return 1;
		}		
	}
	
	/**
	 * transfer role type from int to RoleType
	 * @param i
	 * @return
	 */
	RoleType intToRoleType(int i) {
		switch (i) {
		case 1:
			return RoleType.BULLY;
		case 2:
			return RoleType.NIMBLE;
		case 3:
			return RoleType.TANK;
		default:
			return RoleType.BULLY;		
		}
	}
	
	/**
	 * set role 
	 * @param type
	 */
	public void setRole(int type) {
		this.Items.clear();		
		this.Items.add(MapResource.mapRoleFactory.create(intToRoleType(type),this));
	}
	
	/**
	 * set role 
	 * @param type
	 * @param role
	 */
	public void setRole(int type,GameCharacter role) {
		this.Items.clear();	
		MapRole mr =(MapRole) MapResource.mapRoleFactory.create(intToRoleType(type),this);
		mr.setGameCharacter(role);
		this.Items.add(mr);
	}
	
	/**
	 * set role
	 * @param role
	 * @return
	 */
	public boolean setRole(IMovable role) {
		if (this.Items == null)
			return false;
		
		if (this.Items.isEmpty()) {
			this.Items.add((IMapItem)role);
			return true;
		} else {
			this.Items.add((IMapItem)role);
			return true;
		}
	}
	
	/**
	 * get the role 
	 * @return
	 */
	public IMovable getRole() {
		for (IMapItem movable : Items) {
			if (movable instanceof IMovable) {
				return (IMovable) movable;
			}
		}
		return null;
	}
	
	/**
	 * move the movable object from one block to another.
	 *  
	 * @param target
	 * @param map (if not null, means the role in the block is the player)
	 * we need update the location of the role in the map.
	 * @return
	 */
	public boolean moveRoleTo(MapBlock target, MapInfor map) {
		synchronized (m_MoveLock) {
			if (target == null)
				return false;
			
			if (this.Items.isEmpty())
				return false;
			
			IMovable movable = this.getRole();
			if (movable != null) {
				if (target.setRole(movable)) {
					this.Items.remove(movable);		
					
					MapItem mItem = (MapItem)movable;
					mItem.setOwnerBlock(target);
					if (map != null) {
						map.setPlayerX(target.getLocationX());
						map.setPlayerY(target.getLocationY());
					}
					return true;
				} else {
					return false;
				}
			}		
			return false;
		}
	}
	
	/**
	 * get the item location of y
	 * @return
	 */
	public int getLocationY() {
		return m_Position.y;
	}

	/**
	 * get the item location of x
	 * @return
	 */
	public int getLocationX() {
		return m_Position.x;
	}
	
	/**
	 * set location of y
	 * @param y
	 */
	public void setLocationY(int y) {
		 m_Position.y = y;
	}

	/**
	 * set location of x
	 * @param x
	 */
	public void setLocationX(int x) {
		m_Position.x=x;
	}

	/**
	 * transfer block type into int
	 * @param t
	 * @return
	 */
	//1 for role,2 for land, 3 for merchant,4 for monster; 5 for in/out 0 for null;
	static public int mapBlockTypeToInt(MapBlockType t) {
		switch (t) {
		case BLOCK_ROLE:
			return 1;
		case BLOCK_CHEST:
			return 2;//TODO: differentiate the chest from merchant
		case BLOCK_MERCHANT:
			return 3;
		case BLOCK_MONSTER:
			return 4;
		case BLOCK_EMPTY:
			return 0;
		default:
			return 0;
		}
	}
	
	/**
	 * transfer land type to  int
	 * @param v
	 * @return
	 */
	public int LandTypeToInt(LandType v) {
		switch (v) {
		case LAND_SAND:
			return 1;
		case LAND_GRASS:
			return 2;
		case LAND_STONE:
			return 3;
		case LAND_HOR_WALL:
			return 4;
		case LAND_VER_WALL:
			return 5;
		case LAND_DOOR_OPENED:
			return 6;
		case LAND_DOOR_CLOSED:
			return 7;
		default:
			return 2;
		}
	}
	
	/**
	 * get block type 
	 * @return
	 */
	public MapBlockType getBlockType() {
		if (this.Items.isEmpty()) {
			return MapBlockType.BLOCK_EMPTY;
		} else {
			IMapItem item = null;
			if (this.getRole() == null) {
				item = Items.get(0);
			}				
			else {
				item = (IMapItem) this.getRole();
			}
			return item.getBlockType();
		}
	}
	
	/**
	 * set the monster
	 * @param monster
	 * @return
	 */
	public boolean setMonster(IMapMonster monster) {
		if (this.Items == null)
			return false;
		
		if (this.Items.isEmpty()) {
			this.Items.add(monster);
			monster.setOwnerBlock(this);
			return true;
		} else {
			return false;
		}
	}	

	/**
	 * set fixed item 
	 * @param item
	 * @return
	 */
	public boolean setFixedItem(IMapFixedItem item) {
		if (this.Items == null)
			return false;
		
		if (this.Items.isEmpty()) {
			this.Items.add(item);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * clear the item
	 */
	public void clearItem() {
		this.Items.clear();
		
	}		
}
