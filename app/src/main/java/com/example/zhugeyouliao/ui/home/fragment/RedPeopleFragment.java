package com.example.zhugeyouliao.ui.home.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zhugeyouliao.R;
import com.example.zhugeyouliao.api.NetUrlUtils;
import com.example.zhugeyouliao.base.LazyBaseFragments;
import com.example.zhugeyouliao.http.BaseCallBack;
import com.example.zhugeyouliao.http.BaseOkHttpClient;
import com.example.zhugeyouliao.ui.home.adapter.RedPeopleAdapter;
import com.example.zhugeyouliao.ui.home.bena.RedPeopleBean;
import com.example.zhugeyouliao.ui.home.bena.RedPeopleJsonBean;
import com.example.zhugeyouliao.utils.AnimationUtils;
import com.example.zhugeyouliao.utils.JSONUtils;
import com.example.zhugeyouliao.utils.ListUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * @Author： longyu
 * @Time： 2021/1/13 20:03
 * @description： 首页红人
 */
public class RedPeopleFragment extends LazyBaseFragments {
    @BindView(R.id.tv_line)
    TextView tvLine;
    @BindView(R.id.tv_fans_notice)
    TextView tvFansNotice;
    @BindView(R.id.tv_win_rate_notice)
    TextView tvWinRateNotice;
    @BindView(R.id.rlv_layout)
    RecyclerView rlvLayout;
    @BindView(R.id.srl_layout)
    SmartRefreshLayout srlLayout;
    @BindView(R.id.llyt_no_data)
    LinearLayout llytNoData;

    private RedPeopleAdapter mRedPeopleAdapter;

    private int mPage = 1;

    private int mRedType = 0;

    private List<RedPeopleBean> mPropleBeans = new ArrayList<>();
    private RedPeopleBean mRedPeopleBean = new RedPeopleBean();

    @Override
    public View bindLayout(LayoutInflater inflater) {
        return inflater.inflate(R.layout.frag_red_people, null);
    }

    @Override
    public void initView() {
        initRefreshLayout();
        initRecyclerView();
    }

    @Override
    public void initData() {
        getData(false);
    }

    /**
     * @date: 2021/1/14 15:47
     * @description 初始化刷新
     */
    private void initRefreshLayout() {
        srlLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPage = 1;
                mPropleBeans.clear();
                getData(false);
            }
        });
        srlLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPage++;
                getData(false);
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
        mRedPeopleAdapter = new RedPeopleAdapter(mContext);
        rlvLayout.setAdapter(mRedPeopleAdapter);
    }

    /**
     * @date: 2021/1/14 16:18
     * @description 获取数据
     */
    private void getData(boolean isLoading) {
        BaseOkHttpClient.newBuilder()
                .url(NetUrlUtils.GET_CREATE_GOD_LIST)
                .addParam("current", mPage)
                .addParam("size", 10)
                .addParam("searchInt1", "502")
                .addParam("searchInt2", 2)
                .addParam("searchStr1", "")
                .isLoading(isLoading)
                .form()
                .post()
                .json()
                .build()
                .enqueue(mContext, new BaseCallBack<String>() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        RedPeopleJsonBean jsonBean = JSONUtils.jsonString2Bean(data, RedPeopleJsonBean.class);
                        if (jsonBean != null) {
                            if (mPage == 1) {
                                if (jsonBean.getRecords() != null && jsonBean.getRecords().size() > 0) {
                                    if (mRedType == 1) {
                                        String[] sortNameArr = {"hitRate"};
                                        boolean[] isAscArr = {false};
                                        ListUtils.sort(jsonBean.getRecords(), sortNameArr, isAscArr);
                                    }
                                    llytNoData.setVisibility(View.GONE);
                                    mRedPeopleBean.setFirstData(jsonBean.getRecords().get(0));
                                    if (jsonBean.getRecords().size() > 1) {
                                        mRedPeopleBean.setSecondData(jsonBean.getRecords().get(1));
                                    }
                                    if (jsonBean.getRecords().size() > 2) {
                                        mRedPeopleBean.setThirdlyData(jsonBean.getRecords().get(2));
                                    }
                                    jsonBean.getRecords().remove(0);
                                    jsonBean.getRecords().remove(0);
                                    jsonBean.getRecords().remove(0);
                                    mRedPeopleBean.setListBeans(jsonBean.getRecords());
                                    mPropleBeans.add(mRedPeopleBean);
                                    mPropleBeans.add(mRedPeopleBean);
                                    mRedPeopleAdapter.refreshList(mPropleBeans);
                                    srlLayout.finishRefresh();
                                } else {
                                    llytNoData.setVisibility(View.VISIBLE);
                                    srlLayout.finishRefresh();
                                }
                            } else {
                                if (jsonBean.getRecords().size() >= 10) {
                                    srlLayout.finishLoadMore();
                                } else {
                                    srlLayout.finishLoadMoreWithNoMoreData();
                                }
                                mPropleBeans.get(1).getListBeans().addAll(jsonBean.getRecords());
                                mRedPeopleAdapter.notifyDataSetChanged();
                            }
                        } else {
                            if (mPage == 1) {
                                llytNoData.setVisibility(View.VISIBLE);
                                srlLayout.finishRefresh();
                            } else {
                                srlLayout.finishLoadMore();
                            }
                        }
                    }

                    @Override
                    public void onError(int code, String msg) {
                        toast(msg);
                        if (mPage == 1) {
                            llytNoData.setVisibility(View.VISIBLE);
                            srlLayout.finishRefresh();
                        } else {
                            srlLayout.finishLoadMore();
                        }
                    }

                    @Override
                    public void onFailure(Call call, IOException e) {
                        toast(e.toString());
                        if (mPage == 1) {
                            llytNoData.setVisibility(View.VISIBLE);
                            srlLayout.finishRefresh();
                        } else {
                            srlLayout.finishLoadMore();
                        }
                    }
                });
    }

    @OnClick({R.id.tv_fans_notice, R.id.tv_win_rate_notice})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_fans_notice:
                //粉丝榜
                AnimationUtils.setAnimation(tvLine.getLayoutParams().width, 0, 0, 0, 200, tvLine);
                mRedPeopleAdapter.setmType(0);
                mRedType = 0;
                mPage = 1;
                getData(true);
                break;
            case R.id.tv_win_rate_notice:
                //胜率榜
                AnimationUtils.setAnimation(0, tvLine.getLayoutParams().width, 0, 0, 200, tvLine);
                mRedPeopleAdapter.setmType(1);
                mRedType = 1;
                mPage = 1;
                getData(true);
                break;
        }
    }

}
