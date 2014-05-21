/**
 * @author Xin Shao 
 * SOEN 6441 Team Project 
 * Winter 2014
 */
package D2DTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;

import Data.Character.GameCharacter;
import Data.dataIO.CharacterLoad;
import Data.dataIO.CharacterSave;
import Data.dataIO.MapLoad;
import Data.dataIO.MapSave;
import Data.dataIO.MonsterLoad;
import Data.dataIO.MonsterSave;
import GUI.mapView.MapInfor;

/**
 * 
 *this is the class to test the function of load XML file . 
 */
public class D2DTestLoad {
	MapLoad loader = null;
	MapSave saveMap = null;
	CharacterLoad loadCharacter = null;
	CharacterSave saveCharacter = null;
	MonsterLoad loadMonster = null;
	MonsterSave saveMonster = null;
	@Before
	public void setUp() throws Exception{
		loader = new MapLoad();
		saveMap = new MapSave();
		loadCharacter = new CharacterLoad();
		saveCharacter = new CharacterSave();
		loadMonster = new MonsterLoad();
		saveMonster = new MonsterSave();
		
	}
	
	@Test
	public void testMapLoadResult() {
		MapInfor mp = new MapInfor();
		try{
		loader.load("src/D2DTest/mapData_test.xml",mp);

		assertEquals(1, mp.getRowCount());
		assertEquals(1, mp.getColumnCount());
		assertEquals(20, mp.getBlockWidth());
		assertEquals(40, mp.getBlockHeight());
		assertEquals(0, mp.getPlayerX());
		assertEquals(0, mp.getPlayerY());
		assertEquals(false, mp.isRoleSelectDone());
		//assertEquals(1, mp.getBlock(0, 0).getBlockType());

		}catch(Exception e){
			return ;
		}
		
	}
    @Test
    public void testBuild2saveMap(){
		//assertEquals(saveMap.save(new MapInfor()),false);

    	 
    }
	@Test 
	public void testBuild2saveCharacter(){
		//assertEquals(saveCharacter.saveCharacter(new GameCharacter()),false);
		
	}
	@Test
	public void testBuild2loadMonster(){
		assertEquals(loadMonster.loadMonster(null),null);

	}
	
	@Test
	public void testBuild2saveMonster(){
		//assertEquals(saveMonster.saveMonster(null,null, null),false);
	}
	
	@Test
	public void testBuild2SaveMap(){
		//assertEquals(saveCharacter.saveCharacter(new GameCharacter()),false);

	}

	
	@Test
	public void testCharacterLoad(){
		GameCharacter character = new GameCharacter();
		try{
			loadCharacter.characterLoad("src/D2DTest/characterData_test.xml",character);
		}catch(Exception e){
			return;
		}
		assertEquals(29, character.ability.getStrength());
		assertEquals(1, character.ability.getDexterity());
		assertEquals(28, character.ability.getConstitution());
		assertEquals(6, character.ability.getWisdom());
		assertEquals(9, character.ability.getIntelligence());
		assertEquals(11, character.ability.getCharisma());
		assertEquals(10, character.attribute.getBasicAttack());
		assertEquals(2, character.attribute.getBasicPhysicalArmor());
		assertEquals(0, character.attribute.getBasicMagicResist());
		assertEquals(0, character.attribute.getBasicMoveDistance());
		assertEquals(10, character.attribute.getBasicAttackDistance() );
		
		assertEquals(411, character.status.getCurrentHP());
		assertEquals(411, character.status.getMAXHP());
		assertEquals(50, character.status.getBasicMP());
		assertEquals(104, character.status.getCurrentMP());
		assertEquals(104, character.status.getMAXMP());
		assertEquals(1, character.status.getLevel());
		assertEquals(50, character.status.getSkillPoint());
		assertEquals(6, character.status.getAbilityPoint());
		assertEquals(1000,character.status.getMoney());
		assertEquals(150, character.status.getBasicHP() );
	}
	
	@After
	public void tearDown() throws Exception{
	}
}
