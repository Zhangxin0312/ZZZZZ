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

public class ThreeAdapter extends RecyclerView.Adapter<ThreeAdapter.Holder> {
    Context context;
    List<ShouYe.ResultBean.PzshBean.CommodityListBeanX> commodityList;
    ShouFragment shouFragment;
    public ThreeAdapter(Context context, List<ShouYe.ResultBean.PzshBean.CommodityListBeanX> commodityList, ShouFragment shouFragment) {
        this.commodityList=commodityList;
        this.context=context;
        this.shouFragment=shouFragment;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //品质生活的recyclerView展示
        View view=View.inflate(context, R.layout.item_3,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int i) {
          //品质生活的recyclerView的title   price  iv
           holder.san_title.setText(commodityList.get(i).getCommodityName());
           holder.san_price.setText("￥"+commodityList.get(i).getPrice());
            Glide.with(context).load(commodityList.get(i).getMasterPic()).into(holder.san_iv);
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
        @BindView(R.id.san_iv)
        ImageView san_iv;
        @BindView(R.id.san_title)
        TextView san_title;
        @BindView(R.id.san_price)
        TextView  san_price;
        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
