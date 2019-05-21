package com.bw.myproduct.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.myproduct.R;
import com.bw.myproduct.bean.Pop_2;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PopAdapter_2 extends RecyclerView.Adapter<PopAdapter_2.Holder> {
    List<Pop_2.ResultBean> mlist;
    Context context;
    public PopAdapter_2(List<Pop_2.ResultBean> list, Context context) {
         this.mlist=list;
         this.context=context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //pop的name
        View view=View.inflate(context, R.layout.pop_adapter_2,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
          holder.pop_name_2.setText(mlist.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public  class Holder  extends  RecyclerView.ViewHolder{
        @BindView(R.id.pop_name_2)
        TextView pop_name_2;
        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
