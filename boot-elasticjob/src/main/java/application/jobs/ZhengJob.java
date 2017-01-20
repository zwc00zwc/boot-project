package application.jobs;

import core.domain.service.MemberService;
import job.SimpleJob;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by alan.zheng on 2017/1/18.
 */
public class ZhengJob implements SimpleJob {
    @Autowired
    private MemberService memberService;

    public void execute() {
        System.out.print("我在测试ZhengJob"+new Date().toString());
    }
}
