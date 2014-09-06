/**
 * @author Lin Zhu (6555659)
 * SOEN 6441 Team Project 
 * Winter 2014
 */
package Data.Character;

import Data.Builder.DiceTool;
import Data.GameData.CommonData;

/**
 * the class Information stores the name and a race name for a character
 * 
 * @author Lin Zhu
 * 
 */
public class Information {
	private String name;
	private String fighterType; // 

	/**
	 * Class Information Constructor (No argument)
	 */
	public Information() {
		DiceTool dice = new DiceTool(CommonData.CHARACTER_INFORMATION_NAMES.length);
		this.name = CommonData.CHARACTER_INFORMATION_NAMES[dice.getValue()-1];
		this.fighterType = "UnKnown";
	}

	/**
	 * Class Information Constructor
	 * 
	 * @param nm
	 * @param ra
	 */
	public Information(String nm, String ra) {
		this.name = nm;
		this.fighterType = ra;
	}

	/**
	 * Class Information Constructor
	 * 
	 * @param nm
	 * @param ra
	 */
	public Information(String nm, int ra) {
		this.name = nm;
		switch (ra) {
		case 1:
			this.fighterType = CommonData.FIGHTER_TYPE_BULLY;
			break;
		case 2:
			this.fighterType = CommonData.FIGHTER_TYPE_NIMBLE;
			break;
		case 3:
			this.fighterType = CommonData.FIGHTER_TYPE_TANK;
			break;
		}
	}

	/**
	 * Class Information Constructor
	 * 
	 * @param ra
	 */
	public Information(int ra) {
		DiceTool dice = new DiceTool(CommonData.CHARACTER_INFORMATION_NAMES.length);
		this.name = CommonData.CHARACTER_INFORMATION_NAMES[dice.getValue()-1];
		switch (ra) {
		case 1:
			this.fighterType = CommonData.FIGHTER_TYPE_BULLY;
			break;
		case 2:
			this.fighterType = CommonData.FIGHTER_TYPE_NIMBLE;
			break;
		case 3:
			this.fighterType = CommonData.FIGHTER_TYPE_TANK;
			break;
		default:
			throw new RuntimeException("Error input value! No such a race!");
		}

	}

	/**
	 * get the type of the character
	 * 
	 * @return
	 */
	public int getType() {
		if (this.fighterType.equals( CommonData.FIGHTER_TYPE_BULLY)){
			return 1;
		} else if (this.fighterType.equals(	CommonData.FIGHTER_TYPE_NIMBLE)) {
			return 2;
		} else if (this.fighterType.equals(	CommonData.FIGHTER_TYPE_TANK)) {
			return 3;
		}
		return -1;
	}

	/**
	 * Method for getting the value of member variable name
	 * 
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Method for getting the value of member variable race
	 * 
	 * @return
	 */
	public String getRace() {
		return this.fighterType;
	}

	/**
	 * Method for setting the value of member variable name
	 * 
	 * @param nm
	 */
	public void setName(String nm) {
		this.name = nm;
	}

	/**
	 * Method for setting the value of member variable race
	 * 
	 * @param ra
	 */
	public void setFighterType(String ra) {
		this.fighterType = ra;
	}

}
