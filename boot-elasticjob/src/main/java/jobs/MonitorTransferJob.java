package jobs;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import jobs.common.HttpRequestClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by alan.zheng on 2018/1/16.
 */
public class MonitorTransferJob implements SimpleJob {
    @Autowired
    private HttpRequestClient httpRequestClient;

    public void execute(ShardingContext shardingContext) {
        String resultStr = httpRequestClient.doGet("https://www.yrw.com/products/queryTransferProjectList?currentPage=1&pageSize=8&orderSource=rateDesc");
    }
}
