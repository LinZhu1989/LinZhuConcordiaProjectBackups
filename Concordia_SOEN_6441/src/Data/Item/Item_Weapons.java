/**
 * @author Lin Zhu (6555659)
 * SOEN 6441 Team Project 
 * Winter 2014
 */
package Data.Item;

/**
 * Class Item_Weapons is a type of the items
 * 
 * @author Lin Zhu
 * 
 */
public class Item_Weapons implements Interface_Weapons, Interface_Item {

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
	private WeaponsType type;
	/**
	 * Class Item_Weapons Constructor (With no argument)
	 */
	public Item_Weapons() {
		this.name = "Nothing";
		this.weight = 0;
		this.price = 0;
		this.ajustATK = 0;
		this.adjustPhysicalArmor = 0;
		this.adjustMagicResist = 0;
		this.adjustAttackDistance = 0;
		this.adjustMoveDistance = 0;
		this.adjustStrength = 0;
		this.adjustDexterity = 0;
		this.adjustConstitution = 0;
		this.adjustWisdom = 0;
		this.adjustIntelligence = 0;
		this.adjustCharisma = 0;
		this.adjustCurrentHP = 0;
		this.adjustCurrentMP = 0;
		this.adjustMaxHP = 0;
		this.adjustMaxMP = 0;
		this.type=WeaponsType.NOWEAPONS;
	}

	public Item_Weapons(WeaponsType ty,String n, int w, int p, int[] fix) {
		this.name = n;
		this.weight = w;
		this.price = p;
		this.ajustATK = fix[0];
		this.adjustPhysicalArmor = fix[1];
		this.adjustMagicResist = fix[2];
		this.adjustAttackDistance = fix[3];
		this.adjustMoveDistance = fix[4];
		this.adjustStrength = fix[5];
		this.adjustDexterity = fix[6];
		this.adjustConstitution = fix[7];
		this.adjustWisdom = fix[8];
		this.adjustIntelligence = fix[9];
		this.adjustCharisma = fix[10];
		this.adjustCurrentHP = fix[11];
		this.adjustCurrentMP = fix[12];
		this.adjustMaxHP = fix[13];
		this.adjustMaxMP = fix[14];
		this.type=ty;
	}

	public Item_Weapons(int ty,String n, int w, int p, int[] fix) {
		this.name = n;
		this.weight = w;
		this.price = p;
		this.ajustATK = fix[0];
		this.adjustPhysicalArmor = fix[1];
		this.adjustMagicResist = fix[2];
		this.adjustAttackDistance = fix[3];
		this.adjustMoveDistance = fix[4];
		this.adjustStrength = fix[5];
		this.adjustDexterity = fix[6];
		this.adjustConstitution = fix[7];
		this.adjustWisdom = fix[8];
		this.adjustIntelligence = fix[9];
		this.adjustCharisma = fix[10];
		this.adjustCurrentHP = fix[11];
		this.adjustCurrentMP = fix[12];
		this.adjustMaxHP = fix[13];
		this.adjustMaxMP = fix[14];
		this.type=getWeaponsTypebyInt(ty);
	}
	
	public Item_Weapons(String ty,String n, int w, int p, int[] fix) {
		this.name = n;
		this.weight = w;
		this.price = p;
		this.ajustATK = fix[0];
		this.adjustPhysicalArmor = fix[1];
		this.adjustMagicResist = fix[2];
		this.adjustAttackDistance = fix[3];
		this.adjustMoveDistance = fix[4];
		this.adjustStrength = fix[5];
		this.adjustDexterity = fix[6];
		this.adjustConstitution = fix[7];
		this.adjustWisdom = fix[8];
		this.adjustIntelligence = fix[9];
		this.adjustCharisma = fix[10];
		this.adjustCurrentHP = fix[11];
		this.adjustCurrentMP = fix[12];
		this.adjustMaxHP = fix[13];
		this.adjustMaxMP = fix[14];
		this.type=getWeaponsTypebyString(ty);
	}
	
	/**
	 * get weapon type
	 * @param ty
	 * @return
	 */
	public int getIntbyWeaponsType(WeaponsType ty){
		switch(ty){
		case SWORD:
			return 1;
		case BOW:
			return 2;
		default:
			return 0;
		}
	}
	
	/**
	 * get weapon by type
	 * @param ty
	 * @return
	 */
	public WeaponsType getWeaponsTypebyInt(int ty){
		switch(ty){
		case 1:
			return WeaponsType.SWORD;
		case 2:
			return WeaponsType.BOW;
		default:
			return WeaponsType.NOWEAPONS;
		}
	}
	
	public WeaponsType getWeaponsTypebyString(String ty){
	return WeaponsType.valueOf(ty);
	}
	
	/**
	 * get type
	 */
	public WeaponsType getType() {
		return type;
	}
	
	/**
	 * get item name
	 */
	public String getItemName() {

		return this.name;
	}

	/**
	 * get item weight
	 */
	public int getItemWeight() {

		return this.weight;
	}

	/**
	 * get item price
	 */
	public int getItemPrice() {

		return this.price;
	}

	public int adjustATK() {

		return ajustATK;
	}

	public int adjustPhysicalArmor() {

		return adjustPhysicalArmor;
	}

	public int adjustMagicResist() {

		return adjustMagicResist;
	}

	public int adjustAttackDistance() {
		return getIntbyWeaponsType(type);
		//return adjustAttackDistance;
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
