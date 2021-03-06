package Utils;

import Server.InitDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.Augmenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class ScreenShot  {
    private static Logger log= LoggerFactory.getLogger(ScreenShot.class);
    public static String nowdata=DateUtil.format(DateUtil.time8);
    static String path= System.getProperties().getProperty("user.dir") + "/TestReport/"+nowdata;
    //自定义截图名
    public static String screenshot(AndroidDriver<AndroidElement> driver,String name) {
        String picname;
        AndroidDriver augmentedDriver = (AndroidDriver) new Augmenter().augment(driver);
        File file = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
        try {
            //根据日期创建文件夹
            File dir = new File(path);
            if (!dir.exists())
            {dir.mkdirs();}
            FileUtils.copyFile(file, new File(path + "/" + name + ".png"));
            picname= name + ".png";
            log.info("截图："+picname);
        } catch (IOException e) {
            picname="001.png";
            log.error("截图失败！！");
            e.printStackTrace();
        }
        return picname;
    }

    //按时间截图
    public static String TakeScreen(AndroidDriver<AndroidElement> driver) {
        String picname;
        AndroidDriver augmentedDriver = (AndroidDriver) new Augmenter().augment(driver);
        File file = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
        try {
            File dir = new File(path);
            String time=new DateUtil().getdata(DateUtil.time8);
            if (!dir.exists())
            {dir.mkdirs();}
            FileUtils.copyFile(file, new File(path  + "/" + time + ".png"));
            picname=time+ ".png";
            log.info("截图："+picname);
        } catch (IOException e) {
            picname="截图失败";
            log.error("截图失败！！");
            e.printStackTrace();
        }
        return picname;
    }

    //针对元素进行截图
    public void TakeScreenForEle(AndroidDriver<AndroidElement> driver,AndroidElement element, String path, String fileName) throws Exception{
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
    public  String screenShots(AndroidDriver<AndroidElement> driver,String path1,String name) {
        String picname=name+".png";
        AndroidDriver augmentedDriver = (AndroidDriver) new Augmenter().augment(driver);
        File file = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
        try {
            File dir = new File(path+"/"+path1);
            if (!dir.exists())
            {dir.mkdirs();}
            FileUtils.copyFile(file, new File(path+"/"+path1 +"/" + name + ".png"));
            log.error("ScreenShot: screenShots","截图："+path+"/"+path1 +"/"+picname);
        } catch (IOException e) {
            picname="截图失败";
            log.error("ScreenShot: screenShots","截图失败");
            e.printStackTrace();
        }
        return picname;
    }
}
