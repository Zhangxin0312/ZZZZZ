package com.bw.myproduct.adapter2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bw.myproduct.R;
import com.bw.myproduct.beans.MyQuanBean;

import java.util.List;

public class MyQuanAdapter extends RecyclerView.Adapter {
      int type=1;
    Context context;
    List<MyQuanBean.ResultBean> mlist;
    public MyQuanAdapter(Context context, List<MyQuanBean.ResultBean> list) {
        this.context=context;
        this.mlist=list;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(type==1){
            //一左两右的图片展示
            View view=View.inflate(context, R.layout.myquan_1_item,null);
            return new Holder_1(view);
        }else{
            //一行三个图片的展示
            View view=View.inflate(context, R.layout.myquan_2_item,null);
            return new Holder_2(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if(type==1){
            LinearLayoutManager layoutManager=new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            ((Holder_1)viewHolder).myquan_recycler_1.setLayoutManager(layoutManager);
            //第一种类型的展示
            MyQuan_1_Adapter myQuan_1_adapter=new MyQuan_1_Adapter(context,mlist);
            ((Holder_1)viewHolder).myquan_recycler_1.setAdapter(myQuan_1_adapter);
        }else{
            LinearLayoutManager layoutManager=new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            ((Holder_2)viewHolder).myquan_recycler_2.setLayoutManager(layoutManager);
            //第二种类型的展示
            MyQuan_2_Adapter myQuan_2_adapter=new MyQuan_2_Adapter(context,mlist);
            ((Holder_2)viewHolder).myquan_recycler_2.setAdapter(myQuan_2_adapter);

        }
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    @Override
    public int getItemViewType(int position) {
        String image = mlist.get(position).getImage();
        if(image==null){
            type=1;
            return type;
        }else{
            type=2;
            return  type;
        }
    }
    public  class Holder_1  extends  RecyclerView.ViewHolder{
        RecyclerView myquan_recycler_1;
        public Holder_1(@NonNull View itemView) {
            super(itemView);
            myquan_recycler_1=itemView.findViewById(R.id.myquan_recycler_1);
        }
    }
    public  class Holder_2  extends  RecyclerView.ViewHolder{
        RecyclerView myquan_recycler_2;
        public Holder_2(@NonNull View itemView) {
            super(itemView);
            myquan_recycler_2=itemView.findViewById(R.id.myquan_recycler_2);
        }
    }

}
