package com.example.zhugeyouliao.ui.home.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhugeyouliao.R;
import com.example.zhugeyouliao.adapter.AFinalRecyclerViewAdapter;
import com.example.zhugeyouliao.adapter.BaseRecyclerViewHolder;
import com.example.zhugeyouliao.api.NetUrlUtils;
import com.example.zhugeyouliao.ui.home.bena.RecommendHotBean;
import com.example.zhugeyouliao.utils.DateUtils;
import com.example.zhugeyouliao.utils.ImageUtils;
import com.shehuan.niv.NiceImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author： longyu
 * @Time： 2021/1/14 10:21
 * @description： 首页推荐 热门比赛
 */
public class RecommendHotItemAdapter extends AFinalRecyclerViewAdapter<RecommendHotBean.RecordsBean> {

    private int mType;

    public void setType(int mType) {
        this.mType = mType;
    }

    public RecommendHotItemAdapter(Activity ctx) {
        super(ctx);
    }

    @Override
    protected BaseRecyclerViewHolder onCreateCustomerViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(m_Inflater.inflate(R.layout.item_recommend_hot_contest, parent, false));
    }

    @Override
    protected void onBindCustomerViewHolder(BaseRecyclerViewHolder holder, int position) {
        ((ViewHolder) holder).showData(position);
    }


    public class ViewHolder extends BaseRecyclerViewHolder {

        @BindView(R.id.niv_contest_logo)
        NiceImageView nivContestLogo;
        @BindView(R.id.iv_team_logo_left)
        ImageView ivTeamLogoLeft;
        @BindView(R.id.tv_team_logo_left)
        TextView tvTeamLogoLeft;
        @BindView(R.id.tv_contest_time)
        TextView tvContestTime;
        @BindView(R.id.iv_team_logo_right)
        ImageView ivTeamLogoRight;
        @BindView(R.id.tv_team_logo_right)
        TextView tvTeamLogoRight;
        @BindView(R.id.tv_hot_state)
        TextView tvHotState;
        @BindView(R.id.tv_hot_contest_state)
        TextView tvHotContestState;
        @BindView(R.id.view_line)
        View viewLine;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void showData(int position) {
            List<RecommendHotBean.RecordsBean> list = getList();
            if (list != null) {
                if (position == list.size() - 1) {
                    viewLine.setVisibility(View.GONE);
                }
                RecommendHotBean.RecordsBean item = list.get(position);
                if (item != null) {
                    ImageUtils.getPic(NetUrlUtils.createPhotoUrl(item.getHeadUrl()), nivContestLogo, m_Activity);
                    ImageUtils.getPic(NetUrlUtils.createPhotoUrl(item.getATeamLogo()), ivTeamLogoLeft, m_Activity);
                    tvTeamLogoLeft.setText(item.getATeamShortName());
                    ImageUtils.getPic(NetUrlUtils.createPhotoUrl(item.getBTeamLogo()), ivTeamLogoRight, m_Activity);
                    tvTeamLogoRight.setText(item.getBTeamShortName());
                    String startTimeStr = item.getMatchStartTimeStr();
                    String substring = startTimeStr.substring(0, startTimeStr.length() - 3);
                    tvContestTime.setText(substring);
                    String status = "";
                    if (item.getType() == 1) {
                        switch (item.getMatchTypeStatus()) {
                            case 0:
                                status = m_Activity.getResources().getString(R.string.not_start);
                                break;
                            case 1:
                                status = m_Activity.getResources().getString(R.string.not_start);
                                break;
                            case 2:
                                status = m_Activity.getResources().getString(R.string.underway);
                                break;
                            case 3:
                                status = m_Activity.getResources().getString(R.string.underway);
                                break;
                            case 4:
                                status = m_Activity.getResources().getString(R.string.underway);
                                break;
                            case 5:
                                status = m_Activity.getResources().getString(R.string.underway);
                                break;
                            case 6:
                                status = m_Activity.getResources().getString(R.string.underway);
                                break;
                            case 7:
                                status = m_Activity.getResources().getString(R.string.underway);
                                break;
                            case 8:
                                status = m_Activity.getResources().getString(R.string.finished);
                                break;
                            case 9:
                                status = m_Activity.getResources().getString(R.string.postpone);
                                break;
                            case 10:
                                status = m_Activity.getResources().getString(R.string.interrupt);
                                break;
                            case 11:
                                status = m_Activity.getResources().getString(R.string.cut_at_waist);
                                break;
                            case 12:
                                status = m_Activity.getResources().getString(R.string.cancel);
                                break;
                            case 13:
                                status = m_Activity.getResources().getString(R.string.undetermined);
                                break;

                        }
                    } else if (item.getType() == 2) {
                        switch (item.getMatchTypeStatus()) {
                            case 0:
                                status = m_Activity.getResources().getString(R.string.not_start);
                                break;
                            case 1:
                                status = m_Activity.getResources().getString(R.string.not_start);
                                break;
                            case 2:
                                status = m_Activity.getResources().getString(R.string.underway);
                                break;
                            case 3:
                                status = m_Activity.getResources().getString(R.string.underway);
                                break;
                            case 4:
                                status = m_Activity.getResources().getString(R.string.underway);
                                break;
                            case 5:
                                status = m_Activity.getResources().getString(R.string.underway);
                                break;
                            case 6:
                                status = m_Activity.getResources().getString(R.string.underway);
                                break;
                            case 7:
                                status = m_Activity.getResources().getString(R.string.underway);
                                break;
                            case 8:
                                status = m_Activity.getResources().getString(R.string.underway);
                                break;
                            case 9:
                                status = m_Activity.getResources().getString(R.string.underway);
                                break;
                            case 10:
                                status = m_Activity.getResources().getString(R.string.finished);
                                break;
                            case 11:
                                status = m_Activity.getResources().getString(R.string.interrupt);
                                break;
                            case 12:
                                status = m_Activity.getResources().getString(R.string.cancel);
                                break;
                            case 13:
                                status = m_Activity.getResources().getString(R.string.postpone);
                                break;
                            case 14:
                                status = m_Activity.getResources().getString(R.string.cut_at_waist);
                                break;
                            case 15:
                                status = m_Activity.getResources().getString(R.string.undetermined);
                                break;
                        }
                    } else {
                        switch (item.getMatchTypeStatus()) {
                            case 0:
                                status = m_Activity.getResources().getString(R.string.not_start);
                                break;
                            case 1:
                                status = m_Activity.getResources().getString(R.string.underway);
                                break;
                            case 2:
                                status = m_Activity.getResources().getString(R.string.finished);
                                break;
                            case 3:
                                status = m_Activity.getResources().getString(R.string.postpone);
                                break;
                            case 4:
                                status = m_Activity.getResources().getString(R.string.delete);
                                break;
                        }
                    }

                    tvHotContestState.setText(status);
                }
            }


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(v, position, getItem(position));
                    }
                }
            });
        }
    }
}
