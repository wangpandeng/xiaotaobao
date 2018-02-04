package com.dataoke.client.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dataoke.client.R;
import com.dataoke.client.model.response.HotGoodsResponse;
import com.dataoke.client.utils.StringUtil;

import java.util.List;

/**
 * @author ddllxy
 * @date 2018/1/24 0024
 */

public class HotGoodsAdapter extends BaseQuickAdapter<HotGoodsResponse.HotCommodity, BaseViewHolder> {


    public HotGoodsAdapter(@LayoutRes int layoutResId, @Nullable List<HotGoodsResponse.HotCommodity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HotGoodsResponse.HotCommodity item) {
        helper.setText(R.id.tv_title, item.title);

        ImageView imageView = helper.getView(R.id.iv_icon);
        Glide.with(mContext).load(item.image).into(imageView);

        StringBuilder sb = new StringBuilder("￥");
        sb.append(item.coupon_value).append("劵后");
        Spannable strPrice = StringUtil.setInnerTextColorAndSize(sb.toString(), "劵后", mContext.getString(R.string.font_less_gray), 12);
        helper.setText(R.id.tv_price, strPrice);

        helper.setTextColor(R.id.tv_price, ContextCompat.getColor(mContext, R.color.color_red));
    }
}
