package application.config;

import application.jobs.ZhengJob;
import job.JobScheduler;
import job.SimpleJob;
import job.SpringJobScheduler;
import job.config.JobConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reg.zookeeper.ZookeeperRegistryCenter;

import javax.annotation.Resource;

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

    @Resource
    public ZookeeperRegistryCenter registryCenter;

    @Bean(initMethod = "init")
    public JobScheduler dataflowJobScheduler(final SimpleJob simpleJob) {
        JobConfig jobConfig=new JobConfig("zhengJob", simpleJob.getClass().getCanonicalName(),"0/20 * * * * ?");
        return new SpringJobScheduler(jobConfig,registryCenter,simpleJob);
    }
}
