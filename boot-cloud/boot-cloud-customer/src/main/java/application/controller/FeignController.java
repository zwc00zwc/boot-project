package application.controller;

import application.service.FeignServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by alan.zheng on 2017/8/14.
 */
@RestController
public class FeignController {
    @Autowired
    private FeignServiceClient feignServiceClient;

    @RequestMapping("/index")
    public String index(){
        return feignServiceClient.index();
    }
}
