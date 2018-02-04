package com.dataoke.client.ui.detail.presenter;

import com.dataoke.client.constant.JsonConstant;
import com.dataoke.client.model.response.DetailResponse;
import com.dataoke.client.model.response.CommonGoodsResponse;
import com.dataoke.client.ui.base.BasePresenter;
import com.dataoke.client.ui.detail.contract.DetailContract;
import com.dataoke.client.utils.JsonUtils;

/**
 * @author ddllxy
 * @date 2018/1/31 0031
 */

public class DetailPresenter extends BasePresenter<DetailContract.IView> implements DetailContract.IPresenter {


    @Override
    public void getDetailInfo() {
        DetailResponse detailResponse = JsonUtils.jsonStrToObject(JsonConstant.DETAIL_JSON, DetailResponse.class);
        mView.setDetailInfo(detailResponse);
    }

    @Override
    public void getRecommendData() {
        CommonGoodsResponse response = JsonUtils.jsonStrToObject(JsonConstant.MAIN_COMMODITY_JSON, CommonGoodsResponse.class);
        mView.setRecommendData(response);
    }
}
