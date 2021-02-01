package com.example.zhugeyouliao.ui.community.bean;

import com.example.zhugeyouliao.bean.BaseBean;

import java.util.List;

/**
 * @Author： longyu
 * @Time： 2021/1/22 8:06
 * @description： 文章详情
 */
public class CommunityArticleDetailsBean extends BaseBean {

    private List<ArticleDetailsCommentBean> detailsCommentBeans;

    public List<ArticleDetailsCommentBean> getDetailsCommentBeans() {
        return detailsCommentBeans;
    }

    public void setDetailsCommentBeans(List<ArticleDetailsCommentBean> detailsCommentBeans) {
        this.detailsCommentBeans = detailsCommentBeans;
    }
}
