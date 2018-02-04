package com.dataoke.client.ui.home.presenter;

import com.dataoke.client.constant.JsonConstant;
import com.dataoke.client.model.response.CommonGoodsResponse;
import com.dataoke.client.ui.base.BasePresenter;
import com.dataoke.client.ui.home.contract.OtherCommodityContract;
import com.dataoke.client.utils.JsonUtils;

/**
 * @author ddllxy
 * @date 2018/1/29 0029
 */

public class OtherCommodityPresenter extends BasePresenter<OtherCommodityContract.IView> implements OtherCommodityContract.IPresenter {

    public static OtherCommodityPresenter newInstance() {
        return new OtherCommodityPresenter();
    }

    @Override
    public void getOtherCommodityData() {
        CommonGoodsResponse commonGoodsResponse = JsonUtils.jsonStrToObject(JsonConstant.MAIN_COMMODITY_JSON, CommonGoodsResponse.class);
        mView.setOtherCommodityData(commonGoodsResponse);
    }

    @Override
    public void getMoreOtherCommodityData() {
        CommonGoodsResponse commonGoodsResponse = JsonUtils.jsonStrToObject(JsonConstant.MAIN_COMMODITY_JSON, CommonGoodsResponse.class);
        mView.setMoreOtherCommodityData(commonGoodsResponse);
    }
}
