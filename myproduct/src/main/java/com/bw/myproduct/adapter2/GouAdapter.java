package com.bw.myproduct.adapter2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.myproduct.R;
import com.bw.myproduct.beans.GouBean;
import com.bw.myproduct.beans.GouBeans;
import com.bw.myproduct.fragment.GouFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GouAdapter extends RecyclerView.Adapter<GouAdapter.Holder> {
    Context context;
    List<GouBean.ResultBean> mlist;
    GouFragment gouFragment;
    public GouAdapter(Context context, List<GouBean.ResultBean> list, GouFragment gouFragment) {
         this.context=context;
         this.mlist=list;
         this.gouFragment=gouFragment;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //购物车数据的展示
        View view=View.inflate(context, R.layout.gou_adapter,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int i) {
           mlist.get(i).setNum(1);
           holder.gou_title.setText(mlist.get(i).getCommodityName());
           Glide.with(context).load(mlist.get(i).getPic()).into(holder.gou_iv);
           holder.gou_price.setText(mlist.get(i).getPrice()+"");
           holder.gou_cb.setChecked(mlist.get(i).isFlag());
           holder.gou_cb.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   //点击CheckBox的时候  让外层选中
                   gouFragment.getWai(holder.gou_cb.isChecked(),i);
                   //计算总价
                   gouFragment.toSum();
               }
           });
           holder.jia.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   int n = Integer.parseInt(holder.nums.getText().toString());
                   n++;
                   mlist.get(i).setNum(n);
                   holder.nums.setText(n+"");
                   gouFragment.toSum();
               }
           });
        holder.jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int n = Integer.parseInt(holder.nums.getText().toString());
                n--;
                if(n<=1){
                    n=1;
                    Toast.makeText(context,"已经最少了，不能在减了",Toast.LENGTH_SHORT).show();
                }

                mlist.get(i).setNum(n);
                holder.nums.setText(n+"");
                gouFragment.toSum();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public  class Holder  extends  RecyclerView.ViewHolder{
        @BindView(R.id.gou_iv)
        ImageView gou_iv;
        @BindView(R.id.gou_cb)
        CheckBox gou_cb;
        @BindView(R.id.gou_title)
        TextView gou_title;
        @BindView(R.id.gou_price)
        TextView  gou_price;
        @BindView(R.id.jia)
        TextView jia;
        @BindView(R.id.jian)
        TextView jian;
        @BindView(R.id.nums)
        Button nums;
        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
