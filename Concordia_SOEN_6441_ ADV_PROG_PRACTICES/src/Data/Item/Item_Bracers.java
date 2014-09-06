/**
 * @author Lin Zhu (6555659)
 * SOEN 6441 Team Project 
 * Winter 2014
 */
package Data.Item;

/**
 * define Item_Bracers class
 */
public class Item_Bracers implements Interface_Bracers, Interface_Item {

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

	public Item_Bracers() {
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
	}

	public Item_Bracers(String n, int w, int p, int[] fix) {
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
