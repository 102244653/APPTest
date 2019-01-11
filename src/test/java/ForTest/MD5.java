package ForTest;

import org.apache.commons.codec.digest.DigestUtils;
import org.testng.annotations.Test;

public class MD5 {

    @Test
    public  void  testmd5(){

//        String text="clientType=1&lang=0&network=1&timestamp="+${timestamp}+"&uid="+${uid}+"&version=4.3.1" +
//                "token="+${token}+"uuid="+${uuid}+"action="+${action};
        String text="clientType=1&lang=0&network=1&timestamp=1545983692&uid=100009&version=4.3.1token=uuid=123456action=v2.0/login";
        //String text="clientType=1&lang=0&network=1&timestamp=1545983692&uid=100009&version=4.3.1token=uuid=A4B668B6-F626-463A-B534-4EE13E32B764action=v2.8/index/activity";
        String encodeStr= DigestUtils.md5Hex("clientType=0&lang=0&network=0&timestamp=1545986858&uid=100009&version=4.3.1token=0ebe9b828b5aaaadb82196a99f3f657d=action=v2.0/member/check_secpwd");
        System.out.println(encodeStr);
    }
}
