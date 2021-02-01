package com.example.zhugeyouliao.ui.home.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zhugeyouliao.R;
import com.example.zhugeyouliao.adapter.AFinalRecyclerViewAdapter;
import com.example.zhugeyouliao.adapter.BaseRecyclerViewHolder;
import com.example.zhugeyouliao.ui.home.bena.MatchesBean;
import com.example.zhugeyouliao.widget.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author： longyu
 * @Time： 2021/1/16 21:58
 * @description： 电竞详情 赛事预测
 */
public class MatchesAdapter extends AFinalRecyclerViewAdapter<MatchesBean> {

    public MatchesAdapter(Activity ctx) {
        super(ctx);
    }

    @Override
    protected BaseRecyclerViewHolder onCreateCustomerViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(m_Inflater.inflate(R.layout.item_matches, parent, false));
    }

    @Override
    protected void onBindCustomerViewHolder(BaseRecyclerViewHolder holder, int position) {
        ((ViewHolder) holder).show(position);
    }

    public class ViewHolder extends BaseRecyclerViewHolder {

        @BindView(R.id.civ_img)
        CircleImageView civImg;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_people_num)
        TextView tvPeopleNum;
        @BindView(R.id.tv_win_rate)
        TextView tvWinRate;
        @BindView(R.id.rlt_view)
        RelativeLayout rltView;
        @BindView(R.id.tv_contest_name)
        TextView tvContestName;
        @BindView(R.id.tv_contest_content)
        TextView tvContestContent;
        @BindView(R.id.tv_contest_date)
        TextView tvContestDate;
        @BindView(R.id.tv_contest_integral)
        TextView tvContestIntegral;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void show(int position) {
        }
    }
}
