package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activity.XiangActivity;
import com.bw.movie.bean.ShouBean;
import com.bw.movie.bean.ShowIngBean;
import com.bw.movie.bean.ShowOnBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class XiangAdapter extends RecyclerView.Adapter<XiangAdapter.Holder> {
    Context context;
    List<ShouBean.ResultBean> xlist;
    List<ShowIngBean.ResultBean> mlist;
    List<ShowOnBean.ResultBean> plist;
    int flag;
    XiangActivity activity;
    boolean flags=false;
    public XiangAdapter(Context context,List<ShouBean.ResultBean> list,  List<ShowIngBean.ResultBean> list1, List<ShowOnBean.ResultBean> list2, int flag) {
        this.context=context;
        this.xlist=list;
        this.mlist=list1;
        this.plist=list2;
        this.flag=flag;
        activity= (XiangActivity) context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.xiang_view, null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int position) {
        if(flag==1){
            holder.xiang_iv.setImageURI(xlist.get(position).getImageUrl());
            holder.xiang_name.setText(xlist.get(position).getName());
            holder.xiang_title.setText(xlist.get(position).getSummary());
            if(xlist.get(position).getFollowMovie()!=2){
                holder.attention_id.setImageResource(R.mipmap.com_icon_collection_selected_xhdpi);
            }else{
                holder.attention_id.setImageResource(R.mipmap.com_icon_collection_default);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.getId(xlist.get(position).getId());
                }
            });

            holder.attention_id.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(xlist.get(position).isFlags()){
                        //取消关注
                        activity.Cancel(xlist.get(position).getId());
                         xlist.get(position).setFlags(false);
                        holder.attention_id.setImageResource(R.mipmap.com_icon_collection_default);
                    }else
                    {
                        //关注
                        activity.Attention(xlist.get(position).getId());
                        xlist.get(position).setFlags(true);
                        holder.attention_id.setImageResource(R.mipmap.com_icon_collection_selected_xhdpi);
                    }

                }
            });
        }else if(flag==2)
        {
            holder.xiang_iv.setImageURI(mlist.get(position).getImageUrl());
            holder.xiang_name.setText(mlist.get(position).getName());
            holder.xiang_title.setText(mlist.get(position).getSummary());
            if(mlist.get(position).getFollowMovie()!=2){
                holder.attention_id.setImageResource(R.mipmap.com_icon_collection_selected_xhdpi);
            }else{
                holder.attention_id.setImageResource(R.mipmap.com_icon_collection_default);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.getId(mlist.get(position).getId());
                }
            });

            holder.attention_id.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mlist.get(position).isFlags()){
                        //取消关注
                        activity.Cancel(mlist.get(position).getId());
                        mlist.get(position).setFlags(false);
                        holder.attention_id.setImageResource(R.mipmap.com_icon_collection_default);
                    }else
                    {
                        //关注
                        activity.Attention(mlist.get(position).getId());
                        mlist.get(position).setFlags(true);
                        holder.attention_id.setImageResource(R.mipmap.com_icon_collection_selected_xhdpi);
                    }
                }
            });
        }else
        {
            holder.xiang_iv.setImageURI(plist.get(position).getImageUrl());
            holder.xiang_name.setText(plist.get(position).getName());
            holder.xiang_title.setText(plist.get(position).getSummary());
            if(plist.get(position).getFollowMovie()!=2){
                holder.attention_id.setImageResource(R.mipmap.com_icon_collection_selected_xhdpi);
            }else{
                holder.attention_id.setImageResource(R.mipmap.com_icon_collection_default);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.getId(plist.get(position).getId());
                }
            });

            holder.attention_id.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(plist.get(position).isFlags()){
                        //取消关注
                        activity.Cancel(plist.get(position).getId());
                        plist.get(position).setFlags(false);
                        holder.attention_id.setImageResource(R.mipmap.com_icon_collection_default);
                    }else
                    {
                        //关注
                        activity.Attention(plist.get(position).getId());
                        plist.get(position).setFlags(true);
                        holder.attention_id.setImageResource(R.mipmap.com_icon_collection_selected_xhdpi);
                    }
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if(flag==1){
            return xlist.size();
        }else if (flag==2){
            return mlist.size();
        }else
        {
            return plist.size();
        }
    }

    public  class Holder extends  RecyclerView.ViewHolder{
        @BindView(R.id.xiang_iv)
        SimpleDraweeView xiang_iv;
        @BindView(R.id.xiang_name)
        TextView xiang_name;
        @BindView(R.id.xiang_title)
        TextView xiang_title;
        @BindView(R.id.attention_id)
        ImageView attention_id;
        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
