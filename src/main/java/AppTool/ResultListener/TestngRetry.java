package AppTool.ResultListener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;

public class TestngRetry implements IRetryAnalyzer {

    private int retryCount = 1;
    private static int maxRetryCount=1;

    public boolean retry(ITestResult result) {
        if (retryCount <= maxRetryCount) {
            String message = "Retry for [" + result.getName() + "] on class [" + result.getTestClass().getName() + "] Retry "
                    + retryCount + " times";
           // logger.info(message);
            Reporter.setCurrentTestResult(result);
            Reporter.log("RunCount=" + (retryCount + 1));
            retryCount++;
            return true;
        }
        return false;
    }
}
