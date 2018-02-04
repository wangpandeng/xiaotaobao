package com.dataoke.client.model.response;

import com.dataoke.client.model.base.BaseResponse;

import java.util.List;

/**
 * @author ddllxy
 * @date 2018/1/24 0024
 */

public class BannerResponse extends BaseResponse {

    public List<Banner> data;

    public static class Banner {
        public int type;
        public String title;
        public String image;
        public String url;
    }
}
