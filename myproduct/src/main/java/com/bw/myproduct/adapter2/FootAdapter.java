package com.bw.myproduct.adapter2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.myproduct.R;
import com.bw.myproduct.beans.FootBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FootAdapter extends RecyclerView.Adapter<FootAdapter.Holder> {
    Context context;
    List<FootBean.ResultBean> mlist;
    public FootAdapter(Context context, List<FootBean.ResultBean> list) {
        this.context=context;
        this.mlist=list;
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //我的足迹RecyclerVIew展示
        View view=View.inflate(context, R.layout.foot_item,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int i) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd  HH:mm");
        Date date=new Date(System.currentTimeMillis());
        //时间  价格  图片   个数
           holder.foot_num.setText("已浏览"+mlist.get(i).getBrowseNum()+"次");
           holder.foot_price.setText("￥"+mlist.get(i).getPrice());
           Glide.with(context).load(mlist.get(i).getMasterPic()).into(holder.foot_iv);
           holder.foot_time.setText(simpleDateFormat.format(date));
           holder.foot_title.setText(mlist.get(i).getCommodityName());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public  class Holder  extends  RecyclerView.ViewHolder{
        @BindView(R.id.foot_iv)
        ImageView foot_iv;
        @BindView(R.id.foot_price)
        TextView foot_price;
        @BindView(R.id.foot_num)
        TextView foot_num;
        @BindView(R.id.foot_time)
        TextView foot_time;
        @BindView(R.id.foot_title)
        TextView foot_title;
        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
