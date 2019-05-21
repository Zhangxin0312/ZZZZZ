package com.bw.myproduct.contract;

import com.bw.myproduct.bean.Banners;
import com.bw.myproduct.bean.LoginBean;
import com.bw.myproduct.bean.PingBean;
import com.bw.myproduct.bean.Pop;
import com.bw.myproduct.bean.Pop_2;
import com.bw.myproduct.bean.QuanBean;
import com.bw.myproduct.bean.ShouYe;
import com.bw.myproduct.bean.Sou;
import com.bw.myproduct.bean.XiangBean;
import com.bw.myproduct.model.MyModel;

import java.util.List;

public interface Contract {

    public  interface  MyPresenterInterface{
        //登录
        public  void  Login(String phone, String pwd);
        //注册
        public  void  Regist(String phone, String pwd);
        //Banner
        public  void  Ban();
        //首页
        public  void  ShouYe();
        //搜索
        public  void   Sou(String content, int page);
        //popupWindow一级列表
        public  void   Pop();
        //popupWindow二级列表
        public  void   Pop_2(String id);
        //详情
        public  void   Xiang(int id,int userId,String  sessionId);
        //评论
        public  void   Ping(int id,int page);
        //圈子
        public  void   Quan(int page,int userId,String sessionId);
        //加入购物车
        public   void   Add(int userId,String sessionId,String data );
    }
    public  interface  MyModelInterface{
        //登录
        public  void toLogin(String url, String phone, String pwd, MyModel.MyCallBack myCallBack);
        //注册
        public  void toRegist(String url, String phone, String pwd, MyModel.MyCallBack myCallBack);
        //Banner
        public  void  toBan(String url, MyModel.MyCallBack myCallBack);
        //首页
        public  void  toShouYe(String url, MyModel.ShowCallBack showCallBack);
        //搜索
        public  void   toSou(String url, String content, int page, MyModel.MyXiangBacks myXiangBacks);
        //popupWindow一级列表
        public  void   toPop(String url, MyModel.MyCallBacks myCallBacks);
        //popupWindow二级列表
        public  void   toPop_2(String url,String id ,MyModel.MyCallBacks myCallBacks);
        //详情
        public  void   toXiang(String url, int id,int userId,String  sessionId,MyModel.MyXiangBacks myXiangBacks);
        //评论
        public  void   toPing(String url,int id ,int page,MyModel.MyCallBacks myCallBacks);
        //圈子列表
        public  void   toQuan(String url,int userId ,String sessionId,int page,MyModel.MyCallBacks myCallBacks);
        //加入购物车
        public  void   toAdd(String url,int userId ,String sessionId,String data,MyModel.MyCallBack myCallBack);
    }
    public  interface  MyLoginInterface{
        //登录
        public  void      Logins(LoginBean loginBean);
    }
    public  interface  MyRegistInterface{
        //展示
        public  void     Regists(String str);
    }
    public  interface  MyShowInterface{
        //Banner
        public  void    MyBan(List<Banners.ResultBean>  list);
        //首页
        public  void   MyShouYe(ShouYe shou);
        //popupWindow一级列表
        public  void   MyPop(List<Pop.ResultBean>   list);
        //popupWindow二级列表
        public  void   MyPop2(List<Pop_2.ResultBean>   list);
    }
    public  interface  MySouInterface{
        //搜索成功
        public  void    MySous(List<Sou.ResultBean>  list);
        //搜索失败
        public  void    MySouError(String str);
    }
    public  interface  MyXiangInterface{
        //详情
        public  void    MyXiang(XiangBean xiangBean);
        //为空的时候
        public  void    MyXiangs(String str);
        //加入购物车
        public  void    MyAdd(String  str);
    }
    public  interface  MyPingInterface{
        //评论
        public  void    MyPing(List<PingBean.ResultBean>  list);
    }
    public  interface  MyQuanInterface{
        //圈子列表
        public  void    MyQuan(List<QuanBean.ResultBean>  list);
    }

}
