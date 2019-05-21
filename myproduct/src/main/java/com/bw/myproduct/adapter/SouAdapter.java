package com.bw.myproduct.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.myproduct.R;
import com.bw.myproduct.bean.Sou;
import com.bw.myproduct.fragment.ProductFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SouAdapter  extends RecyclerView.Adapter<SouAdapter.Holder> {
    Context context;
    List<Sou.ResultBean> mlist;
    ProductFragment productFragment;
    public SouAdapter(Context context, List<Sou.ResultBean> mlist, ProductFragment productFragment) {
        this.context=context;
        this.mlist=mlist;
        this.productFragment=productFragment;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //搜索的展示
         View view=View.inflate(context, R.layout.sou_adapter,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int i) {
        //搜索出来要展示的title  price  num  iv
        holder.sou_title.setText(mlist.get(i).getCommodityName());
        holder.sou_price.setText("￥"+mlist.get(i).getPrice());
        holder.sou_num.setText("已售"+mlist.get(i).getSaleNum()+"件");
        Glide.with(context).load(mlist.get(i).getMasterPic()).into(holder.sou_iv);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productFragment.getIds(mlist.get(i).getCommodityId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public  class Holder extends RecyclerView.ViewHolder{
        @BindView(R.id.sou_iv)
        ImageView sou_iv;
        @BindView(R.id.sou_title)
        TextView sou_title;
        @BindView(R.id.sou_price)
        TextView sou_price;
        @BindView(R.id.sou_num)
        TextView  sou_num;
        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
