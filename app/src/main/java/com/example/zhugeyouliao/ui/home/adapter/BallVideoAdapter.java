package com.example.zhugeyouliao.ui.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhugeyouliao.R;
import com.example.zhugeyouliao.adapter.AFinalRecyclerViewAdapter;
import com.example.zhugeyouliao.adapter.BaseRecyclerViewHolder;
import com.example.zhugeyouliao.ui.home.bena.BallVideoBean;

import butterknife.ButterKnife;

/**
 * @Author： longyu
 * @Time： 2021/1/17 21:45
 * @description： 球类 比赛视频
 */
public class BallVideoAdapter extends AFinalRecyclerViewAdapter<BallVideoBean> {

    public BallVideoAdapter(Context ctx) {
        super(ctx);
    }

    @Override
    protected BaseRecyclerViewHolder onCreateCustomerViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(m_Inflater.inflate(R.layout.item_ball_video, parent, false));
    }

    @Override
    protected void onBindCustomerViewHolder(BaseRecyclerViewHolder holder, int position) {
        ((ViewHolder) holder).show(position);
    }

    public class ViewHolder extends BaseRecyclerViewHolder {

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
