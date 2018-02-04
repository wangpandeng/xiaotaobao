package com.dataoke.client.ui.base;

import android.support.annotation.NonNull;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * desc:
 * author: Will .
 * date: 2017/9/2 .
 */
public interface IBaseContract {

    interface IBasePresenter<T extends IBaseView> {

        void attachView(T view);

        void detachView();
    }


    interface IBaseView {
        @NonNull
        BasePresenter initPresenter();

        void showToast(String message);

        //显示进度中
        void showLoading();

        //显示请求成功
        void showSuccess();

        //失败重试
        void showFailed();

        //显示当前网络不可用
        void showNoNet();

        //重试
        void onRetry();

        /**
         * 绑定生命周期
         *
         * @param <T>
         * @return
         */
        <T> LifecycleTransformer<T> bindToLife();

    }
}
