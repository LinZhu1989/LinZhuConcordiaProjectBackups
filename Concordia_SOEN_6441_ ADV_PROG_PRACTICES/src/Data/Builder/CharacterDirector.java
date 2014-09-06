package Data.Builder;

import Data.Character.GameCharacter;
import GUI.battleView.BattleLog;

/**
 * This is a Character Director
 * 
 * @author Lin Zhu
 * 
 */
public class CharacterDirector {
	private CharacterBuilder characterBuilder;

	public void setCharacterBuilder(CharacterBuilder cb) {
		characterBuilder = cb;
	}

	public GameCharacter getGameCharacter() {
		return characterBuilder.getGameCharacter();
	}

	public void constructCharacter() {
		characterBuilder.createNewGameCharacter();
		characterBuilder.abilityGeneration();
		characterBuilder.skillsGeneration();
	}

	public void constructCharacter(BattleLog bl) {
		characterBuilder.createNewGameCharacter();
		characterBuilder.abilityGeneration(bl);
		characterBuilder.skillsGeneration();
	}
	
	public void constructCharacter(BattleLog bl,int level) {
		characterBuilder.createNewGameCharacter(level);
		characterBuilder.abilityGeneration(bl);
		characterBuilder.skillsGeneration();
		characterBuilder.adjustLevel(level, bl);
	}

}
