package application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by alan.zheng on 2017/9/15.
 */
@Controller
@RequestMapping(value = "/index")
public class IndexController {
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
