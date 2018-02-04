package com.dataoke.client.model.response;

import com.dataoke.client.model.base.BaseResponse;

import java.util.List;

/**
 * @author ddllxy
 * @date 2018/1/25 0025
 */

public class CommonGoodsResponse extends BaseResponse {
    public List<CommonGoods> data;
    public int total;
    public int page;
    public int size;

    public static class CommonGoods {
        public String title;
        public int sell_num;
        public String price;
        public String coupon_value;
        public String image;
        public int id;
        public String goods_id;
        public int is_new;
        public int is_tmall;
        public String coupon_id;
        public int start_time;
        public int is_video;
        public int ci;
        public int cn;
        public int huodong_type;
    }

}
