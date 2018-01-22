package jobs;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import jobs.common.HttpRequestClient;
import jobs.common.WeiXinClient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alan.zheng on 2018/1/16.
 */
public class MonitorTransferJob implements SimpleJob {
    private static Logger logger = LoggerFactory.getLogger(MonitorTransferJob.class);
    @Autowired
    private HttpRequestClient httpRequestClient;

    @Autowired
    private WeiXinClient weiXinClient;

    @Resource
    private ThreadPoolTaskExecutor taskExecutor;

    public void execute(ShardingContext shardingContext) {
        try {
            logger.info("开始监控高收益转让项目");
            String resultStr = httpRequestClient.doGet("https://www.yrw.com/products/queryTransferProjectList?currentPage=1&pageSize=8&orderSource=rateDesc");
            JSONObject jsonObject = JSONObject.parseObject(resultStr);
            JSONObject ar = null;
            BigDecimal rate = null;
            BigDecimal availableBalance = null;
            BigDecimal baseRate = new BigDecimal("40");
            if ((Boolean) jsonObject.get("success")){
                JSONArray jsonArray = (JSONArray) jsonObject.get("resultList");
                for (int i = 0;i<jsonArray.size();i++){
                    ar = (JSONObject) jsonArray.get(i);
                    ar.get("minAnnualizedRate");
                    rate = new BigDecimal(ar.get("minAnnualizedRate")+"");
                    availableBalance = new BigDecimal(ar.get("availableBalance")+"");
                    if (baseRate.compareTo(rate)<0){
                        //通知
                        weiXinClient.monitorTransferProject(ar.get("name")+"",rate,availableBalance,"oYzLx0oYFJyaV3qGprKHm6DSRHBA");
                        //自动投资
                        taskExecutor.execute(new TransactionThread(ar,availableBalance,"JXmufrTPbmzGaGTCld7DJA==","HnxrxgodkpzHI1SS5GUWiA=="));
                        Thread.sleep(500);
                        taskExecutor.execute(new TransactionThread(ar,availableBalance,"oF9X7mGq+0HedeuvGrGOyw==","Jemr+UzSmwwpfD0MrTY9BQ=="));
                    }
                }
            }
        } catch (Exception e) {
            logger.error("监控转让项目异常",e);
        }
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

    private class TransactionThread implements Runnable{
        private JSONObject ar;
        private BigDecimal availableBalance;
        private String userName;
        private String password;
        public TransactionThread(JSONObject _ar,BigDecimal _availableBalance,String _userName,String _password){
            ar = _ar;
            availableBalance = _availableBalance;
            userName = _userName;
            password = _password;
        }
        public void run() {
            //自动投资
            try {
                Map<String,Object> postMap = new HashMap<String, Object>();
                String token = getToken(userName,password);
                String thumbnail = ar.get("thumbnail")+"";
                if (StringUtils.isNotEmpty(thumbnail)){
                    String[] args = thumbnail.split("/");
                    String projectid = args[args.length - 1];
                    postMap.put("projectId",projectid.substring(0,9));
                }
                BigDecimal invest = availableBalance.multiply(new BigDecimal("0.6"));
                int a = invest.intValue()/1000 + 1;
                int b = a*1000;
                BigDecimal totalInvest = new BigDecimal(b);
                postMap.put("transferId",ar.get("id"));
                postMap.put("projectCategory","2");
                postMap.put("transferPrincipal",totalInvest);
                postMap.put("device","8905da6cb8ded9bf57977e7710a4d279df18476d");
                postMap.put("token",token);
                String result = httpRequestClient.doPost("https://api.yrw.com/security/order/createOrder",postMap,"1.7.0");
                System.out.print(result);
                //支付
                JSONObject resultJson = JSONObject.parseObject(result);
                if ((Boolean) resultJson.get("success")){
                    JSONObject orderResult = (JSONObject) resultJson.get("result");
                    Map<String,Object> payMap = new HashMap<String, Object>();
                    payMap.put("orderNo",orderResult.get("orderNo"));
                    payMap.put("usedCapital",orderResult.get("investAmount"));
                    payMap.put("device","8905da6cb8ded9bf57977e7710a4d279df18476d");
                    payMap.put("token",token);
                    String payResult = httpRequestClient.doPost("https://api.yrw.com/security/transaction/pay/order/cashDesk",payMap,"1.7.0");
                    System.out.print(payResult);
                }
            } catch (Exception e) {
                logger.error("自动投资异常",e);
            }
        }
    }
}
