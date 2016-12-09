package application;

import application.listener.MqListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by XR on 2016/12/9.
 */
@SpringBootApplication
@ComponentScan(basePackages = "core.domain,application")
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class);
        MqListener.listen();
    }
}
