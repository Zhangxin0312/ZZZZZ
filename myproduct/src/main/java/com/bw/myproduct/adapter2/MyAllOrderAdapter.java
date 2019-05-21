package com.bw.myproduct.adapter2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bw.myproduct.R;
import com.bw.myproduct.beans.OrderBean;
import com.bw.myproduct.fragment.DingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAllOrderAdapter extends RecyclerView.Adapter {
    Context context;
    OrderBean orderBean;
    DingFragment dingFragment;
    int  type=1;
    public MyAllOrderAdapter(Context context, OrderBean orderBean, DingFragment dingFragment) {
        this.context=context;
        this.orderBean=orderBean;
        this.dingFragment=dingFragment;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //所有订单的展示
       if(type==1){
           //代付款  -->支付
           View view1= View.inflate(context,R.layout.ding_2_order,null);
           return new Holder_1(view1);
       }else if(type==2)
       {
           //待收货   -->确认收货
           View view2= View.inflate(context,R.layout.ding_3_order,null);
           return new Holder_2(view2);
       }else if(type==3)
       {
           //待评价   -->去评价
           View view3= View.inflate(context,R.layout.ding_4_order,null);
           return new Holder_3(view3);
       }else
        {
            //已完成
            View view4= View.inflate(context,R.layout.ding_5_order,null);
            return new Holder_4(view4);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if(type==1) {
            LinearLayoutManager layoutManager=new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            ((Holder_1)viewHolder).ding_2_rlv.setLayoutManager(layoutManager);
            //去支付的RecyclerView
            MyTwoOrderAdapters myOrderAdapter=new MyTwoOrderAdapters(context,orderBean,dingFragment);
            ((Holder_1)viewHolder).ding_2_rlv.setAdapter(myOrderAdapter);
        }else if (type==2) {
            LinearLayoutManager layoutManager=new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            ((Holder_2)viewHolder).ding_3_rlv.setLayoutManager(layoutManager);
            //待收货的RecyclerView
            MyThreeOrderAdapter myThreeOrderAdapter=new MyThreeOrderAdapter(context,orderBean,dingFragment);
            ((Holder_2)viewHolder).ding_3_rlv.setAdapter(myThreeOrderAdapter);
        }else if (type==3) {
            LinearLayoutManager layoutManager=new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            ((Holder_3)viewHolder).ding_4_rlv.setLayoutManager(layoutManager);
            //待评价的RecyclerView
            MyFourOrderAdapter myFourOrderAdapter=new MyFourOrderAdapter(context,orderBean,dingFragment);
            ((Holder_3)viewHolder).ding_4_rlv.setAdapter(myFourOrderAdapter);
        }else{
            LinearLayoutManager layoutManager=new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            ((Holder_4)viewHolder).ding_5_rlv.setLayoutManager(layoutManager);
            //已完成的RecyclerView
            MyFiveOrderAdapter myFiveOrderAdapter=new MyFiveOrderAdapter(context,orderBean,dingFragment);
            ((Holder_4)viewHolder).ding_5_rlv.setAdapter(myFiveOrderAdapter);
        }
    }
        @Override
        public int getItemCount() {
            return   4;
        }
    @Override
    public int getItemViewType(int position) {
        if(orderBean.getOrderList().get(position).getOrderStatus()==1){
            type=1;
            return type;
        }else if(orderBean.getOrderList().get(position).getOrderStatus()==2){
            type=2;
            return type;
        }else if(orderBean.getOrderList().get(position).getOrderStatus()==3){
            type=3;
            return type;
        }else {
            type=4;
            return type;
        }
    }
    public  class Holder_1 extends  RecyclerView.ViewHolder{
        @BindView(R.id.ding_2_rlv)
        RecyclerView ding_2_rlv;
        public Holder_1(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public  class Holder_2 extends  RecyclerView.ViewHolder{
        @BindView(R.id.ding_3_rlv)
        RecyclerView ding_3_rlv;
        public Holder_2(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public  class Holder_3 extends  RecyclerView.ViewHolder{
        @BindView(R.id.ding_4_rlv)
        RecyclerView ding_4_rlv;
        public Holder_3(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public  class Holder_4 extends  RecyclerView.ViewHolder{
        @BindView(R.id.ding_5_rlv)
        RecyclerView ding_5_rlv;
        public Holder_4(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
