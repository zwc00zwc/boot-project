package application.controller;

import core.domain.model.Member;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by alan.zheng on 2017/9/15.
 */
@Controller
@RequestMapping(value = "/index")
@Api(description = "IndexController相关操作接口")
public class IndexController {
    @RequestMapping(value = "/index")
    @ApiOperation(value="index接口", notes="事件发生货到付款静安寺",httpMethod = "GET")
    public Member index(){
        Member member = new Member();
        return member;
    }
}
