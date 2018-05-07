package application.jobs;

import com.alibaba.fastjson.JSONObject;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import application.jobs.common.HttpRequestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by alan.zheng on 2017/11/20.
 */
public class SignJob implements SimpleJob {
    private static Logger logger = LoggerFactory.getLogger(SignJob.class);
    @Autowired
    private HttpRequestClient httpRequestClient;

    public void execute(ShardingContext shardingContext) {
        //控制随机事件 (1小时内)
        Random random = new Random();
        //获取千位数
        Integer q = random.nextInt(3);
        //获取百位数
        Integer b = random.nextInt(10);
        //获取十位数
        Integer s = random.nextInt(10);
        //获取个位数
        Integer g = random.nextInt(10);
        Integer total = q * 1000 + b * 100 + s * 10 + g;
        try {
            Thread.sleep(total*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String token = null;
        Map map = null;
        try {
            //13059758695
            token = getToken("JXmufrTPbmzGaGTCld7DJA==","HnxrxgodkpzHI1SS5GUWiA==");
        } catch (Exception e) {
            logger.error("token获取失败JXmufrTPbmzGaGTCld7DJA==",e);
        }
        try {
            sign(token,"8905da6cb8ded9bf57977e7710a4d279df18476d");
        } catch (Exception e) {
            logger.error("签到失败",e);
        }

        try {
            //13575658971
            token = getToken("oF9X7mGq+0HedeuvGrGOyw==","Jemr+UzSmwwpfD0MrTY9BQ==");
        } catch (Exception e) {
            logger.error("token获取失败oF9X7mGq+0HedeuvGrGOyw==",e);
        }
        try {
            sign(token,"noDevice");
        } catch (Exception e) {
            logger.error("签到失败",e);
        }

        try {
            //13588712642
            token = getToken("uuCBWAqOOTth3tbSiL2SlQ==","0wuzscz5gMK9rDfoRhcx2A==");
        } catch (Exception e) {
            logger.error("token获取失败uuCBWAqOOTth3tbSiL2SlQ==",e);
        }
        try {
            sign(token,"4fbb14f0f2b5aae5c11eec2deeeebd7d2e33fcf0");
            exchange(token);
        } catch (Exception e) {
            logger.error("签到失败",e);
        }
    }

    private void sign(String token,String device){
        Map map = new HashMap();
        map.put("device",device);
        map.put("token",token);
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

    /**
     * 兑换优惠券
     * @param token
     */
    private void exchange(String token){
        Map map = new HashMap();
        map.put("invokeParameters",
                "args_goodId_1_long_55&args_amount_45_integer_174&args_goodsCount_1_integer_1");
        map.put("loginSource",2);
        map.put("device","4fbb14f0f2b5aae5c11eec2deeeebd7d2e33fcf0");
        map.put("token",token);
        String result = httpRequestClient.doPost("https://api.yrw.com/security/find/dynamicInvoke?invokeMethod=3",map,"1.0.0");
    }

    public static void main(String[] args){
        //控制随机事件 (1小时内)
        Random random = new Random();
        //获取千位数
        Integer q = random.nextInt(3);
        //获取百位数
        Integer b = random.nextInt(10);
        //获取十位数
        Integer s = random.nextInt(10);
        //获取个位数
        Integer g = random.nextInt(10);
        Integer total = q * 1000 + b * 100 + s * 10 + g;
        System.out.print("当前随机秒数:" + total);
    }
}
