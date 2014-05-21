package GUI.mapView;
/**
 * @author DanZhang
 * SOEN 6441 Team Project 
 * Winter 2014
 */
import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.Random;

import utility.FixedItemType;
import utility.InOutType;
import utility.LandType;
/**
 *this is the class to creat a random map 
 *
 */
public class MapRandom {
	private Random random;
	private MapResource R;
	private MapBlock board[][];
	MapInfor mapI;
	
	/**
	 * constructor of the MapRondom 
	 * @param R
	 * @param mapI
	 */
	public MapRandom(MapResource R, MapInfor mapI) {
		random = new Random();
		this.R = R;
		this.mapI = mapI;
		board = new MapBlock[R.mapInfor.getColumnCount()][R.mapInfor.getRowCount()];
		for (int i = 0; i < R.mapInfor.getColumnCount(); i++) {
			for (int j = 0; j < R.mapInfor.getRowCount(); j++) {
				board[i][j] = new MapBlock(i,j);
			}
		}
	}
	
	/**
	 * creat a map block
	 * @return
	 */
	public MapBlock[][] create() {
		
		LandType road =	randomMapRoadStyle();
		randomMapWallStyle();
		randomMapLandStyle(road);
		randomMapMerchant();
		randomMapMonster();
		randomMapRole();
		randomMapOut();

		return board;
	}

	/**
	 * random map out
	 */
	private void randomMapOut() {
		int x = random.nextInt(8);
		int y = random.nextInt(8);
		board[x][y].setInOut(InOutType.INVALIDOUT);			
	}
	
	/**
	 * random map role
	 */
	private void randomMapRole() {
		int x = 0;
		int y;
		int number = random.nextInt(3) + 2;
		for (int i = 0; i < number; i++) {

			do {
				x = random.nextInt(R.mapInfor.getColumnCount());
				y = random.nextInt(R.mapInfor.getRowCount());
			} while (board[x][y].isOccupied() || board[x][y].isWall());

			int type = randomRole();
			board[x][y].setRole(type);
			if (i == 1) {
				mapI.setPlayerX(x);
				mapI.setPlayerY(y);
				mapI.setRoleSelectDone(true);
			}
		}

	}

	/**
	 * random map monster
	 */
	private void randomMapMonster() {
		int number = random.nextInt(2) + 0;
		int x;
		int y;
		for (int i = 0; i < number; i++) {
			do {
				x = random.nextInt(R.mapInfor.getColumnCount());
				y = random.nextInt(R.mapInfor.getRowCount());
			} while (board[x][y].isOccupied() || board[x][y].isWall());
			int type = randomMonster();
			board[x][y].setMonster(type);
		}
	}

	/**
	 * random merchant 
	 */
	private void randomMapMerchant() {
		int number = random.nextInt(2) + 1;

		int x;
		int y;
		for (int i = 0; i < number; i++) {
			do {
				x = random.nextInt(R.mapInfor.getColumnCount());
				y = random.nextInt(R.mapInfor.getRowCount());
			} while (board[x][y].isOccupied() || board[x][y].isWall());
			board[x][y].setFixedItem(FixedItemType.MERCHANT);
			//board[x][y].setImage(R.imgMerchant_B);
		}
		number = random.nextInt(4) + 1;
		for (int i = 0; i < number; i++) {
			do {
				x = random.nextInt(R.mapInfor.getColumnCount());
				y = random.nextInt(R.mapInfor.getRowCount());
			} while (board[x][y].isOccupied() || board[x][y].isWall());
			board[x][y].setFixedItem(FixedItemType.CHEST);
			//board[x][y].setImage(R.imgTreasure_B);
		}

	}

	/**
	 * random land style
	 * @param road
	 */
	private void randomMapLandStyle(LandType road) {
		LandType land = LandType.LAND_UNDEFINED;
		do {
		land = randomLandType();
		} while (land == road);
		
		for (int i = 0; i < R.mapInfor.getColumnCount(); i++) {
			for (int j = 0; j < R.mapInfor.getRowCount(); j++) {
				if (board[i][j].blankBlock()) {
					board[i][j].setLand(land);
				}
			}
		}
	}

	/**
	 * random map wall style
	 */
	private void randomMapWallStyle() {
		int centerX = 2 + random.nextInt(8);
		int centerY = 2 + random.nextInt(8);

		for (int j = 0; j < R.mapInfor.getRowCount(); j++) {
			if (board[centerX][j].blankBlock()) {
				board[centerX][j].setLand(LandType.LAND_VER_WALL);				
			}
			else
				board[centerX][j].setLand(LandType.LAND_DOOR_CLOSED);		
		}
		for (int i = 0; i < R.mapInfor.getColumnCount(); i++) {
			if (board[i][centerY].blankBlock()) {
				board[i][centerY].setLand(LandType.LAND_HOR_WALL);
			}
			else
				board[i][centerY].setLand(LandType.LAND_DOOR_CLOSED);

		}

	}

	/**
	 * random road style
	 * @return
	 */
	private LandType randomMapRoadStyle() {

		int verticalDraw = 1;
		int sY = random.nextInt(R.mapInfor.getRowCount());
		
		int minY = 1;
		int length = 1;
		int maxY = 1;
		int firstVertical = 1;
		int verticalMin;
		int verticalMax;
		
		for (int i = 0; i < R.mapInfor.getColumnCount(); i++) {
			for (int j = 0; j < R.mapInfor.getRowCount(); j++) {
				board[i][j] = new MapBlock(i,j);
			}
		}
		verticalMin = random.nextInt(4);
		verticalMax = random.nextInt(5) + R.mapInfor.getColumnCount() - 5;

		LandType landType = randomLandType();
		for (int i = verticalMin; i < verticalMax; i++) {

			if (verticalDraw == 1) {
				if (firstVertical == 1) {
					minY = random.nextInt(2);
					length = random.nextInt(4) + 4;
					maxY = minY + length;
					for (int j = minY; j <= maxY; j++) {
						board[i][j].setLand(landType);
					}
					firstVertical = 0;
				} else {
					// Go up top vertex
					if (minY != 0) {
						length = random.nextInt(minY);
						for (int j = minY; j > length; j--) {
							board[i][j].setLand(landType);
						}
						minY = length + 1;
					} else {
						board[i][minY].setLand(landType);
					}

					if (maxY != R.mapInfor.getRowCount()) {
						length = random.nextInt(R.mapInfor.getRowCount() - maxY) + 1;
						for (int j = maxY; j < maxY + length; j++) {
							board[i][j].setLand(landType);
						}
						maxY = maxY + length - 1;
					} else {
						board[i][maxY].setLand(landType);
					}

				}
			}
			// Draw the Horizon
			else {
				board[i][minY].setLand(landType);
				board[i][maxY].setLand(landType);
				firstVertical = 0;
			}

			verticalDraw = random.nextInt(2);
			if (i == verticalMax - 1) {
				for (int j = minY; j <= maxY; j++) {
					board[i][j].setLand(landType);
				}
			}
		}

		return landType;
	}

	/**
	 * random land type
	 * @return
	 */
	private LandType randomLandType() {
		int land = random.nextInt(3) + 1;
		switch (land) {
		case 1:
			setImage(R.imgSand);
			return LandType.LAND_SAND;			
		case 2:
			setImage(R.imgGrass);
			return LandType.LAND_GRASS;			
		default:
			setImage(R.imgStone);
			return LandType.LAND_STONE;
			}
	}

	/**
	 * random role type
	 * @return
	 */
	private int randomRole() {
		int type = random.nextInt(4);

		switch (type) {
		case 1:
			setImage(R.imgRole_1_B);
			break;
		case 2:
			setImage(R.imgRole_2_B);
			break;
		case 3:
			setImage(R.imgRole_3_B);
			break;
		default:
			setImage(R.imgRole_1_B);
			break;

		}
		return type;
	}

	/**
	 * random monster
	 * @return
	 */
	private int randomMonster() {
		int type = random.nextInt(10) + 1;

		switch (type) {
		case 1:
			setImage(R.imgMonster1_1);
			break;
		case 2:
			setImage(R.imgMonster1_2);
			break;
		case 3:
			setImage(R.imgMonster2_1);
			break;
		case 4:
			setImage(R.imgMonster2_2);
			break;
		case 5:
			setImage(R.imgMonster3_1);
			break;
		case 6:
			setImage(R.imgMonster3_2);
			break;
		case 7:
			setImage(R.imgMonster4_1);
			break;
		case 8:
			setImage(R.imgMonster4_2);
			break;
		case 9:
			setImage(R.imgMonster5_1);
			break;
		case 10:
			setImage(R.imgMonster5_2);
			break;
		}
		return type;
	}

	/**
	 * set image
	 * @param img
	 */
	private void setImage(Image img) {

	}
}
