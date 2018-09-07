package AppTool.BaseDriver;

import AppTool.Logger.TestLog;
import AppTool.SelefTools.PropertiesUtil;
import AppTool.ServerManage.Servers;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class SelfDriver {

    public  AndroidDriver driver;

    public  AndroidDriver getdriver(){
        return driver;
    }

    public  void QuietDriver() {
        try{
            driver.close();
        }catch (WebDriverException e){ }
    }


    //启动Appium服务
//    public  void InitAppium(int portStart,int bootstrapPort) throws Exception {
//        new Servers().startServers(portStart, bootstrapPort);
//    }

    //启动手机设备
    public  AndroidDriver InitDriver(String udid,String port){
        try {
            this.driver =new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:"+port+"/wd/hub"), this.SetCaps(udid));//127.0.0.1:62001  //6fa1d37f
            //设置元素查找等待，10s之后报错（全局）
            this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            TestLog.info("SelfDriver: InitDriver","测试设备启动成功,SessionId："+this.driver.getSessionId());
        } catch (MalformedURLException e) {
            TestLog.error("SelfDriver: InitDriver","测试设备启动失败，测试终止...");
        }
        return driver;
    }

    public  void InitDriver(){
        try {
            this.driver =new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), this.SetCaps("127.0.0.1:62001"));//127.0.0.1:62001  //6fa1d37f
            //设置元素查找等待，10s之后报错（全局）
            this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            TestLog.info("SelfDriver: InitDriver","设备启动成功,SessionId："+this.driver.getSessionId());
        } catch (MalformedURLException e) {
            TestLog.error("SelfDriver: InitDriver","设备启动失败，测试被迫中止...");
        }
    }


    //设备初始化设置,用端口号识别设备
    public DesiredCapabilities SetCaps(String Udid){
        PropertiesUtil p=new PropertiesUtil(System.getProperties().getProperty("user.dir")+"\\Congfig\\caps.properties");
        DesiredCapabilities caps=new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, p.getPro("devicename"));

        //以下两项参数是结合使用，第一个unicodeKeyBoard是使用appium自带的输入法，可以隐藏键盘并支持中文输入
        //resetKeyBoard是执行完测试后将设备的输入法重置回原有的输入法
        caps.setCapability("unicodeKeyboard", true);
        caps.setCapability("resetKeyboard", true);

        //不对app进行重签名，因为有的app在重签名之后无法使用
        caps.setCapability("noSign", true);
        caps.setCapability("noReset", true);
        //设置session的超时时间
        caps.setCapability("newCommandTimeout", p.getPro("timeout"));

        //设备版本号
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, p.getPro("platformtversion"));

        //指定执行引擎，默认是Appium，4.2以下设置需指定为Selendroid
        //caps.setCapability("automationName","appium");
        //执行手机web测试时，浏览器的名称，在做app测试时这项参数可以不写
        //caps.setCapability("browserName","Chrome");

        //设备UDID
        caps.setCapability("udid", Udid);
        //app参数是指定要安装的测试apk的路径
        caps.setCapability(MobileCapabilityType.APP,p.getPro("Package"));
        //已安装使用包名
        //caps.setCapability("appPackage","com.seele.tigerwallet");
        //设置启动页面
        caps.setCapability("appActivity", p.getPro("Activity"));

        //默认打开计算器
        //已安装使用包名
        //caps.setCapability("appPackage","com.android.bbkcalculator");
        //设置启动页面
        //caps.setCapability("appActivity", "com.android.bbkcalculator.Calculator");

        //A new session could not be created的解决方法
        //caps.setCapability("appWaitActivity",p.getPro("WaitActivity"));
        //每次启动时覆盖session，否则第二次后运行会报错不能新建session
        caps.setCapability("sessionOverride", true);

        return caps;
    }
}
