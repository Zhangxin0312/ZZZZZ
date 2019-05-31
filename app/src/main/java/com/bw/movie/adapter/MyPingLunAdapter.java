package com.bw.movie.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.PingLunBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @Author：dell
 * @E-mail： 15001194794@163.com
 * @Date：2019/5/16 19:04
 * @Description：描述信息
 */
public class MyPingLunAdapter extends RecyclerView.Adapter<MyPingLunAdapter.Holder>{
    List<PingLunBean.ResultBean> list;
    Context context;
    MyIdCheck myIdCheck;
    int aa;

    public void setMyIdCheck(MyIdCheck myIdCheck) {
        this.myIdCheck = myIdCheck;
    }

    public MyPingLunAdapter(List<PingLunBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_pinglun,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int position) {
        Uri uri=Uri.parse(list.get(position).getCommentHeadPic());
        holder.simpleDraweeView.setImageURI(uri);
       // Glide.with(context).load(list.get(position).getCommentUserId()).into(holder.simpleDraweeView);
        holder.textView1.setText(list.get(position).getCommentUserName());
        holder.textView2.setText(list.get(position).getCommentContent());
        holder.textView4.setText(list.get(position).getGreatNum()+"");
      //  holder.img.setImageResource(list.get(position).getIsGreat());

        //将毫秒值转换为年月日导包import java.text.SimpleDateFormat;


        //设置毫秒值
        int date= (int) list.get(position).getCommentTime();

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
        // time为转换格式后的字符串
        String time = dateFormat.format(new Date(date));
        holder.textView3.setText(time);

        //点赞

                /*if(list.get(position).getFollowCinema()==1){
                    myId.succ(list.get(position).getId(),list.get(position).getFollowCinema()==1);
                    Resources resources = context.getResources();
                    Drawable drawable = resources.getDrawable(R.mipmap.xin);
                    holder.imageView_heart.setBackground(drawable);

                }else{
                    myId.succ(list.get(position).getId(),list.get(position).getFollowCinema()==2);
                    Resources resources = context.getResources();

                    Drawable drawable = resources.getDrawable(R.mipmap.xinx);
                    holder.imageView_heart.setBackground(drawable);

                }*/
        if (list.get(position).getIsGreat()==0){
            holder.img.setImageResource(R.mipmap.wedian);
          //  holder.img.setImageDrawable(ContextCompat.getDrawable(context,R.mipmap.wedian));
        }else {
            holder.img.setImageResource(R.mipmap.dianzan);
          //  holder.img.setImageDrawable(ContextCompat.getDrawable(context,R.mipmap.dianzan));
        }
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (list.get(position).getIsGreat()==0){
                   aa=list.get(position).getGreatNum();
                   aa++;
                   holder.textView4.setText(aa+"");
                    list.get(position).setIsGreat(1);
                    list.get(position).setGreatNum(aa);
                  holder.img.setImageResource(R.mipmap.dianzan);
                    myIdCheck.success(list.get(position).getCommentId());
//                }else {
//                    holder.img.setImageDrawable(ContextCompat.getDrawable(context,R.mipmap.dianzan));
//                    myIdCheck.success(list.get(position).getCommentId());
//                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        SimpleDraweeView simpleDraweeView;
        TextView textView1,textView2,textView3,textView4;
        ImageView img;
        public Holder(View itemView) {
            super(itemView);
            simpleDraweeView=itemView.findViewById(R.id.p_simple);
            textView1=itemView.findViewById(R.id.p_text1);
            textView2=itemView.findViewById(R.id.p_text2);
            textView3=itemView.findViewById(R.id.p_text3);
            textView4=itemView.findViewById(R.id.p_text4);
            img=itemView.findViewById(R.id.dianzan);

        }
    }

    public interface MyIdCheck{
        public void success(int id);
    }
}
