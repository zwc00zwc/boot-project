package application.controller;

import application.annotation.Auth;
import businessmq.SpringProductProvide;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import core.domain.model.Member;
import core.domain.service.MemberService;
import mongodb.MongodbManager;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by XR on 2016/12/9.
 */
@RestController
@SpringBootApplication
public class HomeController {
    private static Logger logger= LoggerFactory.getLogger(HomeController.class);
    @Autowired
    private MemberService memberService;
//    @Autowired
//    private SpringProductProvide springProductProvide;
    @Auth(rule = "")
    @RequestMapping("/")
    String home() {
        List<Member> list= memberService.queryList();
        logger.info("这是一条log,访问hello world");
        return "Hello World! "+list.size()+"";
    }
    @RequestMapping("/index")
    String index(){
        MongodbManager.getAuthDatabase();
        MongoDatabase database=MongodbManager.getAuthDatabase();
        MongoCollection collection= database.getCollection("waiterror");
        Document document=new Document();
        document.append("detail","提交发送消费失败");
        document.append("createtime",new Date());
        collection.insertOne(document);
        return "index";
    }
    @RequestMapping("/indexload")
    String indexload(){
        List<Member> memberList=new ArrayList<Member>();
        try {
            URL url1 = new URL("file:E:/boot-core-1.0-SNAPSHOT.jar");
            URLClassLoader myClassLoader1 = new URLClassLoader(new URL[] { url1 }, null);
            Thread.currentThread().setContextClassLoader(myClassLoader1);
            Class<?> myClass1 = myClassLoader1.loadClass("core.domain.service.MemberService");
//            MemberService memberService1=(MemberService)myClass1.newInstance();
            Method mainMethod = myClass1.getMethod("queryList");
            mainMethod.invoke(myClass1);
//            memberService1.aaa();
//            memberList=memberService1.queryList();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return "load"+memberList.size();
    }

    String indexjarload(){
        return "loadjar";
    }

    @RequestMapping(value = "send")
    public String send(){
//        springProductProvide.send("aaa");
        return "send";
    }
}
