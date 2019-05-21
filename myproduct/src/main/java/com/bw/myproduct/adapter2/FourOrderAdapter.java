package com.bw.myproduct.adapter2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class FourOrderAdapter extends RecyclerView.Adapter<FourOrderAdapter.Holder> {
    Context context;
    List<OrderBean.OrderListBean.DetailListBean> detailList;
    DingFragment dingFragment;
    public FourOrderAdapter(Context context, List<OrderBean.OrderListBean.DetailListBean> detailList, DingFragment dingFragment) {
        this.context=context;
        this.detailList=detailList;
        this.dingFragment=dingFragment;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //去评价的RecyclerView
        View view=View.inflate(context, R.layout.four__4_order,null);
        return new Holder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int i) {
         final String pic = detailList.get(i).getCommodityPic();
         final String[] split = pic.split(",");
         Glide.with(context).load(split[0]).into(holder.four_order_iv);
           holder.four_order_price.setText("￥:"+detailList.get(i).getCommodityPrice());
           holder.four_order_title.setText(detailList.get(i).getCommodityName());
           holder.assess_id.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   //得到外层的orderId
                   String waiId = dingFragment.getWaiId(detailList.get(i));
                       //去评价
                       int price = detailList.get(i).getCommodityPrice();
                       String name = detailList.get(i).getCommodityName();
                       dingFragment.Assess(detailList.get(i).getCommodityId(),waiId,price,name,pic,i);
               }
           });

    }

    @Override
    public int getItemCount() {
        return detailList.size();
    }

    public  class Holder extends  RecyclerView.ViewHolder{
        @BindView(R.id.four_order_iv)
        ImageView four_order_iv;
        @BindView(R.id.four_order_price)
        TextView four_order_price;
        @BindView(R.id.four_order_title)
        TextView four_order_title;
        @BindView(R.id.assess_id)
        Button assess_id;
        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
