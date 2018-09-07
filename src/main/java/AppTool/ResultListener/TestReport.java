package AppTool.ResultListener;

import AppTool.SelefTools.DateUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**生成html测试报告*/
public class TestReport implements IReporter {
    public static String path = System.getProperties().getProperty("user.dir")+"\\TestReport";
    public static String reportName= "APPTest"+ DateUtil.format(DateUtil.time8)+".html";
    public static String dirc= DateUtil.format(DateUtil.CHECK_LOG_FORMAT);

    //报告存放地址
    private String Reportpath =path+"\\"+dirc+"\\"+reportName;

    //jason参数存放地址
    private String templatePath = System.getProperty("user.dir")+"\\Config\\reportdemo";

    //通过总数
    private int testsPass = 0;

    //失败总数
    private int testsFail = 0;

    //未执行总数
    private int testsSkip = 0;

    //开始时间
    private String beginTime;

    //执行总时间
    private long totalTime;

    //项目名称
    private String name="APP测试报告";

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        List<ITestResult> list = new ArrayList<ITestResult>();
        for (ISuite suite : suites) {
            Map<String, ISuiteResult> suiteResults = suite.getResults();
            for (ISuiteResult suiteResult : suiteResults.values()) {
                ITestContext testContext = suiteResult.getTestContext();
                IResultMap passedTests = testContext.getPassedTests();
                testsPass = testsPass + passedTests.size();
                IResultMap failedTests = testContext.getFailedTests();
                testsFail = testsFail + failedTests.size();
                IResultMap skippedTests = testContext.getSkippedTests();
                testsSkip = testsSkip + skippedTests.size();
                IResultMap failedConfig = testContext.getFailedConfigurations();
                list.addAll(this.listTestResult(passedTests));
                list.addAll(this.listTestResult(failedTests));
                list.addAll(this.listTestResult(skippedTests));
                list.addAll(this.listTestResult(failedConfig));
            }
        }
        this.sort(list);
        this.outputResult(list);
    }

    private ArrayList<ITestResult> listTestResult(IResultMap resultMap) {
        Set<ITestResult> results = resultMap.getAllResults();
        return new ArrayList<ITestResult>(results);
    }

    private void sort(List<ITestResult> list) {
        Collections.sort(list, new Comparator<ITestResult>() {
            @Override
            public int compare(ITestResult r1, ITestResult r2) {
                if (r1.getStartMillis() > r2.getStartMillis()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
    }

    private void outputResult(List<ITestResult> list) {
        try {
            List<ReportInfo> listInfo = new ArrayList<ReportInfo>();
            int index = 0;
            for (ITestResult result : list) {
                String tn = result.getTestContext().getCurrentXmlTest().getParameter("testCase");
                if(index==0){
                    SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss.SSS");
                    beginTime = formatter.format(new Date(result.getStartMillis()));
                    index++;
                }
                long spendTime = result.getEndMillis() - result.getStartMillis();
                totalTime += spendTime;
                String status = this.getStatus(result.getStatus());
                List<String> log = Reporter.getOutput(result);
                for (int i = 0; i < log.size(); i++) {
                    log.set(i, log.get(i).replaceAll("\"", "\\\\\""));
                }
                Throwable throwable = result.getThrowable();
                if(throwable!=null){
                    log.add(throwable.toString().replaceAll("\"", "\\\\\""));
                    StackTraceElement[] st = throwable.getStackTrace();
                    for (StackTraceElement stackTraceElement : st) {
                        log.add(("    " + stackTraceElement).replaceAll("\"", "\\\\\""));
                    }
                }
                ReportInfo info = new ReportInfo();
                info.setName(tn);
                info.setSpendTime((spendTime / 1000) + " S");
                //测试结果
                info.setStatus(status);
                //测试类名称
                info.setClassName(result.getInstanceName());
                //测试方法名称
                info.setMethodName(result.getName());
                //测试用例描述（可以替换成测试图片）
                String pic=result.getName()+".png";
                info.setDescription(pic);
                //result.getMethod().getDescription()
                // "<a target=\"_blank\" href=\""+pic+"\"> "+pic+"</a>"
                //测试log
                info.setLog(log);
                listInfo.add(info);
            }
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("testName", name);
            result.put("testPass", testsPass);
            result.put("testFail", testsFail);
            result.put("testSkip", testsSkip);
            result.put("testAll", testsPass+testsFail+testsSkip);
            result.put("beginTime", beginTime);
            result.put("totalTime", (totalTime/1000)+" S");
            result.put("testResult", listInfo);
            Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
            String template = this.read(templatePath);
            File dir = new File(path+"\\"+dirc);
            if (!dir.exists())
            {dir.mkdirs();}
            BufferedWriter output = new BufferedWriter( new OutputStreamWriter(new FileOutputStream(new File(Reportpath)),"UTF-8"));
            template = template.replaceFirst("\\$\\{resultData\\}", gson.toJson(result));
            output.write(template);
            output.flush();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String getStatus(int status) {
        String statusString = null;
        switch (status) {
            case 1:
                statusString = "成功";
                break;
            case 2:
                statusString = "失败";
                break;
            case 3:
                statusString = "跳过";
                break;
            default:
                break;
        }
        return statusString;
    }

    public static class ReportInfo {

        private String name;

        private String className;

        private String methodName;

        private String description;

        private String spendTime;

        private String status;

        private List<String> log;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getMethodName() {
            return methodName;
        }

        public void setMethodName(String methodName) {
            this.methodName = methodName;
        }

        public String getSpendTime() {
            return spendTime;
        }

        public void setSpendTime(String spendTime) {
            this.spendTime = spendTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<String> getLog() {
            return log;
        }

        public void setLog(List<String> log) {
            this.log = log;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

    }

    private String read(String path) {
        File file = new File(path);
        InputStream is = null;
        StringBuffer sb = new StringBuffer();
        try {
            is = new FileInputStream(file);
            int index = 0;
            byte[] b = new byte[1024];
            while ((index = is.read(b)) != -1) {
                sb.append(new String(b, 0, index));
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
