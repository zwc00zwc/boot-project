package jobs;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import core.domain.model.Member;
import core.domain.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by alan.zheng on 2017/4/18.
 */
public class DataTestJob implements DataflowJob {
    @Autowired
    private MemberService memberService;
    //分割数据
    public List fetchData(ShardingContext shardingContext) {
        switch (shardingContext.getShardingItem()){
            case 0 : return null;
            case 1 : return memberService.queryList();
        }
        return null;
    }
    //处理数据
    public void processData(ShardingContext shardingContext, List list) {
        if (list!=null&&list.size()>0){
            for (Object l:list) {
                Member m = (Member) l;
                System.out.print(m.getUserName()+new Date());
            }
        }
    }
}
