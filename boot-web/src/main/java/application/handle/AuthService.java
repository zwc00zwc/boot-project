package application.handle;

import application.annotation.Auth;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by alan.zheng on 2017/4/6.
 */
@Aspect
@Component
public class AuthService {
    @Pointcut("@annotation(annotation.Auth)")
    public void methodPointcut(){

    }

    @Before("@annotation(auth)")
    public void before(Auth auth){
        System.out.println("before方法");
        ServletRequestAttributes requestAttributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request= requestAttributes.getRequest();
    }
}
