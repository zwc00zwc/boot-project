package application;

import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2016/12/19.
 */
@Configuration
public class SpringApplicationBuilder extends SpringBootServletInitializer {
    @Override
    protected org.springframework.boot.builder.SpringApplicationBuilder configure(org.springframework.boot.builder.SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }
}
