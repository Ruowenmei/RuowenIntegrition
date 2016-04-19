package com.ruowen.listviewdemo.bean;

import com.ruowen.listviewdemo.bean.base.BaseBean;

import java.io.Serializable;

/**
 * Created by ruowen on 2016/4/15.
 */
public class TopTitleBean implements BaseBean, Serializable {
    private int type;
    private String topTitle;

    public TopTitleBean(int type, String topTitle){
        this.type = type;
        this.topTitle = topTitle;
    }

    @Override
    public int getType() {
        return type;
    }

    public String getTopTitle() {
        return topTitle;
    }
}
