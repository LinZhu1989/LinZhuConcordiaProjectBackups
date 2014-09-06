/**
 * @author Lin Zhu (6555659)
 * SOEN 6441 Team Project 
 * Winter 2014
 */
package Data.Character;

import Data.GameData.CommonData;

/**
 * the class Status stores the status for a character
 * 
 * @author Lin Zhu
 * 
 */
public class Status {
	private int basicHP;
	private int currentHP;
	private int MAXHP;
	private int basicMP;
	private int currentMP;
	private int MAXMP;
	private int level;
	private int skillPoint;
	private int abilityPoint;
	private int money;

	/**
	 * Class Information Constructor (No argument)
	 */
	public Status() {
		this.basicHP = 50;
		this.currentHP = basicHP;
		this.MAXHP = basicHP;
		this.basicMP = 50;
		this.currentMP = basicMP;
		this.MAXMP = basicMP;
		this.level = 1;
		this.skillPoint = CommonData.CHARACTER_STATUS_INITIALSKILLPTS;
		this.abilityPoint = CommonData.CHARACTER_STATUS_INITIALABILITYPTS;
		this.money = CommonData.CHARACTER_STATUS_INITIALMONEY;

	}

	public Status(int mst) {
		this.basicHP =  CommonData.MONSTER_MAXHP[mst];
		this.currentHP = CommonData.MONSTER_MAXHP[mst];
		this.MAXHP = CommonData.MONSTER_MAXHP[mst];
		this.basicMP = 0;
		this.currentMP = 0;
		this.MAXMP = 0;
		this.level = mst;
		this.skillPoint = 0;
		this.abilityPoint = 0;
		this.money = CommonData.MONSTER_MONSTERWORTHMONEY[mst];

	}

	/**
	 * Class Information Constructor (With argument)
	 * 
	 * @param bHP
	 * @param bMP
	 * @param ra
	 */
	public Status(int bHP, int bMP, int ra) {
		this.basicHP = bHP;
		this.basicMP = bMP;
		this.MAXHP=this.basicHP;
		this.MAXMP=this.basicMP;
		this.currentHP = MAXHP;
		this.currentMP = MAXMP;
		this.level = 1;
		this.skillPoint = CommonData.CHARACTER_STATUS_INITIALSKILLPTS;
		this.money = CommonData.CHARACTER_STATUS_INITIALMONEY;
		this.abilityPoint = 0;
	}

	/**
	 * Class Information Constructor (With argument)
	 * 
	 * @param bHP
	 * @param bMP
	 * @param ra
	 */
	public Status(int bHP, int bMP, String ra) {
		this.basicHP = bHP;
		this.basicMP = bMP;
		this.currentHP = MAXHP;
		this.currentMP = MAXMP;
		this.level = 1;
		this.skillPoint = CommonData.CHARACTER_STATUS_INITIALSKILLPTS;
		this.money = CommonData.CHARACTER_STATUS_INITIALMONEY;
		this.abilityPoint = 0;
	}

	public void refreshStatus(){
		
	}
	
	
	/**
	 * lose Money with a number
	 * 
	 * @param money
	 * @return
	 */
	public boolean loseMoney(int money) {
		if (money < this.money) {
			return false;
		} else {
			this.money -= money;
			return true;
		}
	}

	/**
	 * gain Money with a number
	 * 
	 * @param money
	 * @return
	 */
	public boolean gainMoney(int money) {
		this.money -= money;
		return true;
	}

	/**
	 * lose skill Pts
	 * 
	 * @param pts
	 * @return
	 */
	public boolean loseSkillPts(int pts) {
		if (pts > this.skillPoint) {
			return false;
		} else {
			this.skillPoint -= pts;
			return true;
		}
	}

	/**
	 * gain skill Pts
	 * 
	 * @param pts
	 * @return
	 */
	public boolean gainSkillPts(int pts) {
		this.skillPoint -= pts;
		return true;
	}

	/**
	 * add ability Pts
	 * 
	 * @param pts
	 * @return
	 */
	public boolean addAbilityPts(int pts) {
		this.abilityPoint += pts;
		return true;

	}

	/**
	 * get Basic value of the status of a character
	 * 
	 * @return
	 */
	public int getBasicHP() {

		return this.basicHP;
	}

	/**
	 * get Basic value of the status of a character
	 * 
	 * @return
	 */
	public int getBasicMP() {
		return this.basicMP;
	}

	/**
	 * get Basic value of the status of a character
	 * 
	 * @return
	 */
	public int getCurrentHP() {
		return this.currentHP;
	}

	/**
	 * get Basic value of the status of a character
	 * 
	 * @return
	 */
	public int getCurrentMP() {
		return this.currentMP;
	}

	/**
	 * get Basic value of the status of a character
	 * 
	 * @return
	 */
	public int getMAXHP() {
		return this.MAXHP;
	}

	/**
	 * get Basic value of the status of a character
	 * 
	 * @return
	 */
	public int getMAXMP() {
		return this.MAXMP;
	}

	/**
	 * get Basic value of the status of a character
	 * 
	 * @return
	 */
	public int getLevel() {
		return this.level;
	}

	/**
	 * get Basic value of the status of a character
	 * 
	 * @return
	 */
	public int getSkillPoint() {
		return this.skillPoint;
	}

	/**
	 * get Basic value of the status of a character
	 * 
	 * @return
	 */
	public int getAbilityPoint() {
		return this.abilityPoint;
	}

	/**
	 * get Basic value of the status of a character
	 * 
	 * @return
	 */
	public int getMoney() {
		return this.money;
	}

	/**
	 * set Basic value of the status of a character
	 * 
	 * @param bHP
	 */
	public void setBasicHP(int bHP) {

		this.basicHP = bHP;
	}

	/**
	 * set Basic value of the status of a character
	 * 
	 * @param bMP
	 */
	public void setBasicMP(int bMP) {
		this.basicMP = bMP;
	}

	/**
	 * set Basic value of the status of a character
	 * 
	 * @param cHP
	 */
	public void setCurrentHP(int cHP) {
		this.currentHP = cHP;
	}

	/**
	 * set Basic value of the status of a character
	 * 
	 * @param cMP
	 */
	public void setCurrentMP(int cMP) {
		this.currentMP = cMP;
	}

	/**
	 * set Basic value of the status of a character
	 * 
	 * @param MHP
	 */
	public void setMAXHP(int MHP) {
		this.MAXHP = MHP;
	}

	/**
	 * set Basic value of the status of a character
	 * 
	 * @param MMP
	 */
	public void setMAXMP(int MMP) {
		this.MAXMP = MMP;
	}

	/**
	 * set Basic value of the status of a character
	 * 
	 * @param lvl
	 */
	public void setLevel(int lvl) {
		this.level = lvl;
	}

	/**
	 * set Basic value of the status of a character
	 * 
	 * @param sp
	 */
	public void setSkillPoint(int sp) {
		this.skillPoint = sp;
	}

	/**
	 * set Basic value of the status of a character
	 * 
	 * @param ap
	 */
	public void setAbilityPoint(int ap) {
		this.abilityPoint = ap;
	}

	/**
	 * set Basic value of the status of a character
	 * 
	 * @param mn
	 */
	public void setMoney(int mn) {
		this.money = mn;
	}

}
