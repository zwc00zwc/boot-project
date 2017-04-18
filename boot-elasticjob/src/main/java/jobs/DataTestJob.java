package jobs;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;

import java.util.List;

/**
 * Created by alan.zheng on 2017/4/18.
 */
public class DataTestJob implements DataflowJob {
    public List fetchData(ShardingContext shardingContext) {
        return null;
    }

    public void processData(ShardingContext shardingContext, List list) {

    }
}
