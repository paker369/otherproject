package com.example.zhugeyouliao.ui.home.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhugeyouliao.R;
import com.example.zhugeyouliao.adapter.AFinalRecyclerViewAdapter;
import com.example.zhugeyouliao.adapter.BaseRecyclerViewHolder;
import com.example.zhugeyouliao.ui.home.bena.MatertialBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author： longyu
 * @Time： 2021/1/15 22:46
 * @description： 电竞详情 诸葛密料
 */
public class MaterialAdapter extends AFinalRecyclerViewAdapter<MatertialBean.RecordsBean> {

    public MaterialAdapter(Activity ctx) {
        super(ctx);
    }

    @Override
    protected BaseRecyclerViewHolder onCreateCustomerViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(m_Inflater.inflate(R.layout.item_material, parent, false));
    }

    @Override
    protected void onBindCustomerViewHolder(BaseRecyclerViewHolder holder, int position) {
        ((ViewHolder) holder).show(position);
    }

    public class ViewHolder extends BaseRecyclerViewHolder {

        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.tv_team_left)
        TextView tvTeamLeft;
        @BindView(R.id.tv_score)
        TextView tvScore;
        @BindView(R.id.tv_team_right)
        TextView tvTeamRight;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void show(int position) {
            MatertialBean.RecordsBean item = getItem(position);
            if (item != null) {
                tvName.setText(item.getMatchShortName());
                tvDate.setText(item.getStartTime());
                tvTeamLeft.setText(item.getAName());
                tvTeamRight.setText(item.getBName());
                tvScore.setText(item.getTeamScore());
            }
        }
    }

}
