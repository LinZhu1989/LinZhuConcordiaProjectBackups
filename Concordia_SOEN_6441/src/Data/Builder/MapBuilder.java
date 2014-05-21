package Data.Builder;

import GUI.battleView.BattleLog;
import GUI.mapView.MapInfor;

/**
 * This is a Map Builder
 * 
 * @author Lin Zhu
 * 
 */
public abstract class MapBuilder {
	protected MapInfor map;

	public MapInfor getMapInfor() {
		return map;
	}

	public void createNewMapInfor() {
		map = new MapInfor();
	}

	public abstract boolean updateMapInfor(MapInfor oldMap, BattleLog bl);
}
