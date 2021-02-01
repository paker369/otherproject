package com.example.zhugeyouliao.ui.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zhugeyouliao.R;
import com.example.zhugeyouliao.api.NetUrlUtils;
import com.example.zhugeyouliao.base.LazyBaseFragments;
import com.example.zhugeyouliao.http.BaseCallBack;
import com.example.zhugeyouliao.http.BaseOkHttpClient;
import com.example.zhugeyouliao.ui.home.activity.ElectronicSportsDetailsActivity;
import com.example.zhugeyouliao.ui.home.adapter.MatchesAdapter;
import com.example.zhugeyouliao.ui.home.bena.MatchesBean;
import com.example.zhugeyouliao.ui.home.bena.RecommendHotBean;
import com.example.zhugeyouliao.utils.JSONUtils;
import com.example.zhugeyouliao.utils.LogUtils;

import java.io.IOException;
import java.io.Serializable;

import butterknife.BindView;
import okhttp3.Call;

/**
 * @Author： longyu
 * @Time： 2021/1/15 20:37
 * @description： 电竞详情 赛事预测
 */
public class MatchesFragment extends LazyBaseFragments {

    @BindView(R.id.rlv_layout)
    RecyclerView rlvLayout;
    @BindView(R.id.llyt_no_data)
    LinearLayout llytNoData;

    private MatchesAdapter mMatchesAdapter;

    private int mPage = 1;

    private boolean mIsVisibleToUser = false;

    /**
     * 加载界面
     *
     * @param footBallContestData
     * @return
     */
    public static MatchesFragment newInstance(RecommendHotBean.RecordsBean footBallContestData) {
        MatchesFragment fragment = new MatchesFragment();
        Bundle args = new Bundle();
        args.putSerializable("footBallContestData", (Serializable) footBallContestData);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * @date: 2021/1/17 19:41
     * @description 获取数据
     */
    private RecommendHotBean.RecordsBean getMatchesBeans() {
        return (RecommendHotBean.RecordsBean) getArguments().getSerializable("footBallContestData");
    }

    @Override
    public View bindLayout(LayoutInflater inflater) {
        return inflater.inflate(R.layout.layout_recycleview, null);
    }

    @Override
    public void initView() {
        initAdapter();
    }

    @Override
    public void initData() {
        getMatchesData(true);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mIsVisibleToUser = isVisibleToUser;
    }

    /**
     * 下拉刷新 上拉加载
     */
    public void onRefresh(boolean isTop) {
        if (mIsVisibleToUser) {
            if (isTop) {
                mPage = 1;
                getMatchesData(false);
            } else {
                mPage++;
                getMatchesData(false);
            }
        }
    }

    /**
     * @date: 2021/1/16 22:33
     * @description 初始化适配器
     */
    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        rlvLayout.setLayoutManager(layoutManager);
        mMatchesAdapter = new MatchesAdapter(mContext);
        rlvLayout.setAdapter(mMatchesAdapter);
    }

    /**
     * @date: 2021/1/29 15:35
     * @description 获取数据
     */
    private void getMatchesData(boolean isLoading) {
        BaseOkHttpClient.newBuilder()
                .url(NetUrlUtils.GET_CREATE_FORECAST_LIST)
                .addParam("current", mPage)
                .addParam("size", 10)
                .addParam("searchInt1", getMatchesBeans().getType())
                .addParam("searchStr1", getMatchesBeans().getMatchId())
                .isLoading(isLoading)
                .form()
                .post()
                .json()
                .build()
                .enqueue(mContext, new BaseCallBack<String>() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        LogUtils.e("TAG", "---" + data);
                        MatchesBean matchesBean = JSONUtils.jsonString2Bean(data, MatchesBean.class);
                        if (matchesBean != null) {
                            if (matchesBean.getRecords() != null && matchesBean.getRecords().size() > 0) {
                                llytNoData.setVisibility(View.GONE);
                            } else {
                                llytNoData.setVisibility(View.VISIBLE);
                            }
                        }
                        if (mPage == 1) {
                            ((ElectronicSportsDetailsActivity) mContext).finishRefresh(1);
                        } else {
                            ((ElectronicSportsDetailsActivity) mContext).finishRefresh(2);
                        }
                    }

                    @Override
                    public void onError(int code, String msg) {
                        toast(msg);
                        if (mPage == 1) {
                            llytNoData.setVisibility(View.VISIBLE);
                            ((ElectronicSportsDetailsActivity) mContext).finishRefresh(1);
                        } else {
                            ((ElectronicSportsDetailsActivity) mContext).finishRefresh(2);
                        }
                    }

                    @Override
                    public void onFailure(Call call, IOException e) {
                        toast(e.toString());
                        if (mPage == 1) {
                            llytNoData.setVisibility(View.VISIBLE);
                            ((ElectronicSportsDetailsActivity) mContext).finishRefresh(1);
                        } else {
                            ((ElectronicSportsDetailsActivity) mContext).finishRefresh(2);
                        }
                    }
                });

    }

}
