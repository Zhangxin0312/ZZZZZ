package com.bw.movie.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.bw.movie.bean.ZhifubaoBean;
import com.bw.movie.custom.MyView;
import com.bw.movie.R;
import com.bw.movie.bean.TicketBean;
import com.bw.movie.app.App;
import com.bw.movie.bean.WeixinBean;
import com.bw.movie.contract.ContractInter;
import com.bw.movie.presenter.Presenter;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.xml.transform.Result;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TicketActivity extends AppCompatActivity  implements  ContractInter.MyTicketInter{
    @BindView(R.id.ting_id)
        TextView ting_id;
        @BindView(R.id.myView)
        com.bw.movie.custom.MyView myView;
        @BindView(R.id.price_id)
        TextView price_id;
        @BindView(R.id.ticket_back)
        ImageView ticket_back;
        @BindView(R.id.ticket_id)
        ImageView ticket_id;
        TicketActivity activity;
        ContractInter.PresenterInter  presenterInter;
        PopupWindow  pop;
       int count=0;
        TicketBean ticketBean;
         private  static  String APP_ID="wxb3852e6a6b7d9516";
     IWXAPI api;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            Result result = (Result) msg.obj;
                Toast.makeText(TicketActivity.this, result.getSystemId(), Toast.LENGTH_LONG).show();
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        ButterKnife.bind(this);
        regToWx();
        presenterInter=new Presenter<>(this);
        activity=this;
        Intent intent = getIntent();
        String start = intent.getStringExtra("start");
        String end = intent.getStringExtra("end");
        String hall = intent.getStringExtra("hall");
        final double price = intent.getDoubleExtra("price",0);
        final int id = intent.getIntExtra("id", 0);
        //时间
        SimpleDateFormat format=new SimpleDateFormat("MM-dd");
        Date date=new Date(System.currentTimeMillis());
        //开始时间
        ting_id.setText(format.format(date)+"     "+start+"--"+end+"      "+hall);
        myView.setData(8,8);
        myView.setMaxSelected(4);
        //返回
        ticket_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 activity.finish();
            }
        });
        myView.setScreenName(hall);
        myView.setSeatChecker(new MyView.SeatChecker() {
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
                price_id.setText(count*price+"");
            }

            @Override
            public void unCheck(int row, int column) {
                count--;
                price_id.setText(count*price+"");
            }

            @Override
            public String[] checkedSeatTxt(int row, int column) {
                return new String[0];
            }
        });
        //购票下单
        ticket_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //购票下单
                int userId=App.UserId;
                String  str=userId+""+id+count+"movie";
                String sign = md5Decode(str);
                presenterInter.buyTicket(id,count,sign);


                View view=View.inflate(TicketActivity.this,R.layout.to_ticket_view,null);
                ImageView ticket_back_id=view.findViewById(R.id.ticket_back_id);
                final RadioButton zhifubao_id=view.findViewById(R.id.zhifubao_id);
                final RadioButton weixin_id=view.findViewById(R.id.weixin_id);
                final Button sum_id=view.findViewById(R.id.sum_id);
                sum_id.setText("微信支付"+count*price);
                pop = new PopupWindow(view,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT,true);
                //关闭popupWindow
                ticket_back_id.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pop.dismiss();
                    }
                });
                weixin_id.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                       if(weixin_id.isChecked()){
                           sum_id.setText("微信支付"+count*price);
                       }

                    }
                });
                zhifubao_id.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                      if(zhifubao_id.isChecked()){
                          sum_id.setText("支付宝支付"+count*price);
                      }
                    }
                });
                sum_id.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(weixin_id.isChecked()){
                            String orderId = ticketBean.getOrderId();
                            //微信支付
                            presenterInter.toWX(1,orderId);

                        }else
                        {
                            String orderId = ticketBean.getOrderId();
                            //支付宝支付
                            presenterInter.toZFB(2,orderId);
                        }
                    }

                });
                pop.setBackgroundDrawable(new BitmapDrawable());
                pop.setOutsideTouchable(true);
                pop.showAtLocation(view,Gravity.CENTER_HORIZONTAL,0,100);
            }
        });
    }
    //MD5加密
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

    //购票下单
    @Override
    public void MyTicket(TicketBean ticketBean) {
        this.ticketBean=ticketBean;
        Toast.makeText(TicketActivity.this,ticketBean.getMessage(),Toast.LENGTH_SHORT).show();
    }
       //微信支付的返回值
    @Override
    public void MyWX(WeixinBean weixinBean) {
        PayReq request = new PayReq();
        request.appId = "wxb3852e6a6b7d9516";
        request.partnerId = weixinBean.getPartnerId();
        request.prepayId= weixinBean.getPrepayId();
        request.packageValue = weixinBean.getPackageValue();
        request.nonceStr= weixinBean.getNonceStr();
        request.timeStamp=weixinBean.getTimeStamp();
        request.sign= weixinBean.getSign();
        request.extData = "app data";

        api.sendReq(request);
    }
    //支付宝支付的返回值
    @Override
    public void MyZFB(ZhifubaoBean zhifubaoBean) {
        final String orderInfo = zhifubaoBean.getResult();   // 订单信息
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(TicketActivity.this);
                Map<String,String> result = alipay.payV2(orderInfo,true);

                Message msg = new Message();
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    private  void regToWx() {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, APP_ID, true);

        // 将应用的appId注册到微信
        api.registerApp(APP_ID);
    }
}
