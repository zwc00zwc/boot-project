package jobs;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by alan.zheng on 2017/4/18.
 */
public class TestJob implements SimpleJob {
    private static Logger logger = LoggerFactory.getLogger(TestJob.class);
    public void execute(ShardingContext shardingContext) {
        logger.info("运行TestJob");
        System.out.print("运行TestJob");
    }
}
