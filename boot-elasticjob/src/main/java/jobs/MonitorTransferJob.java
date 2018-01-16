package jobs;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import jobs.common.HttpRequestClient;
import jobs.common.WeiXinClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * Created by alan.zheng on 2018/1/16.
 */
public class MonitorTransferJob implements SimpleJob {
    private static Logger logger = LoggerFactory.getLogger(MonitorTransferJob.class);
    @Autowired
    private HttpRequestClient httpRequestClient;

    @Autowired
    private WeiXinClient weiXinClient;

    public void execute(ShardingContext shardingContext) {
        try {
            String resultStr = httpRequestClient.doGet("https://www.yrw.com/products/queryTransferProjectList?currentPage=1&pageSize=8&orderSource=rateDesc");
            JSONObject jsonObject = JSONObject.parseObject(resultStr);
            JSONObject ar = null;
            jsonObject.get("success");
            System.out.print("a："+jsonObject.get("success"));
            BigDecimal baseRate = new BigDecimal("15.9");
            if ((Boolean) jsonObject.get("success")){
                JSONArray jsonArray = (JSONArray) jsonObject.get("resultList");
                for (int i = 0;i<jsonArray.size();i++){
                    ar = (JSONObject) jsonArray.get(i);
                    ar.get("minAnnualizedRate");
                    BigDecimal rate = new BigDecimal(ar.get("minAnnualizedRate")+"");
                    BigDecimal availableBalance = new BigDecimal(ar.get("availableBalance")+"");
                    if (baseRate.compareTo(rate)<0){
                        weiXinClient.monitorTransferProject(ar.get("name")+"",rate,availableBalance,"oYzLx0oYFJyaV3qGprKHm6DSRHBA");
                    }
                }
            }
        } catch (Exception e) {
            logger.error("监控转让项目异常",e);
        }
    }
}
