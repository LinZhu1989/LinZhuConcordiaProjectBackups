/**
 * @author Lin Zhu (6555659)
 * SOEN 6441 Team Project 
 * Winter 2014
 */
package Data.Generator;

import java.util.ArrayList;

import Data.Builder.DiceTool;
import Data.Character.Ability;
import Data.Character.Attribute;
import Data.Character.GameCharacter;
import Data.Character.Skills;
import Data.GameData.CommonData;

/**
 * the class HeroGenerator helps to generate the hero character
 * 
 * @author Lin Zhu
 * 
 */
public class HeroGenerator {
	
	/**
	 * Class HeroGenerator Constructor (With no argument)
	 */
	private HeroGenerator() {

	}

	/**
	 * get Multiple Heros Without Race Set
	 * 
	 * @param number
	 * @return
	 */
	public ArrayList<GameCharacter> getMultipleHerosWithoutRaceSet(int number) {

		DiceTool diceRace = new DiceTool(
				CommonData.CHARACTER_INFORMATION_FIGHTER_TYPES.length);
		DiceTool diceName = new DiceTool(
				CommonData.CHARACTER_INFORMATION_NAMES.length);

		ArrayList<GameCharacter> characters = new ArrayList<GameCharacter>(
				number);
		for (int i = 0; i < number; i++) {
			String chaName;
			chaName = CommonData.CHARACTER_INFORMATION_NAMES[diceName.getValue()-1];
			int raceNum = diceRace.getValue();
			GameCharacter tempCharacter = new GameCharacter(
					chaName,
					CommonData.CHARACTER_INFORMATION_FIGHTER_TYPES[raceNum],
					CommonData.CHARACTER_STATUS_RACEBASICHP[raceNum],
					CommonData.CHARACTER_STATUS_RACEBASICMP[raceNum],
					new Ability(
							CommonData.CHARACTER_ABILITY_RACEBASICS[raceNum][0],
							CommonData.CHARACTER_ABILITY_RACEBASICS[raceNum][1],
							CommonData.CHARACTER_ABILITY_RACEBASICS[raceNum][2],
							CommonData.CHARACTER_ABILITY_RACEBASICS[raceNum][3],
							CommonData.CHARACTER_ABILITY_RACEBASICS[raceNum][4],
							CommonData.CHARACTER_ABILITY_RACEBASICS[raceNum][5]),
					new Attribute(
							CommonData.CHARACTER_ATTRIBUTE_BASICATK,
							CommonData.CHARACTER_ATTRIBUTE_BASICPHYSICALARMOR[raceNum],
							CommonData.CHARACTER_ATTRIBUTE_BASICMAGICRRESIST[raceNum],
							CommonData.CHARACTER_ATTRIBUTE_BASICMOVEDISTANCE[raceNum],
							CommonData.CHARACTER_ATTRIBUTE_BASICATTACKDISTANCE[raceNum]),
					new Skills());
			characters.add(tempCharacter);
		}
		return characters;
	}

	/**
	 * get Multiple Heros With Race Set
	 * 
	 * @param number
	 * @param name
	 * @return
	 */
	public ArrayList<GameCharacter> getMultipleHerosWithRaceSet(int number,
			String name) {
		DiceTool diceName = new DiceTool(
				CommonData.CHARACTER_INFORMATION_NAMES.length);

		ArrayList<GameCharacter> characters = new ArrayList<GameCharacter>(
				number);
		for (int i = 0; i < number; i++) {
			String chaName;
			chaName = CommonData.CHARACTER_INFORMATION_NAMES[diceName.getValue()-1];
			int raceNum = 0;
			if (name.equalsIgnoreCase(CommonData.FIGHTER_TYPE_BULLY)) {
				raceNum = 0;
			} else if (name
					.equalsIgnoreCase(CommonData.FIGHTER_TYPE_NIMBLE)) {
				raceNum = 1;
			} else {
				raceNum = 2;
			}
			GameCharacter tempCharacter = new GameCharacter(
					chaName,
					name,
					CommonData.CHARACTER_STATUS_RACEBASICHP[raceNum],
					CommonData.CHARACTER_STATUS_RACEBASICMP[raceNum],
					new Ability(
							CommonData.CHARACTER_ABILITY_RACEBASICS[raceNum][0],
							CommonData.CHARACTER_ABILITY_RACEBASICS[raceNum][1],
							CommonData.CHARACTER_ABILITY_RACEBASICS[raceNum][2],
							CommonData.CHARACTER_ABILITY_RACEBASICS[raceNum][3],
							CommonData.CHARACTER_ABILITY_RACEBASICS[raceNum][4],
							CommonData.CHARACTER_ABILITY_RACEBASICS[raceNum][5]),
					new Attribute(
							CommonData.CHARACTER_ATTRIBUTE_BASICATK,
							CommonData.CHARACTER_ATTRIBUTE_BASICPHYSICALARMOR[raceNum],
							CommonData.CHARACTER_ATTRIBUTE_BASICMAGICRRESIST[raceNum],
							CommonData.CHARACTER_ATTRIBUTE_BASICMOVEDISTANCE[raceNum],
							CommonData.CHARACTER_ATTRIBUTE_BASICATTACKDISTANCE[raceNum]),
					new Skills());
			characters.add(tempCharacter);
		}
		return characters;
	}

	/**
	 * get One Hero With Race Set
	 * 
	 * @param name
	 * @return
	 */
	public GameCharacter getOneHeroWithRaceSet(String name) {
		DiceTool diceName = new DiceTool(
				CommonData.CHARACTER_INFORMATION_NAMES.length);

		String chaName;
		chaName = CommonData.CHARACTER_INFORMATION_NAMES[diceName.getValue()-1];
		int raceNum = 0;
		if (name.equalsIgnoreCase(CommonData.FIGHTER_TYPE_BULLY)) {
			raceNum = 0;
		} else if (name
				.equalsIgnoreCase(CommonData.FIGHTER_TYPE_NIMBLE)) {
			raceNum = 1;
		} else {
			raceNum = 2;
		}
		GameCharacter character = new GameCharacter(
				chaName,
				name,
				CommonData.CHARACTER_STATUS_RACEBASICHP[raceNum],
				CommonData.CHARACTER_STATUS_RACEBASICMP[raceNum],
				new Ability(CommonData.CHARACTER_ABILITY_RACEBASICS[raceNum][0],
						CommonData.CHARACTER_ABILITY_RACEBASICS[raceNum][1],
						CommonData.CHARACTER_ABILITY_RACEBASICS[raceNum][2],
						CommonData.CHARACTER_ABILITY_RACEBASICS[raceNum][3],
						CommonData.CHARACTER_ABILITY_RACEBASICS[raceNum][4],
						CommonData.CHARACTER_ABILITY_RACEBASICS[raceNum][5]),
				new Attribute(
						CommonData.CHARACTER_ATTRIBUTE_BASICATK,
						CommonData.CHARACTER_ATTRIBUTE_BASICPHYSICALARMOR[raceNum],
						CommonData.CHARACTER_ATTRIBUTE_BASICMAGICRRESIST[raceNum],
						CommonData.CHARACTER_ATTRIBUTE_BASICMOVEDISTANCE[raceNum],
						CommonData.CHARACTER_ATTRIBUTE_BASICATTACKDISTANCE[raceNum]),
				new Skills());
		return character;
	}

	/**
	 * get One Hero Without Race Set
	 * 
	 * @return
	 */
	public GameCharacter getOneHeroWithoutRaceSet() {

		DiceTool diceRace = new DiceTool(
				CommonData.CHARACTER_INFORMATION_FIGHTER_TYPES.length);
		DiceTool diceName = new DiceTool(
				CommonData.CHARACTER_INFORMATION_NAMES.length);

		String chaName;
		chaName = CommonData.CHARACTER_INFORMATION_NAMES[diceName.getValue()-1];
		int raceNum = diceRace.getValue();
		GameCharacter character = new GameCharacter(
				chaName,
				CommonData.CHARACTER_INFORMATION_FIGHTER_TYPES[raceNum],
				CommonData.CHARACTER_STATUS_RACEBASICHP[raceNum],
				CommonData.CHARACTER_STATUS_RACEBASICMP[raceNum],
				new Ability(CommonData.CHARACTER_ABILITY_RACEBASICS[raceNum][0],
						CommonData.CHARACTER_ABILITY_RACEBASICS[raceNum][1],
						CommonData.CHARACTER_ABILITY_RACEBASICS[raceNum][2],
						CommonData.CHARACTER_ABILITY_RACEBASICS[raceNum][3],
						CommonData.CHARACTER_ABILITY_RACEBASICS[raceNum][4],
						CommonData.CHARACTER_ABILITY_RACEBASICS[raceNum][5]),
				new Attribute(
						CommonData.CHARACTER_ATTRIBUTE_BASICATK,
						CommonData.CHARACTER_ATTRIBUTE_BASICPHYSICALARMOR[raceNum],
						CommonData.CHARACTER_ATTRIBUTE_BASICMAGICRRESIST[raceNum],
						CommonData.CHARACTER_ATTRIBUTE_BASICMOVEDISTANCE[raceNum],
						CommonData.CHARACTER_ATTRIBUTE_BASICATTACKDISTANCE[raceNum]),
				new Skills());

		return character;
	}
}
