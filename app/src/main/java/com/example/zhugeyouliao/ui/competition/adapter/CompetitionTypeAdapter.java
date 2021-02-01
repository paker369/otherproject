package com.example.zhugeyouliao.ui.competition.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhugeyouliao.R;
import com.example.zhugeyouliao.adapter.AFinalRecyclerViewAdapter;
import com.example.zhugeyouliao.adapter.BaseRecyclerViewHolder;
import com.example.zhugeyouliao.ui.competition.bean.CompetitionBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author： longyu
 * @Time： 2021/1/20 15:51
 * @description： 赛事推荐  分类
 */
public class CompetitionTypeAdapter extends AFinalRecyclerViewAdapter<CompetitionBean> {

    public CompetitionTypeAdapter(Activity ctx) {
        super(ctx);
    }

    @Override
    protected BaseRecyclerViewHolder onCreateCustomerViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(m_Inflater.inflate(R.layout.item_competition_type, parent, false));
    }

    @Override
    protected void onBindCustomerViewHolder(BaseRecyclerViewHolder holder, int position) {
        ((ViewHolder) holder).show(position);
    }

    public class ViewHolder extends BaseRecyclerViewHolder {

        @BindView(R.id.tv_competition_left_top)
        TextView tvCompetitionLeftTop;
        @BindView(R.id.tv_competition_right_top)
        TextView tvCompetitionRightTop;
        @BindView(R.id.iv_competition_live)
        ImageView ivCompetitionLive;
        @BindView(R.id.tv_competition_left_bottom)
        TextView tvCompetitionLeftBottom;
        @BindView(R.id.tv_competition_right_bottom)
        TextView tvCompetitionRightBottom;
        @BindView(R.id.tv_competition_center_top_left)
        TextView tvCompetitionCenterTopLeft;
        @BindView(R.id.tv_competition_center_top)
        TextView tvCompetitionCenterTop;
        @BindView(R.id.tv_competition_center_top_right)
        TextView tvCompetitionCenterTopRight;
        @BindView(R.id.tv_competition_center)
        TextView tvCompetitionCenter;
        @BindView(R.id.tv_competition_center_bottom)
        TextView tvCompetitionCenterBottom;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void show(int position) {

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
