package com.bw.myproduct.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bw.myproduct.R;
import com.bw.myproduct.adapter2.GouAdapter;
import com.bw.myproduct.beans.GouBean;
import com.bw.myproduct.beans.GouBeans;
import com.bw.myproduct.contract.ContractInterface;
import com.bw.myproduct.greendao.gen.DaoMaster;
import com.bw.myproduct.greendao.gen.DaoSession;
import com.bw.myproduct.greendao.gen.GouBeansDao;
import com.bw.myproduct.presenter.MyPresens;
import com.bw.myproduct.view.SuanActivity;

import java.util.ArrayList;
import java.util.List;

public class GouFragment extends Fragment  implements  ContractInterface.MyViewInter{
    RecyclerView gou_recyclerView;
    ContractInterface.MyPresenterInter myPresenterInter;
    private SharedPreferences sp;
    List<GouBean.ResultBean>  mlist=new ArrayList<>();
    private GouAdapter gouAdapter;
    TextView sum;
    CheckBox  checkAll;
    private View view;
    Button last;
    private GouBeansDao beansDao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.gou_fragment,null);
        gou_recyclerView = view.findViewById(R.id.gou_recyclerView);
        sum=view.findViewById(R.id.sum);
        last=view.findViewById(R.id.last);
        //购物车展示
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        gou_recyclerView.setLayoutManager(layoutManager);
        //得到userId和sessionId
        sp = getActivity().getSharedPreferences("share", Context.MODE_PRIVATE);
        String sessionId = sp.getString("sessionId", null);
        int userId = sp.getInt("userId", 0);
        //实例化对象
        myPresenterInter=new MyPresens(this);
        //获取购物车数据列表
        myPresenterInter.Gou(userId,sessionId);
        //展示数据
        gouAdapter = new GouAdapter(getActivity(),mlist,this);
        gou_recyclerView.setAdapter(gouAdapter);
        initData();
        //去结算
        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),SuanActivity.class);
                intent.putExtra("sum",Integer.parseInt(sum.getText().toString()));
                startActivity(intent);
            }
        });
        return view;
    }

    private void initData() {
        //全选
        checkAll=view.findViewById(R.id.checkAll);
        checkAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkAll.isChecked()){
                    for (int i = 0; i < mlist.size(); i++) {
                        mlist.get(i).setFlag(true);
                    }
                    toSum();
                }else{
                    for (int i = 0; i < mlist.size(); i++) {
                        mlist.get(i).setFlag(false);
                    }
                    sum.setText("0");
                }
                gouAdapter.notifyDataSetChanged();

            }
        });
    }

     public  void  getWai(boolean checked, int i){
        mlist.get(i).setFlag(checked);
     }

    @Override
    public void MyGou(List<GouBean.ResultBean> list) {
        //查询将数据写入数据库
          mlist.clear();
         mlist.addAll(list);
        gouAdapter.notifyDataSetChanged();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getActivity(), "db");
        SQLiteDatabase database = helper.getWritableDatabase();

        DaoMaster daoMaster=new DaoMaster(database);
        DaoSession daoSession = daoMaster.newSession();
        beansDao = daoSession.getGouBeansDao();
        for (int i = 0; i <list.size() ; i++) {
            GouBeans beans=new GouBeans();
            beans.setCommodityId(list.get(i).getCommodityId());
            beans.setCommodityName(list.get(i).getCommodityName());
            beans.setCount(list.get(i).getCount());
            beans.setFlag(list.get(i).isFlag());
            beans.setPrice(list.get(i).getPrice());
            beans.setNum(list.get(i).getNum());
            beans.setPic(list.get(i).getPic());
            beansDao.insert(beans);
        }


    }

    public  void toSum(){
        //计算总价
        int zong=0;
        for (int i = 0; i <mlist.size() ; i++) {
              if(mlist.get(i).isFlag()){
                  int price = mlist.get(i).getPrice();
                  int num = mlist.get(i).getNum();
                  zong+= price*num;
            }
        }
        sum.setText(zong+"");
        gouAdapter.notifyDataSetChanged();
    }
}
