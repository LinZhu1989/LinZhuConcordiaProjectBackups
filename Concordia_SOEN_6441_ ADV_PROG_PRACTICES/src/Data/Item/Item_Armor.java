/**
 * @author Lin Zhu (6555659)
 * SOEN 6441 Team Project 
 * Winter 2014
 */
package Data.Item;

/**
 * define Item_Armor
 */
public class Item_Armor implements Interface_Armor, Interface_Item {

	private String name;
	private int weight;
	private int price;
	private int ajustATK;
	private int adjustPhysicalArmor;
	private int adjustMagicResist;
	private int adjustAttackDistance;
	private int adjustMoveDistance;
	private int adjustStrength;
	private int adjustDexterity;
	private int adjustConstitution;
	private int adjustWisdom;
	private int adjustIntelligence;
	private int adjustCharisma;
	private int adjustCurrentHP;
	private int adjustCurrentMP;
	private int adjustMaxHP;
	private int adjustMaxMP;
	private ArmorType type;

	public Item_Armor() {
		this.name="Nothing";
		this.weight=0;
		this.price=0;
		this.ajustATK=0;
		this.adjustPhysicalArmor=0;
		this.adjustMagicResist=0;
		this.adjustAttackDistance=0;
		this.adjustMoveDistance=0;
		this.adjustStrength=0;
		this.adjustDexterity=0;
		this.adjustConstitution=0;
		this.adjustWisdom=0;
		this.adjustIntelligence=0;
		this.adjustCharisma=0;
		this.adjustCurrentHP=0;
		this.adjustCurrentMP=0;
		this.adjustMaxHP=0;
		this.adjustMaxMP=0;
		this.type=ArmorType.NOARMOR;
	}

	public Item_Armor(ArmorType ty,String n, int w, int p, int[] fix) {
		this.name=n;
		this.weight=w;
		this.price=p;
		this.ajustATK=fix[0];
		this.adjustPhysicalArmor=fix[1];
		this.adjustMagicResist=fix[2];
		this.adjustAttackDistance=fix[3];
		this.adjustMoveDistance=fix[4];
		this.adjustStrength=fix[5];
		this.adjustDexterity=fix[6];
		this.adjustConstitution=fix[7];
		this.adjustWisdom=fix[8];
		this.adjustIntelligence=fix[9];
		this.adjustCharisma=fix[10];
		this.adjustCurrentHP=fix[11];
		this.adjustCurrentMP=fix[12];
		this.adjustMaxHP=fix[13];
		this.adjustMaxMP=fix[14];
		this.type=ty;
	}

	public Item_Armor(int ty,String n, int w, int p, int[] fix) {
		this.name=n;
		this.weight=w;
		this.price=p;
		this.ajustATK=fix[0];
		this.adjustPhysicalArmor=fix[1];
		this.adjustMagicResist=fix[2];
		this.adjustAttackDistance=fix[3];
		this.adjustMoveDistance=fix[4];
		this.adjustStrength=fix[5];
		this.adjustDexterity=fix[6];
		this.adjustConstitution=fix[7];
		this.adjustWisdom=fix[8];
		this.adjustIntelligence=fix[9];
		this.adjustCharisma=fix[10];
		this.adjustCurrentHP=fix[11];
		this.adjustCurrentMP=fix[12];
		this.adjustMaxHP=fix[13];
		this.adjustMaxMP=fix[14];
		this.type=getArmorTypebyInt(ty);
	}
	
	public Item_Armor(String ty,String n, int w, int p, int[] fix) {
		this.name=n;
		this.weight=w;
		this.price=p;
		this.ajustATK=fix[0];
		this.adjustPhysicalArmor=fix[1];
		this.adjustMagicResist=fix[2];
		this.adjustAttackDistance=fix[3];
		this.adjustMoveDistance=fix[4];
		this.adjustStrength=fix[5];
		this.adjustDexterity=fix[6];
		this.adjustConstitution=fix[7];
		this.adjustWisdom=fix[8];
		this.adjustIntelligence=fix[9];
		this.adjustCharisma=fix[10];
		this.adjustCurrentHP=fix[11];
		this.adjustCurrentMP=fix[12];
		this.adjustMaxHP=fix[13];
		this.adjustMaxMP=fix[14];
		this.type=getArmorTypebyString(ty);
	}
	
	/**
	 * get int by armor type
	 * @param ty
	 * @return
	 */
	public int getIntbyArmorType(ArmorType ty){
		switch(ty){
		case PADDED:
			return 1;
		case LEATHER:
			return 2;
		case STUDDED_LEATHER:
			return 3;
		case CHAIN_SHIRT:
			return 4;
		case BREAST_PLATE:
			return 5;
		case BANDED_MAIL:
			return 6;
		case HALF_PLATE:
			return 7;
		case FULL_PLATE:
			return 8;
		default:
			return 0;
		}
	}
	
	/**
	 * get armor type by int
	 * @param ty
	 * @return
	 */
	public ArmorType getArmorTypebyInt(int ty){
		switch(ty){
		case 1:
			return ArmorType.PADDED;
		case 2:
			return ArmorType.LEATHER;
		case 3:
			return ArmorType.STUDDED_LEATHER;
		case 4:
			return ArmorType.CHAIN_SHIRT;
		case 5:
			return ArmorType.BREAST_PLATE;
		case 6:
			return ArmorType.BANDED_MAIL;
		case 7:
			return ArmorType.HALF_PLATE;
		case 8:
			return ArmorType.FULL_PLATE;
		default:
			return ArmorType.NOARMOR;
		}
	}
	
	public ArmorType getArmorTypebyString(String ty){
		return ArmorType.valueOf(ty);
	}
	
	public ArmorType getType() {
		return type;
	}
	public String getItemName() {

		return this.name;
	}

	public int getItemWeight() {

		return this.weight;
	}

	public int getItemPrice() {

		return this.price;
	}



	public int adjustATK() {

		return ajustATK;
	}

	public int adjustPhysicalArmor() {
		return this.getIntbyArmorType(type);
		//return adjustPhysicalArmor;
	}

	public int adjustMagicResist() {

		return adjustMagicResist;
	}

	public int adjustAttackDistance() {

		return adjustAttackDistance;
	}

	public int adjustMoveDistance() {

		return adjustMoveDistance;
	}

	public int adjustStrength() {

		return adjustStrength;
	}

	public int adjustDexterity() {

		return adjustDexterity;
	}

	public int adjustConstitution() {

		return adjustConstitution;
	}

	public int adjustWisdom() {

		return adjustWisdom;
	}

	public int adjustIntelligence() {

		return adjustIntelligence;
	}

	public int adjustCharisma() {

		return adjustCharisma;
	}

	public int adjustCurrentHP() {

		return adjustCurrentHP;
	}

	public int adjustCurrentMP() {

		return adjustCurrentMP;
	}

	public int adjustMaxHP() {

		return adjustMaxHP;
	}

	public int adjustMaxMP() {

		return adjustMaxMP;
	}



}
