package AppTool.WaitElement;

import AppTool.BaseDriver.SelfDriver;
import AppTool.Logger.TestLog;
import AppTool.SelefTools.MyException;
import AppTool.SelefTools.PropertiesUtil;
import AppTool.SelefTools.Time;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.util.List;

public class WaitElement {

    public AndroidDriver driver;

    public WaitElement(AndroidDriver driver){
        this.driver=driver;
    }

    //等待单个元素
    public AndroidElement findElement(final By by) {
        AndroidElement AppElement=null;
        try {
            //等待元素出现，10s之后即超时
            AppElement =  new AndroidDriverWait(driver, 10).until(new ExpectedCondition<AndroidElement>() {
                public AndroidElement apply(AndroidDriver driver) {
                    return (AndroidElement) driver.findElement(by);
                }
            });
        } catch (WebDriverException e) {
            TestLog.error("WaitElement: findElement","元素:" + by + "查找超时");
        }finally {
            return AppElement;
        }
    }

    //等待多个元素
    public  List<AndroidElement> findElements(final By by) {
        List<AndroidElement> AppElements = null;
        try {
            //等待元素出现，10s之后即超时
            AppElements = (List<AndroidElement>) new AndroidDriverWait(driver, 5).until(new ExpectedCondition<AndroidElement>() {
                public AndroidElement apply(AndroidDriver driver) {
                    return (AndroidElement)driver.findElements(by);
                }
            });
        } catch (WebDriverException e) {
            TestLog.error("WaitElement: findElements","元素:" + by + "查找超时");
        }
        return AppElements;
    }


    //按照元素属性选择查找元素
    public  WebElement MyElement(String path,String str){
        try {
            PropertiesUtil p=new PropertiesUtil(path);
            String text=p.getPro(str);
            String T=text.split(">")[0].trim();
            String V=text.split(">")[1].trim();
            if(T.toLowerCase().equals("id")){ return this.findElement(By.id(V));}
            if(T.toLowerCase().equals("name")){ return this.findElement(By.name(V));}
            if(T.toLowerCase().equals("class")){ return this.findElement(By.className(V));}
            if(T.toLowerCase().equals("xpath")){ return this.findElement(By.xpath(V));}
            if(T.toLowerCase().equals("linktext")){ return this.findElement(By.linkText(V));}
            if(T.toLowerCase().equals("css")){ return this.findElement(By.cssSelector(V));}
            if(T.toLowerCase().equals("tagname")){ return this.findElement(By.tagName(V));}
            if(T.toLowerCase().equals("byxpath")){return driver.findElementByXPath(V);}
            if(T.toLowerCase().equals("byname")){ return driver.findElementByName(V);}
            if(T.toLowerCase().equals("byclassname")){ return driver.findElementByClassName(V);}
            if(T.toLowerCase().equals("byid")){ return driver.findElementById(V);}
            if(T.toLowerCase().equals("bylinktext")){return driver.findElementByLinkText(V);}
            if(T.toLowerCase().equals("uiautomator")){ return driver.findElementByAndroidUIAutomator(V);}
            if(T.toLowerCase().equals("bycss")){ return driver.findElementByCssSelector(V);}
        }catch (MyException e){
            e.printStackTrace();
        }finally {
            return null;
        }
    }

    public  List<AndroidElement> MyElements(String path,String str){
        try {
            PropertiesUtil p=new PropertiesUtil(path);
            String text=p.getPro(str);
            String T=text.split(">")[0].trim();
            String V=text.split(">")[1].trim();
            if(T.toLowerCase().equals("id")){ return this.findElements(By.id(V));}
            if(T.toLowerCase().equals("name")){ return this.findElements(By.name(V));}
            if(T.toLowerCase().equals("class")){ return this.findElements(By.className(V));}
            if(T.toLowerCase().equals("xpath")){ return this.findElements(By.xpath(V));}
            if(T.toLowerCase().equals("linktext")){ return this.findElements(By.linkText(V));}
            if(T.toLowerCase().equals("css")){ return this.findElements(By.cssSelector(V));}
            if(T.toLowerCase().equals("tagname")){ return this.findElements(By.tagName(V));}
            if(T.toLowerCase().equals("byxpath")){return driver.findElementsByXPath(V);}
            if(T.toLowerCase().equals("byname")){ return driver.findElementsByName(V);}
            if(T.toLowerCase().equals("byclassname")){ return driver.findElementsByClassName(V);}
            if(T.toLowerCase().equals("byid")){ return driver.findElementsById(V);}
            if(T.toLowerCase().equals("bylinktext")){return driver.findElementsByLinkText(V);}
            if(T.toLowerCase().equals("uiautomator")){ return driver.findElementsByAndroidUIAutomator(V);}
            if(T.toLowerCase().equals("bycss")){ return driver.findElementsByCssSelector(V);}
        }catch (MyException e){
            e.printStackTrace();
        }finally {
            return null;
        }
    }


    //等待某个元素xs直到出现
    public  boolean waitXS(By by, int i){
        boolean isexit=false;
        if(i<1){
            TestLog.error("WaitElement: waitXS", "等待时间必须大于1s");
            return isexit;
        }
        for (int second = 0;second<=i; second++) {
            AndroidElement Ele=(AndroidElement)driver.findElement(by);
            if (Ele!= null){
                isexit= true;
                break;
            }else {
                if (second==i){TestLog.error("WaitElement: waitXS","元素" + by + "未找到");}
                Time.sleep(1000);
            }
        }
        return isexit;
    }

    //等待某个元素10s直到出现
    public  boolean  waitE10S(By by){
        boolean isexit=false;
        for (int second = 0;second<=10; second++) {
            AndroidElement Ele=(AndroidElement)driver.findElement(by);
            if (Ele!= null){
                isexit= true;break;
            }else {
                if (second==10){TestLog.error("WaitElement: waitE10S","元素" + by + "未找到");}
                Time.sleep(1000);
            }
        }
        return isexit;
    }

    //判断某个元素是否出现
    public  boolean findE(By by){
        boolean isexit1 ;
        AndroidElement Ele=(AndroidElement)driver.findElement(by);
        if (Ele!= null){
            isexit1= true;
        }else{
            isexit1=false;
        }
        return isexit1;
    }

    //判断某个元素显示在界面
    public  boolean Eledisplay(By by) {
        boolean isdisplay=false;
        for (int i = 0; i <2; i++) {
            AndroidElement Ele = (AndroidElement)driver.findElement(by);
            if (Ele == null||Ele.isDisplayed() == false) {
                Time.sleep(1000);
            }else {
                isdisplay = true;
            }
        }
        return isdisplay;
    }

    public boolean Eledisplay(String  str) {
        boolean isdisplay=false;
        for (int i = 0; i <2; i++) {
            AndroidElement Ele = (AndroidElement)driver.findElementByAndroidUIAutomator(str);
            if (Ele == null||Ele.isDisplayed() == false) {
                Time.sleep(1000);
            }else {
                isdisplay = true;
            }
        }
        return isdisplay;
    }

}
