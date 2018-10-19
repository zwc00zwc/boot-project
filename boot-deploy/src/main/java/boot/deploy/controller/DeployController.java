package boot.deploy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @auther a-de
 * @date 2018/10/19 15:58
 */
@Controller
public class DeployController {
    @ResponseBody
    @RequestMapping(value = "/deploy")
    public String deploy(){
        return "deploy";
    }

    @ResponseBody
    @RequestMapping(value = "/uploadFile")
    public String uploadFile(){
        return "";
    }
}
