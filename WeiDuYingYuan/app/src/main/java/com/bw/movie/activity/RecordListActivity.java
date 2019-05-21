
package com.bw.movie.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.adapter.RecodeAdapter;
import com.bw.movie.adapter.SuccessAdapter;
import com.bw.movie.bean.RecodeBean;
import com.bw.movie.contract.ContractInter;
import com.bw.movie.presenter.Presenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecordListActivity extends AppCompatActivity  implements  ContractInter.RecordInter{
    ContractInter.PresenterInter presenterInter;
    List<RecodeBean.ResultBean> mlist=new ArrayList<>();
    @BindView(R.id.recode_recycler)
    RecyclerView recode_recycler;
    private RecodeAdapter recodeAdapter;
    @BindView(R.id.success_id)
    Button success_id;
    @BindView(R.id.dai_id)
    Button dai_id;
    @BindView(R.id.recode_back)
    ImageView recode_back;
    SuccessAdapter successAdapter;
    RecordListActivity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_list);
        activity=this;
        ButterKnife.bind(this);
        //购票记录
        presenterInter=new Presenter<>(this);
        presenterInter.RecordList(1);
        LinearLayoutManager layoutManager=new LinearLayoutManager(RecordListActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recode_recycler.setLayoutManager(layoutManager);
        recodeAdapter = new RecodeAdapter(RecordListActivity.this,mlist);
        recode_recycler.setAdapter(recodeAdapter);
        //代付款
        dai_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dai_id.setBackgroundResource(R.drawable.zi);
                success_id.setBackgroundResource(R.drawable.hui);
                dai_id.setTextColor(Color.WHITE);
                success_id.setTextColor(Color.BLACK);
                //待付款
                presenterInter.RecordList(1);
                LinearLayoutManager layoutManager=new LinearLayoutManager(RecordListActivity.this);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recode_recycler.setLayoutManager(layoutManager);
                recodeAdapter = new RecodeAdapter(RecordListActivity.this,mlist);
                recode_recycler.setAdapter(recodeAdapter);
            }
        });
        //已完成啊
        success_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //已完成
                dai_id.setBackgroundResource(R.drawable.hui);
                success_id.setBackgroundResource(R.drawable.zi);
                dai_id.setTextColor(Color.BLACK);
                success_id.setTextColor(Color.WHITE);

                presenterInter.RecordList(2);
                LinearLayoutManager layoutManager=new LinearLayoutManager(RecordListActivity.this);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recode_recycler.setLayoutManager(layoutManager);

                successAdapter = new SuccessAdapter(RecordListActivity.this,mlist);
                recode_recycler.setAdapter(successAdapter);
            }
        });

        recode_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }
     //购票记录的返回值
    @Override
    public void MyRecord(List<RecodeBean.ResultBean> list) {
         mlist.clear();
         mlist.addAll(list);
        recodeAdapter.notifyDataSetChanged();
        successAdapter.notifyDataSetChanged();
    }
}
