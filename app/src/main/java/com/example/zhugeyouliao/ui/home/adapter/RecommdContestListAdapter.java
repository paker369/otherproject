package com.example.zhugeyouliao.ui.home.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhugeyouliao.R;
import com.example.zhugeyouliao.adapter.AFinalRecyclerViewAdapter;
import com.example.zhugeyouliao.adapter.BaseRecyclerViewHolder;
import com.example.zhugeyouliao.api.NetUrlUtils;
import com.example.zhugeyouliao.ui.home.bena.RecommendContestBean;
import com.example.zhugeyouliao.utils.ImageUtils;
import com.example.zhugeyouliao.widget.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author： longyu
 * @Time： 2021/1/14 11:17
 * @description： 首页推荐列表
 */
public class RecommdContestListAdapter extends AFinalRecyclerViewAdapter<RecommendContestBean.RecordsBean> {

    public RecommdContestListAdapter(Activity ctx) {
        super(ctx);
    }

    @Override
    protected BaseRecyclerViewHolder onCreateCustomerViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(m_Inflater.inflate(R.layout.item_recommend_contest_list, parent, false));
    }

    @Override
    protected void onBindCustomerViewHolder(BaseRecyclerViewHolder holder, int position) {
        ((ViewHolder) holder).showData(position);
    }

    public class ViewHolder extends BaseRecyclerViewHolder {

        @BindView(R.id.civ_contest_logo)
        CircleImageView civContestLogo;
        @BindView(R.id.tv_contest_left_name)
        TextView tvContestLeftName;
        @BindView(R.id.tv_contest_time)
        TextView tvContestTime;
        @BindView(R.id.tv_contest_title)
        TextView tvContestTitle;
        @BindView(R.id.tv_contest_name)
        TextView tvContestName;
        @BindView(R.id.tv_contest_state)
        TextView tvContestState;
        @BindView(R.id.tv_contest_integral)
        TextView tvContestIntegral;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void showData(int position) {
            RecommendContestBean.RecordsBean item = getItem(position);
            if (item != null) {
                ImageUtils.getPic(NetUrlUtils.createPhotoUrl(item.getMatchLogo()), civContestLogo, m_Activity);
                tvContestLeftName.setText(item.getMatchShortNameZh());
                tvContestTime.setText(item.getMatchStartTime());
                tvContestTitle.setText(item.getMatchNameZh());
                tvContestName.setText(item.getATeamName() + "  VS  " + item.getBTeamName());
                switch (item.getStageType()){
                    case 1:
                        tvContestState.setText(m_Activity.getResources().getString(R.string.underway));
                        break;
                    case 2:
                        tvContestState.setText(m_Activity.getResources().getString(R.string.finished));
                        break;
                    case 3:
                        tvContestState.setText(m_Activity.getResources().getString(R.string.not_start));
                        break;
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
