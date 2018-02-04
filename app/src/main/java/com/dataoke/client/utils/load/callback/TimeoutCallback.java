package com.dataoke.client.utils.load.callback;

import android.content.Context;
import android.view.View;

import com.dataoke.client.R;
import com.dataoke.client.utils.load.Callback;

/**
 * Description:TODO
 * Create Time:2017/9/2 16:17
 * Author:KingJA
 * Email:kingjavip@gmail.com
 *
 * @author Administrator
 */

public class TimeoutCallback extends Callback {

    @Override
    protected int onCreateView() {
        return R.layout.layout_timeout;
    }

    @Override
    protected boolean onReloadEvent(Context context, View view) {
        return false;
    }

}
