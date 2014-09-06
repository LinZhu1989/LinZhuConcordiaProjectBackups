/**
 * @author Lin Zhu (6555659)
 * SOEN 6441 Team Project 
 * Winter 2014
 */
package Data.Character;

import Data.Builder.DiceTool;
import Data.GameData.CommonData;
import GUI.battleView.BattleLog;
import utility.MonsterType;

/**
 * the class Monster is a Game Monster of the game
 * 
 * @author Lin Zhu
 * 
 */
public class GameMonster implements GameFighter {

	public Ability ability; // Stores 6 ability values for a Monster
	public Attribute attribute;
	public Status status;
	private String monsterName;

	/**
	 * Class Monster Constructor (With no argument)
	 */
	public GameMonster() {
		this.ability = new Ability();
		this.attribute = new Attribute();
		this.status = new Status();
		this.monsterName = "unknown";
	}

	public GameMonster(int lvl) {
		this.ability = new Ability(lvl);
		this.attribute = new Attribute(lvl);
		this.status = new Status(lvl);
		DiceTool dt = new DiceTool(CommonData.MONSTER_NAMES.length);
		this.monsterName = CommonData.MONSTER_NAMES[dt.getValue() - 1];
	}

	public boolean updateLevelTo(int targetLevel, BattleLog bl) {
		
		targetLevel=targetLevel/2+1;
		int levels = targetLevel - this.status.getLevel();

		
		if (targetLevel <= 10 && levels > 0) {
			bl.setLogInfor("Monster Level up! " + this.status.getLevel()
					+ " -> " + targetLevel + " !");
			this.status.setLevel(targetLevel);
			DiceTool dice10 = new DiceTool(10);
			for (int i = 0; i < levels; i++) {
				bl.setLogInfor("NO." + (i + 1) + " Updating..");
				bl.setLogInfor("Updating new HP...");
				bl.setLogInfor("->Current HP: " + this.status.getBasicHP());
				bl.setLogInfor("->ConstitutionModifier: "
						+ this.getConstitutionModifier());
				bl.setLogInfor("-> Throw a D10...");
				this.status.setBasicHP(this.status.getBasicHP()
						+ dice10.getValue(bl) + this.getConstitutionModifier());
				bl.setLogInfor("New Monster Level " + targetLevel);
				bl.setLogInfor("New HP: " + this.status.getBasicHP());
				bl.setLogInfor("Monster Level up done!");
			}
			this.status.setCurrentHP(this.status.getBasicHP());
			return true;
		} else if (levels < 0) {
			bl.setLogInfor("Need to adjust level down...");
			bl.setLogInfor("Reset Monster to level 1...");
			bl.setLogInfor("Update to the target level: " + targetLevel);
			this.status.setLevel(targetLevel);
			DiceTool dice10 = new DiceTool(10);
			for (int i = 0; i < targetLevel - 1; i++) {
				bl.setLogInfor("NO." + (i + 1) + " Updating..");
				bl.setLogInfor("Updating new HP...");
				bl.setLogInfor("->Current HP: " + this.status.getBasicHP());
				bl.setLogInfor("->ConstitutionModifier: "
						+ this.getConstitutionModifier());
				bl.setLogInfor("-> Throw a D10...");
				this.status.setBasicHP(this.status.getBasicHP()
						+ dice10.getValue(bl) + this.getConstitutionModifier());
				bl.setLogInfor("New Monster Level " + targetLevel);
				bl.setLogInfor("New Basic HP: " + this.status.getBasicHP());

			}
			this.status.setCurrentHP(this.status.getBasicHP());
			bl.setLogInfor("Monster Level down done!");
			return false;
		} else {

		}
		bl.setLogInfor("No need to update!");
		return false;
	}

	public boolean levelUpByOne(BattleLog bl) {

		if (this.status.getLevel() < 10) {
			bl.setLogInfor("Monster Level up! " + this.status.getLevel()
					+ " -> " + (this.status.getLevel() + 1) + " !");
			this.status.setLevel(this.status.getLevel() + 1);
			DiceTool dice10 = new DiceTool(10);
			bl.setLogInfor("Updating new Basic HP...");
			bl.setLogInfor("->Current Basic HP: " + this.status.getBasicHP());
			bl.setLogInfor("->ConstitutionModifier: "
					+ this.getConstitutionModifier());
			bl.setLogInfor("-> Throw a D10...");
			this.status.setBasicHP(this.status.getBasicHP()
					+ dice10.getValue(bl) + this.getConstitutionModifier());
			bl.setLogInfor("New Basic HP: " + this.status.getBasicHP());
			bl.setLogInfor("New Monster Level " + this.status.getLevel());
			this.status.setCurrentHP(this.status.getBasicHP());
			bl.setLogInfor("Monster Level up done!");

			return true;
		} else {
			return false;
		}

	}

	public boolean levelUpByMulti(int levels, BattleLog bl) {

		if (this.status.getLevel() + levels <= 10) {
			bl.setLogInfor("Monster Level up! " + this.status.getLevel()
					+ " -> " + (this.status.getLevel() + levels) + " !");
			this.status.setLevel(this.status.getLevel() + levels);
			DiceTool dice10 = new DiceTool(10);
			for (int i = 0; i < levels; i++) {
				bl.setLogInfor("NO. " + (i + 1) + " Updating..");
				bl.setLogInfor("Updating new Basic HP...");
				bl.setLogInfor("->Current Basic HP: "
						+ this.status.getBasicHP());
				bl.setLogInfor("->ConstitutionModifier: "
						+ this.getConstitutionModifier());
				bl.setLogInfor("-> Throw a D10...");
				this.status.setBasicHP(this.status.getBasicHP()
						+ dice10.getValue(bl) + this.getConstitutionModifier());
				bl.setLogInfor("New Monster Level " + this.status.getLevel());
				bl.setLogInfor("New Basic HP: " + this.status.getBasicHP());
			}
			this.status.setCurrentHP(this.status.getBasicHP());
			bl.setLogInfor("Monster Level up done!");
			return true;
		} else {
			return false;
		}
	}

	public int[] getBaseAttackBonus() {
		return this.attribute.attackBonus[this.status.getLevel()];
	}

	/**
	 * abilityModifiers helps to get the modified values
	 * 
	 * @param abi
	 * @return
	 */
	public int modifierAbility(String choice) {
		int abi = 0;
		if (choice.equalsIgnoreCase("strength")) {
			abi = this.ability.getStrength();
		} else if (choice.equalsIgnoreCase("dexterity")) {
			abi = this.ability.getDexterity();
		} else if (choice.equalsIgnoreCase("constitution")) {
			abi = this.ability.getConstitution();
		} else if (choice.equalsIgnoreCase("wisdom")) {
			abi = this.ability.getWisdom();
		} else if (choice.equalsIgnoreCase("intelligence")) {
			abi = this.ability.getIntelligence();
		} else if (choice.equalsIgnoreCase("charisma")) {
			abi = this.ability.getCharisma();
		}

		if (abi >= 10) {
			return (abi - 10) / 2;
		} else if (abi >= 1) {
			return (abi - 10) / 2 - 1;
		} else {
			System.out
					.println("Error Input Ability Score! The ability score should be positive!");
			return -Integer.MAX_VALUE;
		}

	}

	public int getStrengthModifier() {
		return modifierAbility("strength");
	}

	public int getDexterityModifier() {
		return modifierAbility("dexterity");
	}

	public int getConstitutionModifier() {
		return modifierAbility("constitution");
	}

	public int getWisdomModifier() {
		return modifierAbility("wisdom");
	}

	public int getIntelligenceModifier() {
		return modifierAbility("intelligence");
	}

	public int getCharismaModifier() {
		return modifierAbility("charisma");
	}

	public int getArmorClass() {
		int armor = 0;
		armor += this.attribute.getBasicPhysicalArmor();
		armor += this.getArmorModifier();
		armor += this.getShieldModifier();
		return armor;
	}

	public int getArmorModifier() {
		return 0;
	}

	public int getShieldModifier() {
		return 0;
	}

	public int[] getAttackBonus(BattleLog bl) {
		bl.setLogInfor("   No weapons, so no Modifier is called.");
		int multipleAtk = this.getBaseAttackBonus().length;
		int[] attackBonus = new int[multipleAtk];
		for (int i = 0; i < multipleAtk; i++) {
			attackBonus[i] = this.attribute.getBasicAttack();
		}
		for (int i = 0; i < multipleAtk; i++) {
			attackBonus[i] += this.getBaseAttackBonus()[i];
		}
		return attackBonus;
	}

	public int[] getAttackBonus() {

		int multipleAtk = this.getBaseAttackBonus().length;
		int[] attackBonus = new int[multipleAtk];
		for (int i = 0; i < multipleAtk; i++) {
			attackBonus[i] = this.attribute.getBasicAttack();
		}
		for (int i = 0; i < multipleAtk; i++) {
			attackBonus[i] += this.getBaseAttackBonus()[i];
		}
		return attackBonus;
	}

	public int getAttackDamage(BattleLog bl) {
		int damage = 0;
		DiceTool dice8 = new DiceTool(8);
		bl.setLogInfor("   Throw a d8 dice.");
		damage += dice8.getValue(bl);
		bl.setLogInfor("   No weapons, so no Modifier is called.");
		bl.setLogInfor("   Damage Result:" + damage);
		return damage;

	}

	public void refreshGameFighter() {
		int lvl = this.status.getLevel();
		this.ability = new Ability(lvl);
		this.attribute = new Attribute(lvl);
		this.status = new Status(lvl);
	}

	/**
	 * get the member variable Ability of the monster
	 * 
	 * @return
	 */
	public Ability getAbility() {
		return this.ability;
	}

	/**
	 * get the member variable Attribute of the monster
	 * 
	 * @return
	 */
	public Attribute getAttribute() {
		return this.attribute;
	}

	/**
	 * get the member variable Status of the monster
	 * 
	 * @return
	 */
	public Status getStatus() {
		return this.status;
	}

	/**
	 * set the member variable Ability of the monster
	 * 
	 * @param chaAbl
	 */
	public void setAbility(Ability chaAbl) {
		this.ability = chaAbl;
	}

	/**
	 * set the member variable Attribute of the monster
	 * 
	 * @param chaAtt
	 */
	public void setAttribute(Attribute chaAtt) {
		this.attribute = chaAtt;
	}

	/**
	 * set the member variable Status of the monster
	 * 
	 * @param chaStat
	 */
	public void setStatus(Status chaStat) {
		this.status = chaStat;
	}

	public boolean checkDeath() {
		if (this.status.getCurrentHP() <= 0) {
			return true;
		}
		return false;
	}

	public String getName() {
		return this.monsterName;
	}

	public String getMonsterName() {
		return this.monsterName;
	}

	public void setMonsterName(String n) {
		this.monsterName = n;
	}

	public void loseHP(int attackDamage) {
		System.out.println("Before HP:" + this.status.getCurrentHP());
		if (this.status.getCurrentHP() <= attackDamage) {
			this.status.setCurrentHP(0);
		} else {
			this.status.setCurrentHP(this.status.getCurrentHP() - attackDamage);
		}
		System.out.println("After HP:" + this.status.getCurrentHP());
	}

	public int getFixedStrength() {
		int fixStrength = this.ability.getStrength();

		return fixStrength;
	}

	/**
	 * get Fixed value by calculating the all the status of a character
	 * 
	 * @return
	 */
	public int getFixedDexterity() {
		int fixDexterity = this.ability.getDexterity();

		return fixDexterity;
	}

	/**
	 * get Fixed value by calculating the all the status of a character
	 * 
	 * @return
	 */
	public int getFixedConstitution() {
		int fixConstitution = this.ability.getConstitution();

		return fixConstitution;
	}

	/**
	 * get Fixed value by calculating the all the status of a character
	 * 
	 * @return
	 */
	public int getFixedWisdom() {
		int fixWisdom = this.ability.getWisdom();

		return fixWisdom;
	}

	/**
	 * get Fixed value by calculating the all the status of a character
	 * 
	 * @return
	 */
	public int getFixedIntelligence() {
		int fixIntelligence = this.ability.getIntelligence();

		return fixIntelligence;
	}

	/**
	 * get Fixed value by calculating the all the status of a character
	 * 
	 * @return
	 */
	public int getFixedCharisma() {
		int fixCharisma = this.ability.getCharisma();

		return fixCharisma;
	}

	public String getAttackBonusString() {
		int[] temp = this.getAttackBonus();
		String result = "";
		for (int i = 0; i < temp.length; i++) {
			result = result + temp[i];
			if (i != temp.length - 1) {
				result = result + " / ";
			}
		}
		return result;
	}

	public String getBaseAttackBonusString() {
		int[] temp = this.getBaseAttackBonus();
		String result = "";
		for (int i = 0; i < temp.length; i++) {
			result = result + temp[i];
			if (i != temp.length - 1) {
				result = result + " / ";
			}
		}
		return result;
	}

	public String getHPLevelString() {

		String result = "";
		result = result + this.getFixedMaxHP();
		result = result + "  Level: ";
		result = result + this.status.getLevel();
		return result;
	}

	/**
	 * get Fixed value by calculating the all the status of a character
	 * 
	 * @return
	 */
	public int getFixedMaxHP() {

		int fixMaxHP = this.status.getBasicHP();

		return fixMaxHP;
	}

	/**
	 * get Fixed value by calculating the all the status of a character
	 * 
	 * @return
	 */
	public int getFixedATK() {

		int fixATK = this.attribute.getBasicAttack();
		return fixATK;
	}

	/**
	 * get Fixed value by calculating the all the status of a character
	 * 
	 * @return
	 */
	public int getFixedPhysicalArmor() {

		int fixPhysicalArmor = this.attribute.getBasicPhysicalArmor();
		return fixPhysicalArmor;
	}

	/**
	 * get Fixed value by calculating the all the status of a character
	 * 
	 * @return
	 */
	public int getFixedMagicResist() {
		int fixMagicResist = this.attribute.getBasicAttack();

		return fixMagicResist;
	}

	/**
	 * get Fixed value by calculating the all the status of a character
	 * 
	 * @return
	 */
	public int getFixedMoveDistance() {
		int fixMoveDistance = this.attribute.getBasicMoveDistance();
		return fixMoveDistance;
	}

	/**
	 * get Fixed value by calculating the all the status of a character
	 * 
	 * @return
	 */
	public int getFixedAttackDistance() {
		int fixAttackDistance = this.attribute.getBasicAttackDistance();
		return fixAttackDistance;
	}

}
