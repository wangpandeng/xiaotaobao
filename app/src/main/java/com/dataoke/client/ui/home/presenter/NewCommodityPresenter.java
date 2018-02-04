package com.dataoke.client.ui.home.presenter;

import com.dataoke.client.constant.JsonConstant;
import com.dataoke.client.model.response.BannerResponse;
import com.dataoke.client.model.response.CaseResponse;
import com.dataoke.client.model.response.HotGoodsResponse;
import com.dataoke.client.model.response.CommonGoodsResponse;
import com.dataoke.client.ui.base.BasePresenter;
import com.dataoke.client.ui.home.contract.NewCommodityContract;
import com.dataoke.client.utils.JsonUtils;

/**
 * banner的P
 *
 * @author ddllxy
 * @date 2018/1/24 0024
 */

public class NewCommodityPresenter extends BasePresenter<NewCommodityContract.IView> implements NewCommodityContract.IPresenter {

    public static NewCommodityPresenter newInstance() {
        return new NewCommodityPresenter();
    }

    @Override
    public void getBannerData() {
        BannerResponse bannerResponse = JsonUtils.jsonStrToObject(JsonConstant.BANNER_JSON, BannerResponse.class);
        mView.setBanner(bannerResponse);
    }

    @Override
    public void getCaseData() {
        CaseResponse caseResponse = JsonUtils.jsonStrToObject(JsonConstant.CASE_JSON, CaseResponse.class);
        mView.setCaseData(caseResponse);
    }

    @Override
    public void getHotCommodityData() {
        HotGoodsResponse hotGoodsResponse = JsonUtils.jsonStrToObject(JsonConstant.HOT_COMMODITY_JSON, HotGoodsResponse.class);
        mView.setHotCommodityData(hotGoodsResponse);
    }

    @Override
    public void getMainCommodityData() {
        CommonGoodsResponse commonGoodsResponse = JsonUtils.jsonStrToObject(JsonConstant.MAIN_COMMODITY_JSON, CommonGoodsResponse.class);
        mView.setMainCommodityData(commonGoodsResponse);
    }

    @Override
    public void getMoreMainCommodityData() {
        CommonGoodsResponse commonGoodsResponse = JsonUtils.jsonStrToObject(JsonConstant.MAIN_COMMODITY_JSON, CommonGoodsResponse.class);
        mView.setMoreMainCommodityData(commonGoodsResponse);
    }

}
