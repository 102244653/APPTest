package PageHandler;

import static ActionUtils.ClickUtil.*;
import static ActionUtils.TextUtil.*;

public class WechatLoginHandler {

    public void loginwchat(String value){
        String[] text=value.trim().split(":");
        click("id>com.tencent.mm:id/d1w");
        click("id>com.tencent.mm:id/kk");
        sendText("text>请填写手机号",text[0]);
        click("text>下一步");
        sendText("text>请填写微信密码",text[1]);
        click("text>登录");
    }

}
