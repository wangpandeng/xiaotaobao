package com.dataoke.client.adapter;

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
 * @date 2018/2/2 0002
 */

public class LinearGoodsAdapter extends BaseQuickAdapter<CommonGoodsResponse.CommonGoods, BaseViewHolder> {
    public LinearGoodsAdapter(int layoutResId, @Nullable List<CommonGoodsResponse.CommonGoods> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommonGoodsResponse.CommonGoods item) {
        ImageView ivGoodsPic = helper.getView(R.id.iv_goods_pic);
        Glide.with(mContext).load(item.image).into(ivGoodsPic);
        helper.setText(R.id.tv_name, item.title);
        helper.setText(R.id.tv_coupon_price, "优惠券\t￥" + item.coupon_value);
        helper.setText(R.id.tv_sale_number, "销量\t" + item.sell_num);

        StringBuilder sb = new StringBuilder("￥");
        sb.append(item.coupon_value).append("劵后");
        Spannable strPrice = StringUtil.setInnerTextColorAndSize(sb.toString(), "劵后", mContext.getString(R.string.font_less_gray), 12);
        helper.setText(R.id.tv_price, strPrice);

    }
}
