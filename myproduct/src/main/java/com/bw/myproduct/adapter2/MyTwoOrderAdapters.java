package com.bw.myproduct.adapter2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bw.myproduct.R;
import com.bw.myproduct.beans.OrderBean;
import com.bw.myproduct.fragment.DingFragment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyTwoOrderAdapters extends RecyclerView.Adapter<MyTwoOrderAdapters.Holder> {
    Context context;
    OrderBean orderBean;
    DingFragment dingFragment;
    private int sum;

    public MyTwoOrderAdapters(Context context, OrderBean orderBean, DingFragment dingFragment) {
        this.context=context;
        this.orderBean=orderBean;
        this.dingFragment=dingFragment;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //去支付的外层
        View view=View.inflate(context, R.layout.two_order_view,null);
        return new Holder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int i) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        holder.create_time.setText(simpleDateFormat.format(date));
        holder.ding_id.setText(orderBean.getOrderList().get(i).getOrderId()+"");
        //商品总价和 总商品个数
        sum = dingFragment.getSum(i);
        holder.two_order_count.setText("商品需付款"+ sum +"元");
        Log.e("tag",sum+"------------------");
        holder.topay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查询订单的orderId
                String orderId = orderBean.getOrderList().get(i).getOrderId();
                Log.e("tag","============"+sum);
                dingFragment.Jump(sum,orderId);
            }
        });

        LinearLayoutManager layoutManager=new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        holder.two_rlv_id.setLayoutManager(layoutManager);
        //设置分割线
        //  holder.two_rlv_id.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));
       //展示内层recyclerViw
        MyTwoOrderAdapter   myTwoOrderAdapter = new MyTwoOrderAdapter(context,orderBean.getOrderList().get(i).getDetailList(),dingFragment);
        holder.two_rlv_id.setAdapter(myTwoOrderAdapter);
        //删除订单
        holder.cancle_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("取消支付");
                builder.setMessage("是否确认删除");
                //点击对话框以外的区域是否让对话框消失
                builder.setCancelable(true);
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dingFragment.MyCancles(orderBean.getOrderList().get(i).getOrderId());
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return orderBean.getOrderList().size();
    }

    public  class Holder extends  RecyclerView.ViewHolder{
        TextView ding_id;
        TextView create_time, two_order_count;
        RecyclerView two_rlv_id;
        Button cancle_id,topay;
        public Holder(@NonNull View itemView) {
            super(itemView);
            ding_id = itemView.findViewById(R.id.ding_id);
            create_time = itemView.findViewById(R.id.create_time);
            two_rlv_id=itemView.findViewById(R.id.two_rlv_id);
            two_order_count=itemView.findViewById(R.id.two_order_count);
            cancle_id=itemView.findViewById(R.id.cancle_id);
            topay=itemView.findViewById(R.id.topay);
        }
    }
}
