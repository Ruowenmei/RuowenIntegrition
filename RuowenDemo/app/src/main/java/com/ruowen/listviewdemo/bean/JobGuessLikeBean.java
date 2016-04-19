package com.ruowen.listviewdemo.bean;

import com.ruowen.listviewdemo.bean.base.BaseBean;
import com.ruowen.listviewdemo.bean.base.TypeEnum;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ruowen on 2016/4/6.
 */
public class JobGuessLikeBean implements BaseBean, Serializable {

    private int type;
    private String title;
    private String label;
    private String location;
    private List<String> recReason;
    private List<String> welfares;

    public JobGuessLikeBean(int type, String title, String location, String salary
            , List<String> recReason, List<String> welfares){
        this.type = type;
        this.title = title;
        this.location = location;
        this.label = salary;
        this.recReason = recReason;
        this.welfares = welfares;

    }

    public int getType() {
        return type;
    }

    public List<String> getRecReason() {
        return recReason;
    }

    public List<String> getWelfares() {
        return welfares;
    }

    public String getTitle() {
        return title;
    }

    public String getLabel() {
        return label;
    }

    public String getLocation() {
        return location;
    }
}
