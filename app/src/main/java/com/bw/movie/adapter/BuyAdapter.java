package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activity.BuyActivity;
import com.bw.movie.bean.FindBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BuyAdapter extends RecyclerView.Adapter<BuyAdapter.Holder> {
    Context context;
    List<FindBean.ResultBean> mlist;
    BuyActivity activity;
    public BuyAdapter(Context context, List<FindBean.ResultBean> list) {
        this.context=context;
        this.mlist=list;
        activity= (BuyActivity) context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.find_view, null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int position) {
         holder.find_iv.setImageURI(mlist.get(position).getLogo());
         holder.find_name.setText(mlist.get(position).getName());
         holder.find_address.setText(mlist.get(position).getAddress());
         holder.find_num.setText(mlist.get(position).getDistance()+"km");
        if(mlist.get(position).isFlag()){
            holder.fllow_id.setImageResource(R.mipmap.com_icon_collection_selected_xhdpi);

        }else
        {
            holder.fllow_id.setImageResource(R.mipmap.com_icon_collection_default_xhdpi);
        }
         holder.fllow_id.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(mlist.get(position).isFlag()){
                     //取消关注
                     activity.CancleFllow(mlist.get(position).getId());
                 }else
                 {
                     //关注
                     activity.Fllow(mlist.get(position).getId());
                     mlist.get(position).setFlag(true);
                 }
             }
         });
         holder.itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 activity.Jump(mlist.get(position).getId(),mlist.get(position).getName(),mlist.get(position).getAddress());
             }
         });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public  class Holder extends  RecyclerView.ViewHolder{
        @BindView(R.id.find_iv)
        SimpleDraweeView find_iv;
        @BindView(R.id.find_name)
        TextView find_name;
        @BindView(R.id.find_address)
        TextView find_address;
        @BindView(R.id.find_num)
        TextView find_num;
        @BindView(R.id.fllow_id)
        ImageView fllow_id;
        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
