package D2DTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.After;
import org.junit.Before;
import org.junit.rules.ExpectedException;

import GUI.characterView.CharacterGUI;
import GUI.characterView.CharacterResource;
import GUI.mapView.MapInfor;

public class D2DTestGui {
	MapInfor mi = null;
	CharacterGUI hero = null;
	
	@Before
	public void setUp() throws Exception{
		mi = new MapInfor();
		mi.initBlocks(20, 15);
		hero = new CharacterGUI(1,new CharacterResource());
		
	}
	
	@Test
	public void testMapInforInit() {
		assertNotNull(mi.getBlock(19, 14));
	}
	
	@Test
	public void testMapInforException() {
		try {
			mi.setBlockWidth(-1);
			fail("Should have thrown an Exception because width is invalid!");
		} catch (Exception e) {
			assertTrue(e.getMessage().equalsIgnoreCase("blockWidth should larger than 0"));
		}		
	}
	
	@Test
	public void testMapInforChange() {		
		int number = mi.getColumnCount();
		mi.columNumberDecrease();
		assertEquals(number-1,mi.getColumnCount());		
		number = mi.getColumnCount();
		mi.columNumberIncrease();
		assertEquals(number+1,mi.getColumnCount());
	}
	

	@Test
	public void testHeroInforInit() {
		assertEquals(hero.getRole().getFixedStrength(),29);
		
	}
	@Test
	public void testHeroInforChange() {
		int number = hero.getRole().getFixedStrength();
		hero.getRole().ability.addAbility(1, 0, 0, 0, 0, 0);
		hero.getRole().status.addAbilityPts(-1);
		assertEquals(hero.getRole().getFixedStrength(),number+1);
		
	}
	
	
	@After
	public void tearDown() throws Exception{
	}
}
