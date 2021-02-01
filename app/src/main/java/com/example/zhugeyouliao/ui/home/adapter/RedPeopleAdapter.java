package com.example.zhugeyouliao.ui.home.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zhugeyouliao.R;
import com.example.zhugeyouliao.adapter.AFinalRecyclerViewAdapter;
import com.example.zhugeyouliao.adapter.BaseRecyclerViewHolder;
import com.example.zhugeyouliao.api.NetUrlUtils;
import com.example.zhugeyouliao.ui.home.bena.RedPeopleBean;
import com.example.zhugeyouliao.ui.home.bena.RedPeopleListBean;
import com.example.zhugeyouliao.utils.ImageUtils;
import com.example.zhugeyouliao.utils.ToastUtils;
import com.example.zhugeyouliao.widget.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Author： longyu
 * @Time： 2021/1/14 15:50
 * @description： 首页红人
 */
public class RedPeopleAdapter extends AFinalRecyclerViewAdapter<RedPeopleBean> {

    public static final int HOME_ITEM_0 = 0;
    public static final int HOME_ITEM_1 = 1;

    private int mType;

    public void setmType(int mType) {
        this.mType = mType;
    }

    private OnPeopleItemClickListener onPeopleItemClickListener;

    public void setOnPeopleItemClickListener(OnPeopleItemClickListener onPeopleItemClickListener) {
        this.onPeopleItemClickListener = onPeopleItemClickListener;
    }

    public RedPeopleAdapter(Activity ctx) {
        super(ctx);
    }

    @Override
    protected BaseRecyclerViewHolder onCreateCustomerViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HOME_ITEM_0) {
            return new NoticeViewHolder(m_Inflater.inflate(R.layout.layout_red_prople_notice, parent, false));
        } else {
            return new ListViewHolder(m_Inflater.inflate(R.layout.layout_recycleview, parent, false));
        }
    }

    @Override
    protected void onBindCustomerViewHolder(BaseRecyclerViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case HOME_ITEM_0:
                ((NoticeViewHolder) holder).show(position);
                break;
            case HOME_ITEM_1:
                ((ListViewHolder) holder).show(position);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HOME_ITEM_0;
        } else if (position == 1) {
            return HOME_ITEM_1;
        }
        return super.getItemViewType(position);
    }

    public class NoticeViewHolder extends BaseRecyclerViewHolder {

        @BindView(R.id.civ_notice_two_img)
        CircleImageView civNoticeTwoImg;
        @BindView(R.id.tv_notice_two)
        TextView tvNoticeTwo;
        @BindView(R.id.tv_notice_two_people_num)
        TextView tvNoticeTwoPeopleNum;
        @BindView(R.id.tv_notice_two_attention)
        TextView tvNoticeTwoAttention;
        @BindView(R.id.civ_notice_one_img)
        CircleImageView civNoticeOneImg;
        @BindView(R.id.tv_notice_one)
        TextView tvNoticeOne;
        @BindView(R.id.tv_notice_one_people_num)
        TextView tvNoticeOnePeopleNum;
        @BindView(R.id.tv_notice_one_attention)
        TextView tvNoticeOneAttention;
        @BindView(R.id.civ_notice_three_img)
        CircleImageView civNoticeThreeImg;
        @BindView(R.id.tv_notice_thre)
        TextView tvNoticeThre;
        @BindView(R.id.tv_notice_three_people_num)
        TextView tvNoticeThreePeopleNum;
        @BindView(R.id.tv_notice_thre_attention)
        TextView tvNoticeThreAttention;

        public NoticeViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void show(int position) {
            RedPeopleBean item = getItem(position);
            if (item != null) {
                ImageUtils.getPic(NetUrlUtils.createPhotoUrl(item.getFirstData().getHeadUrl()), civNoticeOneImg, m_Activity);
                ImageUtils.getPic(NetUrlUtils.createPhotoUrl(item.getSecondData().getHeadUrl()), civNoticeTwoImg, m_Activity);
                ImageUtils.getPic(NetUrlUtils.createPhotoUrl(item.getThirdlyData().getHeadUrl()), civNoticeThreeImg, m_Activity);
                tvNoticeOne.setText(item.getFirstData().getNickName());
                tvNoticeTwo.setText(item.getSecondData().getNickName());
                tvNoticeThre.setText(item.getThirdlyData().getNickName());
                tvNoticeOnePeopleNum.setText("" + item.getFirstData().getFansCount());
                tvNoticeTwoPeopleNum.setText("" + item.getSecondData().getFansCount());
                tvNoticeThreePeopleNum.setText("" + item.getThirdlyData().getFansCount());
                //(1  已关注     0未关注 3 自己)
                int isFollow = item.getFirstData().getIsFollow();
                switch (isFollow) {
                    case 1:
                        tvNoticeOneAttention.setText(m_Activity.getResources().getString(R.string.followed));
                        tvNoticeOneAttention.setBackground(m_Activity.getResources().getDrawable(R.drawable.shape_202020_10));
                        break;
                    case 2:
                        tvNoticeOneAttention.setText(m_Activity.getResources().getString(R.string.attention));
                        tvNoticeOneAttention.setBackground(m_Activity.getResources().getDrawable(R.drawable.shape_0090ec_10));
                        break;
                    case 3:
                        tvNoticeOneAttention.setVisibility(View.GONE);
                        break;
                }
                int isFollow2 = item.getSecondData().getIsFollow();
                switch (isFollow2) {
                    case 1:
                        tvNoticeTwoAttention.setText(m_Activity.getResources().getString(R.string.followed));
                        tvNoticeTwoAttention.setBackground(m_Activity.getResources().getDrawable(R.drawable.shape_202020_10));
                        break;
                    case 2:
                        tvNoticeTwoAttention.setText(m_Activity.getResources().getString(R.string.attention));
                        tvNoticeTwoAttention.setBackground(m_Activity.getResources().getDrawable(R.drawable.shape_0090ec_10));
                        break;
                    case 3:
                        tvNoticeTwoAttention.setVisibility(View.GONE);
                        break;
                }
                int isFollow3 = item.getThirdlyData().getIsFollow();
                switch (isFollow3) {
                    case 1:
                        tvNoticeThreAttention.setText(m_Activity.getResources().getString(R.string.followed));
                        tvNoticeThreAttention.setBackground(m_Activity.getResources().getDrawable(R.drawable.shape_202020_10));
                        break;
                    case 2:
                        tvNoticeThreAttention.setText(m_Activity.getResources().getString(R.string.attention));
                        tvNoticeThreAttention.setBackground(m_Activity.getResources().getDrawable(R.drawable.shape_0090ec_10));
                        break;
                    case 3:
                        tvNoticeThreAttention.setVisibility(View.GONE);
                        break;
                }
            }
        }

        @OnClick(R.id.tv_notice_thre_attention)
        public void onClick() {
            ToastUtils.show(m_Activity, "您点击了关注");
        }
    }

    public class ListViewHolder extends BaseRecyclerViewHolder {

        @BindView(R.id.rlv_layout)
        RecyclerView rlvLayout;

        public ListViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void show(int position) {
            RedPeopleBean item = getItem(position);
            LinearLayoutManager layoutManager = new LinearLayoutManager(m_Activity);
            rlvLayout.setLayoutManager(layoutManager);
            RedPeopleListAdapter listAdapter = new RedPeopleListAdapter(m_Activity);
            listAdapter.setmType(mType);
            rlvLayout.setAdapter(listAdapter);
            listAdapter.refreshList(item.getListBeans());
            listAdapter.setOnItemClickListener(new OnItemClickListener<RedPeopleListBean>() {
                @Override
                public void onItemClick(View view, int childPosition, RedPeopleListBean model) {
                    if (onPeopleItemClickListener != null) {
                        onPeopleItemClickListener.onPeopleItemClick(view, position, childPosition, model);
                    }
                }

                @Override
                public void onItemLongClick(View view, int position, RedPeopleListBean model) {

                }
            });
        }
    }

    // 点击事件接口
    public interface OnPeopleItemClickListener {
        void onPeopleItemClick(View view, int position, int childPosition, RedPeopleListBean model);
    }
}
