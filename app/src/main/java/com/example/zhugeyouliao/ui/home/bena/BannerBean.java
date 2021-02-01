package com.example.zhugeyouliao.ui.home.bena;

import com.example.zhugeyouliao.bean.BaseBean;

/**
 * @Author： longyu
 * @Time： 2021/1/14 0:01
 * @description： 轮播图
 */
public class BannerBean extends BaseBean {

    /**
     * backstageActiveId : 7
     * name : 诸葛有料：预言家 参与发帖送豪礼
     * updateTime : 2020-10-20T15:55:53.000+0000
     * id : 49
     * pic : https://img.zhugeyl.com/6448ddfa-f8f3-40e3-af54-65f6fc74fe08.jpg
     * type : 5
     * url : http://zhugeyl.com/
     */

    private int backstageActiveId;
    private String name;
    private String updateTime;
    private int id;
    private String pic;
    private int type;
    private String url;

    public int getBackstageActiveId() {
        return backstageActiveId;
    }

    public void setBackstageActiveId(int backstageActiveId) {
        this.backstageActiveId = backstageActiveId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
