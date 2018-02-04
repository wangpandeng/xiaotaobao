package com.dataoke.client.utils;

import com.orhanobut.logger.Logger;

/**
 * Log统一管理类
 *
 * @author ddllxy
 */
public class LogUtil {

    private LogUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    // 下面四个是默认tag的函数
    public static void i(String msg) {
        Logger.i(msg);
    }

    public static void d(String msg) {
        Logger.d(msg);
    }

    public static void e(String msg) {
        Logger.e(msg);
    }

    public static void v(String msg) {
        Logger.v(msg);
    }

    public static void json(String json) {
        Logger.json(json);
    }

    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg) {
        Logger.t(tag).i(msg);
    }

    public static void d(String tag, String msg) {
        Logger.t(tag).d(msg);
    }

    public static void e(String tag, String msg) {
        Logger.t(tag).e(msg);
    }

    public static void v(String tag, String msg) {
        Logger.t(tag).v(msg);
    }

    public static void json(String tag, String json) {
        Logger.t(tag).json(json);
    }
}