import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by alan.zheng on 2017/4/18.
 */
@SpringBootApplication
@ComponentScan(basePackages = "core.domain,jobs")
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class);
    }
}
