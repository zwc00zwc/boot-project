package controller;

import com.alibaba.fastjson.JSONObject;
import jobs.common.HttpRequestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alan.zheng on 2017/11/20.
 */
@RestController
@RequestMapping(value = "/home")
public class HomeController {
    @Autowired
    private HttpRequestClient httpRequestClient;

    @Resource
    private ThreadPoolTaskExecutor taskExecutor;

    @RequestMapping(value = "/index")
    String index(){
        return "index";
    }

    @RequestMapping(value = "/yusan")
    String yusan(){
        final String token = getToken();
        Map map = new HashMap();
        map.put("invokeParameters",
                "args_goodId_1_long_128&args_amount_1_integer_174&args_address_1_string_abc&args_receiver_1_string_zhengwenchao&args_mobile_1_string_13059758695&args_province_1_string_zhejiang&args_city_1_string_hangzhou&args_district_1_string_aaa&args_goodsCount_1_integer_1");
        map.put("loginSource",2);
        map.put("device","8905da6cb8ded9bf57977e7710a4d279df18476d");
        map.put("token",token);
        String result = httpRequestClient.doPost("https://api.yrw.com/security/find/dynamicInvoke?invokeMethod=3",map);
        //String result = httpRequestClient.doPost("http://192.168.0.51:8082/security/find/dynamicInvoke?invokeMethod=3&token="+token+"",map);
//        while (true){
//            try {
//                Thread.sleep(300);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
////            System.out.print("线程池运行了");
//            taskExecutor.execute(new Runnable() {
//                public void run() {
//                    Map map = new HashMap();
//                    map.put("invokeParameters",
//                            "args_goodId_1_long_128&args_amount_1_integer_174&args_address_1_string_abc&args_receiver_1_string_zhengwenchao&args_mobile_1_string_13059758695&args_province_1_string_zhejiang&args_city_1_string_hangzhou&args_district_1_string_aaa&args_goodsCount_1_integer_1");
//                    map.put("loginSource",2);
//                    String result = httpRequestClient.doPost("https://api.yrw.com/security/find/dynamicInvoke?invokeMethod=3&token="+token+"",map);
//                    //String result = httpRequestClient.doPost("http://192.168.0.51:8082/security/find/dynamicInvoke?invokeMethod=3&token="+token+"",map);
//                }
//            });
//        }
        return result;
    }

    @RequestMapping(value = "/sign")
    String index2(){
        String token = getToken();
        Map map = new HashMap();
        map.put("device","8905da6cb8ded9bf57977e7710a4d279df18476d");
        String result = httpRequestClient.doPost("https://api.yrw.com/security/member/goSignIn?token="+token+"",map);
        return result;
    }

    private String getToken(){
        Map map = new HashMap();
//        map.put("username","tyFWY0nEAEosAjcBjC6Ljg==");
//        map.put("password","CU9u7+v0+2qUpc+24f+K7A==");
        map.put("username","JXmufrTPbmzGaGTCld7DJA==");
        map.put("password","HnxrxgodkpzHI1SS5GUWiA==");
        map.put("loginSource","2");
        map.put("device","8905da6cb8ded9bf57977e7710a4d279df18476d");
        map.put("equipment","iPhone");
        String response = httpRequestClient.doPost("https://api.yrw.com/logining",map);
        //String response = httpRequestClient.doPost("http://192.168.0.51:8082/logining",map);
        JSONObject jsonObject = JSONObject.parseObject(response);
        JSONObject o = (JSONObject)jsonObject.get("result");
        return o.get("token").toString();
    }
}
