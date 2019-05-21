package com.bw.movie.model;

import com.bw.movie.app.UrlAll;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.RegistBean;
import com.bw.movie.util.OkHttpUtil;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import rx.Observer;

/**
 * @Author：dell
 * @E-mail： 15001194794@163.com
 * @Date：2019/5/10 19:39
 * @Description：描述信息
 */
public class MyModel {
    MyCallBack myCallBack;
    MyCallBack1 myCallBack1;

    public void postRequest(String phone,String pwd){
        OkHttpUtil okHttpUtil=OkHttpUtil.getInstance();
        okHttpUtil.doPost(UrlAll.Url_Login, phone, pwd, new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String string = responseBody.string();
                    Gson gson=new Gson();
                    LoginBean loginBean = gson.fromJson(string, LoginBean.class);
                    myCallBack.success(loginBean);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void postRequestt(String phone, String pwd, String nickName, String birthday, String email, int sex ,String pwd2){
        OkHttpUtil okHttpUtil=OkHttpUtil.getInstance();
        okHttpUtil.doRePost(UrlAll.Url_Regist,nickName,phone,pwd,pwd2,sex,birthday,email, new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String string = responseBody.string();
                    Gson gson=new Gson();
                    RegistBean registBean = gson.fromJson(string, RegistBean.class);
                    myCallBack1.success(registBean);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setMyCallBack(MyCallBack myCallBack) {
        this.myCallBack = myCallBack;
    }
    public void setMyCallBack1(MyCallBack1 myCallBack1) {
        this.myCallBack1 = myCallBack1;
    }

    public interface MyCallBack{
        public void success(Object o);
    }
    public interface MyCallBack1{
        public void success(Object o);
    }
}
