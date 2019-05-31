package com.bw.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bw.movie.R;
import com.bw.movie.adapter.MyPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuideActivity extends AppCompatActivity {

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    List<Integer> list = new ArrayList<>();
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                //1.获取当前页的下标
                int i = viewPager.getCurrentItem();
                //2.当前页下标++
                i++;
                //3.设置显示的页 aaa
                viewPager.setCurrentItem(i);
//                Intent intent=new Intent(GuideActivity.this,LoginActivity.class);
//                startActivity(intent);
  




                //4.重新发送消息
                handler.sendEmptyMessageDelayed(0, 1500);
            }
        }

        ;
    };
    @BindView(R.id.rb1)
    RadioButton rb1;
    @BindView(R.id.rb2)
    RadioButton rb2;
    @BindView(R.id.rb3)
    RadioButton rb3;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.rb4)
    RadioButton rb4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        //添加图片到集合
        list.add(R.mipmap.yd);
        list.add(R.mipmap.aaa);
        list.add(R.mipmap.yo);
        list.add(R.mipmap.yt);
        //创建适配器
        MyPagerAdapter adapter = new MyPagerAdapter(list, GuideActivity.this, handler);
        //设置
        viewPager.setAdapter(adapter);
        //设置监听器
        //5.切换页面,,,改变小圆点的位置
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(list.size()-1==position){
                        Intent intent=new Intent(GuideActivity.this,LoginActivity.class);
                        startActivity(intent);

                }
            }

            @Override
            public void onPageSelected(int position) {
                radioGroup.check(radioGroup.getChildAt(position % list.size()).getId());


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //自动无限轮播
        handler.sendEmptyMessageDelayed(0, 1500);

    }
}
