package com.bw.myproduct.adapter2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.myproduct.R;
import com.bw.myproduct.beans.OrderBean;
import com.bw.myproduct.fragment.DingFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FiveOrderAdapter extends RecyclerView.Adapter<FiveOrderAdapter.Holder> {
    Context context;
    List<OrderBean.OrderListBean.DetailListBean> detailList;
    DingFragment dingFragment;
    public FiveOrderAdapter(Context context, List<OrderBean.OrderListBean.DetailListBean> detailList, DingFragment dingFragment) {
        this.context=context;
        this.detailList=detailList;
        this.dingFragment=dingFragment;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //去评价的RecyclerView
        View view=View.inflate(context, R.layout.five__5_order,null);
        return new Holder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int i) {
          String pic = detailList.get(i).getCommodityPic();
          String[] split = pic.split(",");
          Glide.with(context).load(split[0]).into(holder.five_order_iv);
           holder.five_order_price.setText("￥:"+detailList.get(i).getCommodityPrice());
           holder.five_order_title.setText(detailList.get(i).getCommodityName());
    }

    @Override
    public int getItemCount() {
        return detailList.size();
    }

    public  class Holder extends  RecyclerView.ViewHolder{
        @BindView(R.id.five_order_iv)
        ImageView five_order_iv;
        @BindView(R.id.five_order_price)
        TextView five_order_price;
        @BindView(R.id.five_order_title)
        TextView five_order_title;
        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
