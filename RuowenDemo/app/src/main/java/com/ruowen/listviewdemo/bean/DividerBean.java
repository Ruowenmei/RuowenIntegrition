package com.ruowen.listviewdemo.bean;

import com.ruowen.listviewdemo.bean.base.BaseBean;

import java.io.Serializable;

/**
 * Created by ruowen on 2016/4/15.
 */
public class DividerBean implements BaseBean, Serializable {
    private int type;

    public DividerBean(int type){
        this.type = type;
    }

    @Override
    public int getType() {
        return type;
    }
}
