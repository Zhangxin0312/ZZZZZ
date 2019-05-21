package com.bw.myproduct.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.myproduct.R;
import com.bw.myproduct.contract.ContractInterface;
import com.bw.myproduct.presenter.MyPresens;

public class InsertAddressActivity extends AppCompatActivity implements ContractInterface.MyAddInter {
     EditText shou_name,shou_phone,shou_address,shou_bian;
     Button shou_add;
    private SharedPreferences sp;
    private String sessionId;
    private int userId;
    ContractInterface.MyPresenterInter myPresenterInter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        //添加收货地址的Activity
        shou_name=findViewById(R.id.shou_name);
        shou_phone=findViewById(R.id.shou_phone);
        shou_address=findViewById(R.id.shou_address);
        shou_bian=findViewById(R.id.shou_bian);
        shou_add=findViewById(R.id.shou_add);
        myPresenterInter=new MyPresens<>(this);
        sp = getSharedPreferences("share", MODE_PRIVATE);
        sessionId = sp.getString("sessionId", null);
        userId = sp.getInt("userId", 0);
        initData();
    }
        //添加收货地址
        private void initData() {
            shou_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //获取所有内容  去获取添加的参数
                    String name = shou_name.getText().toString();
                    String phone = shou_phone.getText().toString();
                    String address = shou_address.getText().toString();
                    String bian = shou_bian.getText().toString();
                    //添加地址
                    myPresenterInter.Add(userId,sessionId,name,phone,address,bian);
                }
            });
    }

    @Override
    public void MyAdd(String str) {
        //添加地址
        Toast.makeText(InsertAddressActivity.this,str,Toast.LENGTH_SHORT).show();
         if(str.equals("添加成功")){
             //让添加地址的页面消失
              this.finish();
              //跳转收货地址列表  显示有新增数据的页面
              Intent intent=new Intent(InsertAddressActivity.this,AddressListActivity.class);
              startActivity(intent);
         }
    }
}
