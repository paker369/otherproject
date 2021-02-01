package com.example.zhugeyouliao.ui.home.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
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
import com.example.zhugeyouliao.ui.home.adapter.TextLiveAdapter;
import com.example.zhugeyouliao.ui.home.bena.BallTextLiveBean;
import com.example.zhugeyouliao.ui.home.bena.RecommendHotBean;
import com.example.zhugeyouliao.utils.JSONUtils;
import com.example.zhugeyouliao.utils.LogUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;

/**
 * @Author： longyu
 * @Time： 2021/1/19 10:28
 * @description： 文字直播
 */
public class TextLiveFragment extends LazyBaseFragments {

    @BindView(R.id.rlv_layout)
    RecyclerView rlvLayout;

    private TextLiveAdapter mTextLiveAdapter;

    private CountDownTimer mCountDownTimer;

    /**
     * 加载界面
     *
     * @param footBallContestData
     * @return
     */
    public static TextLiveFragment newInstance(RecommendHotBean.RecordsBean footBallContestData) {
        TextLiveFragment fragment = new TextLiveFragment();
        Bundle args = new Bundle();
        args.putSerializable("footBallContestData", (Serializable) footBallContestData);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * @date: 2021/1/17 19:41
     * @description 获取数据
     */
    private RecommendHotBean.RecordsBean getContestBeans() {
        return (RecommendHotBean.RecordsBean) getArguments().getSerializable("footBallContestData");
    }


    @Override
    public View bindLayout(LayoutInflater inflater) {
        return inflater.inflate(R.layout.frag_text_live, null);
    }

    @Override
    public void initView() {
        initRecyclerView();
    }

    /**
     * @date: 2021/1/19 11:29
     * @description 初始化列表
     */
    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        rlvLayout.setLayoutManager(layoutManager);
        mTextLiveAdapter = new TextLiveAdapter(mContext);
        rlvLayout.setAdapter(mTextLiveAdapter);
    }

    @Override
    public void initData() {
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getContestWordLiveData(true);
            countDown();
        } else {
            if (mCountDownTimer != null)
                mCountDownTimer.cancel();
        }
    }

    /**
     * 计时器
     */
    private void countDown() {
        mCountDownTimer = new CountDownTimer(5 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                getContestWordLiveData(false);
                countDown();
            }
        };
        mCountDownTimer.start();
    }

    /**
     * @date: 2021/1/29 7:51
     * @description 获取本场比赛的文字直播
     */
    private void getContestWordLiveData(boolean isLoading) {
        if (getContestBeans() == null) {
            return;
        }
        BaseOkHttpClient.newBuilder()
                .url(NetUrlUtils.GET_FOOTBALL_WORD_LIVE)
                .addParam("searchStr1", getContestBeans().getNamiId())
                .isLoading(isLoading)
                .form()
                .post()
                .json()
                .build()
                .enqueue(mContext, new BaseCallBack<String>() {
                    @Override
                    public void onSuccess(String data, String msg) {
                        List<BallTextLiveBean> textLiveBeans = JSONUtils.jsonString2Beans(data, BallTextLiveBean.class);
                        if (textLiveBeans != null && textLiveBeans.size() > 0) {
                            if (mTextLiveAdapter == null) {
                                return;
                            }
                            //倒序
                            Collections.reverse(textLiveBeans);
                            mTextLiveAdapter.refreshList(textLiveBeans);
                        }
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


}
