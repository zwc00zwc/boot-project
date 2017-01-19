package application.jobs;

import application.bean.ServiceLocator;
import common.job.SimpleJob;
import common.job.log.JobLogManager;
import core.domain.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by alan.zheng on 2017/1/18.
 */
public class ZhengJob implements SimpleJob{
    @Autowired
    private MemberService memberService;

    public void execute() {
        JobLogManager.log("ZhengJob","在测试哦",new Date());
        System.out.print("我在测试ZhengJob");
    }
}
