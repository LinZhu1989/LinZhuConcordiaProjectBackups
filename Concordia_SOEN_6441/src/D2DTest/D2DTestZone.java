package D2DTest;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import utility.TextZone;
import utility.Zone;


public class D2DTestZone {	

	Zone z = null;
	TextZone tz = null;
	
	@Before
	public void setUp() throws Exception {
		z = new Zone();
		tz = new TextZone(new Point (10,10),20,30, z, "Hello Button");
		z.addZone(tz);
	}

	@After
	public void tearDown() throws Exception {
		z = null;
	}

	@Test
	public void testZone() {
		assertEquals(0, z.getHeight());
		assertEquals(0, z.getWidth());
		
		z.setHeight(-1);
		assertEquals(0,z.getHeight());
		z.setWidth(-1);
		assertEquals(0,z.getWidth());
		
		Point pt = new Point(0,0);
		z.setLocation(pt);
		assertEquals(pt,z.getLocation());
	}
	
	@Test 
	public void testTextZone() {
		
		assertEquals(20, tz.getWidth());
		assertEquals(30, tz.getHeight());
		assertEquals("", tz.getText());
		
		Point pt = new Point(100,100);
		tz.setLocation(pt);
		
		MouseEvent e = new MouseEvent(new JPanel(), 0, 0, 0, 111,111,1, false);
		assertSame(tz, tz.HitTest(e));
	}

}
