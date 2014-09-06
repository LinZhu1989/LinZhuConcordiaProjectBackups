package Data.Builder;

import Data.Character.GameCharacter;
import GUI.battleView.BattleLog;

/**
 * This is a Character Builder
 * 
 * @author Lin Zhu
 * 
 */
abstract public class CharacterBuilder {
	protected GameCharacter hero;

	public GameCharacter getGameCharacter() {
		return hero;
	}

	public void createNewGameCharacter(int level) {
		hero = new GameCharacter();
	}
	
	public abstract void abilityGeneration();

	public abstract void skillsGeneration();

	public abstract void abilityGeneration(BattleLog bl);
	
	public abstract void adjustLevel(int level,BattleLog bl);
	
	public void createNewGameCharacter() {
		hero = new GameCharacter();

	}
}
