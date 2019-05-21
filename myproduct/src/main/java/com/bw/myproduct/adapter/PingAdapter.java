package com.bw.myproduct.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.myproduct.R;
import com.bw.myproduct.bean.PingBean;
import com.bw.myproduct.bean.XiangBean;

import java.util.Arrays;
import java.util.List;

public class PingAdapter extends RecyclerView.Adapter {
    Context context;
    List<PingBean.ResultBean> mlist;
    int type=1;
    public PingAdapter(Context context, List<PingBean.ResultBean> list) {
         this.context=context;
         this.mlist=list;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(type==1){
            //如果没图
            View view=View.inflate(context, R.layout.ping_1_item,null);
            return new Holder_1(view);
        }else{
            //如果有图
            View view = View.inflate(context, R.layout.ping_2_item, null);
            return new Holder_2(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if(type==1){
            LinearLayoutManager layoutManager=new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            ((Holder_1)viewHolder).recycler_1.setLayoutManager(layoutManager);
             //无图  只显示文字
             Ping_1_Adapter ping_1_adapter=new Ping_1_Adapter(context,mlist);
            ((Holder_1)viewHolder).recycler_1.setAdapter(ping_1_adapter);

        }else if(type==2) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            ((Holder_2) viewHolder).recycler_2.setLayoutManager(layoutManager);
            //展示有图的评论
            Ping_2_Adapter ping_2_adapter = new Ping_2_Adapter(context, mlist);
            ((Holder_2) viewHolder).recycler_2.setAdapter(ping_2_adapter);
        }
    }

    @Override
    public int getItemViewType(int position) {
        String image = mlist.get(position).getImage();
        if(image.isEmpty()){
            type=1;
            return type;
        }else{
            type=2;
            return  type;
        }
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public  class Holder_1  extends  RecyclerView.ViewHolder{
            RecyclerView recycler_1;
        public Holder_1(@NonNull View itemView) {
            super(itemView);
            recycler_1=itemView.findViewById(R.id.recycler_1);
        }
    }
    public  class Holder_2  extends  RecyclerView.ViewHolder{
            RecyclerView recycler_2;
        public Holder_2(@NonNull View itemView) {
            super(itemView);
            recycler_2=itemView.findViewById(R.id.recycler_2);
        }
    }


}
