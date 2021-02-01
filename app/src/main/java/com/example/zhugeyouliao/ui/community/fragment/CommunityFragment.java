package com.example.zhugeyouliao.ui.community.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.viewpager.widget.ViewPager;

import com.androidkun.xtablayout.XTabLayout;
import com.example.zhugeyouliao.R;
import com.example.zhugeyouliao.adapter.MainViewPagerAdapter;
import com.example.zhugeyouliao.base.LazyBaseFragments;
import com.example.zhugeyouliao.ui.material.fragment.MaterialTypeFrament;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 社区
 */
public class CommunityFragment extends LazyBaseFragments {

    @BindView(R.id.xTablayout)
    XTabLayout xTablayout;
    @BindView(R.id.iv_gift)
    ImageView ivGift;
    @BindView(R.id.vp_frag_home)
    ViewPager vpFragHome;

    @Override
    public View bindLayout(LayoutInflater inflater) {
        return inflater.inflate(R.layout.frag_competition, null);
    }

    @Override
    public void initView() {
        ArrayList<LazyBaseFragments> mFragmentList = new ArrayList<>();
        mFragmentList.add(CommunityHotFragment.newInstance());
        mFragmentList.add(CommunityPlazaFragment.newInstance());

        List<String> mTabNames = new ArrayList<>();
        mTabNames.add(getString(R.string.hot));
        mTabNames.add(getString(R.string.plaza));

        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getChildFragmentManager(), mTabNames, mFragmentList);
        vpFragHome.setAdapter(mainViewPagerAdapter);
        xTablayout.setupWithViewPager(vpFragHome);
        vpFragHome.setCurrentItem(0);
    }


    @Override
    public void initData() {

    }
}
