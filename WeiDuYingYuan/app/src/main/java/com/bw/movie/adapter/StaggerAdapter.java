package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.bean.PersonCard;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StaggerAdapter extends RecyclerView.Adapter<StaggerAdapter.Holder> {
    Context context;
    List<PersonCard> mlist;
    public StaggerAdapter(Context context, List<PersonCard> list) {
        this.context=context;
        this.mlist=list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.stagger_view, null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        String pic = mlist.get(position).avatarUrl;
        holder.phone_iv.setImageURI(pic);
        holder.phone_iv.getLayoutParams().height= mlist.get(position).imgHeight;
    }
    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public  class Holder extends  RecyclerView.ViewHolder{
        @BindView(R.id.phone_iv)
        SimpleDraweeView phone_iv;
        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
