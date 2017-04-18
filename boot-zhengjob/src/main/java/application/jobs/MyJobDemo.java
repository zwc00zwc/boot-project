package application.jobs;

import application.bean.ServiceLocator;
import core.domain.model.Member;
import core.domain.service.MemberService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by XR on 2016/12/23.
 */
public class MyJobDemo implements Job {
    protected ServiceLocator service = ServiceLocator.getInstance();
    private MemberService memberService = (MemberService)service.getService("memberService");

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<Member> list=memberService.queryList();
        System.out.print("运行Job1"+new Date().toString());
        System.out.print("MemberList个数"+list.size());
    }
}
