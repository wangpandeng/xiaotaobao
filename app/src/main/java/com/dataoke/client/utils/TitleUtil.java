package com.dataoke.client.utils;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dataoke.client.R;

/**
 * Created by wpd on 2018/2/3.
 */

public class TitleUtil {
    public static void setTitle(Activity activity, String title) {
        if (activity == null) {
            throw new NullPointerException("activity is null");
        }
        TextView textView = activity.findViewById(R.id.tv_title);
        if (textView != null) {
            textView.setText(title);
        }
    }

    public static void setTitleColor(Activity activity, int colorId) {
        if (activity == null) {
            throw new NullPointerException("activity is null");
        }
        TextView textView = activity.findViewById(R.id.tv_title);
        if (textView != null) {
            textView.setTextColor(ContextCompat.getColor(activity, colorId));
        }
    }

    public static void setTitleBackListener(Activity activity) {
        if (activity == null) {
            throw new NullPointerException("activity is null");
        }
        ImageView ivBack = activity.findViewById(R.id.iv_back);
        if (ivBack != null) {
            ivBack.setOnClickListener(view -> activity.onBackPressed());
        }
    }

    public static void setRightImageVisible(Activity activity, boolean isShow) {
        if (activity == null) {
            throw new NullPointerException("activity is null");
        }
        ImageView imageRight = activity.findViewById(R.id.iv_right);
        if (imageRight != null) {
            imageRight.setVisibility(isShow ? View.VISIBLE : View.GONE);
        }
    }

    public static void setDividerLineVisible(Activity activity, boolean isShow) {
        if (activity == null) {
            throw new NullPointerException("activity is null");
        }
        View dividerLineView = activity.findViewById(R.id.view_divider_line);
        if (dividerLineView != null) {
            dividerLineView.setVisibility(isShow ? View.VISIBLE : View.GONE);
        }
    }
}
