package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.RecodeBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SuccessAdapter extends RecyclerView.Adapter<SuccessAdapter.Holder> {
    Context context;
    List<RecodeBean.ResultBean> mlist;
    public SuccessAdapter(Context context, List<RecodeBean.ResultBean> list) {
        this.context=context;
        this.mlist=list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.success_view, null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.recode_2_title.setText(mlist.get(position).getMovieName());
        holder.ding_2_id.setText(mlist.get(position).getOrderId());
        holder.yuan_2_id.setText(mlist.get(position).getCinemaName());
        holder.ting_2_id.setText(mlist.get(position).getScreeningHall());
        String beginTime = mlist.get(position).getBeginTime();
        String endTime = mlist.get(position).getEndTime();
        TextPaint paint = holder.start_end.getPaint();
        paint.setFakeBoldText(true);
        holder.start_end.setText(" "+beginTime+"    -    "+endTime);
        holder.num_2_id.setText(mlist.get(position).getAmount()+"张");
        holder.price_2_id.setText(mlist.get(position).getPrice()+"元");
        long createTime = mlist.get(position).getCreateTime();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date(createTime);
        holder.dan_2_id.setText(format.format(date));
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public  class Holder extends  RecyclerView.ViewHolder{
        @BindView(R.id.recode_2_title)
        TextView recode_2_title;
        @BindView(R.id.start_end)
        TextView start_end;
        @BindView(R.id.ding_2_id)
        TextView ding_2_id;
        @BindView(R.id.dan_2_id)
        TextView dan_2_id;
        @BindView(R.id.yuan_2_id)
        TextView yuan_2_id;
        @BindView(R.id.ting_2_id)
        TextView ting_2_id;
        @BindView(R.id.num_2_id)
        TextView num_2_id;
        @BindView(R.id.price_2_id)
        TextView price_2_id;
        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
      }
    }
}
