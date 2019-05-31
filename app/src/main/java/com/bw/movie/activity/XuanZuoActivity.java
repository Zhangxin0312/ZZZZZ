package com.bw.movie.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.app.App;
import com.bw.movie.bean.XiaDanBean;
import com.bw.movie.bean.ZhiFuBean;
import com.bw.movie.contract.ContractInterface;
import com.bw.movie.custom.MyView;
import com.bw.movie.presenter.MyPresenter;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.xml.transform.Result;

import butterknife.BindView;
import butterknife.ButterKnife;

public class XuanZuoActivity extends AppCompatActivity implements ContractInterface.XiaViewInterface {

    @BindView(R.id.gou_text_shi)
    TextView gouTextShi;
    @BindView(R.id.gou_text_shi_end)
    TextView gouTextShiEnd;
    @BindView(R.id.gou_text_ying)
    TextView gouTextYing;

    @BindView(R.id.PayMoney)
    TextView PayMoney;
    @BindView(R.id.PayTrue)
    ImageView PayTrue;
    @BindView(R.id.PayFalse)
    ImageView PayFalse;

    int s = 0;
    ImageView image_qu;
    TextView text_sum;
    RadioButton radio_wei, radio_zhi;
    int type;
    PopupWindow popupWindow;
    @BindView(R.id.movie_setview)
    MyView movieSetview;
    private int count=0;
    ContractInterface.PresenterInterface presenterInterface;
    String orderId;
    private  static  String APP_ID="wxb3852e6a6b7d9516";
    IWXAPI api;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            Result result = (Result) msg.obj;
            Toast.makeText(XuanZuoActivity.this, result.getSystemId(), Toast.LENGTH_LONG).show();
        };
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuan_zuo);
        presenterInterface=new MyPresenter<>(this);
        ButterKnife.bind(this);
        gouTextShi.setText(App.Chentime);
        final TextView Money = findViewById(R.id.PayMoney);
        gouTextShiEnd.setText(App.Chentimeend);
        gouTextYing.setText(App.Chentitle);
        movieSetview.setScreenName(App.Chentitle);
        movieSetview.setMaxSelected(4);
        movieSetview.setSeatChecker(new MyView.SeatChecker() {
            @Override
            public boolean isValidSeat(int row, int column) {
                if(row==4){
                    return false;
                }
                return true;
            }

            @Override
            public boolean isSold(int row, int column) {
                if(row==6&&column==6){
                    return true;
                }
                return false;
            }

            @Override
            public void checked(int row, int column) {
                count++;
                Money.setText(count*App.Chenprice+"");
            }

            @Override
            public void unCheck(int row, int column) {
                count--;
                Money.setText(count*App.Chenprice+"");
            }

            @Override
            public String[] checkedSeatTxt(int row, int column) {
                return new String[0];
            }
        });
        //int s = App.DrawSeat / 10;
        Log.e("tag", s + "");
        movieSetview.setData(5, 10);
        init();
    }
    public String md5Decode(String content) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(content.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("NoSuchAlgorithmException",e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UnsupportedEncodingException", e);
        }
        //对生成的16字节数组进行补零操作
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10){
                hex.append("0");
            }
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }
    private void init() {

        PayFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XuanZuoActivity.this, RecommendXiangActivity.class);
                startActivity(intent);
                finish();
            }
        });
        PayTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = View.inflate(XuanZuoActivity.this, R.layout.item_zhifu, null);
//                String signMd = App.userId+""+App.guGouId+""+s+"movie";
//                String sign =  md5Decode(signMd);
//

                String signMd = App.UserId+""+App.MovieId+""+count+"movie";
                String sign =  md5Decode(signMd);
                presenterInterface.toXiaDan(App.MovieId,count,sign);
                image_qu = view.findViewById(R.id.image_zhufu_qu);
                text_sum = view.findViewById(R.id.text_sum);
                radio_wei = view.findViewById(R.id.radio_weixin);
                radio_zhi = view.findViewById(R.id.radio_zhufubao);
                popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, false);
                popupWindow.showAsDropDown(view);
                popupWindow.setFocusable(true);

                radio_wei.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        type = 1;
                        text_sum.setText("微信支付" + s * App.Chenprice + "元");


                    }
                });
                radio_zhi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        type = 2;
                        text_sum.setText("支付宝支付" + s * App.Chenprice + "元");
                    }
                });
                image_qu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                text_sum.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(type == 1){
                            presenterInterface.toZhiFu(type,orderId);
                            popupWindow.dismiss();
                        }else{
//                            presenterIntface.toZhi(type,orderId);
//                            Thread payThread = new Thread(payRunnable);
//                            payThread.start();
//                            popupWindow.dismiss();
                        }
                    }
                });
            }
        });

    }

    @Override
    public void showXiaDan(Object o) {
        XiaDanBean xiaDanBean= (XiaDanBean) o;
        orderId=xiaDanBean.getOrderId();
        Toast.makeText(this,(String)o,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showWei(Object o) {
        ZhiFuBean zhiFuBean= (ZhiFuBean) o;
        PayReq request = new PayReq();
        request.appId = "wxb3852e6a6b7d9516";
        request.partnerId = zhiFuBean.getPartnerId();
        request.prepayId= zhiFuBean.getPrepayId();
        request.packageValue = zhiFuBean.getPackageValue();
        request.nonceStr= zhiFuBean.getNonceStr();
        request.timeStamp=zhiFuBean.getTimeStamp();
        request.sign= zhiFuBean.getSign();
        request.extData = "app data";

        api.sendReq(request);
    }
}
