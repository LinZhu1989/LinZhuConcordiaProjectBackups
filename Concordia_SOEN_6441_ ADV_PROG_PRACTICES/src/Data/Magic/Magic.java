/**
 * @author Lin Zhu (6555659)
 * SOEN 6441 Team Project 
 * Winter 2014
 */
package Data.Magic;

/**
 * the class Magic contains a magic for a character
 * 
 * @author Lin Zhu
 * 
 */
public class Magic implements MagicInterface {

	private String magicName;
	private int[] manaCost;
	private int magicLevel;
	private boolean effectType;
	private int[] needPtsNextLevel;
	private int magicID;

	/**
	 * Class Information Constructor (No argument)
	 */
	public Magic() {
		this.magicName = "Unknown";
		this.magicLevel = 0;
		this.effectType = true;
	}

	/**
	 * Class Magic Constructor (With argument)
	 * 
	 * @param ID
	 * @param name
	 * @param cost
	 * @param lvl
	 * @param type
	 * @param needPts
	 */
	public Magic(int ID, String name, int[] cost, int lvl, boolean type,
			int[] needPts) {
		this.magicID = ID;
		this.magicName = name;
		this.manaCost = cost;
		this.magicLevel = lvl;
		this.effectType = type;
		this.needPtsNextLevel = needPts;
	}

	/**
	 * set a magic level
	 */
	public boolean setMagicLevel(int lvl) {
		if(lvl<0){
			return false;
		}
		this.magicLevel = lvl;
		return true;
	}

	public int getMagicID() {

		return this.magicID;
	}

	public String getMagicName() {

		return this.magicName;
	}

	public int getManaCost() {

		return this.manaCost[magicLevel - 1];
	}

	public int getMagicLevel() {

		return this.magicLevel;
	}

	public boolean getEffectType() {

		return this.effectType;
	}

	public int getNeedPtsNextLevel() {

		return this.needPtsNextLevel[magicLevel];
	}

	public boolean updateMagic() {
		if(this.magicLevel==3){
			return false;
		}
		this.magicLevel++;
		return true;
	}

	public int[] ajustOppHP() {

		return null;
	}

	public int[] ajustSelfHP() {

		return null;
	}

	public int[] adjustATK() {

		return null;
	}

	public int[] adjustPhysicalArmor() {

		return null;
	}

	public int[] adjustMagicResist() {

		return null;
	}

	public int[] adjustAttackDistance() {

		return null;
	}

	public int[] adjustMoveDistance() {

		return null;
	}

}
