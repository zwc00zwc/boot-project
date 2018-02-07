package application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by alan.zheng on 2018/2/7.
 */
@Controller
public class HomeController {
    @RequestMapping(value = "/index")
    String index(){
        return "index";
    }
}
