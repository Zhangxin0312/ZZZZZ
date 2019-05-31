package com.bw.movie.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.app.App;
import com.bw.movie.bean.ZhengMovieBean;
import com.bw.movie.contract.ContractInterface;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import recycler.coverflow.RecyclerCoverFlow;

/**
 * @Author：dell
 * @E-mail： 15001194794@163.com
 * @Date：2019/5/14 20:09
 * @Description：描述信息
 */
public class RecyflowAdapter extends RecyclerCoverFlow.Adapter<RecyflowAdapter.Holder>{

    List<ZhengMovieBean.ResultBean> list;
    Context context;
    ContractInterface.PresenterInterface presenterInterface;
    MyGouAdapter adapter;
    MyOnClickListener myOnClickListener;

    public void setMyOnClickListener(MyOnClickListener myOnClickListener) {
        this.myOnClickListener = myOnClickListener;
    }

    public RecyflowAdapter(List<ZhengMovieBean.ResultBean> list, Context context, ContractInterface.PresenterInterface presenterInterface, MyGouAdapter adapter) {
        this.list = list;
        this.context = context;
        this.presenterInterface=presenterInterface;
        this.adapter=adapter;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context,R.layout.layout_sim,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        Uri uri=Uri.parse(list.get(position).getImageUrl());
        holder.simpleDraweeView.setImageURI(uri);
        App.MovieId=list.get(position).getId();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                App.CinemaId=list.get(position).getId();
               /* presenterInterface.toGouPiao(App.CinemaId,App.MovieId);*/
                if(myOnClickListener!=null){
                    myOnClickListener.success(list.get(position).getId()+"",list.get(position).getName()+"");
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerCoverFlow.ViewHolder{

        SimpleDraweeView simpleDraweeView;
        public Holder(View itemView) {
            super(itemView);
            simpleDraweeView=itemView.findViewById(R.id.simple);
        }
    }
    public interface MyOnClickListener{
        public void success(String movieid, String moviename);
    }
}
