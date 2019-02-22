package TestCase;

import Server.InitHtmlReport;
import org.testng.annotations.AfterClass;

public class CreatHtmlReport {
    @AfterClass
    public void report(){
        try {
            new InitHtmlReport().CreatHtmlReport();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
