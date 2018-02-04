package com.dataoke.client.model.response;

import com.dataoke.client.model.base.BaseResponse;

import java.util.List;

/**
 * @author ddllxy
 * @date 2018/1/30 0030
 */

public class DetailResponse extends BaseResponse {
    public DetailData data;

    public static class DetailData {
        public int overdue;
        public String title;
        public int id;
        public String details;
        public String image;
        public String price;
        public String quan_price;
        public String sell_num;
        public String goods_id;
        public int start_time;
        public int server_time;
        public String is_video;
        public String video_url;
        public List<String> goods_tag;
        public String url;
    }
}
