package sofa.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @Author: zhengwenchao
 * @Date: 2019 2019-04-23 14:09
 */
@SpringBootApplication
@ImportResource(locations = {"applicationContext.xml"})
public class SofaServerApplication {
    public static void main(String[] args){
        SpringApplication.run(SofaServerApplication.class);
    }
}
