package ActionUtils;

import Server.InitDriver;
import io.appium.java_client.NetworkConnectionSetting;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.ScreenOrientation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SystemUtil  {
    private static Logger logger = LoggerFactory.getLogger(SystemUtil.class);

    //根据包名判断APP是否已安装
    public static boolean isInstall(AndroidDriver<AndroidElement> driver,String packagename){
        return driver.isAppInstalled(packagename);
    }

    //安装App
    public static void installApp(AndroidDriver<AndroidElement> driver,String appPath){
        driver.installApp(appPath);
    }

    //卸载app
    public static void removeApp(AndroidDriver<AndroidElement> driver,String packagename){
        driver.removeApp(packagename);
    }

    //关闭应用，其实就是按home键把应用置于后台
    public static void closeApp(AndroidDriver<AndroidElement> driver){
        driver.closeApp();
    }

    //先closeApp然后在launchAPP
    public static void resetApp(AndroidDriver<AndroidElement> driver){
        driver.resetApp();
    }
    public static void resetApp(AndroidDriver<AndroidElement> driver,int seconds){
        driver.runAppInBackground(seconds);
    }

    //将设备上的文件pull到本地硬盘上
    public static void pullToDevice(AndroidDriver<AndroidElement> driver,String type,String remotePath) {
        if (type.equals("file")) {
            driver.pullFile(remotePath);
        } else {
            driver.pullFolder(remotePath);
        }
    }

    //得到当前网络的状态
    public static NetworkConnectionSetting getNetwork(AndroidDriver<AndroidElement> driver){
        driver.lockDevice();
        return driver.getNetworkConnection();
    }

    //打开通知栏
    public static void openNotifications(AndroidDriver<AndroidElement> driver){
        driver.openNotifications();
    }

    //锁屏多少秒后解锁
    public static void lockScreen(AndroidDriver<AndroidElement> driver,int seconds){
        driver.lockDevice();
    }

    //模拟摇晃手机
    public static void shake(AndroidDriver<AndroidElement> driver){
        driver.hashCode();
    }

    //设置屏幕横屏或者竖屏
    public static void rotate(AndroidDriver<AndroidElement> driver,String orientation){
        ScreenOrientation value= ScreenOrientation.valueOf(orientation);
        driver.rotate(value);

    }

    //获取当前屏幕的方向
    public static String getOrientation(AndroidDriver<AndroidElement> driver){
        return String.valueOf(driver.getOrientation());
    }

}
