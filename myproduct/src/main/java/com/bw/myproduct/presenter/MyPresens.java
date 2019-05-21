package com.bw.myproduct.presenter;

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
import com.bw.myproduct.model.MyModels;

import java.io.File;

public class MyPresens<T>  implements ContractInterface.MyPresenterInter {
    T  viewa;
    ContractInterface.MyModelInter myModelInter;
    public  MyPresens(T tt){
         this.viewa=tt;
         myModelInter=new MyModels();
    }
    //展示购物车
    @Override
    public void Gou(int userId, String sessionId) {
        myModelInter.toGou("small/order/verify/v1/findShoppingCart", userId, sessionId, new MyModels.MyTwoCallBack() {
            @Override
            public void success(Object object) {
                  if(object instanceof GouBean){
                      GouBean gouBean= (GouBean) object;
                      ContractInterface.MyViewInter myViewInter= (ContractInterface.MyViewInter) viewa;
                      myViewInter.MyGou(gouBean.getResult());
                  }
            }
        });
    }
    //结算
    @Override
    public void Suan(int userId, String sessionId, String data, double sum, int address) {
           myModelInter.toSuan("small/order/verify/v1/createOrder", userId, sessionId, data, sum, address, new MyModels.MySuanCallBack() {
               @Override
               public void success(Object object) {
                     if(object instanceof  String){
                         String str= (String) object;
                         ContractInterface.MySuanInter mySuanInter= (ContractInterface.MySuanInter) viewa;
                         mySuanInter.MySuan(str);
                     }
               }
           });
    }
    //添加地址
    @Override
    public void Add(int userId, String sessionId, String name, String phone, String address, String bian) {
        myModelInter.toAdd("small/user/verify/v1/addReceiveAddress", userId, sessionId, name, phone, address, bian, new MyModels.MySuanCallBack() {
            @Override
            public void success(Object object) {
                 if(object instanceof  String){
                     String str= (String) object;
                     ContractInterface.MyAddInter myAddInter= (ContractInterface.MyAddInter) viewa;
                     myAddInter.MyAdd(str);
                 }
            }
        } );
    }
  //我的个人资料
    @Override
    public void Datas(int userId, String sessionId) {
        myModelInter.toData("small/user/verify/v1/getUserById", userId, sessionId, new MyModels.MyTwoCallBack() {
            @Override
            public void success(Object object) {
                    if(object instanceof DataBean){
                        DataBean dataBean= (DataBean) object;
                        ContractInterface.MyDataInter myDataInter= (ContractInterface.MyDataInter) viewa;
                        myDataInter.MyData(dataBean);
                    }
            }
        });
    }
    //修改昵称
    @Override
    public void upNikeName(int userId, String sessionId, String nickName) {
        myModelInter.toUpName("small/user/verify/v1/modifyUserNick", userId, sessionId, nickName, new MyModels.MySuanCallBack() {
            @Override
            public void success(Object object) {
                String str= (String) object;
                ContractInterface.MyDataInter myDataInter= (ContractInterface.MyDataInter) viewa;
                myDataInter.MyUpName(str);
            }
        });
    }
     //修改密码
    @Override
    public void upPwd(int userId, String sessionId, String oldName, String newName) {
        myModelInter.toUpPwd("small/user/verify/v1/modifyUserPwd", userId, sessionId, oldName, newName, new MyModels.MySuanCallBack() {
            @Override
            public void success(Object object) {
                String str= (String) object;
                ContractInterface.MyDataInter myDataInter= (ContractInterface.MyDataInter) viewa;
                myDataInter.MyUpPwd(str);
            }
        });
    }
    //评论
    @Override
    public void Report(int userId, String sessionId, int commodityId,String orderId, String content, File file) {
        myModelInter.toReport("small/commodity/verify/v1/addCommodityComment", userId, sessionId, commodityId, orderId,content, file, new MyModels.MySuanCallBack() {
            @Override
            public void success(Object object) {
                String str= (String) object;
                ContractInterface.MyAssessInter myAssessInter= (ContractInterface.MyAssessInter) viewa;
                myAssessInter.MyAssess(str);
            }
        });
    }
      //发布到圈子
    @Override
    public void Synch(int userId, String sessionId, int commodityId, String content, File file) {
        myModelInter.toSynch("small/circle/verify/v1/releaseCircle", userId, sessionId, commodityId, content, file, new MyModels.MySuanCallBack() {
            @Override
            public void success(Object object) {
                String str= (String) object;
                ContractInterface.MyAssessInter myAssessInter= (ContractInterface.MyAssessInter) viewa;
                myAssessInter.MySynch(str);
            }
        });
    }

    //点赞
    @Override
    public void GiveUp(int userId, String sessionId, int circleId) {
        myModelInter.toGiveUp("small/circle/verify/v1/addCircleGreat",userId, sessionId, circleId, new MyModels.MySuanCallBack() {
            @Override
            public void success(Object object) {
                String str= (String) object;
                ContractInterface.MyQuanInter myQuanInter= (ContractInterface.MyQuanInter) viewa;
                myQuanInter.MyGiveUp(str);
            }
        });
    }
    //取消点赞
    @Override
    public void GiveOut(int userId, String sessionId, int circleId) {
        myModelInter.toGiveOut("small/circle/verify/v1/cancelCircleGreat",userId, sessionId, circleId, new MyModels.MySuanCallBack() {
            @Override
            public void success(Object object) {
                String str= (String) object;
                ContractInterface.MyQuanInter myQuanInter= (ContractInterface.MyQuanInter) viewa;
                myQuanInter.MyGiveOut(str);
            }
        });
    }
    //删除我发表过的圈子
    @Override
    public void Delete(int userId, String sessionId, int circleId) {
        myModelInter.toDelete("small/circle/verify/v1/deleteCircle",userId, sessionId, circleId, new MyModels.MySuanCallBack() {
            @Override
            public void success(Object object) {
                String str= (String) object;
                ContractInterface.MyQuanInter myQuanInter= (ContractInterface.MyQuanInter) viewa;
                myQuanInter.MyDelete(str);
            }
        });
    }

    //我的圈子
    @Override
    public void Quans(int userId, String sessionId, int page, int count) {
        myModelInter.Quans("small/circle/verify/v1/findMyCircleById", userId, sessionId, page, count, new MyModels.MyTwoCallBack() {
            @Override
            public void success(Object object) {
                  if(object instanceof MyQuanBean){
                      MyQuanBean myQuanBean= (MyQuanBean) object;
                      ContractInterface.MyQuanInter myQuanInter= (ContractInterface.MyQuanInter) viewa;
                      myQuanInter.MyQuans(myQuanBean.getResult());
                  }
            }
        });
    }
    //我的足迹
    @Override
    public void Foot(int userId, String sessionId, int page, int count) {
        myModelInter.toFoot("small/commodity/verify/v1/browseList", userId, sessionId, page, count, new MyModels.MyTwoCallBack() {
            @Override
            public void success(Object object) {
                if(object instanceof FootBean){
                    FootBean footBean= (FootBean) object;
                    ContractInterface.MyFootInter myFootInter= (ContractInterface.MyFootInter) viewa;
                    myFootInter.MyFoot(footBean.getResult());
                }
            }
        });
    }
     //我的钱包
    @Override
    public void Wallet(int userId, String sessionId, int page, int count) {
        myModelInter.toWallet("small/user/verify/v1/findUserWallet", userId, sessionId, page, count, new MyModels.MyTwoCallBack() {
            @Override
            public void success(Object object) {
                if(object instanceof WalletBean){
                    WalletBean walletBean= (WalletBean) object;
                    ContractInterface.MyWalletInter myWalletInter= (ContractInterface.MyWalletInter) viewa;
                    myWalletInter.MyWallet(walletBean);
                }
            }
        });
    }
    //地址列表
    @Override
    public void Address(int userId, String sessionId) {
        myModelInter.toAddress("small/user/verify/v1/receiveAddressList", userId, sessionId, new MyModels.MyTwoCallBack() {
            @Override
            public void success(Object object) {
                if(object instanceof MyAddressBean){
                    MyAddressBean myAddressBean= (MyAddressBean) object;
                    ContractInterface.MyAddressInter myAddressInter= (ContractInterface.MyAddressInter) viewa;
                    myAddressInter.MyAddress(myAddressBean.getResult());
                }
            }
        });
    }
    //添加默认地址
    @Override
    public void Default(int userId, String sessionId, int id) {
        myModelInter.toDefault("small/user/verify/v1/setDefaultReceiveAddress",userId,sessionId,id, new MyModels.MySuanCallBack() {
            @Override
            public void success(Object object) {
                  String str= (String) object;
                  ContractInterface.MyAddressInter myAddressInter= (ContractInterface.MyAddressInter) viewa;
                myAddressInter.MyDeault(str);
            }
        });
    }
     //修改地址
    @Override
    public void Update(int userId, String sessionId, int id, String name, String phone, String address, String bian) {
        myModelInter.toUpdate("small/user/verify/v1/changeReceiveAddress",userId,sessionId,id,name,phone,address,bian, new MyModels.MySuanCallBack() {
            @Override
            public void success(Object object) {
                String str= (String) object;
                ContractInterface.MyUpInter myUpInter= (ContractInterface.MyUpInter) viewa;
                myUpInter.MyUp(str);
            }
        });
    }
        //全部订单
    @Override
    public void AllOrders(int userId, String sessionId, int status, int page, int count) {
        myModelInter.toAllOrders("small/order/verify/v1/findOrderListByStatus", userId, sessionId, status, page, count, new MyModels.MyTwoCallBack() {
            @Override
            public void success(Object object) {
                if(object instanceof OrderBean){
                    OrderBean orderBean= (OrderBean) object;
                    ContractInterface.MyOrderInter myOrderInter= (ContractInterface.MyOrderInter) viewa;
                    myOrderInter.MyAllOrder(orderBean);

                }
            }
        });
    }

    //代支付订单
    @Override
    public void TwoOrders(int userId, String sessionId, int status, int page, int count) {
        myModelInter.toTwoOrders("small/order/verify/v1/findOrderListByStatus", userId, sessionId, status, page, count, new MyModels.MyTwoCallBack() {
            @Override
            public void success(Object object) {
                if(object instanceof OrderBean){
                    OrderBean orderBean= (OrderBean) object;
                     ContractInterface.MyOrderInter myOrderInter= (ContractInterface.MyOrderInter) viewa;
                     myOrderInter.MyTwoOrder(orderBean);

                }
            }
        });
    }
     //带付款订单
    @Override
    public void ThreeOrders(int userId, String sessionId, int status, int page, int count) {
        myModelInter.toThreeOrders("small/order/verify/v1/findOrderListByStatus", userId, sessionId, status, page, count, new MyModels.MyTwoCallBack() {
            @Override
            public void success(Object object) {
                if (object instanceof OrderBean) {
                    OrderBean orderBean = (OrderBean) object;
                    ContractInterface.MyOrderInter myOrderInter = (ContractInterface.MyOrderInter) viewa;
                    myOrderInter.MyThreeOrder(orderBean);

                }
            }
        });
    }
     //确认订单
    @Override
    public void Affirms(int userId, String sessionId, String orderId) {
         myModelInter.toAffirms("small/order/verify/v1/confirmReceipt", userId, sessionId, orderId, new MyModels.MySuanCallBack() {
             @Override
             public void success(Object object) {
                 String str= (String) object;
                 ContractInterface.MyOrderInter myOrderInter= (ContractInterface.MyOrderInter) viewa;
                 myOrderInter.MyAffirm(str);
             }
         });
    }
    //待评价订单
    @Override
    public void FourOrders(int userId, String sessionId, int status, int page, int count) {
        myModelInter.toFourOrders("small/order/verify/v1/findOrderListByStatus", userId, sessionId, status, page, count, new MyModels.MyTwoCallBack() {
            @Override
            public void success(Object object) {
                if (object instanceof OrderBean) {
                    OrderBean orderBean = (OrderBean) object;
                    ContractInterface.MyOrderInter myOrderInter = (ContractInterface.MyOrderInter) viewa;
                    myOrderInter.MyFourOrder(orderBean);

                }
            }
        });
    }
    //已完成订单
    @Override
    public void FiveOrders(int userId, String sessionId, int status, int page, int count) {
        myModelInter.toFiveOrders("small/order/verify/v1/findOrderListByStatus", userId, sessionId, status, page, count, new MyModels.MyTwoCallBack() {
            @Override
            public void success(Object object) {
                if (object instanceof OrderBean) {
                    OrderBean orderBean = (OrderBean) object;
                    ContractInterface.MyOrderInter myOrderInter = (ContractInterface.MyOrderInter) viewa;
                    myOrderInter.MyFiveOrder(orderBean);

                }
            }
        });
    }

    @Override
    public void upImage(int userId, String sessionId, File file) {
        myModelInter.toUpImage("small/user/verify/v1/modifyHeadPic", userId, sessionId, file, new MyModels.MyTwoCallBack() {
            @Override
            public void success(Object object) {
                if (object instanceof ImageBean) {
                    ImageBean imageBean = (ImageBean) object;
                    ContractInterface.MyDataInter myDataInter = (ContractInterface.MyDataInter) viewa;
                    myDataInter.MyUpImage(imageBean);

                }
            }
        });
    }

    //取消订单或者删除订单
    @Override
    public void Cancle(int userId, String sessionId, String id) {
         myModelInter.toCancle("small/order/verify/v1/deleteOrder", userId, sessionId, id, new MyModels.MySuanCallBack() {
             @Override
             public void success(Object object) {
                     String str= (String) object;
                     ContractInterface.MyOrderInter myOrderInter= (ContractInterface.MyOrderInter) viewa;
                     myOrderInter.MyCancle(str);
             }
         });
    }
   //取支付订单
    @Override
    public void Pay(int userId, String sessionId, String id, int type) {
         myModelInter.tOPay("small/order/verify/v1/pay", userId, sessionId, id, type, new MyModels.MySuanCallBack() {
             @Override
             public void success(Object object) {
                 String str= (String) object;
                 ContractInterface.MyPayInter myPayInter= (ContractInterface.MyPayInter) viewa;
                 myPayInter.MyPay(str);
             }
         });
    }
}
