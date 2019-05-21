package com.bw.myproduct.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.myproduct.R;
import com.bw.myproduct.bean.PingBean;
import com.bw.myproduct.bean.QuanBean;
import com.bw.myproduct.beans.MyQuanBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Quan_1_Adapter extends RecyclerView.Adapter<Quan_1_Adapter.holder> {
    Context context;
    List<QuanBean.ResultBean> mlist;

    public Quan_1_Adapter(Context context, List<QuanBean.ResultBean> list) {
        this.mlist=list;
        this.context=context;
    }
    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //一左两右的图片展示
        View view=View.inflate(context, R.layout.quan_1_view,null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int i) {
          SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd ");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        holder.quan_1_tou.setImageURI(mlist.get(i).getHeadPic());
        holder.quan_1_name.setText(mlist.get(i).getNickName());
        holder.quan_1_time.setText(simpleDateFormat.format(date));
        holder.quan_1_content.setText(mlist.get(i).getContent());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }
    public class holder extends RecyclerView.ViewHolder{
        SimpleDraweeView quan_1_tou;
        TextView quan_1_name;
        TextView quan_1_time;
        TextView quan_1_content;
        public holder(@NonNull View itemView) {
            super(itemView);
            quan_1_tou= itemView.findViewById(R.id.quan_1_tou);
            quan_1_name= itemView.findViewById(R.id.quan_1_name);
            quan_1_time= itemView.findViewById(R.id.quan_1_time);
            quan_1_content= itemView.findViewById(R.id.quan_1_content);

        }
    }
}
