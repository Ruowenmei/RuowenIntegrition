package com.ruowen.ruowendemo.multitypelistview.bean;

import com.ruowen.ruowendemo.multitypelistview.bean.base.BaseBean;

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
