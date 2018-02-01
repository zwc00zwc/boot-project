package jobs.config;

import java.util.Map;

/**
 * Created by alan.zheng on 2018/2/1.
 */
public class TransferProjectConfig {
    private static Map projectConfigMap;

    public static Map getProjectConfigMap() {
        return projectConfigMap;
    }

    public static void setProjectConfigMap(String key,String value) {
        projectConfigMap.put(key,value);
    }
}
