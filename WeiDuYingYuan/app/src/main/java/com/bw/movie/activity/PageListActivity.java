package com.bw.movie.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.adapter.MoviePageAdapter;
import com.bw.movie.adapter.PageAdapter;
import com.bw.movie.bean.CinemaPageBean;
import com.bw.movie.bean.MoviePageBean;
import com.bw.movie.contract.ContractInter;
import com.bw.movie.presenter.Presenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PageListActivity extends AppCompatActivity implements  ContractInter.PageInter{
    ContractInter.PresenterInter presenterInter;
    int page=1;
    int count=10;
    List<CinemaPageBean.ResultBean> mlist=new ArrayList<>();
    List<MoviePageBean.ResultBean> xlist=new ArrayList<>();
    private PageAdapter pageAdapter;
   @BindView(R.id.movie_id)
    Button movie_id;
    @BindView(R.id.cinema_id)
    Button cinema_id;
    @BindView(R.id.page_recycler)
    RecyclerView  page_recycler;
    @BindView(R.id.page_back)
    ImageView page_back;
     MoviePageAdapter moviePageAdapter;
     PageListActivity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_list);
        ButterKnife.bind(this);
        activity=this;
        presenterInter=new Presenter<>(this);
        //关注的影片
        presenterInter.MoviePage(page,count);
        LinearLayoutManager layoutManager=new LinearLayoutManager(PageListActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        page_recycler.setLayoutManager(layoutManager);
        //影片的Adapter
        moviePageAdapter = new MoviePageAdapter(PageListActivity.this,xlist);
        page_recycler.setAdapter(moviePageAdapter);
        cinema_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关注的影院
                cinema_id.setBackgroundResource(R.drawable.zi);
                cinema_id.setTextColor(Color.WHITE);
                movie_id.setBackgroundResource(R.drawable.hui);
                movie_id.setTextColor(Color.BLACK);
                presenterInter.CinemaPage(page,count);
                LinearLayoutManager layoutManager=new LinearLayoutManager(PageListActivity.this);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                page_recycler.setLayoutManager(layoutManager);
                //影院的Adapter
                pageAdapter = new PageAdapter(PageListActivity.this,mlist);
                page_recycler.setAdapter(pageAdapter);
            }
        });
        movie_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关注的影片
                movie_id.setBackgroundResource(R.drawable.zi);
                movie_id.setTextColor(Color.WHITE);
                cinema_id.setBackgroundResource(R.drawable.hui);
                cinema_id.setTextColor(Color.BLACK);
                presenterInter.MoviePage(page,count);
                LinearLayoutManager layoutManager=new LinearLayoutManager(PageListActivity.this);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                page_recycler.setLayoutManager(layoutManager);
                //影片的Adapter
                moviePageAdapter = new MoviePageAdapter(PageListActivity.this,xlist);
                page_recycler.setAdapter(moviePageAdapter);
            }
        });
        //返回
        page_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }
    //关注的影片
    @Override
    public void MyMoviePage(List<MoviePageBean.ResultBean> list) {
        xlist.clear();
        xlist.addAll(list);
        moviePageAdapter.notifyDataSetChanged();
    }
    //关注的影院
    @Override
    public void MyCinemaList(List<CinemaPageBean.ResultBean> list) {
        mlist.clear();
        mlist.addAll(list);
        pageAdapter.notifyDataSetChanged();
    }
}
