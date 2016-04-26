package com.ruowen.ruowendemo.project.retrofit;

import java.io.Serializable;

/**
 * Date : 2016/4/26
 * Author : ruowen
 * Description :
 */
public class UserInfoBean implements Serializable{
    public String name;
    public String sex;
    public int age;

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }
}
