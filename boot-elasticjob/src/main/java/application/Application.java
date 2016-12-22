package application;

import application.jobs.DataJob;
import application.jobs.TestJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.JobRootConfiguration;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.quartz.simpl.SimpleJobFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Administrator on 2016/12/12.
 */
@SpringBootApplication
@ComponentScan(basePackages = "core.domain,application")
public class Application {

    // 定义作业核心配置
    JobCoreConfiguration simpleCoreConfig = JobCoreConfiguration.newBuilder("TestJob", "0/1 * * * * ?", 10).build();
    // 定义SIMPLE类型配置
    SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(simpleCoreConfig, TestJob.class.getCanonicalName());
    // 定义Lite作业根配置
    LiteJobConfiguration simpleJobRootConfig = LiteJobConfiguration.newBuilder(simpleJobConfig).build();

    // 定义作业核心配置
    JobCoreConfiguration dataflowCoreConfig = JobCoreConfiguration.newBuilder("DataJob", "0/1 * * * * ?", 10).build();
    // 定义DATAFLOW类型配置
    DataflowJobConfiguration dataflowJobConfig = new DataflowJobConfiguration(dataflowCoreConfig, DataJob.class.getCanonicalName(), true);
    // 定义Lite作业根配置
    LiteJobConfiguration dataflowJobRootConfig = LiteJobConfiguration.newBuilder(dataflowJobConfig).build();

    public static void main(String[] args){
        SpringApplication.run(Application.class);
        new Application().init();
    }

    private void init() {
        // 连接注册中心
        CoordinatorRegistryCenter regCenter  = new ZookeeperRegistryCenter(new ZookeeperConfiguration("localhost:2181", "bootjob"));
        regCenter.init();
        new JobScheduler(regCenter,simpleJobRootConfig).init();
        new JobScheduler(regCenter,dataflowJobRootConfig).init();
    }
}
