package Server;

import Base.PageAction;
import Base.TestCase;
import Base.TestStep;
import Base.TestUnit;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.lang.reflect.Method;
import java.util.*;

public class AndroidXmlAnalytic  extends InitDriver{

    /**
     * <br>解析xml测试场景配置文件之前，对appium进行配置</br>
     *
     * @param
     * @return
     */

    public static TestUnit ParseTest(String AppPackageName, String Activity, String PlatformVersion, String DeviceID, String  XmlPath) {
        try {
            //启动appium服务
            appiumServer =new AppiumService();
            appiumServer.StartAppium();
            System.out.println("开始初始化Android设备,耐心等待App启动ing...");
            //初始化安卓设备
            AppiumTest(AppPackageName,Activity,PlatformVersion,DeviceID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //解析测试用例xml
        return parse(new File("src/test/java/TestCaseXml/"+XmlPath));
    }

    public static TestUnit ParseTest(String XmlPath) {
        try {
            appiumServer =new AppiumService();
            appiumServer.StartAppium();
            System.out.println("开始初始化Android设备,耐心等待App启动ing...");
            AppiumTest("","","","");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parse(new File("src/test/java/TestCaseXml/"+XmlPath));
    }


    /**
     * <br>解析xml测试场景配置文件，转换为一个TestUnit实例，也就是一个TestCase的集合</br>
     *
     * @param xmlFile
     * @return
     */
    public static TestUnit parse(File xmlFile) {
        //测试单元
        TestUnit testUnit = null;
        //测试用例xml不为空
        if ( xmlFile == null || !xmlFile.exists()  )
            return testUnit;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        //解析xml
        try {
            builder = factory.newDocumentBuilder();

            Document doc = builder.parse(xmlFile);
            Element root = doc.getDocumentElement();
            //读取case节点
            NodeList cases = root.getElementsByTagName("case");
            //存放case的map
            LinkedHashMap<String, TestCase> caseMap = new LinkedHashMap<String, TestCase>();
            Element child;
            TestCase testCase;//存放step

            //逐个解析xml中的case元素
            for (int i = 0; i < cases.getLength(); i++) {
                child = (Element) cases.item(i);
                testCase = parseTestCase(child);

                if (testCase == null)
                    continue;

                if (caseMap.containsKey(testCase.getCaseid()))
                    throw new RuntimeException("存在多个" + testCase.getCaseid() + "，请检查id配置");
                else
                    caseMap.put(testCase.getCaseid(), testCase);
            }

            testUnit = new TestUnit();
            testUnit.setCaseMap(caseMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return testUnit;
    }

    /**
     * <br>将case元素解析为TestCase实例，也就是一个TestStep的集合</br>
     *
     * @param element 一个case元素
     * @return
     */
    private static TestCase parseTestCase(Element element) {
        if (element == null)
            return null;

        NamedNodeMap attrs = element.getAttributes();
        //根据case的属性实例化TestCase，并注入相应字段值
        TestCase testCase = initByAttributes(attrs, new TestCase());
        //读取step节点
        NodeList stepNodes = element.getElementsByTagName("step");
        int len = stepNodes.getLength();//step个数
        List<TestStep> stepList = new ArrayList<TestStep>(len);//存放step的集合，即单个case

        Element stepNode;
        //逐个解析case元素的step子元素
        for (int i = 0; i < len; i++) {
            stepNode = (Element) stepNodes.item(i);
            stepList.add(parseTestStep(stepNode));
        }
        testCase.setSteps(stepList);

        return testCase;
    }

    /**
     * <br>解析step元素为TestStep实例</br>
     *
     * @param element
     * @return
     */
    private static TestStep parseTestStep(Element element) {
        if (element == null)
            return null;

        TestStep testStep = initByAttributes(element.getAttributes(), new TestStep());
        testStep.setDriver(driver);

        return testStep;
    }

    /**
     * <br>根据xml文件中的元素属性为对象的对应字段注入值</br>
     *
     * @param attrs
     * @param t 需要实例化并注入字段值的对象
     * @return
     */
    private static <T> T initByAttributes(NamedNodeMap attrs, T t) {
        if (attrs == null || attrs.getLength() == 0)
            return t;

        int len = attrs.getLength();
        Node attr;
        String name, value;

        //通过反射逐个注入字段值
        for (int i = 0; i < len; i++) {
            attr = attrs.item(i);
            if (attr == null) continue;

            name = attr.getNodeName();
            value = attr.getNodeValue();
            //通过反射为对象的对应字段注入值
            initByReflect(name, value, t);
        }
        return t;
    }

    /**
     * <br>通过反射为对象的对应字段注入值</br>
     * @param name
     * @param value
     * @param t
     * @return
     */
    private static <T> T initByReflect(String name, String value, T t) {
        if (t == null)
            throw new RuntimeException("null object");
        if (name == null || "".equals(name))
            throw new RuntimeException("empty name");

        Class<?> clazz = t.getClass();
        Method setter, getter;

        try {
            String methodStr = name.substring(0, 1).toUpperCase() + name.substring(1);

            //如果名称是cancel，则调用isCancel()方法，主要是为了语义上的直观
            getter = clazz.getMethod(("cancel".equals(name) ? "is" : "get") + methodStr, new Class<?>[] {});
            setter = clazz.getMethod("set" + methodStr, getter.getReturnType());

            if ("action".equals(name))
                //根据PageAction类中的map来获取名称对应的StepAction（枚举）实例
                setter.invoke(t, PageAction.action(value));
            else if("details".equals(name))
                setter.invoke(t,parseDetail(value));
            else
                setter.invoke(t, value);
        } catch (Exception e) {
			e.printStackTrace();
        }
        return t;
    }

    /**
     * <br>解析行为的细节描述为map</br>
     *
     * @author    102051
     * @date      2017年7月28日 上午11:01:14
     * @param detail
     * @return
     */
    public static Map<String,String> parseDetail(String detail){
        HashMap<String,String> map = new HashMap<>();
        String[] strarr = detail.split(";");

        for(String str : strarr){
            map.put(str.split(":")[0], str.split(":")[1]);
        }
        return map;
    }

    public static void quiteapp(){
        driver.closeApp();
    }

}
