package com.bw.movie.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bw.movie.R;
import com.bw.movie.adapter.MyFragmentPagerAdapter;
import com.bw.movie.fragment.FilmFragment;
import com.bw.movie.fragment.MyFragment;
import com.bw.movie.fragment.ShouYeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowActivity extends AppCompatActivity {


    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.btn_rb1)
    RadioButton btn_rb1;
    @BindView(R.id.btn_rb2)
    RadioButton btn_rb2;
    @BindView(R.id.btn_rb3)
    RadioButton btn_rb3;
    List<Fragment> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);
        list.add(new ShouYeFragment());
        list.add(new FilmFragment());
        list.add(new MyFragment());
        btn_rb1.setBackgroundResource(R.mipmap.filmm);
        MyFragmentPagerAdapter adapter=new MyFragmentPagerAdapter(getSupportFragmentManager(),list);
        viewPager.setAdapter(adapter);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.btn_rb1:
                        viewPager.setCurrentItem(0,false);
                        AnimatorSet animatorSet = new AnimatorSet();//组合动画
                        ObjectAnimator scaleX = ObjectAnimator.ofFloat(btn_rb1, "scaleX", 1, 1.3f,1);
                        ObjectAnimator scaleY = ObjectAnimator.ofFloat(btn_rb1, "scaleY", 1,1.3f,1);
                        animatorSet.setDuration(500);
                        animatorSet.play(scaleX).with(scaleY);//两个动画同时开始
                        animatorSet.start();
                        break;
                    case R.id.btn_rb2:
                        viewPager.setCurrentItem(1,false);
                        AnimatorSet set=new AnimatorSet();
                        ObjectAnimator animator1 = ObjectAnimator.ofFloat(btn_rb2, "scaleX", 1, 1.3f, 1);
                        ObjectAnimator animator2 = ObjectAnimator.ofFloat(btn_rb2, "scaleY", 1, 1.3f, 1);
                        set.setDuration(500);
                        set.play(animator1).with(animator2);
                        set.start();
                        break;
                    case R.id.btn_rb3:
                        viewPager.setCurrentItem(2,false);
                        AnimatorSet set1=new AnimatorSet();
                        ObjectAnimator anim = ObjectAnimator.ofFloat(btn_rb3, "scaleX", 1, 1.3f, 1);
                        ObjectAnimator anim1 = ObjectAnimator.ofFloat(btn_rb3, "scaleY", 1, 1.3f, 1);
                        set1.setDuration(500);
                        set1.play(anim).with(anim1);
                        set1.start();
                        break;
                }
            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                  switch (position){
                      case 0:
                          btn_rb1.setBackgroundResource(R.mipmap.filmm);
                          btn_rb2.setBackgroundResource(R.mipmap.cin);
                          btn_rb3.setBackgroundResource(R.mipmap.my);
                          break;
                      case 1:
                          btn_rb1.setBackgroundResource(R.mipmap.film);
                          btn_rb2.setBackgroundResource(R.mipmap.cinn);
                          btn_rb3.setBackgroundResource(R.mipmap.my);
                              break;
                      case 2:
                          btn_rb1.setBackgroundResource(R.mipmap.film);
                          btn_rb2.setBackgroundResource(R.mipmap.cin);
                          btn_rb3.setBackgroundResource(R.mipmap.myy);
                          break;
                  }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
