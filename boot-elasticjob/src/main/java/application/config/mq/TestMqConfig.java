package application.config.mq;

import businessmq.ConsumerListener;
import businessmq.SpringConsumerListener;
import businessmq.config.ConsumerConfig;
import businessmq.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by alan.zheng on 2017/2/10.
 */
@Configuration
public class TestMqConfig {
    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor(){
        ThreadPoolTaskExecutor threadPoolExecutor=new ThreadPoolTaskExecutor();
        threadPoolExecutor.setCorePoolSize(5);
        threadPoolExecutor.setMaxPoolSize(10);
        threadPoolExecutor.setQueueCapacity(25);
        return new ThreadPoolTaskExecutor();
    }

    @Resource
    private ZookeeperRegistryCenter mqzookeeperRegistryCenter;

    @Bean(name = "testConsumer")
    public TestConsumer zhengJob() {
        return new TestConsumer();
    }

    @Bean(initMethod = "init",name = "TestListen")
    public ConsumerListener dataflowJobScheduler(final TestConsumer testConsumer,ThreadPoolTaskExecutor threadPoolTaskExecutor) {
        ConsumerConfig consumerConfig=new ConsumerConfig();
        consumerConfig.setHost("127.0.0.1");
        consumerConfig.setPort(5672);
        consumerConfig.setUserName("guest");
        consumerConfig.setPassword("guest");
        consumerConfig.setConsumerQueue("command");
        consumerConfig.setJavaClass(testConsumer.getClass().getCanonicalName());
        return new SpringConsumerListener(consumerConfig,mqzookeeperRegistryCenter,testConsumer,threadPoolTaskExecutor);
    }
}
