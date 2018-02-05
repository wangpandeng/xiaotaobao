package com.dataoke.client.ui.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.dataoke.client.R;
import com.dataoke.client.utils.BaseHandler;
import com.dataoke.client.utils.StatusBarUtil;
import com.dataoke.client.utils.ToastUtil;
import com.dataoke.client.utils.load.LoadService;
import com.dataoke.client.utils.load.callback.ErrorCallback;
import com.dataoke.client.utils.load.callback.LoadingCallback;
import com.dataoke.client.utils.load.callback.NoNetCallBack;
import com.trello.rxlifecycle2.LifecycleTransformer;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author ddllxy
 * @date 2018/1/22 0022
 */

public abstract class BaseActivity<P extends BasePresenter> extends BaseSupportActivity implements IBaseContract.IBaseView {
    private Unbinder unbinder;
    public P mPresenter;
    protected LoadService mBaseLoadService;
    protected CommonHandler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new CommonHandler(this);
        //baseActivity统一设置竖直屏幕
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
//        setStatusBar();
        if (mPresenter == null) {
            mPresenter = (P) initPresenter();
            if (mPresenter != null) {
                mPresenter.attachView(this);
            }
        }
        onActivityCreate(savedInstanceState);
    }

    /**
     * 开始操作activity
     *
     * @param savedInstanceState
     */
    protected abstract void onActivityCreate(Bundle savedInstanceState);

    @Override
    protected void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }

    public void setStatusBar() {
        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.colorPrimary));
    }

    @Override
    public void onBackPressedSupport() {
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        super.onBackPressedSupport();
    }

    public abstract int getLayoutId();

    @Override
    public void showToast(String message) {
        ToastUtil.show(message);
    }

    @Override
    public void showLoading() {
        if (mBaseLoadService != null) {
            mBaseLoadService.showCallback(LoadingCallback.class);
        }
    }

    @Override
    public void showSuccess() {
        if (mBaseLoadService != null) {
            new Handler(getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    mBaseLoadService.showSuccess();
                }
            }, 1000);
        }
    }

    @Override
    public void showFailed() {
        if (mBaseLoadService != null) {
            mBaseLoadService.showCallback(ErrorCallback.class);
        }
    }

    @Override
    public void showNoNet() {
        if (mBaseLoadService != null) {
            mBaseLoadService.showCallback(NoNetCallBack.class);
        }
    }

    @Override
    public void onRetry() {

    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return null;
    }

    protected static class CommonHandler extends BaseHandler<BaseActivity> {

        public CommonHandler(BaseActivity reference) {
            super(reference);
        }

        @Override
        protected void handleMessage(BaseActivity reference, Message msg) {
            reference.handleMessage(reference, msg);
        }
    }

    protected void handleMessage(BaseActivity reference, Message msg) {

    }
}
