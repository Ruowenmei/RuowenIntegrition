package com.ruowen.ruowendemo.project.retrofit;

import com.ruowen.ruowendemo.project.config.BaseConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Date : 2016/4/26
 * Author : ruowen
 * Description :
 */
public class AddHeaderInterceptor {
    public static Interceptor TokenInterceptor = new Interceptor() {
            @Override public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                if (null == BaseConfig.TOKEN || alreadyHasAuthorizationHeader(originalRequest)) {
                    return chain.proceed(originalRequest);
                }
                Request authorised = originalRequest.newBuilder()
                        .header("Authorization", BaseConfig.TOKEN)
                        .build();
                return chain.proceed(authorised);
            }
        };

    public static boolean alreadyHasAuthorizationHeader(Request originalRequest){
        if (null != originalRequest.header("Authorization")){
            return true;
        }

        return false;
    }
}
