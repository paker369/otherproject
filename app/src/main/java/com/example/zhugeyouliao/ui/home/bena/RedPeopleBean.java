package com.example.zhugeyouliao.ui.home.bena;

import com.example.zhugeyouliao.bean.BaseBean;

import java.util.List;

/**
 * @Author： longyu
 * @Time： 2021/1/14 15:49
 * @description：  首页红人
 */
public class RedPeopleBean extends BaseBean {
    private RedPeopleListBean firstData;
    private RedPeopleListBean secondData;
    private RedPeopleListBean thirdlyData;

    private List<RedPeopleListBean> listBeans;

    public RedPeopleListBean getFirstData() {
        return firstData;
    }

    public void setFirstData(RedPeopleListBean firstData) {
        this.firstData = firstData;
    }

    public RedPeopleListBean getSecondData() {
        return secondData;
    }

    public void setSecondData(RedPeopleListBean secondData) {
        this.secondData = secondData;
    }

    public RedPeopleListBean getThirdlyData() {
        return thirdlyData;
    }

    public void setThirdlyData(RedPeopleListBean thirdlyData) {
        this.thirdlyData = thirdlyData;
    }

    public List<RedPeopleListBean> getListBeans() {
        return listBeans;
    }

    public void setListBeans(List<RedPeopleListBean> listBeans) {
        this.listBeans = listBeans;
    }
}
