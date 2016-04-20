package com.ruowen.ruowendemo;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by ruowen on 2016/4/19.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        LeakCanary.install(this);
    }
}
