/**
 * @author Lin Zhu (6555659)
 * SOEN 6441 Team Project 
 * Winter 2014
 */
package Data.Character;

import java.util.ArrayList;

import Data.GameData.CommonData;
import Data.Magic.Magic;
import Data.Magic.MagicInterface;

/**
 * the class Skills stores the magics for a character
 * 
 * @author Lin Zhu
 * 
 */
public class Skills {

	private ArrayList<MagicInterface> skill;

	/**
	 * Class Information Constructor (No argument)
	 */
	public Skills() {
		this.skill = new ArrayList<MagicInterface>(9);
	}

	/**
	 * Class Information Constructor (with a race type)
	 * 
	 * @param ra
	 */
	public Skills(int ra) {
		this.skill = new ArrayList<MagicInterface>(9);
		switch (ra) {
		case 1:
			for (int i = 0; i < 3; i++) {
				this.skill.add(new Magic(i + 1, CommonData.MAGICNAMES.get(0)[i],
						CommonData.MAGICCOSTS.get(0)[i], 0, CommonData.MAGICTYPES
								.get(0)[i],
						CommonData.MAGIC_SKILLNEEDPOINT[0][0][i]));
			}
			for (int i = 0; i < 3; i++) {
				this.skill.add(new Magic(i + 4, CommonData.MAGICNAMES.get(1)[i],
						CommonData.MAGICCOSTS.get(1)[i], 0, CommonData.MAGICTYPES
								.get(0)[i],
						CommonData.MAGIC_SKILLNEEDPOINT[0][1][i]));
			}
			for (int i = 0; i < 3; i++) {
				this.skill.add(new Magic(i + 7, CommonData.MAGICNAMES.get(2)[i],
						CommonData.MAGICCOSTS.get(2)[i], 0, CommonData.MAGICTYPES
								.get(0)[i],
						CommonData.MAGIC_SKILLNEEDPOINT[0][2][i]));
			}
			;
			break;

		case 2:
			for (int i = 0; i < 3; i++) {
				this.skill.add(new Magic(i + 1, CommonData.MAGICNAMES.get(3)[i],
						CommonData.MAGICCOSTS.get(3)[i], 0, CommonData.MAGICTYPES
								.get(3)[i],
						CommonData.MAGIC_SKILLNEEDPOINT[1][0][i]));
			}
			for (int i = 0; i < 3; i++) {
				this.skill.add(new Magic(i + 4, CommonData.MAGICNAMES.get(4)[i],
						CommonData.MAGICCOSTS.get(4)[i], 0, CommonData.MAGICTYPES
								.get(4)[i],
						CommonData.MAGIC_SKILLNEEDPOINT[1][1][i]));
			}
			for (int i = 0; i < 3; i++) {
				this.skill.add(new Magic(i + 7, CommonData.MAGICNAMES.get(5)[i],
						CommonData.MAGICCOSTS.get(5)[i], 0, CommonData.MAGICTYPES
								.get(5)[i],
						CommonData.MAGIC_SKILLNEEDPOINT[1][2][i]));
			}
			break;
		case 3:
			for (int i = 0; i < 3; i++) {
				this.skill.add(new Magic(i + 1, CommonData.MAGICNAMES.get(6)[i],
						CommonData.MAGICCOSTS.get(6)[i], 0, CommonData.MAGICTYPES
								.get(6)[i],
						CommonData.MAGIC_SKILLNEEDPOINT[2][0][i]));
			}
			for (int i = 0; i < 3; i++) {
				this.skill.add(new Magic(i + 4, CommonData.MAGICNAMES.get(7)[i],
						CommonData.MAGICCOSTS.get(7)[i], 0, CommonData.MAGICTYPES
								.get(7)[i],
						CommonData.MAGIC_SKILLNEEDPOINT[2][1][i]));
			}
			for (int i = 0; i < 3; i++) {
				this.skill.add(new Magic(i + 7, CommonData.MAGICNAMES.get(8)[i],
						CommonData.MAGICCOSTS.get(8)[i], 0, CommonData.MAGICTYPES
								.get(8)[i],
						CommonData.MAGIC_SKILLNEEDPOINT[2][2][i]));
			}
			break;
		}

	}

	/**
	 * Class Information Constructor (with a race type as a string)
	 * 
	 * @param raName
	 */
	public Skills(String raName) {
		this.skill = new ArrayList<MagicInterface>(9);
		int ra = 0;
		if (raName.equalsIgnoreCase( CommonData.FIGHTER_TYPE_BULLY)) {
			ra = 1;
		} else if (raName.equalsIgnoreCase(CommonData.FIGHTER_TYPE_NIMBLE)) {
			ra = 2;
		} else if (raName.equalsIgnoreCase(CommonData.FIGHTER_TYPE_TANK)) {
			ra = 3;
		}
		switch (ra) {
		case 1:
			for (int i = 0; i < 3; i++) {
				this.skill.add(new Magic(i + 1, CommonData.MAGICNAMES.get(0)[i],
						CommonData.MAGICCOSTS.get(0)[i], 0, CommonData.MAGICTYPES
								.get(0)[i],
						CommonData.MAGIC_SKILLNEEDPOINT[0][0][i]));
			}
			for (int i = 0; i < 3; i++) {
				this.skill.add(new Magic(i + 4, CommonData.MAGICNAMES.get(1)[i],
						CommonData.MAGICCOSTS.get(1)[i], 0, CommonData.MAGICTYPES
								.get(0)[i],
						CommonData.MAGIC_SKILLNEEDPOINT[0][1][i]));
			}
			for (int i = 0; i < 3; i++) {
				this.skill.add(new Magic(i + 7, CommonData.MAGICNAMES.get(2)[i],
						CommonData.MAGICCOSTS.get(2)[i], 0, CommonData.MAGICTYPES
								.get(0)[i],
						CommonData.MAGIC_SKILLNEEDPOINT[0][2][i]));
			}
			;
			break;

		case 2:
			for (int i = 0; i < 3; i++) {
				this.skill.add(new Magic(i + 1, CommonData.MAGICNAMES.get(3)[i],
						CommonData.MAGICCOSTS.get(3)[i], 0, CommonData.MAGICTYPES
								.get(3)[i],
						CommonData.MAGIC_SKILLNEEDPOINT[1][0][i]));
			}
			for (int i = 0; i < 3; i++) {
				this.skill.add(new Magic(i + 4, CommonData.MAGICNAMES.get(4)[i],
						CommonData.MAGICCOSTS.get(4)[i], 0, CommonData.MAGICTYPES
								.get(4)[i],
						CommonData.MAGIC_SKILLNEEDPOINT[1][1][i]));
			}
			for (int i = 0; i < 3; i++) {
				this.skill.add(new Magic(i + 7, CommonData.MAGICNAMES.get(5)[i],
						CommonData.MAGICCOSTS.get(5)[i], 0, CommonData.MAGICTYPES
								.get(5)[i],
						CommonData.MAGIC_SKILLNEEDPOINT[1][2][i]));
			}
			break;
		case 3:
			for (int i = 0; i < 3; i++) {
				this.skill.add(new Magic(i + 1, CommonData.MAGICNAMES.get(6)[i],
						CommonData.MAGICCOSTS.get(6)[i], 0, CommonData.MAGICTYPES
								.get(6)[i],
						CommonData.MAGIC_SKILLNEEDPOINT[2][0][i]));
			}
			for (int i = 0; i < 3; i++) {
				this.skill.add(new Magic(i + 4, CommonData.MAGICNAMES.get(7)[i],
						CommonData.MAGICCOSTS.get(7)[i], 0, CommonData.MAGICTYPES
								.get(7)[i],
						CommonData.MAGIC_SKILLNEEDPOINT[2][1][i]));
			}
			for (int i = 0; i < 3; i++) {
				this.skill.add(new Magic(i + 7, CommonData.MAGICNAMES.get(8)[i],
						CommonData.MAGICCOSTS.get(8)[i], 0, CommonData.MAGICTYPES
								.get(8)[i],
						CommonData.MAGIC_SKILLNEEDPOINT[2][2][i]));
			}
			break;
		}

	}

	/**
	 * add a Magic
	 * 
	 * @param mgc
	 */
	public void addMagic(MagicInterface mgc) {
		this.skill.add(mgc);
	}

	/**
	 * get a Magic
	 * 
	 * @param i
	 * @return
	 */
	public MagicInterface getMagic(int i) {
		return this.skill.get(i - 1);
	}

	/**
	 * get the Number Of Magic
	 * 
	 * @return
	 */
	public int getNumberOfMagic() {
		return this.skill.size();
	}

	/**
	 * update a Magic With ID
	 * 
	 * @param i
	 * @return
	 */
	public boolean updateMagicWithID(int i) {
		return this.skill.get(i - 1).updateMagic();
	}

}
