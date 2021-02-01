package com.example.zhugeyouliao.ui.home.bena;

import com.example.zhugeyouliao.bean.BaseBean;

/**
 * @Author： longyu
 * @Time： 2021/1/19 10:25
 * @description： 足球  文字直播
 */
public class BallTextLiveBean extends BaseBean {

    /**
     * data : 大家好，欢迎收看本场比赛直播，球员们正在热身，比赛即将开始
     * time :
     * position : 0
     * type : 0
     */

    private String data;
    private String time;
    private int position;
    private int type;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
