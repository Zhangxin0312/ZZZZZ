package com.bw.myproduct.adapter2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.myproduct.R;
import com.bw.myproduct.beans.OrderBean;
import com.bw.myproduct.fragment.DingFragment;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFourOrderAdapter extends RecyclerView.Adapter<MyFourOrderAdapter.Holder> {
    Context context;
    OrderBean orderBean;
    DingFragment dingFragment;
    public MyFourOrderAdapter(Context context, OrderBean orderBean, DingFragment dingFragment) {
        this.context=context;
        this.orderBean=orderBean;
        this.dingFragment=dingFragment;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //去评价的外层数据
        View view=View.inflate(context, R.layout.four_order_view,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int i) {
        //订单号
        holder.four_ding_id.setText(orderBean.getOrderList().get(i).getOrderId()+"");
        //展示数据
        LinearLayoutManager layoutManager=new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        holder.four_rlv_id.setLayoutManager(layoutManager);
        //RecyclerView内层的展示
        FourOrderAdapter  fourOrderAdapter= new FourOrderAdapter(context,orderBean.getOrderList().get(i).getDetailList(),dingFragment);
        holder.four_rlv_id.setAdapter(fourOrderAdapter);
        //评价的删除
        holder.four_del_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("删除评价");
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
         @BindView(R.id.four_ding_id)
         TextView four_ding_id;
        @BindView(R.id.four_rlv_id)
        RecyclerView four_rlv_id;
        @BindView(R.id.four_del_id)
        ImageView four_del_id;
        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
