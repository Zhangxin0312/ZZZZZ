package com.bw.movie.view;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.bw.movie.R;
import com.bw.movie.contract.ContractInterface;
import com.bw.movie.presenter.MyPresenter;

/**
 * @Author：dell
 * @E-mail： 15001194794@163.com
 * @Date：2019/5/13 14:35
 * @Description：描述信息
 */
public class MyViews extends LinearLayout implements View.OnClickListener{

    public View view;
    public ImageView search_image;
    public EditText search_edname;
    public TextView search_textName;
    public TextView locations;
    public RelativeLayout searchLinear;
    public ImageView dingwei;
    private AutoTransition transition;
    Context context;
    ContractInterface.PresenterInterface presenterInterface;
    public MyViews(Context context) {
        super(context);

    }

    public MyViews(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;
        view = LayoutInflater.from(context).inflate(R.layout.layout_itemview,this);

        search_image = view.findViewById(R.id.search_image);
        search_edname = view.findViewById(R.id.search_edname);
        locations = view.findViewById(R.id.locations);
        search_textName = view.findViewById(R.id.search_textName);
        searchLinear = view.findViewById(R.id.search_linear);
        dingwei = view.findViewById(R.id.dingwei);
        search_image.setOnClickListener(this);
        search_textName.setOnClickListener(this);
        dingwei.setOnClickListener(this);
        initLocationOption();
    }

    public MyViews(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_image:
                //点击搜索 伸展
                initExpand();
                break;
            case R.id.search_textName:
                //点击text收缩
                //initReduce();

                break;
        }

    }

    /*设置伸展状态时的布局*/
    public void initExpand() {
        search_edname.setHint("CGV影城");
        search_edname.requestFocus();
        search_edname.setHintTextColor(Color.WHITE);
        search_textName.setText("搜索");
        search_textName.setVisibility(View.VISIBLE);
        search_edname.setVisibility(View.VISIBLE);
        LayoutParams LayoutParams = (LinearLayout.LayoutParams) searchLinear.getLayoutParams();
        LayoutParams.width = dip2px(230);
        LayoutParams.setMargins(dip2px(0), dip2px(0), dip2px(0), dip2px(0));
        searchLinear.setLayoutParams(LayoutParams);
        search_edname.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                search_edname.setFocusable(true);
                search_edname.setFocusableInTouchMode(true);
                return false;
            }
        });
        //开始动画
        beginDelayedTransition(searchLinear);

    }

 /*   *//*设置收缩状态时的布局*//*
    private void initReduce() {
        search_edname.setCursorVisible(false);
        search_edname.setVisibility(View.GONE);
        search_textName.setVisibility(View.GONE);
        LayoutParams LayoutParams = (LinearLayout.LayoutParams)  searchLinear.getLayoutParams();
        LayoutParams.width = dip2px(40);
        LayoutParams.setMargins(dip2px(0), dip2px(0), dip2px(0), dip2px(0));
        searchLinear.setLayoutParams(LayoutParams);

        //隐藏键盘
        InputMethodManager imm = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(((Activity)context).getWindow().getDecorView().getWindowToken(), 0);
        search_edname.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                search_edname.setCursorVisible(true);
            }
        });
        //开始动画
        beginDelayedTransition( searchLinear);
    }*/


    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void beginDelayedTransition(ViewGroup view) {
        transition = new AutoTransition();
        transition.setDuration(500);
        TransitionManager.beginDelayedTransition(view, transition);
    }

    private int dip2px(float dpVale) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpVale * scale + 0.5f);
    }
    /**
     * 初始化定位参数配置
     */

    private void initLocationOption() {
//定位服务的客户端。宿主程序在客户端声明此类，并调用，目前只支持在主线程中启动
        LocationClient locationClient = new LocationClient(getContext());
//声明LocationClient类实例并配置定位参数
        LocationClientOption locationOption = new LocationClientOption();
        MyLocationListener myLocationListener = new MyLocationListener();
//注册监听函数
        locationClient.registerLocationListener(myLocationListener);
//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        locationOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
        locationOption.setCoorType("gcj02");
//可选，默认0，即仅定位一次，设置发起连续定位请求的间隔需要大于等于1000ms才是有效的
        locationOption.setScanSpan(1000);
//可选，设置是否需要地址信息，默认不需要
        locationOption.setIsNeedAddress(true);
//可选，设置是否需要地址描述
        locationOption.setIsNeedLocationDescribe(true);
//可选，设置是否需要设备方向结果
        locationOption.setNeedDeviceDirect(false);
//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        locationOption.setLocationNotify(true);
//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        locationOption.setIgnoreKillProcess(true);
//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        locationOption.setIsNeedLocationDescribe(true);
//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        locationOption.setIsNeedLocationPoiList(true);
//可选，默认false，设置是否收集CRASH信息，默认收集
        locationOption.SetIgnoreCacheException(false);
//可选，默认false，设置是否开启Gps定位
        locationOption.setOpenGps(true);
//可选，默认false，设置定位时是否需要海拔信息，默认不需要，除基础定位版本都可用
        locationOption.setIsNeedAltitude(false);
//设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者，该模式下开发者无需再关心定位间隔是多少，定位SDK本身发现位置变化就会及时回调给开发者
        locationOption.setOpenAutoNotifyMode();
//设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者
        locationOption.setOpenAutoNotifyMode(3000,1, LocationClientOption.LOC_SENSITIVITY_HIGHT);
//需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        locationClient.setLocOption(locationOption);
//开始定位
        locationClient.start();
    }
    /**
     * 实现定位回调
     */
    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location){
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取经纬度相关（常用）的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

            //获取纬度信息
            double latitude = location.getLatitude();
            //获取经度信息
            double longitude = location.getLongitude();
            //获取定位精度，默认值为0.0f
            float radius = location.getRadius();
            //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准
            String coorType = location.getCoorType();
            //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明
            int errorCode = location.getLocType();
            locations.setText(location.getAddress().address);

        }
    }
}
