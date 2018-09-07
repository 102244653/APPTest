package TestScript.ScriptManage.CreatOrImportWallet;

import AppTool.BaseAction.Action;
import AppTool.Logger.TestLog;
import AppTool.SelefTools.Time;
import AppTool.SelfAssert.CheckPoint;
import AppTool.SelfAssert.SelfAssert;
import AppTool.WaitElement.WaitElement;
import TestScript.ElementManage.CreatOrImportPage.ImportWordPage;
import TestScript.ElementManage.CreatOrImportPage.PayWordPage;
import TestScript.ElementManage.MenuPage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import static AppTool.BaseAction.Action.*;

public class SetPayWord {
    public Action AC;
    public WaitElement WE;
    public AndroidDriver driver;
    public SetPayWord(AndroidDriver driver){
        this.AC=new Action(driver);
        this.WE=new WaitElement(driver);
        this.driver=driver;
    }

    public void SetPayWord(int[] payword){
       // CheckPoint.checkEqules(getText(PayWordPage.pagetitle),"请输入密码","IsSetPay");
        if(!AC.getText(PayWordPage.pagetitle).equals("请输入密码")){
            TestLog.error("SetPayWord: SetPayWord","未找到密码界面");
            return;
        }
        for(int i=0;i<payword.length;i++){
            By key=By.id("com.seele.tigerwallet:id/btn_key"+payword[i]);
            AC.click(key);
        }
        AC.click(PayWordPage.queren);
    }

    public void ConfirmPayWord(int[] payword){
        for(int i=0;i<payword.length;i++){
            By key=By.id("com.seele.tigerwallet:id/btn_key"+payword[i]);
            AC.click(key);
        }
        AC.click(PayWordPage.queren);
        for(int i=0;i<30;i++){
            if(!WE.findE(MenuPage.loading)){
                break;
            }else {
                Time.sleep(1000);
            }
        }
        if(WE.Eledisplay(MenuPage.alterinfo)){
            String text=AC.getText(MenuPage.alterinfo);
            SelfAssert.assertTrue(false,text);
        }
        SelfAssert.assertTrue(true);
    }
}
