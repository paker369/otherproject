package com.example.zhugeyouliao.ui.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zhugeyouliao.MyApplication;
import com.example.zhugeyouliao.R;
import com.example.zhugeyouliao.adapter.AFinalRecyclerViewAdapter;
import com.example.zhugeyouliao.api.NetUrlUtils;
import com.example.zhugeyouliao.base.LazyBaseFragments;
import com.example.zhugeyouliao.config.Constants;
import com.example.zhugeyouliao.http.BaseCallBack;
import com.example.zhugeyouliao.http.BaseOkHttpClient;
import com.example.zhugeyouliao.ui.home.activity.BasketballDetailsActivity;
import com.example.zhugeyouliao.ui.home.activity.ElectronicSportsDetailsActivity;
import com.example.zhugeyouliao.ui.home.activity.FootballDetailsActivity;
import com.example.zhugeyouliao.ui.home.adapter.RecommendAdpater;
import com.example.zhugeyouliao.ui.home.bena.BannerBean;
import com.example.zhugeyouliao.ui.home.bena.RecommendBean;
import com.example.zhugeyouliao.ui.home.bena.RecommendContestBean;
import com.example.zhugeyouliao.ui.home.bena.RecommendHotBean;
import com.example.zhugeyouliao.utils.JSONUtils;
import com.example.zhugeyouliao.utils.LogUtils;
import com.example.zhugeyouliao.utils.LoginCheckUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import kotlin.collections.ArrayDeque;
import okhttp3.Call;

/**
 * @Author： longyu
 * @Time： 2021/1/13 20:03
 * @description： 首页推荐
 */
public class RecommendFragment extends LazyBaseFragments implements RecommendAdpater.OnRecommendItemClickListener {

    @BindView(R.id.rlv_layout)
    RecyclerView rlvLayout;
    @BindView(R.id.srl_layout)
    SmartRefreshLayout srlLayout;

    private RecommendAdpater mRecommendAdpater;

    private RecommendBean mRecommendBean = new RecommendBean();

    private List<RecommendBean> mRecommendist = new ArrayDeque<>();

    //type 1足球 2篮球
    private int mHotType = 1;
    //页数
    private int mPage = 1;
    //个数
    private int mSize = 10;

    @Override
    public View bindLayout(LayoutInflater inflater) {
        return inflater.inflate(R.layout.frag_recomment, null);
    }

    @Override
    public void initView() {
        initRefreshLayout();
        initRecyclerView();
    }

    /**
     * @description 初始化刷新
     * @date: 2021/1/13 20:28
     */
    private void initRefreshLayout() {
        srlLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPage = 1;
                mRecommendist.clear();
                mRecommendist.add(mRecommendBean);
                mRecommendist.add(mRecommendBean);
                mRecommendAdpater.refreshList(mRecommendist);
                getBannerData(false);
            }
        });
        srlLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPage++;
                getHomeListData(false);
            }
        });
    }

    /**
     * @date: 2021/1/13 20:29
     * @description 初始化列表数据
     */
    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        rlvLayout.setLayoutManager(linearLayoutManager);
        mRecommendAdpater = new RecommendAdpater(mContext);
        rlvLayout.setAdapter(mRecommendAdpater);
        mRecommendAdpater.setOnRecommendItemClickListener(this);
        mRecommendist.add(mRecommendBean);
        mRecommendist.add(mRecommendBean);
        mRecommendAdpater.refreshList(mRecommendist);
    }

    @Override
    public void initData() {
        getBannerData(false);
    }

    /**
     * 获取轮播图数据
     */
    private void getBannerData(boolean isLoading) {
        BaseOkHttpClient.newBuilder()
                .url(NetUrlUtils.HOME_BANNER)
                .isLoading(isLoading)
                .get()
                .form()
                .build()
                .enqueue(mContext, new BaseCallBack<String>() {

                    @Override
                    public void onSuccess(String s, String msg) {
                        List<BannerBean> bannerBeans = JSONUtils.jsonString2Beans(s, BannerBean.class);
                        mRecommendBean.setBanners(bannerBeans);
                        mRecommendAdpater.notifyDataSetChanged();
                        getHotContestData(isLoading);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        toast(msg);
                        getHotContestData(isLoading);
                    }

                    @Override
                    public void onFailure(Call call, IOException e) {
                        toast(e.toString());
                        getHotContestData(isLoading);
                    }
                });
    }

    /**
     * @date: 2021/1/27 15:16
     * @description 获取热门比赛
     */
    private void getHotContestData(boolean isLoading) {
        BaseOkHttpClient.newBuilder()
                .url(NetUrlUtils.HOME_HOT_CONTEST)
                .addParam("current", 1)
                .addParam("size", 3)
                .addParam("searchInt1", MyApplication.mPreferenceProvider.getUserId())
                .addParam("searchInt2", mHotType)
                .addParam("searchInt5", "521")
                .isLoading(isLoading)
                .post()
                .json()
                .form()
                .build()
                .enqueue(mContext, new BaseCallBack<String>() {

                    @Override
                    public void onSuccess(String s, String msg) {
                        RecommendHotBean hotBean = JSONUtils.jsonString2Bean(s, RecommendHotBean.class);
                        if (hotBean != null) {
                            mRecommendBean.setHotBeans(hotBean.getRecords());
                            mRecommendAdpater.notifyDataSetChanged();
                        }
                        getHomeListData(isLoading);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        toast(msg);
                        getHomeListData(isLoading);
                    }

                    @Override
                    public void onFailure(Call call, IOException e) {
                        toast(e.toString());
                        getHomeListData(isLoading);
                    }
                });
    }

    /**
     * @date: 2021/1/26 16:44
     * @description 获取列表数据
     */
    private void getHomeListData(boolean isLoading) {
        BaseOkHttpClient.newBuilder()
                .url(NetUrlUtils.HOME_RECOMMEND)
                .addParam("current", mPage)
                .addParam("size", mSize)
                .addParam("searchInt2", mHotType)
                .isLoading(isLoading)
                .post()
                .json()
                .form()
                .build()
                .enqueue(mContext, new BaseCallBack<String>() {

                    @Override
                    public void onSuccess(String s, String msg) {
                        RecommendContestBean contestBean = JSONUtils.jsonString2Bean(s, RecommendContestBean.class);
                        if (contestBean != null) {
                            if (contestBean.getRecords() != null) {
                                mRecommendBean.setContestBeans(contestBean.getRecords());
                                mRecommendAdpater.notifyDataSetChanged();
                            }
                            initRecommendData(contestBean.getRecords());
                        }
                    }

                    @Override
                    public void onError(int code, String msg) {
                        toast(msg);
                        initRecommendData(null);
                    }

                    @Override
                    public void onFailure(Call call, IOException e) {
                        toast(e.toString());
                        initRecommendData(null);
                    }
                });
    }


    /**
     * 初始化推荐数据
     */
    private void initRecommendData(List<RecommendContestBean.RecordsBean> records) {
        if (mPage == 1) {
            srlLayout.finishRefresh();
        } else {
            if (records != null && records.size() >= mSize) {
                srlLayout.finishLoadMore();
            } else {
                srlLayout.finishLoadMoreWithNoMoreData();
            }
        }
    }

    @Override
    public void onTypeClick(View view, int type) {
        mHotType = type;
        getHotContestData(true);
    }

    @Override
    public void onCommendItemClick(View view, int position, int childPosition, RecommendHotBean.RecordsBean model) {
        if(!LoginCheckUtils.checkLoginShowDialog(mContext)){
            return;
        }

        Bundle bundle = new Bundle();
        bundle.putSerializable("bean", (Serializable) model);
        if (mHotType == 1) {
            MyApplication.openActivity(mContext, FootballDetailsActivity.class, bundle);
        } else if (mHotType == 2) {
            MyApplication.openActivity(mContext, BasketballDetailsActivity.class, bundle);
        } else {
            MyApplication.openActivity(mContext, ElectronicSportsDetailsActivity.class, bundle);
        }
    }

    @Override
    public void onCommendListItemClick(View view, int position, int childPosition, RecommendContestBean.RecordsBean model) {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRecommendBean = null;
        mRecommendist.clear();
    }
}
