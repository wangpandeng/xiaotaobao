package com.dataoke.client.ui.detail.contract;

import com.dataoke.client.model.response.DetailResponse;
import com.dataoke.client.model.response.CommonGoodsResponse;
import com.dataoke.client.ui.base.IBaseContract;

/**
 * @author ddllxy
 * @date 2018/1/30 0030
 */

public interface DetailContract {
    public interface IView extends IBaseContract.IBaseView {
        /**
         * 设置推荐信息
         */
        void setRecommendData( CommonGoodsResponse response );

        /**
         * 设置商品详情页信息
         */
        void setDetailInfo( DetailResponse detailResponse );
    }

    public interface IPresenter extends IBaseContract.IBasePresenter<IView> {
        void getDetailInfo();

        void getRecommendData();
    }
}
