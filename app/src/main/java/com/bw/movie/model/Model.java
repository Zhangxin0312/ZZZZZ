package com.bw.movie.model;

import android.util.Log;

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
import com.bw.movie.util.HttpUtils;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;

import okhttp3.ResponseBody;
import rx.Observer;

public class Model  implements ContractInter.ModelInter {
    MyCallBacks myCallBacks;
    //旋转木马效果  热门电影
    @Override
    public void Bans(String url, int page, int count, final MyCallBacks myCallBacks) {
        this.myCallBacks=myCallBacks;
        HttpUtils httpUtils = HttpUtils.getInstance();
        httpUtils.doBanGet(url,page,count, new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }
            @Override
            public void onNext(ResponseBody body) {
                try {
                    String json = body.string();
                    Gson gson=new Gson();
                    ShouBean shouBean = gson.fromJson(json, ShouBean.class);
                    myCallBacks.success(shouBean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //正在上映
    @Override
    public void Showings(String url, int page, int count, final MyCallBacks myCallBacks) {
        this.myCallBacks=myCallBacks;
        HttpUtils httpUtils = HttpUtils.getInstance();
        httpUtils.doShowingGet(url,page,count, new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(ResponseBody body) {
                try {
                    String json = body.string();
                    Gson gson=new Gson();
                    ShowIngBean showIngBean = gson.fromJson(json, ShowIngBean.class);
                    myCallBacks.success(showIngBean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
     //即将上映
    @Override
    public void ShowOns(String url, int page, int count, final MyCallBacks myCallBacks) {
        this.myCallBacks=myCallBacks;
        HttpUtils httpUtils = HttpUtils.getInstance();
        httpUtils.doShowOnGet(url,page,count, new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseBody body) {
                try {
                    String json = body.string();
                    Gson gson=new Gson();
                    ShowOnBean showOnBean = gson.fromJson(json, ShowOnBean.class);
                    myCallBacks.success(showOnBean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //根据id查看电影信息
    @Override
    public void Xiangs(String url, int id, final MyCallBacks myCallBacks) {
        this.myCallBacks=myCallBacks;
        HttpUtils httpUtils = HttpUtils.getInstance();
        httpUtils.doXiangGet(url,id, new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(ResponseBody body) {
                try {
                    String json = body.string();
                    Gson gson=new Gson();
                    XiangBean xiangBean = gson.fromJson(json, XiangBean.class);
                    myCallBacks.success(xiangBean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //查看电影详情
    @Override
    public void Movings(String url, int id, final MyCallBacks myCallBacks) {
        this.myCallBacks=myCallBacks;
        HttpUtils httpUtils = HttpUtils.getInstance();
        httpUtils.doMovingGet(url,id, new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(ResponseBody body) {
                try {
                    String json = body.string();
                    Gson gson=new Gson();
                    MovingBean movingBean = gson.fromJson(json, MovingBean.class);
                    myCallBacks.success(movingBean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //查看电影评价
    @Override
    public void toAssess(String url, int id, int page, int count, final MyCallBacks myCallBacks) {
        this.myCallBacks=myCallBacks;
        HttpUtils httpUtils = HttpUtils.getInstance();
        httpUtils.doAssessGet(url,id,page,count, new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(ResponseBody body) {
                try {
                    String json = body.string();
                    Gson gson=new Gson();
                    AssessBean assessBean = gson.fromJson(json, AssessBean.class);
                    myCallBacks.success(assessBean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
     //添加对影片的评论
    @Override
    public void toAddPing(String url, final HashMap<String,Object> map, final MyCallBacks myCallBacks) {
        this.myCallBacks=myCallBacks;
        HttpUtils httpUtils = HttpUtils.getInstance();
        httpUtils.doAddPing(url,map, new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(ResponseBody body) {
                try {
                    String json = body.string();
                    JSONObject object=new JSONObject(json);
                    String message = object.getString("message");
                    myCallBacks.success(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //添加用户对评论的回复
    @Override
    public void toAddReply(String url, HashMap<String, Object> map, final MyCallBacks myCallBacks) {
        this.myCallBacks=myCallBacks;
        HttpUtils httpUtils = HttpUtils.getInstance();
        httpUtils.doAddReply(url,map, new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(ResponseBody body) {
                try {
                    String json = body.string();
                    JSONObject object=new JSONObject(json);
                    String message = object.getString("message");
                    myCallBacks.success(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //关注
    @Override
    public void toAttention(String url, int id, final MyCallBacks myCallBacks) {
        this.myCallBacks=myCallBacks;
        HttpUtils httpUtils = HttpUtils.getInstance();
        httpUtils.doAttention(url,id, new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(ResponseBody body) {
                try {
                    String json = body.string();
                    JSONObject object=new JSONObject(json);
                    String message = object.getString("message");
                    myCallBacks.success(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
     //取消关注
    @Override
    public void toCancle(String url, int id, final MyCallBacks myCallBacks) {
        this.myCallBacks=myCallBacks;
        HttpUtils httpUtils = HttpUtils.getInstance();
        httpUtils.doCancle(url,id, new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(ResponseBody body) {
                try {
                    String json = body.string();
                    JSONObject object=new JSONObject(json);
                    String message = object.getString("message");
                    myCallBacks.success(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //点赞
    @Override
    public void toDian(String url, int id, final MyCallBacks myCallBacks) {
        this.myCallBacks=myCallBacks;
        HttpUtils httpUtils = HttpUtils.getInstance();
        httpUtils.doDian(url,id, new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(ResponseBody body) {
                try {
                    String json = body.string();
                    JSONObject object=new JSONObject(json);
                    String message = object.getString("message");
                    myCallBacks.success(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //根据电影ID查询当前排片该电影的影院列表
    @Override
    public void toFindMovie(String url, int id, final MyCallBacks myCallBacks) {
        this.myCallBacks=myCallBacks;
        HttpUtils httpUtils = HttpUtils.getInstance();
        httpUtils.doFind(url,id, new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(ResponseBody body) {
                try {
                    String json = body.string();
                     Gson gson=new Gson();
                    FindBean findBean = gson.fromJson(json, FindBean.class);
                    myCallBacks.success(findBean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
     //关注影院
    @Override
    public void toFllowCinema(String url, int id, final MyCallBacks myCallBacks) {
        this.myCallBacks=myCallBacks;
        HttpUtils httpUtils = HttpUtils.getInstance();
        httpUtils.doFllowCinema(url,id, new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(ResponseBody body) {
                try {
                    String json = body.string();
                    JSONObject object=new JSONObject(json);
                    String message = object.getString("message");
                    myCallBacks.success(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //取消关注影院
    @Override
    public void toCancleFllowCinema(String url, int id, final MyCallBacks myCallBacks) {
        this.myCallBacks=myCallBacks;
        HttpUtils httpUtils = HttpUtils.getInstance();
        httpUtils.doCancleFllowCinema(url,id, new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(ResponseBody body) {
                try {
                    String json = body.string();
                    JSONObject object=new JSONObject(json);
                    String message = object.getString("message");
                    myCallBacks.success(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //根据电影ID和影院ID查询电影排期列表
    @Override
    public void toMovieScheduleList(String url, int movieId, int cinemasId, final MyCallBacks myCallBacks) {
        this.myCallBacks=myCallBacks;
        HttpUtils httpUtils = HttpUtils.getInstance();
        httpUtils.doMovieScheduleList(url,movieId,cinemasId, new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(ResponseBody body) {
                try {
                    String json = body.string();
                    Gson gson=new Gson();
                    MovieScheduleListBean movieScheduleListBean = gson.fromJson(json, MovieScheduleListBean.class);
                    myCallBacks.success(movieScheduleListBean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //根据用户ID查询用户信息
    @Override
    public void getUserInfo(String url, final MyCallBacks myCallBacks) {
        this.myCallBacks=myCallBacks;
        HttpUtils httpUtils = HttpUtils.getInstance();
        httpUtils.getUserInfo(url,new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(ResponseBody body) {
                try {
                    String json = body.string();
                    Gson gson=new Gson();
                    UserInfoBean userInfoBean = gson.fromJson(json, UserInfoBean.class);
                    myCallBacks.success(userInfoBean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
      //用户上传头像
    @Override
    public void getHeadPic(String url, File file, final MyCallBacks myCallBacks) {
        this.myCallBacks=myCallBacks;
        HttpUtils httpUtils = HttpUtils.getInstance();
        httpUtils.getHeadPic(url,file,new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(ResponseBody body) {
                try {
                    String json = body.string();
                    Gson gson=new Gson();
                    HeadPicBean headPicBean = gson.fromJson(json, HeadPicBean.class);
                    myCallBacks.success(headPicBean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //签到
    @Override
    public void toSignIn(String url, final MyCallBacks myCallBacks) {
        this.myCallBacks=myCallBacks;
        HttpUtils httpUtils = HttpUtils.getInstance();
        httpUtils.toSignIn(url,new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(ResponseBody body) {
                try {
                    String json = body.string();
                    JSONObject object=new JSONObject(json);
                    String message = object.getString("message");
                    myCallBacks.success(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //购票下单
    @Override
    public void buyTicket(String url, int id, int count, String sign, final MyCallBacks myCallBacks) {
        this.myCallBacks=myCallBacks;
        HttpUtils httpUtils = HttpUtils.getInstance();
        httpUtils.toTicket(url,id,count,sign,new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(ResponseBody body) {
                try {
                    String json = body.string();
                    Gson gson=new Gson();
                    TicketBean ticketBean = gson.fromJson(json,TicketBean.class);
                    myCallBacks.success(ticketBean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //微信支付
    @Override
    public void toWX(String url, int type, String orderId, final MyCallBacks myCallBacks) {
        this.myCallBacks=myCallBacks;
        HttpUtils httpUtils = HttpUtils.getInstance();
        httpUtils.toPay(url,type,orderId,new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(ResponseBody body) {
                try {
                    String json = body.string();
                    Gson gson=new Gson();
                    WeixinBean weixinBean = gson.fromJson(json,WeixinBean.class);
                    myCallBacks.success(weixinBean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
      //支付宝
    @Override
    public void toZFB(String url, int type, String orderId, final MyCallBacks myCallBacks) {
        this.myCallBacks=myCallBacks;
        HttpUtils httpUtils = HttpUtils.getInstance();
        httpUtils.toPay(url,type,orderId,new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(ResponseBody body) {
                try {
                    String json = body.string();
                    Gson gson=new Gson();
                    ZhifubaoBean zhifubaoBean = gson.fromJson(json,ZhifubaoBean.class);
                    myCallBacks.success(zhifubaoBean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //用户购票记录查询列表
    @Override
    public void RecordList(String url, int page, int count, int status, final MyCallBacks myCallBacks) {
        this.myCallBacks=myCallBacks;
        HttpUtils httpUtils = HttpUtils.getInstance();
        httpUtils.toRecordList(url,page,count,status,new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(ResponseBody body) {
                try {
                    String json = body.string();
                    Gson gson=new Gson();
                    RecodeBean recodeBean = gson.fromJson(json,RecodeBean.class);
                    myCallBacks.success(recodeBean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //关注的影片
    @Override
    public void MoviePage(String url, int page, int count, final MyCallBacks myCallBacks) {
        this.myCallBacks=myCallBacks;
        HttpUtils httpUtils = HttpUtils.getInstance();
        httpUtils.toMovie(url,page,count,new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(ResponseBody body) {
                try {
                    String json = body.string();
                    Gson gson=new Gson();
                    MoviePageBean pageListBean = gson.fromJson(json,MoviePageBean.class);
                    myCallBacks.success(pageListBean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
     //关注的影院
    @Override
    public void CinemaPage(String url, int page, int count, final MyCallBacks myCallBacks) {
        this.myCallBacks=myCallBacks;
        HttpUtils httpUtils = HttpUtils.getInstance();
        httpUtils.toCinema(url,page,count,new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseBody body) {
                try {
                    String json = body.string();
                    Gson gson=new Gson();
                    CinemaPageBean cinemaPageBean = gson.fromJson(json,CinemaPageBean.class);
                    myCallBacks.success(cinemaPageBean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
      //意见反馈
    @Override
    public void recordFeedBack(String url, String content, final MyCallBacks myCallBacks) {
        this.myCallBacks=myCallBacks;
        HttpUtils httpUtils = HttpUtils.getInstance();
        httpUtils.toFeedBack(url,content,new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(ResponseBody body) {
                try {
                    String json = body.string();
                    JSONObject object=new JSONObject(json);
                    String message = object.getString("message");
                    myCallBacks.success(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void WXLogin(String url, String code, final MyCallBacks myCallBacks) {
        this.myCallBacks=myCallBacks;
        HttpUtils httpUtils = HttpUtils.getInstance();
        httpUtils.WXLogin(url,code,new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {
                Log.e("tag","onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("tag","onError");
            }

            @Override
            public void onNext(ResponseBody body) {
                try {
                    String json = body.string();
                    Gson gson=new Gson();
                    WXBean wxBean = gson.fromJson(json, WXBean.class);
                    myCallBacks.success(wxBean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public  interface  MyCallBacks{
        public  void success(Object object);
    }
}
