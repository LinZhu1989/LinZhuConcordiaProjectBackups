/**
 * @author Lin Zhu (6555659)
 * SOEN 6441 Team Project 
 * Winter 2014
 */
package Data.Character;

import Data.GameData.CommonData;

/**
 * the class Attribute handles 5 attribute values for a Game Character
 * 
 * @author Lin Zhu
 * 
 */
public class Attribute {
	private int basicAtk;
	private int basicPhysicalArmor;
	private int basicMagicResist;
	private int basicMoveDistance;
	private int basicAttackDistance;
	public int[][] attackBonus;

	/**
	 * Class Attribute Constructor (With no argument)
	 */
	public Attribute() {
		this.basicAtk = CommonData.DEFAULT_CHARACTER_ATTRIBUTE_BASICATK;
		this.basicPhysicalArmor = CommonData.DEFAULT_CHARACTER_ATTRIBUTE_BASICPHYSICALARMOR;
		this.basicMagicResist = CommonData.DEFAULT_CHARACTER_ATTRIBUTE_BASICMAGICRESIST;
		this.basicMoveDistance = CommonData.DEFAULT_CHARACTER_ATTRIBUTE_BASICMOVEDISTANCE;
		this.basicAttackDistance = CommonData.DEFAULT_CHARACTER_ATTRIBUTE_BASICATTACKDISTANCE;
		this.attackBonus = new int[20][];
		this.attackBonus = CommonData.BASE_ATT_BONUS;
	}

	/**
	 * Class Attribute Constructor (With argument)
	 * 
	 * @param bAK
	 * @param bPA
	 * @param bMR
	 * @param bMD
	 * @param bAD
	 */
	public Attribute(int bAK, int bPA, int bMR, int bMD, int bAD) {
		this.basicAtk = bAK;
		this.basicPhysicalArmor = bPA;
		this.basicMagicResist = bMR;
		this.basicMoveDistance = bMD;
		this.basicAttackDistance = bAD;
		this.attackBonus = new int[20][];
		this.attackBonus = CommonData.BASE_ATT_BONUS;
	}

	public Attribute(int lvl) {
		this.basicAtk = CommonData.MONSTER_ATK[lvl];
		this.basicPhysicalArmor = CommonData.MONSTER_BASIC_PHYSICAL_ARMOR[lvl];
		this.basicMagicResist = CommonData.MONSTER_BASIC_MAGIC_RESIST[lvl];
		this.basicMoveDistance = CommonData.MONSTER_BASICMOVEDISTANCE;
		this.basicAttackDistance = CommonData.MONSTER_BASICATTACKDISTANCE;
		this.attackBonus = new int[20][];
		this.attackBonus = CommonData.BASE_ATT_BONUS;
	}

	/**
	 * update Attribute with a new Attribute object
	 * 
	 * @param atr
	 */
	public void updateAttribute(Attribute atr) {
		this.basicAtk = atr.getBasicAttack();
		this.basicPhysicalArmor = atr.getBasicPhysicalArmor();
		this.basicMagicResist = atr.getBasicMagicResist();
		this.basicMoveDistance = atr.getBasicMoveDistance();
		this.basicAttackDistance = atr.getBasicAttackDistance();
	}

	/**
	 * get value of basic Atk
	 * 
	 * @return
	 */
	public int getBasicAttack() {
		return this.basicAtk;
	}

	/**
	 * get value of basic Physical Armor
	 * 
	 * @return
	 */
	public int getBasicPhysicalArmor() {
		return this.basicPhysicalArmor;
	}

	/**
	 * get value of basic Magic Resist
	 * 
	 * @return
	 */
	public int getBasicMagicResist() {
		return this.basicMagicResist;
	}

	/**
	 * get value of basic Move Distance
	 * 
	 * @return
	 */
	public int getBasicMoveDistance() {
		return this.basicMoveDistance;
	}

	/**
	 * get value of basic Attack Distance
	 * 
	 * @return
	 */
	public int getBasicAttackDistance() {
		return this.basicAttackDistance;
	}

	/**
	 * set the basic ATK with a new value
	 * 
	 * @param bAK
	 */
	public boolean setBasicAttack(int bAK) {
		if (bAK < 0) {
			return false;
		}
		this.basicAtk = bAK;
		return true;
	}

	/**
	 * set the basic Physical Armor with a new value
	 * 
	 * @param bPA
	 */
	public boolean setBasicPhysicalArmor(int bPA) {
		if (bPA < 0) {
			return false;
		}
		this.basicPhysicalArmor = bPA;
		return true;
	}

	/**
	 * set the basic Magic Resist with a new value
	 * 
	 * @param bBR
	 */
	public boolean setBasicMagicResist(int bBR) {
		if (bBR < 0) {
			return false;
		}
		this.basicMagicResist = bBR;
		return true;
	}

	/**
	 * set the basic Move Distance with a new value
	 * 
	 * @param bMD
	 */
	public boolean setBasicMoveDistance(int bMD) {
		if (bMD < 0) {
			return false;
		}
		this.basicMoveDistance = bMD;
		return true;
	}

	/**
	 * set the basic Attack Distance with a new value
	 * 
	 * @param bAD
	 */
	public boolean setBasicAttackDistance(int bAD) {
		if (bAD < 0) {
			return false;
		}
		this.basicAttackDistance = bAD;
		return true;

	}



}
