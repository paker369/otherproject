package com.example.zhugeyouliao.ui.mine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zhugeyouliao.R;
import com.example.zhugeyouliao.adapter.AFinalRecyclerViewAdapter;
import com.example.zhugeyouliao.base.BaseActivity;
import com.example.zhugeyouliao.ui.mine.adapter.MyPubishGambitAdapter;
import com.example.zhugeyouliao.ui.mine.bean.MyPublishGambitBean;
import com.example.zhugeyouliao.utils.StatusBarUtils;
import com.example.zhugeyouliao.widget.SlideRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author： longyu
 * @Time： 2021/1/21 14:32
 * @description： 我参与的话题
 */
public class MyParticipationGambitActivity extends BaseActivity implements AFinalRecyclerViewAdapter.OnItemClickListener<MyPublishGambitBean> {


    @BindView(R.id.status_bar_view)
    View statusBarView;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.center_title)
    TextView centerTitle;
    @BindView(R.id.srlv_layout)
    SlideRecyclerView rlvLayout;
    @BindView(R.id.llyt_no_data)
    LinearLayout llytNoData;
    @BindView(R.id.srl_layout)
    SmartRefreshLayout srlLayout;
    private MyPubishGambitAdapter mMyPubishGambitAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_collect;
    }

    @Override
    protected void initData() {
        statusBarView.getLayoutParams().height = StatusBarUtils.getStatusBarHeight(mContext);
        initTitle(getString(R.string.mine_gambit));
        initRefreshLayout();
        initRecyclerView();
        getData();
    }

    /**
     * @date: 2021/1/20 18:00
     * @description 初始化列表
     */
    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        rlvLayout.setLayoutManager(layoutManager);
        mMyPubishGambitAdapter = new MyPubishGambitAdapter(mContext);
        rlvLayout.setAdapter(mMyPubishGambitAdapter);
        mMyPubishGambitAdapter.setOnItemClickListener(this);
    }

    /**
     * @description 初始化刷新
     * @date: 2021/1/13 20:28
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
     * @date: 2021/1/20 18:28
     * @description 获取数据
     */
    private void getData() {
        List<MyPublishGambitBean> beanList = new ArrayList<>();
        MyPublishGambitBean myCollectBean = new MyPublishGambitBean();
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
        mMyPubishGambitAdapter.refreshList(beanList);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onItemClick(View view, int position, MyPublishGambitBean model) {
    }

    @Override
    public void onItemLongClick(View view, int position, MyPublishGambitBean model) {

    }

}
