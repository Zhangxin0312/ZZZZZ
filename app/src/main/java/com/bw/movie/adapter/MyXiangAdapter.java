package com.bw.movie.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.RecommendBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * @Author：dell
 * @E-mail： 15001194794@163.com
 * @Date：2019/5/14 11:06
 * @Description：描述信息
 */
public class MyXiangAdapter extends RecyclerView.Adapter<MyXiangAdapter.Holder>{
    List<RecommendBean.ResultBean> list;
    Context context;

    public MyXiangAdapter(List<RecommendBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context,R.layout.layout_xiang_item,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Uri uri=Uri.parse(list.get(position).getLogo());
        holder.simpleDraweeView.setImageURI(uri);
        holder.textView_title.setText(list.get(position).getName());
        holder.textView_name.setText(list.get(position).getAddress());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        SimpleDraweeView simpleDraweeView;
        TextView textView_name,textView_title;
        public Holder(View itemView) {
            super(itemView);
            simpleDraweeView=itemView.findViewById(R.id.xiang_simple);
            textView_name=itemView.findViewById(R.id.xiang_name);
            textView_title=itemView.findViewById(R.id.xiang_title);
        }
    }
}
