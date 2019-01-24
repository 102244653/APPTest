package ElementUtil;

import Utils.ConfigUtil;
import Utils.Constants;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetByLocator {
	private static Logger logger = LoggerFactory.getLogger(GetByLocator.class);

	public static AndroidElement getAppElement(AndroidDriver<AndroidElement> driver, String key)  {
		//根据变量 ElementNameInpropFile，从属性配置文件中读取对应的配置对象
		String element=null;
		try{
			element= ConfigUtil.getProperty(key, Constants.Elements);
		}catch (Exception e){}
		if(element==null){
			element=key;
		}
		// 将配置对象中的定位类型存到 locatorType 变量，将定位表达式的值存入到 locatorValue 变量
        String locatorType = element.split(">")[0];
        String locatorValue = element.split(">")[1];
		// 输出  locatorType 变量值和locatorValue 变量值，验证是否赋值正确
        logger.info("获取的定位类型：" + locatorType + "\t 获取的定位表达式: " + locatorValue );
		// 根据 locatorType 的变量值内容判断，返回何种定位方式的 By 对象
		if(locatorType.toLowerCase().equals("id"))
			return WaitElement.findElement(driver,By.id(locatorValue));
		else if(locatorType.toLowerCase().equals("text"))
			return driver.findElementByAndroidUIAutomator("new UiSelector().text(\""+locatorValue+"\")");
		else if((locatorType.toLowerCase().equals("classname"))||(locatorType.toLowerCase().equals("class")))
			return WaitElement.findElement(driver,By.className(locatorValue));
		else if((locatorType.toLowerCase().equals("tagname"))||(locatorType.toLowerCase().equals("tag")))
			return WaitElement.findElement(driver,By.tagName(locatorValue));
		else if((locatorType.toLowerCase().equals("linktext"))||(locatorType.toLowerCase().equals("link")))
			return WaitElement.findElement(driver,By.linkText(locatorValue));
		else if(locatorType.toLowerCase().equals("partiallinktext"))
			return WaitElement.findElement(driver,By.partialLinkText(locatorValue));
		else if((locatorType.toLowerCase().equals("cssselector"))||(locatorType.toLowerCase().equals("css")))
			return WaitElement.findElement(driver,By.cssSelector(locatorValue));
		else if(locatorType.toLowerCase().equals("xpath"))
			return WaitElement.findElement(driver,By.xpath(locatorValue));
		else
			try {
				throw new Exception("输入的 locator type 未在程序中被定义：" + locatorType );
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null;
    }
}
