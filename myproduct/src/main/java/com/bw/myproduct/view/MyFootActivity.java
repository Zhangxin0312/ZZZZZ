package com.bw.myproduct.view;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bw.myproduct.R;
import com.bw.myproduct.adapter2.FootAdapter;
import com.bw.myproduct.beans.FootBean;
import com.bw.myproduct.contract.ContractInterface;
import com.bw.myproduct.presenter.MyPresens;

import java.util.ArrayList;
import java.util.List;

public class MyFootActivity extends AppCompatActivity implements  ContractInterface.MyFootInter{
    RecyclerView foot_rlv;
    ContractInterface.MyPresenterInter myPresenterInter;
    int page=1;
    int count=5;
    List<FootBean.ResultBean> mlist=new ArrayList<>();
    private FootAdapter footAdapter;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_foot);
        foot_rlv=findViewById(R.id.foot_rlv);

        GridLayoutManager layoutManager=new GridLayoutManager(MyFootActivity.this,2);
        foot_rlv.setLayoutManager(layoutManager);
        //实例化对象
        myPresenterInter=new MyPresens<>(this);
        //获取userId和sessionId
       SharedPreferences sp = getSharedPreferences("share", MODE_PRIVATE);
       int  userId = sp.getInt("userId", 0);
       String  sessionId = sp.getString("sessionId", null);
       //请求网络
        myPresenterInter.Foot(userId,sessionId,page,count);
        //展示我的足迹
        footAdapter = new FootAdapter(MyFootActivity.this,mlist);
        foot_rlv.setAdapter(footAdapter);
    }

    @Override
    public void MyFoot(List<FootBean.ResultBean> list) {
        //我的足迹返回的数据
           mlist.clear();
           mlist.addAll(list);
           footAdapter.notifyDataSetChanged();
    }
}
