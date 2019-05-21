package com.bw.movie.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.activity.PageListActivity;
import com.bw.movie.activity.RecordFeedBackActivity;
import com.bw.movie.activity.RecordListActivity;
import com.bw.movie.activity.UserInfoActivity;
import com.bw.movie.contract.ContractInter;
import com.bw.movie.presenter.Presenter;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author：dell
 * @E-mail： 15001194794@163.com
 * @Date：2019/5/11 10:32
 * @Description：描述信息
 */
public class MyFragment extends Fragment implements ContractInter.MyInter {
    @BindView(R.id.my_tou)
    SimpleDraweeView my_tou;
    @BindView(R.id.my_user_info)
    LinearLayout my_user_info;
    @BindView(R.id.signIn_id)
    Button signIn_id;
    @BindView(R.id.recordList_id)
    LinearLayout recordList_id;
    @BindView(R.id.page_id)
    LinearLayout page_id;
    @BindView(R.id.feed_back_id)
    LinearLayout feed_back_id;
    ContractInter.PresenterInter presenterInter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_my,container,false);
        ButterKnife.bind(this,view);
        my_tou.setImageResource(R.mipmap.mytou);
        presenterInter=new Presenter<>(this);
        //我的信息
        initData1();
        //我的关注
        initData2();
        //购票记录
        initData3();
        //意见反馈
        initData4();
//        initData5();
//        initData6();
        return view;
    }
    //我的信息
    public void initData1() {
        my_user_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),UserInfoActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
            }
        });
        //签到
        signIn_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenterInter.toSignIn();
            }
        });
    }
    //我的关注
    public void initData2() {
        page_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),PageListActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
            }
        });
    }
    //购票记录
    public void initData3() {
        recordList_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent=new Intent(getActivity(),RecordListActivity.class);
               startActivity(intent);
                getActivity().overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
            }
        });
    }
    //意见反馈
    public void initData4() {
        feed_back_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),RecordFeedBackActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
            }
        });
    }
    //我的信息
    public void initData5() {
//        my_data.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(getActivity(),);
//                startActivity(intent);
//            }
//        });
    }
    //我的信息
    public void initData6() {
//        my_data.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(getActivity(),);
//                startActivity(intent);
//            }
//        });
    }
   //签到
    @Override
    public void MySign(String str) {
        Toast.makeText(getActivity(),str,Toast.LENGTH_SHORT).show();
    }
}
