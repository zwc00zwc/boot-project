package application.jobs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Created by alan.zheng on 2017/11/20.
 */
@Configuration
public class ThreadPoolTaskExecutorConfig {
    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor(){
        ThreadPoolTaskExecutor threadPoolExecutor=new ThreadPoolTaskExecutor();
        threadPoolExecutor.setCorePoolSize(10);
        threadPoolExecutor.setMaxPoolSize(50);
        threadPoolExecutor.setQueueCapacity(1000);
        threadPoolExecutor.setKeepAliveSeconds(300);
        return new ThreadPoolTaskExecutor();
    }
}
