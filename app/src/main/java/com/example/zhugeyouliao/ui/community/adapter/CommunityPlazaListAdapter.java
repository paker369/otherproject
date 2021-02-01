package com.example.zhugeyouliao.ui.community.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhugeyouliao.R;
import com.example.zhugeyouliao.adapter.AFinalRecyclerViewAdapter;
import com.example.zhugeyouliao.adapter.BaseRecyclerViewHolder;
import com.example.zhugeyouliao.ui.community.bean.CommunityPlazaListBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author： longyu
 * @Time： 2021/1/23 7:51
 * @description： 社区  广场 列表
 */
public class CommunityPlazaListAdapter extends AFinalRecyclerViewAdapter<CommunityPlazaListBean> {

    public CommunityPlazaListAdapter(Activity ctx) {
        super(ctx);
    }

    @Override
    protected BaseRecyclerViewHolder onCreateCustomerViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(m_Inflater.inflate(R.layout.item_community_plaza_list, parent, false));
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
            CommunityPlazaListBean item = getItem(position);
            if (item != null){
                    ivPlazaImg.setImageResource(item.getImg());
                    tvPlazaName.setText(item.getName());
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
