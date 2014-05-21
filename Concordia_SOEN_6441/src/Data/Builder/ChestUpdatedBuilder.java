package Data.Builder;

import java.util.List;

import GUI.battleView.BattleLog;
import GUI.mapView.MapFixedItemFactory.MapFixedItem;
import GUI.mapView.MapInfor;
import GUI.mapView.MapItem;
import GUI.mapView.MapMonsterFactory.MapMonster;
import GUI.mapView.MapRoleFactory.MapRole;

/**
 * This is a Map Updated Builder
 * 
 * @author Lin Zhu
 * 
 */
public class ChestUpdatedBuilder extends ChestBuilder {

	public boolean updateChest(int level, BattleLog bl) {
		if (chest == null) {
			return false;
		} else {
			chest.updateItems(level, bl);
			return true;
		}
	}
}
