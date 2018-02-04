package com.dataoke.client.ui.home.contract;

import com.dataoke.client.model.response.BannerResponse;
import com.dataoke.client.model.response.CaseResponse;
import com.dataoke.client.model.response.HotGoodsResponse;
import com.dataoke.client.model.response.CommonGoodsResponse;
import com.dataoke.client.ui.base.IBaseContract;

/**
 * @author ddllxy
 * @date 2018/1/24 0024
 */

public interface NewCommodityContract {
    public interface IView extends IBaseContract.IBaseView {
        /**
         * 设置banner
         *
         * @param banner
         */
        void setBanner(BannerResponse banner);

        /**
         * 设置case橱窗
         *
         * @param caseData
         */
        void setCaseData(CaseResponse caseData);

        /**
         * 设置热卖商品
         *
         * @param hotCommodityData
         */
        void setHotCommodityData(HotGoodsResponse hotCommodityData);

        /**
         * 首页商品列表
         *
         * @param mainCommodityData
         */
        void setMainCommodityData(CommonGoodsResponse mainCommodityData);

        /**
         * 设置更多的首页商品列表数据
         *
         * @param mainCommodityData
         */
        void setMoreMainCommodityData(CommonGoodsResponse mainCommodityData);
    }

    public interface IPresenter extends IBaseContract.IBasePresenter<IView> {
        /**
         * 获取banner数据
         */
        void getBannerData();

        /**
         * 获取case数据
         */
        void getCaseData();

        /**
         * 获取热卖商品
         */
        void getHotCommodityData();

        /**
         * 获取首页主要商品列表
         */
        void getMainCommodityData();

        /**
         * 获取更多首页商品
         */
        void getMoreMainCommodityData();
    }
}
