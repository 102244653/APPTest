package ActionUtils;

import Server.InitDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WaitUtil {
    private static Logger logger = LoggerFactory.getLogger(WaitUtil.class);

    public static void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            logger.error("=====等待异常=====");
        }
    }

    //等待下一个操作（ms）
    public static void waitAction(AndroidDriver<AndroidElement> driver,int ms){
        io.appium.java_client.TouchAction touch=new io.appium.java_client.TouchAction(driver);
        touch.waitAction(ms);
    }
}
