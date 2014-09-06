/**
 * @author Lin Zhu (6555659)
 * SOEN 6441 Team Project 
 * Winter 2014
 */
package Data.ActionHelper;

/**
 * the class AttackHelper helps the action of a character
 * 
 * @author Lin Zhu
 * 
 */
import java.awt.Point;
import java.util.ArrayList;

import utility.*;
import Data.Item.WeaponsType;
import GUI.mapView.*;
import GUI.mapView.MapRoleFactory.MapRole;

public class CheckAroundHelper {

	private int attackRange;

	/**
	 * Class AroundHelper Constructor (With no argument)
	 */
	public CheckAroundHelper() {
		this.attackRange = 1;
	}

	public ArrayList<Point> checkDoorBlocks(MapInfor mp, Point loc) {

		ArrayList<Point> aroundPoints = new ArrayList<Point>(1);

		aroundPoints.addAll(calAroundBlocksHelper(mp, loc, 1));

		return aroundPoints;
	}
	
	public ArrayList<Point> checkMerchantBlocks(MapInfor mp, Point loc) {

		ArrayList<Point> aroundPoints = new ArrayList<Point>(1);

		aroundPoints.addAll(calAroundBlocksHelper(mp, loc, 2));

		return aroundPoints;
	}
	
	public ArrayList<Point> checkChestBlocks(MapInfor mp, Point loc) {

		ArrayList<Point> aroundPoints = new ArrayList<Point>(1);

		aroundPoints.addAll(calAroundBlocksHelper(mp, loc, 3));

		return aroundPoints;
	}

	private ArrayList<Point> calAroundBlocksHelper(MapInfor mp, Point location,
			int type) {
		ArrayList<Point> attackBlks = new ArrayList<Point>(1);
		int mapX = mp.getRowCount();
		int mapY = mp.getColumnCount();

		for (int dirct = 1; dirct < 5; dirct++) {
			Point nextBlock = new Point();
			switch (dirct) {
			case 1:
				if (location.x - this.attackRange >= 0) {
					nextBlock.setLocation(location.x - this.attackRange,
							location.y);
				}
				break;
			case 2:
				if (location.x + this.attackRange <= mapX) {
					nextBlock.setLocation(location.x + this.attackRange,
							location.y);
				}
				break;
			case 3:
				if (location.y - this.attackRange >= 0) {
					nextBlock.setLocation(location.x, location.y
							- this.attackRange);
				}
				break;
			case 4:
				if (location.y + this.attackRange <= mapY) {
					nextBlock.setLocation(location.x, location.y
							+ this.attackRange);
				}
				break;
			}
			switch (type) {
			case 1:
				if (mp.getBlock(nextBlock).getMapItem() == null) {
					if (mp.getBlock(nextBlock).getLand()
							.equals(LandType.LAND_DOOR_CLOSED)) {
						attackBlks.add(nextBlock);
					}
				}
				break;
			case 2:
				if (mp.getBlock(nextBlock).getMapItem() == null) {

				} else {
					if (mp.getBlock(nextBlock).getMapItem().getBlockType()
							.equals(MapBlockType.BLOCK_MERCHANT)) {

					}
				}
				break;
			case 3:
				if (mp.getBlock(nextBlock).getMapItem() == null) {

				} else {
					if (mp.getBlock(nextBlock).getMapItem().getBlockType()
							.equals(MapBlockType.BLOCK_CHEST)) {

					}
				}
				break;
			}

		}
		return attackBlks;
	}

}
