package com.bw.movie.activity;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.adapter.MyGouAdapter;
import com.bw.movie.adapter.MyPingLunAdapter;
import com.bw.movie.adapter.MyXiangAdapter;
import com.bw.movie.adapter.MyXiangQingAdapter;
import com.bw.movie.adapter.RecyflowAdapter;
import com.bw.movie.adapter.SpacesItemDecoration;
import com.bw.movie.app.App;
import com.bw.movie.bean.GouBean;
import com.bw.movie.bean.PingLunBean;
import com.bw.movie.bean.RecommendBean;
import com.bw.movie.bean.XiangQingBean;
import com.bw.movie.bean.ZanBean;
import com.bw.movie.bean.ZhengMovieBean;
import com.bw.movie.contract.ContractInterface;
import com.bw.movie.presenter.MyPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import recycler.coverflow.RecyclerCoverFlow;

public class RecommendXiangActivity extends AppCompatActivity implements ContractInterface.RecommendDetailsViewInterface {

    ContractInterface.PresenterInterface presenterInterface;
    List<RecommendBean.ResultBean> list = new ArrayList<>();
    MyXiangAdapter adapter;
    ImageView fanhui;

    RecyclerCoverFlow recyclerCoverFlow;
    List<ZhengMovieBean.ResultBean> list1 = new ArrayList<>();
    RecyflowAdapter adapter1;
    @BindView(R.id.x_title)
    TextView xTitle;
    @BindView(R.id.x_name)
    TextView xName;
    @BindView(R.id.x_image)
    SimpleDraweeView xImage;
    TextView xiangqing,pinglun;
    PopupWindow popupWindow;
    RecyclerView recyclerView;
    List<XiangQingBean.ResultBean> lists=new ArrayList<>();
    MyXiangQingAdapter adapterr;
    List<PingLunBean.ResultBean> plist=new ArrayList<>();
    MyPingLunAdapter adapter2;
    Context context;
    ImageView pop_image;
    RecyclerView gRecyclerView;
    List<GouBean.ResultBean> glist=new ArrayList<>();
    MyGouAdapter adapterg;
    int space=15;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_xiang);
        ButterKnife.bind(this);
        fanhui=findViewById(R.id.fanhui);
        recyclerCoverFlow = findViewById(R.id.recycler_coverflow);
        presenterInterface = new MyPresenter<>(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        Uri uri = Uri.parse(App.Ximage);
        xImage.setImageURI(uri);
        xTitle.setText(App.Xtitle);
        xName.setText(App.Xname);
        HashMap<String, Integer> map = new HashMap<>();
        presenterInterface.toZheng(App.CinemaId,map);
        intt();

        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //购票
        gRecyclerView=findViewById(R.id.g_recycler);
        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(this);
        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        gRecyclerView.setLayoutManager(linearLayoutManager1);
        //适配器
        adapterg=new MyGouAdapter(glist,RecommendXiangActivity.this);
        gRecyclerView.setAdapter(adapterg);
        presenterInterface.toGouPiao(App.CinemaId,App.MovieId);
        gRecyclerView.addItemDecoration(new SpacesItemDecoration(space));

        xImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View view1=View.inflate(RecommendXiangActivity.this,R.layout.yyxq_linear,null);
                recyclerView=view1.findViewById(R.id.recycler_xiang);

                popupWindow = new PopupWindow(view1, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

                ColorDrawable dw = new ColorDrawable(0xffffffff);
                popupWindow.setBackgroundDrawable(dw);
                xiangqing=view1.findViewById(R.id.xiangqing_id);
                pinglun=view1.findViewById(R.id.pinglu_id);
                pop_image=view1.findViewById(R.id.yyxq_shouqi_img);
                popupWindow.showAsDropDown(xImage,100,400);
                popupWindow.setFocusable(true);
                LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(RecommendXiangActivity.this);
                linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager1);
                lists.clear();
                xiangqing.setBackground(getResources().getDrawable(R.drawable.xiangqingactivi1));
                //pinglun.setBackground(getResources().getDrawable(R.drawable.xiangqingactivi2));

                adapterr=new MyXiangQingAdapter(lists,RecommendXiangActivity.this);
                recyclerView.setAdapter(adapterr);
                presenterInterface.toXiangQing(App.CinemaId);
                xiangqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(RecommendXiangActivity.this);
                linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager1);
                lists.clear();
                xiangqing.setBackground(getResources().getDrawable(R.drawable.xiangqingactivi1));
                pinglun.setBackground(getResources().getDrawable(R.drawable.xiangqingactivi2));

                adapterr=new MyXiangQingAdapter(lists,RecommendXiangActivity.this);
                recyclerView.setAdapter(adapterr);
                presenterInterface.toXiangQing(App.CinemaId);

                pop_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       popupWindow.dismiss();
                    }
                });

            }
        });
                pinglun.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(RecommendXiangActivity.this);
                        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
                        recyclerView.setLayoutManager(linearLayoutManager1);
                        plist.clear();
                        xiangqing.setBackground(getResources().getDrawable(R.drawable.xiangqingactivi2));
                        pinglun.setBackground(getResources().getDrawable(R.drawable.xiangqingactivi1));

                        adapter2=new MyPingLunAdapter(plist,context);
                        recyclerView.setAdapter(adapter2);
                        presenterInterface.toPingLun(App.CinemaId,1,5);
                        adapter2.setMyIdCheck(new MyPingLunAdapter.MyIdCheck() {
                            @Override
                            public void success(int id) {
                                 presenterInterface.toZan(id);
                            }
                        });

                    }
                });

            }
        });

    }

    private void pinglunInit(final Context context) {
        this.context=context;

    }

    private void intt() {
        adapter1 = new RecyflowAdapter(list1, RecommendXiangActivity.this,presenterInterface,adapterg);
        recyclerCoverFlow.setAdapter(adapter1);
        adapter1.setMyOnClickListener(new RecyflowAdapter.MyOnClickListener() {
            @Override
            public void success(String movieid, String moviename) {
                presenterInterface.toGouPiao(Integer.parseInt(movieid),App.MovieId);
            }
        });
    }

    @Override
    public void showRecommendDetails(Object o) {
        RecommendBean recommendBean = (RecommendBean) o;
        list.addAll(recommendBean.getResult());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showZheng(Object o) {
        ZhengMovieBean zhengMovieBean = (ZhengMovieBean) o;
        list1.addAll(zhengMovieBean.getResult());
        adapter1.notifyDataSetChanged();
    }

    @Override
    public void showXiangQing(Object o) {
        XiangQingBean xiangQingBean= (XiangQingBean) o;
        lists.add(xiangQingBean.getResult());
        adapterr.notifyDataSetChanged();
    }

    @Override
    public void showPingLun(Object o) {
        PingLunBean pingLunBean= (PingLunBean) o;
        plist.addAll(pingLunBean.getResult());
        adapter2.notifyDataSetChanged();
    }

    @Override
    public void showGou(Object o) {
        GouBean gouBean= (GouBean) o;
        glist.addAll(gouBean.getResult());
        adapterg.notifyDataSetChanged();
    }

    @Override
    public void showZan(Object o) {
        ZanBean zanBean= (ZanBean) o;
        presenterInterface.toPingLun(App.CinemaId,1,5);
    }
}
