package GUI.mapView;

/**
 * @author DanZhang
 * SOEN 6441 Team Project 
 * Winter 2014
 */
import java.util.List;
import java.awt.Point;
import java.util.ArrayList;

import Data.Character.GameFixedItem;
import GUI.mapView.MapMonsterFactory.MapMonster;
import GUI.mapView.MapRoleFactory.MapRole;
import utility.FixedItemType;
import utility.LandType;
import utility.MapBlockType;

/**
 * this is the class to definate the map information
 * 
 */
public class MapInfor {
	private int rowCount;
	private int columnCount;
	private int blockHeight;
	private int blockWidth;
	private int playerX;
	private int playerY;
	private boolean isRoleSelectDone;
	private MapBlock[][] block;
	private MapRole role;
	public ArrayList<MapItem> m_monsters;
	public ArrayList<MapItem> fixedItems;

	/**
	 * constructor of Map Infor
	 */
	public MapInfor() {
		rowCount = 15;
		columnCount = 20;
		setRoleSelectDone(false);
		calculateHW();
		m_monsters = new ArrayList<MapItem>();
		fixedItems = new ArrayList<MapItem>();
	}

	/**
	 * xml file will use this method
	 * 
	 * @return
	 */
	public MapBlock[][] getBoard() {
		return block;
	}

	public void analysisMap() {
		int i = 0;
		int j = 0;
		m_monsters.clear();
		fixedItems.clear();
		try {
			for (i = 0; i < columnCount; i++) {
				for (j = 0; j < rowCount; j++) {
					if (block[i][j].isOccupied()) {
						IMapItem item=block[i][j].getMapItem();
						if (item.getBlockType() == MapBlockType.BLOCK_ROLE) {
							if (i == playerX && j == playerY)
								role = (MapRole) block[i][j].getMapItem();
							else
								m_monsters.add((MapItem) block[i][j].getMapItem());
						} else if (item.getBlockType() ==MapBlockType.BLOCK_MONSTER) {
							m_monsters.add((MapMonster) block[i][j].getMapItem());
						}
					} else{
						IMapItem item=block[i][j].getMapItem();
						if(item!=null){
							if(item.getBlockType()==MapBlockType.BLOCK_MERCHANT||item.getBlockType()==MapBlockType.BLOCK_CHEST){
								this.fixedItems.add((MapItem)block[i][j].getMapItem());
							}
						}
					}
				}
			}

		} catch (Exception e) {
			System.out.println(e.toString() + i + "  " + j + " " + columnCount
					+ " " + rowCount + " " + block.length + " "
					+ block[0].length);
		}

		List<MapItem> mlist = m_monsters;
		System.out.println("ROLE  " + role.getOwnerBlock().toString());
		for (MapItem monster : mlist) {
			System.out
					.println("Monster  " + monster.getOwnerBlock().toString());
		}
	}

	/**
	 * clear the map block
	 */
	public void clearBoard() {
		block = new MapBlock[rowCount][columnCount];
		initBlocks(columnCount, rowCount);
		calculateHW();
	}

	/**
	 * initiate block
	 * 
	 * @param col
	 * @param row
	 */
	public void initBlocks(int col, int row) {
		block = new MapBlock[col][row];
		this.columnCount = col;
		this.rowCount = row;
		setBlocks(new MapBlock[col][row]);
		for (int i = 0; i < columnCount; i++) {
			for (int j = 0; j < rowCount; j++) {
				block[i][j] = new MapBlock(i, j);
			}
		}
	}

	/**
	 * set the block
	 * 
	 * @param blks
	 */
	public void setBlocks(MapBlock[][] blks) {
		block = blks;
	}

	/**
	 * caculate block height and width
	 */
	public void calculateHW() {
		setBlockHeight(600 / rowCount);
		try {
			setBlockWidth(800 / columnCount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * decrease the column number
	 */
	public void columNumberDecrease() {
		if (columnCount > 10)
			columnCount--;
		calculateHW();
	}

	/**
	 * decrease the row number
	 */
	public void rowNumberDecrease() {
		if (rowCount > 10)
			rowCount--;
		calculateHW();

	}

	/**
	 * increase the column number
	 */
	public void columNumberIncrease() {
		if (columnCount < 40)
			columnCount++;
		calculateHW();
	}

	/**
	 * increase the row number
	 */
	public void rowNumberIncrease() {
		if (rowCount < 40)
			rowCount++;
		calculateHW();
	}

	/**
	 * get player location on the map
	 * 
	 * @return
	 */
	public Point getPlayerLocation() {
		return new Point(getPlayerX(), getPlayerY());
	}

	/**
	 * get the player location
	 * 
	 * @return
	 */
	public int getColumnCount() {
		return columnCount;
	}

	/**
	 * get the row count
	 * 
	 * @return
	 */
	public int getRowCount() {
		return rowCount;
	}

	/**
	 * set column count
	 * 
	 * @param c
	 */
	public void setColumnCount(int c) {
		columnCount = c;
	}

	/**
	 * set row count
	 * 
	 * @param r
	 */
	public void setRowCount(int r) {
		rowCount = r;
	}

	/**
	 * set block
	 * 
	 * @param col
	 * @param row
	 * @param blk
	 */
	public void setBlock(int col, int row, MapBlock blk) {
		this.block[col][row] = blk;
	}

	/**
	 * get the block
	 * 
	 * @param col
	 * @param row
	 * @return
	 */
	public MapBlock getBlock(int col, int row) {
		return getBlock(new Point(col, row));
	}

	/**
	 * @param nextBlock
	 *            .x = col, nextBlock1.y = row
	 * @return
	 */
	public MapBlock getBlock(Point nextBlock1) {
		MapBlock blk = block[nextBlock1.x][nextBlock1.y];
		return blk;
	}

	/**
	 * set the block
	 */
	public void setAllunFound() {
		for (int i = 0; i < columnCount; i++)
			for (int j = 0; j < rowCount; j++) {
				if (block[i][j] == null) {
					int a = 0;
					a = a + 1;
				} else {
					block[i][j].found = false;
					block[i][j].foundValue = 500;
				}
			}
	}

	/**
	 * get the block width
	 * 
	 * @return
	 */
	public int getBlockWidth() {
		return blockWidth;
	}

	/**
	 * set the block width
	 * 
	 * @param blockWidth
	 */
	public void setBlockWidth(int blockWidth) throws Exception {
		if (blockWidth <= 0) {
			throw new RuntimeException("blockWidth should larger than 0");
		}
		this.blockWidth = blockWidth;
	}

	/**
	 * get the block height
	 * 
	 * @return
	 */
	public int getBlockHeight() {
		return blockHeight;
	}

	/**
	 * set the block height
	 * 
	 * @param blockHeight
	 */
	public void setBlockHeight(int blockHeight) {
		this.blockHeight = blockHeight;
	}

	/**
	 * judge if the block is a role
	 * 
	 * @return
	 */
	public boolean isRoleSelectDone() {
		return isRoleSelectDone;
	}

	/**
	 * @param isRoleSelectDone
	 */
	public void setRoleSelectDone(boolean isRoleSelectDone) {
		this.isRoleSelectDone = isRoleSelectDone;
	}

	/**
	 * get the player x location
	 * 
	 * @return
	 */
	public int getPlayerX() {
		return playerX;
	}

	/**
	 * set the player y location
	 * 
	 * @param playerX
	 */
	public void setPlayerX(int playerX) {
		this.playerX = playerX;
	}

	/**
	 * get the player y location
	 * 
	 * @return
	 */
	public int getPlayerY() {
		return playerY;
	}

	/**
	 * set the player y location
	 * 
	 * @param playerY
	 */
	public void setPlayerY(int playerY) {
		this.playerY = playerY;
	}

	/**
	 * set all the block movable
	 */
	public void setAllBlockMovable() {

		for (int i = 0; i < columnCount; i++) {
			for (int j = 0; j < rowCount; j++) {
				block[i][j].setLand(LandType.LAND_GRASS);
			}
		}
	}

	/**
	 * get the role of map role
	 * @return
	 */
	public MapRole getRole() {
		return role;
	}

	/**
	 * get monster 
	 * @return
	 */
	public ArrayList<MapItem> getMonster() {
		return m_monsters;
	}

	/**
	 * get the fixed item
	 * @return
	 */
	public ArrayList<MapItem> getFixedItems() {
		return this.fixedItems;
	}

	/**
	 * set all the blocks to wall
	 */
	public void setAllBlockIsWall() {

		for (int i = 0; i < columnCount; i++) {
			for (int j = 0; j < rowCount; j++) {
				block[i][j].setLand(LandType.LAND_HOR_WALL);
			}
		}
	}

	/**
	 * clear method
	 */
	public void clear() {
		this.m_monsters.clear();
	}

}
