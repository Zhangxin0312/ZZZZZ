package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activity.MovieActivity;
import com.bw.movie.bean.AssessBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AssessAdapter extends RecyclerView.Adapter<AssessAdapter.Holder> {
    Context context;
    List<AssessBean.ResultBean> mlist;
    MovieActivity activity;
    public AssessAdapter(Context context, List<AssessBean.ResultBean> list) {
        this.context=context;
        this.mlist=list;
        activity= (MovieActivity) context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.assess_item, null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int position) {
         holder.assess_iv.setImageURI(mlist.get(position).getCommentHeadPic());
         holder.assess_name.setText(mlist.get(position).getCommentUserName());
         holder.assess_content.setText(mlist.get(position).getCommentContent());
         //获取当前时间
         SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
         Date date=new Date(System.currentTimeMillis());
         holder.assess_time.setText(format.format(date));
         holder.assess_zan.setText(mlist.get(position).getGreatNum()+"");
         holder.assess_num.setText(mlist.get(position).getReplyNum()+"");
         if(mlist.get(position).getIsGreat()==1){
             holder.dian_id.setImageResource(R.mipmap.com_icon_praise_selected_hdpi);
         }else
         {
             holder.dian_id.setImageResource(R.mipmap.com_icon_praise_default_hdpi);
         }
         //评论
         holder.assess_ping_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.Ping(mlist.get(position).getCommentId());
            }
        });
         //点赞
        holder.dian_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.Dian(mlist.get(position).getCommentId());
                holder.dian_id.setImageResource(R.mipmap.com_icon_praise_selected_hdpi);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public  class Holder extends  RecyclerView.ViewHolder{
        @BindView(R.id.assess_iv)
        SimpleDraweeView assess_iv;
        @BindView(R.id.assess_name)
        TextView assess_name;
        @BindView(R.id.assess_content)
        TextView assess_content;
        @BindView(R.id.assess_time)
        TextView assess_time;
        @BindView(R.id.assess_zan)
        TextView assess_zan;
        @BindView(R.id.assess_num)
        TextView assess_num;
        @BindView(R.id.assess_ping_id)
        ImageView assess_ping_id;
        @BindView(R.id.dian_id)
        ImageView  dian_id;
        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
