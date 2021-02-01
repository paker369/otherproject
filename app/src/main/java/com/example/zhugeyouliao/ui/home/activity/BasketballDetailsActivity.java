package com.example.zhugeyouliao.ui.home.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.zhugeyouliao.R;
import com.example.zhugeyouliao.adapter.MainViewPagerAdapter;
import com.example.zhugeyouliao.api.NetUrlUtils;
import com.example.zhugeyouliao.base.BaseActivity;
import com.example.zhugeyouliao.base.LazyBaseFragments;
import com.example.zhugeyouliao.ui.home.bena.RecommendHotBean;
import com.example.zhugeyouliao.ui.home.fragment.BallMaterialFragment;
import com.example.zhugeyouliao.ui.home.fragment.BallVideoFragment;
import com.example.zhugeyouliao.ui.home.fragment.MatchesFragment;
import com.example.zhugeyouliao.utils.ImageUtils;
import com.example.zhugeyouliao.utils.StatusBarUtils;
import com.example.zhugeyouliao.widget.CircleImageView;
import com.example.zhugeyouliao.widget.NoScrollViewPager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Author： longyu
 * @Time： 2021/1/17 11:05
 * @description： 篮球详情
 */
public class BasketballDetailsActivity extends BaseActivity {

    @BindView(R.id.status_bar_view)
    View statusBarView;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.center_title)
    TextView centerTitle;
    @BindView(R.id.niv_cart_left_img)
    CircleImageView nivCartLeftImg;
    @BindView(R.id.tv_card_left)
    TextView tvCardLeft;
    @BindView(R.id.tv_cart_contest_score)
    TextView tvCartContestScore;
    @BindView(R.id.tv_cart_state)
    TextView tvCartState;
    @BindView(R.id.tv_cart_contest_name)
    TextView tvCartContestName;
    @BindView(R.id.niv_cart_right_img)
    CircleImageView nivCartRightImg;
    @BindView(R.id.tv_card_right)
    TextView tvCardRight;
    @BindView(R.id.tv_detials_material)
    TextView tvDetialsMaterial;
    @BindView(R.id.tv_detials_text)
    TextView tvDetialsText;
    @BindView(R.id.tv_detials_matches)
    TextView tvDetialsMatches;
    @BindView(R.id.tv_detials_video)
    TextView tvDetialsVideo;
    @BindView(R.id.nvp_layout)
    NoScrollViewPager nvpLayout;
    @BindView(R.id.srl_layout)
    SmartRefreshLayout srlLayout;
    @BindView(R.id.tv_issue)
    TextView tvIssue;

    private BallMaterialFragment mMaterialFragment;

    private MatchesFragment mMatchesFragment;

    private BallVideoFragment mBallVideoFragment;

    /**
     * 获取数据
     *
     * @return
     */
    private RecommendHotBean.RecordsBean getFootBallContestData() {
        return (RecommendHotBean.RecordsBean) getIntent().getSerializableExtra("bean");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_contest_detials;
    }

    @Override
    protected void initData() {
        statusBarView.getLayoutParams().height = StatusBarUtils.getStatusBarHeight(mContext);
        initTitle(getString(R.string.basketball_details));
        tvDetialsVideo.setVisibility(View.VISIBLE);
        initRefreshLayout();
        initBeanData();
        initFragment();
    }

    /**
     * 初始化数据
     */
    private void initBeanData() {
        RecommendHotBean.RecordsBean contestData = getFootBallContestData();
        if (contestData != null) {
            ImageUtils.getPic(NetUrlUtils.createPhotoUrl(contestData.getATeamLogo()), nivCartLeftImg, mContext);
            ImageUtils.getPic(NetUrlUtils.createPhotoUrl(contestData.getBTeamLogo()), nivCartRightImg, mContext);
            tvCardLeft.setText(contestData.getATeamShortName());
            tvCardRight.setText(contestData.getBTeamShortName());
            tvCartContestScore.setText(contestData.getMatchScore());
            tvCartContestName.setText(contestData.getMatchShortName() + "  " + contestData.getMatchStartTimeStr());
            String status = "";
            switch (contestData.getMatchTypeStatus()) {
                case 0:
                    status = getResources().getString(R.string.not_start);
                    break;
                case 1:
                    status = getResources().getString(R.string.not_start);
                    break;
                case 2:
                    status = getResources().getString(R.string.underway);
                    break;
                case 3:
                    status = getResources().getString(R.string.underway);
                    break;
                case 4:
                    status = getResources().getString(R.string.underway);
                    break;
                case 5:
                    status = getResources().getString(R.string.underway);
                    break;
                case 6:
                    status = getResources().getString(R.string.underway);
                    break;
                case 7:
                    status = getResources().getString(R.string.underway);
                    break;
                case 8:
                    status = getResources().getString(R.string.underway);
                    break;
                case 9:
                    status = getResources().getString(R.string.underway);
                    break;
                case 10:
                    status = getResources().getString(R.string.finished);
                    break;
                case 11:
                    status = getResources().getString(R.string.interrupt);
                    break;
                case 12:
                    status = getResources().getString(R.string.cancel);
                    break;
                case 13:
                    status = getResources().getString(R.string.postpone);
                    break;
                case 14:
                    status = getResources().getString(R.string.cut_at_waist);
                    break;
                case 15:
                    status = getResources().getString(R.string.undetermined);
                    break;
            }
            tvCartState.setText(status);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    /**
     * @date: 2021/1/17 11:09
     * @description 初始化刷新
     */
    private void initRefreshLayout() {
        srlLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mMaterialFragment.onRefresh(true);
                mMatchesFragment.onRefresh(true);
            }
        });
        srlLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mMaterialFragment.onRefresh(false);
                mMatchesFragment.onRefresh(false);
            }
        });
    }

    /**
     * 关闭刷新
     *
     * @param type
     */
    public void finishRefresh(int type) {
        if (type == 1) {
            srlLayout.finishRefresh();
        } else if (type == 2) {
            srlLayout.finishLoadMore();
        } else {
            srlLayout.finishLoadMoreWithNoMoreData();
        }
    }

    /**
     * @date: 2021/1/16 9:58
     * @description 初始化fragment
     */
    private void initFragment() {
        List<LazyBaseFragments> fragmentsList = new ArrayList<>();
        mMaterialFragment = BallMaterialFragment.newInstance(getFootBallContestData(), 2);
        mMatchesFragment = MatchesFragment.newInstance(getFootBallContestData());
        mBallVideoFragment = BallVideoFragment.newInstance(getFootBallContestData());
        fragmentsList.add(mMaterialFragment);
        fragmentsList.add(mMatchesFragment);
        fragmentsList.add(mBallVideoFragment);
        nvpLayout.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager(), fragmentsList));
    }

    /**
     * @date: 2021/1/16 9:59
     * @description 初始化点击背景
     */
    private void initClcikBg() {
        tvDetialsMatches.setBackground(null);
        tvDetialsMaterial.setBackground(null);
        tvDetialsVideo.setBackground(null);
    }

    @OnClick({R.id.tv_detials_material, R.id.tv_detials_matches, R.id.tv_detials_video, R.id.tv_issue})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_detials_material:
                //诸葛密料
                nvpLayout.setCurrentItem(0);
                initClcikBg();
                tvDetialsMaterial.setBackground(getResources().getDrawable(R.drawable.shape_white_10_top));
                break;
            case R.id.tv_detials_matches:
                //赛事预测
                nvpLayout.setCurrentItem(1);
                initClcikBg();
                tvDetialsMatches.setBackground(getResources().getDrawable(R.drawable.shape_white_10_top));
                break;
            case R.id.tv_detials_video:
                //比赛视频
                nvpLayout.setCurrentItem(2);
                initClcikBg();
                tvDetialsVideo.setBackground(getResources().getDrawable(R.drawable.shape_white_10_top));
                break;
            case R.id.tv_issue:
                //发布
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMaterialFragment = null;
        mBallVideoFragment = null;
        mMatchesFragment = null;
    }
}
