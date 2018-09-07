package TestScript.ElementManage;

import org.openqa.selenium.By;

public class MenuPage {

    //资产
    public static final By account=By.xpath("//android.widget.TextView[@text='资产']");

    //行情
    public static final By price=By.xpath("//android.widget.TextView[@text='行情']");

    //发现
    public static final By find=By.xpath("//android.widget.TextView[@text='发现']");

    //我的
    public static final By self=By.xpath("//android.widget.TextView[@text='我的']");

    //加载图标
    public static final By  loading=By.id("com.seele.tigerwallet:id/tipTextView");

    //弹窗错误提示信息
    public static final By alterinfo=By.id("com.seele.tigerwallet:id/contentPanel");

    //弹窗确认
    public static final By alterok=By.id("android:id/button1");


}
