package application.controller;

import application.annotation.Auth;
import application.service.TestService;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mongodb.MongodbManager;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by XR on 2016/12/9.
 */
@RestController
@RequestMapping(value = "/home")
@Api(description = "HomeController相关操作接口")
public class HomeController {
    private static Logger logger= LoggerFactory.getLogger(HomeController.class);
    @Autowired
    private TestService testService;
//    @Autowired
//    private SpringProductProvide springProductProvide;
    @Auth(rule = "")
    @RequestMapping(value = "/",method = RequestMethod.GET)
    @ApiOperation(value="home接口", notes="jsdfkasjdfs")
    String home() {
        logger.info("这是一条log,访问hello world");
        return "Hello World!";
    }


    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ApiOperation(value="test接口", notes="事件发生货到付款静安寺")
    String test(String name){
//        try {
//            memberService.test(8L,name);
//            System.out.print("aaa");
//        } catch (Exception e) {
//            System.out.print("报错了");
//        }
//        System.out.print("test");
//        return "aa";
        testService.a();
        return "aaa";
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    @ApiOperation(value="index接口", notes="事件发生货到付款静安寺")
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
    @RequestMapping(value = "/indexload",method = RequestMethod.GET)
    @ApiOperation(value="indexload接口", notes="事件发生货到付款静安寺")
    String indexload(){
        return "load";
    }

    String indexjarload(){
        return "loadjar";
    }

    @RequestMapping(value = "send",method = RequestMethod.GET)
    @ApiOperation(value="send接口", notes="事件发生货到付款静安寺")
    public String send(){
//        springProductProvide.send("aaa");
        return "send";
    }
}
