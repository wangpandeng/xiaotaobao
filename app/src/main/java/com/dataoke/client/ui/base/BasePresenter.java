package com.dataoke.client.ui.base;

/**
 * @author ddllxy
 * @date 2018/1/22 0022
 */

public class BasePresenter<T extends IBaseContract.IBaseView> implements IBaseContract.IBasePresenter<T> {

    public T mView;

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        if (mView != null) {
            mView = null;
        }
    }
}
