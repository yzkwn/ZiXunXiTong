package com.hhlt.konsultado.util;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HttpUtils {

    private static final Gson gson = new Gson();

    /**
     * GET 方法
     * @param url
     * @return
     */
    public static Map<String,Object> doGet(String url){
        Map<String,Object> map = new HashMap<>();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000) // 连接超时
                .setConnectionRequestTimeout(5000) // 请求超时
                .setSocketTimeout(5000)
                .setRedirectsEnabled(true) // 允许自动重定向
                .build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            if(httpResponse.getStatusLine().getStatusCode() == 200){
                String jsonResult = EntityUtils.toString(httpResponse.getEntity()) ;
                map = gson.fromJson(jsonResult,map.getClass());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * 封装post
     * @return
     */
    public static  String doPost(String url,String data, int timeout){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 超时设置
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(timeout) // 连接超时
                .setConnectionRequestTimeout(timeout) // 请求超时
                .setSocketTimeout(timeout)
                .setRedirectsEnabled(true) // 允许自动重定向
                .build();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        httpPost.addHeader("Content-Type","text/html;chartest=UTF-8");
        if(data != null && data instanceof String){ // 使用字符串传参
             StringEntity stringEntity = new StringEntity(data.toString(),"UTF-8");
             httpPost.setEntity(stringEntity);

        }
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            if(httpResponse.getStatusLine().getStatusCode() == 200 || httpResponse.getStatusLine().getStatusCode() == 8){
                String result = EntityUtils.toString(httpEntity);
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  null;
    }


    /**
     * http请求POST方式
     *
     * @param requestUrl
     *            请求URL
     * @param params
     *            map格式请求参数
     * @return String
     */
    public static String post(String requestUrl, Map<String, Object> params) {
        URL url;
        try {
            url = new URL(requestUrl);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            System.setProperty("sun.net.client.defaultConnectTimeout", "60000");// 连接超时15秒
            System.setProperty("sun.net.client.defaultReadTimeout", "60000"); // 读取超时30秒
            http.connect();
            String parameters = "";
            if(params!=null){
                Set<String> strings = params.keySet();
                for(String key : strings){
                    String value=params.get(key)!=null?params.get(key).toString():"";
                    value = URLEncoder.encode(value, "UTF-8");
                    parameters += key +"="+ value +"&";
                }
                if(strings.size()>0){
                    parameters = parameters.substring(0, parameters.length()-1);
                }
            }
            OutputStream os = http.getOutputStream();
            os.write(parameters.getBytes("UTF-8"));// 传入参数
            InputStream is = http.getInputStream();
            ByteArrayOutputStream jsonBytes=new ByteArrayOutputStream();
            byte[] bt=new byte[128];
            int len=0;
            while ((len=is.read(bt))!=-1){
                jsonBytes.write(bt,0,len);
            }
            String result = new String(jsonBytes.toByteArray(), "UTF-8");
            is.close();
            os.flush();
            os.close();
            System.out.println(result);
            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
