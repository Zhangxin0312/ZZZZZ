package com.bw.myproduct.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.myproduct.R;
import com.bw.myproduct.adapter2.MyQuanAdapter;
import com.bw.myproduct.beans.MyQuanBean;
import com.bw.myproduct.contract.ContractInterface;
import com.bw.myproduct.presenter.MyPresens;

import java.util.ArrayList;
import java.util.List;

public class MyQuanActivity extends AppCompatActivity implements ContractInterface.MyQuanInter {
      RecyclerView my_quan_rlv;
      ContractInterface.MyPresenterInter myPresenterInter;
     SharedPreferences sp;
     int page=1;
     int count=5;
     List<MyQuanBean.ResultBean> mlist=new ArrayList<>();
     MyQuanAdapter myQuanAdapter;
     int userId;
     String sessionId;
     ImageView delete_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_quan);
        my_quan_rlv=findViewById(R.id.my_quan_rlv);
        delete_id=findViewById(R.id.delete_id);
        //实例化对象
        myPresenterInter=new MyPresens<>(this);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        my_quan_rlv.setLayoutManager(layoutManager);
        //获取userId和sesionId
        sp = getSharedPreferences("share", Context.MODE_PRIVATE);
        userId = sp.getInt("userId",0);
        sessionId = sp.getString("sessionId", null);
        myPresenterInter.Quans(userId, sessionId,page,count);
         //展示adapter
        myQuanAdapter = new MyQuanAdapter(MyQuanActivity.this,mlist);
        my_quan_rlv.setAdapter(myQuanAdapter);
        //删除数据
        initData();
    }
    @Override
    public void MyQuans(List<MyQuanBean.ResultBean> list) {
        //返回的数据
        mlist.clear();
        mlist.addAll(list);
        myQuanAdapter.notifyDataSetChanged();
    }
    //删除数据
    public void initData() {
        delete_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i <mlist.size() ; i++) {
                     mlist.get(i).setFlag(true);
                }
                myQuanAdapter = new MyQuanAdapter(MyQuanActivity.this,mlist);
                my_quan_rlv.setAdapter(myQuanAdapter);
            }
        });
    }
    //点赞
     public  void  getconfirmId(int id){
        //点赞  图片变红 文字变红 数量加1
        myPresenterInter.GiveUp(userId,sessionId,id);

    }
    //点赞的返回数据
    @Override
    public void MyGiveUp(String str) {
        Toast.makeText(MyQuanActivity.this,str,Toast.LENGTH_SHORT).show();
    }
    public void getCancle(int circleId) {
        myPresenterInter.GiveOut(userId,sessionId,circleId);
    }
     //取消点赞的返回
    @Override
    public void MyGiveOut(String str) {
        Toast.makeText(MyQuanActivity.this,str,Toast.LENGTH_SHORT).show();
    }
    //删除我发表过的圈子
    public void delete(int commodityId) {
           myPresenterInter.Delete(userId,sessionId,commodityId);
    }
    //删除圈子的结果  成功或者失败
    @Override
    public void MyDelete(String str) {
        Toast.makeText(MyQuanActivity.this,str,Toast.LENGTH_SHORT).show();
        if(str.equals("删除成功")){
              myPresenterInter.Quans(userId,sessionId,page,count);
        }
    }
}
