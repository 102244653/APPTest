package AppTool.SelefTools;

import AppTool.Logger.TestLog;

/**
 * Created by Administrator on 2017/9/27.
 */
public class Time {

    public static void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            TestLog.info("Time: sleep",e.getLocalizedMessage());
        }
    }
}
