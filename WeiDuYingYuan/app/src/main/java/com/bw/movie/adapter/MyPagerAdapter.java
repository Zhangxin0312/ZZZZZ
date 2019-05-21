package com.bw.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * @Author：dell
 * @E-mail： 15001194794@163.com
 * @Date：2019/5/10 14:59
 * @Description：描述信息
 */
public class MyPagerAdapter extends PagerAdapter {
    List<Integer> list;
    Context context;
    Handler handler;

    public MyPagerAdapter(List<Integer> list, Context context, Handler handler) {
        this.list = list;
        this.context = context;
        this.handler = handler;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }
    //加载视图的fangfa

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //创建图片控件
        ImageView imageView=new ImageView(context);
        //设置图片
        imageView.setImageResource(list.get(position%list.size()));
        //设置图片的伸缩尺寸
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        //添加到容器
        container.addView(imageView);
        return imageView;
    }
    //销毁视图的方法

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
