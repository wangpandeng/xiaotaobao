package com.dataoke.client.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dataoke.client.MyApplication;
import com.dataoke.client.R;


/**
 * 吐司提示工具类
 *
 * @author ddllxy
 */
public class ToastUtil {

    private static Toast mToast;
    private static Context mContext = MyApplication.sApplication;
    private static final View v;
    private static final TextView textView;

    /**
     * 只加载一次，不用每次显示toast的时候加载布局
     */
    static {
        v = LayoutInflater.from(mContext).inflate(R.layout.toast, null);
        textView = (TextView) v.findViewById(R.id.tv_msg);
    }

    /**
     * 显示一个居于界面正中间显示的Toast(LENGTH_SHORT)
     *
     * @param message 要显示的文字
     */
    public static void show(String message) {
        show(message, Toast.LENGTH_SHORT);
    }

    /**
     * 显示一个居于界面正中间显示的Toast(LENGTH_SHORT)
     *
     * @param resId 要显示的文字
     */
    public static void show(int resId) {
        if (resId != 0) {
            show(mContext.getString(resId), Toast.LENGTH_SHORT);
        }
    }

    /**
     * 显示一个居于界面正中间显示的Toast
     *
     * @param message  要显示的文字
     * @param duration 显示的时间长度
     */
    public static void show(String message, int duration) {
        try {
            hideToast();
            textView.setText(message);
            mToast = new Toast(mContext);
            mToast.setDuration(duration);
            mToast.setView(v);
            mToast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭一个正在显示的Toast
     */
    private static void hideToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
}
