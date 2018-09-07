package AppTool.ServerManage;

import AppTool.Logger.TestLog;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class CreatPort {

    private DosCmd execDos;
    public CreatPort(){
        this.execDos=new DosCmd();
    }
    /**
     * 验证端口是否被占用
     * @param portNum
     * @return boolean
     */
    public  Boolean isPortUsed(int portNum) {
        List<String> portRes = new ArrayList<String>();
        String osName=System.getProperty("os.name");
        boolean flag=true;
        try {
            String command="";
            if(osName.toLowerCase().contains("mac")){
                command="netstat -an|grep " + portNum;
            }else if(osName.toLowerCase().contains("win")){
                command="netstat -ano|findstr " + portNum;
            }
            portRes = execDos.execCmdConsole(command);
            System.out.println(portRes.size());
            if (portRes.size() > 0) {
                TestLog.debug("CreatPort: isPortUsed",String.valueOf(portNum)+" 已被使用");
            }else{
                TestLog.debug("CreatPort: isPortUsed",String.valueOf(portNum)+" 未被使用");
                flag=false;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            TestLog.error("CreatPort: isPortUsed",String.valueOf(portNum)+"get port occupied status failure"+e);
        }
        return flag;
    }
    /**
     * 基于当前连接的设备数量生成可用端口
     * @param portStart,Starting ports  起始端口
     * @param deviceTotal,Total number of devices
     * @return List<Integer>
     */
    public  List<Integer> GeneratPortList(int portStart, int deviceTotal) {
        List<Integer> portList = new ArrayList<Integer>();
        while (portList.size() != deviceTotal) {
            if (portStart >= 0 && portStart <= 65535) {
                //判断端口是否被占用
                if (!isPortUsed(portStart)) {
                    portList.add(portStart);
                }
                portStart++;
            }
        }
        return portList;
    }

    /**
     * 拼接设备启动命令
     * portStart  appium起始端口号
     * bootstrapPortList  设备起始端口号
     * */
    @Test
    public String[] GetCommad() throws Exception {//int portStart,int bootstrapPort
        String[] commad=null;
        Servers server = new Servers();
        List<String> udidList = server.getDevices();//获取设备
        int count = udidList.size();//设备数量
        TestLog.info("CreatPort: GetCommad",String.valueOf(count));
        List<Integer> portList = GeneratPortList(2222, count);
        for (Integer i : portList) {
            TestLog.info("CreatPort: GetCommad",String.valueOf(i));
        }
        List<Integer> bootstrapPortList = GeneratPortList(3333, count);
        for (Integer i : bootstrapPortList) {
            TestLog.info("CreatPort: GetCommad",String.valueOf(i));
        }
        for (int i = 0; i < udidList.size(); i++) {
            String appium_commad = "appium -p " + portList.get(i) + " -bp " + bootstrapPortList.get(i) + " -U "
                    + udidList.get(i) + " > " + "logs/" + udidList.get(i).split(":")[0] + ".log";
            TestLog.info("CreatPort: GetCommad",appium_commad);
            commad[i]=appium_commad;
        }
        return commad;
    }
}

