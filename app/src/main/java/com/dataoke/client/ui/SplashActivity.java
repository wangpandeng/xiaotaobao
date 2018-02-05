package com.dataoke.client.ui;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.WindowManager;

import com.dataoke.client.R;
import com.dataoke.client.ui.base.BaseActivity;
import com.dataoke.client.ui.base.BasePresenter;

/**
 * @author ddllxy
 * @date 2018/2/5 0005
 */

public class SplashActivity extends BaseActivity {
    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        mHandler.sendEmptyMessageDelayed(100, 2000);

    }

    @Override
    protected void handleMessage(BaseActivity reference, Message msg) {
        super.handleMessage(reference, msg);
        if (msg.what == 100) {
            MainActivity.startMainActivity(SplashActivity.this);
            finish();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash_layout;
    }
}
