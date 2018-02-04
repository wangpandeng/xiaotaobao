package com.dataoke.client.ui.delivery.presenter;

import com.dataoke.client.constant.JsonConstant;
import com.dataoke.client.model.response.CommonGoodsResponse;
import com.dataoke.client.ui.base.BasePresenter;
import com.dataoke.client.ui.delivery.contract.DetailFreeDeliveryContract;
import com.dataoke.client.utils.JsonUtils;

/**
 * @author ddllxy
 * @date 2018/1/29 0029
 */

public class DetailFreeDeliveryPresenter extends BasePresenter<DetailFreeDeliveryContract.IView> implements DetailFreeDeliveryContract.IPresenter {

    public static DetailFreeDeliveryPresenter newInstance() {
        return new DetailFreeDeliveryPresenter();
    }

    @Override
    public void getData() {
        CommonGoodsResponse commonGoodsResponse = JsonUtils.jsonStrToObject(JsonConstant.MAIN_COMMODITY_JSON, CommonGoodsResponse.class);
        mView.setCommodityData(commonGoodsResponse);
    }

    @Override
    public void getMoreData() {
        CommonGoodsResponse commonGoodsResponse = JsonUtils.jsonStrToObject(JsonConstant.MAIN_COMMODITY_JSON, CommonGoodsResponse.class);
        mView.setMoreCommodityData(commonGoodsResponse);
    }
}
