package com.bw.movie.app;

import com.bw.movie.bean.LoginBean;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * @Author：dell
 * @E-mail： 15001194794@163.com
 * @Date：2019/5/11 10:24
 * @Description：描述信息
 */
public interface Api {
    @FormUrlEncoded
    @POST
    public Observable<ResponseBody> post(@Url String url, @FieldMap HashMap<String,String> map);

    @FormUrlEncoded
    @POST
    public Observable<ResponseBody> rpost(@Url String url, @Field("nickName")String nickName,@Field("phone")String phone,@Field("pwd")String pwd,@Field("pwd2")String pwd2,@Field("sex")int sex,@Field("birthday")String birthday,@Field("email")String email);

}
