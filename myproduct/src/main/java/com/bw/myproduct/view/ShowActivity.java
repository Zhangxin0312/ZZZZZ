package com.bw.myproduct.view;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bw.myproduct.R;
import com.bw.myproduct.fragment.DingFragment;
import com.bw.myproduct.fragment.GouFragment;
import com.bw.myproduct.fragment.MyFragment;
import com.bw.myproduct.fragment.QuanFragment;
import com.bw.myproduct.fragment.ShouFragment;

public class ShowActivity extends FragmentActivity {
    ViewPager  vp;
   RadioGroup rg;
   RadioButton rb1,rb2,rb3,rb4,rb5;
   public  Fragment  fragment=null;
    private int flag;
    private String nickName;
    private int status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        //传回来的值 用来跳转
        Intent intent = getIntent();
        flag = intent.getIntExtra("flag", 0);
        nickName = intent.getStringExtra("nickName");
        //获取id
        init();
        rb1.setBackgroundResource(R.mipmap.tab_home_bottom_shouyes);
        vp.setCurrentItem(0);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb1:
                        vp.setCurrentItem(0);
                        break;
                    case R.id.rb2:
                        vp.setCurrentItem(1);
                        break;
                    case R.id.rb3:
                        vp.setCurrentItem(2);
                        break;
                    case R.id.rb4:
                        vp.setCurrentItem(3);
                        break;
                    case R.id.rb5:
                        vp.setCurrentItem(4);
                        break;
                }
            }
        });
         vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                 @Override
                 public void onPageScrolled(int i, float v, int i1) {

                 }

                 @Override
                 public void onPageSelected(int i) {
                     switch (i){
                         case 0:
                             rb1.setBackgroundResource(R.mipmap.tab_home_bottom_shouyes);
                             rb2.setBackgroundResource(R.mipmap.tab_home_bottom_quanzi);
                             rb4.setBackgroundResource(R.mipmap.tab_home_bottom_zhangdan);
                             rb5.setBackgroundResource(R.mipmap.tab_home_bottom_wode);
                             break;
                         case 1:
                             rb1.setBackgroundResource(R.mipmap.tab_home_bottom_shouye);
                             rb2.setBackgroundResource(R.mipmap.tab_home_bottom_quanzis);
                             rb4.setBackgroundResource(R.mipmap.tab_home_bottom_zhangdan);
                             rb5.setBackgroundResource(R.mipmap.tab_home_bottom_wode);
                             break;
                         case 2:
                             rb1.setBackgroundResource(R.mipmap.tab_home_bottom_shouye);
                             rb2.setBackgroundResource(R.mipmap.tab_home_bottom_quanzi);
                             rb4.setBackgroundResource(R.mipmap.tab_home_bottom_zhangdan);
                             rb5.setBackgroundResource(R.mipmap.tab_home_bottom_wode);
                             break;
                         case 3:
                             rb1.setBackgroundResource(R.mipmap.tab_home_bottom_shouye);
                             rb2.setBackgroundResource(R.mipmap.tab_home_bottom_quanzi);
                             rb4.setBackgroundResource(R.mipmap.tab_home_bottom_zhangdans);
                             rb5.setBackgroundResource(R.mipmap.tab_home_bottom_wode);
                             break;
                         case 4:
                             rb1.setBackgroundResource(R.mipmap.tab_home_bottom_shouye);
                             rb2.setBackgroundResource(R.mipmap.tab_home_bottom_quanzi);
                             rb4.setBackgroundResource(R.mipmap.tab_home_bottom_zhangdan);
                             rb5.setBackgroundResource(R.mipmap.tab_home_bottom_wodes);
                             break;
                     }
                 }

             @Override
             public void onPageScrollStateChanged(int i) {

             }
         });

        MyFragmentAdapter myFragmentAdapter=new MyFragmentAdapter(getSupportFragmentManager());
         vp.setAdapter(myFragmentAdapter);
         //跳转订单页面
        if(flag==1){
            vp.setCurrentItem(3);
        }else if(flag==2){
            //跳转购物车页面
            vp.setCurrentItem(2);
        }else if(flag==5){
            //跳转我的页面
            vp.setCurrentItem(4);
        }else if(flag==4){
            //跳转订单页面
            vp.setCurrentItem(3);
        }
    }

    public void init() {
        vp=findViewById(R.id.vp);
        rg=findViewById(R.id.rg);
        rb1=findViewById(R.id.rb1);
        rb2=findViewById(R.id.rb2);
        rb3=findViewById(R.id.rb3);
        rb4=findViewById(R.id.rb4);
        rb5=findViewById(R.id.rb5);

    }
    public  class MyFragmentAdapter  extends FragmentPagerAdapter{
        public MyFragmentAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int i) {
           switch (i){
               case 0:
                   fragment=new ShouFragment();
                   break;
               case 1:
                   fragment=new QuanFragment();
                   break;
               case 2:
                   fragment=new GouFragment();
                   break;
               case 3:
                   fragment=new DingFragment();
                   Bundle bundle1=new Bundle();
                   fragment.setArguments(bundle1);
                   break;
               case 4:
                   fragment=new MyFragment();
                   Bundle bundle=new Bundle();
                   bundle.putString("nickName",nickName);
                   fragment.setArguments(bundle);
                   break;
           }
            return fragment;
        }

        @Override
        public int getCount() {
            return 5;
        }
    }

}
