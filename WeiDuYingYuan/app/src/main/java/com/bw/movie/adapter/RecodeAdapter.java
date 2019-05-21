package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activity.RecordListActivity;
import com.bw.movie.bean.RecodeBean;
import com.bw.movie.bean.ShouBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecodeAdapter extends RecyclerView.Adapter<RecodeAdapter.Holder> {
    Context context;
    List<RecodeBean.ResultBean> mlist;
    public RecodeAdapter(Context context, List<RecodeBean.ResultBean> list) {
        this.context=context;
        this.mlist=list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.record_view, null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.recode_title.setText(mlist.get(position).getMovieName());
        holder.ding_id.setText(mlist.get(position).getOrderId());
        holder.yuan_id.setText(mlist.get(position).getCinemaName());
        holder.ting_id.setText(mlist.get(position).getScreeningHall());
        long createTime = mlist.get(position).getCreateTime();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date(createTime);
        holder.time_id.setText(format.format(date));
        holder.num_id.setText(mlist.get(position).getAmount()+"张");
        holder.price_id.setText(mlist.get(position).getPrice()+"元");
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public  class Holder extends  RecyclerView.ViewHolder{
        @BindView(R.id.recode_title)
        TextView recode_title;
        @BindView(R.id.to_buy_id)
        Button to_buy_id;
        @BindView(R.id.ding_id)
        TextView ding_id;
        @BindView(R.id.yuan_id)
        TextView yuan_id;
        @BindView(R.id.ting_id)
        TextView ting_id;
        @BindView(R.id.time_id)
        TextView time_id;
        @BindView(R.id.num_id)
        TextView num_id;
        @BindView(R.id.price_id)
        TextView price_id;
        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
      }
    }
}
