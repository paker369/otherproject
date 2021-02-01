package com.example.zhugeyouliao.ui.home.bena;

import com.example.zhugeyouliao.bean.BaseBean;

import java.util.List;

/**
 * @Author： longyu
 * @Time： 2021/1/13 20:35
 * @description：  首页推荐
 */
public class RecommendBean extends BaseBean {

    //轮播图
    private List<BannerBean> banners;

    private List<RecommendContestBean.RecordsBean> contestBeans;

    private List<RecommendHotBean.RecordsBean> hotBeans;

    public List<RecommendHotBean.RecordsBean> getHotBeans() {
        return hotBeans;
    }

    public void setHotBeans(List<RecommendHotBean.RecordsBean> hotBeans) {
        this.hotBeans = hotBeans;
    }

    public List<RecommendContestBean.RecordsBean> getContestBeans() {
        return contestBeans;
    }

    public void setContestBeans(List<RecommendContestBean.RecordsBean> contestBeans) {
        this.contestBeans = contestBeans;
    }

    public List<BannerBean> getBanners() {
        return banners;
    }

    public void setBanners(List<BannerBean> banners) {
        this.banners = banners;
    }
}
