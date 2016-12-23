package application.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * Created by XR on 2016/12/23.
 */
@Component
public class ServiceLocator implements BeanFactoryAware {

    private static BeanFactory beanFactory = null;

    private static ServiceLocator servlocator = null;

    public void setBeanFactory(BeanFactory factory) throws BeansException {
        this.beanFactory = factory;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }


    public static ServiceLocator getInstance() {
        if (servlocator == null)
            servlocator = (ServiceLocator) beanFactory.getBean("serviceLocator");
        return servlocator;
    }

    /**
     * 根据提供的bean名称得到相应的服务类
     * @param servName bean名称
     */
    public static Object getService(String servName) {
        return beanFactory.getBean(servName);
    }
}
