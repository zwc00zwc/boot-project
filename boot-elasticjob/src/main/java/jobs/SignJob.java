package jobs;

import com.alibaba.fastjson.JSONObject;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import jobs.common.HttpRequestClient;
import org.apache.http.client.methods.HttpPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alan.zheng on 2017/11/20.
 */
public class SignJob implements SimpleJob {
    private static Logger logger = LoggerFactory.getLogger(SignJob.class);
    @Autowired
    private HttpRequestClient httpRequestClient;

    public void execute(ShardingContext shardingContext) {
        String token = null;
        Map map = null;
        try {
            token = getToken("JXmufrTPbmzGaGTCld7DJA==","HnxrxgodkpzHI1SS5GUWiA==");
        } catch (Exception e) {
            logger.error("token获取失败JXmufrTPbmzGaGTCld7DJA==",e);
        }
        map = new HashMap();
        map.put("device","8905da6cb8ded9bf57977e7710a4d279df18476d");
        map.put("token",token);
        try {
            sign(map);
        } catch (Exception e) {
            logger.error("签到失败",e);
        }

        try {
            token = getToken("oF9X7mGq+0HedeuvGrGOyw==","Jemr+UzSmwwpfD0MrTY9BQ==");
        } catch (Exception e) {
            logger.error("token获取失败oF9X7mGq+0HedeuvGrGOyw==",e);
        }
        map = new HashMap();
        map.put("device","8905da6cb8ded9bf57977e7710a4d279df18476d");
        map.put("token",token);
        try {
            sign(map);
        } catch (Exception e) {
            logger.error("签到失败",e);
        }

        try {
            token = getToken("uuCBWAqOOTth3tbSiL2SlQ==","0wuzscz5gMK9rDfoRhcx2A==");
        } catch (Exception e) {
            logger.error("token获取失败uuCBWAqOOTth3tbSiL2SlQ==",e);
        }
        map = new HashMap();
        map.put("device","8905da6cb8ded9bf57977e7710a4d279df18476d");
        map.put("token",token);
        try {
            sign(map);
        } catch (Exception e) {
            logger.error("签到失败",e);
        }
    }

    private void sign(Map map){
        String result = httpRequestClient.doPost("https://api.yrw.com/security/member/goSignIn",map,"1.0.0");
    }

    private String getToken(String username,String password){
        Map map = new HashMap();
        map.put("username",username);
        map.put("password",password);
        map.put("loginSource","2");
        map.put("device","8905da6cb8ded9bf57977e7710a4d279df18476d");
        map.put("equipment","iPhone");
        String response = httpRequestClient.doPost("https://api.yrw.com/logining",map,"1.0.0");
        JSONObject jsonObject = JSONObject.parseObject(response);
        JSONObject o = (JSONObject)jsonObject.get("result");
        return o.get("token").toString();
    }
}
