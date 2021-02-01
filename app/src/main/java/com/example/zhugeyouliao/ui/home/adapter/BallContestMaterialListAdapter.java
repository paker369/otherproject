package com.example.zhugeyouliao.ui.home.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhugeyouliao.R;
import com.example.zhugeyouliao.adapter.AFinalRecyclerViewAdapter;
import com.example.zhugeyouliao.adapter.BaseRecyclerViewHolder;
import com.example.zhugeyouliao.ui.home.bena.BallContestMaterialListBean;
import com.example.zhugeyouliao.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author： longyu
 * @Time： 2021/1/17 12:20
 * @description： 球类比赛 诸葛密料 列表数据
 */
public class BallContestMaterialListAdapter extends AFinalRecyclerViewAdapter<BallContestMaterialListBean.WebBallMatchPageBean.RecordsBean> {

    public BallContestMaterialListAdapter(Activity ctx) {
        super(ctx);
    }

    @Override
    protected BaseRecyclerViewHolder onCreateCustomerViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(m_Inflater.inflate(R.layout.item_ball_countest_material_list, parent, false));
    }

    @Override
    protected void onBindCustomerViewHolder(BaseRecyclerViewHolder holder, int position) {
        ((ViewHolder) holder).show(position);
    }

    public class ViewHolder extends BaseRecyclerViewHolder {

        @BindView(R.id.tv_ball_material_list_date)
        TextView tvBallMaterialListDate;
        @BindView(R.id.tv_ball_material_list_left_bottom)
        TextView tvBallMaterialListLeftBottom;
        @BindView(R.id.tv_ball_material_list_right_top)
        TextView tvBallMaterialListRightTop;
        @BindView(R.id.tv_ball_material_list_right_bottom)
        TextView tvBallMaterialListRightBottom;
        @BindView(R.id.tv_ball_material_list_name)
        TextView tvBallMaterialListName;
        @BindView(R.id.tv_ball_material_list_vs)
        TextView tvBallMaterialListVs;
        @BindView(R.id.tv_ball_material_list_center_bottom)
        TextView tvBallMaterialListCenterBottom;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void show(int position) {
            BallContestMaterialListBean.WebBallMatchPageBean.RecordsBean item = getItem(position);
            if (item != null) {
                tvBallMaterialListDate.setText(item.getMatchStartTime());
                tvBallMaterialListRightTop.setText(m_Activity.getResources().getString(R.string.halfScore) + item.getHalfScore()
                        + "  " + m_Activity.getResources().getString(R.string.corner_ball) + item.getMatchCorner());
                tvBallMaterialListLeftBottom.setText(m_Activity.getResources().getString(R.string.ya) + (StringUtils.isEmpty(item.getAsianplate()) ? "" : item.getAsianplate()));
                tvBallMaterialListCenterBottom.setText(m_Activity.getResources().getString(R.string.ou) + (StringUtils.isEmpty(item.getEuroloss()) ? "" : item.getEuroloss()));
                tvBallMaterialListRightBottom.setText(m_Activity.getResources().getString(R.string.da) + (StringUtils.isEmpty(item.getSizeBall()) ? "" : item.getSizeBall()));
                tvBallMaterialListName.setText(item.getMatchShortNameZh());
                tvBallMaterialListVs.setText(item.getATeamShortName() + "  " + item.getMatchScore() + "  " + item.getBTeamShortName());
            }
        }
    }

}
