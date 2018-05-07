package controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import jobs.common.HttpRequestClient;
import jobs.common.WeiXinClient;
import jobs.config.WeiXinToken;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sign.RSA;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Created by alan.zheng on 2017/11/20.
 */
@RestController
@RequestMapping(value = "/home")
public class HomeController {
    @Autowired
    private HttpRequestClient httpRequestClient;

    @Autowired
    private WeiXinClient weiXinClient;

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
                    String result = httpRequestClient.doPost("https://api.yrw.com/security/find/dynamicInvoke?invokeMethod=3",map,"1.0.0");
                }
            });
        }
//        return result;
    }

    @RequestMapping(value = "/renqizhi")
    String renqizhi(){
        final CountDownLatch latch = new CountDownLatch(5);

        new Thread(){
            public void run(){
                Map map = new HashMap();
                map.put("invokeParameters",
                        "args_goodId_1_long_86&args_amount_1_integer_5&args_goodsCount_1_integer_1");
                map.put("loginSource",2);
                map.put("device","0a27a9962fe0f8ef107f85f0a13b37441c8a2904");
                map.put("token","R6ZH8ev6Cp6yl4adhND3qRF2lPNyrAu2CL3MehlNrRwfujFz5TQTVWeFXQU5jrx7D9qURrcQwsqfYrjHoUhN2A==");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
                String result = httpRequestClient.doPost("http://192.168.0.51:8082/security/find/dynamicInvoke?invokeMethod=3",map,"1.0.0");
            }
        }.start();
        new Thread(){
            public void run(){
                Map map = new HashMap();
                map.put("invokeParameters",
                        "args_goodId_1_long_86&args_amount_1_integer_5&args_goodsCount_1_integer_1");
                map.put("loginSource",2);
                map.put("device","0a27a9962fe0f8ef107f85f0a13b37441c8a2904");
                map.put("token","shPM2FFrUH1OLlTWvZSfb0O2SsaqYYXPzbdMgykkBVPrdMzb39Oeb2DqIkV0KLcXT9Uz9BdZ1a67yz7QcFwmKw==");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
                String result = httpRequestClient.doPost("http://192.168.0.51:8082/security/find/dynamicInvoke?invokeMethod=3",map,"1.0.0");
            }
        }.start();
        new Thread(){
            public void run(){
                Map map = new HashMap();
                map.put("invokeParameters",
                        "args_goodId_1_long_86&args_amount_1_integer_5&args_goodsCount_1_integer_1");
                map.put("loginSource",2);
                map.put("device","0a27a9962fe0f8ef107f85f0a13b37441c8a2904");
                map.put("token","vlZPg0yXua7sT962SrOMvBlNMq0CVK4IBqQKl4tB2vkKiGIbD+RvPuI6n9W0A21auu/ABJW/lgaF5GuyhVNhhg==");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
                String result = httpRequestClient.doPost("http://192.168.0.51:8082/security/find/dynamicInvoke?invokeMethod=3",map,"1.0.0");
            }
        }.start();
        new Thread(){
            public void run(){
                Map map = new HashMap();
                map.put("invokeParameters",
                        "args_goodId_1_long_86&args_amount_1_integer_5&args_goodsCount_1_integer_1");
                map.put("loginSource",2);
                map.put("device","0a27a9962fe0f8ef107f85f0a13b37441c8a2904");
                map.put("token","CTpBGYmczExOlwmPy3hx10MhW8AnjdWgkEVk8eO4OkB82WmOHD8UBjI4qOJCiib5D9pjCaUAiouLa9IMKJt/Xw==");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
                String result = httpRequestClient.doPost("http://192.168.0.51:8082/security/find/dynamicInvoke?invokeMethod=3",map,"1.0.0");
            }
        }.start();
        new Thread(){
            public void run(){
                Map map = new HashMap();
                map.put("invokeParameters",
                        "args_goodId_1_long_86&args_amount_1_integer_5&args_goodsCount_1_integer_1");
                map.put("loginSource",2);
                map.put("device","0a27a9962fe0f8ef107f85f0a13b37441c8a2904");
                map.put("token","R6ZH8ev6Cp6yl4adhND3qRF2lPNyrAu2CL3MehlNrRwfujFz5TQTVWeFXQU5jrx7D9qURrcQwsqfYrjHoUhN2A==");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
                String result = httpRequestClient.doPost("http://192.168.0.51:8082/security/find/dynamicInvoke?invokeMethod=3",map,"1.0.0");
            }
        }.start();


        new Thread(){
            public void run(){
                Map map = new HashMap();
                map.put("invokeParameters",
                        "args_goodId_1_long_86&args_amount_1_integer_5&args_goodsCount_1_integer_1");
                map.put("loginSource",2);
                map.put("device","0a27a9962fe0f8ef107f85f0a13b37441c8a2904");
                map.put("token","JuucH4tihzD9OMJo2qbGGkyti8t2nMKPEo5j5WXHOa02oQjs66+bPcR1D1nK2bmojDXVtIz5WbKjJypx8gujwQ==");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
                String result = httpRequestClient.doPost("http://192.168.0.51:8082/security/find/dynamicInvoke?invokeMethod=3",map,"1.0.0");
            }
        }.start();
        new Thread(){
            public void run(){
                Map map = new HashMap();
                map.put("invokeParameters",
                        "args_goodId_1_long_86&args_amount_1_integer_5&args_goodsCount_1_integer_1");
                map.put("loginSource",2);
                map.put("device","0a27a9962fe0f8ef107f85f0a13b37441c8a2904");
                map.put("token","+rmj21Q4zg5/ytu+68igfRBGo8ip93xdCC6gsyEGh/qPdCn9EZ89GD2WtDGHl/lKblE8/5t1LjbJ2vFStHRUWw==");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
                String result = httpRequestClient.doPost("http://192.168.0.51:8082/security/find/dynamicInvoke?invokeMethod=3",map,"1.0.0");
            }
        }.start();
        new Thread(){
            public void run(){
                Map map = new HashMap();
                map.put("invokeParameters",
                        "args_goodId_1_long_86&args_amount_1_integer_5&args_goodsCount_1_integer_1");
                map.put("loginSource",2);
                map.put("device","0a27a9962fe0f8ef107f85f0a13b37441c8a2904");
                map.put("token","eBnYTpbe6MlMTIyGy3nPcfayd0EsZ9JaMV3rilEceSuoNn8RiGq4P+t6zAxEXnz2+ZbaKv4giwWdW2Ftn37AKA==");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
                String result = httpRequestClient.doPost("http://192.168.0.51:8082/security/find/dynamicInvoke?invokeMethod=3",map,"1.0.0");
            }
        }.start();
        new Thread(){
            public void run(){
                Map map = new HashMap();
                map.put("invokeParameters",
                        "args_goodId_1_long_86&args_amount_1_integer_5&args_goodsCount_1_integer_1");
                map.put("loginSource",2);
                map.put("device","0a27a9962fe0f8ef107f85f0a13b37441c8a2904");
                map.put("token","vq6GSaVxnJhyvTZbBqVak8hzl3ynQbVvZGKJLDagkaAaowEYD6dqno/8zDf1RWDKjB61lX2p3oqFO5t6NuWvvw==");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
                String result = httpRequestClient.doPost("http://192.168.0.51:8082/security/find/dynamicInvoke?invokeMethod=3",map,"1.0.0");
            }
        }.start();
        new Thread(){
            public void run(){
                Map map = new HashMap();
                map.put("invokeParameters",
                        "args_goodId_1_long_86&args_amount_1_integer_5&args_goodsCount_1_integer_1");
                map.put("loginSource",2);
                map.put("device","0a27a9962fe0f8ef107f85f0a13b37441c8a2904");
                map.put("token","ZzdSPfWyroYD/KzkXFvBpv3rbhcpNt6U1k/hCMYl3i949Oc13x+a0cZufJ4FdhZFO80Gz1AI4BMKdLAtRzF0/Q==");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
                String result = httpRequestClient.doPost("http://192.168.0.51:8082/security/find/dynamicInvoke?invokeMethod=3",map,"1.0.0");
            }
        }.start();
        try {
            latch.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "执行完毕";
    }

    @RequestMapping(value = "/dantiao")
    String dantiao(){
        Map map = new HashMap();
        map.put("invokeParameters",
                "args_goodId_1_long_86&args_amount_1_integer_5&args_goodsCount_1_integer_1");
        map.put("loginSource",2);
        map.put("device","0a27a9962fe0f8ef107f85f0a13b37441c8a2904");
        map.put("token","R6ZH8ev6Cp6yl4adhND3qRF2lPNyrAu2CL3MehlNrRwfujFz5TQTVWeFXQU5jrx7D9qURrcQwsqfYrjHoUhN2A==");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String result = httpRequestClient.doPost("http://192.168.0.51:8082/security/find/dynamicInvoke?invokeMethod=3",map,"1.0.0");
        return "success";
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
        String result = httpRequestClient.doPost("http://localhost:8085/openService/aaa",map,"1.0.0");
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
        String response = httpRequestClient.doPost("https://api.yrw.com/logining",map,"1.0.0");
        //String response = httpRequestClient.doPost("http://192.168.0.51:8082/logining",map);
        JSONObject jsonObject = JSONObject.parseObject(response);
        JSONObject o = (JSONObject)jsonObject.get("result");
        return o.get("token").toString();
    }

    @RequestMapping(value = "index3")
    public String index3(){
        String resultStr = httpRequestClient.doGet("http://192.168.0.51:8080/products/queryTransferProjectList?currentPage=1&pageSize=8&orderSource=rateDesc");
        JSONObject jsonObject = JSONObject.parseObject(resultStr);
        JSONObject ar = null;
        jsonObject.get("success");
        System.out.print("a："+jsonObject.get("success"));
        BigDecimal baseRate = new BigDecimal("40");
        if ((Boolean) jsonObject.get("success")){
            JSONArray jsonArray = (JSONArray) jsonObject.get("resultList");
            for (int i = 0;i<jsonArray.size();i++){
                ar = (JSONObject) jsonArray.get(i);
                ar.get("minAnnualizedRate");
                BigDecimal rate = new BigDecimal(ar.get("minAnnualizedRate")+"");
                BigDecimal availableBalance = new BigDecimal(ar.get("availableBalance")+"");
                if (i==1){
//                        if (baseRate.compareTo(rate)<0){
//                        weiXinClient.monitorTransferProject(ar.get("name")+"",rate,availableBalance,"oYzLx0oYFJyaV3qGprKHm6DSRHBA");
                        //自动投资
                        Map<String,Object> postMap = new HashMap<String, Object>();
//                        String token = getToken("JXmufrTPbmzGaGTCld7DJA==","HnxrxgodkpzHI1SS5GUWiA==");
                        String token = "/0+tUQVzMpv1Pvp7CYcpASx80A/pzuz8wT6i25Iw0nn71U5OR8sv4Kmc2xFnjtwK3Vow2gw0b0v9bHvxF4+aaA==";
                        String thumbnail = ar.get("thumbnail")+"";
                        if (StringUtils.isNotEmpty(thumbnail)){
                            String[] args = thumbnail.split("/");
                            String projectid = args[args.length - 1];
                            postMap.put("projectId",projectid.substring(0,9));
                        }
                        BigDecimal invest = availableBalance.multiply(new BigDecimal("0.6"));
                        int a = invest.intValue()/1000 + 1;
                        int b = a*1000;
                        BigDecimal totalInvest = new BigDecimal(b);
                        postMap.put("transferId",ar.get("id"));
                        postMap.put("projectCategory","2");
                        postMap.put("transferPrincipal",totalInvest);
                        //postMap.put("device","8905da6cb8ded9bf57977e7710a4d279df18476d");
                        postMap.put("device","8905da6cb8ded9bf57977e7710a4d279df18476d");
                        postMap.put("token",token);
                        String result = httpRequestClient.doPost("http://192.168.0.51:8082/security/order/createOrder",postMap,"1.7.0");
                        System.out.print(result);
                    //支付
                    JSONObject resultJson = JSONObject.parseObject(result);
                    if ((Boolean) resultJson.get("success")){
                        JSONObject orderResult = (JSONObject) resultJson.get("result");
                        Map<String,Object> payMap = new HashMap<String, Object>();
                        payMap.put("orderNo",orderResult.get("orderNo"));
                        payMap.put("usedCapital",orderResult.get("investAmount"));
                        payMap.put("device","8905da6cb8ded9bf57977e7710a4d279df18476d");
                        payMap.put("token",token);
                        String payResult = httpRequestClient.doPost("http://192.168.0.51:8082/security/transaction/pay/order/cashDesk",payMap,"1.7.0");
                        System.out.print(payResult);
                    }

//                    }
                }

            }
        }
        return "index3";
    }

    private String getToken(String username,String password){
        Map map = new HashMap();
        map.put("username",username);
        map.put("password",password);
        map.put("loginSource","2");
        map.put("device","8905da6cb8ded9bf57977e7710a4d279df18476d");
        map.put("equipment","iPhone");
        String response = httpRequestClient.doPost("http://192.168.0.51:8082/logining",map,"1.0.0");
        JSONObject jsonObject = JSONObject.parseObject(response);
        JSONObject o = (JSONObject)jsonObject.get("result");
        return o.get("token").toString();
    }

    @RequestMapping(value = "index4")
    public String index4(){
        String token = "/0+tUQVzMpv1Pvp7CYcpASx80A/pzuz8wT6i25Iw0nn71U5OR8sv4Kmc2xFnjtwK3Vow2gw0b0v9bHvxF4+aaA==";
        Map<String,Object> payMap = new HashMap<String, Object>();
        payMap.put("orderNo","YRTC20180122141853593003126");
        payMap.put("device","8905da6cb8ded9bf57977e7710a4d279df18476d");
        payMap.put("token",token);
        String payResult = httpRequestClient.doPost("http://192.168.0.51:8082/security/transaction/pay/order/cashDesk",payMap,"1.7.0");
        System.out.print(payResult);
        return "index4";
    }

    @RequestMapping(value = "/index5")
    @ResponseBody
    public String index5(){
        String token = WeiXinToken.getToken();
        return token;
    }
}
