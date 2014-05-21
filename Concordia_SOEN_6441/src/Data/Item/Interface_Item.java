/**
 * Author:	Lin Zhu
 * ID:		6555659
 * SOEN 6441 Team Project 
 * Winter 2014
 */
package Data.Item;

public interface Interface_Item {

	public String getItemName();

	public int adjustATK();

	public int adjustPhysicalArmor();

	public int adjustAttackDistance();

	public int adjustMoveDistance();

	public int adjustStrength();

	public int adjustDexterity();

	public int adjustConstitution();

	public int adjustWisdom();

	public int adjustIntelligence();

	public int adjustCharisma();
	
	public int adjustMaxHP();

	public int adjustCurrentHP();

	public int adjustCurrentMP();



	public int adjustMaxMP();

	public int adjustMagicResist();

	public int getItemWeight();

	public int getItemPrice();

}
