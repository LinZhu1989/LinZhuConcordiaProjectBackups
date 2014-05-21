package Data.Builder;

import Data.Character.Ability;
import Data.Character.Skills;
import Data.GameData.CommonData;
import GUI.battleView.BattleLog;

/**
 * This is a Nimble Builder
 * 
 * @author Lin Zhu
 * 
 */
public class NimbleBuilder extends CharacterBuilder {

	public void abilityGeneration() {
		hero.ChaInfo.setFighterType(CommonData.FIGHTER_TYPE_NIMBLE);
		Ability newAbi = new Ability(2);
		hero.setAbility(newAbi);
	}

	public void abilityGeneration(BattleLog bl) {
		hero.ChaInfo.setFighterType(CommonData.FIGHTER_TYPE_NIMBLE);
		Ability newAbi = new Ability(2, bl);
		hero.setAbility(newAbi);
	}

	public void skillsGeneration() {
		hero.skill = new Skills(2);
	}
	
	public void adjustLevel(int level, BattleLog bl) {

		hero.updateLevelTo(level, bl);
		
	}
}
