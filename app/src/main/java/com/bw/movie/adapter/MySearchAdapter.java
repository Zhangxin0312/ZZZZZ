package com.bw.movie.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.bean.SearchBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * @Author：dell
 * @E-mail： 15001194794@163.com
 * @Date：2019/5/24 14:44
 * @Description：描述信息
 */
public class MySearchAdapter extends RecyclerView.Adapter<MySearchAdapter.Holder>{
    List<SearchBean.ResultBean> list;
    Context context;

    public MySearchAdapter(List<SearchBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.layout_search,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Uri uri=Uri.parse(list.get(position).getLogo());
        holder.simpleDraweeView.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        SimpleDraweeView simpleDraweeView;
        public Holder(View itemView) {
            super(itemView);
            simpleDraweeView=itemView.findViewById(R.id.search_img);
        }
    }
}
