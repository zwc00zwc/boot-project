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
                        taskExecutor.execute(new TransactionThread(ar,availableBalance,"oF9X7mGq+0HedeuvGrGOyw==","Jemr+UzSmwwpfD0MrTY9BQ=="));
                        Thread.sleep(1000);
                        taskExecutor.execute(new TransactionThread(ar,availableBalance,"JXmufrTPbmzGaGTCld7DJA==","HnxrxgodkpzHI1SS5GUWiA=="));
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
                //计算账户余额
                BigDecimal payBalance = null;
                BigDecimal totalInvest = null;
                for (int i = 9;i > 1;i--){
                    double ratio = i*0.1;
                    payBalance = payBalance(ratio);
                    if (payBalance.compareTo(new BigDecimal(10001))<=0){
                        BigDecimal invest = availableBalance.multiply(new BigDecimal(ratio));
                        int a = invest.intValue()/1000 + 1;
                        int b = a*1000;
                        totalInvest = new BigDecimal(b);
                        break;
                    }
                }
                postMap.put("transferId",ar.get("id"));
                postMap.put("projectCategory","2");
                postMap.put("transferPrincipal",totalInvest);
                postMap.put("device","8905da6cb8ded9bf57977e7710a4d279df18476d");
                postMap.put("token",token);
                String result = httpRequestClient.doPost("https://api.yrw.com/security/order/createOrder",postMap,"1.7.0");
                logger.info("创建订单返回结果:",result);
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
                    logger.info("支付订单返回结果:",result);
                    System.out.print(payResult);
                }
            } catch (Exception e) {
                logger.error("自动投资异常",e);
            }
        }

        private BigDecimal payBalance(double ratio){
            BigDecimal discount = null;
            Map<String,Object> detailMap = new HashMap<String, Object>();
            String thumbnail = ar.get("thumbnail")+"";
            if (StringUtils.isNotEmpty(thumbnail)){
                String[] args = thumbnail.split("/");
                String projectid = args[args.length - 1];
                detailMap.put("pid",projectid.substring(0,9));
            }
            detailMap.put("transferId",ar.get("id"));
            detailMap.put("projectCategory",2);
            String detailResult = httpRequestClient.doPost("https://api.yrw.com/project/queryProjectInterestById",detailMap,"1.0.0");
            JSONObject detailJson = JSONObject.parseObject(detailResult);
            if ((Boolean) detailJson.get("success")){
                JSONObject detailResultJson = (JSONObject) detailJson.get("result");
                if (StringUtils.isNotEmpty(detailResultJson.get("discount")+"")){
                    discount = new BigDecimal(detailResultJson.get("discount")+"");
                }
                if (StringUtils.isNotEmpty(detailResultJson.get("availableBalance")+"")){
                    availableBalance = new BigDecimal(detailResultJson.get("availableBalance")+"");
                }
            }
            BigDecimal invest = availableBalance.multiply(new BigDecimal(ratio));
            int a = invest.intValue()/1000 + 1;
            int b = a*1000;
            BigDecimal totalInvest = new BigDecimal(b);
            BigDecimal totalAmount = new BigDecimal(ar.get("totalAmount")+"");
            BigDecimal investDiscount = totalInvest.divide(totalAmount,2,BigDecimal.ROUND_HALF_UP).multiply(discount).setScale(2,BigDecimal.ROUND_HALF_UP);

            BigDecimal payBalance = invest.subtract(investDiscount);
            return payBalance;
        }
    }

//    public static void main(String[] args){
//        BigDecimal totalInvest =new BigDecimal(11000);
//        BigDecimal totalAmount = new BigDecimal("13000.00");
//        BigDecimal discount = new BigDecimal("30.00");
//        BigDecimal investDiscount = totalInvest.divide(totalAmount,2,BigDecimal.ROUND_HALF_UP);
////                .multiply(discount).setScale(2);
//        System.out.print(investDiscount);
//    }
}
