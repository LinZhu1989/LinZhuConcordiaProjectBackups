package D2DTest;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import utility.FixedItemType;
import Data.ActionHelper.AttackHelper;
import Data.ActionHelper.MovableTarget;
import Data.ActionHelper.MoveHelper;
import Data.Builder.*;
import Data.Character.Ability;
import Data.Character.Attribute;
import Data.Character.GameCharacter;
import Data.Character.GameFixedItem;
import Data.Character.GameMonster;
import Data.Character.Information;
import Data.Character.Skills;
import Data.GameData.CommonData;
import Data.Item.ArmorType;
import Data.Item.Item_Armor;
import Data.Item.Item_Shield;
import Data.Item.Item_Weapons;
import Data.Item.ShieldType;
import GUI.mapView.MapInfor;

public class D2DTestData {
	private static final String WeaponsType = null;
	MapInfor tempMap = null;
	MoveHelper testMoveHelper = null;
	AttackHelper testAttackHelper = null;
	Ability testAbi = null;
	Attribute testAttr = null;
	Skills testSkill = null;
	GameCharacter testRole = null;
	GameMonster testMonster = null;
	CommonData comData = null;
	CharacterDirector characterDirector = null;
	GameFixedItem gameFixedItem =null;

	@Before
	public void setUp() throws Exception {
		tempMap = new MapInfor();
		testMoveHelper = new MoveHelper();
		testAttackHelper = new AttackHelper();
		testAbi = new Ability();
		testAttr = new Attribute();
		testSkill = new Skills(1);
		testRole = new GameCharacter();
		testMonster = new GameMonster();
		comData = new CommonData();
		characterDirector = new CharacterDirector();
		gameFixedItem = new GameFixedItem(FixedItemType.MERCHANT);
	}

	@Test
	public void testMovementCalculationAlgorithm() {
		tempMap.initBlocks(15, 20);
		tempMap.setPlayerX(0);
		tempMap.setPlayerY(0);
		tempMap.setAllBlockMovable();
		ArrayList<MovableTarget> targets = testMoveHelper.calMoveBlocks(
				tempMap, 2);
		assertEquals(targets.size(), 5);
		// Check the movable blocks using depth first search
		assertEquals(targets.get(0).getTarget().x, 1);
		assertEquals(targets.get(0).getTarget().y, 0);
		assertEquals(targets.get(1).getTarget().x, 2);
		assertEquals(targets.get(1).getTarget().y, 0);
		assertEquals(targets.get(2).getTarget().x, 1);
		assertEquals(targets.get(2).getTarget().y, 1);
		assertEquals(targets.get(3).getTarget().x, 0);
		assertEquals(targets.get(3).getTarget().y, 1);
		assertEquals(targets.get(4).getTarget().x, 0);
		assertEquals(targets.get(4).getTarget().y, 2);
	}

	@Test
	public void testMovementCalculationAlgorithm2() {
		tempMap.initBlocks(15, 20);
		tempMap.setPlayerX(0);
		tempMap.setPlayerY(0);
		tempMap.setAllBlockMovable();
		ArrayList<MovableTarget> targets = testMoveHelper.calMoveBlocks(
				tempMap, 1);
		assertEquals(targets.size(), 2);
		// Check the movable blocks using depth first search
		assertEquals(targets.get(0).getTarget().x, 1);
		assertEquals(targets.get(0).getTarget().y, 0);
		assertEquals(targets.get(1).getTarget().x, 0);
		assertEquals(targets.get(1).getTarget().y, 1);

	}

	@Test
	public void testAttackCalculationAlgorithm() {
		tempMap.initBlocks(15, 20);
		tempMap.setPlayerX(0);
		tempMap.setPlayerY(0);
		tempMap.setAllBlockMovable();
		ArrayList<Point> targets = testAttackHelper.calAttackBlocks(tempMap,
				tempMap.getPlayerLocation(), true);
		assertEquals(targets, null);
	}

	@Test
	public void testAttackCalculationRangeProblem() {
		tempMap.initBlocks(1, 1);
		tempMap.setPlayerX(0);
		tempMap.setPlayerY(0);
		tempMap.setAllBlockMovable();
		ArrayList<Point> targets = testAttackHelper.calAttackBlocks(tempMap,
				tempMap.getPlayerLocation(), true);
		assertEquals(targets, null);
	}

	@Test
	public void testMovementCalculationRangeProblem() {
		tempMap.initBlocks(1, 1);
		tempMap.setPlayerX(0);
		tempMap.setPlayerY(0);
		tempMap.setAllBlockMovable();
		ArrayList<MovableTarget> targets = testMoveHelper.calMoveBlocks(
				tempMap, 10);
		assertEquals(targets.size(), 0); // Means no where can move,out of range
	}

	@Test
	public void testMovementCalculationWallProblem() {
		tempMap.initBlocks(99, 99);
		tempMap.setPlayerX(49);
		tempMap.setPlayerY(49);
		tempMap.setAllBlockIsWall();
		ArrayList<MovableTarget> targets = testMoveHelper.calMoveBlocks(
				tempMap, 50);
		assertEquals(targets.size(), 0); // Means no where can move(all wall)
	}

	@Test
	public void testAbilitySetNagetiveValues() {

		assertFalse(testAbi.setCharisma(-1)); // Should return false
		assertFalse(testAbi.setConstitution(-1)); // Should return false
		assertFalse(testAbi.setDexterity(-1)); // Should return false
		assertFalse(testAbi.setIntelligence(-1)); // Should return false
		assertFalse(testAbi.setStrength(-1)); // Should return false
		assertFalse(testAbi.setWisdom(-1)); // Should return false
	}

	@Test
	public void testAbilityInitialWithNagetiveException() {
		try {
			testAbi = new Ability(-1, 0, 0, 0, 0, 0);
			fail("Should have thrown an Exception because input is invalid!");
		} catch (Exception e) {
			assertTrue(e.getMessage().equalsIgnoreCase(
					"Error input value! Negative is not acceptable!"));
		}
	}

	@Test
	public void testAttributeSetNagetiveValues() {

		assertFalse(testAttr.setBasicAttack(-1)); // Should return false
		assertFalse(testAttr.setBasicAttackDistance(-1)); // Should return false
		assertFalse(testAttr.setBasicMagicResist(-1)); // Should return false
		assertFalse(testAttr.setBasicMoveDistance(-1)); // Should return false
		assertFalse(testAttr.setBasicPhysicalArmor(-1)); // Should return false
	}

	@Test
	public void testMagicSetLevelWithNagetiveValues() {
		// Should all return false
		assertFalse(testSkill.getMagic(1).setMagicLevel(-1));
		assertFalse(testSkill.getMagic(2).setMagicLevel(-1));
		assertFalse(testSkill.getMagic(3).setMagicLevel(-1));
		assertFalse(testSkill.getMagic(4).setMagicLevel(-1));
		assertFalse(testSkill.getMagic(5).setMagicLevel(-1));
		assertFalse(testSkill.getMagic(6).setMagicLevel(-1));
		assertFalse(testSkill.getMagic(7).setMagicLevel(-1));
		assertFalse(testSkill.getMagic(8).setMagicLevel(-1));
		assertFalse(testSkill.getMagic(9).setMagicLevel(-1));
	}

	@Test
	public void testMagicUpdateWithMaxLevel() {
		testSkill.getMagic(1).setMagicLevel(3);
		testSkill.getMagic(2).setMagicLevel(3);
		testSkill.getMagic(3).setMagicLevel(3);
		testSkill.getMagic(4).setMagicLevel(3);
		testSkill.getMagic(5).setMagicLevel(3);
		testSkill.getMagic(6).setMagicLevel(3);
		testSkill.getMagic(7).setMagicLevel(3);
		testSkill.getMagic(8).setMagicLevel(3);
		testSkill.getMagic(9).setMagicLevel(3);

		assertFalse(testSkill.getMagic(1).updateMagic());
		assertFalse(testSkill.getMagic(2).updateMagic());
		assertFalse(testSkill.getMagic(3).updateMagic());
		assertFalse(testSkill.getMagic(4).updateMagic());
		assertFalse(testSkill.getMagic(5).updateMagic());
		assertFalse(testSkill.getMagic(6).updateMagic());
		assertFalse(testSkill.getMagic(7).updateMagic());
		assertFalse(testSkill.getMagic(8).updateMagic());
		assertFalse(testSkill.getMagic(9).updateMagic());
	}

	@Test
	public void testMagicUpdateWithUnMaximunLevel() {
		testSkill.getMagic(1).setMagicLevel(1);
		testSkill.getMagic(2).setMagicLevel(1);
		testSkill.getMagic(3).setMagicLevel(1);
		testSkill.getMagic(4).setMagicLevel(1);
		testSkill.getMagic(5).setMagicLevel(1);
		testSkill.getMagic(6).setMagicLevel(1);
		testSkill.getMagic(7).setMagicLevel(1);
		testSkill.getMagic(8).setMagicLevel(1);
		testSkill.getMagic(9).setMagicLevel(1);

		assertTrue(testSkill.getMagic(1).updateMagic());
		assertTrue(testSkill.getMagic(2).updateMagic());
		assertTrue(testSkill.getMagic(3).updateMagic());
		assertTrue(testSkill.getMagic(4).updateMagic());
		assertTrue(testSkill.getMagic(5).updateMagic());
		assertTrue(testSkill.getMagic(6).updateMagic());
		assertTrue(testSkill.getMagic(7).updateMagic());
		assertTrue(testSkill.getMagic(8).updateMagic());
		assertTrue(testSkill.getMagic(9).updateMagic());
	}

	@Test
	public void testCharacterAttributesNotNull() {
		assertNotNull(testRole.ChaInfo);
		assertNotNull(testRole.ability);
		assertNotNull(testRole.attribute);
		assertNotNull(testRole.status);
		assertNotNull(testRole.bag);
		assertNotNull(testRole.equipment);
		assertNotNull(testRole.skill);
	}

	@Test
	public void testMonsterAttributesNotNull() {
		assertNotNull(testMonster.ability);
		assertNotNull(testMonster.attribute);
		assertNotNull(testMonster.status);
	}
	@Test
	public void testMonsterDeath() {
		testMonster.status.setCurrentHP(0);
		assertTrue(testMonster.checkDeath());
		testMonster.status.setCurrentHP(1);
		assertTrue(!testMonster.checkDeath());
	}
	


	@Test
	public void testBagCorrectness() {
		assertNotNull(testRole.bag.getItems());
		assertTrue(testRole.bag.getTotalNumber() > 0);
		assertTrue(testRole.bag.getTotalWeight() > 0);
		for (int i = 0; i < testRole.bag.getItems().size(); i++) {
			assertNotNull(testRole.bag.getItems().get(i));
		}
	}

	@Test
	public void testEquipmentCorrectness() {
		assertNotNull(testRole.equipment.getEquipments());
		assertNotNull(testRole.equipment.getArmor());
		assertNotNull(testRole.equipment.getBelt());
		assertNotNull(testRole.equipment.getBoots());
		assertNotNull(testRole.equipment.getHelmet());
		assertNotNull(testRole.equipment.getRing());
		assertNotNull(testRole.equipment.getWeapons());
	}

	@Test
	public void testChaInfoCorrectness() {
		assertNotSame(testRole.ChaInfo.getName(), "UnKnown");
		assertSame(testRole.ChaInfo.getRace(), "UnKnown");
	}

	@Test
	public void testInformationCorrectness() {
		testRole.ChaInfo = new Information(1);
		assertSame(testRole.ChaInfo.getRace(), "Bully");
		testRole.ChaInfo = new Information(2);
		assertSame(testRole.ChaInfo.getRace(), "Nimble");
		testRole.ChaInfo = new Information(3);
		assertSame(testRole.ChaInfo.getRace(), "Tank");
		testRole.ChaInfo = new Information();
	}

	@Test
	public void testSkillsCorrectness() {
		testRole = new GameCharacter(1);
		assertTrue(testRole.skill.getNumberOfMagic() == 9);
		assertTrue(testRole.skill.getMagic(1).getMagicLevel() == 0);
		assertTrue(testRole.skill.getMagic(2).getMagicLevel() == 0);
		assertTrue(testRole.skill.getMagic(3).getMagicLevel() == 0);
		assertTrue(testRole.skill.getMagic(4).getMagicLevel() == 0);
		assertTrue(testRole.skill.getMagic(5).getMagicLevel() == 0);
		assertTrue(testRole.skill.getMagic(6).getMagicLevel() == 0);
		assertTrue(testRole.skill.getMagic(7).getMagicLevel() == 0);
		assertTrue(testRole.skill.getMagic(8).getMagicLevel() == 0);
		assertTrue(testRole.skill.getMagic(9).getMagicLevel() == 0);
	}

	@Test
	public void testSkillsUpdateCorrectness() {
		testRole = new GameCharacter(1);
		testRole.skill.getMagic(1).updateMagic();
		testRole.skill.getMagic(2).updateMagic();
		testRole.skill.getMagic(3).updateMagic();
		testRole.skill.getMagic(4).updateMagic();
		testRole.skill.getMagic(5).updateMagic();
		testRole.skill.getMagic(6).updateMagic();
		testRole.skill.getMagic(7).updateMagic();
		testRole.skill.getMagic(8).updateMagic();
		testRole.skill.getMagic(9).updateMagic();
		assertTrue(testRole.skill.getMagic(1).getMagicLevel() == 1);
		assertTrue(testRole.skill.getMagic(2).getMagicLevel() == 1);
		assertTrue(testRole.skill.getMagic(3).getMagicLevel() == 1);
		assertTrue(testRole.skill.getMagic(4).getMagicLevel() == 1);
		assertTrue(testRole.skill.getMagic(5).getMagicLevel() == 1);
		assertTrue(testRole.skill.getMagic(6).getMagicLevel() == 1);
		assertTrue(testRole.skill.getMagic(7).getMagicLevel() == 1);
		assertTrue(testRole.skill.getMagic(8).getMagicLevel() == 1);
		assertTrue(testRole.skill.getMagic(9).getMagicLevel() == 1);
	}

	@Test
	public void testCharacterWrongRaceException() {
		try {
			testRole = new GameCharacter(4);
			fail("Should have thrown an Exception because input is invalid!");
		} catch (Exception e) {
			assertTrue(e.getMessage().equalsIgnoreCase(
					"Error input value! No such a race!"));
		}
	}

	@Test
	public void testCommonAbilityDataCorrectness() {
		assertTrue(CommonData.CHARACTER_ABILITY_RACEBASICS.length == 3);
		assertTrue(CommonData.CHARACTER_ABILITY_RACEBASICS[0].length == 6);
		assertTrue(CommonData.CHARACTER_ABILITY_EXTRASTRENGTHHP >= 0);
		assertTrue(CommonData.CHARACTER_ABILITY_EXTRAINTELLIGENCEMP >= 0);
		assertTrue(CommonData.CHARACTER_ABILITY_EXTRADEXTERITYPHYSICALARMOR >= 0);
		assertTrue(CommonData.CHARACTER_ABILITY_EXTRACONSTITUTIONHP >= 0);
	}

	@Test
	public void testCommonAttributeDataDEFAULTCorrectness() {

		assertTrue(CommonData.DEFAULT_CHARACTER_ATTRIBUTE_BASICATK >= 0);
		assertTrue(CommonData.DEFAULT_CHARACTER_ATTRIBUTE_BASICPHYSICALARMOR >= 0);
		assertTrue(CommonData.DEFAULT_CHARACTER_ATTRIBUTE_BASICMAGICRESIST >= 0);
		assertTrue(CommonData.DEFAULT_CHARACTER_ATTRIBUTE_BASICMOVEDISTANCE >= 0);
		assertTrue(CommonData.DEFAULT_CHARACTER_ATTRIBUTE_BASICATTACKDISTANCE >= 0);

	}

	@Test
	public void testCommonAttributeDataCorrectness2() {

		assertTrue(CommonData.CHARACTER_ATTRIBUTE_BASICATK >= 0);
		assertTrue(CommonData.CHARACTER_ATTRIBUTE_BASICPHYSICALARMOR.length == 3);
		assertTrue(CommonData.CHARACTER_ATTRIBUTE_BASICMAGICRRESIST.length == 3);
		assertTrue(CommonData.CHARACTER_ATTRIBUTE_BASICMOVEDISTANCE.length == 3);
		assertTrue(CommonData.CHARACTER_ATTRIBUTE_BASICATTACKDISTANCE.length == 3);
	}

	@Test
	public void testCommonStatusDataCorrectness() {

		assertTrue(CommonData.CHARACTER_STATUS_INITIALSKILLPTS >= 0);
		assertTrue(CommonData.CHARACTER_STATUS_INITIALABILITYPTS >= 0);
		assertTrue(CommonData.CHARACTER_STATUS_INITIALMONEY >= 0);
		assertTrue(CommonData.CHARACTER_STATUS_SKILLPOINTSPERLEVEL > 0);
		assertTrue(CommonData.CHARACTER_STATUS_RACEBASICHP.length == 3);
		assertTrue(CommonData.CHARACTER_STATUS_RACEBASICMP.length == 3);
		assertTrue(CommonData.CHARACTER_STATUS_EXPLIST.length == 29);
	}

	@Test
	public void testCommonMagicDataCorrectness() {

		assertTrue(CommonData.MAGIC_SKILLNEEDPOINT.length == 3);
		assertTrue(CommonData.MAGIC_SKILLNEEDPOINT[0].length == 3);
		assertTrue(CommonData.MAGIC_SKILLNEEDPOINT[0][0].length == 3);
		assertTrue(CommonData.MAGIC_SKILLNEEDPOINT[0][0][0].length == 3);

	}

	@Test
	public void testCharacterBullyDirector() {

		characterDirector.setCharacterBuilder(new BullyBuilder());
		characterDirector.constructCharacter();
		assertSame(characterDirector.getGameCharacter().ChaInfo.getRace(),
				"Bully");

	}

	
	@Test
	public void testMERCHANT() {
		gameFixedItem = new GameFixedItem(FixedItemType.MERCHANT);
		assertSame(gameFixedItem.getFixedItemType(),FixedItemType.MERCHANT);
		assertSame(gameFixedItem.getFixedItem().size(),53);
	}
	
	@Test
	public void testCHEST() {
		gameFixedItem = new GameFixedItem(FixedItemType.CHEST);
		assertSame(gameFixedItem.getFixedItemType(),FixedItemType.CHEST);
		assertSame(gameFixedItem.getFixedItem().size(),3);
	}

	@Test
	public void testCharacterNimbleDirector() {

		characterDirector.setCharacterBuilder(new NimbleBuilder());
		characterDirector.constructCharacter();
		assertSame(characterDirector.getGameCharacter().ChaInfo.getRace(),
				"Nimble");

	}

	@Test
	public void testCharacterTankDirector() {

		characterDirector.setCharacterBuilder(new TankBuilder());
		characterDirector.constructCharacter();
		assertSame(characterDirector.getGameCharacter().ChaInfo.getRace(),
				"Tank");

	}

	@Test
	public void testNewItemArmor() {

		Item_Armor armor1 = new Item_Armor(1, " ", 1, 1, new int[15]);
		Item_Armor armor2 = new Item_Armor(2, " ", 1, 1, new int[15]);
		Item_Armor armor3 = new Item_Armor(3, " ", 1, 1, new int[15]);
		Item_Armor armor4 = new Item_Armor(4, " ", 1, 1, new int[15]);
		Item_Armor armor5 = new Item_Armor(5, " ", 1, 1, new int[15]);
		Item_Armor armor6 = new Item_Armor(6, " ", 1, 1, new int[15]);
		Item_Armor armor7 = new Item_Armor(7, " ", 1, 1, new int[15]);
		Item_Armor armor8 = new Item_Armor(8, " ", 1, 1, new int[15]);

		assertSame(armor1.getType(), ArmorType.PADDED);
		assertSame(armor2.getType(), ArmorType.LEATHER);
		assertSame(armor3.getType(), ArmorType.STUDDED_LEATHER);
		assertSame(armor4.getType(), ArmorType.CHAIN_SHIRT);
		assertSame(armor5.getType(), ArmorType.BREAST_PLATE);
		assertSame(armor6.getType(), ArmorType.BANDED_MAIL);
		assertSame(armor7.getType(), ArmorType.HALF_PLATE);
		assertSame(armor8.getType(), ArmorType.FULL_PLATE);

		armor1 = null;
		armor2 = null;
		armor3 = null;
		armor4 = null;
		armor5 = null;
		armor6 = null;
		armor7 = null;
		armor8 = null;

	}

	@Test
	public void testNewItemShield() {

		Item_Shield shield1 = new Item_Shield(1, " ", 1, 1, new int[15]);
		Item_Shield shield2 = new Item_Shield(2, " ", 1, 1, new int[15]);
		Item_Shield shield3 = new Item_Shield(3, " ", 1, 1, new int[15]);

		assertSame(shield1.getType(), ShieldType.BUCKLER);
		assertSame(shield2.getType(), ShieldType.HEAVY_SHIELD);
		assertSame(shield3.getType(), ShieldType.TOWER_SHIELD);

		shield1 = null;
		shield2 = null;
		shield3 = null;

	}
	
	@Test
	public void testNewItemWeapons() {

		Item_Weapons weapons1 = new Item_Weapons(1, " ", 1, 1, new int[15]);
		Item_Weapons weapons2 = new Item_Weapons(2, " ", 1, 1, new int[15]);
		Item_Weapons weapons3 = new Item_Weapons(3, " ", 1, 1, new int[15]);

		assertSame(weapons1.getType(), Data.Item.WeaponsType.SWORD);
		assertSame(weapons2.getType(), Data.Item.WeaponsType.BOW);
		assertSame(weapons3.getType(), Data.Item.WeaponsType.NOWEAPONS);

		weapons1 = null;
		weapons2 = null;
		weapons3 = null;

	}

	@After
	public void tearDown() throws Exception {
		tempMap = null;
		testMoveHelper = null;
		testAttackHelper = null;
		testAbi = null;
		testAttr = null;
		testSkill = null;
		testRole = null;
		testMonster = null;
		comData = null;
		characterDirector = null;
		gameFixedItem =null;
		System.gc();
	}
}
