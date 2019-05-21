package com.bw.myproduct.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.bw.myproduct.R;
import com.bw.myproduct.contract.ContractInterface;
import com.bw.myproduct.presenter.MyPresens;

public class PayActivity extends AppCompatActivity implements ContractInterface.MyPayInter {
    CheckBox bao_cb, wei_cb, yue_cb;
    Button pay_btn;
    private int userId;
    private String sessionId;
    int payType=0,value;
    ContractInterface.MyPresenterInter myPresenterInter;
    private String orderId;
     Button back_id,look_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        //得到总价和订单id
        Intent intent = getIntent();
        value = intent.getIntExtra("values", 0);
        orderId = intent.getStringExtra("orderId" );
        Log.e("tag","YYYYYYYYYYYYYYYYYYYYYYY"+value);
        bao_cb = findViewById(R.id.bao_cb);
        wei_cb = findViewById(R.id.wei_cb);
        yue_cb = findViewById(R.id.yue_cb);
        pay_btn = findViewById(R.id.pay_btn);
        //实例化对象
        myPresenterInter=new MyPresens<>(this);
        //获取userId和sessionId
        SharedPreferences sp = getSharedPreferences("share", MODE_PRIVATE);
        userId = sp.getInt("userId", 0);
        sessionId = sp.getString("sessionId", null);
        //如果余额被选中  就显示按钮
        yue_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(yue_cb.isChecked()){
                    pay_btn.setVisibility(View.VISIBLE);
                    pay_btn.setText("余额支付" + value + "元");
                    Log.e("tag","CCCCCCCCCCCCCCCCC"+value);
                    payType=1;
                    wei_cb.setChecked(false);
                    bao_cb.setChecked(false);
                }else
                {
                     yue_cb.setChecked(false);
                     pay_btn.setVisibility(View.GONE);
                }
            }
        });
        //如果微信被选中  就显示按钮
        wei_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(wei_cb.isChecked()){
                    pay_btn.setVisibility(View.VISIBLE);
                    pay_btn.setText("微信支付" + value + "元");
                    payType=3;
                    yue_cb.setChecked(false);
                    bao_cb.setChecked(false);
                }else
                {
                    yue_cb.setChecked(false);
                    pay_btn.setVisibility(View.GONE);
                }

            }
        });
        //如果支付宝被选中  就显示按钮
        bao_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bao_cb.isChecked()){
                    pay_btn.setVisibility(View.VISIBLE);
                    pay_btn.setText("支付宝支付" + value + "元");
                    payType=2;
                    wei_cb.setChecked(false);
                    yue_cb.setChecked(false);
                }else
                {
                    yue_cb.setChecked(false);
                    pay_btn.setVisibility(View.GONE);
                }

            }
        });
        //点击支付按钮的时候去支付
        pay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myPresenterInter.Pay(userId,sessionId, orderId,payType);
            }
        });
    }

    @Override
    public void MyPay(String str) {
        Toast.makeText(PayActivity.this,str,Toast.LENGTH_SHORT).show();
        if(str.equals("支付成功")){
            View view=View.inflate(PayActivity.this,R.layout.pay_success,null);
            PopupWindow pop=new PopupWindow(view,LinearLayoutCompat.LayoutParams.MATCH_PARENT,LinearLayoutCompat.LayoutParams.MATCH_PARENT);
            back_id=view.findViewById(R.id.back_id);
            look_id=view.findViewById(R.id.look_id);
            back_id.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //返回首页
                    Intent intent=new Intent(PayActivity.this,ShowActivity.class);
                      startActivity(intent);
                }
            });
            look_id.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //查看订单
                     finish();
                }
            });
            pop.showAtLocation(view,Gravity.CENTER,0,0);
        }else
        {
            View view=View.inflate(PayActivity.this,R.layout.pay_error,null);
            final PopupWindow pop=new PopupWindow(view,LinearLayoutCompat.LayoutParams.MATCH_PARENT,LinearLayoutCompat.LayoutParams.MATCH_PARENT);
            Button  next_id =view.findViewById(R.id.next_id);
            next_id.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pop.dismiss();
                }
            });
            pop.showAtLocation(view,Gravity.CENTER,0,0);
        }

    }
}
