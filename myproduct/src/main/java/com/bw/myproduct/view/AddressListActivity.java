package com.bw.myproduct.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.myproduct.R;
import com.bw.myproduct.adapter2.MyAddAdapter;
import com.bw.myproduct.beans.MyAddressBean;
import com.bw.myproduct.contract.ContractInterface;
import com.bw.myproduct.presenter.MyPresens;

import java.util.ArrayList;
import java.util.List;

public class AddressListActivity extends AppCompatActivity implements ContractInterface.MyAddressInter {
      RecyclerView add_rlv;
      int ids=0;
      Button add_id;
    private MyAddAdapter myAddAdapter;
    List<MyAddressBean.ResultBean> mlist=new ArrayList<>();
    ContractInterface.MyPresenterInter myPresenterInter;
    private int userId;
    private String sessionId;
    TextView succ;
    private int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);
        Intent intent = getIntent();
        flag = intent.getIntExtra("flag", 0);
        //收货地址列表
        add_rlv=findViewById(R.id.add_rlv);
        add_id=findViewById(R.id.add_id);
        succ=findViewById(R.id.succ);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        add_rlv.setLayoutManager(layoutManager);
        //得到userid和sessionId
        SharedPreferences sp = getSharedPreferences("share", MODE_PRIVATE);
        userId = sp.getInt("userId", 0);
        sessionId = sp.getString("sessionId", null);
        //实例化对象
        myPresenterInter=new MyPresens<>(this);
        //收货地址列表
        myPresenterInter.Address(userId, sessionId);
        //展示收货地址
        myAddAdapter = new MyAddAdapter(AddressListActivity.this,mlist);
        //添加收货地址
        add_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //让本页面消失  把AddressListActivity设置为栈顶模式，让添加后的地址显示
                AddressListActivity.this.finish();
                Intent intent=new Intent(AddressListActivity.this,InsertAddressActivity.class);
                startActivity(intent);
            }
        });
        add_rlv.setAdapter(myAddAdapter);
        //点击成功  返回提交订单的页面
        succ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==1){
                    Intent intent=new Intent(AddressListActivity.this,SuanActivity.class);
                    intent.putExtra("id",ids);
                    startActivity(intent);
                    AddressListActivity.this.finish();
                }else
                {
                    AddressListActivity.this.finish();
                }

            }
        });
    }

    @Override
    public void MyAddress(List<MyAddressBean.ResultBean> list) {
        //添加地址的返回值
         mlist.clear();
         mlist.addAll(list);
         myAddAdapter.notifyDataSetChanged();
    }
    @Override
    public void MyDeault(String str) {
        //请求默认地址的返回值
        Toast.makeText(AddressListActivity.this,str,Toast.LENGTH_SHORT).show();
    }
    public void getAddId(int id) {
          //得到默认地址的id
        this.ids=id;
        myPresenterInter.Default(userId,sessionId,id);
        //将默认地址进行保存
        SharedPreferences share = getSharedPreferences("share", MODE_PRIVATE);
        SharedPreferences.Editor edit = share.edit();
        edit.putInt("addressId",ids);
        edit.commit();
    }

    public void toUpdate(int id, String address, String name, String phone, String zipCode) {
             this.finish();
            //修改收货地址
             Intent intent=new Intent(AddressListActivity.this,UpActivity.class);
            intent.putExtra("id",id);
            intent.putExtra("address",address);
            intent.putExtra("name",name);
            intent.putExtra("phone",phone);
            intent.putExtra("zipCode",zipCode);
            startActivity(intent);
    }
    public  void MyDel(){
       //删除修改地址
    }
}
