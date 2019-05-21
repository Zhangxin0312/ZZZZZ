package com.bw.myproduct.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.bw.myproduct.R;
import com.bw.myproduct.bean.QuanBean;

import java.util.List;

public class QuanAdapter   extends RecyclerView.Adapter {
      int type=1;
    Context context;
    List<QuanBean.ResultBean> mlist;
    public QuanAdapter(Context context, List<QuanBean.ResultBean> list) {
        this.context=context;
        this.mlist=list;
    }
     //圈子的多条目
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(type==1){
            View view=View.inflate(context, R.layout.quan_1_item,null);
            return new Holder_1(view);
        }else{
            View view=View.inflate(context, R.layout.quan_2_item,null);
            return new Holder_2(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if(type==1){
            //没有图片
            LinearLayoutManager layoutManager=new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            ((Holder_1)viewHolder).quan_recycler_1.setLayoutManager(layoutManager);
            //圈子的Adapter展示
            Quan_1_Adapter quan_1_adapter=new Quan_1_Adapter(context,mlist);
            ((Holder_1)viewHolder).quan_recycler_1.setAdapter(quan_1_adapter);
        }else{
            //有图片
            LinearLayoutManager layoutManager=new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            ((Holder_2)viewHolder).quan_recycler_2.setLayoutManager(layoutManager);
            //圈子的Adapter展示
            Quan_2_Adapter quan_2_adapter=new Quan_2_Adapter(context,mlist);
            ((Holder_2)viewHolder).quan_recycler_2.setAdapter(quan_2_adapter);

        }
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    @Override
    public int getItemViewType(int position) {
        String image = mlist.get(position).getImage();
        if(image==null){
            type=1;
            return type;
        }else{
            type=2;
            return  type;
        }
    }
    public  class Holder_1  extends  RecyclerView.ViewHolder{
        RecyclerView quan_recycler_1;
        public Holder_1(@NonNull View itemView) {
            super(itemView);
            quan_recycler_1=itemView.findViewById(R.id.quan_recycler_1);
        }
    }
    public  class Holder_2  extends  RecyclerView.ViewHolder{
        RecyclerView quan_recycler_2;
        public Holder_2(@NonNull View itemView) {
            super(itemView);
            quan_recycler_2=itemView.findViewById(R.id.quan_recycler_2);
        }
    }

}
