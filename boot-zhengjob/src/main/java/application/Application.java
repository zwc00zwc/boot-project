package application;

import application.jobs.MyJobDemo;
import job.JobScheduleController;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Administrator on 2016/12/12.
 */
@SpringBootApplication
@ComponentScan(basePackages = "core.domain,application")
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class);
//        init1();
    }

    /**
     * zheng job
     */
    private static void init1(){
        JobDetail jobDetail = JobBuilder.newJob(MyJobDemo.class).withIdentity("MyJobDemo").build();
        Scheduler scheduler=null;
        try {
            StdSchedulerFactory factory = new StdSchedulerFactory();
            factory.initialize();
            scheduler = factory.getScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        JobScheduleController jobScheduleController=new JobScheduleController(scheduler,jobDetail,"t1");
        jobScheduleController.scheduleJob("0/5 * * * * ?");
    }
}
