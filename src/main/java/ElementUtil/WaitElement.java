package ElementUtil;

import ActionUtils.WaitUtil;
import Server.InitDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.List;

/**
 * Created by Administrator on 2017/9/4.
 */
public class WaitElement extends InitDriver {

    private static Logger log = LoggerFactory.getLogger(WaitElement.class);

    //等待单个元素
    public static AndroidElement findElement(By by) {
        AndroidElement AppElement=null;
        try {
            //等待元素出现，10s之后即超时
            AppElement =  new AndroidDriverWait(driver, 10).until(new ExpectedCondition<AndroidElement>() {
                public AndroidElement apply(AndroidDriver driver) {
                    return (AndroidElement) driver.findElement(by);
                }
            });
        } catch (Exception e) {
            log.error("元素:" + by + "查找超时,测试被迫中止......");
            Assert.assertTrue(false);
        }finally {
            if(AppElement == null){
                log.error("未查找到元素："+by);
            }
            return AppElement;
        }
    }

    //等待多个元素
    public static List<AndroidElement> findElements( By by) {
        List<AndroidElement> AppElements = null;
        try {
            //等待元素出现，10s之后即超时
            AppElements = (List<AndroidElement>) new AndroidDriverWait(driver, 5).until(new ExpectedCondition<AndroidElement>() {
                public AndroidElement apply(AndroidDriver driver) {
                    return (AndroidElement)driver.findElements(by);
                }
            });
        } catch (Exception e) {
            log.error("元素:" + by + "查找超时,测试被迫中止......");
            Assert.assertTrue(false);
        }
        return AppElements;
    }

    //从元素集合中查找元素
    public static AndroidElement getElement(final By by,int I){
        //by一般是菜单定位
        AndroidElement AppElement=findElements(by).get(I);
        return AppElement;
    }

    //等待某个元素xs直到出现
    public static boolean waitXS(By by, int i){
        boolean isexit=false;
        if(i<1){
            log.error("等待时间必须大于1s");
            return isexit;
        }
        for (int second = 0;second<=i; second++) {
            AndroidElement Ele=findElement(by);
            if (Ele!= null){
                isexit= true;
                break;
            }else {
                if (second==i){log.warn("元素" + by + "未找到");}
                WaitUtil.sleep(1000);
            }
        }
        return isexit;
    }

    //等待某个元素10s直到出现
    public static boolean  waitE10S(By by){
        boolean isexit=false;
        for (int second = 0;second<=10; second++) {
            AndroidElement Ele=findElement(by);
            if (Ele!= null){
                isexit= true;break;
            }else {
                if (second==10){log.warn("元素" + by + "未找到");}
                WaitUtil.sleep(1000);
            }
        }
        return isexit;
    }

    //判断某个元素是否出现
    public static boolean findE(By by){
        boolean isexit1 ;
        AndroidElement Ele=findElement(by);
        if (Ele!= null){
            isexit1= true;
        }else{
            isexit1=false;
            log.warn("元素" + by + "未找到");
        }
        return isexit1;
    }

    //判断某个元素显示在界面
    public static boolean Eledisplay(By by){
        boolean isdisplay=false;
        for (int i = 0; i <2; i++) {
            AndroidElement Ele = findElement(by);
            if (Ele == null||Ele.isDisplayed() == false) {
                WaitUtil.sleep(1000);
            }else {
                isdisplay = true;
            }
        }
        return isdisplay;
    }


}
