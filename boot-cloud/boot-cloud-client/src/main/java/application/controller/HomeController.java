package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by XR on 2016/12/19.
 */
@RestController
public class HomeController {
    @Value("spring.application.name")
    private String serviceName;

    @Value("${server.port}")
    private String port;

    @RequestMapping(value = "/index" ,method = RequestMethod.GET)
    public String index(){
        return "当前调用" + port + "端口" + serviceName + "服务 index";
    }
}
