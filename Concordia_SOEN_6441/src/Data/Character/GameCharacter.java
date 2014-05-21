/**
 * @author Lin Zhu (6555659)
 * SOEN 6441 Team Project 
 * Winter 2014
 */
package Data.Character;

import Data.Builder.DiceTool;
import Data.GameData.CommonData;
import Data.Item.Interface_Item;
import GUI.battleView.BattleLog;

/**
 * the class GameCharacter is a Game Character of the game
 * 
 * @author Lin Zhu
 * 
 */
public class GameCharacter implements GameFighter {

	public Information ChaInfo; // Stores the name and a fighter type name for a
								// character
	public Ability ability; // Stores 6 ability values for a character
	public Attribute attribute;
	public Status status;
	public Skills skill;
	public ItemContainer bag;
	public Equipment equipment;

	/**
	 * Class GameCharacter Constructor (With no argument)
	 */
	public GameCharacter() {

		this.ChaInfo = new Information();
		this.ability = new Ability();
		this.attribute = new Attribute();
		this.skill = new Skills();
		this.status = new Status();
		this.bag = new ItemContainer();
		this.equipment = new Equipment();
	}

	/**
	 * Class GameCharacter Constructor (With argument)
	 */
	public GameCharacter(String noItems) {

		this.ChaInfo = new Information();
		this.ability = new Ability();
		this.attribute = new Attribute();
		this.skill = new Skills();
		this.status = new Status();
		this.bag = new ItemContainer(noItems);
		this.equipment = new Equipment(noItems);
	}

	/**
	 * Class Attribute Constructor (With a race type)
	 * 
	 * @param race
	 */
	public GameCharacter(int race) {

		this.ChaInfo = new Information(race);
		this.ability = new Ability();
		this.attribute = new Attribute();
		this.skill = new Skills(race);
		this.status = new Status();
		this.bag = new ItemContainer();
		this.equipment = new Equipment();
		this.initialCharacterStatus();
	}

	/**
	 * Class Attribute Constructor (With argument)
	 * 
	 * @param nm
	 * @param ra
	 * @param bHP
	 * @param bMP
	 * @param abl
	 * @param atr
	 * @param skl
	 */
	public GameCharacter(String nm, int ra, int bHP, int bMP, Ability abl,
			Attribute atr, Skills skl) {

		this.ChaInfo = new Information(nm, ra);
		this.ability = new Ability();
		this.ability.updateAbility(abl);
		this.attribute = new Attribute();
		this.attribute.updateAttribute(atr);
		this.status = new Status(bHP, bMP, ra);
		this.bag = new ItemContainer();
		this.equipment = new Equipment();
		this.skill = skl;
		this.initialCharacterStatus();

	}

	/**
	 * Class Attribute Constructor (With argument)
	 * 
	 * @param nm
	 * @param ra
	 * @param bHP
	 * @param bMP
	 * @param abl
	 * @param atr
	 * @param skl
	 */
	public GameCharacter(String nm, String ra, int bHP, int bMP, Ability abl,
			Attribute atr, Skills skl) {

		this.ChaInfo = new Information(nm, ra);
		this.ability = new Ability();
		this.ability.updateAbility(abl);
		this.attribute = new Attribute();
		this.attribute.updateAttribute(atr);
		this.status = new Status(bHP, bMP, ra);
		this.bag = new ItemContainer();
		this.equipment = new Equipment();
		this.skill = skl;
		this.initialCharacterStatus();

	}

	/**
	 * initial Character Status
	 */
	public void initialCharacterStatus() {
		this.refreshGameFighter();
	}

	public boolean updateLevelTo(int targetLevel, BattleLog bl) {
 
		int levels = targetLevel - this.status.getLevel();
		if (targetLevel <=20 && levels > 0) {
			bl.setLogInfor("Character Level up! " + this.status.getLevel()
					+ " -> " + targetLevel + " !");
			this.status.setLevel(targetLevel);
			DiceTool dice10 = new DiceTool(10);
			for (int i = 0; i < levels; i++) {
				bl.setLogInfor("NO."+(i+1)+" Updating..");
				bl.setLogInfor("Updating new HP...");
				bl.setLogInfor("->Current HP: "+this.status.getBasicHP());
				bl.setLogInfor("->ConstitutionModifier: "+this.getConstitutionModifier());
				bl.setLogInfor("-> Throw a D10...");
				this.status.setBasicHP(this.status.getBasicHP()
						+ dice10.getValue(bl) + this.getConstitutionModifier());
				bl.setLogInfor("New Character Level "+targetLevel);
				bl.setLogInfor("New HP: "+this.status.getBasicHP());
				
				bl.setLogInfor("Character Level up done!");
			}
			
			this.status.setCurrentHP(this.getFixedMaxHP());
			return true;
		} else if(levels<0){
			bl.setLogInfor("Need to adjust level down...");
			bl.setLogInfor("Reset Character to level 1...");
			bl.setLogInfor("Update to the target level: "+targetLevel);
			this.status.setLevel(targetLevel);
			DiceTool dice10 = new DiceTool(10);
			for (int i = 0; i < targetLevel-1; i++) {
				bl.setLogInfor("NO."+(i+1)+" Updating..");
				bl.setLogInfor("Updating new HP...");
				bl.setLogInfor("->Current HP: "+this.status.getBasicHP());
				bl.setLogInfor("->ConstitutionModifier: "+this.getConstitutionModifier());
				bl.setLogInfor("-> Throw a D10...");
				this.status.setBasicHP(this.status.getBasicHP()
						+ dice10.getValue(bl) + this.getConstitutionModifier());
				bl.setLogInfor("New Character Level "+targetLevel);
				bl.setLogInfor("New Basic HP: "+this.status.getBasicHP());
				this.status.setCurrentHP(this.getFixedMaxHP());
				bl.setLogInfor("Character Level down done!");
			}
			return false;
		}else{
			
		}
		bl.setLogInfor("No need to update!");
		return false;
	}

	public boolean levelUpByOne(BattleLog bl) {
 

		if (this.status.getLevel() < 20) {
			bl.setLogInfor("Character Level up! " + this.status.getLevel()
					+ " -> " + (this.status.getLevel() + 1) + " !");
			this.status.setLevel(this.status.getLevel() + 1);
			DiceTool dice10 = new DiceTool(10);
			bl.setLogInfor("Updating new Basic HP...");
			bl.setLogInfor("->Current Basic HP: "+this.status.getBasicHP());
			bl.setLogInfor("->ConstitutionModifier: "+this.getConstitutionModifier());
			bl.setLogInfor("-> Throw a D10...");
			this.status.setBasicHP(this.status.getBasicHP()
					+ dice10.getValue(bl) + this.getConstitutionModifier());
			bl.setLogInfor("New Basic HP: "+this.status.getBasicHP());
			bl.setLogInfor("New Character Level "+this.status.getLevel());
			this.status.setCurrentHP(this.getFixedMaxHP());
			bl.setLogInfor("Character Level up done!");
			return true;
		} else {
			return false;
		}

	}
	
	public boolean levelDownByOne(BattleLog bl) {
		 

		if (this.status.getLevel() > 1) {
			bl.setLogInfor("Character Level down! " + this.status.getLevel()
					+ " -> " + (this.status.getLevel() - 1) + " !");
			this.status.setLevel(this.status.getLevel() - 1);
			DiceTool dice10 = new DiceTool(10);
			bl.setLogInfor("Updating new Basic HP...");
			bl.setLogInfor("->Current Basic HP: "+this.status.getBasicHP());
			bl.setLogInfor("->ConstitutionModifier: "+this.getConstitutionModifier());
			bl.setLogInfor("-> Throw a D10...");
			this.status.setBasicHP(this.status.getBasicHP()
					- dice10.getValue(bl) - this.getConstitutionModifier());
			bl.setLogInfor("New Basic HP: "+this.status.getBasicHP());
			bl.setLogInfor("New Character Level "+this.status.getLevel());
			this.status.setCurrentHP(this.getFixedMaxHP());
			bl.setLogInfor("Character Level down done!");
			return true;
		} else {
			return false;
		}

	}

	public boolean levelUpByMulti(int levels, BattleLog bl) {
 
		if (this.status.getLevel() + levels <= 20) {
			bl.setLogInfor("Character Level up! " + this.status.getLevel()
					+ " -> " + (this.status.getLevel() + levels) + " !");
			this.status.setLevel(this.status.getLevel() + levels);
			DiceTool dice10 = new DiceTool(10);
			for (int i = 0; i < levels; i++) {
				bl.setLogInfor("NO."+(i+1)+" Updating..");
				bl.setLogInfor("Updating new Basic HP...");
				bl.setLogInfor("->Current Basic HP: "+this.status.getBasicHP());
				bl.setLogInfor("->ConstitutionModifier: "+this.getConstitutionModifier());
				bl.setLogInfor("-> Throw a D10...");
				this.status.setBasicHP(this.status.getBasicHP()
						+ dice10.getValue(bl) + this.getConstitutionModifier());
				bl.setLogInfor("New Character Level "+this.status.getLevel());
				bl.setLogInfor("New Basic HP: "+this.status.getBasicHP());
			}
			this.status.setCurrentHP(this.getFixedMaxHP());
			bl.setLogInfor("Character Level up done!");
			return true;
		} else {
			return false;
		}
	}
	
	public boolean levelUpByMulti(int levels) {
		 
		if (this.status.getLevel() + levels <= 20) {
			this.status.setLevel(this.status.getLevel() + levels);
			DiceTool dice10 = new DiceTool(10);
			for (int i = 0; i < levels; i++) {
				this.status.setBasicHP(this.status.getBasicHP()
						+ dice10.getValue() + this.getConstitutionModifier());
			}
			this.status.setCurrentHP(this.getFixedMaxHP());
			return true;
		} else {
			return false;
		}
	}

	public int[] getBaseAttackBonus() {
		return this.attribute.attackBonus[this.status.getLevel()-1];
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
		armor += this.getFixedPhysicalArmor();
		armor += this.getArmorModifier();
		armor += this.getShieldModifier();
		return armor;
	}

	public int getArmorModifier() {
		return this.equipment.getArmor().adjustPhysicalArmor();
	}

	public int getShieldModifier() {
		return this.equipment.getShield().adjustPhysicalArmor();
	}

	public int[] getAttackBonus(BattleLog bl) {
		int multipleAtk = this.getBaseAttackBonus().length;
		int[] attackBonus = new int[multipleAtk];
		switch (this.equipment.getWeapons().getType()) {
		case SWORD:
			bl.setLogInfor("   MELEE Type need to call Strength Modifier.");
			for (int i = 0; i < multipleAtk; i++) {
				attackBonus[i] += this.getBaseAttackBonus()[i];
				attackBonus[i] += this.getStrengthModifier();
			}
			break;
		case BOW:
			bl.setLogInfor("   RANGED Type need to call Dexterity Modifier.");
			for (int i = 0; i < multipleAtk; i++) {
				attackBonus[i] += this.getBaseAttackBonus()[i];
				attackBonus[i] += this.getDexterityModifier();
			}
			break;
		default:
			bl.setLogInfor("   No weapons, so no Modifier is called.");
			for (int i = 0; i < multipleAtk; i++) {
				attackBonus[i] += this.getBaseAttackBonus()[i];
			}
			break;
		}
		return attackBonus;
	}
	
	public int[] getAttackBonus() {
		int multipleAtk = this.getBaseAttackBonus().length;
		int[] attackBonus = new int[multipleAtk];
		switch (this.equipment.getWeapons().getType()) {
		case SWORD:
			
			for (int i = 0; i < multipleAtk; i++) {
				attackBonus[i] += this.getBaseAttackBonus()[i];
				attackBonus[i] += this.getStrengthModifier();
			}
			break;
		case BOW:
		
			for (int i = 0; i < multipleAtk; i++) {
				attackBonus[i] += this.getBaseAttackBonus()[i];
				attackBonus[i] += this.getDexterityModifier();
			}
			break;
		default:
		
			for (int i = 0; i < multipleAtk; i++) {
				attackBonus[i] += this.getBaseAttackBonus()[i];
			}
			break;
		}
		return attackBonus;
	}

	public int getAttackDamage(BattleLog bl) {
		bl.setLogInfor(">> Weapons Type:"
				+ this.equipment.getWeapons().getType());
		int damage = 0;
		DiceTool dice8 = new DiceTool(8);
		bl.setLogInfor("   Throw a d8 dice.");
		damage += this.getFixedATK();
		damage += dice8.getValue(bl);
		switch (this.equipment.getWeapons().getType()) {
		case SWORD:
			bl.setLogInfor("   MELEE Type need to call Strength Modifier.");
			bl.setLogInfor("   StrengthModifier Result:"
					+ this.getStrengthModifier());
			damage += this.getStrengthModifier();
			break;
		case BOW:
			bl.setLogInfor("   RANGED Type, no Modifier is called.");
			break;
		case NOWEAPONS:
			bl.setLogInfor("   No weapons, so no Modifier is called.");
			break;
		default:
			bl.setLogInfor("   No weapons, so no Modifier is called.");
			break;
		}
		bl.setLogInfor("   Damage Result:" + damage);
		return damage;

	}

	public void refreshGameFighter() {
		String race = this.ChaInfo.getRace();
		if (race.equalsIgnoreCase(CommonData.FIGHTER_TYPE_BULLY)) {
			this.attribute.setBasicAttack(this.ability.getStrength()
					+ CommonData.CHARACTER_ATTRIBUTE_BASICATK);
		} else if (race.equalsIgnoreCase(CommonData.FIGHTER_TYPE_NIMBLE)) {
			this.attribute.setBasicPhysicalArmor(this.ability.getDexterity()
					+ CommonData.CHARACTER_ATTRIBUTE_BASICPHYSICALARMOR[1]);
		} else if (race.equalsIgnoreCase(CommonData.FIGHTER_TYPE_TANK)) {
			this.status.setBasicHP((this.ability.getStrength()
					+ CommonData.CHARACTER_STATUS_RACEBASICHP[2]));
			this.status.setCurrentHP(this.getFixedMaxHP());
		} else {

		}
	}
	
	
	

	/**
	 * update Character Magic With ID
	 * 
	 * @param ID
	 * @return
	 */
	public boolean updateCharacterMagicWithID(int ID) {
		if (this.skill.getMagic(ID).getMagicLevel() >= 3) {
			return false;
		}
		if (this.status.getSkillPoint() >= this.skill.getMagic(ID)
				.getNeedPtsNextLevel()) {
			int needPts = this.skill.getMagic(ID).getNeedPtsNextLevel();
			this.skill.updateMagicWithID(ID);
			this.status.loseSkillPts(needPts);
			return true;
		} else {
			return false;
		}

	}

	/**
	 * buyItem method help a character to buy a certain item from the store
	 * 
	 * @param itm
	 * @return True if buy successfully, corresponding money will be removed
	 *         automatically,else return False
	 */
	public boolean buyItem(Interface_Item itm) {
		if (this.bag.addItem(itm)) {
			this.status.loseMoney(itm.getItemPrice());
			return true;
		} else {
			return false;
		}
	}

	/**
	 * pickUpItem method help a character to pick up a certain item into the
	 * backpack
	 * 
	 * @param itm
	 * @return True if pick it up successfully, corresponding item will be added
	 *         to the bag automatically,else return False if there is no enough
	 *         weight/number space left
	 */
	public boolean pickUpItem(Interface_Item itm) {
		if (this.bag.addItem(itm)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * get the member variable Ability of the character
	 * 
	 * @return
	 */
	public Ability getAbility() {
		return this.ability;
	}

	/**
	 * get the member variable Attribute of the character
	 * 
	 * @return
	 */
	public Attribute getAttribute() {
		return this.attribute;
	}

	/**
	 * get the member variable Skills of the character
	 * 
	 * @return
	 */
	public Skills getSkill() {
		return this.skill;
	}

	/**
	 * get the member variable Status of the character
	 * 
	 * @return
	 */
	public Status getStatus() {
		return this.status;
	}

	/**
	 * get the member variable BackPack of the character
	 * 
	 * @return
	 */
	public ItemContainer getBackPack() {
		return this.bag;
	}

	/**
	 * get the member variable Equipment of the character
	 * 
	 * @return
	 */
	public Equipment getEquipment() {
		return this.equipment;
	}

	/**
	 * get the member variable Information of the character
	 * 
	 * @return
	 */
	public Information getInfo() {
		return this.ChaInfo;
	}

	/**
	 * set the member variable Information of the character
	 * 
	 * @param chaInfo
	 */
	public void setInfo(Information chaInfo) {
		this.ChaInfo = chaInfo;
	}

	/**
	 * set the member variable Ability of the character
	 * 
	 * @param chaAbl
	 */
	public void setAbility(Ability chaAbl) {
		this.ability = chaAbl;
	}

	/**
	 * set the member variable Attribute of the character
	 * 
	 * @param chaAtt
	 */
	public void setAttribute(Attribute chaAtt) {
		this.attribute = chaAtt;
	}

	/**
	 * set the member variable Skills of the character
	 * 
	 * @param chaSki
	 */
	public void setSkill(Skills chaSki) {
		this.skill = chaSki;
	}

	/**
	 * set the member variable Status of the character
	 * 
	 * @param chaStat
	 */
	public void setStatus(Status chaStat) {
		this.status = chaStat;
	}

	/**
	 * set the member variable BackPack of the character
	 * 
	 * @param bg
	 */
	public void setBackPack(ItemContainer bg) {
		this.bag = bg;
	}

	/**
	 * set the member variable Equipment of the character
	 * 
	 * @param eq
	 */
	public void setEquipment(Equipment eq) {
		this.equipment = eq;
	}

	/**
	 * get Fixed value by calculating the all the status of a character
	 * 
	 * @return
	 */
	public int getFixedStrength() {
		int fixStrength = this.ability.getStrength();
		if (this.equipment.getEquipments().size() != 0) {
			for (int i = 0; i < 6; i++) {
				fixStrength += this.equipment.getEquipments().get(i)
						.adjustStrength();
			}
		}

		return fixStrength;
	}

	/**
	 * get Fixed value by calculating the all the status of a character
	 * 
	 * @return
	 */
	public int getFixedDexterity() {
		int fixDexterity = this.ability.getDexterity();
		for (int i = 0; i < 6; i++) {
			fixDexterity += this.equipment.getEquipments().get(i)
					.adjustDexterity();
		}
		return fixDexterity;
	}

	/**
	 * get Fixed value by calculating the all the status of a character
	 * 
	 * @return
	 */
	public int getFixedConstitution() {
		int fixConstitution = this.ability.getConstitution();
		for (int i = 0; i < 6; i++) {
			fixConstitution += this.equipment.getEquipments().get(i)
					.adjustConstitution();
		}
		return fixConstitution;
	}

	/**
	 * get Fixed value by calculating the all the status of a character
	 * 
	 * @return
	 */
	public int getFixedWisdom() {
		int fixWisdom = this.ability.getWisdom();
		for (int i = 0; i < 6; i++) {
			fixWisdom += this.equipment.getEquipments().get(i).adjustWisdom();
		}
		return fixWisdom;
	}

	/**
	 * get Fixed value by calculating the all the status of a character
	 * 
	 * @return
	 */
	public int getFixedIntelligence() {
		int fixIntelligence = this.ability.getIntelligence();
		for (int i = 0; i < 6; i++) {
			fixIntelligence += this.equipment.getEquipments().get(i)
					.adjustIntelligence();
		}
		return fixIntelligence;
	}

	/**
	 * get Fixed value by calculating the all the status of a character
	 * 
	 * @return
	 */
	public int getFixedCharisma() {
		int fixCharisma = this.ability.getCharisma();
		for (int i = 0; i < 6; i++) {
			fixCharisma += this.equipment.getEquipments().get(i)
					.adjustCharisma();
		}
		return fixCharisma;
	}

	/**
	 * get Fixed value by calculating the all the status of a character
	 * 
	 * @return
	 */
	public int getFixedATK() {
 
		int fixATK = this.attribute.getBasicAttack();
		for (int i = 0; i < 6; i++) {
			fixATK += this.equipment.getEquipments().get(i).adjustATK();
		}

		return fixATK;
	}

	/**
	 * get Fixed value by calculating the all the status of a character
	 * 
	 * @return
	 */
	public int getFixedPhysicalArmor() {
 
		int fixPhysicalArmor = this.attribute.getBasicPhysicalArmor();
		for (int i = 0; i < 6; i++) {
			fixPhysicalArmor += this.equipment.getEquipments().get(i)
					.adjustPhysicalArmor();
		}
		return fixPhysicalArmor;
	}

	/**
	 * get Fixed value by calculating the all the status of a character
	 * 
	 * @return
	 */
	public int getFixedMagicResist() {
		int fixMagicResist = this.attribute.getBasicAttack();
		for (int i = 0; i < 6; i++) {
			fixMagicResist += this.equipment.getEquipments().get(i)
					.adjustMagicResist();
		}
		return fixMagicResist;
	}

	/**
	 * get Fixed value by calculating the all the status of a character
	 * 
	 * @return
	 */
	public int getFixedMoveDistance() {
		int fixMoveDistance = this.attribute.getBasicMoveDistance();
		for (int i = 0; i < 6; i++) {
			fixMoveDistance += this.equipment.getEquipments().get(i)
					.adjustMoveDistance();
		}
		return fixMoveDistance;
	}

	/**
	 * get Fixed value by calculating the all the status of a character
	 * 
	 * @return
	 */
	public int getFixedAttackDistance() {
		int fixAttackDistance = this.attribute.getBasicAttackDistance();
		for (int i = 0; i < 6; i++) {
			fixAttackDistance += this.equipment.getEquipments().get(i)
					.adjustAttackDistance();
		}
		return fixAttackDistance;
	}

	/**
	 * get Fixed value by calculating the all the status of a character
	 * 
	 * @return
	 */
	public int getFixedMaxHP() {
 
		int fixMaxHP = this.status.getBasicHP();
		for (int i = 0; i < 6; i++) {
			fixMaxHP += this.equipment.getEquipments().get(i).adjustMaxHP();
		}
		return fixMaxHP;
	}

	/**
	 * get Fixed value by calculating the all the status of a character
	 * 
	 * @return
	 */
	public int getFixedMaxMP() {
 
		int fixMaxMP = this.status.getBasicMP();
		for (int i = 0; i < 6; i++) {
			fixMaxMP += this.equipment.getEquipments().get(i).adjustMaxMP();
		}
		return fixMaxMP;
	}

	public boolean checkDeath() {
		if (this.status.getCurrentHP() <= 0) {
			return true;
		}
		return false;
	}

	public void loseHP(int attackDamage) {
		System.out.println("Before HP:"+this.status.getCurrentHP());
		if (this.status.getCurrentHP() <= attackDamage) {
			this.status.setCurrentHP(0);
		} else {
			this.status.setCurrentHP(this.status.getCurrentHP() - attackDamage);
		}
		System.out.println("After HP:"+this.status.getCurrentHP());
	}

	public String getAttackBonusString() {
		int[] temp = this.getAttackBonus();
		String result="";
		for(int i =0;i<temp.length;i++){
			result=result+temp[i];
			if(i!=temp.length-1){
				result=result+" / ";
			}	
		}
		return result;
	}
	
	public String getBaseAttackBonusString() {
		int[] temp = this.getBaseAttackBonus();
		String result="";
		for(int i =0;i<temp.length;i++){
			result=result+temp[i];
			if(i!=temp.length-1){
				result=result+" / ";
			}	
		}
		return result;
	}
	
	public String getHPLevelString() {
		
		String result="";
		result=result+this.getFixedMaxHP();
		result=result+"  Level: ";
		result=result+this.status.getLevel();
		return result;
	}

}
