package com.bw.myproduct.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.bw.myproduct.R;


@SuppressLint("ValidFragment")
public class JumpFragment extends Fragment {
    PagerSlidingTabStrip tabs;
     ImageView jump_back;
     String[] arr={"商品","详情","评论"};
    private View view;
    ViewPager ViewPager_id;
    public  Fragment fragment=null;
    FrameLayout frameLayout_id;
    private int id;
    private String content;

    @SuppressLint("ValidFragment")
    public JumpFragment(FrameLayout frameLayout_id) {
        this.frameLayout_id=frameLayout_id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.jump_fragment,null);
        tabs= view.findViewById(R.id.tabs);
         init();
         initData();
        return view;
    }

    public void init() {
        //点击返回按钮进行返回
        jump_back=view.findViewById(R.id.jump_back);
        jump_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frameLayout_id.setVisibility(View.GONE);
            }
        });
    }

    public  void initData() {
        //得到商品的id和搜索的名字
        Bundle bundle = getArguments();
        id = bundle.getInt("id");
        content = bundle.getString("content");
        //横向滑动菜单
        ViewPager_id=view.findViewById(R.id.ViewPager_id);
        JumpAdapter jumpAdapter=new JumpAdapter(getChildFragmentManager());
        ViewPager_id.setAdapter(jumpAdapter);
        tabs.setViewPager(ViewPager_id);

    }
      public  class  JumpAdapter  extends FragmentPagerAdapter{
          @Nullable
          @Override
          public CharSequence getPageTitle(int position) {
              return arr[position];
          }

          public JumpAdapter(FragmentManager fm) {
              super(fm);
          }

          @Override
          public Fragment getItem(int i) {
              switch (i){
                  //商品Fragment
                  case 0:
                      fragment=new ProductFragment(frameLayout_id);
                      Bundle bundle2=new Bundle();
                      bundle2.putString("content",content);
                      fragment.setArguments(bundle2);
                      break;
                      //详情Fragment
                  case 1:
                      fragment=new XiangFragment();
                      Bundle bundle=new Bundle();
                      bundle.putInt("id",id);
                      fragment.setArguments(bundle);
                      break;
                      //评论Fragment
                  case 2:
                      fragment=new PingFragment();
                      Bundle bundle1=new Bundle();
                      bundle1.putInt("id",id);
                      fragment.setArguments(bundle1);
                      break;
              }
              return fragment;
          }

          @Override
          public int getCount() {
              return 3;
          }
      }

}
