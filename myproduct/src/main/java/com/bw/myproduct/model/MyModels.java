package com.bw.myproduct.model;

import android.util.Log;

import com.bw.myproduct.beans.DataBean;
import com.bw.myproduct.beans.FootBean;
import com.bw.myproduct.beans.GouBean;
import com.bw.myproduct.beans.ImageBean;
import com.bw.myproduct.beans.MyAddressBean;
import com.bw.myproduct.beans.MyQuanBean;
import com.bw.myproduct.beans.OrderBean;
import com.bw.myproduct.beans.WalletBean;
import com.bw.myproduct.contract.ContractInterface;
import com.bw.myproduct.utils.TwoUtils;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import okhttp3.ResponseBody;
import rx.Observer;

public class MyModels  implements ContractInterface.MyModelInter {
    MyTwoCallBack myTwoCallBack;
    MySuanCallBack mySuanCallBack;
    //购物车的展示
    @Override
    public void toGou(String url, int userId, String sessionId, final MyTwoCallBack myTwoCallBack) {
             this.myTwoCallBack=myTwoCallBack;
              TwoUtils util = TwoUtils.getInstance();
              util.doGou(url, userId, sessionId, new Observer<ResponseBody>() {
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
                          GouBean gouBean = gson.fromJson(json, GouBean.class);
                          myTwoCallBack.success(gouBean);
                      } catch (Exception e) {
                          e.printStackTrace();
                      }
                  }
              });
    }
       //去结算
    @Override
    public void toSuan(String url, int userId, String sessionId, String data, double sum, int address, final MySuanCallBack mySuanCallBack) {
            this.mySuanCallBack =mySuanCallBack;
            TwoUtils util = TwoUtils.getInstance();
            util.doSuanGet(url, userId, sessionId, data, sum, address, new Observer<ResponseBody>() {
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
                    mySuanCallBack.success(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
     //添加收货地址
    @Override
    public void toAdd(String url, int userId, String sessionId, String name, String phone, String address, String bian, final MySuanCallBack mySuanCallBack) {
        this.mySuanCallBack=mySuanCallBack;
        TwoUtils util = TwoUtils.getInstance();
        util.doAddGet(url, userId, sessionId, name, phone, address, bian, new Observer<ResponseBody>() {
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
                    mySuanCallBack.success(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //我的资料
    @Override
    public void toData(String url, int userId, String sessionId, final MyTwoCallBack myTwoCallBack) {
        this.myTwoCallBack=myTwoCallBack;
        TwoUtils util = TwoUtils.getInstance();
        util.doDataGet(url, userId, sessionId, new Observer<ResponseBody>() {
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
                    DataBean dataBean = gson.fromJson(json, DataBean.class);
                    myTwoCallBack.success(dataBean);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //修改昵称
    @Override
    public void toUpName(String url, int userId, String sessionId, String nickName, final MySuanCallBack mySuanCallBack) {
           this.mySuanCallBack=mySuanCallBack;
           TwoUtils util = TwoUtils.getInstance();
           util.doUpNameGet(url,userId,sessionId,nickName, new Observer<ResponseBody>() {
               @Override
               public void onCompleted() {
               }

               @Override
               public void onError(Throwable e) {
               }

               @Override
               public void onNext(ResponseBody body) {
                   try {
                       String  json = body.string();
                       JSONObject object=new JSONObject(json);
                       String message = object.getString("message");
                       mySuanCallBack.success(message);
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
               }
           });
    }
    //修改密码
    @Override
    public void toUpPwd(String url, int userId, String sessionId, String oldName, String newName, final MySuanCallBack mySuanCallBack) {
        this.mySuanCallBack=mySuanCallBack;
        TwoUtils util = TwoUtils.getInstance();
            util.doPwdGet(url,userId,sessionId,oldName,newName, new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseBody body) {
                try {
                    String  json = body.string();
                    JSONObject object=new JSONObject(json);
                    String message = object.getString("message");
                    mySuanCallBack.success(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //去评论
    @Override
    public void toReport(String url, int userId, String sessionId, int commodityId,String orderId, String content, File file, final MySuanCallBack mySuanCallBack) {
         this.mySuanCallBack=mySuanCallBack;
        TwoUtils util = TwoUtils.getInstance();
        util.doReportGet(url, userId, sessionId,commodityId,orderId,content,file, new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(ResponseBody body) {
                try {
                    String  json = body.string();
                    JSONObject object=new JSONObject(json);
                    String message = object.getString("message");
                    mySuanCallBack.success(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
     //同步到圈子
    @Override
    public void toSynch(String url, int userId, String sessionId, int commodityId, String content, File file, final MySuanCallBack mySuanCallBack) {
        this.mySuanCallBack=mySuanCallBack;
        TwoUtils util = TwoUtils.getInstance();
        util.doSynchGet(url, userId, sessionId,commodityId,content,file, new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(ResponseBody body) {
                try {
                    String  json = body.string();
                    JSONObject object=new JSONObject(json);
                    String message = object.getString("message");
                    mySuanCallBack.success(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //点赞
    @Override
    public void toGiveUp(String url, int userId, String sessionId, int circleId, final MySuanCallBack mySuanCallBack) {
        this.mySuanCallBack=mySuanCallBack;
        TwoUtils util = TwoUtils.getInstance();
        util.doGiveUpGet(url, userId, sessionId,circleId, new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(ResponseBody body) {
                try {
                    String  json = body.string();
                    JSONObject object=new JSONObject(json);
                    String message = object.getString("message");
                    mySuanCallBack.success(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
     //取消点赞
    @Override
    public void toGiveOut(String url, int userId, String sessionId, int circleId, final MySuanCallBack mySuanCallBack) {
        this.mySuanCallBack=mySuanCallBack;
        TwoUtils util = TwoUtils.getInstance();
        util.doGiveUpOut(url, userId, sessionId,circleId, new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(ResponseBody body) {
                try {
                    String  json = body.string();
                    JSONObject object=new JSONObject(json);
                    String message = object.getString("message");
                    mySuanCallBack.success(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
     //删除我发表过的圈子
    @Override
    public void toDelete(String url, int userId, String sessionId, int circleId, final MySuanCallBack mySuanCallBack) {
        this.mySuanCallBack=mySuanCallBack;
        TwoUtils util = TwoUtils.getInstance();
        util.doDeleteGet(url, userId, sessionId,circleId, new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(ResponseBody body) {
                try {
                    String  json = body.string();
                    JSONObject object=new JSONObject(json);
                    String message = object.getString("message");
                    mySuanCallBack.success(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //我的圈子
    @Override
    public void Quans(String url, int userId, String sessionId, int page, int count, final MyTwoCallBack myTwoCallBack) {
        this.myTwoCallBack=myTwoCallBack;
        TwoUtils util = TwoUtils.getInstance();
        util.doQuanGet(url, userId, sessionId, page, count, new Observer<ResponseBody>() {
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
                    MyQuanBean myQuanBean = gson.fromJson(json, MyQuanBean.class);
                    myTwoCallBack.success(myQuanBean);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //我的足迹
    @Override
    public void toFoot(String url, int userId, String sessionId, int page, int count, final MyTwoCallBack myTwoCallBack) {
        this.myTwoCallBack=myTwoCallBack;
        TwoUtils util = TwoUtils.getInstance();
        util.doFootGet(url,userId,sessionId,page,count, new Observer<ResponseBody>() {
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
                    FootBean footBean = gson.fromJson(json, FootBean.class);
                    myTwoCallBack.success(footBean);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
   //wode钱包
    @Override
    public void toWallet(String url, int userId, String sessionId, int page, int count, final MyTwoCallBack myTwoCallBack) {
        this.myTwoCallBack=myTwoCallBack;
        TwoUtils util = TwoUtils.getInstance();
        util.doWalletGet(url,userId,sessionId,page,count, new Observer<ResponseBody>() {
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
                    WalletBean walletBean = gson.fromJson(json, WalletBean.class);
                    myTwoCallBack.success(walletBean);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } );
    }
       //地址列表
    @Override
    public void toAddress(String url, int userId, String sessionId, final MyTwoCallBack myTwoCallBack) {
        this.myTwoCallBack=myTwoCallBack;
        TwoUtils util = TwoUtils.getInstance();
        util.doAddress(url,userId,sessionId, new Observer<ResponseBody>() {
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
                    MyAddressBean myAddressBean = gson.fromJson(json, MyAddressBean.class);
                    myTwoCallBack.success(myAddressBean);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //默认地址
    @Override
    public void toDefault(String url, int userId, String sessionId, int id, final MySuanCallBack mySuanCallBack) {
         this.mySuanCallBack=mySuanCallBack;
        TwoUtils util = TwoUtils.getInstance();
        util.doDefaultGet(url,userId,sessionId,id, new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseBody body) {
                try {
                      String  json = body.string();
                    JSONObject object=new JSONObject(json);
                    String message = object.getString("message");
                    mySuanCallBack.success(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
        //修改收货地址
    @Override
    public void toUpdate(String url, int userId, String sessionId, int id, String name, String phone, String address, String bian, final MySuanCallBack mySuanCallBack) {
        this.mySuanCallBack=mySuanCallBack;
        TwoUtils util = TwoUtils.getInstance();
        util.doUpdateGet(url,userId,sessionId,id,name,phone,address,bian, new Observer<ResponseBody>() {
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
                    mySuanCallBack.success(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
      //全部订单
    @Override
    public void toAllOrders(String url, int userId, String sessionId, int status, int page, int count, final MyTwoCallBack myTwoCallBack) {
        this.myTwoCallBack=myTwoCallBack;
        TwoUtils util = TwoUtils.getInstance();
        util.doAllOrders(url,userId,sessionId,status,page,count, new Observer<ResponseBody>() {
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
                    OrderBean orderBean = gson.fromJson(json, OrderBean.class);
                    myTwoCallBack.success(orderBean);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
    //带付款订单
    @Override
    public void toTwoOrders(String url, int userId, String sessionId, int status, int page, int count, final MyTwoCallBack myTwoCallBack) {
        this.myTwoCallBack=myTwoCallBack;
        TwoUtils util = TwoUtils.getInstance();
        util.doAllOrders(url,userId,sessionId,status,page,count, new Observer<ResponseBody>() {
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
                    OrderBean orderBean = gson.fromJson(json, OrderBean.class);
                    myTwoCallBack.success(orderBean);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    //取消订单或删除订单
    @Override
    public void toCancle(String url, int userId, String sessionId, String id, final MySuanCallBack mySuanCallBack) {
        this.mySuanCallBack=mySuanCallBack;
        TwoUtils util = TwoUtils.getInstance();
        util.doCancleGet(url,userId,sessionId,id, new Observer<ResponseBody>() {
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
                    mySuanCallBack.success(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
     //取支付订单
    @Override
    public void tOPay(String url, int userId, String sessionId, String id, int type, final MySuanCallBack mySuanCallBack) {
        this.mySuanCallBack=mySuanCallBack;
        TwoUtils util = TwoUtils.getInstance();
        util.doPayGet(url,userId,sessionId,id,type, new Observer<ResponseBody>() {
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
                    mySuanCallBack.success(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
     //待收货订单
    @Override
    public void toThreeOrders(String url, int userId, String sessionId, int status, int page, int count, final MyTwoCallBack myTwoCallBack) {
        this.myTwoCallBack=myTwoCallBack;
        TwoUtils utils = TwoUtils.getInstance();
        utils.doAllOrders(url,userId,sessionId,status,page,count, new Observer<ResponseBody>() {
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
                    OrderBean orderBean = gson.fromJson(json, OrderBean.class);
                    myTwoCallBack.success(orderBean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
      //确认收货
    @Override
    public void toAffirms(String url, int userId, String sessionId, String id, final MySuanCallBack mySuanCallBack) {
        this.mySuanCallBack=mySuanCallBack;
        TwoUtils util = TwoUtils.getInstance();
        util.doAffirms(url,userId,sessionId,id, new Observer<ResponseBody>() {
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
                    mySuanCallBack.success(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //待评价订单
    @Override
    public void toFourOrders(String url, int userId, String sessionId, int status, int page, int count, final MyTwoCallBack myTwoCallBack) {
        this.myTwoCallBack=myTwoCallBack;
        TwoUtils utils = TwoUtils.getInstance();
        utils.doAllOrders(url,userId,sessionId,status,page,count, new Observer<ResponseBody>() {
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
                    OrderBean orderBean = gson.fromJson(json, OrderBean.class);
                    myTwoCallBack.success(orderBean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
     //已完成订单
    @Override
    public void toFiveOrders(String url, int userId, String sessionId, int status, int page, int count, final MyTwoCallBack myTwoCallBack) {
        this.myTwoCallBack=myTwoCallBack;
        TwoUtils utils = TwoUtils.getInstance();
        utils.doAllOrders(url,userId,sessionId,status,page,count, new Observer<ResponseBody>() {
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
                    OrderBean orderBean = gson.fromJson(json, OrderBean.class);
                    myTwoCallBack.success(orderBean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
     //上传头像
    @Override
    public void toUpImage(String url, int userId, String sessionId, File file, final MyTwoCallBack myTwoCallBack) {
        this.myTwoCallBack=myTwoCallBack;
        TwoUtils utils = TwoUtils.getInstance();
        utils.doImageGet(url,userId,sessionId,file, new Observer<ResponseBody>() {
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
                    ImageBean imageBean = gson.fromJson(json, ImageBean.class);
                    myTwoCallBack.success(imageBean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public  interface  MyTwoCallBack{
        public  void success(Object object);
    }
    public  interface  MySuanCallBack{
        public  void success(Object object);
    }
}
