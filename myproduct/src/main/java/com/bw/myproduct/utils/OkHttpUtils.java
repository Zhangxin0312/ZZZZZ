package com.bw.myproduct.utils;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.bw.myproduct.model.MyModel;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpUtils {
    static  OkHttpUtils okHttpUtils;
    OkHttpClient okHttpClient;
    private  OkHttpUtils(){
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        builder.addInterceptor(new MyInterceptor());
         okHttpClient = builder.build();
    }
    public  static  OkHttpUtils getInstance(){
        if(okHttpUtils==null){
            okHttpUtils=new OkHttpUtils();
        }
        return okHttpUtils;
    }
    public  class MyInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Log.e("tag","url="+request.url());
            Response response = chain.proceed(request);
            return response;
        }
    }
    // 注册
    public void doPost(String url, String phone, String pwd, final Handler handler, MyModel.MyCallBack myCallBack) {
        FormBody.Builder formbody=new FormBody.Builder();
        formbody.add("phone",phone);
        formbody.add("pwd",pwd);
        RequestBody body= formbody.build();
        Request request=new Request.Builder()
                .post(body)
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Message message =new Message() ;
                message.obj=json;
                message.arg1=1;
                handler.sendMessage(message);
            }
        });
    }
    //登录
    public void doLoginPost(String url, String phone, String pwd, final Handler handler, MyModel.MyCallBack myCallBack) {
        FormBody.Builder formbody=new FormBody.Builder();
        formbody.add("phone",phone);
        formbody.add("pwd",pwd);
        RequestBody body= formbody.build();
        Request request=new Request.Builder()
                .post(body)
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Message message =new Message() ;
                message.obj=json;
                message.arg1=2;
                handler.sendMessage(message);
            }
        });
    }
    //Banner展示
    public void doShowGet(String url, final Handler handler, MyModel.MyCallBack myCallBack) {
        Request request=new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Message message =new Message() ;
                message.obj=json;
                message.arg1=3;
                handler.sendMessage(message);
            }
        });
    }
    //首页数据展示
    public void doShowGet2(String url, final Handler handler, MyModel.ShowCallBack showCallBack) {
        Request request=new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Message message =new Message() ;
                message.obj=json;
                message.arg1=4;
                handler.sendMessage(message);
            }
        });
    }
    //搜索 ....
    public void doSouGet(String url, String content, int page, final Handler handler) {
        url=url+"?keyword="+content+"&page="+page+"&count=15";
        Request request=new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Message message =new Message() ;
                message.obj=json;
                message.arg1=5;
                handler.sendMessage(message);
            }
        });
    }
    //popupwindow的一级列表
    public void doPopGet(String url, final Handler handler) {
        Request request=new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Message message =new Message() ;
                message.obj=json;
                message.arg1=6;
                handler.sendMessage(message);
            }
        });
    }
    //popwindow的二级列表
    public void doPopGet2(String url, String id, final Handler handler) {
        url=url+"?firstCategoryId="+id;
        Request request=new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Message message =new Message() ;
                message.obj=json;
                message.arg1=7;
                handler.sendMessage(message);
            }
        });
    }
    //详情
    public void doXiangGet(String url, int id, int userId, String sessionId, final Handler handler) {
        url=url+"?commodityId="+id;
        Request.Builder builder=new Request.Builder()
                .get()
                .url(url)
                .addHeader("userId", String.valueOf(userId))
                .addHeader("sessionId",sessionId);
        Request request = builder.build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Message message =new Message() ;
                message.obj=json;
                message.arg1=8;
                handler.sendMessage(message);
            }
        });
    }
    //评论
    public void doPingGet(String url, int id, int page, final Handler handler) {
        url=url+"?commodityId="+id+"&page="+page+"&count=20";
        Request request=new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Message message =new Message() ;
                message.obj=json;
                message.arg1=9;
                handler.sendMessage(message);
            }
        });
    }
    //圈子列表
    public void doQuanGet(String url, int userId, String sessionId, int page, final Handler handler) {
        url=url+"?page="+page+"&count=5";
        Request.Builder builder=new Request.Builder()
                .get()
                .url(url)
                .addHeader("userId", String.valueOf(userId))
                .addHeader("sessionId",sessionId);
        Request request = builder.build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Message message =new Message() ;
                message.obj=json;
                message.arg1=10;
                handler.sendMessage(message);
            }
        });
    }
    //加入购物车
    public void doAddGet(String url, int userId, String sessionId,String data, final Handler handler) {
        FormBody.Builder builder=new FormBody.Builder();
        builder.add("data",data);
        RequestBody body = builder.build();
        Request.Builder builder1=new Request.Builder()
                .put(body)
                .url(url)
                .addHeader("userId", String.valueOf(userId))
                .addHeader("sessionId",sessionId);
        Request request = builder1.build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Message message =new Message() ;
                message.obj=json;
                message.arg1=11;
                handler.sendMessage(message);
            }
        });
    }
}
