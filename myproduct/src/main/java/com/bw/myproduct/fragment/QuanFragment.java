package com.bw.myproduct.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.myproduct.R;
import com.bw.myproduct.adapter.QuanAdapter;
import com.bw.myproduct.bean.QuanBean;
import com.bw.myproduct.contract.Contract;
import com.bw.myproduct.presenter.MyPresenter;

import java.util.ArrayList;
import java.util.List;

public class QuanFragment extends Fragment implements Contract.MyQuanInterface {
    RecyclerView quan_recyclerView;
    List<QuanBean.ResultBean> mlist=new ArrayList<>();
    private QuanAdapter quanAdapter;
    Contract.MyPresenterInterface myPresenterInterface;
    private SharedPreferences sp;
    int page=1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=View.inflate(getActivity(), R.layout.quan_fragment,null);
        //得到userId和sessionId
        sp = getActivity().getSharedPreferences("share", Context.MODE_PRIVATE);
        int userId = sp.getInt("userId",0);
        String sessionId = sp.getString("sessionId", null);
        //圈子的展示
        quan_recyclerView=view.findViewById(R.id.quan_recyclerView);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        quan_recyclerView.setLayoutManager(layoutManager);
        //请求数据
        myPresenterInterface=new MyPresenter<>(this);
        myPresenterInterface.Quan(page,userId,sessionId);
        //Adapter
        quanAdapter = new QuanAdapter(getActivity(),mlist);
        quan_recyclerView.setAdapter(quanAdapter);
        return view;
    }

    @Override
    public void MyQuan(List<QuanBean.ResultBean> list) {
        //圈子的返回数据
         mlist.clear();
         mlist.addAll(list);
         quanAdapter.notifyDataSetChanged();
    }
}
