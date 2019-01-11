package ActionUtils;

import ElementUtil.WaitElement;
import Server.InitDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.JavascriptExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static ElementUtil.GetByLocator.getLocator;

public class SwipeUtil extends InitDriver {
    private static Logger log = LoggerFactory.getLogger(SwipeUtil.class);
    static  io.appium.java_client.TouchAction touch=new io.appium.java_client.TouchAction(driver);

    //上滑(num--滑动次数)
    public static void Swipe(String point,int num) {
        try {
            int width = driver.manage().window().getSize().width;
            int height = driver.manage().window().getSize().height;
            switch (point){
                case "up":
                    for (int i = 0; i < num; i++) {
                        driver.swipe(width / 2, height * (3 / 4), width / 2, height / 4, 1000);
                        WaitUtil.sleep(1000);
                    }
                    break;
                case "down":
                    for (int i = 0; i < num; i++) {
                        driver.swipe(width/2, height / 4, width / 2, height * (3 / 4), 1000);
                        WaitUtil.sleep(1000);
                    }
                    break;
                case "left":
                    for (int i = 0; i < num; i++) {
                        driver.swipe(width * 3 / 4, height / 2, width / 4, height / 2, 1000);
                        WaitUtil.sleep(1000);
                    }
                    break;
                case "right":
                    for (int i = 0; i < num; i++) {
                        driver.swipe(width / 4, height / 2, width * 3 / 4, height / 2, 1000);
                        WaitUtil.sleep(1000);
                    }
                    break;
                default:break;
            }
        }catch (Exception e){
            log.error("滑动失败...");
            e.printStackTrace();
        }
    }

    public static void Swipe(String elementname){
        AndroidElement ele=WaitElement.findElement(getLocator(elementname));//定位到元素
        WaitUtil.sleep(1000);
        JavascriptExecutor dj=(JavascriptExecutor)driver;//将Driver实例化为js对象
        dj.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", ele);//滑动到上面定位到的元素的位置
    }

    public static void SwipeElement(String point,String elementname1, AndroidElement elementname2){
        //获取待操作元素
        AndroidElement element = WaitElement.findElement(getLocator(elementname1));
        int x=elementname2.getLocation().getX();
        int y=elementname2.getLocation().getY();
        int width=elementname2.getSize().getWidth();
        int height=elementname2.getSize().getHeight();
        //计算移动后的位置坐标,getLocation获取顶点坐标
        int pointX=x; int pointY=y;
        switch (point){
           case "up":
               pointX=x+width/2;
               pointY=y+height/4;
               break;
           case "down":
               pointX=x+width/2;
               pointY=y+height*3/4;
               break;
           case "left":
              pointX=x+width/4;
              pointY=y+height/2;
               break;
           case "right":
               pointX=x+width*3/4;
               pointY=y+height/2;
               break;
               default:break;
        }
        //进行移动
        touch.longPress(elementname2).moveTo(pointX,pointY).release().perform();
    }

    //移动到某元素
    public static void moveToEle(String elementname){
        touch.moveTo(WaitElement.findElement(getLocator(elementname)));
    }

    //放大
    public static void zoom(int x, int y) {
        driver.zoom(x,y);
    }

    //缩小，以（x,y）为基准，计算得出（x,y-100）,(x,y+100)两个点，然后2个手指按住这两个点同时滑到（x,y）
    public static void pinch(int x, int y){
        driver.pinch(x,y);
    }


}
