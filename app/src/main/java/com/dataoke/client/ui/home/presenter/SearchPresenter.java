package com.dataoke.client.ui.home.presenter;

import com.dataoke.client.constant.JsonConstant;
import com.dataoke.client.model.response.CommonGoodsResponse;
import com.dataoke.client.model.response.SearchHotTagResponse;
import com.dataoke.client.ui.base.BasePresenter;
import com.dataoke.client.ui.home.contract.SearchContract;
import com.dataoke.client.utils.JsonUtils;

/**
 * @author ddllxy
 * @date 2018/2/2 0002
 */

public class SearchPresenter extends BasePresenter<SearchContract.IView> implements SearchContract.IPresenter {
    public static SearchPresenter newInstance() {
        return new SearchPresenter();
    }

    @Override
    public void getHotSearchTagData() {
        SearchHotTagResponse searchHotTagResponse = JsonUtils.jsonStrToObject(JsonConstant.HOT_TAG_JSON, SearchHotTagResponse.class);
        mView.setHotSearchTagData(searchHotTagResponse);
        mView.showSuccess();
    }

    @Override
    public void getSearchData() {
        CommonGoodsResponse commonGoodsResponse = JsonUtils.jsonStrToObject(JsonConstant.MAIN_COMMODITY_JSON, CommonGoodsResponse.class);
        mView.setSearchData(commonGoodsResponse);
    }

    @Override
    public void getMoreSearchData() {
        CommonGoodsResponse commonGoodsResponse = JsonUtils.jsonStrToObject(JsonConstant.MAIN_COMMODITY_JSON, CommonGoodsResponse.class);
        mView.setMoreSearchData(commonGoodsResponse);
    }
}
