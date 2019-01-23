package TestCase;

import Base.TestUnit;
import Server.AndroidXmlAnalytic;
import Server.RunUnitService;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WeChatLogin {
	
	private static RunUnitService runService;
		
	@BeforeTest
	public void stup() {
		TestUnit testunit = AndroidXmlAnalytic.ParseTest("com.tencent.mm","com.tencent.mm.ui.LauncherUI","5.1","A10ABNQ6T3TJ","NewLogin.xml");
		runService = new RunUnitService(testunit);
		System.out.println("------------------【微信登录流程的测试场景点】------------------");
	}
		@Test
	public void case1() throws Exception{
		runService.runCase("case1");
	}
	
	@AfterTest
	public void TearDown(){
		runService.closeApp();
	}
}
