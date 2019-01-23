package ActionUtils;

import ElementUtil.WaitElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static ElementUtil.GetByLocator.getAppElement;

public class ClickUtil {
    private static Logger logger = LoggerFactory.getLogger(ClickUtil.class);

    public static void click(AndroidDriver<AndroidElement> driver,String elementname){
        getAppElement(driver,elementname).click();
        logger.info(elementname+" : 点击完成");
    }

    //按压
    public static void press(AndroidDriver<AndroidElement> driver, String elementname){
        io.appium.java_client.TouchAction touch=new io.appium.java_client.TouchAction(driver);
        touch.press(getAppElement(driver,elementname));
    }

    //坐标按压
    public static void press(AndroidDriver<AndroidElement> driver, int x,int y){
        io.appium.java_client.TouchAction touch=new io.appium.java_client.TouchAction(driver);
        touch.press(x, y);
    }

    //释放
    public static void releae(AndroidDriver<AndroidElement> driver){
        io.appium.java_client.TouchAction touch=new io.appium.java_client.TouchAction(driver);
        touch.release();
    }

    //控件的中心点上敲击一下
    public static void tap(AndroidDriver<AndroidElement> driver, String elementname,int i) {
        for (int k=1;k<=i;k++) {
            io.appium.java_client.TouchAction touch=new io.appium.java_client.TouchAction(driver);
            touch.tap(getAppElement(driver,elementname));
        }
    }

    //在（x,y）点轻击一下
    public static void tap(AndroidDriver<AndroidElement> driver, int x,int y,int i){
        io.appium.java_client.TouchAction touch=new io.appium.java_client.TouchAction(driver);
        for (int k=1;k<=i;k++) {
            touch.tap(x, y);
        }
    }

    //以控件el的左上角为基准，x轴向右移动x单位，y轴向下移动y单位。在该点上轻击。
    public static void tap(AndroidDriver<AndroidElement> driver, String elementname,int x,int y){
        io.appium.java_client.TouchAction touch=new io.appium.java_client.TouchAction(driver);
        touch.tap(getAppElement(driver,elementname), x, y);
    }

    //按下一个键的同时按下附加键
    public static void pressAndPress(AndroidDriver<AndroidElement> driver, int key,Integer metastate){
        driver.pressKeyCode(key, metastate);
    }

    //长按一个控件
    public static void longPress(AndroidDriver<AndroidElement> driver, String elementname){
        io.appium.java_client.TouchAction touch=new io.appium.java_client.TouchAction(driver);
        touch.longPress(getAppElement(driver,elementname));
    }

    public static void longPress(AndroidDriver<AndroidElement> driver, int x,int y){
        io.appium.java_client.TouchAction touch=new io.appium.java_client.TouchAction(driver);
        touch.longPress(x,y);
    }

    //偏移点长按
    public static void longPress(AndroidDriver<AndroidElement> driver, String elementname,int x,int y){
        io.appium.java_client.TouchAction touch=new io.appium.java_client.TouchAction(driver);
        touch.longPress(getAppElement(driver,elementname), x,y);
    }

    //取消动作
    public static void cancel(AndroidDriver<AndroidElement> driver){
        io.appium.java_client.TouchAction touch=new io.appium.java_client.TouchAction(driver);
        touch.cancel();
    }
}
