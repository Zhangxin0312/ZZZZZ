package com.bw.myproduct.fragment;

import android.content.Intent;
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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.myproduct.R;
import com.bw.myproduct.adapter.XiangAdapter;
import com.bw.myproduct.bean.XiangBean;
import com.bw.myproduct.contract.Contract;
import com.bw.myproduct.presenter.MyPresenter;
import com.bw.myproduct.view.ShowActivity;
import com.stx.xhb.xbanner.XBanner;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class XiangFragment extends Fragment implements  Contract.MyXiangInterface{
    RecyclerView xiang_rlv_id;
    WebView web;
    Contract.MyPresenterInterface myPresenterInterface;
    private XiangAdapter xiangAdapter;
    ImageView xiang_iv_id;
    SharedPreferences  sp;
    XBanner xiang_ban;
    private View view;
    RelativeLayout rela;
    Button gouwu,mai_id;
    private String sessionId;
    private int userId;
    private JSONArray array;
     int id=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.xiang_fragment,null);
        //获得得到的数据
        Bundle bundle = getArguments();
        mai_id=view.findViewById(R.id.mai_id);
        xiang_rlv_id= view.findViewById(R.id.xiang_rlv_id);
        xiang_iv_id= view.findViewById(R.id.xiang_iv_id);
        xiang_ban= view.findViewById(R.id.xiang_ban);
        web= view.findViewById(R.id.web);

        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xiang_rlv_id.setLayoutManager(layoutManager);
        //获取userId和sessionId
        sp=getActivity().getSharedPreferences("share",getActivity().getApplicationContext().MODE_PRIVATE);
        sessionId = sp.getString("sessionId", null);
        userId = sp.getInt("userId", 0);
        myPresenterInterface=new MyPresenter<>(this);
        //得到id去详情
        id = bundle.getInt("id");
        myPresenterInterface.Xiang(id, userId, sessionId);
        initData();
        return view;
    }

    private void initData() {
        rela=view.findViewById(R.id.rela);
        gouwu=view.findViewById(R.id.gouwu);
        rela.bringToFront();
        //所需要的data参数
        JSONObject object=new JSONObject();
        array = new JSONArray();
        try {
            object.put("commodityId",id);
            object.put("count",3);
            array.put(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        gouwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //将购物车里添加数据
             myPresenterInterface.Add(userId,sessionId,array.toString());
            }
        });
        //跳转购物车界面
        mai_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(getActivity(),ShowActivity.class);
                intent1.putExtra("flag",2);
                startActivity(intent1);
            }
        });
    }
       //p层传回来的详情数据
    @Override
    public void MyXiang(XiangBean xiangBean) {
        //XBanner数据
        final List<String>  mlist=new ArrayList<>();
        String picture = xiangBean.getResult().getPicture();
        String[] split = picture.split(",");
        for (int i = 0; i <split.length ; i++) {
            mlist.add(split[i]);
        }
        xiang_ban.setData(mlist,null);
        xiang_ban.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(getActivity()).load(mlist.get(position)).into((ImageView) view);
                xiang_ban.setAutoPalyTime(1000);
            }
        });
        //详情数据
        xiangAdapter = new XiangAdapter(getActivity(),xiangBean);
        xiang_rlv_id.setAdapter(xiangAdapter);
        //webView的展示
        web.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        String htmlData=xiangBean.getResult().getDetails();
        htmlData = htmlData.replaceAll("&", "");
        htmlData = htmlData.replaceAll("quot;", "\"");
        htmlData = htmlData.replaceAll("lt;", "<");
        htmlData = htmlData.replaceAll("gt;", ">");
        htmlData = htmlData.replaceAll("<img", "<img width=\"100%\"");
        web.loadDataWithBaseURL(null, htmlData, "text/html", "utf-8", null);
    }

    @Override
    public void MyXiangs(String str) {
        //详情数据为空的时候展示图片
        if(str.equals("数据为空")){
              xiang_iv_id.setImageResource(R.mipmap.b);
        }
    }

    @Override
    public void MyAdd(String str) {
        //添加购物车返回来的数据
        Toast.makeText(getActivity(),str,Toast.LENGTH_SHORT).show();
    }
}
