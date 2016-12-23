package application.jobs;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import core.domain.model.Member;
import core.domain.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by alan.zheng on 2016/12/23.
 */
public class ElasticDataJob implements DataflowJob {

    @Autowired
    private MemberService memberService;

    public List fetchData(ShardingContext shardingContext) {
        List<Member> list = memberService.queryList();
        return list;
    }

    public void processData(ShardingContext shardingContext, List list) {
        System.out.print(list.size()+"现在时间："+new Date().toString());
    }
}
