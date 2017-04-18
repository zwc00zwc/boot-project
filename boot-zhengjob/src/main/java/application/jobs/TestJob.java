package application.jobs;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import core.domain.model.Member;
import core.domain.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by XR on 2016/12/12.
 */
public class TestJob implements SimpleJob {

    public void execute(ShardingContext shardingContext) {
        try {
            Thread.sleep(1000);
            System.out.print("TestJob"+new Date().toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
