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
import com.bw.myproduct.bean.ShouYe;
import com.bw.myproduct.fragment.ShouFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FirstAdapter  extends RecyclerView.Adapter<FirstAdapter.Holder> {
    Context context;
    List<ShouYe.ResultBean.RxxpBean.CommodityListBean> commodityList;
    ShouFragment   shouFragment;
    public FirstAdapter(Context context, List<ShouYe.ResultBean.RxxpBean.CommodityListBean> commodityList, ShouFragment shouFragment) {
        this.commodityList=commodityList;
        this.context=context;
        this.shouFragment=shouFragment;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //热销新品的RecyclerView展示
        View view=View.inflate(context, R.layout.item_1,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int i) {
           //热销新品的  ttle   price   iv
           holder.yi_title.setText(commodityList.get(i).getCommodityName());
           holder.yi_price.setText("￥"+commodityList.get(i).getPrice());
           Glide.with(context).load(commodityList.get(i).getMasterPic()).into(holder.yi_iv);
           //itemView的点击详情事件  获取id
           holder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   shouFragment.getId(commodityList.get(i).getCommodityId());
               }
           });
    }

    @Override
    public int getItemCount() {
        return commodityList.size();
    }

    public  class Holder  extends  RecyclerView.ViewHolder{
        @BindView(R.id.yi_iv)
        ImageView yi_iv;
        @BindView(R.id.yi_title)
        TextView yi_title;
        @BindView(R.id.yi_price)
        TextView yi_price;
        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
