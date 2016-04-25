package com.ruowen.ruowendemo.project;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by ruowen on 2016/4/19.
 */
public class MyApplication extends Application {

    public static final String TAG = "ruowen";

    @Override
    public void onCreate() {
        super.onCreate();

        LeakCanary.install(this);
    }
}
