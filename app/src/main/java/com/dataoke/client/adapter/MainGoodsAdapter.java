package com.dataoke.client.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dataoke.client.R;
import com.dataoke.client.model.response.CommonGoodsResponse;
import com.dataoke.client.utils.StringUtil;

import java.util.List;

/**
 * @author ddllxy
 * @date 2018/1/25 0025
 */

public class MainGoodsAdapter extends BaseQuickAdapter<CommonGoodsResponse.CommonGoods, BaseViewHolder> {
    public MainGoodsAdapter(@LayoutRes int layoutResId, @Nullable List<CommonGoodsResponse.CommonGoods> data) {
        super(layoutResId, data);
    }

    private boolean mShowTop = false;
    private boolean isShowNotice = false;

    public MainGoodsAdapter(@LayoutRes int layoutResId, @Nullable List<CommonGoodsResponse.CommonGoods> data, boolean mShowTop) {
        super(layoutResId, data);
        this.mShowTop = mShowTop;
    }

    public void setShowNotice(boolean isShowNotice) {
        this.isShowNotice = isShowNotice;
    }

    @Override
    protected void convert(BaseViewHolder helper, CommonGoodsResponse.CommonGoods item) {
        ImageView ivIcon = helper.getView(R.id.iv_icon);
        Glide.with(mContext).load(item.image).into(ivIcon);

        StringBuilder sb = new StringBuilder("劵");
        sb.append("￥").append(item.coupon_value);
        helper.setText(R.id.tv_coupon, sb);

        StringBuilder sbPrice = new StringBuilder();
        sbPrice.append("￥").append(item.price).append("劵后");
        Spannable strPrice = StringUtil.setInnerTextColorAndSize(sbPrice.toString(), "劵后", mContext.getString(R.string.font_less_gray), 12);
        helper.setText(R.id.tv_coupon_price, strPrice);

        helper.setText(R.id.tv_name, item.title);
        helper.setText(R.id.tv_sell_num, "销量\t" + item.sell_num);

        if (item.is_video == 0) {
            helper.setGone(R.id.tv_video, false);
        } else {
            helper.setGone(R.id.tv_video, true);
        }

        if (mShowTop) {
            helper.setText(R.id.tv_top, "TOP\n" + (helper.getPosition() + 1));
            helper.setVisible(R.id.tv_top, true);
        } else {
            helper.setVisible(R.id.tv_top, false);
        }

        if (isShowNotice) {
            helper.setVisible(R.id.tv_notice, true);
        } else {
            helper.setVisible(R.id.tv_notice, false);
        }
    }
}
