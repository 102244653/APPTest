package ElementUtil;

import com.google.common.base.Function;
import io.appium.java_client.android.AndroidDriver;

/**
 * Created by Administrator on 2017/9/4.
 */
public interface ExpectedCondition<T> extends Function<AndroidDriver, T> {
}
