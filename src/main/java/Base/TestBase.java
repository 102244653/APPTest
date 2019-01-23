package Base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;


//测试基础变量

public class TestBase {
    //用例编号
    private String caseid;
    //用例名称
    private String casename;
    //安卓驱动
    private AndroidDriver<AndroidElement> driver;//读取用例时赋值parseTestStep

    private boolean cancel;

    public String getId() {
        return caseid;
    }

    public void setId(String id) {
        this.caseid = id;
    }

    public String getName() {
        return casename;
    }

    public void setName(String name) {
        this.casename = name;
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }


    public AndroidDriver<AndroidElement> getAndroidDriver() {
        return driver;
    }

    public void setAndroidDriver(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
    }
}
