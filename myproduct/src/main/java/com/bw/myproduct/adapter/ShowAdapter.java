package com.bw.myproduct.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.bw.myproduct.R;
import com.bw.myproduct.bean.ShouYe;
import com.bw.myproduct.fragment.ShouFragment;

public class ShowAdapter extends RecyclerView.Adapter{
    Context context;
    ShouYe shou;
    ShouFragment shouFragment;
    int type=0;
    public ShowAdapter(Context context, ShouYe shou, ShouFragment shouFragment) {
         this.context=context;
         this.shou=shou;
         this.shouFragment = shouFragment;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
      //首页的多条目
        if(type==0){
            //热销新品的name 和内层的rlv
            View view=View.inflate(context, R.layout.first_item,null);
             return new Holder_1(view);
        }else if(type==1){
            //魔力时尚的name 和内层的rlv
            View view=View.inflate(context, R.layout.two_item,null);
            return new Holder_2(view);
        }else {
            //品质生活的name 和内层的rlv
            View view=View.inflate(context, R.layout.three_item,null);
            return new Holder_3(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        if(type==0){
            ((Holder_1)viewHolder).first_name.setWidth(width);
            ( (Holder_1)viewHolder).first_name.setText(shou.getResult().getRxxp().getName());
            //热销新品的recyclerView
            LinearLayoutManager layoutManager=new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            ( (Holder_1)viewHolder).first_rlv.setLayoutManager(layoutManager);
            //recyclerView 的Adapter展示
            FirstAdapter firstAdapter=new FirstAdapter(context,shou.getResult().getRxxp().getCommodityList(),shouFragment);
            ( (Holder_1)viewHolder).first_rlv.setAdapter(firstAdapter);
        }else if(type==1){
            //魔力时尚
            ((Holder_2)viewHolder).two_name.setWidth(width);
            ( (Holder_2)viewHolder).two_name.setText(shou.getResult().getMlss().getName());
            //魔力时尚的recyclerView
            LinearLayoutManager layoutManager=new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            ( (Holder_2)viewHolder).two_rlv.setLayoutManager(layoutManager);
            //recyclerView 的Adapter展示
            TwoAdapter twoAdapter=new TwoAdapter(context,shou.getResult().getMlss().getCommodityList(),shouFragment);
            ( (Holder_2)viewHolder).two_rlv.setAdapter(twoAdapter);
        }else {
            ((Holder_3)viewHolder).three_name.setWidth(width);
            ( (Holder_3)viewHolder).three_name.setText(shou.getResult().getPzsh().getName());
            //品质生活的recyclerView
             GridLayoutManager layoutManager=new GridLayoutManager(context,2);
            ( (Holder_3)viewHolder).three_rlv.setLayoutManager(layoutManager);
            //recyclerView 的Adapter展示
            ThreeAdapter threeAdapter=new ThreeAdapter(context,shou.getResult().getPzsh().getCommodityList(),shouFragment);
            ( (Holder_3)viewHolder).three_rlv.setAdapter(threeAdapter);
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
      if(position==0){
          type=0;
          return type;
      }else if(position==1){
          type=1;
          return type;
      }else
      {
          type=2;
          return type;
      }
    }

    public  class Holder_1  extends  RecyclerView.ViewHolder{
        TextView first_name;
        RecyclerView first_rlv;
        public Holder_1(@NonNull View itemView) {
            super(itemView);
            first_name=itemView.findViewById(R.id.first_name);
            first_rlv=itemView.findViewById(R.id.first_rlv);
        }
    }
    public  class Holder_2  extends  RecyclerView.ViewHolder{
        TextView two_name;
        RecyclerView two_rlv;
        public Holder_2(@NonNull View itemView) {
            super(itemView);
            two_name=itemView.findViewById(R.id.two_name);
            two_rlv=itemView.findViewById(R.id.two_rlv);
        }
    }
    public  class Holder_3 extends  RecyclerView.ViewHolder{
        TextView three_name;
        RecyclerView three_rlv;
        public Holder_3(@NonNull View itemView) {
            super(itemView);
            three_name=itemView.findViewById(R.id.three_name);
            three_rlv=itemView.findViewById(R.id.three_rlv);
        }
    }
}
