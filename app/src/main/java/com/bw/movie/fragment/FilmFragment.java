package com.bw.movie.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bw.movie.R;
import com.bw.movie.adapter.MyFilmAdapter;
import com.bw.movie.view.MyViews;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Author：dell
 * @E-mail： 15001194794@163.com
 * @Date：2019/5/11 10:32
 * @Description：描述信息
 */
public class FilmFragment extends Fragment {
    @BindView(R.id.view_crnema)
    MyViews viewCrnema;
    @BindView(R.id.film_rb1_id)
    RadioButton filmRb1Id;
    @BindView(R.id.film_rb2_id)
    RadioButton filmRb2Id;
    @BindView(R.id.film_radio_group)
    RadioGroup filmRadioGroup;
    @BindView(R.id.film_viewpager_id)
    ViewPager filmViewpagerId;
    Unbinder unbinder;

    List<Fragment> list=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_film, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list.add(new Recommend_theaters_Fragment());
        list.add(new Near_the_cinema_Fragment());
        MyFilmAdapter filmAdapter=new MyFilmAdapter(getActivity().getSupportFragmentManager(),list);
        filmViewpagerId.setAdapter(filmAdapter);

        filmRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.film_rb1_id:
                        filmViewpagerId.setCurrentItem(0,false);
                        break;
                    case R.id.film_rb2_id:
                        filmViewpagerId.setCurrentItem(1,false);
                        break;
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
