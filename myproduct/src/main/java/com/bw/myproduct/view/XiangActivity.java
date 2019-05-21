package com.bw.myproduct.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
import com.stx.xhb.xbanner.XBanner;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class XiangActivity extends AppCompatActivity  implements  Contract.MyXiangInterface {
    Contract.MyPresenterInterface myPresenterInterface;
    RecyclerView xiang_rlv_id;
    WebView web;
    private XiangAdapter xiangAdapter;
    ImageView xiang_iv_id;
    SharedPreferences sp;
    XBanner xiang_ban;
    private View view;
    RelativeLayout rela;
    Button gouwu,mai_id;
    private String sessionId;
    private int userId;
    private JSONArray array;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xiang_fragment);

        Intent intent = getIntent();
        mai_id=findViewById(R.id.mai_id);
        xiang_rlv_id = findViewById(R.id.xiang_rlv_id);
        xiang_iv_id = findViewById(R.id.xiang_iv_id);
        xiang_ban = findViewById(R.id.xiang_ban);
        web = findViewById(R.id.web);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xiang_rlv_id.setLayoutManager(layoutManager);
        //得到userId和sessionId
        sp = getSharedPreferences("share", MODE_PRIVATE);
        sessionId = sp.getString("sessionId", null);
        userId = sp.getInt("userId", 0);
        //实例化对象
        myPresenterInterface = new MyPresenter<>(this);
        //得到传过来的id
        id = intent.getIntExtra("id", 0);
        //进行详情接口获取数据
        myPresenterInterface.Xiang(id, userId, sessionId);
        initData();
    }

    private void initData() {
        rela = findViewById(R.id.rela);
        gouwu = findViewById(R.id.gouwu);
        rela.bringToFront();
        //点击购买按钮
        gouwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject object = new JSONObject();
                array = new JSONArray();
                try {
                    object.put("commodityId", id);
                    object.put("count", 3);
                    array.put(object);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //进行购买
                myPresenterInterface.Add(userId, sessionId, array.toString());
            }
        });
        mai_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击买的时候 跳转展示页面  然后跳转购物车
                Intent intent1=new Intent(XiangActivity.this,ShowActivity.class);
                intent1.putExtra("flag",2);
                startActivity(intent1);
            }
        });
    }

    @Override
    public void MyXiang(XiangBean xiangBean) {
        //Banner展示
        final List<String> mlist = new ArrayList<>();
        String picture = xiangBean.getResult().getPicture();
        String[] split = picture.split(",");
        for (int i = 0; i < split.length; i++) {
            mlist.add(split[i]);
        }
        xiang_ban.setData(mlist, null);
        xiang_ban.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(XiangActivity.this).load(mlist.get(position)).into((ImageView) view);
                xiang_ban.setAutoPalyTime(1000);
            }
        });
        //获取详情
        xiangAdapter = new XiangAdapter(XiangActivity.this, xiangBean);
        xiang_rlv_id.setAdapter(xiangAdapter);
        //展示webView
        web.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        String htmlData = xiangBean.getResult().getDetails();
        htmlData = htmlData.replaceAll("&", "");
        htmlData = htmlData.replaceAll("quot;", "\"");
        htmlData = htmlData.replaceAll("lt;", "<");
        htmlData = htmlData.replaceAll("gt;", ">");
        htmlData = htmlData.replaceAll("<img", "<img width=\"100%\"");
        web.loadDataWithBaseURL(null, htmlData, "text/html", "utf-8", null);
    }

    @Override
    public void MyXiangs(String str) {
        //如果数据为空 就展示一个图片
        if (str.equals("数据为空")) {
            xiang_iv_id.setImageResource(R.mipmap.b);
        }
    }

    @Override
    public void MyAdd(String str) {
        //进行购买的返回值
        Toast.makeText(XiangActivity.this, str, Toast.LENGTH_SHORT).show();
    }

}



