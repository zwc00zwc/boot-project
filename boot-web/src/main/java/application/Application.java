package application;

import application.listener.MqListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import java.util.Date;

/**
 * Created by XR on 2016/12/9.
 */
@SpringBootApplication
@ComponentScan(basePackages = "core.domain,application")
public class Application extends SpringBootServletInitializer {

    @Override
    protected org.springframework.boot.builder.SpringApplicationBuilder configure(org.springframework.boot.builder.SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

    public static void main(String[] args){
        SpringApplication springApplication= new SpringApplication(Application.class);
        springApplication.run(args);
        System.out.print(new Date().toString());
        MqListener.listen();
    }
}
