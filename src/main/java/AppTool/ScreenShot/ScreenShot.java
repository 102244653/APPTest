package AppTool.ScreenShot;

import AppTool.BaseDriver.SelfDriver;
import AppTool.Logger.TestLog;
import AppTool.ResultListener.TestReport;
import AppTool.SelefTools.DateUtil;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.Augmenter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class ScreenShot extends SelfDriver{

    static String path= System.getProperties().getProperty("user.dir") + "/TestReport/";

    //自定义截图名
    public  String screenshot(String name) {
        String picname;
        AndroidDriver augmentedDriver = (AndroidDriver) new Augmenter().augment(driver);
        File file = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
        try {
            //根据日期创建文件夹
            String nowdata= TestReport.dirc;
            File dir = new File(path +nowdata);
            if (!dir.exists())
            {dir.mkdirs();}
            FileUtils.copyFile(file, new File(path +nowdata + "/" + name + ".png"));
            picname=name+".png";
            TestLog.info("ScreenShot: screenshot(name)","截图："+picname);
        } catch (IOException e) {
            picname="001.png";
            TestLog.error("ScreenShot: screenshot(name)","截图失败！！");
            e.printStackTrace();
        }
        return picname;
    }

    //按时间截图
    public  String TakeScreen() {
        String picname;
        AndroidDriver augmentedDriver = (AndroidDriver) new Augmenter().augment(driver);
        File file = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
        try {
            //根据日期创建文件夹
            String nowdata= DateUtil.format(DateUtil.CHECK_LOG_FORMAT);
            File dir = new File(path +nowdata);
            String time=new DateUtil().getdata(DateUtil.time8);
            if (!dir.exists())
            {dir.mkdirs();}
            FileUtils.copyFile(file, new File(path + nowdata + "/" +
                    time + ".png"));
            picname=nowdata + "/" +time+ ".png";
            TestLog.info("ScreenShot: TakeScreen","截图："+picname);
        } catch (IOException e) {
            picname="MyTool.png";
            TestLog.error("ScreenShot: TakeScreen","截图失败！！");
            e.printStackTrace();
        }
        return picname;
    }

    //针对元素进行截图
    public void TakeScreenForEle(AndroidElement element, String path, String fileName) throws Exception{
        AndroidDriver augmentedDriver = (AndroidDriver) new Augmenter().augment(driver);
        // 获得element的位置和大小
        Point location = element.getLocation();
        Dimension size = element.getSize();
        byte[] imageByte=((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.BYTES);
        // 创建全屏截图
        BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(imageByte));
        // 截取element所在位置的子图。
        BufferedImage croppedImage = originalImage.getSubimage(
                location.getX(),
                location.getY(),
                size.getWidth(),
                size.getHeight());
        try {
            ImageIO.write(croppedImage, "png", new File(path + fileName + ".png"));
            //ImageIO.write(im, formatName, output)
        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    /**
     * 自定义截图路径和名字
     **/
    public  String screenShots(String path,String name) {

        String picname=name+".png";
        AndroidDriver augmentedDriver = (AndroidDriver) new Augmenter().augment(driver);
        File file = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
        try {
            File dir = new File(path);
            if (!dir.exists())
            {dir.mkdirs();}
            FileUtils.copyFile(file, new File(path +"/" + name + ".png"));
            //FileUtils.copyFile(file, new File(path + "/" + picname));
            TestLog.error("ScreenShot: screenShots","截图："+path+picname);
        } catch (IOException e) {
            picname="error";
            TestLog.error("ScreenShot: screenShots","截图失败");
            e.printStackTrace();
        }
        return picname;
    }
}
