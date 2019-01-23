package ActionUtils;


import ElementUtil.WaitElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static ElementUtil.GetByLocator.getAppElement;

public class ClearUtil  {
    private static Logger logger = LoggerFactory.getLogger(ClearUtil.class);

    //手动清楚内容
    public static void clearText(AndroidDriver<AndroidElement> driver,String page, String elementname) {
        driver.pressKeyCode(123);
        for (int i = 0; i < getAppElement(driver,elementname).getText().length(); i++) {
            driver.pressKeyCode(67);
        }
        logger.info(elementname+" : 内容已清除");
    }

    public static void clear(AndroidDriver<AndroidElement> driver,String elementname){
        getAppElement(driver,elementname).clear();
    }
}
