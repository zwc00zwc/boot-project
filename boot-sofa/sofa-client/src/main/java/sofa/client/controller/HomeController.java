package sofa.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sofa.server.api.TestServer;
import sofa.server.common.model.TestModel;

/**
 * @Author: zhengwenchao
 * @Date: 2019 2019-04-23 14:56
 */
@Controller
@RequestMapping(value = "home")
public class HomeController {
    @Autowired
    private TestServer testServer;

    @ResponseBody
    @RequestMapping(value = "index")
    public String index() {
        TestModel testModel = testServer.test();
        String value = "desc:" + testModel.getDesc();
        System.out.println("value:"+value);
        return value;
    }
}
