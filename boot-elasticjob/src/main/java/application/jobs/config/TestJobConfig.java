package application.jobs.config;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import application.jobs.TestJob;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * Created by alan.zheng on 2017/4/18.
 */
//@Configuration
public class TestJobConfig {
    @Resource
    private ZookeeperRegistryCenter zookeeperRegistryCenter;

    @Bean(name = "testJob")
    public SimpleJob simpleJob() {
        return new TestJob();
    }

    @Bean(name = "testJobScheduler", initMethod = "init")
    public JobScheduler testJobScheduler(final SimpleJob simpleJob, @Value("${simpleJob.cron}") final String cron, @Value("${simpleJob.shardingTotalCount}") final int shardingTotalCount) {
        return new SpringJobScheduler(simpleJob, zookeeperRegistryCenter, getLiteJobConfiguration(simpleJob.getClass(), cron, shardingTotalCount));
    }

    private LiteJobConfiguration getLiteJobConfiguration(final Class<? extends SimpleJob> jobClass, final String cron, final int shardingTotalCount) {
        //failover(true)开启失效转移
        return LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder(
                jobClass.getName(), cron, shardingTotalCount).failover(true).build(), jobClass.getCanonicalName())).overwrite(true).build();
    }
}
