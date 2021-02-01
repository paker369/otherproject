package com.example.zhugeyouliao.ui.home.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhugeyouliao.R;
import com.example.zhugeyouliao.adapter.AFinalRecyclerViewAdapter;
import com.example.zhugeyouliao.adapter.BaseRecyclerViewHolder;
import com.example.zhugeyouliao.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author： longyu
 * @Time： 2021/1/17 12:20
 * @description： 球类比赛 诸葛密料 联盟数据
 */
public class BallContestLeagueAdapter extends AFinalRecyclerViewAdapter<String> {

    private int mLeagueposition = 0;

    public void setLeagueposition(int mLeagueposition) {
        this.mLeagueposition = mLeagueposition;
    }

    public BallContestLeagueAdapter(Activity ctx) {
        super(ctx);
    }

    @Override
    protected BaseRecyclerViewHolder onCreateCustomerViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(m_Inflater.inflate(R.layout.item_ball_contest_league, parent, false));
    }

    @Override
    protected void onBindCustomerViewHolder(BaseRecyclerViewHolder holder, int position) {
        ((ViewHolder) holder).show(position);
    }

    public class ViewHolder extends BaseRecyclerViewHolder {

        @BindView(R.id.tv_league_name)
        TextView tvLeagueName;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void show(int position) {
            String item = getItem(position);
            if (!StringUtils.isEmpty(item)) {
                tvLeagueName.setText(item);
            }

            if (mLeagueposition == position) {
                tvLeagueName.setBackground(m_Activity.getResources().getDrawable(R.drawable.shape_0090ec_15));
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
