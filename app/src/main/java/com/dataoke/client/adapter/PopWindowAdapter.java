package com.dataoke.client.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dataoke.client.R;

import java.util.List;

/**
 * @author ddllxy
 * @date 2018/2/6 0006
 */

public class PopWindowAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public PopWindowAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_item_content, item);
    }
}
