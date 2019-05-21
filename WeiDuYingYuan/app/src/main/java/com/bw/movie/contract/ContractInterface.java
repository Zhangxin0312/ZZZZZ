package com.bw.movie.contract;

/**
 * @Author：dell
 * @E-mail： 15001194794@163.com
 * @Date：2019/5/10 19:41
 * @Description：描述信息
 */
public interface ContractInterface {
    //登录view
    public interface LoginViewInterface{
        public void showLogin(Object o);
    }
    //注册view
    public interface RegViewInterface{
        public void showReg(Object o);
    }
    //p层
    public interface PresenterInterface{
        public void toLogin(String phone,String pwd);
        public void toRegist(String phone,String pwd,String nickName,String birthday,String email,int sex,String pwd2);
    }


}
