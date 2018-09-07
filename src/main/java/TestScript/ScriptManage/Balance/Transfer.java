package TestScript.ScriptManage.Balance;

import AppTool.BaseAction.Action;
import AppTool.Logger.TestLog;
import AppTool.SelfAssert.CheckPoint;
import AppTool.WaitElement.WaitElement;
import DataManage.TestData.Data_PayWord;
import TestScript.ElementManage.BalancePage.TransferAccountPage;
import TestScript.ElementManage.MenuPage;
import TestScript.ScriptManage.CreatOrImportWallet.SetPayWord;
import io.appium.java_client.android.AndroidDriver;

import static AppTool.BaseAction.Action.*;

public class Transfer {

    public Action AC;
    public WaitElement WE;
    public AndroidDriver driver;
    public Transfer(AndroidDriver driver){
        this.AC=new Action(driver);
        this.WE=new WaitElement(driver);
        this.driver=driver;
    }

    //转账
    public void TransferCoin(int type,String adress,String value,String notes)throws Exception {
        //判断是否进入转账记录页面
        if(!WE.Eledisplay(TransferAccountPage.listtitle)){
            CheckPoint.checkError("请先进入转账记录界面","IsTransferListPage");
            return;
        }
        AC.click(TransferAccountPage.transfer);
        //是否进入转账界面
        if(WE.Eledisplay(TransferAccountPage.listtitle)){
            AC.sendText(TransferAccountPage.transferadress,adress);
            AC.sendText(TransferAccountPage.transferamount,value);
            AC.sendText(TransferAccountPage.transfernote,notes);
            AC.click(TransferAccountPage.transferbtn);
            new SetPayWord(driver).SetPayWord(Data_PayWord.paywords);
            //输入密码后是否报错
            if(WE.Eledisplay(MenuPage.alterinfo)){
                String text= AC.getText(MenuPage.alterinfo);
                CheckPoint.checkError(text,"TransferFail");
            }
        }else {
            CheckPoint.checkError("请先进入转账界面","IsTransferPage");
        }

    }
}
