package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.XiangQingBean;

import java.util.List;

/**
 * @Author：dell
 * @E-mail： 15001194794@163.com
 * @Date：2019/5/16 16:15
 * @Description：描述信息
 */
public class MyXiangQingAdapter extends RecyclerView.Adapter<MyXiangQingAdapter.Holder>{
    List<XiangQingBean.ResultBean> list;
    Context context;
    MyXiId myXiId;

    public void setMyXiId(MyXiId myXiId) {
        this.myXiId = myXiId;
    }

    public MyXiangQingAdapter(List<XiangQingBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.layout_xiang,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.textView1.setText(list.get(position).getAddress());
        holder.textView2.setText(list.get(position).getPhone());
        holder.textView3.setText(list.get(position).getVehicleRoute());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        TextView textView1,textView2,textView3,textView4;
        public Holder(View itemView) {
            super(itemView);
            textView1=itemView.findViewById(R.id.text_address);
            textView2=itemView.findViewById(R.id.text_phone);
            textView3=itemView.findViewById(R.id.text_luxian);

        }
    }
    public interface MyXiId{
        public void success(int id);
    }
}
