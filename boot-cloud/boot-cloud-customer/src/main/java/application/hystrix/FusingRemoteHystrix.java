package application.hystrix;

import application.service.FeignServiceClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by alan.zheng on 2017/8/22.
 */
@Component
public class FusingRemoteHystrix implements FeignServiceClient {
    public String index() {
        return "this message send failed";
    }
}
