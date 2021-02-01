package com.example.zhugeyouliao.ui.community.activity;

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
import com.example.zhugeyouliao.ui.community.adapter.CommunityArticleDetailsAadapter;
import com.example.zhugeyouliao.ui.mine.adapter.MyCollectAdapter;
import com.example.zhugeyouliao.ui.mine.bean.MyCollectBean;
import com.example.zhugeyouliao.utils.StatusBarUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shehuan.niv.NiceImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Author： longyu
 * @Time： 2021/1/24 19:09
 * @description： 社区分类
 */
public class CommunityPlazaTypeActivity extends BaseActivity implements AFinalRecyclerViewAdapter.OnItemClickListener<MyCollectBean> {
    @BindView(R.id.status_bar_view)
    View statusBarView;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.center_title)
    TextView centerTitle;
    @BindView(R.id.iv_right_img)
    ImageView ivRightImg;
    @BindView(R.id.niv_plaza_type_top_bg)
    NiceImageView nivPlazaTypeTopBg;
    @BindView(R.id.iv_plaza_type)
    ImageView ivPlazaType;
    @BindView(R.id.tv_plaza_state)
    TextView tvPlazaState;
    @BindView(R.id.tv_plaza_top_content)
    TextView tvPlazaTopContent;
    @BindView(R.id.rlv_plaza_layout)
    RecyclerView rlvPlazaLayout;
    @BindView(R.id.llyt_no_data)
    LinearLayout llytNoData;
    @BindView(R.id.srl_plaza_type_layout)
    SmartRefreshLayout srlPlazaTypeLayout;

    private MyCollectAdapter mMyCollectAdapter;

    /**
     * 获取标题
     *
     * @return
     */
    private String getPlazaTitle() {
        return getIntent().getStringExtra("title");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_community_plaza_type;
    }

    @Override
    protected void initData() {
        statusBarView.getLayoutParams().height = StatusBarUtils.getStatusBarHeight(mContext);
        initTitle(getPlazaTitle());
        ivRightImg.setImageResource(R.mipmap.ic_community_gift);

        initRefreshLayout();
        initRecyclerView();
        getData();
    }

    /**
     * @date: 2021/1/22 6:48
     * @description 初始化刷新
     */
    private void initRefreshLayout() {
        srlPlazaTypeLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh();
            }
        });
        srlPlazaTypeLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
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
        rlvPlazaLayout.setLayoutManager(linearLayoutManager);
        mMyCollectAdapter = new MyCollectAdapter(mContext);
        rlvPlazaLayout.setAdapter(mMyCollectAdapter);
        mMyCollectAdapter.setOnItemClickListener(this);
    }

    /**
     * @date: 2021/1/20 18:28
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
        mMyCollectAdapter.refreshList(beanList);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_right_img, R.id.tv_plaza_state, R.id.tv_issue})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_right_img:
                //礼物
                break;
            case R.id.tv_plaza_state:
                //状态
                break;
            case R.id.tv_issue:
                //发布
                break;
        }
    }

    @Override
    public void onItemClick(View view, int position, MyCollectBean model) {

    }

    @Override
    public void onItemLongClick(View view, int position, MyCollectBean model) {

    }
}
