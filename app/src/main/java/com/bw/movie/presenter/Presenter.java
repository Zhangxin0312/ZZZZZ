package com.bw.movie.presenter;


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
import com.bw.movie.contract.ContractInter;
import com.bw.movie.model.Model;
import com.bw.movie.model.MyModel;

import java.io.File;
import java.util.HashMap;

public class Presenter<T>  implements ContractInter.PresenterInter {
    T views;
    ContractInter.ModelInter modelInter;
    MyModel myModel;

    public Presenter(T tt) {
        this.views = tt;
        modelInter = new Model();
        myModel=new MyModel();
    }

    //旋转木马
    @Override
    public void Ban(int page, int count) {
        modelInter.Bans("movieApi/movie/v1/findHotMovieList", page, count, new Model.MyCallBacks() {
            @Override
            public void success(Object object) {
                if (object instanceof ShouBean) {
                    ShouBean shouBean = (ShouBean) object;
                    ContractInter.ShouInter shouInter = (ContractInter.ShouInter) views;
                    shouInter.toBan(shouBean.getResult());
                }
            }
        });
    }
    //正在上映
    @Override
    public void Showing(int page, int count) {
        modelInter.Showings("movieApi/movie/v1/findReleaseMovieList", page, count, new Model.MyCallBacks() {
            @Override
            public void success(Object object) {
                if (object instanceof ShowIngBean) {
                    ShowIngBean showIngBean = (ShowIngBean) object;
                    ContractInter.ShouInter shouInter = (ContractInter.ShouInter) views;
                    shouInter.MyShowing(showIngBean.getResult());
                }
            }
        });
    }
     //即将上映
    @Override
    public void ShowOn(int page, int count) {
        modelInter.ShowOns("movieApi/movie/v1/findComingSoonMovieList", page, count, new Model.MyCallBacks() {
            @Override
            public void success(Object object) {
                if (object instanceof ShowOnBean) {
                    ShowOnBean showOnBean = (ShowOnBean) object;
                    ContractInter.ShouInter shouInter = (ContractInter.ShouInter) views;
                    shouInter.MyShowOn(showOnBean.getResult());
                }
            }
        });
    }
    //根据电影id查看信息
    @Override
    public void Xiang(int id) {
        modelInter.Xiangs("movieApi/movie/v1/findMoviesById", id, new Model.MyCallBacks() {
            @Override
            public void success(Object object) {
                if(object instanceof  XiangBean){
                    XiangBean xiangBean= (XiangBean) object;
                    ContractInter.MovieInter movieInter= (ContractInter.MovieInter) views;
                    movieInter.MyXiang(xiangBean);
                }
            }
        });
    }
     //查看电影详情
    @Override
    public void Moving(int id) {
        modelInter.Movings("movieApi/movie/v1/findMoviesDetail", id, new Model.MyCallBacks() {
            @Override
            public void success(Object object) {
                if(object instanceof MovingBean){
                   MovingBean movingBean= (MovingBean) object;
                    ContractInter.MovieXiangInter movieInter= (ContractInter.MovieXiangInter) views;
                    movieInter.MyMoving(movingBean);
               }
            }
        });
    }
      //评论列表
    @Override
    public void Assess(int id) {
        modelInter.toAssess("movieApi/movie/v1/findAllMovieComment", id,1,10, new Model.MyCallBacks() {
            @Override
            public void success(Object object) {
                if(object instanceof AssessBean){
                    AssessBean assessBean= (AssessBean) object;
                    ContractInter.MovieInter movieInter= (ContractInter.MovieInter) views;
                    movieInter.MyAssess(assessBean.getResult());
                }
            }
        });
    }
    //添加对影片的评论
    @Override
    public void AddPing(HashMap<String,Object> map) {
      modelInter.toAddPing("movieApi/movie/v1/verify/movieComment",map, new Model.MyCallBacks() {
          @Override
          public void success(Object object) {
                if(object instanceof  String){
                    String str= (String) object;
                    ContractInter.MovieInter movieInter= (ContractInter.MovieInter) views;
                    movieInter.MyAddPing(str);
                }
          }
      });
    }
    //添加用户对评论的回复
    @Override
    public void AddReply(HashMap<String, Object> map) {
        modelInter.toAddReply("movieApi/movie/v1/verify/commentReply",map, new Model.MyCallBacks() {
            @Override
            public void success(Object object) {
                if(object instanceof  String){
                    String str= (String) object;
                    ContractInter.MovieInter movieInter= (ContractInter.MovieInter) views;
                    movieInter.MyAddReply(str);
                }
            }
        });
    }
    //关注
    @Override
    public void Attention(int id) {
        modelInter.toAttention("movieApi/movie/v1/verify/followMovie",id, new Model.MyCallBacks() {
            @Override
            public void success(Object object) {
                if(object instanceof  String){
                    String str= (String) object;
                    ContractInter.XingInter   xingInter= (ContractInter.XingInter) views;
                    xingInter.MyAttention(str);
                }
            }
        });
    }
     //取消关注成功
    @Override
    public void Cancles(int id) {
        modelInter.toCancle("movieApi/movie/v1/verify/cancelFollowMovie",id, new Model.MyCallBacks() {
            @Override
            public void success(Object object) {
                if(object instanceof  String){
                    String str= (String) object;
                    ContractInter.XingInter   xingInter= (ContractInter.XingInter) views;
                    xingInter.MyCancle(str);
                }
            }
        });
    }
   //点赞
    @Override
    public void Dian(int id) {
        modelInter.toDian("movieApi/movie/v1/verify/movieCommentGreat",id, new Model.MyCallBacks() {
            @Override
            public void success(Object object) {
                if(object instanceof  String){
                    String str= (String) object;
                    ContractInter.MovieInter movieInter= (ContractInter.MovieInter) views;
                    movieInter.MyDian(str);
                }
            }
        });
    }
    //根据电影ID查询当前排片该电影的影院列表
    @Override
    public void FindMovie(int id) {
        modelInter.toFindMovie("movieApi/movie/v1/findCinemasListByMovieId",id, new Model.MyCallBacks() {
            @Override
            public void success(Object object) {
                if(object instanceof FindBean){
                    FindBean findBean = (FindBean) object;
                    ContractInter.BuyInter buyInter= (ContractInter.BuyInter) views;
                    buyInter.MyFind(findBean.getResult());
                }
            }
        });
    }
    //关注影院
    @Override
    public void FllowCinema(int id) {
        modelInter.toFllowCinema("movieApi/cinema/v1/verify/followCinema",id, new Model.MyCallBacks() {
            @Override
            public void success(Object object) {
                if(object instanceof String){
                    String str= (String) object;
                    ContractInter.BuyInter buyInter= (ContractInter.BuyInter) views;
                    buyInter.MyFllowCinema(str);
                }
            }
        });
    }
    //取消关注影院
    @Override
    public void CancleFllowCinema(int id) {
        modelInter.toCancleFllowCinema("movieApi/cinema/v1/verify/cancelFollowCinema",id, new Model.MyCallBacks() {
            @Override
            public void success(Object object) {
                if(object instanceof String){
                    String str= (String) object;
                    ContractInter.BuyInter buyInter= (ContractInter.BuyInter) views;
                    buyInter.MyCancleFllowCinema(str);
                }
            }
        });
    }
   //根据电影ID和影院ID查询电影排期列表
    @Override
    public void MovieScheduleList(int movieId, int cinemasId) {
        modelInter.toMovieScheduleList("movieApi/movie/v1/findMovieScheduleList",movieId,cinemasId, new Model.MyCallBacks() {
            @Override
            public void success(Object object) {
                if(object instanceof MovieScheduleListBean){
                    MovieScheduleListBean bean= (MovieScheduleListBean) object;
                    ContractInter.MovieListInter movieListInter= (ContractInter.MovieListInter) views;
                    movieListInter.MyMovieScheduleList(bean.getResult());
                }
            }
        });
    }
     //根据用户ID查询用户信息
    @Override
    public void getUserInfo() {
        modelInter.getUserInfo("movieApi/user/v1/verify/getUserInfoByUserId", new Model.MyCallBacks() {
            @Override
            public void success(Object object) {
                if(object instanceof UserInfoBean){
                    UserInfoBean bean= (UserInfoBean) object;
                    ContractInter.UserInfoInter userInfoInter= (ContractInter.UserInfoInter) views;
                    userInfoInter.MyUser(bean);
                }
            }
        });
    }
    //上传用户头像
    @Override
    public void getHeadPic(File file) {
        modelInter.getHeadPic("movieApi/user/v1/verify/uploadHeadPic", file,new Model.MyCallBacks() {
            @Override
            public void success(Object object) {
                if(object instanceof HeadPicBean){
                    HeadPicBean bean= (HeadPicBean) object;
                    ContractInter.UserInfoInter userInfoInter= (ContractInter.UserInfoInter) views;
                    userInfoInter.MyHeadPic(bean);
                }
            }
        });
    }
     //签到
    @Override
    public void toSignIn() {
        modelInter.toSignIn("movieApi/user/v1/verify/userSignIn",new Model.MyCallBacks() {
            @Override
            public void success(Object object) {
                if(object instanceof String){
                    String str= (String) object;
                    ContractInter.MyInter myInter= (ContractInter.MyInter) views;
                    myInter.MySign(str);
                }
            }
        });
    }
    //购票下单
    @Override
    public void buyTicket(int id, int count, String sign) {
        modelInter.buyTicket("movieApi/movie/v1/verify/buyMovieTicket",id,count,sign,new Model.MyCallBacks() {
            @Override
            public void success(Object object) {
                if(object instanceof TicketBean){
                    TicketBean ticketBean= (TicketBean) object;
                    ContractInter.MyTicketInter myTicketInter= (ContractInter.MyTicketInter) views;
                    myTicketInter.MyTicket(ticketBean);
                }
            }
        });
    }
    //微信支付
    @Override
    public void toWX(int type, String orderId) {
        modelInter.toWX("movieApi/movie/v1/verify/pay",type,orderId,new Model.MyCallBacks() {
            @Override
            public void success(Object object) {
                if(object instanceof WeixinBean){
                    WeixinBean weixinBean= (WeixinBean) object;
                    ContractInter.MyTicketInter myTicketInter= (ContractInter.MyTicketInter) views;
                    myTicketInter.MyWX(weixinBean);
                }
            }
        });
    }
    //去支付宝支付
    @Override
    public void   toZFB(int type, String orderId) {
        modelInter.toZFB("movieApi/movie/v1/verify/pay",type,orderId,new Model.MyCallBacks() {
            @Override
            public void success(Object object) {
                if(object instanceof ZhifubaoBean){
                    ZhifubaoBean zhifubaoBean= (ZhifubaoBean) object;
                    ContractInter.MyTicketInter myTicketInter= (ContractInter.MyTicketInter) views;
                    myTicketInter.MyZFB(zhifubaoBean);
                }
            }
        });
    }
      //用户购票记录查询列表
    @Override
    public void RecordList(int status) {
        modelInter.RecordList("movieApi/user/v1/verify/findUserBuyTicketRecordList",1,5,status,new Model.MyCallBacks() {
            @Override
            public void success(Object object) {
                if(object instanceof RecodeBean){
                    RecodeBean recodeBean= (RecodeBean) object;
                    ContractInter.RecordInter recordInter= (ContractInter.RecordInter) views;
                    recordInter.MyRecord(recodeBean.getResult());
               }
            }
        });
    }
    //查询用户关注的影片列表
    @Override
    public void MoviePage(final int page, int count) {
        modelInter.MoviePage("movieApi/movie/v1/verify/findMoviePageList",page,count,new Model.MyCallBacks() {
            @Override
            public void success(Object object) {
                if(object instanceof MoviePageBean){
                    MoviePageBean moviePageBean= (MoviePageBean) object;
                    ContractInter.PageInter pageInter= (ContractInter.PageInter) views;
                    pageInter.MyMoviePage(moviePageBean.getResult());
                }
            }
        });
    }
     //查询用户关注的影院列表
    @Override
    public void CinemaPage(final int page, int count) {
        modelInter.CinemaPage("movieApi/cinema/v1/verify/findCinemaPageList",page,count,new Model.MyCallBacks() {
            @Override
            public void success(Object object) {
                if(object instanceof CinemaPageBean){
                    CinemaPageBean cinemaPageBean= (CinemaPageBean) object;
                    ContractInter.PageInter pageInter= (ContractInter.PageInter) views;
                    pageInter.MyCinemaList(cinemaPageBean.getResult());
                }
            }
        });
    }
    //意见反馈
    @Override
    public void recordFeedBack(String content) {
        modelInter.recordFeedBack("movieApi/tool/v1/verify/recordFeedBack",content,new Model.MyCallBacks() {
            @Override
            public void success(Object object) {
                if(object instanceof String){
                    String str= (String) object;
                    ContractInter.FeedBackInter feedBackInter= (ContractInter.FeedBackInter) views;
                    feedBackInter.MyFeedBack(str);
                }
            }
        });
    }
     //微信支付
    @Override
    public void WXLogin(String code) {
        modelInter.WXLogin("movieApi/user/v1/weChatBindingLogin",code,new Model.MyCallBacks() {
            @Override
            public void success(Object object) {
                if(object instanceof WXBean){
                    WXBean wxBean= (WXBean) object;
                    ContractInter.WXInter wxInter= (ContractInter.WXInter) views;
                    wxInter.MyWX(wxBean);
                }
            }
        });
    }

   /* @Override
    public void toXiTong() {
        myModel.setMyCallBack13(new MyModel.MyCallBack13() {
            @Override
            public void success(Object o) {
                ContractInter.MyInter myInter= (ContractInter.MyInter) views;
                myInter.MyXiTong(o);
            }
        });
        myModel.getXiTong();
    }*/
}
