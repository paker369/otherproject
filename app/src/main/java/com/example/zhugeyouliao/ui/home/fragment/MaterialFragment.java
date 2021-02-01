package com.example.zhugeyouliao.ui.home.fragment;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zhugeyouliao.R;
import com.example.zhugeyouliao.api.NetUrlUtils;
import com.example.zhugeyouliao.base.LazyBaseFragments;
import com.example.zhugeyouliao.http.BaseCallBack;
import com.example.zhugeyouliao.http.BaseOkHttpClient;
import com.example.zhugeyouliao.ui.home.activity.ElectronicSportsDetailsActivity;
import com.example.zhugeyouliao.ui.home.adapter.MaterialAdapter;
import com.example.zhugeyouliao.ui.home.bena.MatertialBean;
import com.example.zhugeyouliao.ui.home.bena.RecommendHotBean;
import com.example.zhugeyouliao.utils.AnimationUtils;
import com.example.zhugeyouliao.utils.ImageUtils;
import com.example.zhugeyouliao.utils.JSONUtils;
import com.shehuan.niv.NiceImageView;

import java.io.IOException;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * @Author： longyu
 * @Time： 2021/1/15 20:35
 * @description： 电竞详情 诸葛密料
 */
public class MaterialFragment extends LazyBaseFragments {

    @BindView(R.id.tv_recent_record_left)
    TextView tvRecentRecordLeft;
    @BindView(R.id.tv_recent_record_right)
    TextView tvRecentRecordRight;
    @BindView(R.id.tv_recent_record_history)
    TextView tvRecentRecordHistory;
    @BindView(R.id.niv_material_img)
    NiceImageView nivMaterialImg;
    @BindView(R.id.tv_show_field)
    TextView tvShowField;
    @BindView(R.id.tv_victory_defeat)
    TextView tvVictoryDefeat;
    @BindView(R.id.tv_win_rate)
    TextView tvWinRate;
    @BindView(R.id.tv_line)
    TextView tvLine;
    @BindView(R.id.tv_five)
    TextView tvFive;
    @BindView(R.id.tv_ten)
    TextView tvTen;
    @BindView(R.id.tv_competition)
    TextView tvCompetition;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_team_left)
    TextView tvTeamLeft;
    @BindView(R.id.tv_score)
    TextView tvScore;
    @BindView(R.id.tv_team_right)
    TextView tvTeamRight;
    @BindView(R.id.tv_material_name)
    TextView tvmaterialName;
    @BindView(R.id.rlv_layout)
    RecyclerView rlvLayout;
    @BindView(R.id.rlt_contest)
    RelativeLayout rltContest;

    private int mPage = 1;

    private int mSize = 5;
    //团队id
    private int mTeamId;
    private boolean mIsShow = false;

    private MaterialAdapter mMaterialAdapter;

    private boolean mIsVisibleToUser = false;

    /**
     * 加载界面
     *
     * @return
     */
    public static MaterialFragment newInstance(RecommendHotBean.RecordsBean recordsBean) {
        MaterialFragment fragment = new MaterialFragment();
        Bundle args = new Bundle();
        args.putSerializable("recordsBean", (Serializable) recordsBean);
        fragment.setArguments(args);
        return fragment;
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
        return inflater.inflate(R.layout.frag_electronic_sporrts_material, null);
    }

    @Override
    public void initView() {
        initRecycleView();
    }

    /**
     * @date: 2021/1/30 8:44
     * @description 初始化比赛数据
     */
    private void initContestData() {
        RecommendHotBean.RecordsBean recordsBean = getRecordsBean();
        if (recordsBean != null) {
            mTeamId = recordsBean.getATeamId();
            tvRecentRecordLeft.setText(recordsBean.getATeamShortName());
            tvRecentRecordRight.setText(recordsBean.getBTeamShortName());
            ImageUtils.getPic(NetUrlUtils.createPhotoUrl(recordsBean.getATeamLogo()), nivMaterialImg, mContext);
            tvmaterialName.setText(recordsBean.getATeamShortName());
        }
    }

    @Override
    public void initData() {
        initContestData();
        getMaterialData(true);
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
                getMaterialData(false);
            } else {
//            mPage++;
//            getMaterialData(false);
                ((ElectronicSportsDetailsActivity) mContext).finishRefresh(2);
            }
        }
    }


    /**
     * @date: 2021/1/15 22:45
     * @description 初始化列表
     */
    private void initRecycleView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        rlvLayout.setLayoutManager(layoutManager);
        mMaterialAdapter = new MaterialAdapter(mContext);
        rlvLayout.setAdapter(mMaterialAdapter);
    }

    /**
     * @date: 2021/1/15 22:42
     * @description 初始化比赛胜负值
     */
    private void initTextView(String str) {
        SpannableString string = new SpannableString(str);
        string.setSpan(
                new TextAppearanceSpan(mContext, R.style.text_style0),
                0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        string.setSpan(
                new TextAppearanceSpan(mContext, R.style.text_style1),
                2, string.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        tvVictoryDefeat.setText(string, TextView.BufferType.SPANNABLE);
    }

    /**
     * @date: 2021/1/15 22:48
     * @description 初始化近期战绩点击
     */
    private void initTextRecentRecordClick() {
        tvRecentRecordLeft.setBackground(null);
        tvRecentRecordRight.setBackground(null);
        tvRecentRecordHistory.setBackground(null);
    }

    /**
     * @date: 2021/1/30 7:55
     * @description 获取数据
     */
    private void getMaterialData(boolean isLoading) {
        BaseOkHttpClient.Builder builder = BaseOkHttpClient.newBuilder()
                .url(NetUrlUtils.GET_ELECTRONIC_SPORTS_1)
                .addParam("current", mPage)
                .addParam("size", mSize)
                .addParam("searchInt6", mTeamId);
        if (mIsShow) {
            builder.addParam("searchInt7", getRecordsBean().getBTeamId());
        }
        builder.addParam("searchStr1", getRecordsBean().getNamiId())
                .addParam("searchInt5", 0)
                .isLoading(isLoading)
                .form()
                .post()
                .json()
                .build()
                .enqueue(mContext, new BaseCallBack<String>() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        MatertialBean matertialBean = JSONUtils.jsonString2Bean(data, MatertialBean.class);
                        if (matertialBean != null) {
                            int win = 0;
                            List<MatertialBean.RecordsBean> records = matertialBean.getRecords();
                            if (records != null && records.size() > 0) {
                                for (int i = 0; i < records.size(); i++) {
                                    if (records.get(i).getIsWin() == 1) {
                                        win++;
                                    }
                                }
                                initTextView(win + getString(R.string.victory) + (mSize - win) + getString(R.string.defeat));
                                String format = NumberFormat.getInstance().format((float) win / (float) mSize * 100);
                                tvWinRate.setText(format + "%");
                                mMaterialAdapter.refreshList(records);
                            }
                        }
                        ((ElectronicSportsDetailsActivity) mContext).finishRefresh(1);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        toast(msg);
                    }

                    @Override
                    public void onFailure(Call call, IOException e) {
                        toast(e.toString());
                    }
                });
    }


    @OnClick({R.id.tv_recent_record_left, R.id.tv_recent_record_right, R.id.tv_recent_record_history, R.id.tv_five, R.id.tv_ten})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_recent_record_left:
                //比赛 左边
                mIsShow = false;
                initTextRecentRecordClick();
                rltContest.setVisibility(View.VISIBLE);
                mTeamId = getRecordsBean().getATeamId();
                tvmaterialName.setText(getRecordsBean().getATeamShortName());
                ImageUtils.getPic(NetUrlUtils.createPhotoUrl(getRecordsBean().getATeamLogo()), nivMaterialImg, mContext);
                tvRecentRecordLeft.setBackground(getResources().getDrawable(R.drawable.shape_0090ec_10));
                getMaterialData(true);
                break;
            case R.id.tv_recent_record_right:
                //比赛 右边
                mIsShow = false;
                initTextRecentRecordClick();
                rltContest.setVisibility(View.VISIBLE);
                mTeamId = getRecordsBean().getBTeamId();
                tvmaterialName.setText(getRecordsBean().getBTeamShortName());
                ImageUtils.getPic(NetUrlUtils.createPhotoUrl(getRecordsBean().getBTeamLogo()), nivMaterialImg, mContext);
                tvRecentRecordRight.setBackground(getResources().getDrawable(R.drawable.shape_0090ec_10));
                getMaterialData(true);
                break;
            case R.id.tv_recent_record_history:
                //历史交锋
                mIsShow = true;
                initTextRecentRecordClick();
                rltContest.setVisibility(View.GONE);
                mTeamId = getRecordsBean().getATeamId();
                tvRecentRecordHistory.setBackground(getResources().getDrawable(R.drawable.shape_0090ec_10));
                getMaterialData(true);
                break;
            case R.id.tv_five:
                //近5场
                AnimationUtils.setAnimation(tvLine.getLayoutParams().width, 0, 0, 0, 200, tvLine);
                tvShowField.setText(getString(R.string.five));
                mSize = 5;
                getMaterialData(true);
                break;
            case R.id.tv_ten:
                //近10场
                AnimationUtils.setAnimation(0, tvLine.getLayoutParams().width, 0, 0, 200, tvLine);
                tvShowField.setText(getString(R.string.ten));
                mSize = 10;
                getMaterialData(true);
                break;
        }
    }
}
