package Data.Builder;

import GUI.battleView.BattleLog;
import GUI.mapView.MapInfor;

/**
 * This is a Map Director
 * 
 * @author Lin Zhu
 * 
 */
public class MapDirector {
	private MapBuilder mapBuilder;

	public void setMapBuilder(MapBuilder cb) {
		mapBuilder = cb;
	}

	public MapInfor getMapInfor() {
		return mapBuilder.getMapInfor();
	}

	public void updatetMap(MapInfor oldMap, BattleLog bl) {
		mapBuilder.updateMapInfor(oldMap, bl);
	}
}
