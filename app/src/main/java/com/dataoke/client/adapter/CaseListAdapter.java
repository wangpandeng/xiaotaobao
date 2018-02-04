package com.dataoke.client.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dataoke.client.R;
import com.dataoke.client.model.response.CaseResponse;

import java.util.List;

/**
 * @author ddllxy
 * @date 2018/1/25 0025
 */

public class CaseListAdapter extends BaseQuickAdapter<CaseResponse.Case, BaseViewHolder> {

    public CaseListAdapter(@LayoutRes int layoutResId, @Nullable List<CaseResponse.Case> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CaseResponse.Case item) {
        helper.setText(R.id.tv_name, item.name);
        ImageView imageView = helper.getView(R.id.iv_icon);
        Glide.with(imageView.getContext()).load(item.image).into(imageView);
    }
}
