package com.bw.myproduct.adapter;

import android.content.Context;
import android.net.Uri;
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
import com.makeramen.roundedimageview.RoundedImageView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Ping_1_Adapter extends RecyclerView.Adapter<Ping_1_Adapter.holder> {
    Context context;
    List<PingBean.ResultBean> mlist;
    public Ping_1_Adapter(Context context, List<PingBean.ResultBean> list) {
        this.context=context;
        this.mlist=list;
    }


    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //第一层RecyclerView展示
        View view=View.inflate(context, R.layout.ping_1_view,null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int i) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd   HH:mm");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        holder.ping1_time.setText(simpleDateFormat.format(date));
        //tou  name   time  content   iv
        holder.ping1_tou.setImageURI(mlist.get(i).getHeadPic());
        holder.ping1_name.setText(mlist.get(i).getNickName());
        holder.ping1_content.setText(mlist.get(i).getContent());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }



    public class holder extends RecyclerView.ViewHolder{
        @BindView(R.id.ping1_tou)
        SimpleDraweeView ping1_tou;
        @BindView(R.id.ping1_name)
        TextView ping1_name;
        @BindView(R.id.ping1_time)
        TextView ping1_time;
        @BindView(R.id.ping1_content)
        TextView ping1_content;
        public holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
