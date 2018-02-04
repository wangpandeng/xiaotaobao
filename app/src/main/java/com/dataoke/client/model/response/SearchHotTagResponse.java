package com.dataoke.client.model.response;

import com.dataoke.client.model.base.BaseResponse;

import java.util.List;

/**
 * @author ddllxy
 * @date 2018/2/2 0002
 */

public class SearchHotTagResponse extends BaseResponse {
    public List<HotTagData> data;

    public static class HotTagData {
        public String name;
        public String describe;
    }
}
