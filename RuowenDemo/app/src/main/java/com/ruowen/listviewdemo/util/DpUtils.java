package com.ruowen.listviewdemo.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by ruowen on 2015/9/8.
 */
public class DpUtils {
    /**
     * dip转px
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px转dip
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * Homepager使用
     * @param res
     * @param dp
     * @return
     */
    public static int dpToPx(Resources res, int dp)
    {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                res.getDisplayMetrics());
    }
}
