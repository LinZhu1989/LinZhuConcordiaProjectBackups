/**
 * @author Lin Zhu (6555659)
 * SOEN 6441 Team Project 
 * Winter 2014
 */
package Data.GameData;

import java.util.ArrayList;

public class CommonData {

	/**
	 * The data of Character
	 */
	public static final String FIGHTER_TYPE_BULLY = "Bully";
	public static final String FIGHTER_TYPE_NIMBLE = "Nimble";
	public static final String FIGHTER_TYPE_TANK = "Tank";	
	public static final String[] CHARACTER_INFORMATION_FIGHTER_TYPES = {
		FIGHTER_TYPE_BULLY, FIGHTER_TYPE_NIMBLE, FIGHTER_TYPE_TANK };
	


	/**
	 * The data of Character_Ability
	 */
	// strength; dexterity; constitution; wisdom; intelligence; charisma;
	// Total raceBasicAbility 90;
	public static final int[][] CHARACTER_ABILITY_RACEBASICS = {
			{ 29, 7, 28, 6, 9, 11 }, { 13, 28, 13, 12, 11, 13 },
			{ 12, 10, 11, 21, 17, 19 } };
	public static final int CHARACTER_ABILITY_EXTRASTRENGTHHP = 9;
	public static final int CHARACTER_ABILITY_EXTRAINTELLIGENCEMP = 6;
	public static final int CHARACTER_ABILITY_EXTRADEXTERITYPHYSICALARMOR = 1;
	public static final int CHARACTER_ABILITY_EXTRACONSTITUTIONHP = 15;
	/**
	 * The data of Character_Attribute
	 */

	public static final int CHARACTER_ATTRIBUTE_BASICATK = 2;
	public static final int[] CHARACTER_ATTRIBUTE_BASICPHYSICALARMOR = { 1, 1,
			1 };
	public static final int[] CHARACTER_ATTRIBUTE_BASICMAGICRRESIST = { 0, 0, 0 };
	public static final int[] CHARACTER_ATTRIBUTE_BASICMOVEDISTANCE = { 6, 6, 6 };
	public static final int[] CHARACTER_ATTRIBUTE_BASICATTACKDISTANCE = { 1, 1,
			1 };
	public static final int DEFAULT_CHARACTER_ATTRIBUTE_BASICATK = 2;
	public static final int DEFAULT_CHARACTER_ATTRIBUTE_BASICPHYSICALARMOR = 10;
	public static final int DEFAULT_CHARACTER_ATTRIBUTE_BASICMAGICRESIST = 0;
	public static final int DEFAULT_CHARACTER_ATTRIBUTE_BASICMOVEDISTANCE = 6;
	public static final int DEFAULT_CHARACTER_ATTRIBUTE_BASICATTACKDISTANCE = 1;

	public static final int[][] BASE_ATT_BONUS = { { 1 }, { 2 }, { 3 }, { 4 },
			{ 5 }, { 6, 1 }, { 7, 2 }, { 8, 3 }, { 9, 4 }, { 10, 5 },
			{ 11, 6, 1 }, { 12, 7, 2 }, { 13, 8, 3 }, { 14, 9, 4 },
			{ 15, 10, 5 }, { 16, 11, 6, 1 }, { 17, 12, 7, 2 },
			{ 18, 13, 8, 3 }, { 19, 14, 9, 4 }, { 20, 15, 10, 5 }, };

	/**
	 * The data of Character_Status
	 */
	public static final int[] CHARACTER_STATUS_RACEBASICHP = { 50, 50, 50 };
	public static final int[] CHARACTER_STATUS_RACEBASICMP = { 50, 50, 50 };
	public static final int CHARACTER_STATUS_INITIALSKILLPTS = 50;
	public static final int CHARACTER_STATUS_INITIALABILITYPTS = 90;
	public static final int CHARACTER_STATUS_INITIALMONEY = 1000;
	public static final int CHARACTER_STATUS_SKILLPOINTSPERLEVEL = 6;
	public static final int[] CHARACTER_STATUS_EXPLIST = { 1000, 2250, 3750,
			5500, 7500, 10000, 13000, 16500, 20500, 26000, 32000, 39000, 47000,
			57000, 69000, 83000, 99000, 119000, 143000, 175000, 210000, 255000,
			310000, 375000, 450000, 550000, 675000, 825000, 10000000 };

	/**
	 * The data of Monster
	 */

	public static final int[] MONSTER_MAXHP = { 25, 35, 45, 55, 75, 90, 140,
			180, 250, 290 };
	public static final int[] MONSTER_ATK = { 1, 1, 2, 2, 3, 3, 4, 4, 5,
			5 };
	public static final int MONSTER_MAXLEVEL = 10;
	public static final int[] MONSTER_MINWISDOMBEFOUND = { 0, 4, 7, 10, 15 };
	public static final int MONSTER_MONSTERWORTHMONEY[] = { 80, 100, 200, 250,
			400, 500, 600, 800, 1500, 1800 };
	public static final int[] MONSTER_BASIC_PHYSICAL_ARMOR = { 0,0,1,3,3,3,4,7,8,10 };
	public static final int[] MONSTER_BASIC_MAGIC_RESIST = { 0,0,1,3,3,3,4,7,8,10 };
	public static final int MONSTER_BASICMOVEDISTANCE = 6;
	public static final int MONSTER_BASICATTACKDISTANCE = 1;
			
	
	
	/**
	 * The data for BackPack & Equipment
	 */

	public static final int DEFAULTBACKPACKWEIGHT = 2000;
	public static final int DEFAULTBACKPACKNUMBER = 50;
	public static final int DEFAULTEQUIPMENTWEIGHT = 2000;

	public static final int CostUpgradeAbility(int startScore, int tarScore) {

		int costInt = 0;
		if (startScore >= 0 && tarScore >= 0 && (tarScore - startScore) >= 0) {
			int timeOfAdd = tarScore - startScore; // Distance of the target
													// score
													// and the start score;
			int counter = 3;
			while (timeOfAdd > 0) {
				costInt += counter / 3; // Increase the cost value in every 3
										// times of cost of addtion
				counter++;
				timeOfAdd--;
			}
			return costInt;
		} else {
			System.out.println("Error Input Ability Score!");
			return Integer.MAX_VALUE;
		}
	}

	/**
	 * Data for Magics
	 */
	public static final int[][][][] MAGIC_SKILLNEEDPOINT = {
			{ { { 1, 3, 5 }, { 4, 3, 5 }, { 10, 5, 7 } },
					{ { 1, 3, 5 }, { 4, 3, 5 }, { 10, 5, 7 } },
					{ { 1, 3, 5 }, { 4, 3, 5 }, { 10, 5, 7 } } },
			{ { { 1, 2, 4 }, { 5, 3, 4 }, { 9, 3, 5 } },
					{ { 1, 3, 5 }, { 4, 3, 5 }, { 10, 5, 7 } },
					{ { 1, 2, 4 }, { 5, 3, 4 }, { 10, 3, 5 } } },
			{ { { 1, 2, 3 }, { 3, 2, 3 }, { 6, 3, 4 } },
					{ { 1, 2, 3 }, { 3, 2, 3 }, { 6, 3, 4 } },
					{ { 1, 2, 3 }, { 3, 2, 3 }, { 6, 3, 4 } }, } };

	private static final String[] MAGIC_NAMEFORDWARVEN1 = { "Headshot",
			"Take Aim", "Assassinate" };
	private static final String[] MAGIC_NAMEFORDWARVEN2 = { "Surge",
			"Psi Blades", "Overpower" };
	private static final String[] MAGIC_NAMEFORDWARVEN3 = { "Avalanche",
			"Storm Bolt", "Reverse Polarity" };
	private static final String[] MAGIC_NAMEFORELVEN1 = { "Shackleshot",
			"Powershot", "Focus Fire" };
	private static final String[] MAGIC_NAMEFORELVEN2 = { "Moment of Courage",
			"Living Armor", "Berserker's Blood" };
	private static final String[] MAGIC_NAMEFORELVEN3 = { "Lucent Beam",
			"Magic Missile", "Moonlight Shadow" };
	private static final String[] MAGIC_NAMEFORWIZARD1 = { "Arc Lightning",
			"Lightning Bolt", "Thundergod's Wrath" };
	private static final String[] MAGIC_NAMEFORWIZARD2 = { "Purification",
			"Repel", "Hand of God" };
	private static final String[] MAGIC_NAMEFORWIZARD3 = { "Purification",
			"Plague Ward", "Poison Nova" };

	private static final int[][] MAGIC_COSTFORDWARVEN1 = { { 50, 50, 50 },
			{ 50, 50, 50 }, { 50, 50, 50 } };
	private static final int[][] MAGIC_COSTFORDWARVEN2 = { { 50, 50, 50 },
			{ 50, 50, 50 }, { 50, 50, 50 } };
	private static final int[][] MAGIC_COSTFORDWARVEN3 = { { 50, 50, 50 },
			{ 50, 50, 50 }, { 50, 50, 50 } };
	private static final int[][] MAGIC_COSTFORELVEN1 = { { 50, 50, 50 },
			{ 50, 50, 50 }, { 50, 50, 50 } };
	private static final int[][] MAGIC_COSTFORELVEN2 = { { 50, 50, 50 },
			{ 50, 50, 50 }, { 50, 50, 50 } };

	private static final int[][] MAGIC_COSTFORELVEN3 = { { 50, 50, 50 },
			{ 50, 50, 50 }, { 50, 50, 50 } };
	private static final int[][] MAGIC_COSTFORWIZARD1 = { { 50, 50, 50 },
			{ 50, 50, 50 }, { 50, 50, 50 } };
	private static final int[][] MAGIC_COSTFORWIZARD2 = { { 50, 50, 50 },
			{ 50, 50, 50 }, { 50, 50, 50 } };
	private static final int[][] MAGIC_COSTFORWIZARD3 = { { 50, 50, 50 },
			{ 50, 50, 50 }, { 50, 50, 50 } };

	private static final Boolean[] MAGIC_TYPEFORDWARVEN1 = { true, true, true };
	private static final Boolean[] MAGIC_TYPEFORDWARVEN2 = { true, true, true };
	private static final Boolean[] MAGIC_TYPEFORDWARVEN3 = { true, true, true };
	private static final Boolean[] MAGIC_TYPEFORELVEN1 = { true, true, true };
	private static final Boolean[] MAGIC_TYPEFORELVEN2 = { true, true, true };

	private static final Boolean[] MAGIC_TYPEFORELVEN3 = { true, true, true };
	private static final Boolean[] MAGIC_TYPEFORWIZARD1 = { true, true, true };
	private static final Boolean[] MAGIC_TYPEFORWIZARD2 = { true, true, true };
	private static final Boolean[] MAGIC_TYPEFORWIZARD3 = { true, true, true };

	private static final ArrayList<String[]> combineMagicName() {
		ArrayList<String[]> result = new ArrayList<String[]>();
		result.add(MAGIC_NAMEFORDWARVEN1);
		result.add(MAGIC_NAMEFORDWARVEN2);
		result.add(MAGIC_NAMEFORDWARVEN3);
		result.add(MAGIC_NAMEFORELVEN1);
		result.add(MAGIC_NAMEFORELVEN2);
		result.add(MAGIC_NAMEFORELVEN3);
		result.add(MAGIC_NAMEFORWIZARD1);
		result.add(MAGIC_NAMEFORWIZARD2);
		result.add(MAGIC_NAMEFORWIZARD3);
		return result;

	}

	private static final ArrayList<int[][]> combineMagicCost() {
		ArrayList<int[][]> result = new ArrayList<int[][]>();
		result.add(MAGIC_COSTFORDWARVEN1);
		result.add(MAGIC_COSTFORDWARVEN2);
		result.add(MAGIC_COSTFORDWARVEN3);
		result.add(MAGIC_COSTFORELVEN1);
		result.add(MAGIC_COSTFORELVEN2);
		result.add(MAGIC_COSTFORELVEN3);
		result.add(MAGIC_COSTFORWIZARD1);
		result.add(MAGIC_COSTFORWIZARD2);
		result.add(MAGIC_COSTFORWIZARD3);
		return result;

	}

	private static final ArrayList<Boolean[]> combineMagicTypes() {
		ArrayList<Boolean[]> result = new ArrayList<Boolean[]>();
		result.add(MAGIC_TYPEFORDWARVEN1);
		result.add(MAGIC_TYPEFORDWARVEN2);
		result.add(MAGIC_TYPEFORDWARVEN3);
		result.add(MAGIC_TYPEFORELVEN1);
		result.add(MAGIC_TYPEFORELVEN2);
		result.add(MAGIC_TYPEFORELVEN3);
		result.add(MAGIC_TYPEFORWIZARD1);
		result.add(MAGIC_TYPEFORWIZARD2);
		result.add(MAGIC_TYPEFORWIZARD3);
		return result;

	}

	public static final ArrayList<String[]> MAGICNAMES = combineMagicName();
	public static final ArrayList<int[][]> MAGICCOSTS = combineMagicCost();
	public static final ArrayList<Boolean[]> MAGICTYPES = combineMagicTypes();

	/**
	 * Data for Items
	 */
	public static final String ITEM_COMMONNAMES_ARMOR="Armor";
	public static final String ITEM_COMMONNAMES_WEAPONS = "Weapons";
	public static final String ITEM_COMMONNAMES_SHIELD="Shield";
	
	public static final String[] ITEM_COMMONNAMES = { ITEM_COMMONNAMES_ARMOR, "Belt", "Boots",
			"Helmet", "Ring", ITEM_COMMONNAMES_WEAPONS, "Potion" ,ITEM_COMMONNAMES_SHIELD,"Bracers","Gloves"};

	public static final String[] ITEM_ARMORNAMES = { "Armor1", "Armor2",
			"Armor3", "Armor4", "Armor5", "Armor6", "Armor7", "Armor8" };
	public static final String[] ITEM_BELTNAMES = { "Belt1", "Belt2", "Belt3",
			"Belt4", "Belt5" };
	public static final String[] ITEM_BOOTSNAMES = { "Boots1", "Boots2",
			"Boots3", "Boots4", "Boots5" };
	public static final String[] ITEM_HELMETNAMES = { "Helmet1", "Helmet2",
			"Helmet3", "Helmet4", "Helmet5" };
	public static final String[] ITEM_RINGNAMES = { "Ring1", "Ring2", "Ring3",
			"Ring4", "Ring5" };
	public static final String[] ITEM_WEAPONSNAMES = { "Weapons1", "Weapons2",
			"Weapons3", "Weapons4", "Weapons5" };
	public static final String[] ITEM_POTIONNAMES = { "Potion1", "Potion2",
			"Potion3", "Potion4", "Potion5" };
	public static final String[] ITEM_SHIELDNAMES = { "Shield1", "Shield2",
		"Shield3", "Shield4", "Shield5" };
	public static final String[] ITEM_BRACERSNAMES = { "Bracers1", "Bracers2",
		"Bracers3", "Bracers4", "Bracers5" };
	public static final String[] ITEM_GLOVESNAMES = { "Gloves1", "Gloves2",
		"Gloves3", "Gloves4", "Gloves5" };

	public static final int[] ITEM_ARMORWEIGHTS = { 10, 20, 20, 30,30,30, 30, 50 };
	public static final int[] ITEM_BELTWEIGHTS = { 5, 5, 10, 10, 15 };
	public static final int[] ITEM_BOOTSWEIGHTS = { 5, 10, 10, 15, 20 };
	public static final int[] ITEM_HELMETWEIGHTS = { 10, 10, 15, 15, 20 };
	public static final int[] ITEM_RINGWEIGHTS = { 2, 3, 5, 5, 6 };
	public static final int[] ITEM_WEAPONSWEIGHTS = { 10, 15, 20, 20, 25 };
	public static final int[] ITEM_POTIONWEIGHTS = { 1, 1, 2, 2, 3 };
	public static final int[] ITEM_SHIELDWEIGHTS = { 1, 1, 2, 2, 3 };
	public static final int[] ITEM_BRACERSWEIGHTS = { 1, 1, 2, 2, 3 };
	public static final int[] ITEM_GLOVESWEIGHTS = { 1, 1, 2, 2, 3 };

	public static final int[] ITEM_ARMORPRICES = { 200, 250, 250,350,380,400, 500, 800 };
	public static final int[] ITEM_BELTPRICES = { 50, 80, 100, 150, 200 };
	public static final int[] ITEM_BOOTSPRICES = { 200, 300, 350, 400, 600 };
	public static final int[] ITEM_HELMETPRICES = { 150, 200, 250, 400, 700 };
	public static final int[] ITEM_RINGPRICES = { 50, 80, 100, 150, 200 };
	public static final int[] ITEM_WEAPONSPRICES = { 400, 450, 600, 700, 1000 };
	public static final int[] ITEM_POTIONPRICES = { 50, 60, 100, 220, 280 };
	public static final int[] ITEM_SHIELDPRICES = { 50, 60, 100, 220, 280 };
	public static final int[] ITEM_BRACERSPRICES = { 50, 60, 100, 220, 280 };
	public static final int[] ITEM_GLOVESPRICES = { 50, 60, 100, 220, 280 };

	// 1st is weight,2nd is price,rest are ajust values
	// "ajustATK","adjustPhysicalArmor","adjustMagicResist","adjustAttackDistance",
	// "adjustMoveDistance","adjustStrength","adjustDexterity","adjustConstitution",
	// "adjustWisdom","adjustIntelligence","adjustCharisma","adjustCurrentHP",
	// "adjustCurrentMP","adjustMaxHP","adjustMaxMP;
	public static final int[][] ITEM_ARMORFIXVALUES = {
			{ 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
	public static final int[][] ITEM_BELTFIXVALUES = {
			{ 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 3, 0, 2, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 3, 0, 3, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 4, 0, 4, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 5, 0, 5, 0, 0, 0, 0, 0, 0, 0 } };
	public static final int[][] ITEM_BOOTSFIXVALUES = {
			{ 0, 1, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 1, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 2, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 2, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 3, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0 } };
	public static final int[][] ITEM_HELMETFIXVALUES = {
			{ 0, 2, 0, 0, 0, 0, 0, 0, 2, 3, 0, 0, 0, 0, 0 },
			{ 0, 2, 0, 0, 0, 0, 0, 0, 3, 2, 0, 0, 0, 0, 0 },
			{ 0, 3, 0, 0, 0, 0, 0, 0, 4, 1, 0, 0, 0, 0, 0 },
			{ 0, 3, 0, 0, 0, 0, 0, 0, 4, 2, 0, 0, 0, 0, 0 },
			{ 0, 4, 0, 0, 0, 0, 0, 0, 5, 2, 0, 0, 0, 0, 0 } };
	public static final int[][] ITEM_RINGFIXVALUES = {
			{ 0, 1, 0, 0, 0, 1, 0, 2, 2, 0, 1, 0, 0, 0, 0 },
			{ 0, 1, 0, 0, 0, 2, 0, 2, 2, 0, 1, 0, 0, 0, 0 },
			{ 0, 1, 0, 0, 0, 2, 0, 3, 3, 0, 2, 0, 0, 0, 0 },
			{ 0, 1, 0, 0, 0, 3, 0, 3, 3, 0, 3, 0, 0, 0, 0 },
			{ 0, 2, 0, 0, 0, 3, 0, 4, 4, 0, 5, 0, 0, 0, 0 } };
	public static final int[][] ITEM_WEAPONSFIXVALUES = {
			{ 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
	public static final int[][] ITEM_POTIONFIXVALUES = {
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 50, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 300, 100, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 400, 150, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 500, 300, 0, 0 } };
	public static final int[][] ITEM_SHIELDFIXVALUES = {
		{ 0, 0, 0, 0, 1, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 1, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 2, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 3, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0 } };
	public static final int[][] ITEM_BRACERSFIXVALUES = {
		{ 0, 2, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 2, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 3, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 3, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 4, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
	public static final int[][] ITEM_GLOVESFIXVALUES = {
		{ 0, 0, 0, 0, 1, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 1, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 2, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 2, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 3, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0 } };

	/**
	 * Genders and Names
	 */

	public static final String[] CHARACTER_INFORMATION_NAMES = { "Alton",
			"Balok", "Baragh", "Belaern", "Belgos", "Bemril", "Berg'inyon",
			"Bhintel", "Brorn", "Bruherd", "Caelkoth", "Callimar", "Chakos",
			"Chaszmyr", "Coranzen", "Dantrag", "Dhuunyl", "Dinin",
			"Dresmorlin", "Dro", "Duagloth", "Durdyn", "Elamshin", "Elendar",
			"Elkantar", "Filraen", "Ghaundan", "Ghaundar", "Guldor", "Guldor",
			"Gwylyss", "Hadrogh", "Hatch'net", "Honemmeth", "Houndaer",
			"Ildan", "Ilmryn", "Ilphrin", "Imbros", "Irennan", "Istolil",
			"Istorvir", "Iymril", "Jaezred", "Jalynfein", "Jeggred", "Jevan",
			"Jhaamdath", "Jhaldrym", "Jivvin", "Jyslin", "K'yorl", "Kalannar",
			"Kethan", "Kluthruel", "Kophyn", "Krenaste", "Krondorl", "Kyorlin",
			"Lesaonar", "Lirdnolu", "Llaulmyn", "Malaggar", "Micarlin",
			"Minolin", "Molvayas", "Morennel", "Nadal", "Nalfein",
			"Narissorin", "Narlros", "Nilonim", "Nimruil", "Numrini'th",
			"Nyloth", "Nym", "Omareth", "Orgoloth", "Ornaryn", "Pharaun",
			"Pharius", "Quave", "Quendar", "Quenthel", "Quevven", "Ranaghar",
			"Relonor", "Riklaunim", "Rinnill", "Ristel", "Ruathym", "Ryld",
			"Ryltar", "Sabal", "Selakiir", "Seldszar", "Seldszar", "Sengo",
			"Solaufein", "Sorn", "Syrdar", "Szordrin", "Szordrin", "Taldinyon",
			"Tarlyn", "Tathlyn", "Tazennin", "Tebryn", "Tolokoph", "Torrellan",
			"Trelgath", "Tsabrak", "Urlryn", "Valas", "Veldrin", "Velkyn",
			"Vhurdaer", "Vhurindrar", "Vielyn", "Vlondril", "Vorn", "Vuzlyn",
			"Welverin", "Xarann", "Xundus", "Yazston", "Yuimmar", "Zaknafein",
			"Zeerith", "Zyn", "Ahlysaaria", "Akordia", "Alaunirra", "Alystin",
			"Amalica", "Angaste", "Anluryn", "Ardulace", "Aunrae", "Balaena",
			"Baltana", "Bautha", "Belarbreena", "Beszrima", "Brigantyna",
			"Briza", "Brorna", "Burryna", "Byrtyn", "Cazna", "Chadra",
			"Chadzina", "Chalithra", "Chandara", "Chardalyn", "Charinida",
			"Charlindra", "Chenzira", "Chessintra", "Dhaunae", "Dilynrae",
			"Drada", "Drisinil", "Eclavdra", "Elerra", "Elvanshalee",
			"Elvraema", "Erakasyne", "Ereldra", "Faeryl", "Felyndiira",
			"Felyndiira", "Filfaere", "G'eldriia", "Gaussra", "Ghilanna",
			"Greyanna", "Gurina", "Haelra", "Halisstra", "Ilharess",
			"Ilivarrra", "Ilmra", "Imrae", "Jaelryn", "Jezzara", "Jhaelryna",
			"Jhaelrynna", "Jhalass", "Jhangara", "Jhanniss", "Jhulae",
			"Khaless", "Kiaran", "Laele", "Laele", "Larynda", "LiNeerlay",
			"Lledrith", "Llolfaen", "Lualyrr", "Lythrana", "Malice", "Maya",
			"Menzoberra", "Mez'Barris", "Micarlin", "Miz'ri", "Mizzrym",
			"Myrymma", "Narcelia", "Nathrae", "Nedylene", "Nendra", "Nizana",
			"Nulliira", "Olorae", "Pellanistra", "Phaere", "Phyrra", "Qilue",
			"Quarra", "Rauva", "Rilrae", "Sabrae", "Saradreza", "Sassandra",
			"Schezalle", "Shimyra", "ShriNeerune", "Shulvallriel",
			"Shurdriira", "Shurdriira", "Shurraenil", "Shyntlara", "SiNafay",
			"Sindyrrith", "Solenzara", "Ssapriina", "T'risstree", "Talabrina",
			"Talice", "Tallrene", "Thalra", "Thirza", "Thraele", "Triel",
			"Ulitree", "Ulviirala", "Umrae", "Urlryn", "Urmelena", "Vhondryl",
			"Viconia", "Vierna", "Vornalla", "Waerva", "Wuyondra", "Xalyth",
			"Xullrae", "Xune", "Yasrena", "Yvonnel", "Z'ress", "Zarra",
			"Zebeyana", "Zeerith", "Zelpassa", "Zendalure", "Zesstra", "Zilvra" };

	public static final String[] MONSTER_NAMES = { "Achaios", "Bormos",
			"Epione", "Inferi", "Leimone", "Mnestra", "Penthesileia", "Rhea",
			"Acis", "Bromios", "Erato", "Inuus", "Leipephile", "Molossos",
			"Peripanos", "Salamis", "Adonis", "Brontes", "Erebos", "Iobes",
			"Leuke", "Morpheus", "Persephone", "Salios", "Aegipan", "Bukolos",
			"Euadne", "Iphis", "Leukippe", "Mulciber", "Perseus", "Satyros",
			"Aigis", "Camers", "Euchenor", "Irae", "Leukon", "Musa", "Phanes",
			"Semele", "Aigyptos", "Carna", "Euenos", "Irus", "Linos",
			"Mykenai", "Pheraia", "Sibyl", "Aiolides", "Catillus", "Eunomos",
			"Ischys", "Lityerses", "Myrine", "Philyra", "Sikyon", "Aion",
			"Charis", "Eupalamos", "Isyrion", "Llawran", "Myrto", "Phlegrai",
			"Silvanus", "Aisa", "Chesias", "Euphorbos", "Janus", "Lykeios",
			"Nausithos", "Phobos", "Sinope", "Aisakos", "Chryses", "Europe",
			"Jupiter", "Lykomedes", "Nautes", "Phrasios", "Sisyphos",
			"Aithilla", "Cybele", "Fames", "Justitia", "Lykophron", "Neaira",
			"Phrixos", "Sithon", "Aithon", "Damia", "Fauna", "Kampe",
			"Lykurgos", "Neilos", "Phthonos", "Sol", "Aitne", "Danae",
			"Galateia", "Kapys", "Lynkos", "Nemea", "Pieria", "Sybaris",
			"Akakos", "Dardanos", "Galeos", "Kaukon", "Lysippe", "Nessos",
			"Pisos", "Syme", "Alkmene", "Deianeira", "Glauke", "Kaunos",
			"Machaon", "Nireus", "Pitane", "Talaos", "Ampelos", "Deidameia",
			"Grups", "Kelmis", "Maiandros", "Nomios", "Pittheus", "Tantalos",
			"Anaxibia", "Deimachos", "Gyes", "Kephalos", "Makaria", "Nyx",
			"Poine", "Tatius", "Anius", "Deimos", "Gygas", "Kilix", "Mars",
			"Ogaphos", "Polybos", "Telephassa", "Antigone", "Dekelos", "Halia",
			"Klaros", "Mavors", "Ogygos", "Polydamna", "Tenes", "Apemosyne",
			"Delphos", "Halisera", "Kleobis", "Megareus", "Oiax", "Polykaon",
			"Teukros", "Archedios", "Derkynos", "Helias", "Kranaos",
			"Melaineus", "Oibalos", "Polyxo", "Thaeox", "Argo", "Dodona",
			"Helios", "Kyknos", "Melampus", "Oinomaos", "Portheus", "Thamyri",
			"Arkeisios", "Dryope", "Hemithea", "Kyzikos", "Memphis", "Ophis",
			"Potitii", "Thelxion", "Askalabos", "Dwyvaer", "Hepaklos", "Laios",
			"Menestheus", "Orthaia", "Priamos", "Theophane", "Atropos",
			"Dysaules", "Herkyna", "Lampetos", "Merops", "Oxylos", "Prokne",
			"Thespis", "Atys", "Echetlos", "Hippotes", "Laodameia", "Mestor",
			"Pallene", "Prokris", "Thoas", "Augeias", "Echo", "Hopladamos",
			"Laodike", "Metaneira", "Pasiphae", "Proteus", "Thyia", "Auson",
			"Eidothea", "Huaina", "Lapithes", "Metis", "Pedasos", "Prothoos",
			"Tyche", "Bacchus", "Elatus", "Hylas", "Latinos", "Metope",
			"Peirene", "Remnus", "Typhon", "Bakis", "Elpenor", "Iamos",
			"Latona", "Minos", "Pelias", "Rhadamanthys", "Uranos", "Belos",
			"Enipeus", "Ianthe", "Lausus", "Minyas", "Penates", "Rhadine",
			"Vesta", "Berekyntia", "Epigonoi", "Ilos", "Laverna",
			"Misericordia", "Penia", "Rhakios", "Zephyrus" };

	public static final String[] MONSTERDESCRIPTIONS = { "Big", "Ugly",
			"Smart", "Beautiful", "Strong", "Week" };

	public static final String[] CHARACTERDESCRIPTIONS = { "Big", "Ugly",
			"Smart", "Beautiful", "Strong", "Week" };

	public static final void copyArray(int[] copier, int[] target){
		assert(copier.length==target.length);
		for(int i=0;i<copier.length;i++){
			target[i]=copier[i];
		}	
	}
	
	public static final void copyArray(int[][] copier, int[][] target){
		assert(copier.length==target.length);
		assert(copier[0].length==target[0].length);
		for(int i=0;i<copier.length;i++){
		for(int j=0;j<copier[0].length;j++){
			target[i][j]=copier[i][j];
		}	
		}
	}
	
	public CommonData() {

	}

}
