package com.bw.myproduct.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.myproduct.R;
import com.bw.myproduct.bean.XiangBean;

import java.util.List;

public class XiangAdapter extends RecyclerView.Adapter<XiangAdapter.Holder> {
    Context context;
    XiangBean xiangBean;
    public XiangAdapter(Context context, XiangBean xiangBean) {
         this.context=context;
         this.xiangBean=xiangBean;
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //详情的名字
        View view=  View.inflate(context, R.layout.xiang_adapter,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        //获取name
        holder.xiang_name.setText(xiangBean.getResult().getCommodityName());
    }

    @Override
    public int getItemCount() {
        return  1;
    }
    public  class Holder extends  RecyclerView.ViewHolder{
        TextView xiang_name;
        public Holder(@NonNull View itemView) {
            super(itemView);
            xiang_name=itemView.findViewById(R.id.xiang_name);
        }
    }
}
