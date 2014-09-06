package Data.Character;

import GUI.battleView.BattleLog;

public interface GameFighter {

	public int getStrengthModifier();
	public int getDexterityModifier();
	public int getConstitutionModifier();
	public int getWisdomModifier();
	public int getIntelligenceModifier();
	public int getCharismaModifier();
	public int getArmorClass();
	public int getArmorModifier();
	public int getShieldModifier();
	public int getAttackDamage(BattleLog bl);
	public int[] getAttackBonus(BattleLog bl);
	public int[] getAttackBonus();
	public boolean updateLevelTo(int targetLevel,BattleLog bl);
	public boolean levelUpByOne(BattleLog bl);
	public boolean levelUpByMulti(int levels,BattleLog bl);
	public void refreshGameFighter();
	public boolean checkDeath();
	public void loseHP(int attackDamage);

	
}
