package com.basic.core.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;


/**
 * @author: wangzw
 * @description: http工具类
 * @version: 1.0
 * @date: 2017/11/15 17:15
 */
public class HttpClientUtils {

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    /**
     * utf-8字符编码
     */
    public static final String CHARSET_UTF_8 = "utf-8";

    /**
     * HTTP内容类型
     */
    public static final String CONTENT_TYPE_TEXT_HTML = "text/xml";

    /**
     * HTTP内容类型。相当于form表单的形式，提交数据
     */
    public static final String CONTENT_TYPE_FORM_URL = "application/x-www-form-urlencoded";

    /**
     * HTTP内容类型。相当于form表单的形式，提交数据
     */
    public static final String CONTENT_TYPE_JSON_URL = "application/json;charset=utf-8";

    private static CloseableHttpClient closeableHttpClient;

    private static RequestConfig requestConfig;

    private static class HttpClientHolder{
       private static PoolingHttpClientConnectionManager pool;

       static CloseableHttpClient instance(){
            try {
                logger.info("初始化HttpClientUtils----start");
                SSLContextBuilder builder = new SSLContextBuilder();
                builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
                SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());
                // 配置同时支持 HTTP 和 HTPPS
                Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create().register(
                        "http", PlainConnectionSocketFactory.getSocketFactory()).register(
                        "https", sslsf).build();
                // 初始化连接管理器
                pool = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
                // 将最大连接数增加到200
                pool.setMaxTotal(200);
                // 设置最大路由
                pool.setDefaultMaxPerRoute(2);
                // 根据默认超时限制初始化requestConfig
                int socketTimeout = 10000;
                int connectTimeout = 10000;
                int connectionRequestTimeout = 10000;
                requestConfig = RequestConfig.custom().setConnectionRequestTimeout(connectionRequestTimeout).setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
                logger.info("初始化HttpClientUtils---end");
                return HttpClients.custom().setConnectionManager(pool).setDefaultRequestConfig(requestConfig).setRetryHandler(new DefaultHttpRequestRetryHandler(0, false)).build();
            }catch (Exception e) {
                e.printStackTrace();
            }
           return null;
        }
    }

    public static CloseableHttpClient getHttpClient() {
        if(closeableHttpClient == null){
            synchronized (HttpClientUtils.class){
                closeableHttpClient = HttpClientHolder.instance();
            }
        }
        return closeableHttpClient;
    }

    /**
     * @author: wangzw
     * @description: 发送post请求
     * @version: 1.0
     * @date: 2017/11/15 17:23
     */
    private static JSONObject sendHttpPost(HttpPost httpPost) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String responseContent = null;
        try {
            httpClient = getHttpClient();
            httpPost.setConfig(requestConfig);
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, CHARSET_UTF_8);
            EntityUtils.consume(entity);
            logger.info("HTTP-POST请求返回CODE:[{}],VAL:[{}]",response.getStatusLine().getStatusCode(),JSONObject.parse(responseContent));
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("responseCode",response.getStatusLine().getStatusCode());
            jsonObject.put("responseVal",responseContent);
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @author: wangzw
     * @description: 发送Get请求
     * @version: 1.0
     * @date: 2017/11/15 17:31
     */
    private static JSONObject sendHttpGet(HttpGet httpGet) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String responseContent = null;
        try {
            httpClient = getHttpClient();
            httpGet.setConfig(requestConfig);
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, CHARSET_UTF_8);
            EntityUtils.consume(entity);
            logger.info("HTTP-GET请求返回CODE:[{}],VAL:[{}]",response.getStatusLine().getStatusCode(),JSONObject.parse(responseContent));
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("responseCode",response.getStatusLine().getStatusCode());
            jsonObject.put("responseVal",responseContent);
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @author: wangzw
     * @description: 发送 post请求
     * @version: 1.0
     * @date: 2017/11/15 17:31
     */
    public static JSONObject sendHttpPost(String httpUrl) {
        HttpPost httpPost = new HttpPost(httpUrl);
        return sendHttpPost(httpPost);
    }

    /**
     * @author: wangzw
     * @description: 发送 get请求
     * @version: 1.0
     * @date: 2017/11/15 17:31
     */
    public static JSONObject sendHttpGet(String httpUrl) {
        HttpGet httpGet = new HttpGet(httpUrl);
        return sendHttpGet(httpGet);
    }

    /**
     * @author: wangzw
     * @description: 发送 get请求,参数map
     * @version: 1.0
     * @date: 2017/11/15 17:31
     */
    public static JSONObject sendHttpGet(String httpUrl,Map<String,Object> paramMap) {
        String stringParamter = convertStringParamter(paramMap);
        httpUrl = httpUrl+"?"+stringParamter;
        HttpGet httpGet = new HttpGet(httpUrl);
        return sendHttpGet(httpGet);
    }

    /**
     * @author: wangzw
     * @description: 发送post请求，x-www-form-urlencoded格式，参数(格式:key1=value1&key2=value2)
     * @version: 1.0
     * @date: 2017/11/15 17:32
     */
    public static JSONObject sendHttpPost(String httpUrl, String params) {
        HttpPost httpPost = new HttpPost(httpUrl);
        try {
            if (params != null && params.trim().length() > 0) {
                StringEntity stringEntity = new StringEntity(params, "UTF-8");
                stringEntity.setContentType(CONTENT_TYPE_FORM_URL);
                httpPost.setEntity(stringEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendHttpPost(httpPost);
    }

    /**
     * @author: wangzw
     * @description: 发送 post请求，x-www-form-urlencoded格式，参数map
     * @version: 1.0
     * @date: 2017/11/15 17:37
     */
    public static JSONObject sendHttpPost(String httpUrl, Map<String, String> maps) {
        String stringParamter = convertStringParamter(maps);
        return sendHttpPost(httpUrl, stringParamter);
    }

    /**
     * @author: wangzw
     * @description: 发送 post请求，application/json格式，参数map
     * @version: 1.0
     * @date: 2017/11/15 17:37
     */
    public static JSONObject sendHttpPostJson(String httpUrl, Map<String, String> maps) {
        String toJSONString = JSONObject.toJSONString(maps);
        return sendHttpPostJson(httpUrl, toJSONString);
    }

    /**
     * @author: wangzw
     * @description: 发送 post请求,参数(格式 json)
     * @version: 1.0
     * @date: 2017/11/15 17:33
     */
    public static JSONObject sendHttpPostJson(String httpUrl, String paramsJson) {
        HttpPost httpPost = new HttpPost(httpUrl);
        try {
            httpPost.addHeader("Content-type", "application/json");
            if (paramsJson != null && paramsJson.trim().length() > 0) {
                StringEntity stringEntity = new StringEntity(paramsJson, "UTF-8");
                stringEntity.setContentType(CONTENT_TYPE_JSON_URL);
                httpPost.setEntity(stringEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendHttpPost(httpPost);
    }

    /**
     * @author: wangzw
     * @description: 发送 post请求 发送xml数据，参数(格式 Xml)
     * @version: 1.0
     * @date: 2017/11/15 17:37
     */
    public static JSONObject sendHttpPostXml(String httpUrl, String paramsXml) {
        HttpPost httpPost = new HttpPost(httpUrl);
        try {
            if (StringUtils.isNotEmpty(paramsXml)) {
                StringEntity stringEntity = new StringEntity(paramsXml, "UTF-8");
                stringEntity.setContentType(CONTENT_TYPE_TEXT_HTML);
                httpPost.setEntity(stringEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendHttpPost(httpPost);
    }

    public static JSONObject sendHttpPostObject(String httpUrl, Map<String,Object> paramMap) {
        HttpPost httpPost = new HttpPost(httpUrl);
        try {
            MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();
            if(paramMap == null || paramMap.size() <= 0){
                return null;
            }
            for (String key : paramMap.keySet()) {
                meBuilder.addPart(key, new StringBody(paramMap.get(key).toString(), ContentType.TEXT_PLAIN));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendHttpPost(httpPost);
    }

    /**
     * @author: wangzw
     * @description: 发送 post请求,二进制流
     * @version: 1.0
     * @date: 2017/11/15 17:32
     */
    public static JSONObject sendHttpPostForByte(String httpUrl, Map<String, String> paramMap, Map<String,byte[]> byteMap) {
        HttpPost httpPost = new HttpPost(httpUrl);
        MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();
        if(paramMap == null || paramMap.size() <= 0 || byteMap == null || byteMap.size()<=0){
            return null;
        }
        for (String key : paramMap.keySet()) {
            meBuilder.addPart(key, new StringBody(paramMap.get(key), ContentType.TEXT_PLAIN));
        }
        for (String key : byteMap.keySet()) {
            meBuilder.addBinaryBody(key,byteMap.get(key));
        }
        HttpEntity reqEntity = meBuilder.build();
        httpPost.setEntity(reqEntity);
        return sendHttpPost(httpPost);
    }

    /**
     * @author: wangzw
     * @description: 发送 post请求（带文件）
     * @version: 1.0
     * @date: 2017/11/15 17:32
     */
    public static JSONObject sendHttpPostForFile(String httpUrl, Map<String, String> paramMap,Map<String,File> fileMap) {
        HttpPost httpPost = new HttpPost(httpUrl);
        MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();
        if(paramMap == null || paramMap.size() <= 0 || fileMap == null || fileMap.size()<=0){
            return null;
        }
        for (String key : paramMap.keySet()) {
            meBuilder.addPart(key, new StringBody(paramMap.get(key), ContentType.TEXT_PLAIN));
        }
        for (String key : fileMap.keySet()) {
            FileBody fileBody = new FileBody(fileMap.get(key));
            meBuilder.addPart(key, fileBody);
        }
        HttpEntity reqEntity = meBuilder.build();
        httpPost.setEntity(reqEntity);
        return sendHttpPost(httpPost);
    }

    /**
     * @author: wangzw
     * @description: 将map集合的键值对转化成：key1=value1&key2=value2 的形式
     * @version: 1.0
     * @date: 2017/11/15 17:36
     */
    public static String convertStringParamter(Map parameterMap) {
        StringBuffer parameterBuffer = new StringBuffer();
        if (parameterMap != null) {
            Iterator iterator = parameterMap.keySet().iterator();
            String key = null;
            String value = null;
            while (iterator.hasNext()) {
                key = (String) iterator.next();
                if (parameterMap.get(key) != null) {
                    value = (String) parameterMap.get(key);
                } else {
                    value = "";
                }
                parameterBuffer.append(key).append("=").append(value);
                if (iterator.hasNext()) {
                    parameterBuffer.append("&");
                }
            }
        }
        return parameterBuffer.toString();
    }

}