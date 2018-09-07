package AppTool.BaseAction;

import AppTool.BaseDriver.SelfDriver;
import AppTool.Logger.TestLog;
import AppTool.SelefTools.Time;
import AppTool.WaitElement.WaitElement;
import io.appium.java_client.NetworkConnectionSetting;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;


public class Action {

    public AndroidDriver driver;
    public  io.appium.java_client.TouchAction touch=new io.appium.java_client.TouchAction(driver);
    public WaitElement WE;

    public Action(AndroidDriver driver){
        this.driver=driver;
        this.WE=new WaitElement(driver);
    }

    //手动清楚内容
    public  void clearText(By type) {
        driver.pressKeyCode(123);
        for (int i = 0; i < WE.findElement(type).getText().length(); i++) {
            driver.pressKeyCode(67);
        }
    }

    //上滑(num--滑动次数)
    public  void upSwipe(int num) {
        try {
            int width = driver.manage().window().getSize().width;
            int height = driver.manage().window().getSize().height;
            for (int i = 0; i < num; i++) {
                driver.swipe(width / 2, height * (3 / 4), width / 2, height / 4, 1000);
                Time.sleep(1);
            }
        }catch (Exception e){
            TestLog.error("Action: upSwipe","向上滑动失败...");
        }

    }

    //下滑
    public  void downSwipe(int num) {
        try {
            int width = driver.manage().window().getSize().width;
            int height = driver.manage().window().getSize().height;
            for (int i = 0; i < num; i++) {
                driver.swipe(width/2, height / 4, width / 2, height * (3 / 4), 1000);
                Time.sleep(1);
            }
        }catch (Exception e){
            TestLog.error("Action: downSwipe","向下滑动失败...");
        }
    }

    //右滑
    public  void rightSwipe(int num) {
        try {
            int width = driver.manage().window().getSize().width;
            int height = driver.manage().window().getSize().height;
            for (int i = 0; i < num; i++) {
                driver.swipe(width / 4, height / 2, width * 3 / 4, height / 2, 1000);
                Time.sleep(1);
            }
        }catch (Exception e){
            TestLog.error("Action: rightSwipe","向右滑动失败...");
        }
    }

    //左滑
    public  void leftSwipe(int num) {
        try {
            int width = driver.manage().window().getSize().width;
            int height = driver.manage().window().getSize().height;
            for (int i = 0; i < num; i++) {
                driver.swipe(width * 3 / 4, height / 2, width / 4, height / 2, 1000);
                Time.sleep(1);
            }
        }catch (Exception e){
            TestLog.error("Action: leftSwipe","向左滑动失败...");
        }
    }

    //指定元素左滑
    public  void eleLeftSwipe(By by, int num) {
        try {
            int width = WE.findElement(by).getSize().width;
            int height = WE.findElement(by).getSize().height;
            for (int i = 0; i < num; i++) {
                driver.swipe(width * 3 / 4, height / 2, width / 4, height / 2, 1000);
                Time.sleep(1);
            }
        }catch (Exception e){
            TestLog.error("Action: eleLeftSwipe",by+"向左滑动失败...");
        }
    }

    //点击
    public  void click(By by){
        WE.findElement(by).click();
        TestLog.info("Action: click()","点击"+by);
        Time.sleep(500);
    }

    public  void click(String by){
        driver.findElementByAndroidUIAutomator(by).click();
        TestLog.info("Action: click()","点击"+by);
        Time.sleep(500);
    }

    //根据包名判断APP是否已安装
    public  boolean isInsatll(String packagename){
        return driver.isAppInstalled(packagename);
    }

    //安装App
    public  void installApp(String appPath){
        driver.installApp(appPath);
    }

    //卸载app
    public  void removeApp(String packagename){
        driver.removeApp(packagename);
    }

    //按下一个键的同时按下附加键
    public  void pressAndPress(int key,Integer metastate){
        driver.pressKeyCode(key, metastate);
    }

    //关闭应用，其实就是按home键把应用置于后台
    public  void closeApp(){
        driver.closeApp();
    }

    public  void close(){
        driver.quit();
    }


    //先closeApp然后在launchAPP
    public  void resetApp(){
        driver.resetApp();
    }

    public  void resetApp(int seconds){
        driver.runAppInBackground(seconds);
    }

    //将设备上的文件pull到本地硬盘上
    public  void pullToDevice(String type,String remotePath) {
        if (type.equals("file")) {
            driver.pullFile(remotePath);
        } else {
            driver.pullFolder(remotePath);
        }
    }

    //得到当前网络的状态
    public  NetworkConnectionSetting getNetwork(){
        driver.lockDevice();
        return driver.getNetworkConnection();
    }

    //打开通知栏
    public  void openNotifications(){
        driver.openNotifications();
    }

    //2个手指操作控件，从对角线向中心点滑动。
    public  void pinch(WebElement el) {

    }

    //以（x,y）为基准，计算得出（x,y-100）,(x,y+100)两个点，然后2个手指按住这两个点同时滑到（x,y）
    public  void pinch(int x, int y){

    }

    //锁屏多少秒后解锁
    public  void lockScreen(int seconds){
        driver.lockDevice();
    }

    //模拟摇晃手机
    public  void shake(){
        driver.hashCode();
    }

    //切换至WebView
    public  void ToWebView(){
        // 页面上的所有context给返回
        List<String> contextSet = (List<String>) driver.getContextHandles();
        for (String contextName : contextSet) {
            TestLog.info("Action: ToWebView","ContextName:"+contextName);
        }
        for(String context : contextSet){
            TestLog.info("Action: ToWebView","页面上的context内容为：" + context);
            if(context.toLowerCase().contains("webview")){
                driver.context(context);
                break;
            }
        }
    }

    public  void ToWebView(String name){
        driver.context(name);
    }

    //切回APP
    public  void ToApp(){
        driver.context("NATIVE_APP");
    }

    //设置屏幕横屏或者竖屏
    public  void rotate(String orientation){
        ScreenOrientation value= ScreenOrientation.valueOf(orientation);
        driver.rotate(value);
    }

    //获取当前屏幕的方向
    public  String getOrientation(){
        return String.valueOf(driver.getOrientation());
    }

    //利用android的uiautoamtor中的属性来获取单个控件
    public  void findElementByAndroidUIAutomator(String using){

    }

    public  void findElementsByAndroidUIAutomator(String using){

    }

    //利用accessibility id来获取单个控件
    public  void  findElementByAccessibilityId(String using){

    }

    //输入信息
    public  void sendText(By type, String text){
        WE.findElement(type).sendKeys(text);
        TestLog.info("Action: sendText","输入："+text);
    }

    //读取文本信息
    public  String getText(By type){
        String text=WE.findElement(type).getText().trim();
        TestLog.info("Action: getText","已读取到内容："+text);
        if(text.isEmpty()){
            text=" ";
        }
        return text;
    }

    public  String getText(String str){
        String text=driver.findElementByAndroidUIAutomator(str).getText().trim();
        TestLog.info("Action: getText","已读取到内容："+text);
        if(text.isEmpty()){
            text=" ";
        }
        return text;
    }

    //输出错误信息
    public  void print(String text){
        TestLog.info("Action: print",text);
    }

    //移动鼠标到某元素上--悬停
    public  void moveandwait(By type) {
        Actions action = new Actions(driver);
        AndroidElement E1 = WE.findElement(type);
        action.moveToElement(E1).perform();
        TestLog.info("Action: moveandwait","鼠标移动到" + type + "上面！");
    }

    //移动鼠标到某元素并点击
    public  void moveandclick(By type) {
        Actions action = new Actions(driver);
        AndroidElement E1 = WE.findElement(type);
        action.moveToElement(E1).perform();
        E1.click();
        TestLog.info("Action: moveandclick","点击元素" + type);
    }

    //调用js
    public  void jsAction(String js){
        ((JavascriptExecutor) driver).executeScript(js);
        TestLog.info("Action: jsAction",js+" 执行成功");

    }

    //按压
    public  void press(By type){
        touch.press(WE.findElement(type));
    }

    //坐标按压
    public  void press(int x,int y){
        touch.press(x, y);
    }

    //释放
    public  void releae(){
        touch.release();
    }

    //移动到某元素
    public  void moveToEle(By type){
        touch.moveTo(WE.findElement(type));
    }

    //偏移到某元素

    //控件的中心点上敲击一下
    public  void tap(By type){
        touch.tap(WE.findElement(type));
    }

    //在（x,y）点轻击一下
    public  void tap(int x,int y){
        touch.tap(x, y);
    }

    //以控件el的左上角为基准，x轴向右移动x单位，y轴向下移动y单位。在该点上轻击。
    public  void tap(By type, int x, int y){
        touch.tap(WE.findElement(type), x, y);
    }

    //等待一个操作（ms）
    public  void waitAction(int ms){
        touch.waitAction(ms);
    }

    //长按一个控件
    public  void longPress(By type){
        touch.longPress(WE.findElement(type));
    }
    public  void longPress(int x,int y){
        touch.longPress(x,y);
    }

    //偏移点长按
    public  void longPress(By type, int x, int y){
        touch.longPress(WE.findElement(type), x,y);
    }

    //取消动作
    public  void cancel(){
        touch.cancel();
    }
}
