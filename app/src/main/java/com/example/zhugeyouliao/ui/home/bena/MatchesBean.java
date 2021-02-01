package com.example.zhugeyouliao.ui.home.bena;

import com.example.zhugeyouliao.bean.BaseBean;

import java.util.List;

/**
 * @Author： longyu
 * @Time： 2021/1/16 21:58
 * @description： 赛事预测
 */
public class MatchesBean extends BaseBean {

    /**
     * total : 0
     * current : 1
     * pages : 0
     * size : 10
     * records : []
     */

    private int total;
    private int current;
    private int pages;
    private int size;
    private List<?> records;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<?> getRecords() {
        return records;
    }

    public void setRecords(List<?> records) {
        this.records = records;
    }
}
