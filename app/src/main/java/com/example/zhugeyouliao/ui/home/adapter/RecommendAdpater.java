package com.example.zhugeyouliao.ui.home.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Outline;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.zhugeyouliao.MyApplication;
import com.example.zhugeyouliao.R;
import com.example.zhugeyouliao.adapter.AFinalRecyclerViewAdapter;
import com.example.zhugeyouliao.adapter.BaseRecyclerViewHolder;
import com.example.zhugeyouliao.api.NetUrlUtils;
import com.example.zhugeyouliao.ui.home.activity.BasketballDetailsActivity;
import com.example.zhugeyouliao.ui.home.activity.EventActivity;
import com.example.zhugeyouliao.ui.home.activity.FootballDetailsActivity;
import com.example.zhugeyouliao.ui.home.bena.BannerBean;
import com.example.zhugeyouliao.ui.home.bena.RecommendBean;
import com.example.zhugeyouliao.ui.home.bena.RecommendContestBean;
import com.example.zhugeyouliao.ui.home.bena.RecommendHotBean;
import com.example.zhugeyouliao.utils.ImageUtils;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.config.BannerConfig;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Author： longyu
 * @Time： 2021/1/13 20:32
 * @description： 首页推荐
 */
public class RecommendAdpater extends AFinalRecyclerViewAdapter<RecommendBean> {

    public static final int HOME_ITEM_0 = 0;
    public static final int HOME_ITEM_1 = 1;

    private OnRecommendItemClickListener mOnRecommendItemClickListener;

    public void setOnRecommendItemClickListener(OnRecommendItemClickListener mOnRecommendItemClickListener) {
        this.mOnRecommendItemClickListener = mOnRecommendItemClickListener;
    }


    public RecommendAdpater(Activity ctx) {
        super(ctx);
    }

    @Override
    protected BaseRecyclerViewHolder onCreateCustomerViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HOME_ITEM_0) {
            return new TopViewHolder(m_Inflater.inflate(R.layout.item_recommend_top, parent, false));
        } else {
            return new ListViewHolder(m_Inflater.inflate(R.layout.item_recommend_list, parent, false));
        }
    }

    @Override
    protected void onBindCustomerViewHolder(BaseRecyclerViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case HOME_ITEM_0:
                ((TopViewHolder) holder).show(position);
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

    /**
     * 头部
     */
    public class TopViewHolder extends BaseRecyclerViewHolder {

        @BindView(R.id.banner)
        Banner banner;
        @BindView(R.id.tv_hot_contest)
        TextView tvHotContest;
        @BindView(R.id.tv_basketball)
        TextView tvBasketball;
        @BindView(R.id.rlt_basketball)
        RelativeLayout rltBasketball;
        @BindView(R.id.tv_football)
        TextView tvFootball;
        @BindView(R.id.rlt_football)
        RelativeLayout rltFootball;
        @BindView(R.id.tv_electronic_sportsv)
        TextView tvElectronicSportsv;
        @BindView(R.id.rlt_electronic_sports)
        RelativeLayout rltElectronicSports;
        @BindView(R.id.tv_activity)
        TextView tvActivity;
        @BindView(R.id.rlt_activity)
        RelativeLayout rltActivity;
        @BindView(R.id.tv_more)
        TextView tvMore;
        @BindView(R.id.rlt_more)
        RelativeLayout rltMore;
        @BindView(R.id.rlv_recommend)
        RecyclerView rlvRecommend;

        public TopViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void show(int position) {
            RecommendBean item = getItem(position);
            List<BannerBean> banners = item.getBanners();
            if (banners != null && banners.size() > 0) {
                banner.setAdapter(new ImageAdapter(banners))
                        .setIndicator(new CircleIndicator(m_Activity));
            }

            LinearLayoutManager layoutManager = new LinearLayoutManager(m_Activity);
            rlvRecommend.setLayoutManager(layoutManager);
            RecommendHotItemAdapter itemAdapter = new RecommendHotItemAdapter(m_Activity);
            rlvRecommend.setAdapter(itemAdapter);
            itemAdapter.refreshList(item.getHotBeans());

            itemAdapter.setOnItemClickListener(new OnItemClickListener<RecommendHotBean.RecordsBean>() {
                @Override
                public void onItemClick(View view, int childPosition, RecommendHotBean.RecordsBean model) {
                    if (mOnRecommendItemClickListener != null) {
                        mOnRecommendItemClickListener.onCommendItemClick(view, position, childPosition, model);
                    }
                }

                @Override
                public void onItemLongClick(View view, int position, RecommendHotBean.RecordsBean model) {

                }
            });
        }


        public class ImageAdapter extends BannerAdapter<BannerBean, ImageAdapter.BannerViewHolder> {
            public ImageAdapter(List<BannerBean> mDatas) {
                //设置数据，也可以调用banner提供的方法,或者自己在adapter中实现
                super(mDatas);
            }

            //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
            @Override
            public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
                ImageView imageView = new ImageView(parent.getContext());
                //注意，必须设置为match_parent，这个是viewpager2强制要求的
                imageView.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                return new BannerViewHolder(imageView);
            }

            @Override
            public void onBindView(BannerViewHolder holder, BannerBean data, int position, int size) {
                ImageUtils.getCircularPic(data.getPic(), (ImageView) holder.itemView, m_Activity, 10, R.mipmap.ic_default_pic);
            }

            class BannerViewHolder extends RecyclerView.ViewHolder {
                ImageView imageView;

                public BannerViewHolder(@NonNull ImageView view) {
                    super(view);
                    this.imageView = view;
                }
            }
        }

        /**
         * @date: 2021/1/14 0:06
         * @description 初始化点击
         */
        private void initTextClick() {
            tvBasketball.setBackground(null);
            tvFootball.setBackground(null);
            tvElectronicSportsv.setBackground(null);
            tvActivity.setBackground(null);
            tvMore.setBackground(null);
        }

        @OnClick({R.id.tv_basketball, R.id.tv_football, R.id.tv_electronic_sportsv, R.id.tv_activity, R.id.tv_more})
        public void onClick(View view) {
            initTextClick();
            switch (view.getId()) {
                case R.id.tv_basketball:
                    tvBasketball.setBackground(m_Activity.getResources().getDrawable(R.drawable.home_recommend_model_click));
                    Drawable drawableLeft = m_Activity.getResources().getDrawable(R.mipmap.ic_home_recommend_basketball);
                    tvHotContest.setCompoundDrawablesWithIntrinsicBounds(drawableLeft,null, null, null);
                    if (mOnRecommendItemClickListener != null) {
                        mOnRecommendItemClickListener.onTypeClick(view, 2);
                    }
                    break;
                case R.id.tv_football:
                    tvFootball.setBackground(m_Activity.getResources().getDrawable(R.drawable.home_recommend_model_click));
                    Drawable drawableLeft1 = m_Activity.getResources().getDrawable(R.mipmap.ic_home_recommend_football);
                    tvHotContest.setCompoundDrawablesWithIntrinsicBounds(drawableLeft1,null, null, null);
                    if (mOnRecommendItemClickListener != null) {
                        mOnRecommendItemClickListener.onTypeClick(view, 1);
                    }
                    break;
                case R.id.tv_electronic_sportsv:
                    tvElectronicSportsv.setBackground(m_Activity.getResources().getDrawable(R.drawable.home_recommend_model_click));
                    Drawable drawableLeft2 = m_Activity.getResources().getDrawable(R.mipmap.ic_home_recommend_electronic_sports);
                    tvHotContest.setCompoundDrawablesWithIntrinsicBounds(drawableLeft2,null, null, null);
                    if (mOnRecommendItemClickListener != null) {
                        mOnRecommendItemClickListener.onTypeClick(view, 3);
                    }
                    break;
                case R.id.tv_activity:
                    tvActivity.setBackground(m_Activity.getResources().getDrawable(R.drawable.home_recommend_model_click));
                    MyApplication.openActivity(m_Activity, EventActivity.class);
                    break;
                case R.id.tv_more:
                    tvMore.setBackground(m_Activity.getResources().getDrawable(R.drawable.home_recommend_model_click));
                    break;
            }
        }
    }

    /**
     * 列表
     */
    public class ListViewHolder extends BaseRecyclerViewHolder {

        @BindView(R.id.rlv_recommend_list)
        RecyclerView rlvRecommendList;

        public ListViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void show(int position) {
            RecommendBean item = getItem(position);

            LinearLayoutManager layoutManager = new LinearLayoutManager(m_Activity);
            rlvRecommendList.setLayoutManager(layoutManager);
            RecommdContestListAdapter recommdContestListAdapter = new RecommdContestListAdapter(m_Activity);
            rlvRecommendList.setAdapter(recommdContestListAdapter);
            recommdContestListAdapter.refreshList(item.getContestBeans());
            recommdContestListAdapter.setOnItemClickListener(new OnItemClickListener<RecommendContestBean.RecordsBean>() {
                @Override
                public void onItemClick(View view, int childPosition, RecommendContestBean.RecordsBean model) {
                    if (mOnRecommendItemClickListener != null) {
                        mOnRecommendItemClickListener.onCommendListItemClick(view, position, childPosition, model);
                    }
                }

                @Override
                public void onItemLongClick(View view, int position, RecommendContestBean.RecordsBean model) {

                }
            });
        }
    }

    // 点击事件接口
    public interface OnRecommendItemClickListener {
        void onTypeClick(View view, int type);

        void onCommendItemClick(View view, int position, int childPosition, RecommendHotBean.RecordsBean model);

        void onCommendListItemClick(View view, int position, int childPosition, RecommendContestBean.RecordsBean model);
    }

}
