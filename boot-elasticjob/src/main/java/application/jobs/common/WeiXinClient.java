package application.jobs.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import application.jobs.config.WeiXinToken;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alan.zheng on 2018/1/16.
 */
@Component
public class WeiXinClient {
    private static Logger logger = LoggerFactory.getLogger(WeiXinClient.class);
    @Autowired
    private HttpRequestClient httpRequestClient;

    @Resource
    private ThreadPoolTaskExecutor taskExecutor;

    public String getAccessToken(){
        String token = WeiXinToken.getToken();
        if (StringUtils.isEmpty(token)){
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
            return accessToken;
        }
        return token;
    }

    public void monitorTransferProject(String projectName, BigDecimal rate, BigDecimal availableBalance, String openid){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("touser",openid);
        jsonObject.put("template_id","A3vRcZNXUvvOmkGeVD2ZVIkG9WxHn1XSiT703WR92rs");

        JSONObject dataJsonObject = new JSONObject();
        JSONObject valueJsonObject = null;
        valueJsonObject = new JSONObject();
        valueJsonObject.put("value","转让项目高收益");
        valueJsonObject.put("color","#173177");
        dataJsonObject.put("first",valueJsonObject);
        valueJsonObject = new JSONObject();
        valueJsonObject.put("value",projectName);
        valueJsonObject.put("color","#173177");
        dataJsonObject.put("keyword1",valueJsonObject);
        valueJsonObject = new JSONObject();
        valueJsonObject.put("value", rate+"%");
        valueJsonObject.put("color","#173177");
        dataJsonObject.put("keyword2",valueJsonObject);
        valueJsonObject = new JSONObject();
        valueJsonObject.put("value", availableBalance+"元");
        valueJsonObject.put("color","#173177");
        dataJsonObject.put("keyword3",valueJsonObject);
        valueJsonObject = new JSONObject();
        valueJsonObject.put("value","速度投资");
        valueJsonObject.put("color","#173177");
        dataJsonObject.put("remark",valueJsonObject);
        jsonObject.put("data",dataJsonObject);

        taskExecutor.execute(new SendWeixinMsgThread(jsonObject));
    }

    /**
     * 发送微信消息线程
     */
    private class SendWeixinMsgThread implements Runnable{
        private JSONObject jsonObject;
        public SendWeixinMsgThread(final JSONObject _jsonObject){
            jsonObject=_jsonObject;
        }
        public void run() {
            String accessToken = getAccessToken();
            String postUrl="https://api.weixin.qq.com/cgi-bin/message/template/send";
            String retCode = httpRequestClient.doJsonPost(postUrl+"?access_token="+accessToken+"", jsonObject.toString());
            if (StringUtils.isEmpty(retCode)) {
                logger.error("微信发送模板消息无返回");
            }
            Map<String, Object> ticketMap = JSON.parseObject(retCode, java.util.HashMap.class);
            String errcode = ticketMap.get("errcode").toString();
            String errmsg = ticketMap.get("errmsg").toString();
            if (!"0".equals(errcode)){
                logger.error("微信发送模板消息异常" + errmsg + "");
            }
        }
    }
}
