package com.bw.myproduct.adapter2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

public class MyQuan_2_Adapter extends RecyclerView.Adapter<MyQuan_2_Adapter.holder> {
    Context context;
    List<MyQuanBean.ResultBean> mlist;
    MyQuanActivity activity;
    boolean flag = true;
    public MyQuan_2_Adapter(Context context, List<MyQuanBean.ResultBean> list) {
        this.mlist=list;
        this.context=context;
        activity= (MyQuanActivity) context;
    }
    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //一行三个图片
        View view=View.inflate(context, R.layout.myquan_2_view,null);
        return new holder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final holder holder, final int i) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd  HH:mm");
        Date date=new Date(System.currentTimeMillis());
        holder.myquan_2_tou.setImageURI(mlist.get(i).getHeadPic());
        holder.myquan_2_name.setText(mlist.get(i).getNickName());
        holder.myquan_2_time.setText(simpleDateFormat.format(date));
        holder.myquan_2_content.setText(mlist.get(i).getContent());
        holder.myquan_2_iv.setImageURI(mlist.get(i).getImage());
        holder.myquan_2_num.setText(mlist.get(i).getGreatNum()+"");

        holder.myquan_2_conform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag == true){
                    flag = false;
                    holder.myquan_2_conform.setBackgroundResource(R.mipmap.common_btn_prise_s_hdpi);
                    holder.myquan_2_num.setTextColor(Color.RED);
                    activity.getconfirmId( mlist.get(i).getId());
                    int num =Integer.parseInt( holder.myquan_2_num.getText().toString());
                    holder.myquan_2_num.setText(""+num++);
                    mlist.get(i).setGreatNum(num);

                }else {
                    flag = true;
                    holder.myquan_2_conform.setBackgroundResource(R.mipmap.common_btn_prise_n_hdpi);
                    holder.myquan_2_num.setTextColor(Color.GRAY);
                    activity.getCancle( mlist.get(i).getId());
                    int num =Integer.parseInt( holder.myquan_2_num.getText().toString());
                    holder.myquan_2_num.setText(""+num);
                    mlist.get(i).setGreatNum(num);
                }
            }
        });


        if(mlist.get(i).isFlag()){
            holder.myquan_2_cb.setVisibility(View.VISIBLE);
        }else
        {
            holder.myquan_2_cb.setVisibility(View.GONE);
        }
        holder.myquan_2_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("删除圈子");
                builder.setMessage("是否确认删除");
                //点击对话框以外的区域是否让对话框消失
                builder.setCancelable(true);
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        activity.delete(mlist.get(i).getId());
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        holder.myquan_2_cb.setVisibility(View.GONE);
                    }
                });
                 builder.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class holder extends RecyclerView.ViewHolder{
        SimpleDraweeView myquan_2_tou;
        TextView myquan_2_name;
        TextView myquan_2_time,myquan_2_num;
        TextView myquan_2_content;
        SimpleDraweeView myquan_2_iv;
        ImageView myquan_2_conform;
        CheckBox myquan_2_cb;
        public holder(@NonNull View itemView) {
            super(itemView);
            myquan_2_tou= itemView.findViewById(R.id.myquan_2_tou);
            myquan_2_name= itemView.findViewById(R.id.myquan_2_name);
            myquan_2_time= itemView.findViewById(R.id.myquan_2_time);
            myquan_2_conform= itemView.findViewById(R.id.myquan_2_conform);
            myquan_2_num= itemView.findViewById(R.id.myquan_2_num);
            myquan_2_content= itemView.findViewById(R.id.myquan_2_content);
            myquan_2_iv= itemView.findViewById(R.id.myquan_2_iv);
            myquan_2_cb=itemView.findViewById(R.id.myquan_2_cb);
        }
    }
}
