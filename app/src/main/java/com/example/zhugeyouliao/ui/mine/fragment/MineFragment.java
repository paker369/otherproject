package com.example.zhugeyouliao.ui.mine.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhugeyouliao.MyApplication;
import com.example.zhugeyouliao.R;
import com.example.zhugeyouliao.base.LazyBaseFragments;
import com.example.zhugeyouliao.ui.mine.activity.EditPersonalInfoActivity;
import com.example.zhugeyouliao.ui.mine.activity.MyAttentionActivity;
import com.example.zhugeyouliao.ui.mine.activity.MyCollectActivity;
import com.example.zhugeyouliao.ui.mine.activity.MyParticipationGambitActivity;
import com.example.zhugeyouliao.ui.mine.activity.MyPublishGambitActivity;
import com.example.zhugeyouliao.ui.mine.activity.MyPulishAnalyseActivity;
import com.example.zhugeyouliao.utils.LoginCheckUtils;
import com.example.zhugeyouliao.widget.CircleImageView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author： longyu
 * @Time： 2021/1/13 16:13
 * @description：
 */
public class MineFragment extends LazyBaseFragments {
    @BindView(R.id.ic_mine_header)
    CircleImageView icMineHeader;
    @BindView(R.id.tv_mine_name)
    TextView tvMineName;
    @BindView(R.id.iv_mine_set)
    ImageView ivMineSet;
    @BindView(R.id.iv_mine_send)
    ImageView ivMineSend;
    @BindView(R.id.iv_mine_exit)
    ImageView ivMineExit;
    @BindView(R.id.tv_mine_like)
    TextView tvMineLike;
    @BindView(R.id.tv_mine_message)
    TextView tvMineMessage;
    @BindView(R.id.tv_mine_integral)
    TextView tvMineIntegral;
    @BindView(R.id.llt_conter)
    LinearLayout lltConter;
    @BindView(R.id.tv_mine_collet)
    TextView tvMineCollet;
    @BindView(R.id.tv_mine_send)
    TextView tvMineSend;
    @BindView(R.id.tv_mine_gambit)
    TextView tvMineGambit;
    @BindView(R.id.tv_mine_attention)
    TextView tvMineAttention;

    @Override
    public View bindLayout(LayoutInflater inflater) {
        return inflater.inflate(R.layout.frag_mine, null);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.ic_mine_header, R.id.iv_mine_set, R.id.iv_mine_send, R.id.iv_mine_exit,
            R.id.tv_mine_like, R.id.tv_mine_message, R.id.tv_mine_integral, R.id.tv_mine_collet,
            R.id.tv_mine_participate_gambit, R.id.tv_mine_send, R.id.tv_mine_gambit, R.id.tv_mine_attention})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ic_mine_header:
                //头像
//                if (!LoginCheckUtils.checkLoginShowDialog(mContext)) {
//                    return;
//                }
                MyApplication.openActivity(mContext, EditPersonalInfoActivity.class);
                break;
            case R.id.iv_mine_set:
                //设置
                break;
            case R.id.iv_mine_send:
                //发布
                if (!LoginCheckUtils.checkLoginShowDialog(mContext)) {
                    return;
                }
                break;
            case R.id.iv_mine_exit:
                //退出
                break;
            case R.id.tv_mine_like:
                //点赞
                if (!LoginCheckUtils.checkLoginShowDialog(mContext)) {
                    return;
                }
                break;
            case R.id.tv_mine_message:
                //消息
                if (!LoginCheckUtils.checkLoginShowDialog(mContext)) {
                    return;
                }
                break;
            case R.id.tv_mine_integral:
                //我的积分
                if (!LoginCheckUtils.checkLoginShowDialog(mContext)) {
                    return;
                }
                break;
            case R.id.tv_mine_collet:
                //我的收藏
//                if (!LoginCheckUtils.checkLoginShowDialog(mContext)) {
//                    return;
//                }
                MyApplication.openActivity(mContext, MyCollectActivity.class);
                break;
            case R.id.tv_mine_send:
                //我发布的分析
//                if (!LoginCheckUtils.checkLoginShowDialog(mContext)) {
//                    return;
//                }
                MyApplication.openActivity(mContext, MyPulishAnalyseActivity.class);
                break;
            case R.id.tv_mine_participate_gambit:
                //我发布的话题
//                if (!LoginCheckUtils.checkLoginShowDialog(mContext)) {
//                    return;
//                }
                MyApplication.openActivity(mContext, MyParticipationGambitActivity.class);
                break;
            case R.id.tv_mine_gambit:
                //我参与的话题
//                if (!LoginCheckUtils.checkLoginShowDialog(mContext)) {
//                    return;
//                }
                MyApplication.openActivity(mContext, MyPublishGambitActivity.class);
                break;
            case R.id.tv_mine_attention:
                //我关注的大神
                if (!LoginCheckUtils.checkLoginShowDialog(mContext)) {
                    return;
                }
                MyApplication.openActivity(mContext, MyAttentionActivity.class);
                break;
        }
    }
}
