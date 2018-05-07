package application.jobs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Timer;

/**
 * Created by alan.zheng on 2018/2/1.
 */
@Configuration
public class HeartWeixinConfig {
    @Bean
    public HeartWeixinToken heartWeixinToken(){
        return new HeartWeixinToken();
    }

    @Bean
    public Timer timer(final HeartWeixinToken heartWeixinToken){
        Timer timer=new Timer();
        timer.schedule(heartWeixinToken, 0, 6000 * 1000);
        System.out.print("心跳运行");
        return timer;
    }
}
