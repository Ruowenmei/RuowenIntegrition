package com.ruowen.ruowendemo.project.retrofit.api;

import com.ruowen.ruowendemo.project.retrofit.bean.UserInfoBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Date : 2016/4/26
 * Author : ruowen
 * Description : retrofit2.0定义接口的方式，同步调用接口和异步调用接口定义形式一样；1.x时，接口形式不一样
 */
public interface UserInfo {
    @GET("User/{user}")//是否以斜线开始，对URL解析有影响
    Call<UserInfoBean> getUserInfo(@Path("user") String user);
}
