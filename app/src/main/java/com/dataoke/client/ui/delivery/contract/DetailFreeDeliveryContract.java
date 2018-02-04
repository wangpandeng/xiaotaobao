package com.dataoke.client.ui.delivery.contract;

import com.dataoke.client.model.response.CommonGoodsResponse;
import com.dataoke.client.ui.base.IBaseContract;

/**
 * @author ddllxy
 * @date 2018/1/29 0029
 */

public class DetailFreeDeliveryContract {
    public interface IView extends IBaseContract.IBaseView {
        /**
         * 设置包邮的数据
         *
         * @param commonGoodsResponse
         */
        void setCommodityData(CommonGoodsResponse commonGoodsResponse);

        /**
         * 设置下拉加载数据
         *
         * @param commonGoodsResponse
         */
        void setMoreCommodityData(CommonGoodsResponse commonGoodsResponse);
    }

    public interface IPresenter extends IBaseContract.IBasePresenter<IView> {
        /**
         * 获取包邮的数据
         */
        void getData();

        /**
         * 下拉加载数据
         */
        void getMoreData();
    }
}
