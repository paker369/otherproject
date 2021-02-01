package com.example.zhugeyouliao.ui.community.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zhugeyouliao.MyApplication;
import com.example.zhugeyouliao.R;
import com.example.zhugeyouliao.adapter.AFinalRecyclerViewAdapter;
import com.example.zhugeyouliao.base.LazyBaseFragments;
import com.example.zhugeyouliao.ui.community.activity.CommunityArticleDetailsActivity;
import com.example.zhugeyouliao.ui.community.adapter.CommunityHotAdapter;
import com.example.zhugeyouliao.ui.mine.bean.MyCollectBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author： longyu
 * @Time： 2021/1/22 6:34
 * @description： 社区  热门
 */
public class CommunityHotFragment extends LazyBaseFragments implements AFinalRecyclerViewAdapter.OnItemClickListener<MyCollectBean> {

    @BindView(R.id.tv_hot_search)
    TextView tvHotSearch;
    @BindView(R.id.rlv_layout)
    RecyclerView rlvLayout;
    @BindView(R.id.llyt_no_data)
    LinearLayout llytNoData;
    @BindView(R.id.srl_layout)
    SmartRefreshLayout srlLayout;

    private CommunityHotAdapter mCommunityHotAdapter;

    /**
     * 加载界面
     *
     * @return
     */
    public static CommunityHotFragment newInstance() {
        CommunityHotFragment fragment = new CommunityHotFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View bindLayout(LayoutInflater inflater) {
        return inflater.inflate(R.layout.frag_community_hot, null);
    }

    @Override
    public void initView() {
        initRefreshLayout();
        initRecyclerView();
    }

    @Override
    public void initData() {
        getData();
    }

     /**
      * @date: 2021/1/22 6:48
      * @description 初始化刷新
      */
    private void initRefreshLayout() {
        srlLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh();
            }
        });
        srlLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMoreWithNoMoreData();
            }
        });
    }

     /**
      * @date: 2021/1/22 6:48
      * @description 初始化列表
      */
    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        rlvLayout.setLayoutManager(linearLayoutManager);
        mCommunityHotAdapter = new CommunityHotAdapter(mContext);
        rlvLayout.setAdapter(mCommunityHotAdapter);
        mCommunityHotAdapter.setOnItemClickListener(this);
    }

     /**
      * @date: 2021/1/22 6:53
      * @description 获取数据
      */
    private void getData() {
        List<MyCollectBean> beanList = new ArrayList<>();
        MyCollectBean myCollectBean = new MyCollectBean();
        beanList.add(myCollectBean);
        beanList.add(myCollectBean);
        beanList.add(myCollectBean);
        beanList.add(myCollectBean);
        beanList.add(myCollectBean);
        beanList.add(myCollectBean);
        beanList.add(myCollectBean);
        beanList.add(myCollectBean);
        beanList.add(myCollectBean);
        beanList.add(myCollectBean);
        beanList.add(myCollectBean);
        mCommunityHotAdapter.refreshList(beanList);
    }


    @OnClick(R.id.tv_hot_search)
    public void onClick() {
        //搜索
    }

    @Override
    public void onItemClick(View view, int position, MyCollectBean model) {
        MyApplication.openActivity(mContext, CommunityArticleDetailsActivity.class);
    }

    @Override
    public void onItemLongClick(View view, int position, MyCollectBean model) {

    }
}
