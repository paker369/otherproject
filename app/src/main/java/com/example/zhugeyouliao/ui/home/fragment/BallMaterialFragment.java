package com.example.zhugeyouliao.ui.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zhugeyouliao.R;
import com.example.zhugeyouliao.adapter.AFinalRecyclerViewAdapter;
import com.example.zhugeyouliao.api.NetUrlUtils;
import com.example.zhugeyouliao.base.LazyBaseFragments;
import com.example.zhugeyouliao.http.BaseCallBack;
import com.example.zhugeyouliao.http.BaseOkHttpClient;
import com.example.zhugeyouliao.ui.home.activity.BasketballDetailsActivity;
import com.example.zhugeyouliao.ui.home.activity.FootballDetailsActivity;
import com.example.zhugeyouliao.ui.home.adapter.BallContestLeagueAdapter;
import com.example.zhugeyouliao.ui.home.adapter.BallContestMaterialListAdapter;
import com.example.zhugeyouliao.ui.home.bena.BallContestMaterialListBean;
import com.example.zhugeyouliao.ui.home.bena.RecommendHotBean;
import com.example.zhugeyouliao.utils.FlowLayoutManager;
import com.example.zhugeyouliao.utils.JSONUtils;
import com.example.zhugeyouliao.widget.CustomRecyclerView;

import java.io.IOException;
import java.io.Serializable;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * @Author： longyu
 * @Time： 2021/1/17 11:39
 * @description： 球类 诸葛密料
 */
public class BallMaterialFragment extends LazyBaseFragments {
    @BindView(R.id.tv_home_team)
    TextView tvHomeTeam;
    @BindView(R.id.tv_guest_team)
    TextView tvGuestTeam;
    @BindView(R.id.tv_all_count)
    TextView tvAllCount;
    @BindView(R.id.tv_home_count)
    TextView tvHomeCount;
    @BindView(R.id.tv_guest_count)
    TextView tvGuestCount;
    @BindView(R.id.rlv_league)
    CustomRecyclerView rlvLeague;
    @BindView(R.id.rlv_layout)
    RecyclerView rlvLayout;
    @BindView(R.id.llyt_no_data)
    LinearLayout llytNoData;

    private BallContestLeagueAdapter mBallContestLeagueAdapter;
    private BallContestMaterialListAdapter mBallContestMaterialListAdapter;

    private int mPage = 1;
    //团队id
    private int mTeamId;
    //0： 全部 1：主场 2：客场
    private int mCourtType = 0;
    //联盟名称
    private String mLeagueName = "";

    /**
     * 加载界面
     *
     * @return
     */
    public static BallMaterialFragment newInstance(RecommendHotBean.RecordsBean recordsBean, int type) {
        BallMaterialFragment fragment = new BallMaterialFragment();
        Bundle args = new Bundle();
        args.putSerializable("recordsBean", (Serializable) recordsBean);
        args.getInt("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * 获取类型
     *
     * @return
     */
    private int getBallType() {
        return getArguments().getInt("type", 1);
    }

    /**
     * @date: 2021/1/17 19:41
     * @description 获取数据
     */
    private RecommendHotBean.RecordsBean getRecordsBean() {
        return (RecommendHotBean.RecordsBean) getArguments().getSerializable("recordsBean");
    }

    @Override
    public View bindLayout(LayoutInflater inflater) {
        return inflater.inflate(R.layout.frag_ball_material, null);
    }

    @Override
    public void initView() {
        initLeague();
        initListView();
    }

    @Override
    public void initData() {
        mTeamId = getRecordsBean().getATeamId();
        getTeamMatchHistoryData(true);
    }

    /**
     * @date: 2021/1/17 12:14
     * @description 初始化联盟
     */
    private void initLeague() {
        FlowLayoutManager flowManager = new FlowLayoutManager(mContext);
        rlvLeague.setLayoutManager(flowManager);
        mBallContestLeagueAdapter = new BallContestLeagueAdapter(mContext);
        rlvLeague.setAdapter(mBallContestLeagueAdapter);
        mBallContestLeagueAdapter.setOnItemClickListener(new AFinalRecyclerViewAdapter.OnItemClickListener<String>() {
            @Override
            public void onItemClick(View view, int position, String model) {
                mLeagueName = model;
                mBallContestLeagueAdapter.setLeagueposition(position);
                mBallContestLeagueAdapter.notifyDataSetChanged();
                getTeamMatchHistoryData(true);
            }

            @Override
            public void onItemLongClick(View view, int position, String model) {

            }
        });
    }

    /**
     * @date: 2021/1/17 12:15
     * @description 初始化列表
     */
    private void initListView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        rlvLayout.setLayoutManager(layoutManager);
        mBallContestMaterialListAdapter = new BallContestMaterialListAdapter(mContext);
        rlvLayout.setAdapter(mBallContestMaterialListAdapter);
    }

    /**
     *
     */
    private void getTeamMatchHistoryData(boolean isLoading) {
        BaseOkHttpClient.Builder builder = BaseOkHttpClient.newBuilder();
        if (getBallType() == 1) {
            builder.url(NetUrlUtils.GET_FOOTBALL_TEAM_MATCH_HISTORY);
        } else if (getBallType() == 2) {
            builder.url(NetUrlUtils.GET_BASEKETBALL_TEAM_MATCH_HISTORY);
        }
        builder.addParam("current", mPage)
                .addParam("size", 10)
                .addParam("searchInt1", mTeamId)
                .addParam("searchInt2", mCourtType)
                .addParam("searchStr1", mLeagueName)
                .addParam("searchInt3", getRecordsBean().getNamiId())
                .isLoading(isLoading)
                .post()
                .json()
                .form()
                .build()
                .enqueue(mContext, new BaseCallBack<String>() {

                    @Override
                    public void onSuccess(String s, String msg) {
                        BallContestMaterialListBean listBean = JSONUtils.jsonString2Bean(s, BallContestMaterialListBean.class);
                        if (listBean != null) {
                            listBean.getMatchNameList().add(0, getString(R.string.all));
                            refreshListData(listBean);
                            if (mPage == 1) {
                                if (getBallType() == 1) {
                                    ((FootballDetailsActivity) mContext).finishRefresh(1);
                                } else if (getBallType() == 2) {
                                    ((BasketballDetailsActivity) mContext).finishRefresh(1);
                                }
                            } else {
                                if (listBean.getWebBallMatchPage().getRecords().size() >= 10) {
                                    if (getBallType() == 1) {
                                        ((FootballDetailsActivity) mContext).finishRefresh(2);
                                    } else if (getBallType() == 2) {
                                        ((BasketballDetailsActivity) mContext).finishRefresh(2);
                                    }
                                } else {
                                    if (getBallType() == 1) {
                                        ((FootballDetailsActivity) mContext).finishRefresh(3);
                                    } else if (getBallType() == 2) {
                                        ((BasketballDetailsActivity) mContext).finishRefresh(3);
                                    }
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(int code, String msg) {
                        toast(msg);
                        if (mPage == 1) {
                            llytNoData.setVisibility(View.VISIBLE);
                            if (getBallType() == 1) {
                                ((FootballDetailsActivity) mContext).finishRefresh(1);
                            } else if (getBallType() == 2) {
                                ((BasketballDetailsActivity) mContext).finishRefresh(1);
                            }
                        } else {
                            if (getBallType() == 1) {
                                ((FootballDetailsActivity) mContext).finishRefresh(2);
                            } else if (getBallType() == 2) {
                                ((BasketballDetailsActivity) mContext).finishRefresh(2);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call call, IOException e) {
                        toast(e.toString());
                        if (mPage == 1) {
                            llytNoData.setVisibility(View.VISIBLE);
                            if (getBallType() == 1) {
                                ((FootballDetailsActivity) mContext).finishRefresh(1);
                            } else if (getBallType() == 2) {
                                ((BasketballDetailsActivity) mContext).finishRefresh(1);
                            }
                        } else {
                            if (getBallType() == 1) {
                                ((FootballDetailsActivity) mContext).finishRefresh(2);
                            } else if (getBallType() == 2) {
                                ((BasketballDetailsActivity) mContext).finishRefresh(2);
                            }
                        }
                    }
                });
    }

    /**
     * 刷新数据
     *
     * @param listBean
     */
    private void refreshListData(BallContestMaterialListBean listBean) {
        mBallContestLeagueAdapter.refreshList(listBean.getMatchNameList());
        mBallContestMaterialListAdapter.refreshList(listBean.getWebBallMatchPage().getRecords());
    }

    /**
     * 下拉刷新 上拉加载
     */
    public void onRefresh(boolean isTop) {
        if (isTop) {
            mPage = 1;
            getTeamMatchHistoryData(false);
        } else {
            mPage++;
            getTeamMatchHistoryData(false);
        }
    }


    @OnClick({R.id.tv_home_team, R.id.tv_guest_team, R.id.tv_all_count, R.id.tv_home_count, R.id.tv_guest_count})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_home_team:
                //主队
                initTeamBg();
                tvHomeTeam.setBackground(getResources().getDrawable(R.drawable.shape_0090ec_15));
                mTeamId = getRecordsBean().getATeamId();
                getTeamMatchHistoryData(true);
                break;
            case R.id.tv_guest_team:
                //客队
                initTeamBg();
                tvGuestTeam.setBackground(getResources().getDrawable(R.drawable.shape_0090ec_15));
                mTeamId = getRecordsBean().getBTeamId();
                getTeamMatchHistoryData(true);
                break;
            case R.id.tv_all_count:
                //全部
                initcountBg();
                tvAllCount.setBackground(getResources().getDrawable(R.drawable.shape_0090ec_15));
                mCourtType = 0;
                getTeamMatchHistoryData(true);
                break;
            case R.id.tv_home_count:
                //主场
                initcountBg();
                tvHomeCount.setBackground(getResources().getDrawable(R.drawable.shape_0090ec_15));
                mCourtType = 1;
                getTeamMatchHistoryData(true);
                break;
            case R.id.tv_guest_count:
                //客场
                initcountBg();
                tvGuestCount.setBackground(getResources().getDrawable(R.drawable.shape_0090ec_15));
                mCourtType = 2;
                getTeamMatchHistoryData(true);
                break;
        }
    }

    /**
     * @date: 2021/1/17 12:32
     * @description 初始化队背景
     */
    private void initTeamBg() {
        tvHomeTeam.setBackground(getResources().getDrawable(R.drawable.shape_16000000_15));
        tvGuestTeam.setBackground(getResources().getDrawable(R.drawable.shape_16000000_15));
    }

    /**
     * @date: 2021/1/17 12:32
     * @description 初始化场背景
     */
    private void initcountBg() {
        tvAllCount.setBackground(getResources().getDrawable(R.drawable.shape_16000000_15));
        tvHomeCount.setBackground(getResources().getDrawable(R.drawable.shape_16000000_15));
        tvGuestCount.setBackground(getResources().getDrawable(R.drawable.shape_16000000_15));
    }
}
