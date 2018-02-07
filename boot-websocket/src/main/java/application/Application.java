package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * Created by alan.zheng on 2017/4/18.
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class);
    }

    //使用spring boot内置servlet容器需要注入此bean，会自动注册使用@ServerEndpoint注解声明的websocket
    //如果使用的是独立的servlet容器就不要注入此bean，提供给容器自己去管理
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
