package com.example.zhugeyouliao.ui.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zhugeyouliao.R;
import com.example.zhugeyouliao.adapter.AFinalRecyclerViewAdapter;
import com.example.zhugeyouliao.api.NetUrlUtils;
import com.example.zhugeyouliao.base.LazyBaseFragments;
import com.example.zhugeyouliao.http.BaseCallBack;
import com.example.zhugeyouliao.http.BaseOkHttpClient;
import com.example.zhugeyouliao.ui.home.adapter.BallVideoAdapter;
import com.example.zhugeyouliao.ui.home.bena.BallVideoBean;
import com.example.zhugeyouliao.ui.home.bena.RecommendHotBean;

import java.io.IOException;
import java.io.Serializable;

import butterknife.BindView;
import okhttp3.Call;

/**
 * @Author： longyu
 * @Time： 2021/1/17 21:32
 * @description： 球类 比赛视频
 */
public class BallVideoFragment extends LazyBaseFragments implements AFinalRecyclerViewAdapter.OnItemClickListener<BallVideoBean> {
    @BindView(R.id.rlv_layout)
    RecyclerView rlvLayout;
    @BindView(R.id.llyt_no_data)
    LinearLayout llytNoData;

    private BallVideoAdapter mBallVideoAdapter;

    /**
     * 加载界面
     *
     * @param footBallContestData
     * @return
     */
    public static BallVideoFragment newInstance(RecommendHotBean.RecordsBean footBallContestData) {
        BallVideoFragment fragment = new BallVideoFragment();
        Bundle args = new Bundle();
        args.putSerializable("footBallContestData", (Serializable) footBallContestData);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * 获取数据
     *
     * @return
     */
    private RecommendHotBean.RecordsBean getContestBeans() {
        return (RecommendHotBean.RecordsBean) getArguments().getSerializable("footBallContestData");
    }

    @Override
    public View bindLayout(LayoutInflater inflater) {
        return inflater.inflate(R.layout.layout_recycleview, null);
    }

    @Override
    public void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rlvLayout.setLayoutManager(layoutManager);
        mBallVideoAdapter = new BallVideoAdapter(mContext);
        rlvLayout.setAdapter(mBallVideoAdapter);
        mBallVideoAdapter.setOnItemClickListener(this);
    }

    @Override
    public void initData() {
        getContestLiveData();
    }

    /**
     * @date: 2021/1/29 16:02
     * @description 比赛视频
     */
    private void getContestLiveData() {
        BaseOkHttpClient.newBuilder()
                .url(NetUrlUtils.GET_URL_LIVE)
                .addParam("searchStr1", getContestBeans().getMatchId())
                .form()
                .post()
                .json()
                .build()
                .enqueue(mContext, new BaseCallBack<String>() {
                    @Override
                    public void onSuccess(String data, String msg) {

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

    @Override
    public void onItemClick(View view, int position, BallVideoBean model) {
        //跳转直播
    }

    @Override
    public void onItemLongClick(View view, int position, BallVideoBean model) {

    }
}
