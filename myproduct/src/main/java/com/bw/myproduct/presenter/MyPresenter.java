package com.bw.myproduct.presenter;

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
import com.bw.myproduct.model.MyModel;

public class MyPresenter<T>  implements Contract.MyPresenterInterface {
    Contract.MyModelInterface myModelInterface;
    T  views;
    public  MyPresenter(T view){
        myModelInterface=new MyModel();
        this.views=view;
    }
    //登录
    @Override
    public void Login(String phone, String pwd) {
        myModelInterface.toLogin("http://172.17.8.100/small/user/v1/login",phone,pwd, new MyModel.MyCallBack() {
            @Override
            public void success(Object object) {
               if(object instanceof LoginBean){
                   LoginBean loginBean= (LoginBean) object;
                   Contract.MyLoginInterface myLoginInterface= (Contract.MyLoginInterface) views;
                   myLoginInterface.Logins(loginBean);
               }

            }
        });
    }
    //注册
    @Override
    public void Regist(String phone, String pwd) {
        myModelInterface.toRegist("http://172.17.8.100/small/user/v1/register",phone,pwd , new MyModel.MyCallBack() {
            @Override
            public void success(Object object) {
                String str= (String) object;
                Contract.MyRegistInterface myRegistInterface= (Contract.MyRegistInterface) views;
                myRegistInterface.Regists(str);
            }
        });
    }
    //Banner
    @Override
    public void Ban() {
        myModelInterface.toBan("http://172.17.8.100/small/commodity/v1/bannerShow", new MyModel.MyCallBack() {
            @Override
            public void success(Object object) {
                if(object instanceof Banners){
                    Banners banners= (Banners) object;
                    Contract.MyShowInterface myShowInterface= (Contract.MyShowInterface) views;
                    myShowInterface.MyBan(banners.getResult());
                }
            }
        });
    }
    //首页
    @Override
    public void ShouYe() {
        myModelInterface.toShouYe("http://172.17.8.100/small/commodity/v1/commodityList", new MyModel.ShowCallBack() {
            @Override
            public void success(Object object) {
                 if(object instanceof ShouYe){
                     ShouYe shouYe= (ShouYe) object;
                     Contract.MyShowInterface myShowInterface= (Contract.MyShowInterface) views;
                     myShowInterface.MyShouYe(shouYe);
                 }
            }
        });
    }
      //搜索的展示
    @Override
    public void Sou(String content, int page) {
        myModelInterface.toSou("http://172.17.8.100/small/commodity/v1/findCommodityByKeyword", content, page, new MyModel.MyXiangBacks() {
            @Override
            public void success(Object object) {
                if(object instanceof Sou){
                    Sou sou= (Sou) object;
                    Contract.MySouInterface mySouInterface= (Contract.MySouInterface) views;
                    mySouInterface.MySous(sou.getResult());
                }
            }

            @Override
            public void error(String str) {
                Contract.MySouInterface mySouInterface= (Contract.MySouInterface) views;
                mySouInterface.MySouError(str);
            }
        });
    }
      //popwindow的一级列表
    @Override
    public void Pop() {
        myModelInterface.toPop("http://172.17.8.100/small/commodity/v1/findFirstCategory", new MyModel.MyCallBacks() {
            @Override
            public void success(Object object) {
                  if(object instanceof Pop){
                      Pop pop= (Pop) object;
                      Contract.MyShowInterface myShowInterface= (Contract.MyShowInterface) views;
                      myShowInterface.MyPop(pop.getResult());
                  }
            }
        });
    }
    //popwindow的二级列表
    @Override
    public void Pop_2(String id) {
        myModelInterface.toPop_2("http://172.17.8.100/small/commodity/v1/findSecondCategory", id, new MyModel.MyCallBacks() {
            @Override
            public void success(Object object) {
               if(object  instanceof Pop_2){
                   Pop_2 pop_2= (Pop_2) object;
                   Contract.MyShowInterface myShowInterface= (Contract.MyShowInterface) views;
                   myShowInterface.MyPop2(pop_2.getResult());
               }
            }
        });
    }
    //详情
    @Override
    public void Xiang(int id,int userId,String  sessionId) {
        myModelInterface.toXiang("http://172.17.8.100/small/commodity/v1/findCommodityDetailsById",id,userId,sessionId, new MyModel.MyXiangBacks(){
            @Override
            public void success(Object object) {
                 if(object instanceof XiangBean){
                     XiangBean xiangBean= (XiangBean) object;
                     Contract.MyXiangInterface myXiangInterface= (Contract.MyXiangInterface) views;
                     myXiangInterface.MyXiang(xiangBean);
                 }
            }
            //详情失败
            @Override
            public void error(String str) {
                Contract.MyXiangInterface myXiangInterface= (Contract.MyXiangInterface) views;
                myXiangInterface.MyXiangs(str);
            }
        });
    }
    //评论
    @Override
    public void Ping(int id, int page) {
        myModelInterface.toPing("http://172.17.8.100/small/commodity/v1/CommodityCommentList",id, page, new MyModel.MyCallBacks() {
            @Override
            public void success(Object object) {
                if(object instanceof PingBean){
                    PingBean pingBean= (PingBean) object;
                    Contract.MyPingInterface myPingInterface= (Contract.MyPingInterface) views;
                    myPingInterface.MyPing(pingBean.getResult());
                }
            }
        });
    }
    //圈子
    @Override
    public void Quan(int page,int userId, String sessionId) {
        myModelInterface.toQuan("http://172.17.8.100/small/circle/v1/findCircleList",userId, sessionId, page, new MyModel.MyCallBacks() {
            @Override
            public void success(Object object) {
                if(object instanceof QuanBean){
                    QuanBean quanBean= (QuanBean) object;
                    Contract.MyQuanInterface myQuanInterface= (Contract.MyQuanInterface) views;
                    myQuanInterface.MyQuan(quanBean.getResult());
                }
            }
        });
    }
    //加入购物车
    @Override
    public void Add(int userId, String sessionId, String data) {
        myModelInterface.toAdd("http://172.17.8.100/small/order/verify/v1/syncShoppingCart", userId, sessionId, data, new MyModel.MyCallBack() {
            @Override
            public void success(Object object) {
                    String str= (String) object;
                    Contract.MyXiangInterface myXiangInterface= (Contract.MyXiangInterface) views;
                    myXiangInterface.MyAdd(str);
            }
        });
    }

}
