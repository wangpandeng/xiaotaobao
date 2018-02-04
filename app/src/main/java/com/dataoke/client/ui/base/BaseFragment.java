package com.dataoke.client.ui.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public abstract class BaseFragment<P extends BasePresenter> extends BaseSupportFragment implements IBaseContract.IBaseView {

    private Unbinder unbinder;
    public P mPresenter;
    protected LoadService mBaseLoadService;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mPresenter == null) {
            mPresenter = (P) initPresenter();
            if (mPresenter != null) {
                mPresenter.attachView(this);
            }
        }
        onFragmentCreate(view, savedInstanceState);
    }


    /**
     * 开始操作fragment
     *
     * @param view
     * @param savedInstanceState
     */
    public abstract void onFragmentCreate(View view, Bundle savedInstanceState);

    @Override
    public void onDestroyView() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroyView();
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
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
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
}
