package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.ShowOnBean;
import com.bw.movie.fragment.ShouYeFragment;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowOnAdapter extends RecyclerView.Adapter<ShowOnAdapter.Holder> {
    Context context;
    List<ShowOnBean.ResultBean> mlist;
    ShouYeFragment  shouYeFragment;
    public ShowOnAdapter(Context context, List<ShowOnBean.ResultBean> list, ShouYeFragment shouYeFragment) {
        this.context=context;
        this.mlist=list;
        this.shouYeFragment=shouYeFragment;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.showonview, null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
         holder.showOn_iv.setImageURI(mlist.get(position).getImageUrl());
         holder.showOn_name.setText(mlist.get(position).getName());
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
        @BindView(R.id.showOn_iv)
        SimpleDraweeView showOn_iv;
        @BindView(R.id.showOn_name)
        TextView showOn_name;
        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
