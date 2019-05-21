package com.bw.myproduct.fragment;

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
import com.bw.myproduct.adapter.PingAdapter;
import com.bw.myproduct.bean.PingBean;
import com.bw.myproduct.contract.Contract;
import com.bw.myproduct.presenter.MyPresenter;

import java.util.ArrayList;
import java.util.List;

public class PingFragment extends Fragment  implements Contract.MyPingInterface {
    RecyclerView  ping_recyclerView_id;
    Contract.MyPresenterInterface myPresenterInterface;
    int page=1;
    List<PingBean.ResultBean>  mlist=new ArrayList<>();
    private PingAdapter pingAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=View.inflate(getActivity(), R.layout.ping_fragment,null);
       //得到首页传过来的id
        Bundle bundle = getArguments();
        int id = bundle.getInt("id");
        //RecyclerView
        ping_recyclerView_id=view.findViewById(R.id.ping_recyclerView_id);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ping_recyclerView_id.setLayoutManager(layoutManager);
        //实例化p层对象
        myPresenterInterface=new MyPresenter<>(this);
        //请求评论的数据
        myPresenterInterface.Ping(id,page);
         //展示
        pingAdapter = new PingAdapter(getActivity(),mlist);
        ping_recyclerView_id.setAdapter(pingAdapter);
        return view;
    }

    @Override
    public void MyPing(List<PingBean.ResultBean> list) {
        //P层返回来的数据
         mlist.clear();
         mlist.addAll(list);
         pingAdapter.notifyDataSetChanged();
    }
}
