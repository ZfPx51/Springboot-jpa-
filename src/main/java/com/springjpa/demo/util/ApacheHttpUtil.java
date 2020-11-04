package com.springjpa.demo.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;


public class ApacheHttpUtil {
    //get请求
    public String httpGet(String url) throws IOException {
        //创建客户端连接对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建请求配置对象
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)   //设置连接超时时间
                .setConnectionRequestTimeout(5000) // 设置请求超时时间
                .setSocketTimeout(5000)
                .setRedirectsEnabled(true)//默认允许自动重定向
                .build();
        //创建HttpGet对象
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        HttpResponse httpResponse = httpClient.execute(httpGet);
        //请求被应答了
        HttpEntity entity = httpResponse.getEntity();
        String strResult = EntityUtils.toString(entity);//获得返回的结果
        httpClient.close();
        return strResult;
    }


    public String httpPost(String url, List<BasicNameValuePair> params) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //生成httppost对象
        HttpPost post = new HttpPost(url);
        // 创建表单的Entity对象,第一个参数是封装好的表单数据，第二个参数就是编码方式
        UrlEncodedFormEntity formEntity=new UrlEncodedFormEntity(params,"utf8");
        //设置表单的Entity对象到Post请求中
        post.setEntity(formEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        HttpEntity news = response.getEntity();
        String str = EntityUtils.toString(news, "utf-8");
        httpClient.close();
        return str;
    }
}
