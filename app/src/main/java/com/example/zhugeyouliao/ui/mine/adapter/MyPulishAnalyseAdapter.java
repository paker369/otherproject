package com.example.zhugeyouliao.ui.mine.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zhugeyouliao.R;
import com.example.zhugeyouliao.adapter.AFinalRecyclerViewAdapter;
import com.example.zhugeyouliao.adapter.BaseRecyclerViewHolder;
import com.example.zhugeyouliao.ui.mine.bean.MyPulishAnalyseBean;
import com.example.zhugeyouliao.widget.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @Author： longyu
 * @Time： 2021/1/21 13:11
 * @description： 我的发布的分析
 */
public class MyPulishAnalyseAdapter extends AFinalRecyclerViewAdapter<MyPulishAnalyseBean> {

    public MyPulishAnalyseAdapter(Activity ctx) {
        super(ctx);
    }

    @Override
    protected BaseRecyclerViewHolder onCreateCustomerViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(m_Inflater.inflate(R.layout.item_my_pulish_analyse, parent, false));
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
        @BindView(R.id.tv_delete)
        TextView tvDelete;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void show(int position) {

            tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(v, position, getItem(position));
                    }
                }
            });
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
