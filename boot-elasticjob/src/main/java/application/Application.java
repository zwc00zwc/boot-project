package application;

import application.jobs.TestJob;
import com.dangdang.ddframe.job.api.JobScheduler;
import com.dangdang.ddframe.job.api.config.JobConfiguration;
import com.dangdang.ddframe.job.api.config.JobConfigurationFactory;
import com.dangdang.ddframe.job.api.config.impl.SimpleJobConfiguration;
import com.dangdang.ddframe.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Administrator on 2016/12/12.
 */
@SpringBootApplication
@ComponentScan
public class Application {
    private ZookeeperConfiguration zkConfig = new ZookeeperConfiguration("localhost:2181", "elastic-job-example", 1000, 3000, 3);

    // 定义Zookeeper注册中心
    private CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(zkConfig);

    // 定义简单作业配置对象
    private final SimpleJobConfiguration simpleJobConfig = JobConfigurationFactory.createSimpleJobConfigurationBuilder("simpleElasticDemoJob",
            TestJob.class, 10, "0/5 * * * * ?").build();

    public static void main(String[] args){
        SpringApplication.run(Application.class);
        new Application().init();
    }

    private void init() {
        // 连接注册中心
        regCenter.init();
        // 启动作业1
        new JobScheduler(regCenter, simpleJobConfig).init();
    }
}
