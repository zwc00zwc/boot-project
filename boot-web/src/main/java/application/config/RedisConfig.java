package application.config;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by alan.zheng on 2016/12/28.
 */
@Configuration
@Scope("singleton")
@DisconfFile(filename = "redis.properties")
public class RedisConfig {
    // 代表连接地址
    private String host;

    // 代表连接port
    private int port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
