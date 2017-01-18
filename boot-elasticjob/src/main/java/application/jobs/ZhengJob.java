package application.jobs;

import application.bean.ServiceLocator;
import common.job.SimpleJob;
import core.domain.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by alan.zheng on 2017/1/18.
 */
public class ZhengJob implements SimpleJob{
    @Autowired
    private MemberService memberService;

    public void execute() {
        System.out.print("我在测试ZhengJob");
    }
}
