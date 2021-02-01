package com.example.zhugeyouliao.ui.community.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhugeyouliao.R;
import com.example.zhugeyouliao.adapter.AFinalRecyclerViewAdapter;
import com.example.zhugeyouliao.adapter.BaseRecyclerViewHolder;
import com.example.zhugeyouliao.ui.community.bean.ArticleDetailsCommentBean;
import com.example.zhugeyouliao.widget.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author： longyu
 * @Time： 2021/1/22 8:16
 * @description： 文章详情评论
 */
public class ArticlecommentAdapter extends AFinalRecyclerViewAdapter<ArticleDetailsCommentBean> {

    public ArticlecommentAdapter(Activity ctx) {
        super(ctx);
    }

    @Override
    protected BaseRecyclerViewHolder onCreateCustomerViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(m_Inflater.inflate(R.layout.item_article_comment, parent, false));
    }

    @Override
    protected void onBindCustomerViewHolder(BaseRecyclerViewHolder holder, int position) {
        ((ViewHolder) holder).show(position);
    }

    public class ViewHolder extends BaseRecyclerViewHolder {

        @BindView(R.id.civ_comment_header)
        CircleImageView civCommentHeader;
        @BindView(R.id.tv_comment_name)
        TextView tvCommentName;
        @BindView(R.id.tv_comment_time)
        TextView tvCommentTime;
        @BindView(R.id.tv_article_comment_content)
        TextView tvArticleCommentContent;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void show(int position) {
        }
    }

}
