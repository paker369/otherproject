package com.example.zhugeyouliao.ui.material.bean;

import com.example.zhugeyouliao.bean.BaseBean;

import java.util.List;

/**
 * @Author： longyu
 * @Time： 2021/1/21 16:25
 * @description：  诸葛秘料
 */
public class MaterialBean extends BaseBean {

    private List<MaterialTopBean> topBeans;

    private List<MaterialListBean> listBeans;

    public List<MaterialTopBean> getTopBeans() {
        return topBeans;
    }

    public void setTopBeans(List<MaterialTopBean> topBeans) {
        this.topBeans = topBeans;
    }

    public List<MaterialListBean> getListBeans() {
        return listBeans;
    }

    public void setListBeans(List<MaterialListBean> listBeans) {
        this.listBeans = listBeans;
    }
}
