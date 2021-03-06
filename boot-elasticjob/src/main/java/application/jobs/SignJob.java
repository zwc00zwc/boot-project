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
        logger.info("随机到点数:"+total);
        try {
            Thread.sleep(total*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            //13059758695
            String jxToken = getToken("JXmufrTPbmzGaGTCld7DJA==","HnxrxgodkpzHI1SS5GUWiA==","8905da6cb8ded9bf57977e7710a4d279df18476d");
            sign(jxToken,"8905da6cb8ded9bf57977e7710a4d279df18476d");
        } catch (Exception e) {
            logger.error("token获取失败JXmufrTPbmzGaGTCld7DJA==,签到异常",e);
        }

        try {
            //13575658971
            String ofToken = getToken("oF9X7mGq+0HedeuvGrGOyw==","Jemr+UzSmwwpfD0MrTY9BQ==","f38bff628f390fb81d32c545e4c7c660a8b9b4d7");
            sign(ofToken,"f38bff628f390fb81d32c545e4c7c660a8b9b4d7");
        } catch (Exception e) {
            logger.error("token获取失败oF9X7mGq+0HedeuvGrGOyw==,签到异常",e);
        }

        try {
            //13588712642
            String uuToken = getToken("uuCBWAqOOTth3tbSiL2SlQ==","lVPgco7cWAvlj3/vjIFkGw==","4fbb14f0f2b5aae5c11eec2deeeebd7d2e33fcf0");
            sign(uuToken,"4fbb14f0f2b5aae5c11eec2deeeebd7d2e33fcf0");
            exchange(uuToken,"4fbb14f0f2b5aae5c11eec2deeeebd7d2e33fcf0");
        } catch (Exception e) {
            logger.error("token获取失败uuCBWAqOOTth3tbSiL2SlQ==，签到失败",e);
        }
    }

    private void sign(String token,String device){
        Map map = new HashMap();
        map.put("device",device);
        map.put("token",token);
        String result = httpRequestClient.doPost("https://api.yrw.com/security/member/goSignIn",map,"1.0.0");
        logger.info("device:"+device+"token:"+token+"签到返回信息"+result);
    }

    private String getToken(String username,String password,String device){
        Map map = new HashMap();
        map.put("username",username);
        map.put("password",password);
        map.put("loginSource","2");
        map.put("device",device);
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
    private void exchange(String token,String device){
        Map map = new HashMap();
        map.put("invokeParameters",
                "args_goodId_1_long_55&args_amount_1_integer_45&args_goodsCount_1_integer_1");
        map.put("loginSource",2);
        map.put("device",device);
        map.put("token",token);
        String result = httpRequestClient.doPost("https://api.yrw.com/security/find/dynamicInvoke?invokeMethod=3",map,"1.0.0");
        logger.info("兑换结果返回信息" + result);
    }

//    public static void main(String[] args){
//        //控制随机事件 (1小时内)
//        Random random = new Random();
//        //获取千位数
//        Integer q = random.nextInt(3);
//        //获取百位数
//        Integer b = random.nextInt(10);
//        //获取十位数
//        Integer s = random.nextInt(10);
//        //获取个位数
//        Integer g = random.nextInt(10);
//        Integer total = q * 1000 + b * 100 + s * 10 + g;
//        System.out.print("当前随机秒数:" + total);
//    }
}
