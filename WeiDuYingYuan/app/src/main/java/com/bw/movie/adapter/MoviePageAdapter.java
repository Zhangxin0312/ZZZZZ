package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activity.PageListActivity;
import com.bw.movie.bean.CinemaPageBean;
import com.bw.movie.bean.MoviePageBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviePageAdapter extends RecyclerView.Adapter<MoviePageAdapter.Holder> {
    Context context;
    List<MoviePageBean.ResultBean> mlist;
    public MoviePageAdapter(Context context, List<MoviePageBean.ResultBean> list) {
        this.context=context;
        this.mlist=list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.movie_page_view, null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
         holder.movie_iv_id.setImageURI(mlist.get(position).getImageUrl());
         holder.movie_content_id.setText(mlist.get(position).getSummary());
         holder.movie_title_id.setText(mlist.get(position).getName());
         long releaseTime = mlist.get(position).getReleaseTime();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
         holder.movie_time_id.setText(format.format(releaseTime));
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public  class Holder extends  RecyclerView.ViewHolder{
        @BindView(R.id.movie_iv_id)
        SimpleDraweeView movie_iv_id;
        @BindView(R.id.movie_content_id)
        TextView movie_content_id;
        @BindView(R.id.movie_title_id)
        TextView movie_title_id;
        @BindView(R.id.movie_time_id)
        TextView  movie_time_id;
        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
      }
    }
}
