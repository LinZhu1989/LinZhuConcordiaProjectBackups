package GUI.characterView;

/**
 * @author DanZhang
 * @author XinShao
 * SOEN 6441 Team Project 
 * Winter 2014
 */
import java.awt.Color;
import java.awt.Image;

import utility.IDrawableUI;
import Data.Builder.BullyBuilder;
import Data.Builder.CharacterDirector;
import Data.Builder.NimbleBuilder;
import Data.Builder.TankBuilder;
import Data.Character.Ability;
import Data.Character.Attribute;
import Data.Character.GameCharacter;
import Data.Character.Skills;
import Data.GameData.CommonData;
import GUI.battleView.BattleLog;

/**
 * data for the character view
 * 
 */
public class CharacterGUI {
	private GameCharacter role;
	private BattleLog log;
	protected Image img;
	protected String strengthString;
	protected String dexterityString;
	protected String constitutionString;
	protected String intelligenceString;
	protected String wisdomString;
	protected String charismaString;
	protected String HPString;
	protected String MPString;
	protected String basicAtkString;
	protected String basicPhysicalArmorString;
	protected String basicMagicResistString;
	protected String basicMoveDistanceString;
	protected String basicAttackDistanceString;
	protected String attrPointString;
	protected String skillPointString;
	protected String heroName;
	protected static int bullyNum = 1;
	protected static int nimbleNum = 1;
	protected static int tankNum = 1;
	private CharacterResource R;
	private int type;

	protected Image skill1_1;
	protected Image skill1_2;
	protected Image skill1_3;
	protected Image skill2_1;
	protected Image skill2_2;
	protected Image skill2_3;
	protected Image skill3_1;
	protected Image skill3_2;
	protected Image skill3_3;

	protected String skill1_1LvString;
	protected String skill1_2LvString;
	protected String skill1_3LvString;
	protected String skill2_1LvString;
	protected String skill2_2LvString;
	protected String skill2_3LvString;
	protected String skill3_1LvString;
	protected String skill3_2LvString;
	protected String skill3_3LvString;

	/**
	 * constructor for data
	 * 
	 * @param type
	 * @param R
	 */
	public CharacterGUI(int type, CharacterResource R) {
		this.R = R;
		this.type = type;
		this.setRole(new GameCharacter(type));
		initialRole();
		init();
		initSkill();
		transferString();
//		R.charInfor.initCharacterInfoLabels(R.descriptionX,R.strengthY,300,600, role);
//		R.charInfor.setTextColor(Color.red);
	}
	
	/**
	 * constructor for data
	 * 
	 * @param type
	 * @param R
	 * @param lg
	 */
	public CharacterGUI(int type, CharacterResource R, BattleLog lg) {
		this.R = R;
		this.type = type;
		this.setRole(new GameCharacter(type));
		initialRole();
		init();
		initSkill();
		transferString();
		this.log = lg;
		
	}

	
	/**
	 * constructor for data
	 * 
	 * @param gc
	 * @param R
	 */
	public CharacterGUI(GameCharacter gc, CharacterResource R, BattleLog lg) {
		this.setRole(gc);
		this.type = gc.getInfo().getType();
		this.R = R;
		// initialRole();
		init();
		initSkill();
		transferString();
		this.log = lg;
	}
	
	/**
	 * constructor for data
	 * 
	 * @param gc
	 * @param R
	 */
	public CharacterGUI(GameCharacter gc, CharacterResource R, BattleLog lg,IDrawableUI ui) {
		this.setRole(gc);
		this.type = gc.getInfo().getType();
		this.R = R;
		this.initForBattle();
		initSkill();
		transferString();
		this.log = lg;
	}

	/**
	 * initial value for the role
	 */
	public void initialRole() {
		String roleName = "Unknown";
		int bHP = CommonData.CHARACTER_STATUS_RACEBASICHP[this.type - 1];
		int bMP = CommonData.CHARACTER_STATUS_RACEBASICMP[this.type - 1];
		Ability abl = new Ability(
				CommonData.CHARACTER_ABILITY_RACEBASICS[this.type - 1][0],
				CommonData.CHARACTER_ABILITY_RACEBASICS[this.type - 1][1],
				CommonData.CHARACTER_ABILITY_RACEBASICS[this.type - 1][2],
				CommonData.CHARACTER_ABILITY_RACEBASICS[this.type - 1][3],
				CommonData.CHARACTER_ABILITY_RACEBASICS[this.type - 1][4],
				CommonData.CHARACTER_ABILITY_RACEBASICS[this.type - 1][5]);
		Attribute atr = new Attribute(
				CommonData.CHARACTER_ATTRIBUTE_BASICATK,
				CommonData.CHARACTER_ATTRIBUTE_BASICPHYSICALARMOR[this.type - 1],
				CommonData.CHARACTER_ATTRIBUTE_BASICMAGICRRESIST[this.type - 1],
				CommonData.CHARACTER_ATTRIBUTE_BASICMOVEDISTANCE[this.type - 1],
				CommonData.CHARACTER_ATTRIBUTE_BASICATTACKDISTANCE[this.type - 1]);
		Skills skl = new Skills(this.type);
		this.setRole(new GameCharacter(roleName, this.type, bHP, bMP, abl, atr,
				skl));
	}

	/**
	 * updating transfer latest skill value to string
	 */
	public void skillUpdate() {
		transferString();
	}

	/**
	 * judge the precondition of skillLevelUp
	 * 
	 * @param skillType1
	 * @param skillType2
	 * @return
	 */
	boolean judgeSkillLvUp(int skillType1, int skillType2) {

		switch (type) {
		case 1:
			return judgeSkillLvUpType(skillType1, skillType2);

		case 2:
			return judgeSkillLvUpType(skillType1, skillType2);

		case 3:
			return judgeSkillLvUpType(skillType1, skillType2);

		}

		return false;
	}

	private boolean judgeSkillLvUpType(int skillType1, int skillType2) {

		switch (skillType1) {

		case 1:
			return judgeSkillType1(skillType2);
		case 2:
			return judgeSkillType2(skillType2);
		case 3:
			return judgeSkillType3(skillType2);

		}

		return false;
	}

	private boolean judgeSkillType1(int skillType1) {

		return this.getRole().updateCharacterMagicWithID(skillType1);

	}

	private boolean judgeSkillType2(int skillType2) {

		return this.getRole().updateCharacterMagicWithID(skillType2);

	}

	private boolean judgeSkillType3(int skillType3) {

		return this.getRole().updateCharacterMagicWithID(skillType3);
	}

	private void initSkill() {

		switch (type) {
		case 1:

			skill1_1 = R.imgSkillDwarf1_1;
			skill1_2 = R.imgSkillDwarf1_2;
			skill1_3 = R.imgSkillDwarf1_3;
			skill2_1 = R.imgSkillDwarf2_1;
			skill2_2 = R.imgSkillDwarf2_2;
			skill2_3 = R.imgSkillDwarf2_3;
			skill3_1 = R.imgSkillDwarf3_1;
			skill3_2 = R.imgSkillDwarf3_2;
			skill3_3 = R.imgSkillDwarf3_3;
			break;
		case 2:
			skill1_1 = R.imgSkillElf1_1;
			skill1_2 = R.imgSkillElf1_2;
			skill1_3 = R.imgSkillElf1_3;
			skill2_1 = R.imgSkillElf2_1;
			skill2_2 = R.imgSkillElf2_2;
			skill2_3 = R.imgSkillElf2_3;
			skill3_1 = R.imgSkillElf3_1;
			skill3_2 = R.imgSkillElf3_2;
			skill3_3 = R.imgSkillElf3_3;
			break;
		case 3:

			skill1_1 = R.imgSkillWitch1_1;
			skill1_2 = R.imgSkillWitch1_2;
			skill1_3 = R.imgSkillWitch1_3;
			skill2_1 = R.imgSkillWitch2_1;
			skill2_2 = R.imgSkillWitch2_2;
			skill2_3 = R.imgSkillWitch2_3;
			skill3_1 = R.imgSkillWitch3_1;
			skill3_2 = R.imgSkillWitch3_2;
			skill3_3 = R.imgSkillWitch3_3;
			break;
		}

	}

	/**
	 * updating transfer latest role value to string
	 */
	public void update() {
		transferString();
	}

	protected void transferString() {

		strengthString = Integer.toString(this.getRole().getFixedStrength());
		dexterityString = Integer.toString(this.getRole().getFixedDexterity());
		constitutionString = Integer.toString(this.getRole()
				.getFixedConstitution());
		intelligenceString = Integer.toString(this.getRole()
				.getFixedIntelligence());
		wisdomString = Integer.toString(this.getRole().getFixedWisdom());
		charismaString = Integer.toString(this.getRole().getFixedCharisma());
		basicAtkString = Integer.toString(this.getRole().getFixedATK());
		basicPhysicalArmorString = Integer.toString(this.getRole()
				.getFixedPhysicalArmor());
		basicMoveDistanceString = Integer.toString(this.getRole()
				.getFixedMoveDistance());
		basicAttackDistanceString = Integer.toString(this.getRole()
				.getFixedAttackDistance());
		basicMagicResistString = Integer.toString(this.getRole()
				.getFixedMagicResist());
		HPString = Integer.toString(this.getRole().getFixedMaxHP());
		MPString = Integer.toString(this.getRole().getFixedMaxMP());
		attrPointString = Integer.toString(this.getRole().status
				.getAbilityPoint());
		skillPointString = Integer.toString(this.getRole().status
				.getSkillPoint());

		skill1_1LvString = Integer.toString(this.getRole().skill.getMagic(1)
				.getMagicLevel());
		skill1_2LvString = Integer.toString(this.getRole().skill.getMagic(2)
				.getMagicLevel());
		skill1_3LvString = Integer.toString(this.getRole().skill.getMagic(3)
				.getMagicLevel());
		skill2_1LvString = Integer.toString(this.getRole().skill.getMagic(4)
				.getMagicLevel());
		skill2_2LvString = Integer.toString(this.getRole().skill.getMagic(5)
				.getMagicLevel());
		skill2_3LvString = Integer.toString(this.getRole().skill.getMagic(6)
				.getMagicLevel());
		skill3_1LvString = Integer.toString(this.getRole().skill.getMagic(7)
				.getMagicLevel());
		skill3_2LvString = Integer.toString(this.getRole().skill.getMagic(8)
				.getMagicLevel());
		skill3_3LvString = Integer.toString(this.getRole().skill.getMagic(9)
				.getMagicLevel());
	}

	protected void init() {

		switch (type) {
		case 1:
			img = R.imgRole_1_G;
			heroName = CommonData.FIGHTER_TYPE_BULLY + "__";
			heroName += bullyNum;
			bullyNum++;
			break;
		case 2:
			heroName = CommonData.FIGHTER_TYPE_NIMBLE + "__";
			heroName += nimbleNum;
			nimbleNum++;
			img = R.imgRole_2_G;
			break;
		case 3:
			heroName = CommonData.FIGHTER_TYPE_TANK + "__";
			heroName += tankNum;
			tankNum++;
			img = R.imgRole_3_G;
			break;
		}

	}
	
	
	protected void initForBattle() {

		switch (type) {
		case 1:
			img = R.imgRole_1_G;
			heroName = CommonData.FIGHTER_TYPE_BULLY+" ";
			
			heroName+=role.ChaInfo.getName();
			break;
		case 2:
			img = R.imgRole_2_G;
			heroName =CommonData.FIGHTER_TYPE_NIMBLE+" ";
			heroName+=role.ChaInfo.getName();
			break;
		case 3:
			img = R.imgRole_3_G;
			heroName = CommonData.FIGHTER_TYPE_TANK+" ";
			heroName+=role.ChaInfo.getName();
			break;
		}

	}

	/**
	 * return image of character
	 * 
	 * @return
	 */
	public Image getImg() {

		return img;
	}

	/**
	 * set all the value of role to zero
	 */
	public void setZero() {

		this.getRole().getAbility()
				.updateAbility(new Ability(0, 0, 0, 0, 0, 0));
		this.getRole().getStatus().setAbilityPoint(90);
	}

	public GameCharacter getRole() {
		return role;
	}

	public void setRole(GameCharacter role) {
		this.role = role;
	}

	public void showInfo() {

		for (int i = 0; i < 6; i++) {
			showOneInfor(i);
		}

	}

	public void showOneInfor(int type) {
		String abi = "";
		switch (type) {
		case 0:
			abi = "Strength";
			break;
		case 1:
			abi = "Dexterity";
			break;
		case 2:
			abi = "Constitution";
			break;
		case 3:
			abi = "Wisdom";
			break;
		case 4:
			abi = "Intelligence";
			break;
		case 5:
			abi = "Charisma";
			break;
		default:
			break;

		}
		//int[][][] diceResult=role.ability.getDiceResult();
//		log.setLogInfor(abi+": ");
//		log.setLogInfor("-> Throw Times: " + diceResult[type][1].length);
//		log.setLogInfor("-> Choose Max of: " + diceResult[type][2].length);
//		log.setLogInfor("-> Throw Numbers: ");
//		String totalNumbers = "   ";
//		for (int i = 0; i < diceResult[type][1].length; i++) {
//			totalNumbers+= diceResult[0][1][i];
//			totalNumbers+= " ";
//		}
//		log.setLogInfor(totalNumbers);
//		
//		log.setLogInfor("-> Choose Max Numbers: ");
//		
//		String maxNumbers = "   ";
//		for (int i = 0; i < diceResult[type][2].length; i++) {
//			maxNumbers+= diceResult[0][2][i];
//			maxNumbers+= " ";
//		}
//		log.setLogInfor(maxNumbers);
	}

}
