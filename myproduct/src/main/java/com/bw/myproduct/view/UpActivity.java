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

public class UpActivity extends AppCompatActivity implements ContractInterface.MyUpInter {
    EditText up_name,up_phone,up_address,up_bian;
    Button up_btn;
    ContractInterface.MyPresenterInter myPresenterInter;
    private int userId;
    private String sessionId;
    private String address;
    private int id;
    private String name;
    private String phone;
    private String zipCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up);

        up_btn=findViewById(R.id.up_btn);
        up_name=findViewById(R.id.up_name);
        up_phone=findViewById(R.id.up_phone);
        up_address=findViewById(R.id.up_address);
        up_bian=findViewById(R.id.up_bian);
        //得到原先地址的值
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        address = intent.getStringExtra("address");
        name = intent.getStringExtra("name");
        phone = intent.getStringExtra("phone");
        zipCode = intent.getStringExtra("zipCode");
        //赋值上去
        up_address.setText(address);
        up_name.setText(name);
        up_bian.setText(zipCode);
        up_phone.setText(phone);
        //获取userId和sessionId
        SharedPreferences sp = getSharedPreferences("share", MODE_PRIVATE);
        userId = sp.getInt("userId", 0);
        sessionId = sp.getString("sessionId", null);
        //实例化对象
        myPresenterInter=new MyPresens<>(this);
        initData();

    }

    private void initData() {
        //点击修改的时候  获取每一个参数的值  进行修改
       up_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String phone1 = up_phone.getText().toString();
               String address1 = up_address.getText().toString();
               String bian1 = up_bian.getText().toString();
               String name1 = up_name.getText().toString();
               myPresenterInter.Update(userId,sessionId,id,name1,phone1,address1,bian1);
           }
       });
    }

    @Override
    public void MyUp(String str) {
        Toast.makeText(UpActivity.this,str,Toast.LENGTH_SHORT).show();
        if(str.equals("修改成功")){
            //让这个页面销毁
            this.finish();
            //如果修改成功  跳转地址列表页面
            Intent intent=new Intent(UpActivity.this,AddressListActivity.class);
            startActivity(intent);
        }
    }


}
