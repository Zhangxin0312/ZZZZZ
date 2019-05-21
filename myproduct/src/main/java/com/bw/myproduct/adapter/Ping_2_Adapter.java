package com.bw.myproduct.adapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.myproduct.R;
import com.bw.myproduct.bean.PingBean;
import com.facebook.drawee.view.SimpleDraweeView;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Ping_2_Adapter extends RecyclerView.Adapter<Ping_2_Adapter.Holder> {
    Context context;
    List<PingBean.ResultBean> mlist;
    public Ping_2_Adapter(Context context, List<PingBean.ResultBean> list) {
        this.context=context;
        this.mlist=list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(context, R.layout.ping_2_view,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd ");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        //RecyclerView的  头像  名字  时间  评论   两个图片
        holder.ping2_tou.setImageURI(mlist.get(i).getHeadPic());
        holder.ping2_name.setText(mlist.get(i).getNickName());
        holder.ping2_time.setText(simpleDateFormat.format(date));
        holder.ping2_content.setText(mlist.get(i).getContent());
        holder.ping2_iv.setImageURI(mlist.get(i).getImage());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public  class Holder extends  RecyclerView.ViewHolder{
        @BindView(R.id.ping2_tou)
        SimpleDraweeView ping2_tou;
        @BindView(R.id.ping2_name)
        TextView ping2_name;
        @BindView(R.id.ping2_time)
        TextView ping2_time;
        @BindView(R.id.ping2_content)
        TextView ping2_content;
        @BindView(R.id.ping2_iv)
        SimpleDraweeView ping2_iv;
        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
