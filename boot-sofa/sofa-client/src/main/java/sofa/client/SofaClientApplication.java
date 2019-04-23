package sofa.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @Author: zhengwenchao
 * @Date: 2019 2019-04-23 14:04
 */
@SpringBootApplication
@ImportResource(locations = {"applicationContext.xml"})
public class SofaClientApplication {
    public static void main(String[] args){
        SpringApplication.run(SofaClientApplication.class);
    }
}
