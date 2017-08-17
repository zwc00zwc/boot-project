package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Created by alan.zheng on 2017/8/17.
 */
@SpringBootApplication
@EnableConfigServer
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class);
    }
}
