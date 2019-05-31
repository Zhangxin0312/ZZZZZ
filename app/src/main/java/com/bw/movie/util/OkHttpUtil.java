package com.bw.movie.util;

import com.bw.movie.app.Api;
import com.bw.movie.bean.GouBean;
import com.bw.movie.bean.GuanZhuBean;
import com.bw.movie.bean.HeartBean;
import com.bw.movie.bean.MiMaBean;
import com.bw.movie.bean.NearCinemaBean;
import com.bw.movie.bean.PingLunBean;
import com.bw.movie.bean.QuHeartBean;
import com.bw.movie.bean.RecommendBean;
import com.bw.movie.bean.XiTongBean;
import com.bw.movie.bean.XiaDanBean;
import com.bw.movie.bean.XiangQingBean;
import com.bw.movie.bean.ZanBean;
import com.bw.movie.bean.ZhengMovieBean;
import com.bw.movie.bean.ZhiFuBean;

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
    //请求推荐影院
    public void doGet(String baseurl,String url, Observer<RecommendBean>observer){
        Api api=retrofit.create(Api.class);
        Observable<RecommendBean> observable = api.get(url);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    //请求小心心关注
    public void getHeart(String url, int userid, String sessionid, Observer<HeartBean>observer){
        Api api=retrofit.create(Api.class);
        Observable<HeartBean> observable = api.getHeart(url, userid, sessionid);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
    //取消关注小心心
    public void getQuHeart(String url, int userid, String sessionid, Observer<QuHeartBean>observer){
        Api api=retrofit.create(Api.class);
        Observable<QuHeartBean> observable = api.getQuHeart(url, userid, sessionid);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
    //附近影院
    public void getNearCinema(String baseurl,String url,int page,int count,String latitude,String longitude,Observer<NearCinemaBean> observer1){
        Api api=retrofit.create(Api.class);
        Observable<NearCinemaBean> observable = api.getNear(url,page,count,longitude,latitude);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer1);
    }

    //推荐影院的详情
    public void getDetils(String url, int userid, String sessionid, Observer<RecommendBean>observer){
        Api api=retrofit.create(Api.class);
        Observable<RecommendBean> observable = api.getDetails(url, userid, sessionid);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
    //正在放映的电影
    public void getZheng(String url,HashMap<String,Integer>map, Observer<ZhengMovieBean>observer){
        /*retrofit2=new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(UrlAll.Url_All)
                .build();*/
        Api api=retrofit.create(Api.class);
        Observable<ZhengMovieBean> observable = api.getZhengMovie(url, map);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
    //电影的详情
    public void getXaingQing(String url, int userId, String sessionId, Observer<XiangQingBean> observer){
        Api api=retrofit.create(Api.class);
        Observable<XiangQingBean> observable = api.getXiangQing(url, userId, sessionId);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
    //电影的评论
    public void getPingLun(String url,Observer<PingLunBean> observer){
        Api api=retrofit.create(Api.class);
        Observable<PingLunBean> observable = api.getPingLun(url);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    //电影购票
    public void getGou(String url, Observer<GouBean> observer){
        Api api=retrofit.create(Api.class);
        Observable<GouBean> observable = api.getGou(url);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    //关注的影院
    public void getGuan(String url, int userId, String sessionId, Observer<GuanZhuBean>observer){
        Api api=retrofit.create(Api.class);
        Observable<GuanZhuBean> observable = api.getGuan(url, userId, sessionId);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    //电影的点赞
    public void getZan(String url, int userId, String sessionId,int commentId, Observer<ZanBean>observer){
        Api api=retrofit.create(Api.class);
        Observable<ZanBean> observable = api.postZan(url, userId, sessionId,commentId);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
    //系统消息列表
    public void getXiTong(String baseurl,String url, int userId, String sessionId, Observer<XiTongBean>observer){
        Api api=retrofit.create(Api.class);
        Observable<XiTongBean> observable = api.getXiTong(url, userId, sessionId);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    //重置密码
    public void getMiMa(String url, int userId, String sessionId, String oldPwd, String newPwd, String newPwd2, Observer<MiMaBean>observer){
        Api api=retrofit.create(Api.class);
        Observable<MiMaBean> observable = api.postMiMa(url, userId, sessionId, oldPwd, newPwd, newPwd2);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
    //购票下单
    public void postGouPiao(String url, int userId, String sessionId, int scheduleId, int amount, String sign, Observer<XiaDanBean> observer){
        Api api=retrofit.create(Api.class);
        Observable<XiaDanBean> observable = api.postXiaDan(url, userId, sessionId, scheduleId, amount, sign);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
    //模糊查询
    public void getSearch(String url,int page,int count,String cinemaName,Observer<ResponseBody>observer){
        Api api=retrofit.create(Api.class);
        Observable<ResponseBody> observable = api.getSearch(url, page, count, cinemaName);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
    //支付
    public void getZhiFu(String url, int userId, String sessionId, int payType, String orderId, Observer<ZhiFuBean> observer){
        Api api=retrofit.create(Api.class);
        Observable<ZhiFuBean> observable = api.postZhi(url, userId, sessionId, payType, orderId);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
}
