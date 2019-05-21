package com.bw.myproduct.adapter2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.myproduct.R;
import com.bw.myproduct.beans.OrderBean;
import com.bw.myproduct.fragment.DingFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyTwoOrderAdapter  extends RecyclerView.Adapter<MyTwoOrderAdapter.Holder> {
    Context context;
    List<OrderBean.OrderListBean.DetailListBean> detailList;
    DingFragment dingFragment;
    public MyTwoOrderAdapter(Context context, List<OrderBean.OrderListBean.DetailListBean> detailList, DingFragment dingFragment) {
        this.context=context;
        this.detailList=detailList;
        this.dingFragment=dingFragment;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //待支付的RdcyclerVIew内层数据展示
        View view=View.inflate(context, R.layout.two__2_order,null);
        return new Holder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        String pic = detailList.get(i).getCommodityPic();
        String[] split = pic.split(",");
        Glide.with(context).load(split[0]).into(holder.two_order_iv);
        holder.two_order_num.setText(detailList.get(i).getCommodityCount()+"");
        holder.two_order_price.setText("￥:"+detailList.get(i).getCommodityPrice());
        holder.two_order_title.setText(detailList.get(i).getCommodityName());
    }

    @Override
    public int getItemCount() {
        return detailList.size();
    }

    public  class Holder extends  RecyclerView.ViewHolder{
        @BindView(R.id.two_order_iv)
        ImageView two_order_iv;
        @BindView(R.id.two_order_num)
        TextView two_order_num;
        @BindView(R.id.two_order_price)
        TextView two_order_price;
        @BindView(R.id.two_order_title)
        TextView two_order_title;
        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
