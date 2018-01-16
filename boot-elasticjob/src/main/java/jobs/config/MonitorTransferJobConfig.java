package jobs.config;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import jobs.MonitorTransferJob;
import jobs.SignJob;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * Created by alan.zheng on 2018/1/16.
 */
@Configuration
public class MonitorTransferJobConfig {
    @Resource
    private ZookeeperRegistryCenter zookeeperRegistryCenter;

    @Bean(name = "monitorTransferJob")
    public SimpleJob monitorTransferJob() {
        return new MonitorTransferJob();
    }

    @Bean(name = "monitorTransferJobScheduler", initMethod = "init")
    public JobScheduler monitorTransferJobScheduler(final SimpleJob monitorTransferJob, @Value("${monitorTransferJob.cron}") final String cron, @Value("${monitorTransferJob.shardingTotalCount}") final int shardingTotalCount,
                                           @Value("${monitorTransferJob.description}") final String description) {
        return new SpringJobScheduler(monitorTransferJob, zookeeperRegistryCenter, getLiteJobConfiguration(monitorTransferJob.getClass(), cron, shardingTotalCount,description));
    }

    private LiteJobConfiguration getLiteJobConfiguration(final Class<? extends SimpleJob> jobClass, final String cron, final int shardingTotalCount, final String description) {
        //failover(true)开启失效转移
        return LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder(
                jobClass.getName(), cron, shardingTotalCount).description(description).build(), jobClass.getCanonicalName())).overwrite(true).build();
    }
}
