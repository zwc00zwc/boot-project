package jobs;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

/**
 * Created by alan.zheng on 2017/4/18.
 */
public class TestJob implements SimpleJob {
    public void execute(ShardingContext shardingContext) {
        System.out.print("运行TestJob");
    }
}
