package com.dataoke.client.model.response;

import com.dataoke.client.model.base.BaseResponse;

import java.util.List;

/**
 * @author ddllxy
 * @date 2018/1/24 0024
 */

public class CaseResponse extends BaseResponse {
    public List<Case> data;

    public static class Case {
        public String name;
        public String image;
        public String type;
        public String url;
    }
}
