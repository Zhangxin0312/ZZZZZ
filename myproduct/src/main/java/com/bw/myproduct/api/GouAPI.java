package com.bw.myproduct.api;


import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

public interface GouAPI {
    //查询购物车
     @GET
    Observable<ResponseBody>  doGouGet(@Url String url, @Header("userId") int userId, @Header("sessionId") String sessionId);
   //去结算
    @POST
   Observable<ResponseBody>  doSuan(@Url String url, @Header("userId") int userId, @Header("sessionId") String sessionId, @Query("orderInfo") String data,@Query("totalPrice") double sum,@Query("addressId")  int address);
   //添加收货地址
   @POST
    Observable<ResponseBody>  doAdd(@Url String url,@Header("userId") int userId, @Header("sessionId") String sessionId,@Query("realName") String name,@Query("phone") String phone,@Query("address") String address,@Query("zipCode")  String bian);
    @GET
    Observable<ResponseBody>   doQuan(@Url String url, @Header("userId") int userId, @Header("sessionId") String sessionId, @Query("page") int page, @Query("count") int count);
   //设置默认收货地址
    @POST
    Observable<ResponseBody>  doDefault(@Url String url, @Header("userId") int userId, @Header("sessionId") String session,@Query("id") int id);
    @PUT
    Observable<ResponseBody>  doUpdate(@Url String url,@Header("userId") int userId, @Header("sessionId") String sessionId,@Query("id") int id,@Query("realName") String name,@Query("phone") String phone,@Query("address") String address,@Query("zipCode")  String bian);
    @GET
    Observable<ResponseBody>  doOrder(@Url String url,@Header("userId") int userId, @Header("sessionId") String sessionId,@Query("status") int status,@Query("page") int page,@Query("count") int count);
    //删除订单
    @DELETE
    Observable<ResponseBody>  doCanCle(@Url String url, @Header("userId") int userId, @Header("sessionId") String sessionId,@Query("orderId") String id);
    //支付订单
    @POST
    Observable<ResponseBody>  doPay(@Url String url, @Header("userId") int userId, @Header("sessionId") String sessionId,@Query("orderId") String id,@Query("payType") int type);
    //删除订单
    @PUT
    Observable<ResponseBody>  doAffirm(@Url String url, @Header("userId") int userId, @Header("sessionId") String sessionId,@Query("orderId") String orderId);
   //修改昵称
    @PUT
    Observable<ResponseBody>  doUpName(@Url String url, @Header("userId") int userId, @Header("sessionId") String sessionId,@Query("nickName") String nickName);
    //修改密码
    @PUT
    Observable<ResponseBody>  doUpPwd(@Url String url, @Header("userId") int userId, @Header("sessionId") String sessionId, @Query("oldPwd") String oldName,@Query("newPwd") String newName);
    //上传头像
    @Multipart
    @POST
    Observable<ResponseBody> doImage(@Url String url, @Header("userId")  int userId, @Header("sessionId") String sessionId,  @Part MultipartBody.Part file);
    //评论
    @Multipart
    @POST
    Observable<ResponseBody> doReport(@Url String url,@Header("userId")  int userId,@Header("sessionId") String sessionId, @Query("commodityId")int commodityId,@Query("orderId") String orderId,@Query("content") String content,@Part MultipartBody.Part file);
    //同步到圈子
    @Multipart
    @POST
    Observable<ResponseBody> doSynch(@Url String url, @Header("userId")  int userId, @Header("sessionId") String sessionId, @Query("commodityId") int commodityId, @Query("content") String content, @Part MultipartBody.Part file);
     //点赞
    @POST
    Observable<ResponseBody> doGiveUp(@Url String url,@Header("userId")  int userId,@Header("sessionId") String sessionId,@Query("circleId") int circleId);
    //取消点赞
    @DELETE
    Observable<ResponseBody> doGiveOut(@Url String url,@Header("userId")  int userId,@Header("sessionId") String sessionId,@Query("circleId") int circleId);
}
