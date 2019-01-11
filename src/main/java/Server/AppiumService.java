package Server;

import java.io.IOException;

public class AppiumService {

    String cmdPath = "cmd.exe /c start";
    String appiumPath = "D:\\nodejs\\node_global\\appium.cmd";
    String cmdappium = cmdPath + " " + appiumPath;

    public void appiumStart() throws IOException, InterruptedException {
        Runtime.getRuntime().exec(cmdappium);
        //log.info("Appium服务启动中,耐心等待ing...");
        Thread.sleep(10000);
    }

    public void appiumStop() throws IOException, InterruptedException {
        //创建或删除taskmgr计划任务,获取任务管理器的system权限
//    	Runtime.getRuntime().exec("schtasks /create /tn taskmgr /sc daily /st 00:01 /tr taskmgr.exe");
//    	Runtime.getRuntime().exec("schtasks /f /delete /tn taskmgr");

        //强制结束node.exe进程
        Runtime.getRuntime().exec("taskkill /f /IM node.exe");
        //log.info("Appium服务关闭中,耐心等待ing...");
        Thread.sleep(3000);
    }

    public void StartAppium() throws IOException, InterruptedException {
        appiumStop();
        appiumStart();
    }

}
