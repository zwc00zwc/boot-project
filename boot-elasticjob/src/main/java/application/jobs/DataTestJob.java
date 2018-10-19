package application.jobs;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by alan.zheng on 2017/4/18.
 */
public class DataTestJob implements DataflowJob {
    private static Logger logger = LoggerFactory.getLogger(TestJob.class);
//    @Autowired
//    private MemberService memberService;
    //分割数据
    public List fetchData(ShardingContext shardingContext) {
//        switch (shardingContext.getShardingItem()){
//            case 0 : return null;
//            case 1 : return memberService.queryList();
//        }
        return null;
    }
    //处理数据
    public void processData(ShardingContext shardingContext, List list) {
        logger.info("分片【"+shardingContext.getShardingItem()+"】开始执行");
        if (list!=null&&list.size()>0){
            for (Object l:list) {
//                Member m = (Member) l;
//                logger.info("分片【"+shardingContext.getShardingItem()+"】执行" + m.getUserName()+new Date());
//                System.out.print(m.getUserName()+new Date());
            }
        }
    }
}
