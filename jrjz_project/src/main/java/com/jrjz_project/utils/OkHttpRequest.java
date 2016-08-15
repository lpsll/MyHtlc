package com.jrjz_project.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by John on 2016/5/24.
 * OkHttp 封装类
 */
public class OkHttpRequest {
    private static OkHttpRequest okHttpRequest;
    private OkHttpClient okHttpClient;
    /**
     * desctipt:获得当前方法的类对象
     * @author  John
     * @return
     */
    public  static OkHttpRequest getInstall(){
        if(okHttpRequest ==null){
            okHttpRequest =new OkHttpRequest();
        }
        return okHttpRequest;
    }

    /**
     * 构建get请求
     * @param url
     * @param formBody
     * @return Request
     */
    public static Request getOfRequest(String url,FormBody formBody){
        Request request=new Request.Builder()
                .get()
                .url(url)
                .put(formBody)
                .build();
//        okHttpClient.newCall(request).enqueue(new Callback().onFailure(Ca););
        return request;
    }

    /**
     * 构建post请求
     * @param url
     * @param formBody
     * @return Request
     */
    public static Request Request(String url,FormBody formBody){
        Request request=new Request.Builder()
                .post(formBody)
                .url(url)
                .build();
        return request;
    }


    /**
     * 开启异步线程访问网络
     * @param request
     * @param responseCallback
     */
    public void enqueue(Request request, Callback responseCallback){
        if(okHttpClient==null){
            okHttpClient=new OkHttpClient.Builder()
                    .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                    .readTimeout(10000L, TimeUnit.MILLISECONDS)
                    .build();
        }
        okHttpClient.newCall(request).enqueue(responseCallback);
    }
}
