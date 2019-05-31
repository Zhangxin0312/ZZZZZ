package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.ShowIngBean;
import com.bw.movie.fragment.ShouYeFragment;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowingAdapter extends RecyclerView.Adapter<ShowingAdapter.Holder> {
    Context context;
    List<ShowIngBean.ResultBean> mlist;
    ShouYeFragment shouYeFragment;
    public ShowingAdapter(Context context, List<ShowIngBean.ResultBean> list, ShouYeFragment shouYeFragment) {
        this.context=context;
        this.mlist=list;
        this.shouYeFragment=shouYeFragment;
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.showing_view, null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
         holder.showIng_iv.setImageURI(mlist.get(position).getImageUrl());
         holder.showIng_name.setText(mlist.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shouYeFragment.Jump();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public  class Holder extends  RecyclerView.ViewHolder{
        @BindView(R.id.showIng_iv)
        SimpleDraweeView showIng_iv;
        @BindView(R.id.showIng_name)
        TextView showIng_name;
        public Holder(View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }
}
