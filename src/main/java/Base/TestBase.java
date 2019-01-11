package Base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import lombok.Data;

//测试基础变量
@Data
public class TestBase {
    //用例编号
    private String caseid;
    //用例名称
    private String casename;
    //安卓驱动
    private AndroidDriver<AndroidElement> driver;//读取用例时赋值parseTestStep

    private boolean cancel;
}
