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

public class MyThreeOrderAdapter extends RecyclerView.Adapter<MyThreeOrderAdapter.Holder> {
    Context context;
    OrderBean orderBean;
    DingFragment dingFragment;
    public MyThreeOrderAdapter(Context context, OrderBean orderBean, DingFragment dingFragment) {
        this.context=context;
        this.orderBean=orderBean;
        this.dingFragment=dingFragment;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //待收货的外层
        View view=View.inflate(context, R.layout.three_order_view,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int i) {
        //获取当前时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss
        Date date = new Date(System.currentTimeMillis());
        holder.three_create_time.setText(simpleDateFormat.format(date));
        holder.three_ding_id.setText(orderBean.getOrderList().get(i).getOrderId()+"");
        holder.Company_id.setText(orderBean.getOrderList().get(i).getExpressCompName());
        holder.Number_id.setText(orderBean.getOrderList().get(i).getExpressSn());
        holder.Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dingFragment.Affirm(orderBean.getOrderList().get(i).getOrderId());
            }
        });

        LinearLayoutManager layoutManager=new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        holder.three_rlv_id.setLayoutManager(layoutManager);
        //展示RecyclerView
        ThreeOrderAdapter  threeOrderAdapter= new ThreeOrderAdapter(context,orderBean.getOrderList().get(i).getDetailList(),dingFragment);
        holder.three_rlv_id.setAdapter(threeOrderAdapter);

    }

    @Override
    public int getItemCount() {
        return orderBean.getOrderList().size();
    }

    public  class Holder extends  RecyclerView.ViewHolder{
         @BindView(R.id.three_ding_id)
         TextView three_ding_id;
         @BindView(R.id.three_create_time)
         TextView three_create_time;
        @BindView(R.id.three_rlv_id)
        RecyclerView three_rlv_id;
        @BindView(R.id.Confirm)
        Button Confirm;
        @BindView(R.id.Company_id)
        TextView Company_id;
        @BindView(R.id.Number_id)
        TextView Number_id;
        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
