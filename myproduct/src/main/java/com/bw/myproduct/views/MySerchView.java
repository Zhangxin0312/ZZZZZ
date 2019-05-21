package com.bw.myproduct.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bw.myproduct.R;

public class MySerchView  extends LinearLayout {
  public   EditText show_name;
  public   Button show_sou;
  public    ImageView show_iv;
    public MySerchView(Context context) {
        super(context);
    }

    public MySerchView(Context context,  AttributeSet attrs) {
        super(context, attrs);
        //搜索 name  图片 按钮
        View view =LayoutInflater.from(context).inflate(R.layout.serch_view,this);
        show_name= view.findViewById(R.id.show_name);
        show_sou= view.findViewById(R.id.show_sou);
        show_iv=view.findViewById(R.id.show_iv);
    }
}
