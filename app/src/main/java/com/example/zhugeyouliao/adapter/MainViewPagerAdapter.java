package com.example.zhugeyouliao.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.zhugeyouliao.base.LazyBaseFragments;

import java.util.List;

/**
 * @Author： longyu
 * @Time： 2021/1/13 16:19
 * @description：  首页viewpager适配器
 */
public class MainViewPagerAdapter extends FragmentPagerAdapter {

    private List<String> mDatas;

    private List<LazyBaseFragments> mFragments;

    public MainViewPagerAdapter(FragmentManager fm, List<LazyBaseFragments> fragmentList) {
        super(fm);
        this.mFragments = fragmentList;
    }

    public MainViewPagerAdapter(@NonNull FragmentManager fm, List<String> mDatas, List<LazyBaseFragments> fragments) {
        super(fm);
        this.mDatas = mDatas;
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mDatas.get(position);
    }
}
