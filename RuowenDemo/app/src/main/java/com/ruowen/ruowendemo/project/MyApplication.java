package com.ruowen.ruowendemo.project;

import android.app.Application;
import android.content.Context;

import com.ruowen.ruowendemo.project.camera.DisplayUtil;
import com.ruowen.ruowendemo.project.hook.HookUtils;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by ruowen on 2016/4/19.
 */
public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        LeakCanary.install(this);

        //DisplayUtil.init(this);

        HookUtils hookUtils = new HookUtils(this);
        hookUtils.hookSystemHandler();
        hookUtils.hookAms();
        hookUtils.hookNavUtils();
    }

    public static Context getAppContext(){
        return context;
    }
}
