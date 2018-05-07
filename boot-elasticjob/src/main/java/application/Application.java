package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by alan.zheng on 2017/4/18.
 */
@SpringBootApplication
@ComponentScan(basePackages = "core.domain,application.jobs,application.controller")
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class);
    }

//    private static void init() {
//        // 定义作业核心配置
//        JobCoreConfiguration simpleCoreConfig = JobCoreConfiguration.newBuilder("TestJob", "0/1 * * * * ?", 10).build();
//        // 定义SIMPLE类型配置
//        SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(simpleCoreConfig, TestJob.class.getCanonicalName());
//        // 定义Lite作业根配置
//        LiteJobConfiguration simpleJobRootConfig = LiteJobConfiguration.newBuilder(simpleJobConfig).build();
//
//        // 定义作业核心配置
//        JobCoreConfiguration dataflowCoreConfig = JobCoreConfiguration.newBuilder("DataJob", "0/1 * * * * ?", 10).build();
//        // 定义DATAFLOW类型配置
//        DataflowJobConfiguration dataflowJobConfig = new DataflowJobConfiguration(dataflowCoreConfig, DataTestJob.class.getCanonicalName(), true);
//        // 定义Lite作业根配置
//        LiteJobConfiguration dataflowJobRootConfig = LiteJobConfiguration.newBuilder(dataflowJobConfig).build();
//        // 连接注册中心
//        CoordinatorRegistryCenter regCenter  = new ZookeeperRegistryCenter(new ZookeeperConfiguration("localhost:2181", "bootjob"));
//        regCenter.init();
//        new JobScheduler(regCenter,simpleJobRootConfig).init();
//        new JobScheduler(regCenter,dataflowJobRootConfig).init();
//    }
}
