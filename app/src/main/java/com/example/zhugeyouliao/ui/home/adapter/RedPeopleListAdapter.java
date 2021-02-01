package com.example.zhugeyouliao.ui.home.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhugeyouliao.R;
import com.example.zhugeyouliao.adapter.AFinalRecyclerViewAdapter;
import com.example.zhugeyouliao.adapter.BaseRecyclerViewHolder;
import com.example.zhugeyouliao.api.NetUrlUtils;
import com.example.zhugeyouliao.ui.home.bena.RedPeopleListBean;
import com.example.zhugeyouliao.utils.ImageUtils;
import com.example.zhugeyouliao.widget.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author： longyu
 * @Time： 2021/1/14 15:58
 * @description： 首页红人列表
 */
public class RedPeopleListAdapter extends AFinalRecyclerViewAdapter<RedPeopleListBean> {

    private int mType;

    public void setmType(int mType) {
        this.mType = mType;
    }

    public RedPeopleListAdapter(Activity ctx) {
        super(ctx);
    }

    @Override
    protected BaseRecyclerViewHolder onCreateCustomerViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(m_Inflater.inflate(R.layout.item_red_people_list, parent, false));
    }

    @Override
    protected void onBindCustomerViewHolder(BaseRecyclerViewHolder holder, int position) {
        ((ViewHolder) holder).show(position);
    }

    public class ViewHolder extends BaseRecyclerViewHolder {

        @BindView(R.id.civ_people_head)
        CircleImageView civPeopleHead;
        @BindView(R.id.tv_people_name)
        TextView tvPeopleName;
        @BindView(R.id.tv_people_num)
        TextView tvPeopleNum;
        @BindView(R.id.tv_people_attention)
        TextView tvPeopleAttention;
        @BindView(R.id.tv_fans)
        TextView tvFans;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void show(int position) {
            RedPeopleListBean item = getItem(position);
            if (item != null) {
                if (mType == 1) {
                    tvFans.setText(m_Activity.getResources().getString(R.string.win_rate_num));
                    tvPeopleNum.setText("" + item.getHitRate() + "%");
                } else {
                    tvFans.setText(m_Activity.getResources().getString(R.string.fans_num));
                    tvPeopleNum.setText("" + item.getFansCount());
                }
                ImageUtils.getPic(NetUrlUtils.createPhotoUrl(item.getHeadUrl()), civPeopleHead, m_Activity);
                tvPeopleName.setText(item.getNickName());
                int isFollow = item.getIsFollow();
                switch (isFollow) {
                    case 1:
                        tvPeopleAttention.setText(m_Activity.getResources().getString(R.string.followed));
                        tvPeopleAttention.setBackground(m_Activity.getResources().getDrawable(R.drawable.shape_202020_10));
                        break;
                    case 2:
                        tvPeopleAttention.setText(m_Activity.getResources().getString(R.string.attention));
                        tvPeopleAttention.setBackground(m_Activity.getResources().getDrawable(R.drawable.shape_0090ec_10));
                        break;
                    case 3:
                        tvPeopleAttention.setVisibility(View.GONE);
                        break;
                }
            }
        }
    }

}
