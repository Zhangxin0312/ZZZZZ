package com.bw.myproduct.adapter2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bw.myproduct.R;
import com.bw.myproduct.beans.OrderBean;
import com.bw.myproduct.fragment.DingFragment;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFiveOrderAdapter extends RecyclerView.Adapter<MyFiveOrderAdapter.Holder> {
    Context context;
    OrderBean orderBean;
    DingFragment dingFragment;
    public MyFiveOrderAdapter(Context context, OrderBean orderBean, DingFragment dingFragment) {
        this.context=context;
        this.orderBean=orderBean;
        this.dingFragment=dingFragment;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //待收货的外层
        View view=View.inflate(context, R.layout.five_order_view,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int i) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        holder.five_ding_id.setText(orderBean.getOrderList().get(i).getOrderId()+"");

        LinearLayoutManager layoutManager=new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        holder.five_rlv_id.setLayoutManager(layoutManager);
        //展示RecyclerView
        FiveOrderAdapter  fiveOrderAdapter= new FiveOrderAdapter(context,orderBean.getOrderList().get(i).getDetailList(),dingFragment);
        holder.five_rlv_id.setAdapter(fiveOrderAdapter);
        //收货时间
        holder.five_time.setText(simpleDateFormat.format(date));
    }

    @Override
    public int getItemCount() {
        return orderBean.getOrderList().size();
    }

    public  class Holder extends  RecyclerView.ViewHolder{
         @BindView(R.id.five_ding_id)
         TextView five_ding_id;
         @BindView(R.id.five_rlv_id)
         RecyclerView five_rlv_id;
        @BindView(R.id.five_time)
         TextView five_time;
        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
