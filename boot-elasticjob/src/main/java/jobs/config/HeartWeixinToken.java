package jobs.config;

import com.alibaba.fastjson.JSON;
import jobs.common.HttpRequestClient;
import jobs.common.WeiXinClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

/**
 * Created by alan.zheng on 2018/2/1.
 */
public class HeartWeixinToken extends TimerTask {
    private static Logger logger = LoggerFactory.getLogger(HeartWeixinToken.class);
    @Autowired
    private HttpRequestClient httpRequestClient;
    @Override
    public void run() {
        String accessToken = null;
        Map<String, String> params = new HashMap<String, String>();
        params.put("grant_type", "client_credential");
        params.put("appid", "wx05aaf675c8f7b3ed");
        params.put("secret", "93187bebc44df53e567692d39173f0a3");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://api.weixin.qq.com/cgi-bin/token");
        stringBuilder.append("?grant_type=client_credential");
        stringBuilder.append("&appid=wx05aaf675c8f7b3ed");
        stringBuilder.append("&secret=93187bebc44df53e567692d39173f0a3");
        try {
            String retCode = httpRequestClient.doGet(stringBuilder.toString());
            logger.info("token返回信息" + retCode);
            Map<String, Object> tokenMap = JSON.parseObject(retCode, HashMap.class);
            accessToken = tokenMap.get("access_token").toString();
        } catch (Exception e) {
            logger.error("获取token异常");
        }
        WeiXinToken.setToken(accessToken);
    }
}
