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

public class AttackHelper {

	private int attackerType; // 1 is role,2 is enemy,3 is monster
	private int attackRange;
	private WeaponsType weaponsType;

	/**
	 * Class AttackHelper Constructor (With no argument)
	 */
	public AttackHelper() {
		this.attackerType = 0;
		this.attackRange = 0;
		this.weaponsType = WeaponsType.NOWEAPONS;
	}

	public ArrayList<Point> calAttackBlocks(MapInfor mp, Point loc,
			boolean withEmpty) {

		ArrayList<Point> attackPoints = new ArrayList<Point>(1);
		WeaponsType type = WeaponsType.NOWEAPONS;

		if(mp.getBlock(loc).getMapItem()==null){
			return null;
		}
		
		if (mp.getBlock(loc).getMapItem().getBlockType()
				.equals(MapBlockType.BLOCK_ROLE)) {

			if (loc.x == mp.getPlayerX() && loc.y == mp.getPlayerY()) {
				System.out.println("Player attempt to attack!");
				this.attackerType = 1;
			} else {
				System.out.println("Enemy attempt to attack!");
				this.attackerType = 2;
			}

			MapRole role = (MapRole) mp.getBlock(loc).getMapItem();
			type = role.getGameCharacter().equipment.getWeapons().getType();
			if (type.equals(WeaponsType.SWORD)
					|| type.equals(WeaponsType.NOWEAPONS)) {
				this.attackRange = 1;
			} else if (type.equals(WeaponsType.BOW)) {
				this.attackRange = 2;
			} else {
				this.attackRange = 0;
			}

		} else if (mp.getBlock(loc).getMapItem().getBlockType()
				.equals(MapBlockType.BLOCK_MONSTER)) {
			System.out.println("Monster attempt to attack!");
			this.attackerType = 3;
			this.attackRange = 1;
		} else {
			this.attackerType = 0;
			this.attackRange = 0;
			System.out.println("Nothing can attack here!");
			return null;
		}

		attackPoints.addAll(calAttackBlocksHelper(mp, loc, withEmpty));

		return attackPoints;
	}

	private ArrayList<Point> calAttackBlocksHelper(MapInfor mp, Point location,
			boolean withEmpty) {
		ArrayList<Point> attackBlks = new ArrayList<Point>(1);
		int mapY = mp.getRowCount();
		int mapX = mp.getColumnCount();

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
				if (location.x + this.attackRange < mapX) {
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
				if (location.y + this.attackRange < mapY) {
					nextBlock.setLocation(location.x, location.y
							+ this.attackRange);
				}
				break;
			}

			if (mp.getBlock(nextBlock).getMapItem() == null) {
				if (withEmpty) {
					attackBlks.add(nextBlock);
					// add if with empty
				}
				// nothing to attack if without empty
			} else {
				switch (this.attackerType) {
				case 1:
					if (mp.getBlock(nextBlock).getMapItem().getBlockType()
							.equals(MapBlockType.BLOCK_MONSTER)
							|| mp.getBlock(nextBlock).getMapItem()
									.getBlockType()
									.equals(MapBlockType.BLOCK_ROLE)) {
						attackBlks.add(nextBlock);
					}
					break;
				case 2:
					if (mp.getBlock(nextBlock).getMapItem().getBlockType()
							.equals(MapBlockType.BLOCK_MONSTER)
							|| mp.getBlock(nextBlock).getMapItem()
									.getBlockType()
									.equals(MapBlockType.BLOCK_ROLE)) {
						attackBlks.add(nextBlock);
					}
					break;
				case 3:
					if (mp.getBlock(nextBlock).getMapItem()
									.getBlockType()
									.equals(MapBlockType.BLOCK_ROLE)) {
						attackBlks.add(nextBlock);
					}
					break;
				default:
					break;
				}

			}
		}

		initialALl();
		return attackBlks;
	}

	private void initialALl() {
		this.attackerType = 0;
		this.attackRange = 0;
		this.weaponsType = WeaponsType.NOWEAPONS;
	}
}
