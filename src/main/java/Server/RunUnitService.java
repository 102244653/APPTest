package Server;

import Base.PageAction;
import Base.TestCase;
import Base.TestStep;
import Base.TestUnit;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class RunUnitService {
    private TestUnit testunit;

    public RunUnitService(TestUnit testunit){
        this.testunit = testunit;
    }

    /**
     * <br>根据索引从TestUnit中获取测试用例</br>
     *
     * @param index
     * @return
     */
    public TestCase getCase(int index){
        int n = 0;
        if(testunit.getCaseMap() == null)
            return null;

        int size = testunit.getCaseMap().size();
        if(index < 0 || (index > 0 && index >= size))
            throw new IndexOutOfBoundsException("index=" + index + ", size=" + size);

        //遍历map内部Entry集合
        for(Map.Entry<String,TestCase> e : testunit.getCaseMap().entrySet()){
            if(n++ == index)
                return e.getValue();
        }

        return null;
    }

    /**
     * <br>根据用例名，即case元素中的id元素值，从TestUnit中获取测试用例</br>
     *
     * @param id
     * @return
     */
    public TestCase getCase(String id){
        return testunit.getCaseMap() == null ? null : testunit.getCaseMap().get(id);
    }

    /**
     * <br>执行测试用例</br>
     *
     * @param id
     * @throws Exception
     */
    public void runCase(String id){
        TestCase testCase = getCase(id);
        List<TestStep> steps = testCase.getSteps();

        for(TestStep step : steps){
            if(step.isCancel())
                continue;
            PageAction action = step.getAction();
            Class<?> clazz = action.handler();

            //如果PageAction实例有handler字段，则调用handler中的方法，否则直接调用run方法
            try {
                if (clazz != null) {
                    String key = step.getAction().key();
                    Method m = clazz.getDeclaredMethod(key, new Class<?>[]{TestStep.class});
                    m.invoke(clazz.newInstance(), step);//调用方法时在step里面取对应的参数即可，多个参数可以分割values
                } else {
                    action.run(step);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    /**
     * <br>根据step元素的值解析出对应的方法名</br>
     * 作用是将"-"后面的第一个字母转为大写，并且去掉“-”
     *
     * @param actionKey
     * @return
     */
    private String getMethodName(String actionKey){
        if(actionKey == null || "".equals(actionKey))
            throw new RuntimeException("empty action key");

        char[] arr = actionKey.toCharArray();
        char prevChar = '\0';
        StringBuilder sb = new StringBuilder();
        char splitChar = '-';

        for(char c : arr){
            if(c == splitChar){
                prevChar = c;
                continue;
            }
            if(prevChar == splitChar) {
                sb.append(Character.toUpperCase(c));
            } else {
                sb.append(c);
            }
            prevChar = c;
        }

        return sb.toString();
    }

    /**
     * <br>执行完毕，退出App程序</br>
     */
    public void closeApp() {
       AndroidXmlAnalytic.quiteapp();
    }



    /**
     * <br></br>
     *
     * @param command
     */
    public static  void AndroidAdb(String command) throws IOException {
        Runtime.getRuntime().exec(command);
    }



}
