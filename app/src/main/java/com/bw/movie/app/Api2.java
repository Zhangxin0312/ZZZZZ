package com.bw.movie.app;

import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

public interface Api2 {

    @GET
    Observable<ResponseBody>  Bans(@Url String url, @Header("userId") int userId,@Header("sessionId")  String sessionId,@Query("page") int page,@Query("count")  int count);
    @GET
    Observable<ResponseBody>  Xiang(@Url String url, @Header("userId") int userId,@Header("sessionId")  String sessionId,@Query("movieId")  int movieId);
    @GET
    Observable<ResponseBody>  Assess(@Url String url, @Header("userId") int userId,@Header("sessionId")  String sessionId,@Query("movieId")  int movieId,@Query("page") int page,@Query("count")  int count);

    @FormUrlEncoded
    @POST
    Observable<ResponseBody>  AddPing(@Url String url, @Header("userId") int userId, @Header("sessionId")  String sessionId, @FieldMap HashMap<String,Object> map);

    @FormUrlEncoded
    @POST
    Observable<ResponseBody>  Dian(@Url String url, @Header("userId") int userId,@Header("sessionId")  String sessionId,@FieldMap HashMap<String,Object> map);

    @GET
    Observable<ResponseBody> Find(@Url String url,@Query("movieId") int id);
    @GET
    Observable<ResponseBody> Fllow(@Url  String url,@Header("userId") int userId, @Header("sessionId")  String sessionId,@Query("cinemaId") int id);
    @GET
    Observable<ResponseBody> MovieScheduleList(@Url String url,@Header("userId") int userId, @Header("sessionId")  String sessionId,@Query("movieId")  int movieId, @Query("cinemasId") int cinemasId);
    @GET
    Observable<ResponseBody> getUserInfo(@Url String url, @Header("userId") int userId,@Header("sessionId")  String sessionId);
    @Multipart
    @POST
    Observable<ResponseBody> getHeadPic(@Url String url,@Header("userId") int userId,@Header("sessionId") String sessionId,@Part MultipartBody.Part file);
    @FormUrlEncoded
    @POST
    Observable<ResponseBody> toTicket(@Url  String url,@Header("userId") int userId, @Header("sessionId")  String sessionId,@FieldMap  HashMap<String ,Object> map);
    @FormUrlEncoded
     @POST
    Observable<ResponseBody> toPay(@Url String url,@Header("userId") int userId,@Header("sessionId") String sessionId,@FieldMap  HashMap<String ,Object> map);
    @GET
    Observable<ResponseBody> toRecode(@Url  String url,@Header("userId") int userId,@Header("sessionId") String sessionId, @Query("page") int page,  @Query("count")int count,  @Query("status")int status);
    @GET
    Observable<ResponseBody> toPage(@Url  String url,@Header("userId")  int userId,@Header("sessionId") String sessionId, @Query("page")  int page, @Query("count") int count);
    @POST
    Observable<ResponseBody> toFeedBack(@Url  String url,@Header("userId") int userId,@Header("sessionId") String sessionId,@Query("content") String content);
    @FormUrlEncoded
    @POST
    Observable<ResponseBody> WXLogin(@Url String url,@FieldMap  HashMap<String,Object> map);
}
