package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.bean.ShouBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BanAdapter extends RecyclerView.Adapter<BanAdapter.Holder> {
    Context context;
    List<ShouBean.ResultBean> mlist;

    public BanAdapter(Context context, List<ShouBean.ResultBean> list) {
        this.context=context;
        this.mlist=list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.ban_view, null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
         holder.ban_iv.setImageURI(mlist.get(position).getImageUrl());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public  class Holder extends  RecyclerView.ViewHolder{
        @BindView(R.id.ban_iv)
        SimpleDraweeView ban_iv;
        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
      }
    }
}
