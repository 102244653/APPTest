package PageHandler;


import Base.TestStep;
import Utils.DBHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DBActionHandler {
	private static Logger log = LoggerFactory.getLogger(DBActionHandler.class);

	public void dbInserta(TestStep step) throws Exception{
		System.out.println("『正常测试』开始执行: "+ step.getValue() + " <" +step.getDesc() + ">");
		int n = DBHelper.insert(step.getValue());
		if(n > 0){	
		}
	}

	
	public void dbInsertma(TestStep step) throws Exception{
		System.out.println("『正常测试』开始执行: "+ step.getValue() + " <" +step.getDesc() + ">");
		int n = DBHelper.insert1(step.getValue());
		if(n > 0){	
		}
	}

	
	public void dbInsertmssha(TestStep step) throws Exception{
		System.out.println("『正常测试』开始执行: "+ step.getValue() + " <" +step.getDesc() + ">");
		int n = DBHelper.insert2(step.getValue());
		if(n > 0){	
		}
	}

	
	public void dbDeletea(TestStep step) throws Exception{
		System.out.println("『正常测试』开始执行: "+ step.getValue() + " <" +step.getDesc() + ">");
		int n = DBHelper.delete(step.getValue());
		if(n > 0){	
		}
	}

	
	public void dbDeletema(TestStep step) throws Exception{
		System.out.println("『正常测试』开始执行: "+ step.getValue() + " <" +step.getDesc() + ">");
		int n = DBHelper.delete1(step.getValue());
		if(n > 0){	
		}
	}
	

	public void dbDeletemssha(TestStep step) throws Exception{
		System.out.println("『正常测试』开始执行: "+ step.getValue() + " <" +step.getDesc() + ">");
		int n = DBHelper.delete2(step.getValue());
		if(n > 0){	
		}
	}
	

	public void dbUpdatea(TestStep step) throws Exception{
		System.out.println("『正常测试』开始执行: "+ step.getValue() + " <" +step.getDesc() + ">");
		String sql = step.getValue();
//		System.err.println("Query-update "+sql);
		int n = DBHelper.update(sql);
		if(n > 0){
			System.out.println("『正常测试』开始执行: "+ step.getValue() + " <" +step.getDesc() + ">");
//			Reporter.log(base.getDesc());
		}	
	}
	

	public void dbUpdatema(TestStep step) throws Exception{
		System.out.println("『正常测试』开始执行: "+ step.getValue() + " <" +step.getDesc() + ">");
		String sql = step.getValue();
//		System.err.println("Query-update "+sql);
		int n = DBHelper.update1(sql);
		if(n > 0){
			System.out.println("『正常测试』开始执行: "+ step.getValue() + " <" +step.getDesc() + ">");
//			Reporter.log(base.getDesc());
		}	
	}

	public void dbUpdatemssha(TestStep step) throws Exception{
		System.out.println("『正常测试』开始执行: "+ step.getValue() + " <" +step.getDesc() + ">");
		String sql = step.getValue();
//		System.err.println("Query-update "+sql);
		int n = DBHelper.update2(sql);
		if(n > 0){
			System.out.println("『正常测试』开始执行: "+ step.getValue() + " <" +step.getDesc() + ">");
//			Reporter.log(base.getDesc());
		}	
	}


	public void dbProcedure(TestStep step) throws Exception{
		String params = step.getValue();
		System.out.println("『正常测试』开始执行: "+ step.getDetails().get("prc_name") + params + " <" +step.getDesc() + ">");
		int n =DBHelper.procedure(step.getDetails().get("prc_name"),params,null);
		if(n > 0){	
		}
	}
	
	public void dbProcedurem(TestStep step) throws Exception{
		String params =step.getValue();
		System.out.println("『正常测试』开始执行: "+ step.getDetails().get("prc_name") + params + " <" +step.getDesc() + ">");
		int n =DBHelper.procedure1(step.getDetails().get("prc_name"),params,null);
		if(n > 0){	
		}
	}
}
