package Data.Builder;

import Data.Character.GameCharacter;
import Data.Character.GameFixedItem;
import GUI.battleView.BattleLog;

/**
 * This is a Chest Director
 * 
 * @author Lin Zhu
 * 
 */
public class ChestDirector {
	private ChestBuilder chestBuilder;

	public void setChestBuilder(ChestBuilder cb) {
		chestBuilder = cb;
	}

	public GameFixedItem getChestBuilderr() {
		return chestBuilder.getChest();
	}

	public void constructCharacter(BattleLog bl,int level) {
		chestBuilder.createNewChest();
		chestBuilder.updateChest(level, bl);
	}

}
