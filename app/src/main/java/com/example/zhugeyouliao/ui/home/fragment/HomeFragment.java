package com.example.zhugeyouliao.ui.home.fragment;


import android.view.LayoutInflater;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.androidkun.xtablayout.XTabLayout;
import com.example.zhugeyouliao.R;
import com.example.zhugeyouliao.adapter.MainViewPagerAdapter;
import com.example.zhugeyouliao.base.LazyBaseFragments;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 首页
 */
public class HomeFragment extends LazyBaseFragments {

    @BindView(R.id.xTablayout)
    XTabLayout xTablayout;
    @BindView(R.id.vp_frag_home)
    ViewPager vpFragHome;

    @Override
    public View bindLayout(LayoutInflater inflater) {
        return inflater.inflate(R.layout.frag_home, null);
    }

    @Override
    public void initView() {
        ArrayList<LazyBaseFragments> mFragmentList = new ArrayList<>();
        RecommendFragment recommendFragment = new RecommendFragment();
        RedPeopleFragment redPeopleFragment = new RedPeopleFragment();
        mFragmentList.add(recommendFragment);
        mFragmentList.add(redPeopleFragment);

        List<String> mTabNames = new ArrayList<>();
        mTabNames.add(getString(R.string.recommend));
        mTabNames.add(getString(R.string.red_people));

        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getChildFragmentManager(), mTabNames, mFragmentList);
        vpFragHome.setAdapter(mainViewPagerAdapter);
        xTablayout.setupWithViewPager(vpFragHome);
        vpFragHome.setCurrentItem(0);
    }

    @Override
    public void initData() {

    }
}
