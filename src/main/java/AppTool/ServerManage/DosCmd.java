package AppTool.ServerManage;

import AppTool.Logger.TestLog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * 此类完成dos或shell命令的执行封装
 *
 */
public class DosCmd {

    //获取当前系统的名字
    String osName=System.getProperty("os.name");

    /**
     * execute dos command
     * @param /dos command,String
     * @return boolean.succeed is true,Failure is false
     *
     */
    public boolean execCmd(String cmdString){
        Runtime CMD = Runtime.getRuntime();
        try {
            //判断设备类型
            if(osName.toLowerCase().contains("mac")){
                String[] command={"/bin/sh","-c",cmdString};
                Process process=CMD.exec(command);
            }else if(osName.toLowerCase().contains("win")){
                Process process=CMD.exec("cmd /c "+cmdString);
            }
            Thread.sleep(5000);
            System.out.println("dos命令执行完成");
            TestLog.debug("DosCmd: execCmd","execute  command "+cmdString+" Succeed");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            // TODO Auto-generated catch block
            TestLog.error("DosCmd: execCmd","execute  command "+cmdString+" Failure: \n"+e);
            return false;
        }
    }
    /**
     * get result of command, after execute dos command
     * 获取一个list返回数据集合
     * @param   /dos command,String
     * @return List<String>
     */
    public List<String> execCmdConsole(String cmdString) throws InterruptedException {
        List<String> dosRes = new ArrayList<String>();
        try {
            Process process=null;
            System.out.println(cmdString);
            if(osName.toLowerCase().contains("mac")){
                String[] command={"/bin/sh","-c",cmdString};
                process = Runtime.getRuntime().exec(command);
            }else if(osName.toLowerCase().contains("win")){
                process = Runtime.getRuntime().exec("cmd /c " + cmdString);
            }
            InputStream in = process.getInputStream();
            BufferedReader inr = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while ((line = inr.readLine()) != null) {
                dosRes.add(line);
            }
            process.waitFor();
            process.destroy();
            TestLog.debug("DosCmd: execCmdConsole","get result of command after execute dos command "+cmdString+" Succeed ");
        } catch (IOException e) {
            TestLog.error("DosCmd: execCmdConsole","get result of command after execute dos command "+cmdString+" Failure:\n"+e);
        }
        return dosRes;
    }
    /**
     * kill server by pid of server
     * 通过pid结束进程
     * @param
     * @return boolean
     */
    public boolean killServer(){
        String command="taskkill -F -PID node.exe";
        if(osName.toLowerCase().contains("mac")){
            command="killall node";
        }else if(osName.toLowerCase().contains("win")){
            command="task kill -F -PID node.exe";
        }else{
            command="task kill -F -PID node.exe";
        }
        if(this.execCmd(command)){
            TestLog.info("DosCmd: killServer","kill server node  Succeed");
            return true;
        }else{
            TestLog.error("DosCmd: killServer","kill server node Failure");
            return false;
        }
    }



//	public static void main(String[] args) throws Exception {
//		System.out.println(System.getProperty("os.name"));
//		DosCmd dc=new DosCmd();
//		List<String> devicesList=dc.execCmdConsole("adb devices");
//		System.out.println(devicesList.size());
//		System.out.println(devicesList.get(1));
//		for(String s:devicesList){
//			System.out.println(s);
//		}
//	}
}
