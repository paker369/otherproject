package com.example.zhugeyouliao.ui.community.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhugeyouliao.R;
import com.example.zhugeyouliao.adapter.AFinalRecyclerViewAdapter;
import com.example.zhugeyouliao.adapter.BaseRecyclerViewHolder;
import com.example.zhugeyouliao.ui.community.bean.CommunityPlazaTypeBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author： longyu
 * @Time： 2021/1/23 7:51
 * @description： 社区  广场 类型
 */
public class CommunityPlazaTypeAdapter extends AFinalRecyclerViewAdapter<CommunityPlazaTypeBean> {

    public CommunityPlazaTypeAdapter(Activity ctx) {
        super(ctx);
    }

    @Override
    protected BaseRecyclerViewHolder onCreateCustomerViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(m_Inflater.inflate(R.layout.item_community_plaza_type, parent, false));
    }

    @Override
    protected void onBindCustomerViewHolder(BaseRecyclerViewHolder holder, int position) {
        ((ViewHolder) holder).show(position);
    }

    public class ViewHolder extends BaseRecyclerViewHolder {

        @BindView(R.id.iv_plaza_img)
        ImageView ivPlazaImg;
        @BindView(R.id.tv_plaza_name)
        TextView tvPlazaName;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void show(int position) {
            CommunityPlazaTypeBean item = getItem(position);
            if (item != null) {
                ivPlazaImg.setImageResource(item.getImg());
                tvPlazaName.setText(item.getName());
                if (item.isSelect()) {
                    itemView.setBackground(m_Activity.getResources().getDrawable(R.drawable.shape_009afc_13));
                } else {
                    itemView.setBackground(m_Activity.getResources().getDrawable(R.drawable.shape_white_13));
                }
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(v, position, item);
                    }
                }
            });
        }
    }

}
