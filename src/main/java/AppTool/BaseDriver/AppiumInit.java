package AppTool.BaseDriver;

import AppTool.SelefTools.PropertiesUtil;
import AppTool.SelefTools.XmlUtil;
import AppTool.ServerManage.DosCmd;
import AppTool.ServerManage.Servers;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class AppiumInit {

    //启动服务并创建测试套件xml
    public static void Init(){
        Servers servers=new Servers();
        DosCmd dos=new DosCmd();
        if(dos.killServer()){
            try {
                servers.startServers(3333, 4444);
            } catch (Exception e) {
                e.printStackTrace();
            }

        try {
            PropertiesUtil pro=new PropertiesUtil("Config/TestClass.properties");
//            if(pro.getPro("casesize").isEmpty()){
//                System.out.println("TestClass.properties配置文件中casesize值不能为空");
//                return;
//            }
//            int k=Integer.valueOf(pro.getPro("casesize"));
//            //添加测试用例类，创建testng.xml文件
//            List<String> classes=new ArrayList<String>();
//            for(int i=1;i<=k;k++){
//				classes.add(pro.getPro("case"+i));
//				}
//            XmlUtil.createTestngSingleXml(classes);
            XmlUtil.createTestngXml("TestCase.CreatTigerWallet");
        } catch (Exception e) {
            e.printStackTrace();
        }
        }else{
             System.out.println("Appium初始化失败");
        }
    }
}
