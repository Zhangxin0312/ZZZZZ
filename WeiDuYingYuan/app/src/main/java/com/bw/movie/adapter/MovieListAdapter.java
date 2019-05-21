package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activity.MovieListActivity;
import com.bw.movie.bean.MovieScheduleListBean;
import com.bw.movie.bean.ShouBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.Holder> {
    Context context;
    List<MovieScheduleListBean.ResultBean> mlist;
    MovieListActivity activity;
    public MovieListAdapter(Context context, List<MovieScheduleListBean.ResultBean> list) {
        this.context=context;
        this.mlist=list;
        activity= (MovieListActivity) context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.movie_list_view, null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        holder.cinema_ting.setText(mlist.get(position).getScreeningHall());
         holder.cinema_price.setText("￥"+mlist.get(position).getPrice());
        mlist.get(position).getPrice();
         holder.cinema_start.setText(mlist.get(position).getBeginTime());
         holder.cinema_end.setText(mlist.get(position).getEndTime());
         holder.itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 activity.Jump(mlist.get(position).getBeginTime(),mlist.get(position).getEndTime(),mlist.get(position).getScreeningHall(),mlist.get(position).getPrice(),mlist.get(position).getId());
             }
         });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public  class Holder extends  RecyclerView.ViewHolder{
        @BindView(R.id.cinema_start)
        TextView cinema_start;
        @BindView(R.id.cinema_end)
        TextView cinema_end;
        @BindView(R.id.cinema_price)
        TextView cinema_price;
        @BindView(R.id.cinema_ting)
        TextView  cinema_ting;
        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
      }
    }
}
