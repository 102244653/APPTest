package ActionUtils;

import ElementUtil.WaitElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static ElementUtil.GetByLocator.getAppElement;

public class TextUtil  {
    private static Logger logger = LoggerFactory.getLogger(TextUtil.class);

    //输入信息
    public static void sendText(AndroidDriver<AndroidElement> driver, String elementname, String text){
        getAppElement(driver,elementname).sendKeys(text);
    }

    //读取文本信息
    public static String getText(AndroidDriver<AndroidElement> driver,String elementname){
        return getAppElement(driver,elementname).getText().trim();
    }

}
