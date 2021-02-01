package com.example.zhugeyouliao.ui.material.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhugeyouliao.R;
import com.example.zhugeyouliao.adapter.AFinalRecyclerViewAdapter;
import com.example.zhugeyouliao.adapter.BaseRecyclerViewHolder;
import com.example.zhugeyouliao.ui.material.bean.MaterialTopBean;
import com.example.zhugeyouliao.widget.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author： longyu
 * @Time： 2021/1/21 16:35
 * @description： 诸葛秘料
 */
public class MaterialTypeTopItemAdapter extends AFinalRecyclerViewAdapter<MaterialTopBean> {

    public MaterialTypeTopItemAdapter(Activity ctx) {
        super(ctx);
    }

    @Override
    protected BaseRecyclerViewHolder onCreateCustomerViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(m_Inflater.inflate(R.layout.item_material_type_top, parent, false));
    }

    @Override
    protected void onBindCustomerViewHolder(BaseRecyclerViewHolder holder, int position) {
        ((ViewHolder) holder).show(position);
    }

    public class ViewHolder extends BaseRecyclerViewHolder {

        @BindView(R.id.civ_material_type_top_img)
        CircleImageView civMaterialTypeTopImg;
        @BindView(R.id.tv_material_type_top_name)
        TextView tvMaterialTypeTopName;
        @BindView(R.id.tv_material_type_top_fans)
        TextView tvMaterialTypeTopFans;
        @BindView(R.id.tv_material_type_top_win_rate)
        TextView tvMaterialTypeTopWinRate;

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
