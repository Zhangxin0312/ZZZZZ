package com.bw.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activity.RecommendXiangActivity;
import com.bw.movie.app.App;
import com.bw.movie.bean.RecommendBean;
import com.bw.movie.contract.ContractInterface;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * @Author：dell
 * @E-mail： 15001194794@163.com
 * @Date：2019/5/13 15:25
 * @Description：描述信息
 */
public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.Holder>{
    List<RecommendBean.ResultBean> list;
    Context context;
    ContractInterface.PresenterInterface presenterInterface;
    MyId myId;

    public void setMyId(MyId myId) {
        this.myId = myId;
    }

    public RecommendAdapter(List<RecommendBean.ResultBean> list, Context context, ContractInterface.PresenterInterface presenterInterface) {
        this.list = list;
        this.context = context;
        this.presenterInterface=presenterInterface;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context,R.layout.layout_recommend_item,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int position) {
        Uri uri=Uri.parse(list.get(position).getLogo());
        holder.recommend_simple.setImageURI(uri);
        holder.recommend_name.setText(list.get(position).getName());
        holder.recommend_address.setText(list.get(position).getAddress());
        holder.recommend_distance.setText(list.get(position).getDistance()+"km");


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.Ximage=list.get(position).getLogo();
                App.Xtitle=list.get(position).getName();
                App.Xname=list.get(position).getAddress();
                App.CinemaId=list.get(position).getId();
                Intent intent=new Intent(context,RecommendXiangActivity.class);
                context.startActivity(intent);

            }
        });

        holder.imageView_heart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(list.get(position).getFollowCinema()==1){
                    myId.succ(list.get(position).getId(),list.get(position).getFollowCinema()==1);
                    Resources resources = context.getResources();
                    Drawable drawable = resources.getDrawable(R.mipmap.xin);
                    holder.imageView_heart.setBackground(drawable);

                }else{
                    myId.succ(list.get(position).getId(),list.get(position).getFollowCinema()==2);
                    Resources resources = context.getResources();

                    Drawable drawable = resources.getDrawable(R.mipmap.xinx);
                    holder.imageView_heart.setBackground(drawable);

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        SimpleDraweeView recommend_simple;
        TextView recommend_name,recommend_address,recommend_distance;
        CheckBox imageView_heart;
        public Holder(View itemView) {
            super(itemView);
            imageView_heart=itemView.findViewById(R.id.xin_id);
            recommend_simple=itemView.findViewById(R.id.recommend_simple);
            recommend_name=itemView.findViewById(R.id.resommend_name);
            recommend_address=itemView.findViewById(R.id.resommend_address);
            recommend_distance=itemView.findViewById(R.id.resommend_distance);
        }
    }
    public interface MyId{
        public void succ(int id, boolean ischeck);
    }
}
