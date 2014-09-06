/**
 * @author Lin Zhu (6555659)
 * SOEN 6441 Team Project 
 * Winter 2014
 */
package Data.Character;

import Data.Builder.DiceTool;
import Data.GameData.CommonData;
import GUI.battleView.BattleLog;

/**
 * the class Ability handles 6 ability values for a Game Character
 * 
 * @author Lin Zhu
 * 
 */
public class Ability {
	private int strength; // Muscle power! Affects your chance to strike a
							// foe with melee attacks and damage.
	private int dexterity; // Coordination and agility! Affects your primary
							// defenses (Armor Class, or AC), ability to
							// dodge attacks (Reflex saves), and the
							// accuracy of your ranged attacks.
	private int constitution; // Health (hit points)! Constitution lets you
								// suffer more damage before dying, and
								// improves your ability to endure physical
								// strain (Fortitude save). Keeping this
								// Ability Score at 14 is encouraged, even
								// if you're making a character who isn't a
								// melee style. Brave adventurers sometimes
								// use 12.
	private int wisdom; // Learning ability and reasoning! Affects skill
						// points gained at each level and the amount of
						// bonus spell points for some classes.
	private int intelligence; // Willpower and intuition! Affects your
								// willpower (Will save) and the amount of
								// bonus spell points for some classes.
	private int charisma; // Personality and magnetism! Determines how well
							// you influence non-player characters (NPCs)
							// and affects the amount of bonus spell points
							// for some classes.

	/**
	 * Class Ability Constructor (With no argument)
	 */
	public Ability() {
		this.strength = 0;
		this.dexterity = 0;
		this.constitution = 0;
		this.wisdom = 0;
		this.intelligence = 0;
		this.charisma = 0;
	}

	/**
	 * Class Ability Constructor (With argument)
	 * 
	 * @param s
	 * @param d
	 * @param co
	 * @param w
	 * @param i
	 * @param ch
	 */
	public Ability(int s, int d, int co, int w, int i, int ch) {
		if (s < 0 || d < 0 || co < 0 || w < 0 || i < 0 || ch < 0) {
			throw new RuntimeException(
					"Error input value! Negative is not acceptable!");
		}

		this.strength = s;
		this.dexterity = d;
		this.constitution = co;
		this.wisdom = w;
		this.intelligence = i;
		this.charisma = ch;
	}

	/**
	 * 
	 * 
	 * @param lvl
	 */
	
	public Ability(int lvl) {
		DiceTool dice6 = new DiceTool(6);
		this.strength = dice6.throwDiceChooseMaxReturnResultOnly(4, 3);
		this.dexterity = dice6.throwDiceChooseMaxReturnResultOnly(4, 3);
		this.constitution = dice6.throwDiceChooseMaxReturnResultOnly(4, 3);
		this.wisdom = dice6.throwDiceChooseMaxReturnResultOnly(4, 3);
		this.intelligence = dice6.throwDiceChooseMaxReturnResultOnly(4, 3);
		this.charisma = dice6.throwDiceChooseMaxReturnResultOnly(4, 3);
	}

	public Ability(int lvl, BattleLog bl) {
		DiceTool dice6 = new DiceTool(6);
		bl.setLogInfor("Ability scores generation:");
		bl.setLogInfor(">>>> Strength <<<<");
		this.strength = dice6.throwDiceChooseMaxOutputLog(4, 3, bl);
		bl.setLogInfor("-> Generated Strength:"+this.strength);
		bl.setLogInfor(">>>> Dexterity <<<<");
		this.dexterity = dice6.throwDiceChooseMaxOutputLog(4, 3, bl);
		bl.setLogInfor("-> Generated Dexterity:"+this.dexterity);
		bl.setLogInfor(">>>> Constitution <<<<");
		this.constitution = dice6.throwDiceChooseMaxOutputLog(4, 3, bl);
		bl.setLogInfor("-> Generated Constitution:"+this.constitution);
		bl.setLogInfor(">>>> Wisdom <<<<");
		this.wisdom = dice6.throwDiceChooseMaxOutputLog(4, 3, bl);
		bl.setLogInfor("-> Generated Wisdom:"+this.wisdom);
		bl.setLogInfor(">>>> Intelligence <<<<");
		this.intelligence = dice6.throwDiceChooseMaxOutputLog(4, 3, bl);
		bl.setLogInfor("-> Generated Intelligence:"+this.intelligence);
		bl.setLogInfor(">>>> Charisma <<<<");
		this.charisma = dice6.throwDiceChooseMaxOutputLog(4, 3, bl);
		bl.setLogInfor("-> Generated Charisma:"+this.charisma);
	}

	

	/**
	 * Method for adding 6 new ability values to the current Ability object
	 * 
	 * @param s
	 * @param d
	 * @param co
	 * @param w
	 * @param i
	 * @param ch
	 */
	public boolean addAbility(int s, int d, int co, int w, int i, int ch) {
		if (this.getStrength() + s < 0 || this.getDexterity() + d < 0
				|| this.getConstitution() + co < 0 || this.getWisdom() + w < 0
				|| this.getIntelligence() + i < 0
				|| this.getCharisma() + ch < 0) {
			return false;
		}
		this.setStrength(this.getStrength() + s);
		this.setDexterity(this.getDexterity() + d);
		this.setConstitution(this.getConstitution() + co);
		this.setWisdom(this.getWisdom() + w);
		this.setIntelligence(this.getIntelligence() + i);
		this.setCharisma(this.getCharisma() + ch);
		return true;
	}

	/**
	 * Method for updating all 6 ability values using a new Ability object
	 * 
	 * @param abl
	 */
	public void updateAbility(Ability abl) {
		this.strength = abl.getStrength();
		this.dexterity = abl.getDexterity();
		this.constitution = abl.getConstitution();
		this.wisdom = abl.getWisdom();
		this.intelligence = abl.getIntelligence();
		this.charisma = abl.getCharisma();
	}

	/**
	 * Method for getting the fixed MAXHP based on the ability values
	 * 
	 * @return the adjusted max HP
	 */
	public int adjustMAXHP() {
		return this.strength * CommonData.CHARACTER_ABILITY_EXTRASTRENGTHHP;
	}

	/**
	 * Method for getting the fixed MAXMP based on the ability values
	 * 
	 * @return the adjusted max MP
	 */
	public int adjustMAXMP() {
		return this.intelligence
				* CommonData.CHARACTER_ABILITY_EXTRAINTELLIGENCEMP;
	}

	/**
	 * Method for getting the fixed PhysicalArmor based on the ability values
	 * 
	 * @return the adjusted Physical Armor
	 */
	public int adjustPhysicalArmor() {
		return this.dexterity
				* CommonData.CHARACTER_ABILITY_EXTRADEXTERITYPHYSICALARMOR;
	}

	/**
	 * Method for getting the fixed MagicResist based on the ability values
	 * 
	 * @return the adjusted Magic Resist
	 */
	public int adjustMagicResist() {
		return 0;
	}

	/**
	 * Method for getting the fixed Attack based on the ability values
	 * 
	 * @return the adjusted ATK
	 */
	public int adjustATK() {
		int bonusATK = 0;

		return bonusATK;
	}

	/**
	 * Method for getting the value of member variable strength
	 * 
	 * @return the adjusted strength
	 */
	public int getStrength() {
		return this.strength;
	}

	/**
	 * Method for setting the value of member variable strength
	 * 
	 * @param s
	 */
	public boolean setStrength(int s) {
		if (s < 0) {
			return false;
		}
		this.strength = s;
		return true;
	}

	/**
	 * Method for getting the value of member variable dexterity
	 * 
	 * @return the adjusted dexterity
	 */
	public int getDexterity() {
		return this.dexterity;
	}

	/**
	 * Method for setting the value of member variable dexterity
	 * 
	 * @param d
	 */
	public boolean setDexterity(int d) {
		if (d < 0) {
			return false;
		}
		this.dexterity = d;
		return true;

	}

	/**
	 * Method for getting the value of member variable constitution
	 * 
	 * @return
	 */
	public int getConstitution() {
		return this.constitution;
	}

	/**
	 * Method for setting the value of member variable constitution
	 * 
	 * @param co
	 */
	public boolean setConstitution(int co) {
		if (co < 0) {
			return false;
		}
		this.constitution = co;
		return true;

	}

	/**
	 * Method for getting the value of member variable wisdom
	 * 
	 * @return
	 */
	public int getWisdom() {
		return this.wisdom;
	}

	/**
	 * Method for setting the value of member variable wisdom
	 * 
	 * @param w
	 */
	public boolean setWisdom(int w) {
		if (w < 0) {
			return false;
		}
		this.wisdom = w;
		return true;

	}

	/**
	 * Method for getting the value of member variable intelligence
	 * 
	 * @return
	 */
	public int getIntelligence() {
		return this.intelligence;
	}

	/**
	 * Method for setting the value of member variable intelligence
	 * 
	 * @param i
	 */
	public boolean setIntelligence(int i) {
		if (i < 0) {
			return false;
		}
		this.intelligence = i;
		return true;

	}

	/**
	 * Method for getting the value of member variable charisma
	 * 
	 * @return
	 */
	public int getCharisma() {
		return this.charisma;
	}

	/**
	 * Method for setting the value of member variable charisma
	 * 
	 * @param ch
	 */
	public boolean setCharisma(int ch) {
		if (ch < 0) {
			return false;
		}
		this.charisma = ch;
		return true;

	}

}
