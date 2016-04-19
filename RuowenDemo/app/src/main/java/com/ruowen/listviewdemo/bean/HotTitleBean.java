package com.ruowen.listviewdemo.bean;

import com.ruowen.listviewdemo.bean.base.BaseBean;
import com.ruowen.listviewdemo.bean.base.TypeEnum;

import java.io.Serializable;

/**
 * Created by ruowen on 2016/4/15.
 */
public class HotTitleBean implements BaseBean, Serializable {
    private int type;
    private String hotTitle;

    public HotTitleBean(int type, String hotTitle){
        this.type = type;
        this.hotTitle = hotTitle;
    }

    @Override
    public int getType() {
        return type;
    }

    public String getHotTitle() {
        return hotTitle;
    }
}
