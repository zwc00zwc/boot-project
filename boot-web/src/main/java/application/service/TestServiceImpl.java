package application.service;

import application.annotation.Auth;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by alan.zheng on 2017/9/14.
 */
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestService testService;

    public void a() {
        //通过到spring容器中获取该类的代理对象
        testService.b();
    }

    @Auth(rule = "")
    public String b() {
        System.out.print("这是b方法");
        return "b";
    }
}
