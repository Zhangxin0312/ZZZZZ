package com.bw.movie.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.adapter.MyNearCinemaAdapter;
import com.bw.movie.adapter.RecommendAdapter;
import com.bw.movie.adapter.SpacesItemDecoration;
import com.bw.movie.bean.NearCinemaBean;
import com.bw.movie.contract.ContractInterface;
import com.bw.movie.presenter.MyPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：dell
 * @E-mail： 15001194794@163.com
 * @Date：2019/5/13 10:37
 * @Description：描述信息
 */
public class Near_the_cinema_Fragment extends Fragment implements ContractInterface.NearCinemaViewInterface {
    ContractInterface.PresenterInterface presenterInterface;
    RecyclerView recyclerView;
    List<NearCinemaBean.ResultBean> list=new ArrayList<>();
    MyNearCinemaAdapter adapter;
    int space=15;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_near_the_cinema,container,false);
        recyclerView=view.findViewById(R.id.near_recycler);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenterInterface=new MyPresenter<>(this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
         adapter=new MyNearCinemaAdapter(list,getActivity(),presenterInterface);
        recyclerView.setAdapter(adapter);
        presenterInterface.toNearCinema(1,10,"116.30551391385724","40.04571807462411");
        recyclerView.addItemDecoration(new SpacesItemDecoration(space));

        adapter.setMyId(new RecommendAdapter.MyId() {
            @Override
            public void succ(int id, boolean ischeck) {
                if(ischeck){
                    presenterInterface.toHeart(id);
                }else {
                    presenterInterface.toQuHeart(id);

                }

            }
        });

    }

    @Override
    public void showNearCinema(Object o) {
        NearCinemaBean nearCinemaBean= (NearCinemaBean) o;
        Log.e("aaa",nearCinemaBean.getMessage());
        list.addAll(nearCinemaBean.getResult());
        adapter.notifyDataSetChanged();
    }
}
