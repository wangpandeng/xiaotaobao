package com.dataoke.client.utils.load;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Description:TODO
 * Create Time:2017/9/4 16:28
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class TargetContext {
    private Context context;
    private ViewGroup parentView;
    private View oldContentView;
    private int childIndex;

    public TargetContext(Context context, ViewGroup parentView, View oldContentView, int childIndex) {
        this.context = context;
        this.parentView = parentView;
        this.oldContentView = oldContentView;
        this.childIndex = childIndex;
    }

    public Context getContext() {
        return context;
    }

    View getOldContentView() {
        return oldContentView;
    }

    int getChildIndex() {
        return childIndex;
    }

    ViewGroup getParentView() {
        return parentView;
    }
}
