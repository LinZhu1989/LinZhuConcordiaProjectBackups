package D2DTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ D2DTestData.class, 
	            D2DTestZone.class, 
	            D2DTestLoad.class,
	            D2DTestGui.class})
public class D2DTest {

} 

