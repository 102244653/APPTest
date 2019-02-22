package ForTest;

import Utils.ConfigUtil;
import Utils.Constants;
import org.testng.annotations.Test;

public class getelement {
    @Test
    public void testConfig(){
        String element= ConfigUtil.getProperty("D-登录密码", Constants.Elements);
        System.out.println(element);
    }
}
