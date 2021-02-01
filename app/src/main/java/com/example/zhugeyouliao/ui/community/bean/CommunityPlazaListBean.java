package com.example.zhugeyouliao.ui.community.bean;

import com.example.zhugeyouliao.bean.BaseBean;

/**
 * @Author： longyu
 * @Time： 2021/1/23 7:39
 * @description：  社区 广场 列表
 */
public class CommunityPlazaListBean extends BaseBean {

    private int img;

    private String name;

    private boolean isSelect;

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
