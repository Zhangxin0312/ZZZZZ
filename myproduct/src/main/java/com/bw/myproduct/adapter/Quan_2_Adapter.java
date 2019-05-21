package com.bw.myproduct.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.myproduct.R;
import com.bw.myproduct.bean.QuanBean;
import com.bw.myproduct.beans.MyQuanBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Quan_2_Adapter extends RecyclerView.Adapter<Quan_2_Adapter.holder> {
    Context context;
    List<QuanBean.ResultBean> mlist;

    public Quan_2_Adapter(Context context, List<QuanBean.ResultBean> list) {
        this.mlist=list;
        this.context=context;
    }
    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //一排三个图片的展示
        View view=View.inflate(context, R.layout.quan_2_view,null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int i) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd ");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        holder.quan_2_tou.setImageURI(mlist.get(i).getHeadPic());
        holder.quan_2_name.setText(mlist.get(i).getNickName());
        holder.quan_2_time.setText(simpleDateFormat.format(date));
        holder.quan_2_content.setText(mlist.get(i).getContent());
        holder.quan_2_iv.setImageURI(mlist.get(i).getImage());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }



    public class holder extends RecyclerView.ViewHolder{
        SimpleDraweeView quan_2_tou;
        TextView quan_2_name;
        TextView quan_2_time;
        TextView quan_2_content;
        SimpleDraweeView quan_2_iv;
        public holder(@NonNull View itemView) {
            super(itemView);
            quan_2_tou= itemView.findViewById(R.id.quan_2_tou);
            quan_2_name= itemView.findViewById(R.id.quan_2_name);
            quan_2_time= itemView.findViewById(R.id.quan_2_time);
            quan_2_content= itemView.findViewById(R.id.quan_2_content);
            quan_2_iv= itemView.findViewById(R.id.quan_2_iv);
        }
    }
}
