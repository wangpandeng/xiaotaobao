package com.dataoke.client;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.alibaba.baichuan.android.trade.AlibcTradeSDK;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeInitCallback;
import com.dataoke.client.utils.LogUtil;
import com.dataoke.client.utils.load.LoadSir;
import com.dataoke.client.utils.load.callback.EmptyCallback;
import com.dataoke.client.utils.load.callback.ErrorCallback;
import com.dataoke.client.utils.load.callback.LoadingCallback;
import com.dataoke.client.utils.load.callback.TimeoutCallback;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.squareup.leakcanary.LeakCanary;

import java.util.LinkedList;

/**
 * @author ddllxy
 * @date 2018/1/18 0018
 */

public class MyApplication extends Application implements Application.ActivityLifecycleCallbacks {
    public static MyApplication sApplication;
    public static LinkedList<Activity> activities;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
        // 启用EventBus3.0加速功能
//        EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus();
        setupLeakCanary();
        initLogger();
        activities = new LinkedList<>();
        LoadSir.newBuilder()
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new ErrorCallback())
                .addCallback(new EmptyCallback())
                .setDefaultCallback(LoadingCallback.class)
                .commit();
        AlibcTradeSDK.asyncInit(this, new AlibcTradeInitCallback() {
            @Override
            public void onSuccess() {
                //初始化成功，设置相关的全局配置参数
                LogUtil.i("alibabaInit", "onSuccess");
                // ...
            }

            @Override
            public void onFailure(int code, String msg) {
                //初始化失败，可以根据code和msg判断失败原因，详情参见错误说明
                LogUtil.i("alibabaInit", "onFailure：" + code + ":>>>" + msg);
            }
        });
    }

    private void initLogger() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .tag("DaTaoKe")   // Default PRETTY_LOGGER
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }

    /**
     * 初始化内存泄露
     */
    protected void setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        enabledStrictMode();
        LeakCanary.install(this);
    }

    private static void enabledStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder() //
                .detectAll() //
                .penaltyLog() //
                .penaltyDeath() //
                .build());
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        activities.add(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        activities.remove(activity);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        unregisterActivityLifecycleCallbacks(this);
    }
}
