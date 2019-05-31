package com.bw.movie.util;

import com.bw.movie.app.Api2;
import com.bw.movie.app.App;
import com.google.gson.Gson;

import java.io.File;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HttpUtils {
   public static HttpUtils httpUtils;
    OkHttpClient okHttpClient;
    public  Retrofit retrofit;
    private final int userId;
    private final String sessionId;

    private  HttpUtils(){
        okHttpClient=new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://mobile.bwstudent.com/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(okHttpClient)
                .build();
        userId = App.UserId;
        sessionId = App.SessionId;
   }

   public  static  HttpUtils getInstance(){
        if(httpUtils==null){
            httpUtils=new HttpUtils();
        }
       return  httpUtils;
   }
    //热门电影
    public void doBanGet(String url, int page, int count, Observer<ResponseBody> observer) {
        Observable<ResponseBody> observable = retrofit.create(Api2.class).Bans(url, userId, sessionId, page, count);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
     //正在上映
    public void doShowingGet(String url, int page, int count, Observer<ResponseBody> observer) {
        Observable<ResponseBody> observable = retrofit.create(Api2.class).Bans(url, userId, sessionId, page, count);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
     //即将上映
    public void doShowOnGet(String url, int page, int count, Observer<ResponseBody> observer) {
        Observable<ResponseBody> observable = retrofit.create(Api2.class).Bans(url, userId, sessionId, page, count);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
     //影片详情
    public void doXiangGet(String url, int id, Observer<ResponseBody> observer) {
        Observable<ResponseBody> observable = retrofit.create(Api2.class).Xiang(url,userId,sessionId,id);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
    //电影详情
    public void doMovingGet(String url, int id, Observer<ResponseBody> observer) {
        Observable<ResponseBody> observable = retrofit.create(Api2.class).Xiang(url,userId,sessionId,id);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
     //电影评论
    public void doAssessGet(String url, int id,int page, int  count ,  Observer<ResponseBody> observer) {
        Observable<ResponseBody> observable = retrofit.create(Api2.class).Assess(url,userId,sessionId,id,page,count);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
    //添加回复的评论
    public void doAddPing(String url, HashMap<String,Object> map, Observer<ResponseBody> observer) {
        Observable<ResponseBody> observable = retrofit.create(Api2.class).AddPing(url,userId,sessionId,map);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
    //添加对影片的评论
    public void doAddReply(String url, HashMap<String,Object> map, Observer<ResponseBody> observer) {
        Observable<ResponseBody> observable = retrofit.create(Api2.class).AddPing(url,userId,sessionId,map);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
    //关注
    public void doAttention(String url, int id, Observer<ResponseBody> observer) {
        Observable<ResponseBody> observable = retrofit.create(Api2.class).Xiang(url,userId,sessionId,id);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
     //取消关注
    public void doCancle(String url, int id, Observer<ResponseBody> observer) {
        Observable<ResponseBody> observable = retrofit.create(Api2.class).Xiang(url,userId,sessionId,id);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
    //点赞
    public void doDian(String url, int id, Observer<ResponseBody> observer) {
        HashMap<String,Object>  map=new HashMap<>();
        map.put("commentId",id);
        Observable<ResponseBody> observable = retrofit.create(Api2.class).Dian(url,userId,sessionId,map);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
     //根据电影ID查询当前排片该电影的影院列表
    public void doFind(String url, int id, Observer<ResponseBody> observer) {
        Observable<ResponseBody> observable = retrofit.create(Api2.class).Find(url,id);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);

    }
    //关注影院
    public void doFllowCinema(String url, int id, Observer<ResponseBody> observer) {
        Observable<ResponseBody> observable = retrofit.create(Api2.class).Fllow(url,userId,sessionId,id);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
    //取消关注影院
    public void doCancleFllowCinema(String url, int id, Observer<ResponseBody> observer) {
        Observable<ResponseBody> observable = retrofit.create(Api2.class).Fllow(url,userId,sessionId,id);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
   //根据电影ID和影院ID查询电影排期列表
    public void doMovieScheduleList(String url, int movieId, int cinemasId, Observer<ResponseBody> observer) {
        Observable<ResponseBody> observable = retrofit.create(Api2.class).MovieScheduleList(url,userId,sessionId,movieId,cinemasId);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
    //根据用户ID查询用户信息
    public void getUserInfo(String url, Observer<ResponseBody> observer) {
        Observable<ResponseBody> observable = retrofit.create(Api2.class).getUserInfo(url,userId,sessionId);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
   //用户上传头像
    public void getHeadPic(String url, File file, Observer<ResponseBody> observer) {
        RequestBody body = RequestBody.create(MediaType.parse("application/otect-stream"), file);
        MultipartBody.Part images = MultipartBody.Part.createFormData("image", file.getName(), body);

        Observable<ResponseBody> observable = retrofit.create(Api2.class).getHeadPic(url,userId,sessionId,images);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
     //签到
    public void toSignIn(String url, Observer<ResponseBody> observer) {
        Observable<ResponseBody> observable = retrofit.create(Api2.class).getUserInfo(url,userId,sessionId);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
     //购票下单
    public void toTicket(String url, int id, int count, String sign, Observer<ResponseBody> observer) {
        HashMap<String ,Object>  map=new HashMap<>();
        map.put("scheduleId",id);
        map.put("amount",count);
        map.put("sign",sign);
        Observable<ResponseBody> observable = retrofit.create(Api2.class).toTicket(url,userId,sessionId,map);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
    //去支付
    public void toPay(String url, int type, String orderId, Observer<ResponseBody> observer) {
        HashMap<String ,Object>  map=new HashMap<>();
        map.put("payType",type);
        map.put("orderId",orderId);
        Observable<ResponseBody> observable = retrofit.create(Api2.class).toPay(url,userId,sessionId,map);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
     //购票记录
    public void toRecordList(String url, int page, int count, int status, Observer<ResponseBody> observer) {
        Observable<ResponseBody> observable = retrofit.create(Api2.class).toRecode(url,userId,sessionId,page,count,status);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
      //关注的影片
    public void toMovie(String url, int page, int count, Observer<ResponseBody> observer) {
        Observable<ResponseBody> observable = retrofit.create(Api2.class).toPage(url,userId,sessionId,page,count);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
    //关注的影院
    public void toCinema(String url, int page, int count, Observer<ResponseBody> observer) {
        Observable<ResponseBody> observable = retrofit.create(Api2.class).toPage(url,userId,sessionId,page,count);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
   //意见反馈
    public void toFeedBack(String url, String content, Observer<ResponseBody> observer) {
        Observable<ResponseBody> observable = retrofit.create(Api2.class).toFeedBack(url,userId,sessionId,content);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
    //微信登录
    public void WXLogin(String url, String code, Observer<ResponseBody> observer) {
        HashMap<String,Object> map=new HashMap<>();
        map.put("code",code);
        Observable<ResponseBody> observable = retrofit.create(Api2.class).WXLogin(url,map);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
}
