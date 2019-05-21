package com.bw.myproduct.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.myproduct.R;
import com.bw.myproduct.bean.Pop;
import com.bw.myproduct.fragment.ShouFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PopAdapter extends RecyclerView.Adapter<PopAdapter.Holder> {
    Context context;
    List<Pop.ResultBean> xlist;
    ShouFragment shouFragment;
    public PopAdapter(Context context, List<Pop.ResultBean> list, ShouFragment shouFragment) {
        this.context=context;
        this.xlist=list;
        this.shouFragment =shouFragment;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
             //popwindow第一层的展示  第一层 name
             View  view=View.inflate(context, R.layout.pop_adapter,null);
             return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int i) {
            //获取第一层的name
            holder.pop_name.setText(xlist.get(i).getName());
            holder.pop_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //点击事件获取id
                    shouFragment.getPopId(xlist.get(i).getId());
                }
            });
    }

    @Override
    public int getItemCount() {
        return xlist.size();
    }

    public  class Holder extends  RecyclerView.ViewHolder{
        @BindView(R.id.pop_name)
        TextView pop_name;
        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
