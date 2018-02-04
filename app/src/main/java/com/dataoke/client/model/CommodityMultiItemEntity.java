package com.dataoke.client.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.dataoke.client.model.response.BannerResponse;
import com.dataoke.client.model.response.CaseResponse;
import com.dataoke.client.model.response.HotGoodsResponse;
import com.dataoke.client.model.response.CommonGoodsResponse;

/**
 * @author ddllxy
 * @date 2018/1/25 0025
 */

public class CommodityMultiItemEntity implements MultiItemEntity {
    public static final int BANNER_TYPE = 10000;
    public static final int CASE_TYPE = 20000;
    public static final int HOT_COMMODITY_TYPE = 30000;
    public static final int MAIN_COMMODITY_TYPE = 40000;

    public BannerResponse bannerResponse;
    public CaseResponse caseResponse;
    public HotGoodsResponse hotGoodsResponse;
    public CommonGoodsResponse commonGoodsResponse;

    private int itemType;

    public CommodityMultiItemEntity(int itemType) {
        this.itemType = itemType;
    }


    @Override
    public int getItemType() {
        return itemType;
    }
}
