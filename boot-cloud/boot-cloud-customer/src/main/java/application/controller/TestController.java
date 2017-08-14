package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by alan.zheng on 2017/8/14.
 */
//@RestController
public class TestController {
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/hi")
    public String hi(){
        return restTemplate.getForObject("http://service-a/hi?id=", String.class);
    }
}
