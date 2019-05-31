package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.XiTongBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @Author：dell
 * @E-mail： 15001194794@163.com
 * @Date：2019/5/21 8:42
 * @Description：描述信息
 */
public class MyXiTongAdapter extends RecyclerView.Adapter<MyXiTongAdapter.Holder>{
    List<XiTongBean.ResultBean> list;
    Context context;
    long date;
    int a;

    public MyXiTongAdapter(List<XiTongBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context,R.layout.layout_xitong,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.textView_xinxi.setText(list.get(position).getTitle());
        holder.g_piao.setText(list.get(position).getContent());
        //设置毫秒值
        date = list.get(position).getPushTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.CHINA);
        // time为转换格式后的字符串
        String time = dateFormat.format(new Date(date));
        holder.g_time.setText(time);
        a = 0;


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        TextView textView_xinxi,g_piao,g_dian,g_time,g_time1,g_time2,g_time3,g_time4;
        public Holder(View itemView) {
            super(itemView);
            textView_xinxi=itemView.findViewById(R.id.g_xinxi);
            g_piao=itemView.findViewById(R.id.g_piao);
            g_time=itemView.findViewById(R.id.g_time);

        }
    }
}
