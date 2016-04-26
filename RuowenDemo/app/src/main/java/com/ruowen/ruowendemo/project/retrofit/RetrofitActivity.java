package com.ruowen.ruowendemo.project.retrofit;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.ruowen.ruowendemo.R;
import com.ruowen.ruowendemo.common.BaseActivity;
import com.ruowen.ruowendemo.project.MyApplication;
import com.ruowen.ruowendemo.project.config.URLConfig;
import com.ruowen.ruowendemo.project.retrofit.api.UserInfo;
import com.ruowen.ruowendemo.project.retrofit.bean.UserInfoBean;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Date : 2016.04.19
 * Author : created by ruowen
 * Description : 网络请求库
 *              参考： http://www.tuicool.com/articles/fQju2uQ
 */

public class RetrofitActivity extends BaseActivity {

    private TextView tvUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void doBeforeOnCreate() {}

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_retrofit);
    }

    @Override
    protected void initViewById() {
        tvUser = (TextView)findViewById(R.id.tv_user);
    }

    @Override
    protected void setListener() {}

    @Override
    protected void processLogic() {
        //配置okHttp请求设置
        OkHttpClient client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)//失败重试
                .connectTimeout(5, TimeUnit.SECONDS)//超时设置
                .addNetworkInterceptor(AddHeaderInterceptor.TokenInterceptor)//拦截器，让所有的请求都加上token
                .cache(null)//设置缓存
                .build();

        //retrofit配置
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLConfig.HOST_URL)
                .client(client)//设置自定义属性的okHttp，也可以使用默认的配置
                .addConverterFactory(GsonConverterFactory.create())//有多个转换器时，Json解析一定要放在最后，因为无法具体判断，不然会出错
                //.addCallAdapterFactory(RxJavaCallAdapterFactory.create())//使用RxJava代替默认回调Call
                .build();

        //自定义的接口API
        final UserInfo userInfo = retrofit.create(UserInfo.class);

        //一个接口的请求执行
        Call<UserInfoBean> call = userInfo.getUserInfo("user.txt");//一个call实例，只能调用一次，如果想多次调用，可以使用clone

        final Call<UserInfoBean> callSync = call.clone();
        new Thread() {
            @Override
            public void run() {
                try {
                Response<UserInfoBean> response = callSync.execute();//不能在主线程执行
                    Log.d("ruowen", "ruowen>>>>>sync success");
                }catch(IOException e){
                    e.printStackTrace();
                    Log.d("ruowen", "ruowen>>>>>sync exception");
                }
            }
        }.start();

        Call<UserInfoBean> callASync = call.clone();
        callASync.enqueue(new Callback<UserInfoBean>() {//enqueue异步请求，可以cancel
            @Override
            public void onResponse(Call<UserInfoBean> call, Response<UserInfoBean> response) {
                Log.d(MyApplication.TAG, "ruowen>>>>>>>>code="+response.code()
                        +"\nmessage="+response.message()
                        +"\nheader="+response.headers());
                UserInfoBean userInfoBean = response.body();
                tvUser.setText("name : "+userInfoBean.getName()+
                "\nsex : "+userInfoBean.getSex()+
                "\nage : "+userInfoBean.getAge());
            }

            @Override
            public void onFailure(Call<UserInfoBean> call, Throwable t) {
                Log.d(MyApplication.TAG, "failure ruowen>>>>>>>>>>"+t.getMessage());
                tvUser.setText("failure");
            }
        });
    }
}
