package com.bw.myproduct.adapter2;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.myproduct.R;
import com.bw.myproduct.beans.MyQuanBean;
import com.bw.myproduct.view.MyQuanActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MyQuan_1_Adapter extends RecyclerView.Adapter<MyQuan_1_Adapter.holder> {
    Context context;
    List<MyQuanBean.ResultBean> mlist;
    MyQuanActivity activity;
    public MyQuan_1_Adapter(Context context, List<MyQuanBean.ResultBean> list) {
        this.mlist=list;
        this.context=context;
        activity= (MyQuanActivity) context;
    }


    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //一左两右 三个图片
        View view=View.inflate(context, R.layout.my_quan_1_view,null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final holder holder, final int i) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        holder.myquan_1_tou.setImageURI(mlist.get(i).getHeadPic());
        holder.myquan_1_name.setText(mlist.get(i).getNickName());
        holder.myquan_1_time.setText(simpleDateFormat.format(date));
        holder.myquan_1_content.setText(mlist.get(i).getContent());
        holder.myquan_1_num.setText(mlist.get(i).getGreatNum()+"");
        if(mlist.get(i).isFlag()){
            holder.myquan_1_cb.setVisibility(View.VISIBLE);
        }else
        {
            holder.myquan_1_cb.setVisibility(View.GONE);
        }
        holder.myquan_1_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.delete(mlist.get(i).getId());
            }
        });
    }
    @Override
    public int getItemCount() {
        return mlist.size();
    }
    public class holder extends RecyclerView.ViewHolder{
        SimpleDraweeView myquan_1_tou;
        TextView myquan_1_name;
        TextView myquan_1_time;
        TextView myquan_1_content;
        TextView myquan_1_num;
        ImageView myquan_1_conform;
        CheckBox myquan_1_cb;
        public holder(@NonNull View itemView) {
            super(itemView);
            myquan_1_tou= itemView.findViewById(R.id.myquan_1_tou);
            myquan_1_name= itemView.findViewById(R.id.myquan_1_name);
            myquan_1_time= itemView.findViewById(R.id.myquan_1_time);
            myquan_1_content= itemView.findViewById(R.id.myquan_1_content);
            myquan_1_num=itemView.findViewById(R.id.myquan_1_num);
            myquan_1_conform=itemView.findViewById(R.id.myquan_1_conform);
            myquan_1_cb=itemView.findViewById(R.id.myquan_1_cb);
        }
    }
}
