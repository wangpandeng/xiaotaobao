package com.dataoke.client.ui.home.contract;

import com.dataoke.client.model.response.CommonGoodsResponse;
import com.dataoke.client.model.response.SearchHotTagResponse;
import com.dataoke.client.ui.base.IBaseContract;

/**
 * @author ddllxy
 * @date 2018/2/2 0002
 */

public class SearchContract {
    public interface IView extends IBaseContract.IBaseView {
        void setHotSearchTagData(SearchHotTagResponse searchHotTagResponse);

        void setSearchData(CommonGoodsResponse searchData);

        void setMoreSearchData(CommonGoodsResponse searchData);
    }

    public interface IPresenter extends IBaseContract.IBasePresenter<IView> {
        void getHotSearchTagData();

        void getSearchData();

        void getMoreSearchData();
    }
}
