package com.example.zhugeyouliao.ui.community.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zhugeyouliao.R;
import com.example.zhugeyouliao.adapter.AFinalRecyclerViewAdapter;
import com.example.zhugeyouliao.adapter.BaseRecyclerViewHolder;
import com.example.zhugeyouliao.ui.community.bean.CommunityArticleDetailsBean;
import com.example.zhugeyouliao.widget.CircleImageView;
import com.example.zhugeyouliao.widget.TopProgressWebView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author： longyu
 * @Time： 2021/1/22 8:05
 * @description： 文章详情
 */
public class CommunityArticleDetailsAadapter extends AFinalRecyclerViewAdapter<CommunityArticleDetailsBean> {

    public static final int HOME_ITEM_0 = 0;
    public static final int HOME_ITEM_1 = 1;

    public CommunityArticleDetailsAadapter(Activity ctx) {
        super(ctx);
    }

    @Override
    protected BaseRecyclerViewHolder onCreateCustomerViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HOME_ITEM_0) {
            return new ViewHolder(m_Inflater.inflate(R.layout.layout_community_article_details_web, parent, false));
        } else {
            return new CommentHolder(m_Inflater.inflate(R.layout.layout_article_details_comment, parent, false));
        }
    }

    @Override
    protected void onBindCustomerViewHolder(BaseRecyclerViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case HOME_ITEM_0:
                ((ViewHolder) holder).show(position);
                break;
            case HOME_ITEM_1:
                ((CommentHolder) holder).show(position);
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

    public class ViewHolder extends BaseRecyclerViewHolder {

        @BindView(R.id.tv_article_details_title)
        TextView tvArticleDetailsTitle;
        @BindView(R.id.civ_article_details_header)
        CircleImageView civArticleDetailsHeader;
        @BindView(R.id.tv_article_details_name)
        TextView tvArticleDetailsName;
        @BindView(R.id.tv_article_details_time)
        TextView tvArticleDetailsTime;
        @BindView(R.id.tv_article_details_read)
        TextView tvArticleDetailsRead;
        @BindView(R.id.webVi_message_details)
        TopProgressWebView webViMessageDetails;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void show(int position) {
        }
    }

    public class CommentHolder extends BaseRecyclerViewHolder {

        @BindView(R.id.tv_article_details_comment)
        TextView tvArticleDetailsComment;
        @BindView(R.id.rlv_layout)
        RecyclerView rlvLayout;

        public CommentHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void show(int position) {

            LinearLayoutManager layoutManager = new LinearLayoutManager(m_Activity);
            rlvLayout.setLayoutManager(layoutManager);
            ArticlecommentAdapter articlecommentAdapter = new ArticlecommentAdapter(m_Activity);
            rlvLayout.setAdapter(articlecommentAdapter);
            articlecommentAdapter.refreshList(getItem(position).getDetailsCommentBeans());
        }
    }

}
