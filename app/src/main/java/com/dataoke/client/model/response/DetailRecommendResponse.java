package com.dataoke.client.model.response;

import com.dataoke.client.model.base.BaseResponse;

import java.util.List;

/**
 * @author ddllxy
 * @date 2018/1/30 0030
 */

public class DetailRecommendResponse extends BaseResponse {
    public List<DetailRecommendData> data;


    public static class DetailRecommendData {
        public String title;
        public String sell_num;
        public String price;
        public String coupon_value;
        public String image;
        public String id;
        public String goods_id;
        public String is_new;
        public String is_tmall;
        public String coupon_id;
        public String start_time;
        public String is_video;
        public String huodong_type;
    }
}
