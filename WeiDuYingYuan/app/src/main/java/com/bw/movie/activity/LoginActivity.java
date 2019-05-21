package com.bw.movie.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.Entity.EncryptUtil;
import com.bw.movie.R;
import com.bw.movie.app.App;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.contract.ContractInterface;
import com.bw.movie.presenter.MyPresenter;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements ContractInterface.LoginViewInterface {
    SharedPreferences.Editor edit;
    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.edit_pwd)
    EditText editPwd;
    @BindView(R.id.btn_regist)
    TextView btnRegist;
    @BindView(R.id.btn_login)
    Button btnLogin;
    ContractInterface.PresenterInterface presenterInterface;
    @BindView(R.id.eye_id)
    ImageView eyeId;
    @BindView(R.id.jzma_id)
    CheckBox jzmaId;
    @BindView(R.id.auto_id)
    CheckBox auto_id;
    @BindView(R.id.wxLogin)
    ImageView wxLogin;

    boolean isHideFirst=true;
    private SharedPreferences sp;
    // APP_ID 替换为你的应用从官方网站申请到的合法appID
    private static final String APP_ID ="wxb3852e6a6b7d9516";
    // IWXAPI 是第三方app和微信通信的openApi接口
    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenterInterface = new MyPresenter<>(this);
        ButterKnife.bind(this);


        sp = PreferenceManager.getDefaultSharedPreferences(this);
        if(sp.getBoolean("rember",false) && sp.getBoolean("automaticLogin",false)){
            String phone1 = sp.getString("phone", null);
            String pwd1 = sp.getString("pwd", null);
            editPhone.setText(phone1);
            editPwd.setText(pwd1);
            jzmaId.setChecked(true);
            presenterInterface.toLogin(phone1,pwd1);
        }
        if(sp.getBoolean("rember",false)){
            String rphone = sp.getString("phone", null);
            String rpwd = sp.getString("pwd", null);
            String decrpty = EncryptUtil.decrypt(rpwd);

            editPhone.setText(rphone);
            editPwd.setText(decrpty);
            jzmaId.setChecked(true);
        }else{
            editPhone.setText(null);
            editPwd.setText(null);
            jzmaId.setChecked(false);
        }
        init();

        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistActivity.class);
                startActivity(intent);
            }
        });
        eyeId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isHideFirst==true){
                    HideReturnsTransformationMethod instance=HideReturnsTransformationMethod.getInstance();
                    editPwd.setTransformationMethod(instance);
                    isHideFirst=false;

                }else{
                    PasswordTransformationMethod instance=PasswordTransformationMethod.getInstance();
                    editPwd.setTransformationMethod(instance);

                    isHideFirst=true;
                }
                int index=editPwd.getText().toString().length();
                editPwd.setSelection(index);
            }
        });
         regToWx();
        //微信登录
        InitData();

    }
    private  void regToWx() {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, APP_ID, true);

        // 将应用的appId注册到微信
        api.registerApp(APP_ID);
    }
    public void init() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit = sp.edit();
                String loginPhone = editPhone.getText().toString().trim();
                String loginPwd = editPwd.getText().toString().trim();
                String miPwd = EncryptUtil.encrypt(loginPwd);

                boolean isCk = jzmaId.isChecked();
                boolean automaticLogin = auto_id.isChecked();

                if (automaticLogin) {
                    edit.putBoolean("automaticLogin", true);
                    edit.commit();
                }
                if (isCk) {
                    edit.putBoolean("rember", true);
                    edit.putString("phone", loginPhone);
                    edit.putString("pwd", miPwd);
                    edit.commit();
                } else {
                    edit.putBoolean("rember", false);
                    edit.putString("phone", null);
                    edit.putString("pwd", null);
                    edit.commit();
                }
                if(Utils.isFastClick()){
                    //to do sth
                    presenterInterface.toLogin(loginPhone, miPwd);
                }


            }
    });
}

    @Override
    public void showLogin(Object o) {

        LoginBean loginBean = (LoginBean) o;
        LoginBean.ResultBean.UserInfoBean userInfo = loginBean.getResult().getUserInfo();
        if (loginBean.getMessage().equals("登陆成功")) {
            App.UserId = loginBean.getResult().getUserId();
            App.SessionId = loginBean.getResult().getSessionId();
            App.Phone = editPhone.getText().toString();
            App.Pwd = editPwd.getText().toString();
            App.Birthday =userInfo.getBirthday();
            App.HeadPic = userInfo.getHeadPic();
            App.NickName = userInfo.getNickName();
            App.LastLoginTime = userInfo.getLastLoginTime();
            App.Sex = userInfo.getSex();


            Intent intent = new Intent(LoginActivity.this, ShowActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
        }

    }
    //微信登录
    public void InitData() {
       wxLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               // send oauth request
               SendAuth.Req req = new SendAuth.Req();
               req.scope = "snsapi_userinfo";
               req.state = "wechat_sdk_demo_test";
               api.sendReq(req);

           }
       });
    }
    public static class Utils {
        // 两次点击按钮之间的点击间隔不能少于1000毫秒
        private static final int MIN_CLICK_DELAY_TIME = 1000;
        private static long lastClickTime;

        public static boolean isFastClick() {
            boolean flag = false;
            long curClickTime = System.currentTimeMillis();
            if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
                flag = true;
            }
            lastClickTime = curClickTime;
            return flag;
        }
    }

}
