package StartTest;

import AppTool.BaseDriver.AppiumInit;
import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";
    private static String path=System.getProperty("user.dir");

    public static void main(String[] args) throws Exception {
        System.setProperty(ESCAPE_PROPERTY, "false");
        AppiumInit.Init();
        List<String> suites = new ArrayList<String>();
        suites.add(System.getProperty("user.dir")+"/testng.xml");
        TestNG test = new TestNG(false);
        test.setTestSuites(suites);
        test.run();
    }
}
