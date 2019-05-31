package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.ShouBean;
import com.bw.movie.fragment.ShouYeFragment;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HotAdapter extends RecyclerView.Adapter<HotAdapter.Holder> {
    Context context;
    List<ShouBean.ResultBean> mlist;
    ShouYeFragment shouYeFragment;
    public HotAdapter(Context context, List<ShouBean.ResultBean> list, ShouYeFragment shouYeFragment) {
        this.context=context;
        this.mlist=list;
        this.shouYeFragment =shouYeFragment;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.hot_view, null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
         holder.hot_iv.setImageURI(mlist.get(position).getImageUrl());
         holder.hot_name.setText(mlist.get(position).getName());
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
        @BindView(R.id.hot_iv)
        SimpleDraweeView hot_iv;
        @BindView(R.id.hot_name)
        TextView hot_name;
        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
