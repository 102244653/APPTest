package AppTool.WaitElement;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.Clock;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.SystemClock;

import java.util.concurrent.TimeUnit;

public class AndroidDriverWait extends FluentWait<AndroidDriver> {
    public static final long DEFAULT_SLEEP_TIMEOUT = 500L;
    private final AndroidDriver driver;

    public AndroidDriverWait(AndroidDriver driver, long timeOutInSeconds) {
        this(driver, new SystemClock(), Sleeper.SYSTEM_SLEEPER, timeOutInSeconds, 500L);
    }

    public AndroidDriverWait(AndroidDriver driver, long timeOutInSeconds, long sleepInMillis) {
        this(driver, new SystemClock(), Sleeper.SYSTEM_SLEEPER, timeOutInSeconds, sleepInMillis);
    }

    public AndroidDriverWait(AndroidDriver driver, Clock clock, Sleeper sleeper, long timeOutInSeconds, long sleepTimeOut) {
        super(driver, clock, sleeper);
        this.withTimeout(timeOutInSeconds, TimeUnit.SECONDS);
        this.pollingEvery(sleepTimeOut, TimeUnit.MILLISECONDS);
        this.ignoring(NotFoundException.class);
        this.driver = driver;
    }

    protected RuntimeException timeoutException(String message, Throwable lastException) {
        TimeoutException ex = new TimeoutException(message, lastException);
        ex.addInfo("Driver info", this.driver.getClass().getName());
        if(this.driver instanceof AndroidDriver) {
            AndroidDriver remote = this.driver;
            if(remote.getSessionId() != null) {
                ex.addInfo("Session ID", remote.getSessionId().toString());
            }

            if(remote.getCapabilities() != null) {
                ex.addInfo("Capabilities", remote.getCapabilities().toString());
            }
        }

        throw ex;
    }

}
