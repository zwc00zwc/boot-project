package application.config;

import application.jobs.ZhengJob;
import common.job.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 造轮子的任务配置类，注入spring启动
 * Created by alan.zheng on 2017/1/18.
 */
@Configuration
public class ZhengJobConfig {
    @Bean
    public ZhengJob dataJob() {
        return new ZhengJob();
    }

    @Bean(initMethod = "init")
    public JobScheduler dataflowJobScheduler(final SimpleJob simpleJob) {
        JobConfig jobConfig=new JobConfig("zhengJob", simpleJob.getClass().getCanonicalName(),"0/5 * * * * ?");
        return new SpringJobScheduler(jobConfig,simpleJob);
    }
}
