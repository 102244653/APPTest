package PageHandler;

import ActionUtils.WaitUtil;
import Base.TestStep;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import static ActionUtils.ClickUtil.*;
import static ActionUtils.SwipeUtil.Swipe;
import static ActionUtils.TextUtil.*;

public class WechatLoginHandler {


    public void loginwechat(TestStep step){
        AndroidDriver<AndroidElement> driver=step.getAndroidDriver();
        if(driver==null){
            throw new RuntimeException("driver is null");
        }
        String[] text=step.getValue().trim().split(":");
        click(driver,"id>com.tencent.mm:id/e4g");
        WaitUtil.sleep(1500);
        sendText(driver,"id>com.tencent.mm:id/kh",text[0]);
        click(driver,"text>下一步");
        WaitUtil.sleep(1500);
        sendText(driver,"text>请填写微信密码",text[1]);
        click(driver,"text>登录");
    }

    public void finddemo(TestStep step){
        AndroidDriver<AndroidElement> driver=step.getAndroidDriver();
        if(driver==null){
            throw new RuntimeException("driver is null");
        }
        click(driver,"id>com.tencent.mm:id/d7_");
        Swipe(driver,"up",1);
        click(driver,"xpath>//*[@text='小程序']");
        WaitUtil.sleep(1000);
        click(driver,"xpath>//*[@text='京东购物']");
        WaitUtil.sleep(1000);

    }

}
