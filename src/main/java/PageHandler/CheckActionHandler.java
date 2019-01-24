package PageHandler;

import Base.TestStep;
import Server.ResultData;
import Utils.ScreenShot;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import com.aventstack.extentreports.utils.ExceptionUtil;


import static ActionUtils.TextUtil.getText;

public class CheckActionHandler  {
	private static Logger log = LoggerFactory.getLogger(CheckActionHandler.class);

	//检查某一个case的执行，会终止用例
	public void caseCheck(TestStep step) throws Exception{
		String Actual=" ";
		String CaseID = step.getCaseid();
		String Expected =step.getExpect();//equals[XXXXX]
		String ExpectedType=Expected.substring(0,Expected.indexOf("["));
		String Expectedvalue=Expected.substring(Expected.indexOf("[")+1,Expected.indexOf("]"));
		String FailHint = step.getMessage();
		AndroidDriver<AndroidElement> driver=step.getAndroidDriver();
		if(driver==null){
			ResultData.SkipCase.add(CaseID);
			throw new RuntimeException("driver is null");
		}
		log.info("『正常测试』开始执行: " + "<" +step.getDesc() + ">");
		ScreenShot.screenshot(driver,CaseID);
		//统计所有结果
		String[] Result=new String[7];
		Result[0]=CaseID;Result[1]=step.getDesc();Result[2]=step.getValue();Result[3]=Expected;
		Result[4]=ScreenShot.screenshot(driver,CaseID);
		try {
			Actual= getText(driver,step.getElement());
			switch (ExpectedType){
				case "contain":
					if(Actual.trim().contains(Expectedvalue)){
						ResultData.PassCase.add(CaseID);
						Result[5]="PASS";Result[6]="测试通过";
						Assert.assertTrue(true);
					}else {
						ResultData.FailCase.add(CaseID);
						Result[5]="FAIL";Result[6]=FailHint +"  "+"Expected 【"+ Expected +"】"+"  "+"but found 【"+ Actual +"】";
						Assert.assertTrue(false,FailHint +"  "+"Expected 【"+ Expected +"】"+"  "+"but found 【"+ Actual +"】");
					}
					break;
				case "equals":
					if(Expectedvalue.equals(Actual.trim())){
						ResultData.PassCase.add(CaseID);
						Result[5]="PASS";Result[6]="测试通过";
						Assert.assertTrue(true);
					}else {
						ResultData.FailCase.add(CaseID);
						Result[5]="FAIL";Result[6]=FailHint +"  "+"Expected 【"+ Expected +"】"+"  "+"but found 【"+ Actual +"】";
						Assert.assertTrue(false,FailHint +"  "+"Expected 【"+ Expected +"】"+"  "+"but found 【"+ Actual +"】");
					}
					break;
				default:
					ResultData.SkipCase.add(CaseID);
					Result[5]="SKIP";Result[6]="未匹配到校验类型："+FailHint;
					break;
			}
			Thread.sleep(500);
		}catch (Error | InterruptedException e)  {
			ResultData.SkipCase.add(CaseID);
			Result[5]="SKIP";
			Result[6]=ExceptionUtil.getStackTrace(e);
			Assert.assertTrue(false,FailHint +"  "+"Expected 【"+ Expected +"】"+"  "+"but found 【"+ Actual +"】");
		}finally {
			ResultData.AllCase.add(Result);
		}
	}

	//检查某一步用力的执行，不会终止用例
	public void stepCheck(TestStep step) throws Exception{
		AndroidDriver<AndroidElement> driver=step.getAndroidDriver();
		if(driver==null){
			throw new RuntimeException("driver is null");
		}
		String Actual=" ";
		String CaseID = step.getCaseid();
		String Expected = step.getExpect();
		String ExpectedType=Expected.substring(0,Expected.indexOf("["));
		String Expectedvalue=Expected.substring(Expected.indexOf("[")+1,Expected.indexOf("]"));
		String FailHint = step.getMessage();
		System.out.println("『正常测试』开始执行: " + "<" +step.getDesc() + ">");
		try {
			Actual=getText(driver,step.getElement());
			switch (ExpectedType){
				case "equals":
					if(!Expectedvalue.equals(Actual.trim())){
						ScreenShot.screenshot(driver,step.getDesc());
						log.error(FailHint +"  "+"Expected 【"+ Expected +"】"+"  "+"but found 【"+ Actual +"】");
					}
					break;
				case "contains":
					if (!Actual.trim().contains(Expectedvalue)) {
						ScreenShot.screenshot(driver,step.getDesc());
						log.error(FailHint +"  "+"Expected 【"+ Expected +"】"+"  "+"but found 【"+ Actual +"】");
					}
					break;
				case "startsWith":
					if (!Actual.trim().startsWith(Expectedvalue)) {
						ScreenShot.screenshot(driver,step.getDesc());
						log.error(FailHint +"  "+"Expected 【"+ Expected +"】"+"  "+"but found 【"+ Actual +"】");
					}
					break;
				case "endWith":
					if (!Actual.trim().endsWith(Expectedvalue)) {
						ScreenShot.screenshot(driver,step.getDesc());
						log.error(FailHint +"  "+"Expected 【"+ Expected +"】"+"  "+"but found 【"+ Actual +"】");
					}
					break;
				default:
					log.error(Expected+"格式错误，请检查用例："+step.getDesc());
					break;
			}

			Thread.sleep(500);
		}catch (Error | InterruptedException e)  {
			ScreenShot.screenshot(driver,step.getDesc());
			log.error(FailHint +"  "+"Expected 【"+ Expected +"】"+"  "+"but found 【"+ Actual +"】");
		}
	}


}
