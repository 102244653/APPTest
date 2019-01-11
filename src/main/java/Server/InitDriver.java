package Server;

import Utils.ConfigUtil;
import Utils.Constants;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class InitDriver {
    /**
     * <br>Appium服务配置</br>
     *
     * @throws Exception
     */
    private static final Logger log = LoggerFactory.getLogger(InitDriver.class);
    public static AndroidDriver<AndroidElement> driver;
    static AppiumService appiumServer;

    public static void AppiumTest(String AppPackageName,String Activity,String PlatformVersion,String DeviceID) throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("device", "uiautomator2");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", PlatformVersion);
        capabilities.setCapability("deviceName", DeviceID);
        capabilities.setCapability("udid", DeviceID);
        capabilities.setCapability("noReset", true);
        capabilities.setCapability("fullReset", false);
        capabilities.setCapability("app",AppPackageName);
        capabilities.setCapability("appActivity",Activity);
        capabilities.setCapability("sessionOverride", false);
        capabilities.setCapability("unicodeKeyboard", true);
        capabilities.setCapability("resetKeyboard", true);
        capabilities.setCapability("noSign", true);
//		capabilities.setCapability("autoWebview", true);
        capabilities.setCapability("newCommandTimeout", 60000);
        driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(40000, TimeUnit.MILLISECONDS);
    }

    public static void AppiumTest() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //设备名称
        capabilities.setCapability("device", "uiautomator2");
        //测试webview的浏览器，一般为chrome
        capabilities.setCapability("browserName",ConfigUtil.getProperty("browserName",Constants.CAP_PROPERTIES));
        //设备UDID
        capabilities.setCapability("deviceName",ConfigUtil.getProperty("UDID", Constants.CAP_PROPERTIES));
        //系统版本号
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", ConfigUtil.getProperty("platformVersion",Constants.CAP_PROPERTIES));
        //已安装直接传如app名字，未安装传入app路径
        capabilities.setCapability("app",ConfigUtil.getProperty("app",Constants.CAP_PROPERTIES));
        //测试APP的默认启动界面
        capabilities.setCapability("appActivity",ConfigUtil.getProperty("appActivity",Constants.CAP_PROPERTIES));
        //需要等待APP的界面，非必填
        capabilities.setCapability("appWaitActivity",ConfigUtil.getProperty("appWaitActivity",Constants.CAP_PROPERTIES));
        //签名设置
        capabilities.setCapability("noSign", true);
        //使用appium默认键盘
        capabilities.setCapability("noReset", true);
        //支持中文
        capabilities.setCapability("unicodeKeyboard",true);
        //测试结束把输入法恢复至默认状态
        capabilities.setCapability("resetKeyboard", true);
        //启动时是否覆盖session，true(覆盖)/false(不覆盖)
        capabilities.setCapability("sessionOverride", false);
        //重新安装APP，true(重新安装)/false(不重新安装)
        capabilities.setCapability("fullReset", false);
        //支持自动切换webview
        capabilities.setCapability("autoWebview", true);
        //设置客户端命令相应超时时间60s
        capabilities.setCapability("newCommandTimeout", 60000);
        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"),capabilities);
        driver.manage().timeouts().implicitlyWait(40000, TimeUnit.MILLISECONDS);
    }



}
