package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by XR on 2016/12/9.
 */
@SpringBootApplication
@ComponentScan(basePackages = "core.domain,application")
@ImportResource({"classpath*:config/mybatis/applicationContext-*.xml"})
//@ImportResource({"classpath:disconf.xml"})//引入disconf
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class);
    }
}
