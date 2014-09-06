package Data.Builder;

import Data.Character.Ability;
import Data.Character.Skills;
import Data.GameData.CommonData;
import GUI.battleView.BattleLog;

/**
 * This is a Bully Builder
 * 
 * @author Lin Zhu
 * 
 */
public class BullyBuilder extends CharacterBuilder {

	public void abilityGeneration() {
		hero.ChaInfo.setFighterType(CommonData.FIGHTER_TYPE_BULLY);
		Ability newAbi = new Ability(1);
		hero.setAbility(newAbi);
	}

	public void abilityGeneration(BattleLog bl) {
		hero.ChaInfo.setFighterType(CommonData.FIGHTER_TYPE_BULLY);
		Ability newAbi = new Ability(1, bl);
		hero.setAbility(newAbi);
	}

	public void skillsGeneration() {
		hero.skill = new Skills(1);
	}

	public void adjustLevel(int level, BattleLog bl) {

		hero.updateLevelTo(level, bl);
		
	}

}
