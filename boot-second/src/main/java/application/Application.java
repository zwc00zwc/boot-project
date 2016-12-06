package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import java.util.Date;

/**
 * Created by XR on 2016/12/6.
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        for (int i=0;i<10;i++){
            try {
                Thread.sleep(1000);
                System.out.print("现在时间"+new Date().toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
