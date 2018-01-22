package jobs.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by alan.zheng on 2017/11/20.
 */
@Component
public class HttpRequestClient {
    private Logger logger = LoggerFactory.getLogger(HttpRequestClient.class);

    @Autowired
    private CloseableHttpClient httpClient;

    @Autowired
    private RequestConfig requestConfig;

    public String doGet(String url){

        // 创建http GET请求
        HttpGet httpGet = new HttpGet(url);

        httpGet.setConfig(this.requestConfig);

        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        try {
            // 执行请求
            try {
                response = httpClient.execute(httpGet);
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
            }
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                try {
                    entity = response.getEntity();
                    String responseBody = EntityUtils.toString(entity, "UTF-8");
                    return responseBody;
                } catch (IOException e) {
                    logger.error(e.getMessage(),e);
                }
            }
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(),e);
                }
            }
        }

        return null;
    }

    public String doJsonPost(String url, String json){

        // 创建http POST请求
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(this.requestConfig);
        HttpEntity entity = null;

        if (json != null) {
            // 构造一个form表单式的实体
            StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
            // 将请求实体设置到httpPost对象中
            httpPost.setEntity(stringEntity);
        }

        CloseableHttpResponse response = null;

        try {
            // 执行请求
            try {
                response = this.httpClient.execute(httpPost);
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
            }

            try {
                entity = response.getEntity();
                String  responseBody =  EntityUtils.toString(entity, "UTF-8");
                return responseBody;
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
            }

        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(),e);
                }
            }
        }
        return null;
    }

    public String doPost(String url, Map<String, Object> params, String version) {
        List<NameValuePair> pairs = null;
        if (params != null && !params.isEmpty()) {
            pairs = new ArrayList<NameValuePair>(params.size());
            for (String key : params.keySet()) {
                if(params.get(key)!=null ){
                    pairs.add(new BasicNameValuePair(key, params.get(key).toString()));
                }
            }
        }
        HttpPost httpPost = new HttpPost(url);
        if (pairs != null && pairs.size() > 0) {
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(pairs, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (StringUtils.isNotEmpty(version)){
            httpPost.setHeader("Accept-Version",version);
        }
        CloseableHttpResponse responseBody = null;
        String response = null;
        try {
            try {
                responseBody = httpClient.execute(httpPost);
            } catch (IOException e) {
                e.printStackTrace();
            }
            HttpEntity entity = null;
            try {
                entity = responseBody.getEntity();
                response =  EntityUtils.toString(entity, "UTF-8");
                return response;
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }finally {
            if (response != null) {
                try {
                    responseBody.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(),e);
                }
            }
        }
        return response;
    }
}
