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

public class TwoAdapter extends RecyclerView.Adapter<TwoAdapter.Holder> {
    Context context;
    List<ShouYe.ResultBean.MlssBean.CommodityListBeanXX> commodityList;
    ShouFragment shouFragment;
    public TwoAdapter(Context context, List<ShouYe.ResultBean.MlssBean.CommodityListBeanXX> commodityList, ShouFragment shouFragment) {
        this.commodityList=commodityList;
        this.context=context;
        this.shouFragment=shouFragment;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //魔力时尚的RecyclerView
        View view=View.inflate(context, R.layout.item_2,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int i) {
          //RecyclerView的 title  price   iv
           holder.er_title.setText(commodityList.get(i).getCommodityName());
           holder.er_price.setText("￥"+commodityList.get(i).getPrice());
           Glide.with(context).load(commodityList.get(i).getMasterPic()).into(holder.er_iv);
           //详情的点击事件
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
        @BindView(R.id.er_iv)
        ImageView er_iv;
        @BindView(R.id.er_title)
        TextView er_title;
        @BindView(R.id.er_price)
        TextView  er_price;
        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
