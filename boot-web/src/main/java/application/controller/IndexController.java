package application.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by alan.zheng on 2017/9/15.
 */
@Controller
@RequestMapping(value = "/index")
@Api(description = "IndexController相关操作接口")
public class IndexController {
    @RequestMapping(value = "/index")
    @ApiOperation(value="index接口", notes="事件发生货到付款静安寺",httpMethod = "GET")
    public String index(){
        return "index";
    }
}
