package TestCase;

import AppTool.SelfAssert.SelfAssert;
import DataManage.TestData.Data_NewWallet;
import DataManage.TestData.Data_PayWord;
import TestScript.ScriptManage.CreatOrImportWallet.NewWallet;
import TestScript.ScriptManage.CreatOrImportWallet.SetPayWord;

import TestScript.ScriptManage.StartApp;
import org.testng.annotations.Test;

public class CreatTigerWallet extends StartApp {

    @Test
    public void CrearTigerWallet(){
        NewWallet wallet=new NewWallet(driver);
        SetPayWord payWord=new SetPayWord(driver);
        try{
            wallet.ConfirmNote();
            wallet.SelectCreat();
            wallet.ReadWords();
            wallet.Remember();
            wallet.ConfirmWord(Data_NewWallet.words);
            payWord.SetPayWord(Data_PayWord.paywords);
            payWord.ConfirmPayWord(Data_PayWord.paywords);
        }catch (Exception e){
            SelfAssert.assertTrue(false,e.getStackTrace().toString());
        }
    }
}
