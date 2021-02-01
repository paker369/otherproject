package com.example.zhugeyouliao.ui.home.bena;

import com.example.zhugeyouliao.bean.BaseBean;

import java.util.List;

/**
 * @Author： longyu
 * @Time： 2021/1/15 11:35
 * @description： 电竞详情
 */
public class ElectronicSportsDetailsBean extends BaseBean {

    private MatertialBean matertialBean;

    private List<MatchesBean> matchesBeans;

    public List<MatchesBean> getMatchesBeans() {
        return matchesBeans;
    }

    public void setMatchesBeans(List<MatchesBean> matchesBeans) {
        this.matchesBeans = matchesBeans;
    }

    public MatertialBean getMatertialBean() {
        return matertialBean;
    }

    public void setMatertialBean(MatertialBean matertialBean) {
        this.matertialBean = matertialBean;
    }
}
