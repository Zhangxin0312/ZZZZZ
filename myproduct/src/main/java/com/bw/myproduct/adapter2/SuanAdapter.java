package com.bw.myproduct.adapter2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.myproduct.R;
import com.bw.myproduct.beans.GouBeans;

import java.util.List;
//展示中间的数据
public class SuanAdapter extends RecyclerView.Adapter<SuanAdapter.Holder> {
    Context context;
    List<GouBeans> mlist;
    public SuanAdapter(Context context, List<GouBeans> list) {
        this.context=context;
        this.mlist=list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //结算的RecyclerView的数据展示
        View view=View.inflate(context, R.layout.suan_adapter,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int i) {
           mlist.get(i).setNum(1);
           holder.suan_title.setText(mlist.get(i).getCommodityName());
           Glide.with(context).load(mlist.get(i).getPic()).into(holder.suan_iv);
           holder.suan_price.setText("￥"+mlist.get(i).getPrice());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public  class Holder  extends  RecyclerView.ViewHolder{
        ImageView suan_iv;
        TextView suan_title,suan_price;
        public Holder(@NonNull View itemView) {
            super(itemView);
            suan_iv=itemView.findViewById(R.id.suan_iv);
            suan_title=itemView.findViewById(R.id.suan_title);
            suan_price=itemView.findViewById(R.id.suan_price);
        }
    }
}
