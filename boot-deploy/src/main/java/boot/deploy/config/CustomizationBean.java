package boot.deploy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;

/**
 * @auther a-de
 * @date 2018/10/19 15:56
 */
public class CustomizationBean implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {
    @Value("${serverPort}")
    private Integer serverPort;

    @Override
    public void customize(ConfigurableServletWebServerFactory server) {
        if (serverPort!=null && serverPort>0){
            server.setPort(serverPort);
        }
    }
}
