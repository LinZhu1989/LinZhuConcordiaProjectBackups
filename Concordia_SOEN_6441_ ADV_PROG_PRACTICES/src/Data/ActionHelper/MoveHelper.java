/**
 * @author Lin Zhu (6555659)
 * SOEN 6441 Team Project 
 * Winter 2014
 */
package Data.ActionHelper;

/**
 * the class MoveHelper helps the action of a character
 * 
 * @author Lin Zhu
 * 
 */
import java.awt.Point;
import java.util.ArrayList;

import utility.LandType;
import GUI.mapView.MapBlock;
import GUI.mapView.MapInfor;

public class MoveHelper {
	/**
	 * Class MoveHelper Constructor (With no argument)
	 */
	public MoveHelper() {

	}

	/**
	 * calculate the movable blocks
	 * 
	 * @param mp
	 * @param moveDistance
	 * @return
	 */
	public ArrayList<MovableTarget> calMoveBlocks(MapInfor mp, int moveDistance) {

		mp.setAllunFound();
		ArrayList<MovableTarget> movablePath = new ArrayList<MovableTarget>(5);
		Point location = mp.getPlayerLocation();
		mp.getBlock(location).found = true;
		movablePath.addAll(calPosiMoveInDirectionMethod(mp, location,
				moveDistance));

		return movablePath;

	}
	
	/**
	 * calculate the movable blocks
	 * 
	 * @param mp
	 * @param moveDistance
	 * @return
	 */
	public ArrayList<MovableTarget> calOppMoveBlocks(MapInfor mp,Point loc, int moveDistance) {

		mp.setAllunFound();
		ArrayList<MovableTarget> movablePath = new ArrayList<MovableTarget>(5);
		mp.getBlock(loc).found = true;
		movablePath.addAll(calPosiMoveInDirectionMethod(mp, loc,
				moveDistance));
		return movablePath;

	}


	/**
	 * calculate possible Move Directions
	 * 
	 * @param mp
	 * @param location
	 * @param moveDistance
	 * @return
	 */
	public ArrayList<MovableTarget> calPosiMoveInDirectionMethod(MapInfor mp,
			Point location, int moveDistance) {
		ArrayList<MovableTarget> movablePath = new ArrayList<MovableTarget>(5);

		if (moveDistance > 0) {
			for (int dirct = 1; dirct < 5; dirct++) {
				Point nextBlock = new Point();
				int moveAbility = moveDistance;
				switch (dirct) {
				case 1:
					nextBlock.setLocation(location.x - 1, location.y);
					break;
				case 2:
					nextBlock.setLocation(location.x + 1, location.y);
					break;
				case 3:
					nextBlock.setLocation(location.x, location.y - 1);
					break;
				case 4:
					nextBlock.setLocation(location.x, location.y + 1);
					break;
				}
				if (nextBlock.x >= 0 && nextBlock.y >= 0
						&& nextBlock.x <= mp.getColumnCount() - 1
						&& nextBlock.y <= mp.getRowCount() - 1) {
					MapBlock block = mp.getBlock(nextBlock);

					if ( (block.getLand() == LandType.LAND_GRASS ||
						 block.getLand() == LandType.LAND_SAND ||
						 block.getLand() == LandType.LAND_STONE|| block.getLand() == LandType.LAND_DOOR_OPENED||block.getLand() == LandType.LAND_DOOR_CLOSED)
							&& (block.isOccupied() == false)
							&& (!block.found || (block.found && block.foundValue < moveAbility))) {
				
						if (block.getLand() == LandType.LAND_DOOR_CLOSED) {
							MovableTarget target = new MovableTarget();
							target.setTarget(nextBlock);
							block.found = true;
							block.foundValue = moveAbility;
							target.addPathInFront(nextBlock);
							target.addPathInFront(location);
							movablePath.add(target);
							moveAbility=0;
						}
						
						if (block.getLand() != LandType.LAND_UNDEFINED) {
							MovableTarget target = new MovableTarget();
							target.setTarget(nextBlock);
							block.found = true;
							block.foundValue = moveAbility;
							target.addPathInFront(nextBlock);
							target.addPathInFront(location);

							movablePath.add(target);
						}
						if (moveAbility - 1 > 0) {
							ArrayList<MovableTarget> futureTargets = new ArrayList<MovableTarget>();
							futureTargets = calPosiMoveInDirectionMethod(mp,
									nextBlock, moveAbility - 1);
							for (int i = 0; i < futureTargets.size(); i++) {
								futureTargets.get(i).addPathInFront(location);
							}

							movablePath.addAll(futureTargets);
						}

					} else {
						continue; // Stop searching in this direction
						// because of
						// wall
					}
				} else {
					continue; // Stop searching in this direction
								// because
					// of out of
					// map
				}
			}
			return movablePath;

		}
		return null;
	}
}
