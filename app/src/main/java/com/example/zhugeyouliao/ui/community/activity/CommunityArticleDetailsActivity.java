package com.example.zhugeyouliao.ui.community.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zhugeyouliao.R;
import com.example.zhugeyouliao.base.BaseActivity;
import com.example.zhugeyouliao.ui.community.adapter.CommunityArticleDetailsAadapter;
import com.example.zhugeyouliao.ui.community.bean.ArticleDetailsCommentBean;
import com.example.zhugeyouliao.ui.community.bean.CommunityArticleDetailsBean;
import com.example.zhugeyouliao.utils.StatusBarUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Author： longyu
 * @Time： 2021/1/22 6:58
 * @description： 文章详情
 */
public class CommunityArticleDetailsActivity extends BaseActivity {
    @BindView(R.id.status_bar_view)
    View statusBarView;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.center_title)
    TextView centerTitle;
    @BindView(R.id.rlv_layout)
    RecyclerView rlvLayout;
    @BindView(R.id.llyt_no_data)
    LinearLayout llytNoData;
    @BindView(R.id.srl_layout)
    SmartRefreshLayout srlLayout;
    @BindView(R.id.tv_article_details_liek)
    TextView tvArticleDetailsLiek;
    @BindView(R.id.tv_article_details_collect)
    TextView tvArticleDetailsCollect;
    @BindView(R.id.tv_article_details_report)
    TextView tvArticleDetailsReport;
    @BindView(R.id.llt_click_pulish_comment)
    LinearLayout lltClickPulishComment;
    @BindView(R.id.tv_article_details_comment_cancel)
    TextView tvArticleDetailsCommentCancel;
    @BindView(R.id.edt_article_details_comment)
    EditText edtArticleDetailsComment;
    @BindView(R.id.tv_article_details_comment_num)
    TextView tvArticleDetailsCommentNum;
    @BindView(R.id.tv_pulish_commment)
    TextView tvPulishCommment;
    @BindView(R.id.rlt_pulish_commment)
    RelativeLayout rltPulishCommment;

    private CommunityArticleDetailsAadapter mCommunityArticleDetailsAadapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_community_article_details;
    }

    @Override
    protected void initData() {
        statusBarView.getLayoutParams().height = StatusBarUtils.getStatusBarHeight(mContext);
        initRefreshLayout();
        initRecyclerView();
        getData();
    }
    /**
     * @date: 2021/1/22 6:48
     * @description 初始化刷新
     */
    private void initRefreshLayout() {
        srlLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh();
            }
        });
        srlLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMoreWithNoMoreData();
            }
        });
    }

    /**
     * @date: 2021/1/22 6:48
     * @description 初始化列表
     */
    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        rlvLayout.setLayoutManager(linearLayoutManager);
        mCommunityArticleDetailsAadapter = new CommunityArticleDetailsAadapter(mContext);
        rlvLayout.setAdapter(mCommunityArticleDetailsAadapter);
    }

     /**
      * @date: 2021/1/22 8:11
      * @description 获取数据
      */
    private void getData(){
        List<CommunityArticleDetailsBean> beanList = new ArrayList<>();
        CommunityArticleDetailsBean detailsBean = new CommunityArticleDetailsBean();

        List<ArticleDetailsCommentBean> commentBeans = new ArrayList<>();
        ArticleDetailsCommentBean commentBean = new ArticleDetailsCommentBean();
        commentBeans.add(commentBean);
        commentBeans.add(commentBean);
        commentBeans.add(commentBean);
        commentBeans.add(commentBean);
        commentBeans.add(commentBean);
        commentBeans.add(commentBean);
        commentBeans.add(commentBean);
        detailsBean.setDetailsCommentBeans(commentBeans);

        beanList.add(detailsBean);
        beanList.add(detailsBean);
        mCommunityArticleDetailsAadapter.refreshList(beanList);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_click_pulish_comment, R.id.tv_article_details_liek, R.id.tv_article_details_collect, R.id.tv_article_details_comment_cancel, R.id.tv_article_details_report, R.id.tv_pulish_commment})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_click_pulish_comment:
                //点击发表评论
                rltPulishCommment.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_article_details_liek:
                //点赞
                break;
            case R.id.tv_article_details_collect:
                //收藏
                break;
            case R.id.tv_article_details_report:
                //举报
                break;
            case R.id.tv_article_details_comment_cancel:
                //取消
                rltPulishCommment.setVisibility(View.GONE);
                break;
            case R.id.tv_pulish_commment:
                //发表评论
                break;
        }
    }
}
