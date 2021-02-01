package com.example.zhugeyouliao.ui.competition.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zhugeyouliao.R;
import com.example.zhugeyouliao.adapter.AFinalRecyclerViewAdapter;
import com.example.zhugeyouliao.base.LazyBaseFragments;
import com.example.zhugeyouliao.ui.competition.adapter.CompetitionTypeAdapter;
import com.example.zhugeyouliao.ui.competition.bean.CompetitionBean;
import com.example.zhugeyouliao.ui.home.adapter.RedPeopleAdapter;
import com.example.zhugeyouliao.utils.AnimationUtils;
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
 * @Time： 2021/1/20 11:03
 * @description： 赛事推荐  分类
 */
public class CompetitionTypeFragment extends LazyBaseFragments implements AFinalRecyclerViewAdapter.OnItemClickListener<CompetitionBean> {

    @BindView(R.id.tv_line)
    TextView tvLine;
    @BindView(R.id.tv_Instant_score)
    TextView tvInstantScore;
    @BindView(R.id.tv_full_time)
    TextView tvFullTime;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.rlv_layout)
    RecyclerView rlvLayout;
    @BindView(R.id.srl_layout)
    SmartRefreshLayout srlLayout;

    private CompetitionTypeAdapter mCompetitionTypeAdapter;

    /**
     * 加载界面
     *
     * @return
     */
    public static CompetitionTypeFragment newInstance(int type) {
        CompetitionTypeFragment fragment = new CompetitionTypeFragment();
        Bundle args = new Bundle();
        args.putInt("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * 获取类型
     *
     * @return
     */
    private int getType() {
        return getArguments().getInt("type", 0);
    }

    @Override
    public View bindLayout(LayoutInflater inflater) {
        return inflater.inflate(R.layout.frag_competition_type, null);
    }

    @Override
    public void initView() {
        initRefreshLayout();
        initRecyclerView();
    }

    @Override
    public void initData() {
        List<CompetitionBean> beanList = new ArrayList<>();
        CompetitionBean bean = new CompetitionBean();
        beanList.add(bean);
        beanList.add(bean);
        beanList.add(bean);
        beanList.add(bean);
        beanList.add(bean);
        beanList.add(bean);
        beanList.add(bean);
        mCompetitionTypeAdapter.refreshList(beanList);
    }

     /**
      * @date: 2021/1/20 15:50
      * @description  初始化刷新
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
     * @date: 2021/1/14 15:48
     * @description 初始化列表
     */
    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        rlvLayout.setLayoutManager(linearLayoutManager);
        mCompetitionTypeAdapter = new CompetitionTypeAdapter(mContext);
        rlvLayout.setAdapter(mCompetitionTypeAdapter);
        mCompetitionTypeAdapter.setOnItemClickListener(this);
    }


    @OnClick({R.id.tv_Instant_score, R.id.tv_full_time, R.id.tv_search, R.id.tv_date})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_Instant_score:
                //即时比分
                AnimationUtils.setAnimation(tvLine.getLayoutParams().width, 0, 0, 0, 200, tvLine);
                break;
            case R.id.tv_full_time:
                //已完场
                AnimationUtils.setAnimation(0, tvLine.getLayoutParams().width, 0, 0, 200, tvLine);
                break;
            case R.id.tv_search:
                //搜索
                break;
            case R.id.tv_date:
                //时间
                break;
        }
    }

    @Override
    public void onItemClick(View view, int position, CompetitionBean model) {

    }

    @Override
    public void onItemLongClick(View view, int position, CompetitionBean model) {

    }
}
