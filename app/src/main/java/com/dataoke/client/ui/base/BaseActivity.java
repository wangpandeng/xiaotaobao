package com.dataoke.client.ui.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;

import com.dataoke.client.R;
import com.dataoke.client.utils.StatusBarUtil;
import com.dataoke.client.utils.ToastUtil;
import com.dataoke.client.utils.load.LoadService;
import com.dataoke.client.utils.load.callback.ErrorCallback;
import com.dataoke.client.utils.load.callback.LoadingCallback;
import com.dataoke.client.utils.load.callback.NoNetCallBack;
import com.trello.rxlifecycle2.LifecycleTransformer;

import butterknife.BindView;
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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            },1000);
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
}
