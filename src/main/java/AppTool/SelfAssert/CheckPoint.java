package AppTool.SelfAssert;

import AppTool.Logger.TestLog;
import AppTool.ResultListener.TestReport;
import AppTool.ScreenShot.ScreenShot;
import AppTool.SelefTools.RandomNum;

public class CheckPoint {
    //检查点错误截图保存路径
    static String  errorPicName= TestReport.path+"CheckPointError\\";

    /**
     * 断言字符是否已某个字符串开头
     * @param picname 检查点失败后的截图名
     * @param content 待断言字符串
     * @param prefix  前缀表达式
     */
    public static void checkStartWith(String content, String prefix, String picname) {
        if (null == picname){
            picname="error"+ new RandomNum().getStringRandom(4);
        }
        if (content.startsWith(prefix)) {
            TestLog.info("CheckPoint： checkStartWith",content+" 与 "+prefix+" 前缀匹配校验成功");
        } else {
            new ScreenShot().screenShots(errorPicName,picname);
            TestLog.info("CheckPoint：checkStartWith","前缀匹配校验失败！\n待校验的字符窜为:" + content + "\n校验的前缀表达式为:" + prefix+"\n截图："+picname);
        }
    }

    /**
     * 断言字符是否已某个字符串结尾
     * @param picname 检查点失败后的截图名
     * @param content 待断言字符串
     * @param endfix  前缀表达式
     */
    public static void checkEndWith(String content, String endfix, String picname) {

        if (null == picname)
            picname="error"+ new RandomNum().getStringRandom(4);
        if (content.endsWith(endfix)) {
            TestLog.info("CheckPoint： checkEndWith",content+" 与 "+endfix+" 后缀匹配校验成功");
        } else {
            new ScreenShot().screenShots(errorPicName,picname);
            TestLog.info("CheckPoint： checkEndWith","后缀匹配校验失败！\n待校验的字符窜为:" + content + "\n校验的后缀表达式为:" + endfix+"\n截图："+picname);
        }
    }


    /**
     * 断言字符串中是否包含某些字符
     * @param picname 检查点失败后的截图名
     * @param content  断言的字符串为
     * @param included 包含的字符串
     */
    public static void checkInclude(String content, String included, String picname) {

        if (null == picname)
            picname="error"+ new RandomNum().getStringRandom(4);
        if (content.contains(included)) {
            TestLog.info("CheckPoint： checkInclude",content+" 与 "+included+" 包含匹配校验成功");
        } else {
            new ScreenShot().screenShots(errorPicName,picname);
            TestLog.info("CheckPoint： checkInclude","包含匹配校验失败！\n待校验的字符串为:" + content + "\n包含字符串为:" + included+"\n截图："+picname);
        }
    }

    /**
     * 断言字符串中是否等于某些字符
     * @param picname 检查点失败后的截图名
     * @param content  断言的字符串为
     * @param included 包含的字符串
     */
    public static void checkEqules(String content, String included, String picname) {
        if (null == picname)
            picname="error"+new  RandomNum().getStringRandom(4);
        if (content.equals(included)) {
            TestLog.info("CheckPoint： checkInclude",content+" 与 "+included+" 等于匹配校验成功");
        } else {
            new ScreenShot().screenShots(errorPicName,picname);
            TestLog.info("CheckPoint： checkInclude","等于包含匹配校验失败！\n待校验的字符串为:" + content + "\n包含字符串为:" + included+"\n截图："+picname);
        }
    }

    //检查点强制报错,text-错误内容
    public static void checkError(String text,String picname){
        new ScreenShot().screenShots(errorPicName,picname);
        TestLog.info("CheckPoint： checkError",text);
    }
}
