package com.ruowen.ruowendemo.common.util;

import android.content.Context;
import android.os.Handler;
import android.util.DisplayMetrics;

/**
 * author: kevinma
 * created on: 2017/3/14 下午2:34
 * description:
 */
public class GlobalData {

    private static Context sApplication;

    private static Handler sGlobalUIHandler;

    private static float sScreenRate = 0;
    private static float sScreenDensity = 0;
    private static int sScreenWidth = 0;
    private static int sScreenHeight = 0;
    private static boolean sIsCoreProcess = false;
    private static boolean sIsDebuggable = true;

    public static void setIsDebuggable(boolean debuggable) {
        sIsDebuggable = debuggable;
    }

    public static boolean isDebuggable() {
        return sIsDebuggable;
    }

    public static boolean isCoreProcess() {
        return sIsCoreProcess;
    }

    public static void setIsCoreProcess(boolean isCoreProcess) {
        sIsCoreProcess = isCoreProcess;
    }

    public static Handler getGlobalUIHandler() {
        return sGlobalUIHandler;
    }


    public static float getScreenRate() {
        return sScreenRate;
    }

    public static float getScreenDensity() {
        return sScreenDensity;
    }

    public static int getScreenWidth() {
        return sScreenWidth;
    }

    public static int getScreenHeight() {
        return sScreenHeight;
    }

    public static Context app() {
        return sApplication;
    }

    public static void initialize(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Ary you kidding me ? context is null");
        }
        sApplication = context.getApplicationContext();
        if (sGlobalUIHandler == null) {
            sGlobalUIHandler = new Handler();
        }
        calculateScreenRate(sApplication);
    }

    private static void calculateScreenRate(Context context) {
        if(context != null && context.getResources() != null) {
            DisplayMetrics screenMatrix = context.getResources().getDisplayMetrics();
            sScreenWidth = screenMatrix.widthPixels;
            sScreenHeight = screenMatrix.heightPixels;
            sScreenRate = (float) screenMatrix.densityDpi / (float) DisplayMetrics.DENSITY_HIGH;
            sScreenDensity = screenMatrix.density;
            if (sScreenWidth > sScreenHeight) {
                int temp = sScreenHeight;
                sScreenHeight = sScreenWidth;
                sScreenWidth = temp;
            }
        }
    }
}
