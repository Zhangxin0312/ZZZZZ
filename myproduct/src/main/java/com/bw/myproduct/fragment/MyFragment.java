package com.bw.myproduct.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.myproduct.R;
import com.bw.myproduct.view.AddressListActivity;
import com.bw.myproduct.view.MyDataActivity;
import com.bw.myproduct.view.MyFootActivity;
import com.bw.myproduct.view.MyQuanActivity;
import com.bw.myproduct.view.MyWalletActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import static android.content.Context.MODE_PRIVATE;

public class MyFragment extends Fragment {
    TextView my_zl,my_qz,my_zj,my_qb,my_address,my_Nick_id;
     View view;
    SimpleDraweeView my_image;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.my_fragment,null);
        my_Nick_id=view.findViewById(R.id.my_Nick_id);
        my_image=view.findViewById(R.id.my_image);
        //我的资料
        initData();
        //我的圈子
        initData2();
        //我的足迹
        initData3();
        //我的钱包
        initData4();
        //我的收货地址
        initData5();
        return view;
    }
    private void initData() {
        //我的资料
        my_zl=view.findViewById(R.id.my_zl);
        my_zl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转我的资料的Activity
                 Intent intent=new Intent(getActivity(),MyDataActivity.class);
                 startActivity(intent);
            }
        });
        //修改后的名字
        SharedPreferences share = getActivity().getSharedPreferences("share", MODE_PRIVATE);
        String newName = share.getString("newName", null);
        String newImage = share.getString("newImage", null);
        my_Nick_id.setText(newName);
        Log.e("tag",newImage);
        my_image.setImageURI(newImage);
    }
    private void initData2() {
        //我的圈子
        my_qz=view.findViewById(R.id.my_qz);
        my_qz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),MyQuanActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initData3() {
        //我的足迹
        my_zj=view.findViewById(R.id.my_zj);
        my_zj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),MyFootActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initData4() {
        //我的钱包
        my_qb = view.findViewById(R.id.my_qb);
        my_qb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),MyWalletActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initData5() {
        //我的收货地址
        my_address=view.findViewById(R.id.my_address);
        my_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),AddressListActivity.class);
                startActivity(intent);
            }
        });
    }
}
