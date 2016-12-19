package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by XR on 2016/12/19.
 */
@EnableDiscoveryClient
@SpringBootApplication
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class);
    }
}
