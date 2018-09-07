package AppTool.ServerManage;

import AppTool.Logger.TestLog;
import AppTool.SelefTools.XmlUtil;

import java.util.ArrayList;
import java.util.List;

public class Servers {
    private List<Integer> appiumPortList;   //appium启动端口
    private List<Integer> bootstrapPortList;//设备占用端口
    private List<String> deviceList;//设备udid
    private CreatPort CreatPort;
    private DosCmd dos;
    private String path=System.getProperty("user.dir");
    public Servers(){
        this.CreatPort=new CreatPort();
        this.dos=new DosCmd();
    }

    public static void main(String[] args) throws Exception {
        TestLog.info("Servers: killServer",String.valueOf(new Servers().startServers(2222, 2322)));
    }

    /**
     * 根据设备数量生成可用端口列表
     * @param start 端口起始号
     * @return 返回值是一个List<Integer>
     * @throws Exception
     */
    //
    private List<Integer> getPortList(int start) throws Exception{
        List<String> deviceList=getDevices();
        List<Integer> portList=CreatPort.GeneratPortList(start, deviceList.size());
        return portList;
    }
    /**
     * 获取当前可用设备
     * @return
     * @throws Exception
     */
    public  List<String> getDevices() throws Exception {
        List<String> devList = dos.execCmdConsole("adb devices");
        List<String> deviceRes = new ArrayList<String>();
        if (devList.size() > 2) {
            for(int i = 1; i < devList.size() - 1; i++) {
                String deviceInfo[] = devList.get(i).split("\t");
                if (deviceInfo[1].trim().equals("device")) {
                    deviceRes.add(deviceInfo[0].trim());
                }
            }
        } else {
            TestLog.info("Servers: getDevices","当前没有设备或设备连接状态不正确");
        }
        return deviceRes;
    }
    /**
     * 生成服务端启动命令字符串存入List
     * @return
     * @throws Exception
     */
    public List<String> GeneratServerCommand(int portStart,int bootstrapPort) throws Exception{
        appiumPortList=getPortList(portStart);
        bootstrapPortList=getPortList(bootstrapPort);
        deviceList=getDevices();
        List<String> commandList=new ArrayList<String>();
        for(int i=0;i<deviceList.size();i++){
            String command="appium -p "+appiumPortList.get(i)+" -bp "+bootstrapPortList.get(i)
                    +" -U "+deviceList.get(i)+" > "+"./TestLog/"+deviceList.get(i).split(":")[0]+".log";
            TestLog.info("Servers: GeneratServerCommand",command);
            commandList.add(command);
        }
        XmlUtil.createDeviceXml(deviceList, appiumPortList);
        return commandList;
    }
    /**
     * 启动所有appium服务端，启动需前杀死服务
     * @return
     * @throws Exception
     */
    public boolean startServers(int portStart,int bootstrapPort) throws Exception{
        dos.killServer();
        List<String> startCommand=GeneratServerCommand(portStart,bootstrapPort);
        boolean flag=false;
        if(startCommand.size()>0){
            for(String s:startCommand){
                dos.execCmd(s);
            }
            flag=true;
            TestLog.info("Servers: startServers","Appium服务启动成功");
        }else{
            flag=false;
            TestLog.error("Servers: startServers","Appium服务启动失败");
        }
        return flag;
    }


}
