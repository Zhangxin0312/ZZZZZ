package com.bw.myproduct.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bw.myproduct.R;
import com.bw.myproduct.adapter.SouAdapter;
import com.bw.myproduct.bean.Sou;
import com.bw.myproduct.contract.Contract;
import com.bw.myproduct.presenter.MyPresenter;
import com.bw.myproduct.view.XiangActivity;
import com.bw.myproduct.views.MySerchView;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class ProductFragment extends Fragment  implements  Contract.MySouInterface{
    int page=1;
    Contract.MyPresenterInterface myPresenterInterface;
    List<Sou.ResultBean> mlist=new ArrayList<>();
    RecyclerView RecyclerView_id_sou;
    private View view;
    private SouAdapter souAdapter;
    FrameLayout frameLayout_id;
    ImageView serch_iv;
    private String content;
    @SuppressLint("ValidFragment")
    public ProductFragment(FrameLayout frameLayout_id) {
        //得到Fragment的id
        this.frameLayout_id=frameLayout_id;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.serch_fragment,null);
        //实例化p层
        myPresenterInterface=new MyPresenter<>(this);
       //首页传过来的数据
        Bundle bundle = getArguments();
        content = bundle.getString("content");
        init();
        return view;
    }
    private void init() {
        RecyclerView_id_sou=view.findViewById(R.id.RecyclerView_id_sou);
        GridLayoutManager layoutManager=new GridLayoutManager(getActivity(),2);
        RecyclerView_id_sou.setLayoutManager(layoutManager);
        //如果没有搜索的时候  走默认的  如果首页搜索了  就按首页搜索的内容进行显示
        if(content==null){
            myPresenterInterface.Sou("1",page);
        }else
        {
            //搜索的请求数据
            myPresenterInterface.Sou(content,page);
        }

        //Adapter展示
        souAdapter = new SouAdapter(getActivity(),mlist,this);
        RecyclerView_id_sou.setAdapter(souAdapter);
    }
    @Override
    public void MySous(List<Sou.ResultBean> list) {
        //搜索返回来的数据
        mlist.clear();
        mlist.addAll(list);
        souAdapter.notifyDataSetChanged();
    }

    @Override
    public void MySouError(String str) {
        //如果搜索为空  展示一个图片
        serch_iv=view.findViewById(R.id.serch_iv);
        if(str.equals("数据为空")){
            serch_iv.setVisibility(View.VISIBLE);
        }
    }

    public  void  getIds(int id){
        //得到id  跳转详情的Activity
           Intent intent=new Intent(getActivity(),XiangActivity.class);
           intent.putExtra("id",id);
           startActivity(intent);
    }
}
