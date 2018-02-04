package com.dataoke.client.ui.home.contract;

import com.dataoke.client.ui.base.IBaseContract;

/**
 * @author ddllxy
 * @date 2018/1/23 0023
 */

public interface HomeContract {
    interface IPresenter extends IBaseContract.IBasePresenter<IView> {
        //获取列表数据
        void getGoodsList();
    }

    interface IView extends IBaseContract.IBaseView {

    }
}
