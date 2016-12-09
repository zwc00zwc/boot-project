package application.controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import core.domain.model.Member;
import core.domain.service.MemberService;
import mongodb.MongodbManager;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Created by XR on 2016/12/9.
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
}
