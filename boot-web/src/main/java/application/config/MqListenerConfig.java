package application.config;

import application.listener.MqListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by alan.zheng on 2017/2/8.
 */
//@Configuration
public class MqListenerConfig {
    @Bean(initMethod = "listen")
    public MqListener dataflowJobScheduler() {
        return new MqListener();
    }
}
