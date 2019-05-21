package com.bw.myproduct.contract;

import com.bw.myproduct.beans.DataBean;
import com.bw.myproduct.beans.FootBean;
import com.bw.myproduct.beans.GouBean;
import com.bw.myproduct.beans.ImageBean;
import com.bw.myproduct.beans.MyAddressBean;
import com.bw.myproduct.beans.MyQuanBean;
import com.bw.myproduct.beans.OrderBean;
import com.bw.myproduct.beans.WalletBean;
import com.bw.myproduct.model.MyModels;

import java.io.File;
import java.util.List;

public interface ContractInterface {
     public  interface  MyPresenterInter{
          //请求购物车
          public  void  Gou(int userId,String sessionId);
          //结算
          public  void  Suan(int userId,String sessionId,String data,double sum,int address);
          //添加收货地址
          public  void  Add(int userId,String sessionId,String name,String phone,String address,String bian);
          //我的资料
          public  void  Datas(int userId,String sessionId);
          //我的圈子
          public  void  Quans(int userId,String sessionId,int page,int count);
          //我的足迹
          public  void  Foot(int userId,String sessionId,int page,int count);
          //我的钱包
          public  void  Wallet(int userId,String sessionId,int page,int count);
          //我的收货地址列表
          public  void  Address(int userId,String sessionId);
          //默认收货地址
          public  void  Default(int userId,String sessionId,int id);
          //修改收货地址
          public  void  Update(int userId,String sessionId,int id,String name,String phone,String address,String bian);
          //全部订单
          public  void  AllOrders(int userId,String sessionId,int status,int page,int count);
          //取消订单
          public  void  Cancle(int userId,String sessionId,String id);
          //去支付订单
          public  void  Pay(int userId,String sessionId,String id,int type);
          //待支付订单
          public  void  TwoOrders(int userId,String sessionId,int status,int page,int count);
          //待付款订单
          public  void  ThreeOrders(int userId,String sessionId,int status,int page,int count);
          //确认收货
          public  void Affirms(int userId,String sessionId,String orderId);
         //带评价
         public  void  FourOrders(int userId,String sessionId,int status,int page,int count);
          //已完成订单
          public  void  FiveOrders(int userId,String sessionId,int status,int page,int count);
         //修改头像
          public  void  upImage(int userId,String sessionId,File file);
         //修改昵称
          public  void  upNikeName(int userId,String sessionId,String nickName);
          //修改密码
          public  void  upPwd(int userId,String sessionId,String oldName,String newName);
          //评论
          public  void  Report(int userId,String sessionId,int commodityId,String orderId,String content,File file);
         //同步到圈子
         public  void    Synch(int userId,String sessionId,int commodityId,String content,File file);
         //圈子点赞
         public  void    GiveUp(int userId,String sessionId,int circleId);
         //取消点赞
         public  void    GiveOut(int userId,String sessionId,int circleId);
         //删除我发表过的圈子
          public  void     Delete(int userId,String sessionId,int circleId);
     }
     public  interface  MyModelInter{
         //购物车数据
          public  void toGou(String url, int userId, String sessionId, MyModels.MyTwoCallBack myTwoCallBack);
          //去结算  创建订单
          public  void  toSuan(String url, int userId, String sessionId, String data, double sum, int address, MyModels.MySuanCallBack mySuanCallBack);
          //添加收货地址
          public  void  toAdd(String url, int userId, String sessionId, String name, String phone, String address, String bian, MyModels.MySuanCallBack mySuanCallBack);
          //我的资料
          public  void  toData(String url, int userId, String sessionId, MyModels.MyTwoCallBack myTwoCallBack);
          //圈子列表
         public  void  Quans(String url, int userId, String sessionId, int page, int count, MyModels.MyTwoCallBack myTwoCallBack);
          //我的足迹
          public  void  toFoot(String url, int userId, String sessionId, int page, int count, MyModels.MyTwoCallBack myTwoCallBack);
          //我的钱包
          public  void  toWallet(String url, int userId, String sessionId, int page, int count, MyModels.MyTwoCallBack myTwoCallBack);
          //收货地址列表
          public  void  toAddress(String url, int userId, String sessionId, MyModels.MyTwoCallBack myTwoCallBack);
          //默认收货地址
          public  void  toDefault(String url, int userId, String sessionId,int id, MyModels.MySuanCallBack mySuanCallBack);
          //修改收货地址
          public  void  toUpdate(String url, int userId, String sessionId,int id, String name, String phone, String address, String bian, MyModels.MySuanCallBack mySuanCallBack);
          //全部订单
          public  void  toAllOrders(String url, int userId, String sessionId,int status,int page,int count, MyModels.MyTwoCallBack myTwoCallBack);
         //带支付订单
          public  void  toTwoOrders(String url, int userId, String sessionId,int status,int page,int count, MyModels.MyTwoCallBack myTwoCallBack);
          //取消订单
          public  void  toCancle(String url, int userId, String sessionId, String id, MyModels.MySuanCallBack mySuanCallBack);
         //支付
          public  void  tOPay(String url, int userId, String sessionId, String id, int type, MyModels.MySuanCallBack mySuanCallBack);
          //带收获订单
          public  void  toThreeOrders(String url, int userId, String sessionId,int status,int page,int count, MyModels.MyTwoCallBack myTwoCallBack);
          //确认订单
          public  void  toAffirms(String url, int userId, String sessionId, String id, MyModels.MySuanCallBack mySuanCallBack);
          //带支付订单
          public  void  toFourOrders(String url, int userId, String sessionId,int status,int page,int count, MyModels.MyTwoCallBack myTwoCallBack);
          //已完成
          public  void  toFiveOrders(String url, int userId, String sessionId,int status,int page,int count, MyModels.MyTwoCallBack myTwoCallBack);
          //修改头像
          public  void toUpImage(String url, int userId, String sessionId, File file, MyModels.MyTwoCallBack myTwoCallBack);
          //修改昵称
          public  void toUpName(String url,int userId,String sessionId,String nickName,MyModels.MySuanCallBack mySuanCallBack);
          //修改密码
          public  void   toUpPwd(String url, int userId, String sessionId, String oldName, String newName, MyModels.MySuanCallBack mySuanCallBack);
           //评论
           public  void  toReport(String url,int userId,String sessionId,int commodityId,String orderId,String content,File file,MyModels.MySuanCallBack mySuanCallBack);
           //同步到圈子
           public  void  toSynch(String url,int userId,String sessionId,int commodityId,String content,File file,MyModels.MySuanCallBack mySuanCallBack);
           //圈子点赞
           public  void   toGiveUp(String url,int userId, String sessionId, int circleId, MyModels.MySuanCallBack mySuanCallBack);
           //取消点赞
           public  void   toGiveOut(String url,int userId, String sessionId, int circleId, MyModels.MySuanCallBack mySuanCallBack);
           //删除我发表过的圈子
           public  void   toDelete(String url,int userId, String sessionId, int circleId, MyModels.MySuanCallBack mySuanCallBack);

     }
     public  interface  MyViewInter{
         //购物车展示
          public  void MyGou(List<GouBean.ResultBean>  list);
     }
     public  interface  MySuanInter{
         //去结算
          public  void MySuan(String str);
     }
     public  interface  MyAddInter{
         //添加地址
          public  void MyAdd(String str);
     }
     public  interface  MyDataInter{
          //我的资料
          public  void MyData(DataBean dataBean);
          //上传头像
          public  void MyUpImage(ImageBean imageBean);
          //修改昵称
          public  void MyUpName(String str);
          //修改密码
          public  void MyUpPwd(String str);

     }
     public  interface  MyQuanInter{
         //我的圈子
          public  void MyQuans(List<MyQuanBean.ResultBean>  list);
          //点赞
          public  void MyGiveUp(String str);
          //取消点赞
          public  void MyGiveOut(String str);
          //删除圈子
          public  void MyDelete(String str);
     }
     public  interface  MyFootInter{
         //我的足迹
          public  void  MyFoot(List<FootBean.ResultBean>  list);
     }
     public  interface  MyWalletInter{
         //我的钱包
          public  void  MyWallet(WalletBean walletBean);
     }
     public  interface  MyAddressInter{
         //我的收货地址
          public  void  MyAddress(List<MyAddressBean.ResultBean>  list);
          //默认地址
          public  void  MyDeault(String str);
     }
     public  interface  MyUpInter{
         //修改地址
          public  void  MyUp(String str );
     }
     public  interface  MyOrderInter{
          //带收货
          public  void  MyTwoOrder(OrderBean orderBean);
          //代付款
          public  void  MyThreeOrder(OrderBean orderBean);
          //删除订单
          public  void  MyCancle(String str);
          //确认订单
          public  void  MyAffirm(String str);
          //待评价订单
          public  void  MyFourOrder(OrderBean orderBean);
          //已完成订单
          public  void  MyFiveOrder(OrderBean orderBean);
          //全部订单
           public void MyAllOrder(OrderBean orderBean);
     }
     public  interface  MyPayInter{
         //去支付
          public  void  MyPay(String str );
     }
    public  interface  MyAssessInter{
        //评论
        public  void  MyAssess(String str );
        //同步到圈子
        public  void  MySynch(String str);
    }
}
