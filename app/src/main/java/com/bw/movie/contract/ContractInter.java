package com.bw.movie.contract;

import com.bw.movie.bean.TicketBean;
import com.bw.movie.bean.AssessBean;
import com.bw.movie.bean.CinemaPageBean;
import com.bw.movie.bean.FindBean;
import com.bw.movie.bean.HeadPicBean;
import com.bw.movie.bean.MovieScheduleListBean;
import com.bw.movie.bean.MovingBean;
import com.bw.movie.bean.MoviePageBean;
import com.bw.movie.bean.RecodeBean;
import com.bw.movie.bean.ShouBean;
import com.bw.movie.bean.ShowIngBean;
import com.bw.movie.bean.ShowOnBean;
import com.bw.movie.bean.UserInfoBean;
import com.bw.movie.bean.WXBean;
import com.bw.movie.bean.WeixinBean;
import com.bw.movie.bean.XiangBean;
import com.bw.movie.bean.ZhifubaoBean;
import com.bw.movie.model.Model;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public interface ContractInter {


    public interface PresenterInter {
        //旋转木马效果
        public  void Ban(int page,int count);
        //正在上映
        public  void Showing(int page,int count);
        //即将上映
        public  void ShowOn(int page,int count);
        //根据电影ID查询电影信息
        public  void Xiang(int id);
        //查看电影详情
        public  void Moving(int id);
        //查看电影评论
        public  void Assess(int id);
        //添加用户对影片的评论
        public  void AddPing(HashMap<String,Object> map);
        //添加用户对评论的回复
        public  void AddReply(HashMap<String,Object> map);
        //关注影片
        public  void Attention(int id);
        //取消关注影片
        public  void Cancles(int id);
        //点赞
        public  void Dian(int id);
        //根据电影ID查询当前排片该电影的影院列表
        public  void FindMovie(int id);
        //关注影院
        public  void FllowCinema(int id);
        //取消关注影院
        public  void CancleFllowCinema(int id);
        //根据电影ID和影院ID查询电影排期列表
        public  void MovieScheduleList(int movieId,int cinemasId);
        //根据用户ID查询用户信息
        public  void getUserInfo();
        //上传头像
        public  void getHeadPic(File file);
        //签到
        public  void toSignIn();
        //购票下单
        public  void  buyTicket(int id,int count,String sign);
        //微信支付
        public  void toWX(int type,String orderId);
        //支付宝支付
        public  void toZFB(int type,String orderId);
        //用户购票记录查询列表
        public  void  RecordList(int status);
        //查询用户关注的影片列表
        public  void  MoviePage(int page,int count);
        //查询用户关注的影院列表
        public  void  CinemaPage(int page,int count);
        //意见反馈
        public  void  recordFeedBack(String content);
        //微信登录
        public  void  WXLogin(String code);
        //系统消息
       // public void toXiTong();
    }
    public interface ModelInter {
        //旋转木马效果
        public  void  Bans(String url, int page, int count, Model.MyCallBacks  myCallBacks);
        //正在上映
        public  void  Showings(String url, int page, int count, Model.MyCallBacks  myCallBacks);
        //即将上映
        public  void  ShowOns(String url, int page, int count, Model.MyCallBacks  myCallBacks);
        //根据电影ID查询电影信息
        public  void  Xiangs(String url,int id,Model.MyCallBacks  myCallBacks);
        //查询电影详情
        public  void  Movings(String url,int id,Model.MyCallBacks  myCallBacks);
        //查看电影评论
        public  void  toAssess(String url,int id,int page,int count,Model.MyCallBacks  myCallBacks);
        //添加用户对影片的评论
        public  void  toAddPing(String url, HashMap<String,Object> map, Model.MyCallBacks  myCallBacks);
        //添加用户对评论的回复
        public  void  toAddReply(String url, HashMap<String,Object> map, Model.MyCallBacks  myCallBacks);
        //关注
        public  void  toAttention(String url,int id,Model.MyCallBacks  myCallBacks);
        //取消关注
        public  void  toCancle(String url,int id,Model.MyCallBacks  myCallBacks);
        //点赞
        public  void  toDian(String url,int id,Model.MyCallBacks  myCallBacks);
        //根据电影ID查询当前排片该电影的影院列表
        public  void  toFindMovie(String url,int id,Model.MyCallBacks  myCallBacks);
        //关注影院
        public  void  toFllowCinema(String url,int id,Model.MyCallBacks  myCallBacks);
        //取消关注影院
        public  void  toCancleFllowCinema(String url,int id,Model.MyCallBacks  myCallBacks);
        //根据电影ID和影院ID查询电影排期列表
        public  void  toMovieScheduleList(String url,int movieId,int cinemasId,Model.MyCallBacks  myCallBacks);
        //根据用户ID查询用户信息
        public  void getUserInfo(String url, Model.MyCallBacks myCallBacks);
        //上传头像
        public  void getHeadPic(String url,File file,Model.MyCallBacks myCallBacks);
        //签到
        public  void toSignIn(String url,Model.MyCallBacks myCallBacks);
        //购票下单
        public  void  buyTicket(String url,int id,int count,String sign,Model.MyCallBacks myCallBacks);
         //微信支付
         public  void toWX(String url, int type, String orderId, Model.MyCallBacks myCallBacks);
        //支付宝支付
        public  void toZFB(String url, int type, String orderId, Model.MyCallBacks myCallBacks);
        //用户购票记录查询列表
        public  void  RecordList(String url,int page,int count,int status,Model.MyCallBacks myCallBacks);
        //查询用户关注的影片列表
        public  void  MoviePage(String url, int page,int count,Model.MyCallBacks myCallBacks);
        //查询用户关注的影院列表
        public  void  CinemaPage(String url, int page,int count,Model.MyCallBacks myCallBacks);
        //意见反馈
        public  void  recordFeedBack(String url,String content,Model.MyCallBacks myCallBacks);
        //微信登录
        public  void  WXLogin(String url,String code,Model.MyCallBacks myCallBacks);
    }
    public interface ShouInter {
        //旋转木马效果
        public  void toBan(List<ShouBean.ResultBean> list);
        //正在上映
        public  void MyShowing(List<ShowIngBean.ResultBean>  list);
        //即将上映
        public  void MyShowOn(List<ShowOnBean.ResultBean>  list);
    }
    public interface MovieInter {
        //根据电影ID查询电影信息
        public void   MyXiang(XiangBean xiangBean);
        //查看电影评论
        public void   MyAssess(List<AssessBean.ResultBean>  list);
        //添加用户对影片的评论
        public void   MyAddPing(String str);
        //添加用户对评论的回复
        public void   MyAddReply(String str);
        //点赞
        public void   MyDian(String str);
    }
    public  interface  MovieXiangInter{
        //查询电影详情
        public void   MyMoving(MovingBean movingBean);
    }
    public  interface XingInter{
        //关注
        public void   MyAttention(String str);
        //取消关注
        public void   MyCancle(String str);
    }
    public  interface BuyInter{
        //根据电影ID查询当前排片该电影的影院列表
        public void   MyFind(List<FindBean.ResultBean>  list);
        //关注影院
        public void   MyFllowCinema(String str);
        //取消关注影院
        public void   MyCancleFllowCinema(String str);
    }
    public  interface MovieListInter{
        //根据电影ID和影院ID查询电影排期列表
        public void   MyMovieScheduleList(List<MovieScheduleListBean.ResultBean>  list);
    }
    public  interface UserInfoInter{
        //根据用户ID查询用户信息
        public void   MyUser(UserInfoBean userInfoBean);
        //上传头像
        public void   MyHeadPic(HeadPicBean headPicBean);
    }
    public  interface MyInter{
        //签到
        public void   MySign(String str);
        //系统消息
      //  public void MyXiTong(Object o);
    }
    public  interface MyTicketInter{
        //购票下单
        public void   MyTicket(TicketBean ticketBean);
        //微信支付
        public void   MyWX(WeixinBean weixinBean);
        //支付宝支付
        public void   MyZFB(ZhifubaoBean zhifubaoBean);

    }
    //用户购票记录查询列表
    public  interface RecordInter{
        public void   MyRecord(List<RecodeBean.ResultBean>  list);
    }

    public  interface PageInter{
        //查询用户关注的影片列表
        public void   MyMoviePage(List<MoviePageBean.ResultBean>  list);
        //查询用户关注的影院列表
        public void   MyCinemaList(List<CinemaPageBean.ResultBean>  list);
    }
    //意见反馈
    public  interface FeedBackInter{
        public void   MyFeedBack(String str);
    }
    //微信登录
    public  interface WXInter{
        public void   MyWX(WXBean wxBean);
    }
}
