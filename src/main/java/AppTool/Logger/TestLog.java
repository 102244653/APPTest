package AppTool.Logger;

import AppTool.SelefTools.DateUtil;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestLog {

    //声明一个日志文件对象
    private TestLog logger;

    //日志文件名
    static String logname= new SimpleDateFormat("MMddHHmmss").format(new java.util.Date()).toString();

    //日志存放地址
    static String  strLogFileName= System.getProperties().getProperty("user.dir")+"\\Logs\\"+ logname+".txt";

    private static Thread thread;

    //构造器
    private TestLog(){
        logger= new TestLog();
        this.CreatLogFile();
        this.thread=Thread.currentThread();
    }

    //生成日志文件
    private  void CreatLogFile(){
        File file =new File(strLogFileName);
        if(file.exists()==false){
            try{
                file.createNewFile();
                System.out.print(strLogFileName+"  创建成功\n");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    /**
     * 向文件中写入内容
     * @param filepath 文件路径与名称
     * @param newstr 写入的内容
     * @return
     * @throws IOException
     */
    private static boolean writeFileContent(String newstr){
        Boolean bool = false;
        String filein = newstr+"\r\n";//新写入的行，换行
        String temp = "";
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        FileOutputStream fos = null;
        PrintWriter pw = null;
        try {
            File file =new File(strLogFileName);
            if(file.exists()==false){
                file.createNewFile();
                System.out.print(strLogFileName+"  创建成功\n");
            }
            //File file = new File(filepath);//文件路径(包括文件名称)
            //将文件读入输入流
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buffer = new StringBuffer();
            //文件原有内容
            for(int i=0;(temp =br.readLine())!=null;i++){
                buffer.append(temp);
                // 行与行之间的分隔符 相当于“\n”
                buffer = buffer.append(System.getProperty("line.separator"));
            }
            buffer.append(filein);
            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buffer.toString().toCharArray());
            pw.flush();
            bool = true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {
            //不要忘记关闭
            try {
                if (pw != null) {
                    pw.close();
                }
                if (fos != null) {
                    fos.close();
                }
                if (br != null) {
                    br.close();
                }
                if (isr != null) {
                    isr.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        }
        return bool;
    }

    public static void info(String classname,String info) {
        String s =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:ms").format(new java.util.Date());
        System.out.println(s+ "  [INFO]  { "+classname+" }  " + info);
        boolean is= writeFileContent(s+ "  [INFO]  { "+classname+" }  " + info);
        if(is==false){
            System.out.println("【"+s+" "+classname+"  "+info+" -日志记录失败 】");
        }
    }

    public static void debug(String classname,String info) {
        //设置日期格式
        String s =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:ms").format(new java.util.Date());
        System.out.println(s+ "  [DEBUG]  { "+classname+" }  " + info);
        boolean is= writeFileContent( s+ "  [DEBUG] { "+classname+" }  " + info);
        if(is==false){
            System.out.println("【"+s+" "+classname+"  "+info+" -日志记录失败 】");
        }
    }

    public static void error(String classname,String info) {
        //设置日期格式
        String s =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:ms").format(new java.util.Date());
        System.out.println(s+ "  [ERROR] { "+classname+" }  " + info);
        boolean is= writeFileContent(s+ "  [ERROR] { "+classname+" }  " + info);
        if(is==false){
            System.out.println("【"+s+" "+classname+"  "+info+"  -日志记录失败 】");
        }
    }

}
