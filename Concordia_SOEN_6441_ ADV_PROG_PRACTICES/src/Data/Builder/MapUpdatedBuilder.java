package Data.Builder;

import java.util.List;

import Data.Character.GameFixedItem;
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
public class MapUpdatedBuilder extends MapBuilder {

	public boolean updateMapInfor(MapInfor oldMap, BattleLog bl) {
		bl.setLogInfor("Updating Map...");
		oldMap.analysisMap();
		bl.setLogInfor("-> Analyzing Map...");
		int lvl = oldMap.getRole().getGameCharacter().status.getLevel();
		bl.setLogInfor("-> Player level is: " + lvl);
		List<MapItem> mlist = oldMap.getMonster();
		List<MapItem> itemlist = oldMap.getFixedItems();

		if (mlist != null && itemlist != null) {
			bl.setLogInfor("-> Totol " + (mlist.size() + itemlist.size())
					+ " MapItems to be checked for update...");
			bl.setLogInfor("-> " + mlist.size() + " Monsters ");
			bl.setLogInfor("-> " + itemlist.size() + " FixtedItems ");
			if (mlist.size() > 0) {
				for (MapItem m : mlist) {
					if (m instanceof MapMonster) {
						bl.setLogInfor("-> Check to update MONSTER...");
						((MapMonster) m).getGameMonster()
								.updateLevelTo(lvl, bl);
					} else if (m instanceof MapRole) {
						bl.setLogInfor("-> Check to update ROLE...");
						if(lvl>4){
							((MapRole) m).getGameCharacter().updateLevelTo(lvl-4, bl);
						}else{
							((MapRole) m).getGameCharacter().updateLevelTo(1, bl);
						}
					}
				}
			}

			if (itemlist.size() > 0) {
				for (MapItem m : itemlist) {
					if (m instanceof MapFixedItem) {
						bl.setLogInfor("-> Check to update 	MERCHANT/CHEST...");
//						((MapFixedItem) m).getMapFixedItem().updateItems(lvl,
//								bl);
						ChestDirector cd = new ChestDirector();
						ChestUpdatedBuilder cub=new ChestUpdatedBuilder();
						cd.setChestBuilder(cub);
						cd.constructCharacter(bl, lvl);
						((MapFixedItem) m).getGameFixedItem().addItem(cd.getChestBuilderr());

					}
					
				}
			}
			return true;
		} else if (mlist == null) {
			bl.setLogInfor("-> Totol " + itemlist.size()
					+ " MapItems to be checked for update...");
			bl.setLogInfor("-> " + 0 + " Monsters ");
			bl.setLogInfor("-> " + itemlist.size() + " FixtedItems ");
			if (itemlist.size() > 0) {
				for (MapItem m : itemlist) {
					if (m instanceof MapFixedItem) {
						bl.setLogInfor("-> Check to update 	MERCHANT/CHEST...");
//						((MapFixedItem) m).getMapFixedItem().updateItems(lvl,
//								bl);
						ChestDirector cd = new ChestDirector();
						ChestUpdatedBuilder cub=new ChestUpdatedBuilder();
						cd.setChestBuilder(cub);
						cd.constructCharacter(bl, lvl);
						((MapFixedItem) m).getGameFixedItem().addItem(cd.getChestBuilderr());
					}
				}
			}

			return true;
		} else if (itemlist == null) {
			bl.setLogInfor("-> Totol " + mlist.size()
					+ " MapItems to be checked for update...");
			bl.setLogInfor("-> " + mlist.size() + " Monsters ");
			bl.setLogInfor("-> " + 0 + " FixtedItems ");

			if (mlist.size() > 0) {
				for (MapItem m : mlist) {
					if (m instanceof MapMonster) {
						bl.setLogInfor("-> Check to update MONSTER...");
						((MapMonster) m).getGameMonster()
								.updateLevelTo(lvl, bl);
					} else if (m instanceof MapRole) {
						bl.setLogInfor("-> Check to update ROLE...");
						if(lvl>4){
							((MapRole) m).getGameCharacter().updateLevelTo(lvl-4, bl);
						}else{
							((MapRole) m).getGameCharacter().updateLevelTo(1, bl);
						}
						
					}
				}
			}
			return true;
		}
		return false;

	}

}
