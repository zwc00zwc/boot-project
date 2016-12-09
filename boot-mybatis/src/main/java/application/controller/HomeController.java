package application.controller;

import application.model.Member;
import application.service.MemberService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by XR on 2016/12/7.
 */
@RestController
@SpringBootApplication
public class HomeController {
    @Autowired
    private MemberService memberService;
    @RequestMapping("/")
    String home() {
        List<Member> list= memberService.queryList();
        return "Hello World! "+list.size()+"";
    }
}
