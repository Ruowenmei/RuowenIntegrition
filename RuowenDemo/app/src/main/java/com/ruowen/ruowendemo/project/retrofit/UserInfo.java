package com.ruowen.ruowendemo.project.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Date : 2016/4/26
 * Author : ruowen
 * Description :
 */
public interface UserInfo {
    @GET("User/{user}")
    Call<UserInfoBean> getUserInfo(@Path("user") String user);
}
