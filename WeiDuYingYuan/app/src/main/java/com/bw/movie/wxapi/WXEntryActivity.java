package com.bw.movie.wxapi;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.bean.WXBean;
import com.bw.movie.contract.ContractInter;
import com.bw.movie.presenter.Presenter;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXEntryActivity extends Activity  implements IWXAPIEventHandler,ContractInter.WXInter{

    IWXAPI api;
    private  static  String APP_ID="wxb3852e6a6b7d9516";
    ContractInter.PresenterInter presenterInter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxentry);
        presenterInter=new Presenter<>(this);
        regToWx();
    }
    private  void regToWx() {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, APP_ID, true);

        // 将应用的appId注册到微信
        api.registerApp(APP_ID);
        api.handleIntent(getIntent(), this);
    }
    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
       if(baseResp.getType()==ConstantsAPI.COMMAND_SENDAUTH){
           SendAuth.Resp resp= (SendAuth.Resp) baseResp;
           String code=  resp.code;
           //去登陆
           presenterInter.WXLogin(code);

       }

    }
      //微信登录的返回值
    @Override
    public void MyWX(WXBean wxBean) {
        Toast.makeText(WXEntryActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
        this.finish();
    }
}
