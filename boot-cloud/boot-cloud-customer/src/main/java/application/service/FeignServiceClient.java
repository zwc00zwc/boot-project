package application.service;

import application.hystrix.FusingRemoteHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by alan.zheng on 2017/8/14.
 */
@Component
@FeignClient(value = "compute-service",fallback = FusingRemoteHystrix.class)
public interface FeignServiceClient {
    @RequestMapping(value = "/index")
    String index();
}
