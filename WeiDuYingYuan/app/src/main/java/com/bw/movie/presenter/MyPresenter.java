package com.bw.movie.presenter;

import com.bw.movie.contract.ContractInterface;
import com.bw.movie.model.MyModel;

/**
 * @Author：dell
 * @E-mail： 15001194794@163.com
 * @Date：2019/5/10 19:43
 * @Description：描述信息
 */
public class MyPresenter<T> implements ContractInterface.PresenterInterface {
    T tt;
    MyModel myModel;

    public MyPresenter(T tt) {
        this.tt = tt;
        myModel=new MyModel();
    }

    @Override
    public void toLogin(String phone, String pwd) {
        myModel.setMyCallBack(new MyModel.MyCallBack() {
            @Override
            public void success(Object o) {
                ContractInterface.LoginViewInterface viewInterface= (ContractInterface.LoginViewInterface) tt;
                viewInterface.showLogin(o);
            }
        });
        myModel.postRequest(phone,pwd);
    }

    @Override
    public void toRegist(String phone, String pwd, String nickName, String birthday, String email, int sex, String pwd2) {
        myModel.setMyCallBack1(new MyModel.MyCallBack1() {
            @Override
            public void success(Object o) {
                ContractInterface.RegViewInterface regViewInterface= (ContractInterface.RegViewInterface) tt;
                regViewInterface.showReg(o);
            }
        });
        myModel.postRequestt(phone, pwd,nickName,birthday,email,sex,pwd2);
    }


}
