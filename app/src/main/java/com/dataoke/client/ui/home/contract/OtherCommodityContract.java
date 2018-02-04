package com.dataoke.client.ui.home.contract;

import com.dataoke.client.model.response.CommonGoodsResponse;
import com.dataoke.client.ui.base.IBaseContract;

/**
 * @author ddllxy
 * @date 2018/1/29 0029
 */

public class OtherCommodityContract {
    public interface IView extends IBaseContract.IBaseView {
        /**
         * 设置数据
         *
         * @param data
         */
        void setOtherCommodityData(CommonGoodsResponse data);

        /**
         * 上拉加载更多数据
         *
         * @param data
         */
        void setMoreOtherCommodityData(CommonGoodsResponse data);
    }

    public interface IPresenter extends IBaseContract.IBasePresenter<IView> {
        /**
         * 获取数据列表页
         */
        void getOtherCommodityData();

        /**
         * 获取更多的数据
         */

        void getMoreOtherCommodityData();
    }
}
