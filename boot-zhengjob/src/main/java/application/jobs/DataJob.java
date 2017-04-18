package application.jobs;

import application.bean.ServiceLocator;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import core.domain.model.Member;
import core.domain.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * Created by XR on 2016/12/22.
 */
public class DataJob implements DataflowJob {

    protected ServiceLocator service = ServiceLocator.getInstance();
    private MemberService memberService = (MemberService)service.getService("memberService");

    public List fetchData(ShardingContext shardingContext) {
        List<Member> list = memberService.queryList();
        return list;
    }

    public void processData(ShardingContext shardingContext, List list) {
        for (int i=0;i<list.size();i++){
            Member m=(Member) list.get(i);
            System.out.print("Member Phone"+m.getPhone());
        }
    }
}
