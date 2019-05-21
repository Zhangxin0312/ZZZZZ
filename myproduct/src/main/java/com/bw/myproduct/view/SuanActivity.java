package com.bw.myproduct.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bw.myproduct.R;
import com.bw.myproduct.adapter2.SuanAdapter;
import com.bw.myproduct.beans.GouBeans;
import com.bw.myproduct.contract.ContractInterface;
import com.bw.myproduct.greendao.gen.DaoMaster;
import com.bw.myproduct.greendao.gen.DaoSession;
import com.bw.myproduct.greendao.gen.GouBeansDao;
import com.bw.myproduct.presenter.MyPresens;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SuanActivity extends AppCompatActivity  implements  ContractInterface.MySuanInter{
      Button commit_id,add_btn;
      RecyclerView suan_recyclerView;
      List<GouBeans>  mlist=new ArrayList<>();
      ContractInterface.MyPresenterInter  myPresenterInter;
      int sum,id=0,addressId,userId;
      GouBeansDao beansDao;
      String sessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suan);
        suan_recyclerView=findViewById(R.id.suan_recyclerView);
        myPresenterInter=new MyPresens<>(this);
        //查询数据库里的数据
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "db");
        SQLiteDatabase database = helper.getReadableDatabase();
        DaoMaster daoMaster=new DaoMaster(database);
        DaoSession daoSession = daoMaster.newSession();
        beansDao = daoSession.getGouBeansDao();
        //计算总价
        sum = 0;
        for (GouBeans beans: beansDao.loadAll()) {
            mlist.add(beans);
            int price = beans.getPrice();
            int num = beans.getNum();
            sum+=(price*num);
        }
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        suan_recyclerView.setLayoutManager(layoutManager);
        //展示数据
        SuanAdapter suanAdapter=new SuanAdapter(SuanActivity.this,mlist);
        suan_recyclerView.setAdapter(suanAdapter);
        initData();
        initDatas();
    }
    private void initData() {
        //提交订单
        commit_id=findViewById(R.id.commit_id);
        //获取userId和sessionId
        SharedPreferences sp = getSharedPreferences("share", MODE_PRIVATE);
        userId = sp.getInt("userId", 0);
        sessionId = sp.getString("sessionId", null);
        addressId = sp.getInt("addressId", 0);
        //提交订单
        commit_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject object=new JSONObject();
                final JSONArray array=new JSONArray();
                for (int i = 0; i <mlist.size() ; i++) {
                    try {
                        object.put("commodityId",mlist.get(i).getCommodityId());
                        object.put("amount",mlist.get(i).getNum());
                        array.put(object);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if(addressId ==0){
                    //如果没有默认收货地址  就跳转收货地址列表 选择默认地址
                    Toast.makeText(SuanActivity.this,"请选择地址",Toast.LENGTH_SHORT).show();
                    Intent intent1=new Intent(SuanActivity.this,AddressListActivity.class);
                    startActivity(intent1);
                }else
                {
                    //如果有默认地址  直接提交
                    myPresenterInter.Suan(userId, sessionId,array.toString(), sum, addressId);
                    //清空数据库里的内容
                    beansDao.deleteAll();
                    //跳转首页  再从首页去订单Fragment
                    Intent intent1=new Intent(SuanActivity.this,ShowActivity.class);
                    intent1.putExtra("flag",1);
                    startActivity(intent1);
                }
            }
        });
    }
    private void initDatas() {
        //如果有数据就展示  如果没有加收货地址
        add_btn=findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到收货列表页面
                Intent intent=new Intent(SuanActivity.this,AddressListActivity.class);
                intent.putExtra("flag",1);
                startActivity(intent);
            }
        });
    }

    @Override
    public void MySuan(String str) {
        //提示结算信息
        Toast.makeText(SuanActivity.this,str,Toast.LENGTH_SHORT).show();
    }
}
