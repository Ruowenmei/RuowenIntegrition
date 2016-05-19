package com.ruowen.ruowendemo.project.retrofitandrxjava;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.ruowen.ruowendemo.R;
import com.ruowen.ruowendemo.common.BaseActivity;
import com.ruowen.ruowendemo.project.MyApplication;
import com.ruowen.ruowendemo.project.config.URLConfig;
import com.ruowen.ruowendemo.project.retrofit.AddHeaderInterceptor;
import com.ruowen.ruowendemo.project.retrofitandrxjava.api.UserInfo;
import com.ruowen.ruowendemo.project.retrofitandrxjava.bean.UserInfoBean;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Date : 2016.04.19
 * Author : created by ruowen
 * Description : Retrofit 和 RxJava配合使用，在处理逻辑复杂时，整体代码逻辑可以非常清爽，逻辑简单，
 *              是使用Retrofit配合RxJava最大的理由
 */

public class RetrofitAndRxJavaActivity extends BaseActivity {

    private TextView tvUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void doBeforeOnCreate() {}

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_retrofit_and_rx_java);
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
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//使用RxJava代替默认回调Call
                .build();

        //自定义的接口API
        final UserInfo userInfo = retrofit.create(UserInfo.class);

        //一个接口的请求执行
        Observable<UserInfoBean> call = userInfo.getUserInfo("user.txt");//一个call实例，只能调用一次，如果想多次调用，可以使用clone
        call.map(new Func1<UserInfoBean, String>() {//转换
                @Override
                public String call(UserInfoBean userInfoBean) {
                    return userInfoBean.getName() + "   map success";
                }
            })
            .subscribeOn(Schedulers.io())//设置网络请求线程
            .observeOn(AndroidSchedulers.mainThread())//设置subscriber执行线程
            .unsubscribeOn(Schedulers.io())//防止call.cancel()时，处理异常
            .subscribe(new Subscriber<String>() {
                @Override
                public void onNext(String s) {
                    tvUser.setText(s);
                    Log.d(MyApplication.TAG, "ruowen>>>>>"+s);
                }

                @Override
                public void onCompleted() {
                    Log.d(MyApplication.TAG, "ruowen>>>>>onCompleted");
                }

                @Override
                public void onError(Throwable e) {
                    Log.d(MyApplication.TAG, "ruowen>>>>>onError   "+e.getMessage());
                }
            });
    }
}
