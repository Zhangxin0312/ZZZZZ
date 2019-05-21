package com.bw.myproduct.fragment;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.bumptech.glide.Glide;
import com.bw.myproduct.R;
import com.bw.myproduct.adapter.PopAdapter;
import com.bw.myproduct.adapter.PopAdapter_2;
import com.bw.myproduct.adapter.ShowAdapter;
import com.bw.myproduct.bean.Banners;
import com.bw.myproduct.bean.Pop;
import com.bw.myproduct.bean.Pop_2;
import com.bw.myproduct.bean.ShouYe;
import com.bw.myproduct.contract.Contract;
import com.bw.myproduct.presenter.MyPresenter;
import com.bw.myproduct.views.MySerchView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

public class ShouFragment  extends Fragment implements  Contract.MyShowInterface{
    XBanner ban;
    Contract.MyPresenterInterface myPresenterInterface;
    RecyclerView RecyclerView_id,pop_rlv,pop_rlv_2;
    public ShowAdapter showAdapter;
     View view;
    FrameLayout FrameLayout_id;
    MySerchView mySerchView;
    List<Pop.ResultBean> xlist=new ArrayList<>();
    List<Pop_2.ResultBean> mlist=new ArrayList<>();
    public View view1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.shou_fragment, null);
        initSou();
        ban= view.findViewById(R.id.ban);
        RecyclerView_id= view.findViewById(R.id.RecyclerView_id);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerView_id.setLayoutManager(layoutManager);
        //请求banner
        myPresenterInterface=new MyPresenter<>(this);
        myPresenterInterface.Ban();
        //多条目展示的请求
        myPresenterInterface.ShouYe();
        return view;
    }
      //搜索
    private void initSou() {
        FrameLayout_id=view.findViewById(R.id.FrameLayout_id);
        mySerchView=view.findViewById(R.id.mySerchView);

        mySerchView.show_sou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = mySerchView.show_name.getText().toString();
                FrameLayout_id.setVisibility(View.VISIBLE);
                //点击搜索按钮的时候 用FrameLayout替换  将内容传过去
                JumpFragment jumpFragment=new JumpFragment(FrameLayout_id);
                getFragmentManager().beginTransaction().replace(R.id.FrameLayout_id,jumpFragment).commit();
                Bundle bundle=new Bundle();
                bundle.putString("content",content);
                jumpFragment.setArguments(bundle);
            }
        });

        //PopupWindow
        mySerchView.show_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //view
                view1 = View.inflate(getActivity(), R.layout.pop_item, null);
                pop_rlv= view1.findViewById(R.id.pop_rlv);
                LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                pop_rlv.setLayoutManager(layoutManager);
                //请求数据
                myPresenterInterface.Pop();
                //一级列表的Adapter
                PopAdapter popAdapter=new PopAdapter(getActivity(),xlist,ShouFragment.this);
                pop_rlv.setAdapter(popAdapter);
                //popupWindow展示
                PopupWindow pop=new PopupWindow(view1,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                pop.setBackgroundDrawable(new BitmapDrawable());
                pop.setOutsideTouchable(true);
                pop.showAsDropDown(view1,100,100);
            }
        });
    }
    public void getPopId(String id){
        //点击的时候展示二级条目
        pop_rlv_2= view1.findViewById(R.id.pop_rlv_2);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        pop_rlv_2.setLayoutManager(layoutManager);
        //请求二级条目的数据
        myPresenterInterface.Pop_2(id);
        PopAdapter_2 popAdapter_2=new PopAdapter_2(mlist,getActivity());
        pop_rlv_2.setAdapter(popAdapter_2);
    }
     //XBanner展示
    @Override
    public void MyBan(List<Banners.ResultBean> list) {
        final List<String>  mlist=new ArrayList<>();
        for (int i = 0; i <list.size() ; i++) {
            mlist.add(list.get(i).getImageUrl());
        }
        ban.setData(mlist,null);
        ban.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(getActivity()).load(mlist.get(position)).into((ImageView) view);
                ban.setAutoPalyTime(1000);
            }
        });
    }
 //首页数据
    @Override
    public void MyShouYe(ShouYe shou) {
        //首页的展示
        showAdapter = new ShowAdapter(getActivity(),shou,this);
        RecyclerView_id.setAdapter(showAdapter);
        showAdapter.notifyDataSetChanged();
    }

    @Override
    public void MyPop(List<Pop.ResultBean> list) {
        //PopupWindow的数据
         xlist.clear();
         xlist.addAll(list);
         showAdapter.notifyDataSetChanged();
    }

    @Override
    public void MyPop2(List<Pop_2.ResultBean> list) {
        //二级条目的数据
         mlist.clear();
         mlist.addAll(list);
         showAdapter.notifyDataSetChanged();
    }

    //得到详情的id  跳转详情
    public void getId(int commodityId) {
        FrameLayout_id.setVisibility(View.VISIBLE);
        JumpFragment jumpFragment= new JumpFragment(FrameLayout_id);
        getFragmentManager().beginTransaction().replace(R.id.FrameLayout_id,jumpFragment).commit();
        Bundle bundle=new Bundle();
        bundle.putInt("id",commodityId);
        jumpFragment.setArguments(bundle);
    }
}

