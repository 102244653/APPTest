package ActionUtils;

import Server.InitDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WaitUtil extends InitDriver {
    private static Logger logger = LoggerFactory.getLogger(WaitUtil.class);
    static  io.appium.java_client.TouchAction touch=new io.appium.java_client.TouchAction(driver);
    public static void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            logger.error("=====等待异常=====");
        }
    }

    //等待下一个操作（ms）
    public static void waitAction(int ms){
        touch.waitAction(ms);
    }
}
