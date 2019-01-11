package PageHandler;

import Base.TestStep;
import Utils.ScreenShot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;


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
		System.out.println("『正常测试』开始执行: " + "<" +step.getDesc() + ">");
		try {
			Actual= getText(step.getElement());
			switch (ExpectedType){
				case "contain":
					if(Actual.trim().contains(Expectedvalue)){
						Assert.assertTrue(true);
					}else {
						ScreenShot.screenshot(CaseID);
						Assert.assertTrue(false,FailHint);
					}
					break;
				default:
					break;
				case "equals":
					if(Expectedvalue.equals(Actual.trim())){
						Assert.assertTrue(true);
					}else {
						ScreenShot.screenshot(CaseID);
						Assert.assertTrue(false,FailHint +"  "+"Expected 【"+ Expected +"】"+"  "+"but found 【"+ Actual +"】");
					}
					break;
			}
			if(Expectedvalue.equals(Actual.trim())){
				Assert.assertTrue(true);
			}else {
				ScreenShot.screenshot(CaseID);
				Assert.assertTrue(false,FailHint +"  "+"Expected 【"+ Expected +"】"+"  "+"but found 【"+ Actual +"】");
			}
			Thread.sleep(500);
		}catch (Error | InterruptedException e)  {
			ScreenShot.screenshot(CaseID);
			Assert.assertTrue(false,FailHint +"  "+"Expected 【"+ Expected +"】"+"  "+"but found 【"+ Actual +"】");
		}
	}

	//检查某一步用力的执行，不会终止用例
	public void stepCheck(TestStep step) throws Exception{
		String Actual=" ";
		String CaseID = step.getCaseid();
		String Expected = step.getExpect();
		String ExpectedType=Expected.substring(0,Expected.indexOf("["));
		String Expectedvalue=Expected.substring(Expected.indexOf("[")+1,Expected.indexOf("]"));
		String FailHint = step.getMessage();
		System.out.println("『正常测试』开始执行: " + "<" +step.getDesc() + ">");
		try {
			Actual=getText(step.getElement());
			switch (ExpectedType){
				case "equals":
					if(!Expectedvalue.equals(Actual.trim())){
						ScreenShot.screenshot(step.getDesc());
						log.error(FailHint +"  "+"Expected 【"+ Expected +"】"+"  "+"but found 【"+ Actual +"】");
					}
					break;
				case "contains":
					if (!Actual.trim().contains(Expectedvalue)) {
						ScreenShot.screenshot(step.getDesc());
						log.error(FailHint +"  "+"Expected 【"+ Expected +"】"+"  "+"but found 【"+ Actual +"】");
					}
					break;
				case "startsWith":
					if (!Actual.trim().startsWith(Expectedvalue)) {
						ScreenShot.screenshot(step.getDesc());
						log.error(FailHint +"  "+"Expected 【"+ Expected +"】"+"  "+"but found 【"+ Actual +"】");
					}
					break;
				case "endWith":
					if (!Actual.trim().endsWith(Expectedvalue)) {
						ScreenShot.screenshot(step.getDesc());
						log.error(FailHint +"  "+"Expected 【"+ Expected +"】"+"  "+"but found 【"+ Actual +"】");
					}
					break;
				default:
					log.error(Expected+"格式错误，请检查用例："+step.getDesc());
					break;
			}

			Thread.sleep(500);
		}catch (Error | InterruptedException e)  {
			ScreenShot.screenshot(step.getDesc());
			log.error(FailHint +"  "+"Expected 【"+ Expected +"】"+"  "+"but found 【"+ Actual +"】");
		}
	}


}
