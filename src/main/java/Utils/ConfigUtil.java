package Utils;

import java.io.*;
import java.util.Properties;

public class ConfigUtil {

    public static String getProperty(String key, String config){
        if(key == null || "".equals(key)){
           // LOG.error("请传入正确的key信息");
            return null;
        }

        if(config == null || "".equals(config)){
           // LOG.error("请传人正确的配置文件信息");
            return null;
        }
        return loadConfig(config).getProperty(key);
    }


    public static Properties loadConfig(String config){
        Properties properties = new Properties();
        try {
            InputStream inputStream = new FileInputStream(config);
            BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            properties.load(bf);
            inputStream.close(); // 关闭流
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static void main(String[] args) {
        String value = ConfigUtil.getProperty("image.maxSize", Constants.CONFIG_COMMON);
        System.out.println(value);
        System.out.println(ConfigUtil.getProperty("UDID",Constants.CAP_PROPERTIES));
    }
}
