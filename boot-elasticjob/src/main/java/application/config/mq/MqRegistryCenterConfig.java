package application.config.mq;

import businessmq.reg.zookeeper.ZookeeperConfig;
import businessmq.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by alan.zheng on 2017/2/13.
 */
@Configuration
public class MqRegistryCenterConfig {
    @Bean(initMethod = "init",name = "mqzookeeperRegistryCenter")
    public ZookeeperRegistryCenter regCenter(@Value("${zhengmqregCenter.serverList}") final String serverList, @Value("${zhengmqregCenter.namespace}") final String namespace, @Value("${zhengmqregCenter.auth}") final String auth) {
        ZookeeperConfig zookeeperConfig=new ZookeeperConfig();
        zookeeperConfig.setServerLists(serverList);
        zookeeperConfig.setNamespace(namespace);
        zookeeperConfig.setAuth(auth);
        return new ZookeeperRegistryCenter(zookeeperConfig);
    }
}
