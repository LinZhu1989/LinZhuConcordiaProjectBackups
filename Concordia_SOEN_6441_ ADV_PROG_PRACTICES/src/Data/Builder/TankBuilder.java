package Data.Builder;

import Data.Character.Ability;
import Data.Character.Skills;
import Data.GameData.CommonData;
import GUI.battleView.BattleLog;

/**
 * This is a Thank BUilder
 * 
 * @author Lin Zhu
 * 
 */
public class TankBuilder extends CharacterBuilder {

	public void abilityGeneration() {
		hero.ChaInfo.setFighterType(CommonData.FIGHTER_TYPE_TANK);
		Ability newAbi = new Ability(3);
		hero.setAbility(newAbi);
	}

	public void abilityGeneration(BattleLog bl) {
		hero.ChaInfo.setFighterType(CommonData.FIGHTER_TYPE_TANK);
		Ability newAbi = new Ability(3, bl);
		hero.setAbility(newAbi);
	}

	public void skillsGeneration() {
		hero.skill = new Skills(3);
	}
	
	public void adjustLevel(int level, BattleLog bl) {

		hero.updateLevelTo(level, bl);
		
	}
}
