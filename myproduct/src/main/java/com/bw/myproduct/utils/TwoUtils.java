package com.bw.myproduct.utils;

import android.util.Log;

import com.bw.myproduct.api.GouAPI;

import java.io.File;
import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TwoUtils {

    static TwoUtils okHttpUtil;
    OkHttpClient okHttpClient;
    private final Retrofit retrofit;
    //封装单例
    private TwoUtils(){
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        builder.addInterceptor(new MyInterceptor());
        okHttpClient = builder.build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://172.17.8.100/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

    }
    public  static TwoUtils getInstance(){
        if(okHttpUtil==null){
            okHttpUtil=new TwoUtils();
        }
        return okHttpUtil;
    }
    //拦截器
    public  class MyInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Log.e("tag","url2="+request.url());
            Response response = chain.proceed(request);
            return response;
        }
    }
    //购物车展示
    public void doGou(String url, int userId, String sessionId, Observer<ResponseBody> observer) {
        Observable<ResponseBody> observable = retrofit.create(GouAPI.class).doGouGet(url, userId, sessionId);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
    //结算
    public void doSuanGet(String url, int userId, String sessionId, String data, double sum, int address,Observer<ResponseBody> observer) {
        Observable<ResponseBody> observable = retrofit.create(GouAPI.class).doSuan(url, userId, sessionId, data, sum, address);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
    //添加地址
    public void doAddGet(String url, int userId, String sessionId, String name, String phone, String address, String bian,Observer<ResponseBody> observer) {
        Observable<ResponseBody> observable = retrofit.create(GouAPI.class).doAdd(url, userId, sessionId, name, phone, address, bian);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
    //我的资料
    public void doDataGet(String url, int userId, String sessionId,Observer<ResponseBody> observer) {
        Observable<ResponseBody> observable = retrofit.create(GouAPI.class).doGouGet(url, userId, sessionId);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
    //修改昵称
    public void doUpNameGet(String url, int userId, String sessionId, String nickName, Observer<ResponseBody> observer) {
        Observable<ResponseBody> observable = retrofit.create(GouAPI.class).doUpName(url, userId, sessionId, nickName);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
    //修改密码
    public void doPwdGet(String url, int userId, String sessionId, String oldName, String newName, Observer<ResponseBody> observer) {
        Observable<ResponseBody> observable = retrofit.create(GouAPI.class).doUpPwd(url, userId, sessionId, oldName,newName);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
    //上传头像
    public void doImageGet(String url, int userId, String sessionId, File file, Observer<ResponseBody> observer) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("application/otcet-stream"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        Observable<ResponseBody> observable = retrofit.create(GouAPI.class).doImage(url, userId, sessionId,body);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
    //我的圈子
    public void doQuanGet(String url, int userId, String sessionId, int page, int count, Observer<ResponseBody> observer) {
        Observable<ResponseBody> observable = retrofit.create(GouAPI.class).doQuan(url, userId, sessionId, page, count);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
     //删除我发表过的圈子
    public void doDeleteGet(String url, int userId, String sessionId, int circleId, Observer<ResponseBody> observer) {
        Observable<ResponseBody> observable = retrofit.create(GouAPI.class).doGiveOut(url, userId, sessionId,circleId);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    //我的足迹
    public void doFootGet(String url, int userId, String sessionId, int page, int count, Observer<ResponseBody> observer) {
        Observable<ResponseBody> observable = retrofit.create(GouAPI.class).doQuan(url, userId, sessionId, page, count);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
    //我的钱包
    public void doWalletGet(String url, int userId, String sessionId, int page, int count, Observer<ResponseBody> observer) {
        Observable<ResponseBody> observable = retrofit.create(GouAPI.class).doQuan(url, userId, sessionId, page, count);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
    //地址列表
    public void doAddress(String url, int userId, String sessionId, Observer<ResponseBody> observer) {
        Observable<ResponseBody> observable = retrofit.create(GouAPI.class).doGouGet(url, userId, sessionId);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
    //默认地址
    public void doDefaultGet(String url,int userId,String sessionId, int id, Observer<ResponseBody> observer) {
        Observable<ResponseBody> observable = retrofit.create(GouAPI.class).doDefault(url, userId, sessionId, id);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
    //修改地址
    public void doUpdateGet(String url, int userId, String sessionId, int id, String name, String phone, String address, String bian, Observer<ResponseBody> observer) {
        Observable<ResponseBody> observable = retrofit.create(GouAPI.class).doUpdate(url, userId, sessionId, id, name, phone, address, bian);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
    //待支付订单
    public void doAllOrders(String url, int userId, String sessionId, int status, int page, int count, Observer<ResponseBody> observer) {
        Observable<ResponseBody> observable = retrofit.create(GouAPI.class).doOrder(url, userId, sessionId, status, page, count);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
    //取消订单或者删除订单
    public void doCancleGet(String url, int userId, String sessionId, String id, Observer<ResponseBody> observer) {
        Observable<ResponseBody> observable = retrofit.create(GouAPI.class).doCanCle(url, userId, sessionId,id);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
    //支付
    public void doPayGet(String url, int userId, String sessionId, String id, int type, Observer<ResponseBody> observer) {
        Observable<ResponseBody> observable = retrofit.create(GouAPI.class).doPay(url, userId, sessionId,id,type);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
    //确认订单
    public void doAffirms(String url, int userId, String sessionId, String orderId, Observer<ResponseBody> observer) {
        Observable<ResponseBody> observable = retrofit.create(GouAPI.class).doAffirm(url, userId, sessionId,orderId);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
    //去评论
    public void doReportGet(String url, int userId, String sessionId,int commodityId,String orderId, String content, File file,Observer<ResponseBody> observer) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("application/otcet-stream"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        Observable<ResponseBody> observable = retrofit.create(GouAPI.class).doReport(url, userId, sessionId,commodityId,orderId,content,body);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
     //同步到圈子
    public void doSynchGet(String url, int userId, String sessionId, int commodityId, String content, File file, Observer<ResponseBody> observer) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("application/otcet-stream"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        Observable<ResponseBody> observable = retrofit.create(GouAPI.class).doSynch(url, userId, sessionId,commodityId,content,body);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    //点赞
    public void doGiveUpGet(String url, int userId, String sessionId, int circleId, Observer<ResponseBody> observer) {
        Observable<ResponseBody> observable = retrofit.create(GouAPI.class).doGiveUp(url, userId, sessionId,circleId);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
    //取消点赞
    public void doGiveUpOut(String url, int userId, String sessionId, int circleId, Observer<ResponseBody> observer) {
        Observable<ResponseBody> observable = retrofit.create(GouAPI.class).doGiveOut(url, userId, sessionId,circleId);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
}
