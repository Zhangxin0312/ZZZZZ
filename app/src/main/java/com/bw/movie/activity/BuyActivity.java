package com.bw.movie.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.BuyAdapter;
import com.bw.movie.bean.FindBean;
import com.bw.movie.contract.ContractInter;
import com.bw.movie.presenter.Presenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BuyActivity extends AppCompatActivity implements ContractInter.BuyInter {
    @BindView(R.id.buy_recycler)
    RecyclerView buy_recycler;
    @BindView(R.id.movie_name_id)
    TextView movie_name_id;
    @BindView(R.id.buy_back)
    ImageView buy_back;
    private int id;
    private String name;
    ContractInter.PresenterInter presenterInter;
    List<FindBean.ResultBean> mlist=new ArrayList<>();
    private BuyAdapter buyAdapter;
    BuyActivity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        activity=this;
        ButterKnife.bind(this);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        name = intent.getStringExtra("name");
        movie_name_id.setText(name);
        //实例化
        presenterInter=new Presenter<>(this);
        //布局管理器
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        buy_recycler.setLayoutManager(layoutManager);
        //获取数据
         presenterInter.FindMovie(id);
        //展示
        buyAdapter = new BuyAdapter(BuyActivity.this,mlist);
        buy_recycler.setAdapter(buyAdapter);
        buy_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }


    @Override
    public void MyFind(List<FindBean.ResultBean> list) {
         mlist.clear();
         mlist.addAll(list);
        buyAdapter.notifyDataSetChanged();
    }

    public void Jump(int cinemasId,String name,String address) {
        Intent intent=new Intent(BuyActivity.this,MovieListActivity.class);
        intent.putExtra("movieId", id);
        intent.putExtra("cinemasId", cinemasId);
        intent.putExtra("name", name);
        intent.putExtra("address", address);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
    }
    //关注影院
    public void Fllow( int id) {
        presenterInter.FllowCinema(id);
    }
    //取消关注影院
    public void CancleFllow(int id) {
        presenterInter.CancleFllowCinema(id);
    }
    //关注影院的返回值
    @Override
    public void MyFllowCinema(String str) {
        Toast.makeText(BuyActivity.this,str,Toast.LENGTH_SHORT).show();

    }
    //取消关注影院的返回值
    @Override
    public void MyCancleFllowCinema(String str) {
        Toast.makeText(BuyActivity.this,str,Toast.LENGTH_SHORT).show();
    }
}
