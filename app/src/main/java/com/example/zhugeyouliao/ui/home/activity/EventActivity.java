package com.example.zhugeyouliao.ui.home.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zhugeyouliao.R;
import com.example.zhugeyouliao.adapter.AFinalRecyclerViewAdapter;
import com.example.zhugeyouliao.base.BaseActivity;
import com.example.zhugeyouliao.ui.home.adapter.EventAdapter;
import com.example.zhugeyouliao.ui.home.bena.EventBean;
import com.example.zhugeyouliao.utils.StatusBarUtils;
import com.example.zhugeyouliao.utils.ToastUtils;
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
 * @Time： 2021/1/15 12:02
 * @description： 首页活动
 */
public class EventActivity extends BaseActivity implements AFinalRecyclerViewAdapter.OnItemClickListener<EventBean> {

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.center_title)
    TextView centerTitle;
    @BindView(R.id.rlv_layout)
    RecyclerView rlvLayout;
    @BindView(R.id.llyt_no_data)
    LinearLayout llytNoData;
    @BindView(R.id.srl_layout)
    SmartRefreshLayout srlLayout;
    @BindView(R.id.status_bar_view)
    View statusBarView;

    private EventAdapter mEventAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_event;
    }

    @Override
    protected void initData() {
        statusBarView.getLayoutParams().height = StatusBarUtils.getStatusBarHeight(mContext);

        initTitle(getString(R.string.activity));
        initRefreshLayout();
        initRecyclerView();
        getData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    /**
     * @date: 2021/1/15 11:33
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
     * @date: 2021/1/15 11:32
     * @description 初始化列表
     */
    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        rlvLayout.setLayoutManager(linearLayoutManager);
        mEventAdapter = new EventAdapter(mContext);
        rlvLayout.setAdapter(mEventAdapter);
        mEventAdapter.setOnItemClickListener(this);
    }

    /**
     * @date: 2021/1/15 11:52
     * @description 获取数据
     */
    private void getData() {
        List<EventBean> beans = new ArrayList<>();
        EventBean eventBean = new EventBean();
        beans.add(eventBean);
        beans.add(eventBean);
        beans.add(eventBean);
        beans.add(eventBean);
        beans.add(eventBean);
        beans.add(eventBean);
        beans.add(eventBean);
        beans.add(eventBean);
        beans.add(eventBean);
        beans.add(eventBean);
        beans.add(eventBean);
        beans.add(eventBean);
        mEventAdapter.refreshList(beans);
        llytNoData.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(View view, int position, EventBean model) {
        ToastUtils.show(mContext, "sgdgsdgsdgds");
    }

    @Override
    public void onItemLongClick(View view, int position, EventBean model) {

    }
}
