package AppTool.SelfAssert;

import AppTool.Logger.TestLog;
import org.testng.Assert;

import java.util.regex.Pattern;

public class SelfAssert extends Assert {

    /**
     * 断言字符是否已某个字符串开头
     *
     * @param message 断言错误消息
     * @param content 待断言字符串
     * @param prefix  前缀表达式
     */
    public static void assertStartWith(String content, String prefix, String message) {
        if (message != null) {
            TestLog.info("SelfAssert: assertStartWith", message);
        }
        if (content.startsWith(prefix)) {
            TestLog.info("SelfAssert: assertStartWith","前缀匹配校验成功");
        } else {
            TestLog.error("SelfAssert: assertStartWith","前缀匹配校验失败！\n待校验的字符窜为:" + content + "\n校验的前缀表达式为:" + prefix);
            fail();
        }
    }

    /**
     * 断言字符是否已某个字符串开头
     *
     * @param content 待断言字符串
     * @param prefix  前缀表达式
     */
    public static void assertStartWith(String content, String prefix) {
        assertStartWith(content, prefix, null);
    }


    /**
     * 断言字符是否已某个字符串结尾
     *
     * @param message 断言错误消息
     * @param content 待断言字符串
     * @param endfix  前缀表达式
     */
    public static void assertEndWith(String content, String endfix, String message) {
        if (message != null)
            TestLog.info("SelfAssert: assertEndWith",message);

        if (content.endsWith(endfix)) {
            TestLog.info("SelfAssert: assertEndWith","后缀匹配校验成功！");
        } else {
            TestLog.error("SelfAssert: assertEndWith","后缀匹配校验失败！\n待校验的字符窜为:" + content + "\n校验的后缀表达式为:" + endfix);
            fail();
        }
    }

    /**
     * 断言字符是否已某个字符串结尾
     *
     * @param content 待断言字符串
     * @param endfix  前缀表达式
     */
    public static void assertEndWith(String content, String endfix) {
        assertEndWith(content, endfix, null);
    }


    /**
     * 根据正则表达式断言是否匹配
     *
     * @param message 断言错误信息
     * @param matcher 待校验的字符串
     * @param regex   校验的正则表达式
     */
    public static void assertMatch(String matcher, String regex, String message) {
        if (message != null)
            TestLog.info("SelfAssert: assertMatch",message);

        if (Pattern.matches(regex, matcher)) {
            TestLog.info("SelfAssert: assertMatch","匹配校验成功！");
        } else {
            TestLog.error("SelfAssert: assertMatch","匹配校验失败！\n待校验的字符串为:" + matcher + "\n校验的正则表达式为:" + regex);
            fail();
        }
    }

    /**
     * 根据正则表达式断言是否匹配
     *
     * @param matcher 待校验的字符串
     * @param regex   校验的正则表达式
     */
    public static void assertMatch(String matcher, String regex) {
        assertMatch(matcher, regex, null);
    }

    /**
     * 根据正则表达式断言是否匹配
     *
     * @param message 断言错误信息
     * @param matcher 待校验的字符串
     * @param regex   校验的正则表达式
     */
    public static void assertNoMatch(String matcher, String regex, String message) {
        if (message != null)
            TestLog.info("SelfAssert: assertNoMatch",message);

        if (!Pattern.matches(regex, matcher)) {
            TestLog.info("SelfAssert: assertNoMatch","匹配校验成功！");
        } else {
            TestLog.error("SelfAssert: assertNoMatch","匹配校验失败！\n待校验的字符串为:" + matcher + "\n校验的正则表达式为:" + regex);
            fail();
        }
    }

    /**
     * 根据正则表达式断言是否匹配
     *
     * @param matcher 待校验的字符串
     * @param regex   校验的正则表达式
     */
    public static void assertNoMatch(String matcher, String regex) {
        assertNoMatch(matcher, regex, null);
    }


    /**
     * 断言字符串中是否包含某些字符
     *
     * @param message  断言错误消息
     * @param content  断言的字符串为
     * @param included 包含的字符串
     */
    public static void assertInclude(String content, String included, String message) {
        if (message != null)
            TestLog.info("SelfAssert: assertInclude",message);
        if (content.contains(included)) {
            TestLog.info("SelfAssert: assertInclude","匹配校验成功！");
        } else {
            TestLog.error("SelfAssert: assertInclude","匹配校验失败！\n待校验的字符串为:" + content + "\n包含字符串为:" + included);
            fail(message);
        }
    }

    /**
     * 断言字符串中是否包含某些字符
     *
     * @param content  断言的字符串为
     * @param included 包含的字符串
     */
    public static void assertInclude(String content, String included) {
        assertInclude(content, included, null);
    }
}
