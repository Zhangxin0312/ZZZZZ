package com.bw.movie.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.activity.XiangActivity;
import com.bw.movie.adapter.BanAdapter;
import com.bw.movie.adapter.HotAdapter;
import com.bw.movie.adapter.ShowOnAdapter;
import com.bw.movie.adapter.ShowingAdapter;
import com.bw.movie.app.App;
import com.bw.movie.bean.ShouBean;
import com.bw.movie.bean.ShowIngBean;
import com.bw.movie.bean.ShowOnBean;
import com.bw.movie.contract.ContractInter;
import com.bw.movie.presenter.Presenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import recycler.coverflow.RecyclerCoverFlow;

/**
 * @Author：dell
 * @E-mail： 15001194794@163.com
 * @Date：2019/5/11 10:32
 * @Description：描述信息
 */
public class ShouYeFragment extends Fragment implements ContractInter.ShouInter {
    ContractInter.PresenterInter presenterInter;

    int page=1;
    int count=7;
    @BindView(R.id.flow)
    RecyclerCoverFlow flow;
    @BindView(R.id.RecyclerView_1)
    RecyclerView RecyclerView_1;
    @BindView(R.id.RecyclerView_2)
    RecyclerView RecyclerView_2;
    @BindView(R.id.RecyclerView_3)
    RecyclerView RecyclerView_3;
    List<ShouBean.ResultBean> mlist=new ArrayList<>();
    List<ShowIngBean.ResultBean> xlist=new ArrayList<>();
    List<ShowOnBean.ResultBean> plist=new ArrayList<>();
    private BanAdapter adapter;
    private HotAdapter hotAdapter;
    private ShowingAdapter showingAdapter;
    private ShowOnAdapter showOnAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_shouye,container,false);
        ButterKnife.bind(this,view);
        presenterInter=new Presenter(this);
        //热门电影
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        RecyclerView_1.setLayoutManager(layoutManager);
        //正在上映
        LinearLayoutManager layoutManager1=new LinearLayoutManager(getActivity());
        layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        RecyclerView_2.setLayoutManager(layoutManager1);
        //即将上映
        LinearLayoutManager layoutManager2=new LinearLayoutManager(getActivity());
        layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        RecyclerView_3.setLayoutManager(layoutManager2);
        //热门电影
        presenterInter.Ban(page,count);
        //正在上映
        presenterInter.Showing(page,count);
        //即将上映
        presenterInter.ShowOn(page,count);
        //展示旋转木马
        adapter = new BanAdapter(getActivity(),mlist);
        flow.setAdapter(adapter);
        //热门电影
        hotAdapter = new HotAdapter(getActivity(),mlist,this);
        RecyclerView_1.setAdapter(hotAdapter);
        //正在热映
        showingAdapter = new ShowingAdapter(getActivity(),xlist,this);
        RecyclerView_2.setAdapter(showingAdapter);
        //即将热映
        showOnAdapter = new ShowOnAdapter(getActivity(),plist,this);
        RecyclerView_3.setAdapter(showOnAdapter);
        return view;
    }

    @Override
    public void toBan(List<ShouBean.ResultBean> list) {
        mlist.addAll(list);
        App.list=mlist;
        adapter.notifyDataSetChanged();
        hotAdapter.notifyDataSetChanged();
    }

    @Override
    public void MyShowing(List<ShowIngBean.ResultBean> list) {
        xlist.addAll(list);
        App.list1=xlist;
        showingAdapter.notifyDataSetChanged();
    }

    @Override
    public void MyShowOn(List<ShowOnBean.ResultBean> list) {
        plist.addAll(list);
        App.list2=plist;
        showOnAdapter.notifyDataSetChanged();
    }
    public  void   Jump(){
        //跳转详情页面
        Intent intent=new Intent(getActivity(),XiangActivity.class);
        startActivity(intent);
        getActivity().finish();
        getActivity().overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
    }
}
