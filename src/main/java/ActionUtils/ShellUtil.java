package ActionUtils;

import Server.InitDriver;
import Server.RunUnitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShellUtil extends InitDriver {
        private static Logger log = LoggerFactory.getLogger(ShellUtil.class);

        //调用js
        public static void jsAction(String js){
            driver.executeScript(js);
            log.info("通过JS传入参数");
        }

        public static void adbinput(String text)throws Exception{
            RunUnitService.AndroidAdb("adb shell input text "+text);
        }


}
