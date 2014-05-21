/**
 * Author:	Lin Zhu
 * ID:		6555659
 * SOEN 6441 Team Project 
 * Winter 2014
 */
package Data.Magic;

/**
 * MagicInterface for the items
 * 
 * @author Lin Zhu
 * 
 */
public interface MagicInterface {

	public String getMagicName(); // Return the name of the magic

	public int getManaCost(); // Return the mana cost of the magic

	public int getMagicLevel(); // Return the level of the magic

	public boolean getEffectType(); // Return the effect type of the magic

	public int getNeedPtsNextLevel(); // Return the skill point needed to the
										// next level

	public boolean updateMagic(); // Return true if update successfully, else
									// return false

	public int[] ajustOppHP();

	public int[] ajustSelfHP();

	public int[] adjustATK();

	public int[] adjustPhysicalArmor();

	public int[] adjustMagicResist();

	public int[] adjustAttackDistance();

	public int[] adjustMoveDistance();

	public boolean setMagicLevel(int i);

}
