package Data.Factory;
/**
 * @author Jingang.Li
 * SOEN 6441 Team Project 
 * Winter 2014
 */
import utility.MonsterType;
import utility.RoleType;
import Data.Character.GameMonster;
import GUI.mapView.IMapRole;
import GUI.mapView.MapBlock;
import GUI.mapView.MapRoleFactory.MapRole;

public class MonsterFactory {
	private MonsterType monsterType = null;

	public MonsterFactory(MonsterType type) {
		this.monsterType = type;
	}


	public GameMonster getMonster() {
		switch (monsterType) {
		case MONSTER_1:
			return new GameMonster(1);
		case MONSTER_2:
			return new GameMonster(2);
		case MONSTER_3:
			return new GameMonster(3);
		case MONSTER_4:
			return new GameMonster(4);
		case MONSTER_5:
			return new GameMonster(5);
		case MONSTER_6:
			return new GameMonster(6);
		case MONSTER_7:
			return new GameMonster(7);
		case MONSTER_8:
			return new GameMonster(8);
		case MONSTER_9:
			return new GameMonster(9);
		case MONSTER_10:
			return new GameMonster(10);
		default:
			return new GameMonster(0);
		}

	}



}
