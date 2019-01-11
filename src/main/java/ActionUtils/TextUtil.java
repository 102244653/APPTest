package ActionUtils;

import ElementUtil.WaitElement;
import Server.InitDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static ElementUtil.GetByLocator.getLocator;

public class TextUtil extends InitDriver {
    private static Logger logger = LoggerFactory.getLogger(TextUtil.class);

    //输入信息
    public static void sendText(String elementname,String text){
        WaitElement.findElement(getLocator(elementname)).sendKeys(text);
    }

    //读取文本信息
    public static String getText(String elementname){
        return WaitElement.findElement(getLocator(elementname)).getText().trim();
    }

}
