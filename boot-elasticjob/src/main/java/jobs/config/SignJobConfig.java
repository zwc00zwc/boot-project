package jobs.config;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import jobs.SignJob;
import jobs.TestJob;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * Created by alan.zheng on 2017/11/20.
 */
@Configuration
public class SignJobConfig {
    @Resource
    private ZookeeperRegistryCenter zookeeperRegistryCenter;

    @Bean(name = "signJob")
    public SimpleJob signJob() {
        return new SignJob();
    }

    @Bean(name = "signJobScheduler", initMethod = "init")
    public JobScheduler simpleJobScheduler(final SimpleJob signJob, @Value("${signJob.cron}") final String cron, @Value("${signJob.shardingTotalCount}") final int shardingTotalCount,
                                           @Value("${signJob.description}") final String description) {
        return new SpringJobScheduler(signJob, zookeeperRegistryCenter, getLiteJobConfiguration(signJob.getClass(), cron, shardingTotalCount,description));
    }

    private LiteJobConfiguration getLiteJobConfiguration(final Class<? extends SimpleJob> jobClass, final String cron, final int shardingTotalCount, final String description) {
        //failover(true)开启失效转移
        return LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder(
                jobClass.getName(), cron, shardingTotalCount).description(description).build(), jobClass.getCanonicalName())).overwrite(true).build();
    }
}
