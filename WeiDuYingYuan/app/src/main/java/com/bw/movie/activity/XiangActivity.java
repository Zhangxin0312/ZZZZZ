package com.bw.movie.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.XiangAdapter;
import com.bw.movie.app.App;
import com.bw.movie.contract.ContractInter;
import com.bw.movie.presenter.Presenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class XiangActivity extends AppCompatActivity  implements  ContractInter.XingInter{
    @BindView(R.id.xiang_recy)
    RecyclerView xiang_recy;
    @BindView(R.id.hot_movie)
    Button hot_movie;
    @BindView(R.id.moving)
    Button moving;
    @BindView(R.id.movie_on)
    Button movie_on;
    @BindView(R.id.back)
    ImageView back;
     XiangAdapter xiangAdapter;
     int flag=1;
   public XiangActivity xiangActivity;
    ContractInter.PresenterInter  presenterInter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang);
        ButterKnife.bind(this);
        xiangActivity=this;
        presenterInter=new Presenter<>(this);
        hot_movie.setBackgroundColor(Color.RED);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xiang_recy.setLayoutManager(layoutManager);
        //展示
        xiangAdapter = new XiangAdapter(XiangActivity.this,App.list,App.list1,App.list2,flag);
        xiang_recy.setAdapter(xiangAdapter);
        initData();
    }

    public void initData() {
        hot_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=1;
                hot_movie.setBackgroundColor(Color.RED);
                moving.setBackgroundColor(Color.WHITE);
                movie_on.setBackgroundColor(Color.WHITE);

                hot_movie.setTextColor(Color.WHITE);
                moving.setTextColor(Color.BLACK);
                movie_on.setTextColor(Color.BLACK);

                xiangAdapter = new XiangAdapter(XiangActivity.this,App.list,App.list1,App.list2,flag);
                xiang_recy.setAdapter(xiangAdapter);
            }
        });
        moving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=2;
                hot_movie.setBackgroundColor(Color.WHITE);
                moving.setBackgroundColor(Color.RED);
                movie_on.setBackgroundColor(Color.WHITE);

                hot_movie.setTextColor(Color.BLACK);
                moving.setTextColor(Color.WHITE);
                movie_on.setTextColor(Color.BLACK);

                xiangAdapter = new XiangAdapter(XiangActivity.this,App.list,App.list1,App.list2,flag);
                xiang_recy.setAdapter(xiangAdapter);
            }
        });
        movie_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=3;
                hot_movie.setBackgroundColor(Color.WHITE);
                moving.setBackgroundColor(Color.WHITE);
                movie_on.setBackgroundColor(Color.RED);

                hot_movie.setTextColor(Color.BLACK);
                moving.setTextColor(Color.BLACK);
                movie_on.setTextColor(Color.WHITE);

                xiangAdapter = new XiangAdapter(XiangActivity.this,App.list,App.list1,App.list2,flag);
                xiang_recy.setAdapter(xiangAdapter);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XiangActivity.this,ShowActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public  void getId(int id){
        //跳转详情页面
        Intent intent=new Intent(XiangActivity.this,MovieActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
    }
     //关注
    public void Attention(int id) {
        presenterInter.Attention(id);
        App.flagg = true;
    }

    @Override
    public void MyAttention(String str) {
        Toast.makeText(XiangActivity.this,str,Toast.LENGTH_SHORT).show();

    }
    //取消关注
    public void Cancel(int id) {
        presenterInter.Cancles(id);
        App.flagg = true;
    }
    @Override
    public void MyCancle(String str) {
        Toast.makeText(XiangActivity.this,str,Toast.LENGTH_SHORT).show();
    }
}
