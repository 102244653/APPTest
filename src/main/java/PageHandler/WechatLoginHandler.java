package PageHandler;

import ActionUtils.WaitUtil;
import Base.TestStep;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import static ActionUtils.ClickUtil.*;
import static ActionUtils.TextUtil.*;

public class WechatLoginHandler {

    public void loginwechat(TestStep step){
        AndroidDriver<AndroidElement> driver=step.getAndroidDriver();
        if(driver==null){
            throw new RuntimeException("driver is null");
        }
        String[] text=step.getValue().trim().split(":");
        click(driver,"id>com.tencent.mm:id/e0y");
        WaitUtil.sleep(1500);
        sendText(driver,"id>com.tencent.mm:id/ka",text[0]);
//        click(driver,"text>下一步");
//        WaitUtil.sleep(1500);
//        sendText(driver,"text>请填写微信密码",text[1]);
//        click(driver,"text>登录");
    }

}
