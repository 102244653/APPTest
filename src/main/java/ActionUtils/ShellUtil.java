package ActionUtils;

import Server.InitDriver;
import Server.RunUnitService;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShellUtil  {
        private static Logger log = LoggerFactory.getLogger(ShellUtil.class);

        //调用js
        public static void jsAction(AndroidDriver<AndroidElement> driver,String js){
            driver.executeScript(js);
            log.info("通过JS传入参数");
        }

        public static void adbinput(String text)throws Exception{
            RunUnitService.AndroidAdb("adb shell input text "+text);
        }


}
