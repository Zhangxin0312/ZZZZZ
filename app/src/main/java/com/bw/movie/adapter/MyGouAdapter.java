package com.bw.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activity.XuanZuoActivity;
import com.bw.movie.app.App;
import com.bw.movie.bean.GouBean;

import java.util.List;

/**
 * @Author：dell
 * @E-mail： 15001194794@163.com
 * @Date：2019/5/17 10:36
 * @Description：描述信息
 */
public class MyGouAdapter extends RecyclerView.Adapter<MyGouAdapter.Holder>{
    List<GouBean.ResultBean> list;
    Context context;

    public MyGouAdapter(List<GouBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.layout_gou,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
         holder.textView_title.setText(list.get(position).getScreeningHall());
         holder.textView_time.setText(list.get(position).getBeginTime());
         holder.textView_price.setText(list.get(position).getEndTime()+"end");
         holder.textView_pric.setText("￥"+list.get(position).getPrice()+"");

        App.Chenprice=list.get(position).getPrice();
         holder.itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 App.Chentime=list.get(position).getBeginTime();
                 App.Chentimeend=list.get(position).getEndTime();
                 App.Chentitle=list.get(position).getScreeningHall();

                 Intent intent=new Intent(context,XuanZuoActivity.class);
                 context.startActivity(intent);
             }
         });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        TextView textView_title,textView_time,textView_price,textView_pric;
        public Holder(View itemView) {
            super(itemView);
            textView_title=itemView.findViewById(R.id.g_title);
            textView_time=itemView.findViewById(R.id.g_time);
            textView_price=itemView.findViewById(R.id.g_price);
            textView_pric=itemView.findViewById(R.id.g_pric);
        }
    }

}
