package Data.Builder;

import utility.FixedItemType;
import Data.Character.GameFixedItem;
import GUI.battleView.BattleLog;
import GUI.mapView.MapInfor;

/**
 * This is a Chest Builder
 * 
 * @author Lin Zhu
 * 
 */
public abstract class ChestBuilder {
	protected GameFixedItem chest;

	public GameFixedItem getChest() {
		return chest;
	}

	public void createNewChest() {
		chest = new GameFixedItem(FixedItemType.CHEST);
	}

	public abstract boolean updateChest(int level, BattleLog bl);
}
