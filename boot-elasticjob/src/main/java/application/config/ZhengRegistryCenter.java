package application.config;

import common.reg.zookeeper.ZookeeperConfig;
import common.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by alan.zheng on 2017/1/19.
 */
@Configuration
public class ZhengRegistryCenter {
    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter regCenter(@Value("${regCenter.serverList}") final String serverList, @Value("${regCenter.namespace}") final String namespace,@Value("${regCenter.auth}") final String auth) {
        ZookeeperConfig zookeeperConfig=new ZookeeperConfig();
        zookeeperConfig.setServerLists(serverList);
        zookeeperConfig.setNamespace(namespace);
        zookeeperConfig.setAuth(auth);
        return new ZookeeperRegistryCenter(zookeeperConfig);
    }
}
