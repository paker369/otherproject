package com.example.zhugeyouliao.ui.community.bean;

import com.example.zhugeyouliao.bean.BaseBean;

import java.util.List;

/**
 * @Author： longyu
 * @Time： 2021/1/23 7:39
 * @description：  社区 广场 类型
 */
public class CommunityPlazaTypeBean extends BaseBean {

    private int img;

    private String name;

    private List<CommunityPlazaListBean> listBeans;

    private boolean isSelect;

    public List<CommunityPlazaListBean> getListBeans() {
        return listBeans;
    }

    public void setListBeans(List<CommunityPlazaListBean> listBeans) {
        this.listBeans = listBeans;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
