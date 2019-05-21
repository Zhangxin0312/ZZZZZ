package com.bw.movie.util;

import com.bw.movie.app.Api;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Author：dell
 * @E-mail： 15001194794@163.com
 * @Date：2019/5/10 19:34
 * @Description：描述信息
 */
public class OkHttpUtil {
    OkHttpClient okHttpClient;
    static OkHttpUtil Utile;
    Retrofit retrofit;
    private OkHttpUtil(){

        okHttpClient=new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build();
        retrofit=new Retrofit.Builder()
                .baseUrl("http://mobile.bwstudent.com/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }
    public static OkHttpUtil getInstance(){
        if(Utile==null){
            Utile=new OkHttpUtil();
        }
        return Utile;
    }

    public void doPost(String url, String phone, String pwd, Observer<ResponseBody> observer){
        Api api=retrofit.create(Api.class);
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("phone",phone);
        hashMap.put("pwd",pwd);
        Observable<ResponseBody> observable = api.post(url, hashMap);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
    public void doRePost(String url,String nickName,String phone,String pwd,String pwd2,int sex,String birthday,String email,Observer<ResponseBody>observer){

        Api api=retrofit.create(Api.class);
        Observable<ResponseBody> observable = api.rpost(url,nickName,phone,pwd,pwd2,sex,birthday,email);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);

    }
}
