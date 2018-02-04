package com.dataoke.client.utils;

import android.content.Context;
import android.util.TypedValue;

import com.dataoke.client.MyApplication;

/**
 * 常用单位转换的辅助类
 */
public class DensityUtil {

    private static Context context = MyApplication.sApplication;

    /**
     * 根据手机分辨率从dp转成px
     *
     * @param dpValue
     * @return
     */
    public static int dip2px(float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f) - 15;
    }

    /**
     * sp转px
     *
     * @param spVal
     * @return
     */
    public static int sp2px(float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal, context.getResources().getDisplayMetrics());
    }

    /**
     * px转sp
     *
     * @param pxVal
     * @return
     */
    public static float px2sp(float pxVal) {
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity + 0.5f);
    }
}
