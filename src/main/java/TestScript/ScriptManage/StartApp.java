package TestScript.ScriptManage;

import AppTool.BaseDriver.AppiumInit;
import AppTool.BaseDriver.SelfDriver;
import org.testng.annotations.*;


public class StartApp extends SelfDriver{

    @BeforeSuite
    public void StartServer() throws Exception {
        AppiumInit.Init();
    }

    @Parameters({"udid","port"})
    @BeforeClass
    public void StartApp(String udid,String port) throws Exception{
        super.InitDriver(udid,port);
    }

    public void InitApp()throws Exception{
        super.getdriver().startActivity("com.seele.tigerwallet","com.seele.tigerwallet.ui.activtiy.WelcomeActivity");
    }

    public void CloseApp()throws Exception{
        super.getdriver().closeApp();
    }

    @AfterSuite
    public void EndTest()throws Exception{
        super.driver.removeApp("com.seele.tigerwallet");
        super.QuietDriver();
    }
}
