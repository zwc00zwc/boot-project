package application;

import application.listener.MqListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;

import java.util.Date;

/**
 * Created by XR on 2016/12/9.
 */
@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)
@ComponentScan(basePackages = "core.domain,application")
//@ImportResource({"classpath:disconf.xml"})//引入disconf
public class Application implements EmbeddedServletContainerCustomizer {
    public static void main(String[] args){
        SpringApplication springApplication= new SpringApplication(Application.class);
        springApplication.run(args);
//        System.out.print(new Date().toString());
//        MqListener.listen();
    }

    public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
        configurableEmbeddedServletContainer.setPort(8086);
    }
}
