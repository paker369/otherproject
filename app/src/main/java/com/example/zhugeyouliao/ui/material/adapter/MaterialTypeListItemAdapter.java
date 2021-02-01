package com.example.zhugeyouliao.ui.material.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhugeyouliao.R;
import com.example.zhugeyouliao.adapter.AFinalRecyclerViewAdapter;
import com.example.zhugeyouliao.adapter.BaseRecyclerViewHolder;
import com.example.zhugeyouliao.ui.material.bean.MaterialListBean;
import com.example.zhugeyouliao.widget.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author： longyu
 * @Time： 2021/1/21 16:35
 * @description： 诸葛秘料 列表数据
 */
public class MaterialTypeListItemAdapter extends AFinalRecyclerViewAdapter<MaterialListBean> {

    public MaterialTypeListItemAdapter(Activity ctx) {
        super(ctx);
    }

    @Override
    protected BaseRecyclerViewHolder onCreateCustomerViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(m_Inflater.inflate(R.layout.item_material_type_list, parent, false));
    }

    @Override
    protected void onBindCustomerViewHolder(BaseRecyclerViewHolder holder, int position) {
        ((ViewHolder) holder).show(position);
    }

    public class ViewHolder extends BaseRecyclerViewHolder {

        @BindView(R.id.civ_material_type_header)
        CircleImageView civMaterialTypeHeader;
        @BindView(R.id.tv_material_type_name)
        TextView tvMaterialTypeName;
        @BindView(R.id.tv_material_type_time)
        TextView tvMaterialTypeTime;
        @BindView(R.id.tv_material_type_win_rate)
        TextView tvMaterialTypeWinRate;
        @BindView(R.id.tv_material_type_contest_team)
        TextView tvMaterialTypeContestTeam;
        @BindView(R.id.tv_material_type_contest_name)
        TextView tvMaterialTypeContestName;
        @BindView(R.id.tv_material_type_contest_state)
        TextView tvMaterialTypeContestState;
        @BindView(R.id.iv_is_scriptures)
        ImageView ivIsScriptures;
        @BindView(R.id.tv_material_type_integral)
        TextView tvMaterialTypeIntegral;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void show(int position) {
            if (position == 0 || position == 1) {
                ivIsScriptures.setVisibility(View.VISIBLE);
                tvMaterialTypeContestState.setText(m_Activity.getResources().getString(R.string.underway));
                tvMaterialTypeContestState.setTextColor(m_Activity.getResources().getColor(R.color.color_00FF66));
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
