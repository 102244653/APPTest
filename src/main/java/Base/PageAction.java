package Base;

import PageHandler.WaitActionHandler;
import PageHandler.WechatLoginHandler;

import java.util.HashMap;
import java.util.Map;

public enum PageAction {

    /**
     * name  方法名  ANDROID_SET
     * key  指令名称(关键字)    string-set
     *指令描速   Android端设置值到全局
     * 所属类   class   String.class
     * */
    //等待操作
    waitForced("waitForced", "强制等待",WaitActionHandler .class),
    androidImplicit("androidImplicit", "Android端隐式等待", WaitActionHandler.class),

    //微信登录
    loginwchat("loginwchat","登录微信", WechatLoginHandler.class)

    ;



    //操作关键字
    private String key;
    //操作描述
    private String desc;
    //所属类文件
    private Class<?> handler;
    //类文件集合
    private static Map<String,PageAction> map;

    static{
        map = new HashMap<String,PageAction>();
        for(PageAction action : PageAction.values()){
            map.put(action.key(), action);
        }
    }

    private PageAction(String key, String desc, Class<?> handler) {
        this(key, desc);
        this.handler = handler;
    }

    private PageAction(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public static PageAction action(String name){
        return map.get(name);
    }

    public String key(){
        return this.key;
    }

    public String desc(){
        return this.desc;
    }

    public Class<?> handler(){
        return this.handler;
    }

    public void run(TestStep step) throws Exception{

    }
}
