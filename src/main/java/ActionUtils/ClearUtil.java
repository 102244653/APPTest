package ActionUtils;


import ElementUtil.WaitElement;
import Server.InitDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static ElementUtil.GetByLocator.getLocator;

public class ClearUtil extends InitDriver {
    private static Logger logger = LoggerFactory.getLogger(ClearUtil.class);

    //手动清楚内容
    public static void clearText(String page,String elementname) {
        driver.pressKeyCode(123);
        for (int i = 0; i < WaitElement.findElement(getLocator(elementname)).getText().length(); i++) {
            driver.pressKeyCode(67);
        }
        logger.info(elementname+" : 内容已清除");
    }

    public static void clear(String elementname){
        WaitElement.findElement(getLocator(elementname)).clear();
    }
}
