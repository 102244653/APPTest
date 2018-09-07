package AppTool.SelefTools;

import AppTool.ServerManage.Servers;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class XmlUtil {
    /**
     * 读取device.xml配置文件
     * @param filePath
     * @return
     * @throws DocumentException
     */
    public static List<String> readXML(String filePath) throws DocumentException {

        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new File(filePath));
        // 获取根元素
        Element root = document.getRootElement();
        //System.out.println(root.getName());
        // 获取特定名称的子元素
        @SuppressWarnings("unchecked")
        List<Element> deviceList = root.elements("deviceId");
        // 迭代输出
        List<String> deviceData = new ArrayList<String>();
        for (Element e : deviceList) {
            for (Iterator iter = e.elementIterator(); iter.hasNext();) {
                Element e1 = (Element) iter.next();
                deviceData.add(e1.getText());
            }
        }
        return deviceData;
    }
    /**
     * 创建device.xml文件
     * @param deviceList
     * @param appiumPortList
     * @throws IOException
     * @throws InterruptedException
     */
    public static void createDeviceXml(List<String> deviceList,List<Integer> appiumPortList) throws IOException,
            InterruptedException {
        Document document = DocumentHelper.createDocument();
        Element root = DocumentHelper.createElement("Device");
        document.setRootElement(root);
        root.addAttribute("name", "appiumstartlist");
        if (deviceList.size() > 0) {
            for (int j = 0; j < deviceList.size(); j++) {
                Element deviceId = root.addElement("deviceId");
                deviceId.addAttribute("id", "devices"+j);
                Element deviceName = deviceId.addElement("deviceName");
                Element appiumPort = deviceId.addElement("appiumPort");
                deviceName.setText(deviceList.get(j));
                appiumPort.setText(String.valueOf(appiumPortList.get(j)));
            }
        }
        OutputFormat format = new OutputFormat("    ", true);
        XMLWriter xmlWrite2 = new XMLWriter(new FileOutputStream("./Config/device.xml"),
                format);
        xmlWrite2.write(document);
    }
    /**
     * 创建testng.xml配置文件
     * @param classname
     * @throws Exception
     */

    //执行单个用例
    public static void createTestngXml(String classname) throws Exception {
        Servers servers=new Servers();
        List<String> deviceList=servers.getDevices();
        System.out.println("设备数量"+deviceList.size());
        Document document = DocumentHelper.createDocument();
        Element root = DocumentHelper.createElement("suite");
        document.setRootElement(root);
        root.addAttribute("name", "Suite");
        root.addAttribute("parallel", "tests");
        //线程数
        root.addAttribute("thread-count", String.valueOf(deviceList.size()));

        //读取设备信息
        List<String> s=readXML("Config/device.xml");
        for(int j=0;j<deviceList.size();j++){
            Element test = root.addElement("test");
            test.addAttribute("name", deviceList.get(j));

            Element classes = test.addElement("classes");

            Element paramtitle = classes.addElement("parameters");
            //数据驱动udid
            Element paramUuid=paramtitle.addElement("parameter");
            paramUuid.addAttribute("name","udid");
            paramUuid.addAttribute("value",s.get(2*j));
            //数据驱动port
            Element paramPort=paramtitle.addElement("parameter");
            paramPort.addAttribute("name", "port");
            paramPort.addAttribute("value",s.get(2*j+1));
            //测试类
            Element classNode=classes.addElement("class");
            classNode.addAttribute("name", classname);
        }
        //设置监听器，生成报告
        Element listeners=root.addElement("listeners");
        Element listener1=listeners.addElement("listener");
        listener1.addAttribute("class-name", "AppTool.ResultListener.TestngListener");
        Element listener2=listeners.addElement("listener");
        listener2.addAttribute("class-name", "AppTool.ResultListener.RetryListener");
        Element listener3=listeners.addElement("listener");
        listener3.addAttribute("class-name", "AppTool.ResultListener.TestReport");

        OutputFormat format = new OutputFormat("    ", true);
        XMLWriter xmlWrite2;
        try {
            xmlWrite2 = new XMLWriter(new FileOutputStream("testng.xml"),format);
            xmlWrite2.write(document);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //单线程执行多条用力
    public static void createTestngXml(List<String> classesList) throws Exception {
        Servers servers=new Servers();
        List<String> deviceList=servers.getDevices();
        System.out.println("设备数量"+deviceList.size());
        Document document = DocumentHelper.createDocument();
        Element root = DocumentHelper.createElement("suite");
        document.setRootElement(root);
        root.addAttribute("name", "Suite");
        root.addAttribute("parallel", "tests");
        root.addAttribute("thread-count", String.valueOf(deviceList.size()));

        List<String> s=readXML("config/device.xml");
        for(int j=0;j<deviceList.size();j++){
            Element test = root.addElement("test");
            test.addAttribute("name", deviceList.get(j));
            Element paramUuid=test.addElement("parameter");
            paramUuid.addAttribute("name","udid");
            paramUuid.addAttribute("value",s.get(2*j));
            Element paramPort=test.addElement("parameter");
            paramPort.addAttribute("name", "port");
            paramPort.addAttribute("value",s.get(2*j+1));
            Element classes = test.addElement("classes");
            for(String className:classesList){
                Element classNode=classes.addElement("class");
                classNode.addAttribute("name", className);
            }
        }
        Element listeners=root.addElement("listeners");
        Element listener1=listeners.addElement("listener");
        listener1.addAttribute("class-name", "AppTool.TestListener.TestngListener");
        Element listener2=listeners.addElement("listener");
        listener2.addAttribute("class-name", "AppTool.TestListener.RetryListener");
        Element listener3=listeners.addElement("listener");
        listener3.addAttribute("class-name", "AppTool.TestListener.TestReport");
        OutputFormat format = new OutputFormat("    ", true);
        XMLWriter xmlWrite2;
        try {
            xmlWrite2 = new XMLWriter(new FileOutputStream("testng_2.xml"),format);
            xmlWrite2.write(document);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //多线程执行多条用例
    public static void createTestngSingleXml(List<String> classesList) throws Exception {
        Servers servers=new Servers();
        List<String> deviceList=servers.getDevices();
        System.out.println("设备数量"+deviceList.size());
        Document document = DocumentHelper.createDocument();
        Element root = DocumentHelper.createElement("suite");
        document.setRootElement(root);
        root.addAttribute("name", "Suite");
        root.addAttribute("parallel", "tests");
        root.addAttribute("thread-count", String.valueOf(deviceList.size()));

        List<String> s=readXML("config/device.xml");
        for(int j=0;j<deviceList.size();j++){
            Element test = root.addElement("test");
            test.addAttribute("name", deviceList.get(j));
            Element paramUuid=test.addElement("parameter");
            paramUuid.addAttribute("name","udid");
            paramUuid.addAttribute("value",s.get(2*j));
            Element paramPort=test.addElement("parameter");
            paramPort.addAttribute("name", "port");
            paramPort.addAttribute("value",s.get(2*j+1));
            Element classes = test.addElement("classes");
            Element classNode=classes.addElement("class");
            classNode.addAttribute("name", classesList.get(j));
        }
        Element listeners=root.addElement("listeners");
        Element listener1=listeners.addElement("listener");
        listener1.addAttribute("class-name", "AppTool.TestListener.TestngListener");
        Element listener2=listeners.addElement("listener");
        listener2.addAttribute("class-name", "AppTool.TestListener.RetryListener");
        Element listener3=listeners.addElement("listener");
        listener3.addAttribute("class-name", "AppTool.TestListener.TestReport");
        OutputFormat format = new OutputFormat("    ", true);
        XMLWriter xmlWrite2;
        try {
            xmlWrite2 = new XMLWriter(new FileOutputStream("testng_3.xml"),format);
            xmlWrite2.write(document);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
