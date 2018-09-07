package TestCase;

import AppTool.SelfAssert.SelfAssert;

import DataManage.TestData.Data_ImportWallet;
import DataManage.TestData.Data_PayWord;
import TestScript.ScriptManage.CreatOrImportWallet.ImportWallet;
import TestScript.ScriptManage.CreatOrImportWallet.SetPayWord;
import TestScript.ScriptManage.StartApp;
import org.testng.annotations.Test;

public class ImportTigerWallet extends StartApp{

    @Test
    public void CrearTigerWallet(){
        ImportWallet importW=new ImportWallet(driver);
        SetPayWord payWord=new SetPayWord(driver);
        try {
            importW.ConfirmNote();
            importW.SelectImport();
            importW.ImportWords(Data_ImportWallet.importwords);
            payWord.SetPayWord(Data_PayWord.paywords);
            payWord.ConfirmPayWord(Data_PayWord.paywords);
        }catch (Exception e){
            SelfAssert.assertTrue(false,e.getStackTrace().toString());
        }

    }
}
