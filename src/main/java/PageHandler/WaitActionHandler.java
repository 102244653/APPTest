package PageHandler;
import Base.TestStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * 等待动作处理类
 */
public class WaitActionHandler {
	private static Logger logger = LoggerFactory.getLogger(WaitActionHandler.class);

	/**
	 * 强制等待
	 * @param
	 * @param step
	 */
	public void waitForced(TestStep step){
		try {
			System.out.println("『正常测试』开始执行: " + "<" +step.getDesc() + ">");
			String waitTime = step.getValue();
			Thread.sleep(Long.valueOf(waitTime));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Android端隐式等待
	 * @param step
	 */
	public void androidImplicit(TestStep step){
		System.out.println("『正常测试』开始执行: " + "<" +step.getDesc() + ">");
		long waitTime = Long.valueOf(step.getValue());
		step.getDriver().manage().timeouts().implicitlyWait(waitTime, TimeUnit.MILLISECONDS);
	}
	
}
