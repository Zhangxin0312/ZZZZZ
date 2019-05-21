package com.bw.myproduct.model;

import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.bw.myproduct.bean.Banners;
import com.bw.myproduct.bean.LoginBean;
import com.bw.myproduct.bean.PingBean;
import com.bw.myproduct.bean.Pop;
import com.bw.myproduct.bean.Pop_2;
import com.bw.myproduct.bean.QuanBean;
import com.bw.myproduct.bean.ShouYe;
import com.bw.myproduct.bean.Sou;
import com.bw.myproduct.bean.XiangBean;
import com.bw.myproduct.contract.Contract;
import com.bw.myproduct.utils.OkHttpUtils;
import com.google.gson.Gson;

import org.json.JSONObject;

public class MyModel  implements Contract.MyModelInterface {
    MyCallBack myCallBack;
    ShowCallBack showCallBack;
    MyCallBacks myCallBacks;
    MyXiangBacks myXiangBacks;
    public Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String json= (String) msg.obj;
            int type=msg.arg1;
            Gson gson=new Gson();
            switch (type){
                case 1:
                    try {
                        JSONObject object=new JSONObject(json);
                        String message = object.getString("message");
                        myCallBack.success(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    LoginBean loginBean = gson.fromJson(json, LoginBean.class);
                    myCallBack.success(loginBean);
                    break;
                case 3:
                    Banners banners = gson.fromJson(json, Banners.class);
                    myCallBack.success(banners);
                    break;
                case 4:
                    ShouYe shouYe = gson.fromJson(json, ShouYe.class);
                    showCallBack.success(shouYe);
                    break;
                case 5:
                    Sou sou=gson.fromJson(json,Sou.class);
                    if(sou.getResult()!=null && sou.getResult().size()!=0&& sou!=null){
                         myXiangBacks.success(sou);
                    }else
                    {
                        myXiangBacks.error("数据为空");
                    }
                    break;
                case 6:
                    Pop pop=gson.fromJson(json,Pop.class);
                    myCallBacks.success(pop);
                    break;
                case 7:
                    Pop_2 pop_2 = gson.fromJson(json, Pop_2.class);
                    myCallBacks.success(pop_2);
                    break;
                case 8:
                    XiangBean xiangBean = gson.fromJson(json, XiangBean.class);
                   if(xiangBean.getResult()!=null&&xiangBean!=null){
                       myXiangBacks.success(xiangBean);
                   }else {
                       myXiangBacks.error("数据为空");
                   }

                    break;
                case 9:
                    PingBean pingBean = gson.fromJson(json, PingBean.class);
                    myCallBacks.success(pingBean);
                    break;
                case 10:
                    QuanBean quanBean = gson.fromJson(json, QuanBean.class);
                    myCallBacks.success(quanBean);
                    break;
                case 11:
                    try {
                        JSONObject object=new JSONObject(json);
                        String message = object.getString("message");
                        myCallBack.success(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };
    //登录
    @Override
    public void toLogin(String url, String phone, String pwd, MyModel.MyCallBack myCallBack) {
        this.myCallBack=myCallBack;
        OkHttpUtils utils = OkHttpUtils.getInstance();
        utils.doLoginPost(url,phone,pwd,handler,myCallBack);
    }
    //注册
    @Override
    public void toRegist(String url, String phone, String pwd, MyModel.MyCallBack myCallBack) {
        this.myCallBack=myCallBack;
        OkHttpUtils utils = OkHttpUtils.getInstance();
        utils.doPost(url,phone,pwd,handler,myCallBack);
    }
      //Banner
    @Override
    public void toBan(String url, MyCallBack myCallBack) {
        this.myCallBack=myCallBack;
        OkHttpUtils utils = OkHttpUtils.getInstance();
        utils.doShowGet(url,handler,myCallBack);
    }
      //首页
    @Override
    public void toShouYe(String url,ShowCallBack showCallBack) {
        this.showCallBack=showCallBack;
        OkHttpUtils utils = OkHttpUtils.getInstance();
        utils.doShowGet2(url,handler,showCallBack);
    }
   //搜索
    @Override
    public void toSou(String url, String content, int page, MyXiangBacks myXiangBacks) {
        this.myXiangBacks=myXiangBacks;
        OkHttpUtils utils = OkHttpUtils.getInstance();
        utils.doSouGet(url,content,page,handler);
    }
    //popwindow一级列表
    @Override
    public void toPop(String url, MyCallBacks myCallBacks) {
        this.myCallBacks=myCallBacks;
        OkHttpUtils utils = OkHttpUtils.getInstance();
        utils.doPopGet(url,handler);
    }
    //popwindow二级列表
    @Override
    public void toPop_2(String url, String id, MyCallBacks myCallBacks) {
        this.myCallBacks=myCallBacks;
        OkHttpUtils utils = OkHttpUtils.getInstance();
        utils.doPopGet2(url,id,handler);
    }
     //详情
    @Override
    public void toXiang(String url, int id,int userId,String  sessionId, MyXiangBacks myXiangBacks) {
        this.myXiangBacks=myXiangBacks;
        OkHttpUtils utils = OkHttpUtils.getInstance();
        utils.doXiangGet(url,id,userId,sessionId,handler);
    }
      //评论
    @Override
    public void toPing(String url, int id, int page, MyCallBacks myCallBacks) {
        this.myCallBacks=myCallBacks;
        OkHttpUtils utils = OkHttpUtils.getInstance();
        utils.doPingGet(url,id,page,handler);
    }
   //圈子列表
    @Override
    public void toQuan(String url, int userId, String sessionId,int page,  MyCallBacks myCallBacks) {
        this.myCallBacks=myCallBacks;
        OkHttpUtils utils = OkHttpUtils.getInstance();
        utils.doQuanGet(url,userId,sessionId,page,handler);
    }
    //加入购物车
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void toAdd(String url, int userId, String sessionId, String data, MyCallBack myCallBack) {
        this.myCallBack=myCallBack;
        OkHttpUtils utils = OkHttpUtils.getInstance();
        utils.doAddGet(url,userId,sessionId,data,handler);
    }

    public  interface  MyCallBack{
        public  void success(Object object);
    }
    public  interface  ShowCallBack{
        public  void success(Object object);
    }
    public  interface  MyCallBacks{
        public  void success(Object object);
    }
    public  interface  MyXiangBacks{
        public  void success(Object object);
        public  void error(String str);
    }
}
