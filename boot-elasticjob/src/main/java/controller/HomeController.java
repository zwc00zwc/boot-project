package controller;

import com.alibaba.fastjson.JSONObject;
import jobs.common.HttpRequestClient;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sign.RSA;

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
//        Map map = new HashMap();
//        map.put("invokeParameters",
//                "args_goodId_1_long_128&args_amount_1_integer_174&args_address_1_string_abc&args_receiver_1_string_zhengwenchao&args_mobile_1_string_13059758695&args_province_1_string_zhejiang&args_city_1_string_hangzhou&args_district_1_string_aaa&args_goodsCount_1_integer_1");
//        map.put("loginSource",2);
//        map.put("device","8905da6cb8ded9bf57977e7710a4d279df18476d");
//        map.put("token",token);
//        String result = httpRequestClient.doPost("https://api.yrw.com/security/find/dynamicInvoke?invokeMethod=3",map);
        //String result = httpRequestClient.doPost("http://192.168.0.51:8082/security/find/dynamicInvoke?invokeMethod=3&token="+token+"",map);
        while (true){
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.print("线程池运行了");
            taskExecutor.execute(new Runnable() {
                public void run() {
//                    Map map = new HashMap();
//                    map.put("invokeParameters",
//                            "args_goodId_1_long_128&args_amount_1_integer_174&args_address_1_string_abc&args_receiver_1_string_zhengwenchao&args_mobile_1_string_13059758695&args_province_1_string_zhejiang&args_city_1_string_hangzhou&args_district_1_string_aaa&args_goodsCount_1_integer_1");
//                    map.put("loginSource",2);
//                    map.put("token",token);
//                    String result = httpRequestClient.doPost("https://api.yrw.com/security/find/dynamicInvoke?invokeMethod=3&token="+token+"",map);
                    //String result = httpRequestClient.doPost("http://192.168.0.51:8082/security/find/dynamicInvoke?invokeMethod=3&token="+token+"",map);
                    Map map = new HashMap();
                    map.put("invokeParameters",
                            "args_goodId_1_long_128&args_amount_1_integer_174&args_address_1_string_abc&args_receiver_1_string_zhengwenchao&args_mobile_1_string_13059758695&args_province_1_string_zhejiang&args_city_1_string_hangzhou&args_district_1_string_aaa&args_goodsCount_1_integer_1");
                    map.put("loginSource",2);
                    map.put("device","8905da6cb8ded9bf57977e7710a4d279df18476d");
                    map.put("token",token);
                    String result = httpRequestClient.doPost("https://api.yrw.com/security/find/dynamicInvoke?invokeMethod=3",map);
                }
            });
        }
//        return result;
    }

    @RequestMapping(value = "/sign")
    String index2(){
        //String token = getToken();
        Map map = new HashMap();
//        map.put("device","8905da6cb8ded9bf57977e7710a4d279df18476d");
        String uid = "079467";
        String publickey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnSI/kOBpq26fIYy4K4PolytaSk7F5LLyQ5hGfdyh8RHxZi3+WlsBhQWvUypoCFXB+rzzftnd4xhoNUuwZGIGKy4HPjDzsT+0/5DDHFoFqEnN+WzoPl4E16zvVh61w49yvKg8Xxo71rV7PPADB/XfPUk1XRj6RrsmGtFYbLsItKQIDAQAB";
        String privateKet = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAKdIj+Q4Gmrbp8hjLgrg+iXK1pKTsXksvJDmEZ93KHxEfFmLf5aWwGFBa9TKmgIVcH6vPN+2d3jGGg1S7BkYgYrLgc+MPOxP7T/kMMcWgWoSc35bOg+XgTXrO9WHrXDj3K8qDxfGjvWtXs88AMH9d89STVdGPpGuyYa0Vhsuwi0pAgMBAAECgYB5RvES+t2kJPViIQ1rU6nnJkQj5jGfcSCQSEv0eXX/icNBRBJPAaIdoEll7zByXEq/fZo7clTxWWXfzQNgOlUHNYMxevBJf8TQN069oUpV17g8wevVlMIDNjCSToFif+0374KCL0/yz/oBWnvKmOMNADUja/+jc6wVJucJB69/EQJBAOrr0mKZgQe1BapX8PGhpgBdjF3aDJuWNV1T1eYytwwKb/3IcuuILaxyKaIGE/FWD2Nd5oS4L4cw5V1hodyF2h0CQQC2SxcjMhBhgPtPRRpxMJyzQwuvK6HYlI1ISgDrCFrYdsaD/BuoEkt0fLhe4gNfnCkJICkmEe+Ac8Q4vlMrmdF9AkEAxw3Y73kYTtqBGWx4mZWZ3hdmN6PqKO9m+lRuGyb/2TEC4ew2DAMQiF0Xj9VMkqKUWWMYT37trsSspKp0hzHXtQJBAIahBHCA2+o2yONtZK7tuP0Hagyvy7LZW1JlHTJ3lChD6C63tXE2ObXJGp0wjE9DVGch8mHkYx5Z4TQLE/+idT0CQQCOtgoQrQzCX+vhgfphhPDZ8kK510inY/igq20AF/FBHw29vd0svUto227NiDcaLryrsna3JmRs8pe//urJdDi3";

        String uidStr = null;
        try {
            byte[] keyvalue = RSA.encryptByPublicKey(Base64.decodeBase64(uid),publickey);
            uidStr = new String(Base64.encodeBase64(keyvalue), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
//            RSA.decryptByPrivateKey(Base64.decodeBase64(uidStr),privateKet);
            String aa = new String(Base64.encodeBase64(RSA.decryptByPrivateKey(Base64.decodeBase64(uidStr),privateKet)), "UTF-8");
            System.out.print("解密完：" + aa);
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("uid",uidStr);
        String result = httpRequestClient.doPost("http://localhost:8085/openService/aaa",map);
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

    @RequestMapping(value = "index3")
    public String index3(){
        String resultStr = httpRequestClient.doGet("https://www.yrw.com/products/queryTransferProjectList?currentPage=1&pageSize=8&orderSource=rateDesc");
        JSONObject jsonObject = JSONObject.parseObject(resultStr);
        return "index3";
    }
}
