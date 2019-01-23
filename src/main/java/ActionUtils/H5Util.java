package ActionUtils;

import Server.InitDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class H5Util  {
    private static Logger logger = LoggerFactory.getLogger(H5Util.class);

    //切换至WebView
    public static void ToWebView(AndroidDriver<AndroidElement> driver){
        // 页面上的所有context给返回
        List<String> contextSet = (List<String>) driver.getContextHandles();
        for (String contextName : contextSet) {
            logger.info(contextName);
        }
        for(String context : contextSet){
            logger.info("页面上的context内容为：" + context);
            if(context.toLowerCase().contains("webview")){
                driver.context(context);
                break;
            }
        }
    }

    public static void ToWebView(AndroidDriver<AndroidElement> driver,String name){
        driver.context(name);
    }

    //切回APP
    public static void ToApp(AndroidDriver<AndroidElement> driver){
        driver.context("NATIVE_APP");
    }
}
