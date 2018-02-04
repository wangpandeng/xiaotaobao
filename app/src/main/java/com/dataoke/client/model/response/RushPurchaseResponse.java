package com.dataoke.client.model.response;

import com.dataoke.client.model.base.BaseResponse;

import java.util.List;

/**
 * Created by wpd on 2018/2/3.
 */

public class RushPurchaseResponse extends BaseResponse {
    public RushPurchaseData data;

    public static class RushPurchaseData {
        public List<RushPurchase> list;
        public String url;//imageurl
    }

    public static class RushPurchase {
        public String title;
        public String sell_num;
        public String price;// 19.72,
        public String coupon_value;// "5.00",
        public String image;// "https:\/\/img.alicdn.com\/imgextra\/i3\/2745093697\/TB26ICadSvHfKJjSZFPXXbttpXa_!!2745093697.jpg_600x600.jpg_.webp",
        public String id;// "5426335",
        public String goods_id;// "534596688539",
        public String is_new;// 0,
        public String is_tmall;// 1,
        public String coupon_id;// "3f20d94c24954f95939c87e9798e1c3d",
        public String quan_over;// 0,
        public String quan_num;// "10000",
        public String start_time;// 1517616000,
        public String sf_wenan;// "\u5e72\u723d\u6b62\u6c57\uff0c\u6e29\u548c\u4eb2\u80a4\uff0c\u6301\u4e45\u6e05\u9999\u66f4\u81ea\u4fe1",
        public String pinpai_name;// "",
        public String pinpai_image;// "https:\/\/img.alicdn.com\/imgextra\/i2\/2053469401\/TB2lwPEiWmgSKJjSspiXXXyJFXa_!!2053469401.png",
        public String is_video;// 0
    }
}
