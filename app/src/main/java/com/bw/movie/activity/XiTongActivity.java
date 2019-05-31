package com.bw.movie.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.adapter.MyXiTongAdapter;
import com.bw.movie.bean.XiTongBean;
import com.bw.movie.contract.ContractInterface;
import com.bw.movie.presenter.MyPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class XiTongActivity extends AppCompatActivity implements ContractInterface.XTViewInterface {

    ContractInterface.PresenterInterface presenterInterface;
    List<XiTongBean.ResultBean> list = new ArrayList<>();
    @BindView(R.id.xt_recycler)
    RecyclerView xtRecycler;
    MyXiTongAdapter adapter;
    ImageView tongzhi_fanhui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xi_tong);
        ButterKnife.bind(this);
        tongzhi_fanhui=findViewById(R.id.tongzhi_fanhui);
        presenterInterface = new MyPresenter<>(this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xtRecycler.setLayoutManager(linearLayoutManager);
        adapter=new MyXiTongAdapter(list,XiTongActivity.this);
        xtRecycler.setAdapter(adapter);
        presenterInterface.toXiTong();
        tongzhi_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void showXiTong(Object o) {
        XiTongBean xiTongBean = (XiTongBean) o;
        list.addAll(xiTongBean.getResult());
        adapter.notifyDataSetChanged();
    }
}
