package application.config;

import application.jobs.DataJob;
import application.jobs.ElasticDataJob;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * elastic配置启动类，注入spring
 * Created by alan.zheng on 2016/12/23.
 */
//@Configuration
public class DataJobConfig {
//    @Bean
//    public ZookeeperRegistryCenter zookeeperRegistryCenter(){
//        return new ZookeeperRegistryCenter(new ZookeeperConfiguration("localhost:2181", "bootjob"));
//    }
    @Resource
    private ZookeeperRegistryCenter regCenter;

//    @Resource
//    private JobEventConfiguration jobEventConfiguration;
//    public JobEventConfiguration jobEventConfiguration(){
//        return
//    }

    @Bean
    public ElasticDataJob dataJob() {
        return new ElasticDataJob();
    }

    @Bean(initMethod = "init")
    public JobScheduler dataflowJobScheduler(final DataflowJob dataflowJob, @Value("${simpleJob.cron}") final String cron, @Value("${simpleJob.shardingTotalCount}") final int shardingTotalCount,
                                             @Value("${simpleJob.shardingItemParameters}") final String shardingItemParameters) {
        return new SpringJobScheduler(dataflowJob, regCenter, getLiteJobConfiguration(dataflowJob.getClass(), cron, shardingTotalCount, shardingItemParameters));
    }

    private LiteJobConfiguration getLiteJobConfiguration(final Class<? extends DataflowJob> jobClass, final String cron, final int shardingTotalCount, final String shardingItemParameters) {
        return LiteJobConfiguration.newBuilder(new DataflowJobConfiguration(JobCoreConfiguration.newBuilder(
                jobClass.getName(), cron, shardingTotalCount).shardingItemParameters(shardingItemParameters).build(), jobClass.getCanonicalName(), true)).overwrite(true).build();
    }
}
