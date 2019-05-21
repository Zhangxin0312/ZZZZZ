package com.bw.myproduct.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.bw.myproduct.R;
import com.bw.myproduct.adapter2.MyAllOrderAdapter;
import com.bw.myproduct.adapter2.MyFiveOrderAdapter;
import com.bw.myproduct.adapter2.MyFourOrderAdapter;
import com.bw.myproduct.adapter2.MyTwoOrderAdapters;
import com.bw.myproduct.adapter2.MyThreeOrderAdapter;
import com.bw.myproduct.beans.OrderBean;
import com.bw.myproduct.contract.ContractInterface;
import com.bw.myproduct.presenter.MyPresens;
import com.bw.myproduct.view.AssessActivity;
import com.bw.myproduct.view.PayActivity;

import static android.content.Context.MODE_PRIVATE;

public class DingFragment extends Fragment implements  ContractInterface.MyOrderInter{
     ImageView iv_All,two_id,three_id,four_id,five_id;
     RecyclerView ding_1_rlv,ding_2_rlv,ding_3_rlv,ding_4_rlv,ding_5_rlv;
     ContractInterface.MyPresenterInter myPresenterInter;
     int page=1,count=5,userId;
     String sessionId;
     View view;
     OrderBean orderBean;
     MyTwoOrderAdapters myOrderAdapter;
     MyFourOrderAdapter myFourOrderAdapter;
     MyThreeOrderAdapter myThreeOrderAdapter;
    private int value;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.ding_fragment,null);
        //实例化对象
        myPresenterInter=new MyPresens<>(this);
        //得到userId和sessionId
        SharedPreferences sp = getActivity().getSharedPreferences("share", MODE_PRIVATE);
        userId = sp.getInt("userId", 0);
        sessionId = sp.getString("sessionId", null);
        //全部订单
        initData();
        //代付款订单
        initData2();
        //待收货订单
        initData3();
        //待评价
        initData4();
        //已完成
        initData5();
        return view;
    }
    //全部订单
    private void initData() {
        iv_All= view.findViewById(R.id.iv_All);
        iv_All.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view1=View.inflate(getActivity(),R.layout.ding_1_order,null);
                PopupWindow popupWindow=new PopupWindow(view1,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                ding_1_rlv=view1.findViewById(R.id.ding_1_rlv);

                LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                ding_1_rlv.setLayoutManager(layoutManager);

                myPresenterInter.AllOrders(userId,sessionId,0,page,count);
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAtLocation(view1, Gravity.CENTER, 0, 0);
            }
        });
    }
    //全部订单
    @Override
    public void MyAllOrder(OrderBean orderBean) {
        this.orderBean=orderBean;
       // 展示全部的订单
        MyAllOrderAdapter myAllOrderAdapter = new MyAllOrderAdapter(getActivity(),orderBean,this);
        ding_1_rlv.setAdapter(myAllOrderAdapter);
    }
    //待付款弹出一个PopupWindow
    private void initData2() {
        two_id=view.findViewById(R.id.two_id);
        //待支付订单的popupWindow
        two_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1=View.inflate(getActivity(),R.layout.ding_2_order,null);
                PopupWindow popupWindow=new PopupWindow(view1,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                ding_2_rlv=view1.findViewById(R.id.ding_2_rlv);

                LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                ding_2_rlv.setLayoutManager(layoutManager);

                myPresenterInter.TwoOrders(userId,sessionId,1,page,count);
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAtLocation(view1, Gravity.CENTER, 0, 0);

               }
         });
     }
        //展示带付款订单的数据
        @Override
        public void MyTwoOrder(OrderBean orderBean) {
            this.orderBean=orderBean;
            //展示带付款订单的Adapter
            myOrderAdapter = new MyTwoOrderAdapters(getActivity(),orderBean,this);
            ding_2_rlv.setAdapter(myOrderAdapter);
            myOrderAdapter.notifyDataSetChanged();
        }
    //待收货订单
    private void initData3() {
        three_id=view.findViewById(R.id.three_id);
        three_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view2=View.inflate(getActivity(),R.layout.ding_3_order,null);
                PopupWindow popupWindow=new PopupWindow(view2,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                ding_3_rlv=view2.findViewById(R.id.ding_3_rlv);

                LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                ding_3_rlv.setLayoutManager(layoutManager);

                myPresenterInter.ThreeOrders(userId,sessionId,2,page,count);
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAtLocation(view2, Gravity.CENTER, 0, 0);
              }
        });
      }
        //待收货订单的展示
        @Override
        public void MyThreeOrder(OrderBean orderBean) {
            this.orderBean=orderBean;
            //展示带收获订单的Adapter
            myThreeOrderAdapter = new MyThreeOrderAdapter(getActivity(),orderBean,this);
            ding_3_rlv.setAdapter(myThreeOrderAdapter);
        }
             //带评价
             private void initData4() {
             four_id=view.findViewById(R.id.four_id);
             four_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view3=View.inflate(getActivity(),R.layout.ding_4_order,null);
                PopupWindow popupWindow=new PopupWindow(view3,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                ding_4_rlv=view3.findViewById(R.id.ding_4_rlv);
                LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                ding_4_rlv.setLayoutManager(layoutManager);
                //待评价
                myPresenterInter.FourOrders(userId,sessionId,3,page,count);
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAtLocation(view3, Gravity.CENTER, 0, 0);
             }
          });
       }
        //待评价订单
        @Override
        public void MyFourOrder(OrderBean orderBean) {
            this.orderBean=orderBean;
            //展示带评价订单的Adapter
            myFourOrderAdapter = new MyFourOrderAdapter(getActivity(),orderBean,this);
            ding_4_rlv.setAdapter(myFourOrderAdapter);
            myFourOrderAdapter.notifyDataSetChanged();
        }
        //已完成订单
       private void initData5() {
          five_id=view.findViewById(R.id.five_id);
           five_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view3=View.inflate(getActivity(),R.layout.ding_5_order,null);
                PopupWindow popupWindow=new PopupWindow(view3,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                ding_5_rlv=view3.findViewById(R.id.ding_5_rlv);

                LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                ding_5_rlv.setLayoutManager(layoutManager);
                //已完成
                myPresenterInter.FiveOrders(userId,sessionId,9,page,count);
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAtLocation(view3, Gravity.CENTER, 0, 0);
            }
        });
    }
    //已完成订单
    @Override
    public void MyFiveOrder(OrderBean orderBean) {
        this.orderBean=orderBean;
        //展示带评价订单的Adapter
        MyFiveOrderAdapter myFiveAdapter= new MyFiveOrderAdapter(getActivity(),orderBean,this);
        ding_5_rlv.setAdapter(myFiveAdapter);
        myFiveAdapter.notifyDataSetChanged();
    }
        //取消订单的操作
        public  void MyCancles(String id){
              myPresenterInter.Cancle(userId,sessionId,id);
        }
        @Override
        public void MyCancle(String str) {
            //取消订单返回的值
            Toast.makeText(getActivity(),str,Toast.LENGTH_SHORT).show();
            if(str.equals("删除成功")){
                myPresenterInter.TwoOrders(userId,sessionId,1,page,count);
                myPresenterInter.FourOrders(userId,sessionId,3,page,count);
            }
        }

        //得到确认收货的orderId
        public  void   Affirm(String orderId){
              myPresenterInter.Affirms(userId,sessionId,orderId);
        }
    //确认收货的返回值
    @Override
    public void MyAffirm(String str) {
        Toast.makeText(getActivity(),str,Toast.LENGTH_SHORT).show();
        if(str.equals("确认收货成功")){
            myPresenterInter.ThreeOrders(userId,sessionId,2,page,count);
        }
    }
     //待评价
    public void Assess(int commodityId, String orderId,int price, String name,String pic,int id) {
           String[] split = pic.split(",");
          //点击按钮的时候  跳转评价的Activity 需要商品的id和订单的id
             Intent intent=new Intent(getActivity(),AssessActivity.class);
             intent.putExtra("commodityId",commodityId);
             intent.putExtra("orderId",orderId);
             intent.putExtra("price",price);
             intent.putExtra("name",name);
             intent.putExtra("pic",split[0]);
            startActivity(intent);
    }
    int ids=0;
      //得到orderId
    public String getWaiId(OrderBean.OrderListBean.DetailListBean detailListBean) {
        for (int i = 0; i <orderBean.getOrderList().size() ; i++) {
            for (int j = 0; j <orderBean.getOrderList().get(i).getDetailList().size() ; j++) {
                OrderBean.OrderListBean.DetailListBean detailListBean1 = orderBean.getOrderList().get(i).getDetailList().get(j);
                if(detailListBean1.equals(detailListBean)){
                    ids=i;
                       return orderBean.getOrderList().get(i).getOrderId();
                 }
            }
        }
        return null;
    }
    //通过外层id循环得到内层的总数量和总价
    public int getSum(int i) {
        int n=0;
        int value= 0;
        for (int j = 0; j <orderBean.getOrderList().get(i).getDetailList().size() ; j++) {
            n+=orderBean.getOrderList().get(i).getDetailList().get(j).getCommodityCount();
            value+=orderBean.getOrderList().get(i).getDetailList().get(j).getCommodityPrice();
        }
        return value;
    }

    public void Jump(int sum, String orderId) {

        Log.e("tag","XXXXXXXXXXXXXXX"+sum);
        //跳转去支付页面
        Intent intent=new Intent(getActivity(),PayActivity.class);
        intent.putExtra("values",sum);
        intent.putExtra("orderId",orderId);
        startActivity(intent);
    }
}
