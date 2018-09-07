package TestScript.ScriptManage.CreatOrImportWallet;

import AppTool.BaseAction.Action;
import AppTool.Logger.TestLog;
import AppTool.SelfAssert.CheckPoint;
import AppTool.SelfAssert.SelfAssert;
import AppTool.WaitElement.WaitElement;
import DataManage.TestData.Data_NewWallet;
import TestScript.ElementManage.CreatOrImportPage.ConfirmWordPage;
import TestScript.ElementManage.CreatOrImportPage.CreatWordsPage;

import TestScript.ElementManage.CreatOrImportPage.FristPage;
import TestScript.ElementManage.CreatOrImportPage.SelectPage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import java.util.ArrayList;

import static AppTool.BaseAction.Action.*;

public class NewWallet {

    public Action AC;
    public WaitElement WE;
    public AndroidDriver driver;
    public NewWallet(AndroidDriver driver){
        this.AC=new Action(driver);
        this.WE=new WaitElement(driver);
        this.driver=driver;
    }


    //确认钱包使用说明
    public void ConfirmNote() throws Exception {
        AC.click(FristPage.agree);
    }

    //创建钱包
    public void SelectCreat() throws Exception {
        AC.click(SelectPage.creatW);
       // CheckPoint.checkEqules();
    }


    //刷新助记词
    public void RefreshWord() throws Exception{
        AC.click(CreatWordsPage.refreshWord);
    }

    //点击什么是助记词
    public void WhatWords() throws Exception{
        AC.click(CreatWordsPage.whatWord);
        //CheckPoint.checkEqules();
    }

    //读取助记词
    public void ReadWords()throws Exception{
        ArrayList<String> readwords=new ArrayList<String>();
        for(int i=1;i<13;i++) {
            String word = AC.getText(By.xpath("//android.support.v7.widget.RecyclerView/android.widget.TextView[" + i + "]"));
            TestLog.info("Data_NewWallet: ReadWords","助记词"+i+": "+word);
            if(!word.isEmpty()){
                readwords.add(word);
            }
        }
        Data_NewWallet.words=readwords;
    }


    //点击我记住了
    public void Remember()throws Exception{
        AC.click(CreatWordsPage.remember);
      //  CheckPoint.checkEqules();
    }

    //确认助记词
    public void ConfirmWord(ArrayList<String> words)throws Exception{
        for(int i = 0; i<words.size(); i++){
            String text=words.get(i);
            AC.click("new UiSelector().text(\"”"+text+"\")");
//            String value=getText(By.id("com.seele.tigerwallet:id/tv_show"+i));
//            if(!value.equals(text)){
//                click("new UiSelector().text(\""+text+"\")");
//            }
        }
        if(WE.Eledisplay(ConfirmWordPage.errorinfo)){
            String text=AC.getText(ConfirmWordPage.errorinfo);
            SelfAssert.assertTrue(false,text);
        }
        TestLog.info("Data_NewWallet: ConfirmWordPage","助记词已确认，创建钱包成功");
        SelfAssert.assertTrue(true);
    }
}
