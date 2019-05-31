package com.bw.movie.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.adapter.MovieListAdapter;
import com.bw.movie.app.App;
import com.bw.movie.bean.MovieScheduleListBean;
import com.bw.movie.bean.MovingBean;
import com.bw.movie.contract.ContractInter;
import com.bw.movie.presenter.Presenter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieListActivity extends AppCompatActivity implements  ContractInter.MovieXiangInter,ContractInter.MovieListInter{
      @BindView(R.id.cinema_name_id)
      TextView cinema_name_id;
      @BindView(R.id.cinema_address_id)
      TextView cinema_address_id;
      @BindView(R.id.cinema_iv)
      SimpleDraweeView cinema_iv;
      @BindView(R.id.cinema_name)
      TextView cinema_name;
      @BindView(R.id.cinema_type)
      TextView cinema_type;
      @BindView(R.id.cinema_author)
       TextView cinema_author;
      @BindView(R.id.cinema_time)
      TextView cinema_time;
      @BindView(R.id.cinema_address)
      TextView cinema_address;
      @BindView(R.id.cinema_recycler)
      RecyclerView cinema_recycler;
      @BindView(R.id.movieList_back)
      ImageView movieList_back;
      ContractInter.PresenterInter presenterInter;
      List<MovieScheduleListBean.ResultBean> mlist=new ArrayList<>();
    private MovieListAdapter movieListAdapter;
     MovieListActivity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        ButterKnife.bind(this);
        activity=this;
        Intent intent = getIntent();
        int movieId = intent.getIntExtra("movieId", 0);
        int cinemasId = intent.getIntExtra("cinemasId", 0);
        String name = intent.getStringExtra("name");
        String address = intent.getStringExtra("address");
        presenterInter=new Presenter<>(this);
        presenterInter.Moving(movieId);
        cinema_address_id.setText(address);
        cinema_name_id.setText(name);

        //布局管理器
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        cinema_recycler.setLayoutManager(layoutManager);
        //请求接口  MovieScheduleList
        presenterInter.MovieScheduleList(movieId,cinemasId);
        //Adapter
        movieListAdapter = new MovieListAdapter(MovieListActivity.this,mlist);
        cinema_recycler.setAdapter(movieListAdapter);
        movieList_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }

    @Override
    public void MyMoving(MovingBean movingBean) {
        MovingBean.ResultBean result = movingBean.getResult();
        cinema_iv.setImageURI(result.getImageUrl());
        cinema_name.setText(result.getName());
        cinema_type.setText(result.getMovieTypes());
        cinema_author.setText(result.getDirector());
        cinema_time.setText(result.getDuration());
        cinema_address.setText(result.getPlaceOrigin());
    }

    @Override
    public void MyMovieScheduleList(List<MovieScheduleListBean.ResultBean> list) {
        mlist.clear();
        mlist.addAll(list);
        App.list3=mlist;
        movieListAdapter.notifyDataSetChanged();
    }

    public void Jump(String start,String end,String hall,double price,int id) {
        Intent intent=new Intent(MovieListActivity.this,TicketActivity.class);
        intent.putExtra("start",start);
        intent.putExtra("end",end);
        intent.putExtra("hall",hall);
        intent.putExtra("price",price);
        intent.putExtra("id",id);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
    }
}
