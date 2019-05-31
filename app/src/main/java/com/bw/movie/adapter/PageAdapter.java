package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activity.PageListActivity;
import com.bw.movie.bean.CinemaPageBean;
import com.bw.movie.bean.MoviePageBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PageAdapter extends RecyclerView.Adapter<PageAdapter.Holder> {
    Context context;
    List<CinemaPageBean.ResultBean> mlist;
    public PageAdapter(Context context, List<CinemaPageBean.ResultBean> list) {
        this.context=context;
        this.mlist=list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.page_view, null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
         holder.page_iv_id.setImageURI(mlist.get(position).getLogo());
         holder.page_address_id.setText(mlist.get(position).getAddress());
         holder.page_name_id.setText(mlist.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public  class Holder extends  RecyclerView.ViewHolder{
        @BindView(R.id.page_iv_id)
        SimpleDraweeView page_iv_id;
        @BindView(R.id.page_name_id)
        TextView page_name_id;
        @BindView(R.id.page_address_id)
        TextView page_address_id;
        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
      }
    }
}
