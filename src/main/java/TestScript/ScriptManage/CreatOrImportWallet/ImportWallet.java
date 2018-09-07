package TestScript.ScriptManage.CreatOrImportWallet;

import AppTool.BaseAction.Action;
import AppTool.Logger.TestLog;
import AppTool.SelefTools.Time;
import AppTool.SelfAssert.SelfAssert;
import AppTool.WaitElement.WaitElement;
import TestScript.ElementManage.CreatOrImportPage.CreatWordsPage;
import TestScript.ElementManage.CreatOrImportPage.FristPage;
import TestScript.ElementManage.CreatOrImportPage.ImportWordPage;
import TestScript.ElementManage.CreatOrImportPage.SelectPage;
import TestScript.ElementManage.MenuPage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class ImportWallet {

    public Action AC;
    public WaitElement WE;
    public AndroidDriver driver;
    public ImportWallet(AndroidDriver driver){
        this.AC=new Action(driver);
        this.WE=new WaitElement(driver);
        this.driver=driver;
    }

    //确认钱包使用说明
    public void ConfirmNote() throws Exception {
        Time.sleep(1000);
        AC.click(FristPage.agree);
    }


    //导入钱包
    public void SelectImport()  throws Exception{
        AC.click(SelectPage.importW);
    }

    //点击什么是助记词
    public void WhatWords() throws Exception{
        AC.click(CreatWordsPage.whatWord);
    }

    //填写助记词
    public void ImportWords(String[] words)throws Exception{
        if(words.length!=12){
            TestLog.error("ImportWallet: ImportWords","助记词个数错误，请检查助记词！");
            return;
        }
        for(int i=0;i<words.length;i++) {
            By key=By.id("com.seele.tigerwallet:id/et_show"+i);
            AC.sendText(key,words[i]);
        }
        AC.click(ImportWordPage.importWallet);
        for(int i=0;i<30;i++){
            if(!WE.findE(MenuPage.loading)){
                break;
            }else {
                Time.sleep(1000);
            }
        }
        if(WE.findE(ImportWordPage.alterinfo1)){
            SelfAssert.assertTrue(false,AC.getText(ImportWordPage.alterinfo1));
        }else {
            SelfAssert.assertTrue(true);
        }
    }
}
