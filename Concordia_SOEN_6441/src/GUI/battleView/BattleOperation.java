/**
 * @author Lin Zhu (6555659)
 * SOEN 6441 Team Project 
 * Winter 2014
 */
package GUI.battleView;

import utility.FightResultType;
import utility.MapBlockType;
import Data.Builder.DiceTool;
import Data.Character.GameCharacter;
import Data.Character.GameFighter;
import Data.Character.GameMonster;
import GUI.mapView.IMapItem;
import GUI.mapView.IMapRole;
import GUI.mapView.MapBlock;
import GUI.mapView.MapMonsterFactory.MapMonster;
import GUI.mapView.MapRoleFactory.MapRole;

/**
 * BattleOperation class handles the Battle Operation
 * 
 * @author Administrator
 * 
 */
public class BattleOperation {

	/**
	 * battle Fight Operation
	 * 
	 * @param attackerBlock
	 * @param defenderBlock
	 * @param bl
	 * @param realFight
	 * @return
	 */
	final static FightResultType battleFightOperation(MapBlock attackerBlock,
			MapBlock defenderBlock, BattleLog bl, boolean realFight) {

		// return FightResultType.FIGHT_KILL;
		MapBlockType attType = attackerBlock.getMapItem().getBlockType();
		MapBlockType defType = defenderBlock.getMapItem().getBlockType();
		bl.setLogInfor("BattleFightOperation!");
		bl.setLogInfor("Attacter Type: " + attType);
		bl.setLogInfor("Defender Type: " + defType);
		if ((!attType.equals(MapBlockType.BLOCK_ROLE) && !attType
				.equals(MapBlockType.BLOCK_MONSTER))
				|| (!defType.equals(MapBlockType.BLOCK_ROLE) && !defType
						.equals(MapBlockType.BLOCK_MONSTER))) {
			bl.setLogInfor("FIGHT_CAN_NOT_HAPPEN!");
			return FightResultType.FIGHT_CAN_NOT_HAPPEN;
		} else {
			IMapItem attacker = attackerBlock.getMapItem();
			IMapItem defender = defenderBlock.getMapItem();
			bl.setLogInfor("CAN FIGHT!");
			return attackOperation(attacker, defender, bl, realFight);
		}

	}

	/**
	 * attack Operation
	 * 
	 * @param attacker
	 * @param defender
	 * @param bl
	 * @param realFight
	 * @return
	 */
	private static FightResultType attackOperation(IMapItem attacker,
			IMapItem defender, BattleLog bl, boolean realFight) {

		bl.setLogInfor("Attack Operation!");
		DiceTool dice20 = new DiceTool(20);
		DiceTool dice8 = new DiceTool(8);
		GameFighter attFighter = getGameFighter(attacker);
		GameFighter defFighter = getGameFighter(defender);

		int attTimes = attFighter.getAttackBonus().length;
		int timers = 0;
		bl.setLogInfor("Attacker can attack " + attTimes + " Time(s)");
		boolean attack = false;
		while (timers < attTimes) {
			int timer=timers;
			timers++;
			bl.setLogInfor("Try No." + (timer + 1) + " Attack");
			bl.setLogInfor("Check if can atteck...");
			bl.setLogInfor("AttackBonus is "
					+ attFighter.getAttackBonus(bl)[timer]);
			bl.setLogInfor("Throw a d20 dice...");
			int attValue = attFighter.getAttackBonus()[timer]
					+ dice20.getValue(bl);
			bl.setLogInfor("Result is: " + attValue);
			if (!realFight) {
				if (attValue >= -100) {
					attack = true;
					bl.setLogInfor("This is not a real attack!");
					bl.setLogInfor("You can kill the enemy by one atteck!");
					bl.setLogInfor("Attack " + (timer + 1) + " Successfully");
					defFighter.loseHP(10000000);
					if (checkDeath(defFighter)) {
						bl.setLogInfor("Defender is dead");
						return FightResultType.FIGHT_KILL;
					}

				} else {
					continue;
				}

			} else {
				bl.setLogInfor("Defender's armor class is: "
						+ defFighter.getArmorClass());
				if (attValue >= defFighter.getArmorClass()) {
					attack = true;
					bl.setLogInfor("Attack Value " + attValue
							+ " >= Defender Armor Class "
							+ defFighter.getArmorClass());

					bl.setLogInfor("Attack " + (timer + 1) + " Successfully");
					defFighter.loseHP(attFighter.getAttackDamage(bl));
					bl.setLogInfor("Attack " + (timer + 1) + " Successfully");
					if (checkDeath(defFighter)) {
						bl.setLogInfor("Defender is dead");
						return FightResultType.FIGHT_KILL;
					}

				} else {
					bl.setLogInfor("Attack Value " + attValue
							+ " < Defender Armor Class "
							+ defFighter.getArmorClass());
					bl.setLogInfor("Attack " + (timer + 1) + " Unsuccessfully");
					continue;
				}

			}

		}
		if (!attack) {
			bl.setLogInfor("Never Attack Successfully!");
			return FightResultType.FIGHT_LOSE;
		} else {
			bl.setLogInfor("Attack Successfully!");
			return FightResultType.FIGHT_WIN;
		}

	}

	/**
	 * check if the defender dead
	 * 
	 * @param defFighter
	 * @return
	 */
	private static boolean checkDeath(GameFighter defFighter) {
		return defFighter.checkDeath();
	}

	/**
	 * get GameFighter
	 * @param item
	 * @return
	 */
	private static GameFighter getGameFighter(IMapItem item) {
		if (item instanceof IMapRole) {
			return (GameFighter) ((MapRole) item).getGameCharacter();
		} else {
			return (GameFighter) ((MapMonster) item).getGameMonster();
		}
	}

}
