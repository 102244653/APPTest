package TestScript.ScriptManage.Balance;

import AppTool.BaseAction.Action;
import AppTool.Logger.TestLog;
import AppTool.SelfAssert.CheckPoint;
import AppTool.WaitElement.WaitElement;
import TestScript.ElementManage.BalancePage.AccountPage;
import TestScript.ElementManage.BalancePage.TransferAccountPage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import static AppTool.BaseAction.Action.*;

public class CheckAmount {

    public Action AC;
    public WaitElement WE;
    public AndroidDriver driver;
    public CheckAmount(AndroidDriver driver){
        this.AC=new Action(driver);
        this.WE=new WaitElement(driver);
        this.driver=driver;
    }

    //检查总资产
    public void CheckAmount(String amount)throws Exception{
        String value=AC.getText(AccountPage.account);
        if(value.equals("*****")){
            AC.click(AccountPage.eyeimg);
            value=AC.getText(AccountPage.account);
        }
        if(!amount.isEmpty()){
            CheckPoint.checkEqules(amount,value,"AmountError");
        }
    }

    //扫描二维码
    public void ScanTransfer()throws Exception{
        AC.click(AccountPage.scanpic);
    }

    //添加资产
    public void AddCion(String coin)throws Exception{
        AC.click(AccountPage.addaccount);
        if(WE.Eledisplay(AccountPage.addtitle)) {
            AC.click(AccountPage.search);
            AC.sendText(AccountPage.searchtext, coin);
            if(WE.Eledisplay(AccountPage.nullresult)){
                CheckPoint.checkError("未找到匹配结果:"+coin,"NotFind"+coin);
            }else {
                AC.click(By.xpath("//android.widget.TextView[@text='"+coin+"']/../../android.widget.LinearLayout[1]"));
                AC.click(AccountPage.canclesearch);
                AC.click(AccountPage.addback);
            }
        }else {
            TestLog.info("CheckAmount: AddCion",coin+"资产添加失败");
            return;
        }
        CheckPoint.checkEqules(AC.getText("new UiSelector().text('" + coin + "')"),coin,"IsAdd"+coin);
    }

    //查看交易记录
    public void CheckList(String coin)throws Exception{
        if(coin.isEmpty()){
            TestLog.error("CheckAmount: CheckList","查看资产名称不能为空");
            return;
        }
        if(WE.Eledisplay("new UiSelector().text('" + coin + "')")) {
            AC.click("new UiSelector().text('" + coin + "')");
        }else {
            this.AddCion(coin);
            try {
                AC.click("new UiSelector().text('" + coin + "')");
            }catch (Exception e){
                CheckPoint.checkError("要查看的资产不存在，请先添加"+coin,"Is"+coin);
                return;
            }
        }
        if(AC.getText(TransferAccountPage.listtitle).equals(coin)){
            TestLog.info("CheckAmount: CheckList","已找到"+coin+"交易记录");
        }else {
            CheckPoint.checkError("未找到"+coin+"交易记录","IsTransferPage");
        }
    }





}
