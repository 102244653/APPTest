package ActionUtils;

import ElementUtil.WaitElement;
import Server.InitDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static ElementUtil.GetByLocator.getLocator;

public class ClickUtil extends InitDriver {
    private static Logger logger = LoggerFactory.getLogger(ClickUtil.class);
    static  io.appium.java_client.TouchAction touch=new io.appium.java_client.TouchAction(driver);

    public static void click(String elementname){
        WaitElement.findElement(getLocator(elementname)).click();
        logger.info(elementname+" : 点击完成");
    }

    //按压
    public static void press(String elementname){
        touch.press(WaitElement.findElement(getLocator(elementname)));
    }

    //坐标按压
    public static void press(int x,int y){
        touch.press(x, y);
    }

    //释放
    public static void releae(){
        touch.release();
    }

    //控件的中心点上敲击一下
    public static void tap(String elementname,int i) {
        for (int k=1;k<=i;k++) {
            touch.tap(WaitElement.findElement(getLocator(elementname)));
        }
    }

    //在（x,y）点轻击一下
    public static void tap(int x,int y,int i){
        for (int k=1;k<=i;k++) {
            touch.tap(x, y);
        }
    }

    //以控件el的左上角为基准，x轴向右移动x单位，y轴向下移动y单位。在该点上轻击。
    public static void tap(String elementname,int x,int y){
        touch.tap(WaitElement.findElement(getLocator(elementname)), x, y);
    }

    //按下一个键的同时按下附加键
    public static void pressAndPress(int key,Integer metastate){
        driver.pressKeyCode(key, metastate);
    }

    //长按一个控件
    public static void longPress(String elementname){
        touch.longPress(WaitElement.findElement(getLocator(elementname)));
    }

    public static void longPress(int x,int y){
        touch.longPress(x,y);
    }

    //偏移点长按
    public static void longPress(String elementname,int x,int y){
        touch.longPress(WaitElement.findElement(getLocator(elementname)), x,y);
    }

    //取消动作
    public static void cancel(){
        touch.cancel();
    }
}
