package com.ruowen.ruowendemo.project.retrofit;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.ruowen.ruowendemo.R;
import com.ruowen.ruowendemo.common.BaseActivity;
import com.ruowen.ruowendemo.project.MyApplication;
import com.ruowen.ruowendemo.project.config.URLConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Date : 2016.04.19
 * Author : created by ruowen
 * Description : 网络请求库
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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLConfig.HOST_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final UserInfo userInfo = retrofit.create(UserInfo.class);
        Call<UserInfoBean> call = userInfo.getUserInfo("user.txt");
        call.enqueue(new Callback<UserInfoBean>() {
            @Override
            public void onResponse(Call<UserInfoBean> call, Response<UserInfoBean> response) {
                Log.d(MyApplication.TAG, "ruowen>>>>>>>>>>"+response.body());
                tvUser.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<UserInfoBean> call, Throwable t) {
                Log.d(MyApplication.TAG, "failure ruowen>>>>>>>>>>"+t.getMessage());
                tvUser.setText("failure");
            }
        });
    }
}
