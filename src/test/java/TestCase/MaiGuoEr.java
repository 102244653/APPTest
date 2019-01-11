package TestCase;

import Base.TestUnit;
import Server.AndroidXmlAnalytic;
import Server.RunUnitService;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MaiGuoEr {
	
	private static RunUnitService runService;
		
	@BeforeTest
	private void stup() throws Exception{
		TestUnit testunit = AndroidXmlAnalytic.ParseTest("com.android.maiguo.activity","com.android.maiguo.activity.MainActivity","5.1","A10ABNQ6T3TJ","MGE.xml");
		runService = new RunUnitService(testunit);
	}
	
	@Test
	public void case1() throws Exception{
		runService.runCase("Login");
	}

	@Test
	public void case2() throws Exception{
		runService.runCase("Logout");
	}
	
	@AfterTest
	public void TearDown(){
		runService.closeApp();
	}
}
