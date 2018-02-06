package com.dataoke.client.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dataoke.client.R;
import com.dataoke.client.model.response.CommonGoodsResponse;
import com.dataoke.client.utils.StringUtil;

import java.util.List;

/**
 * @author ddllxy
 * @date 2018/2/2 0002
 */

public class MultiGoodsAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    public static final int LINEAR_ITEM_TYPE = 1000;
    public static final int GRID_ITEM_TYPE = 2000;
    private List<CommonGoodsResponse.CommonGoods> list;

    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public MultiGoodsAdapter(List<CommonGoodsResponse.CommonGoods> list, Context context) {
        this.list = list;
        this.mContext = context;
    }

    public void addData(List<CommonGoodsResponse.CommonGoods> addData) {
        list.addAll(addData);
        notifyDataSetChanged();
    }

    public void setNewData(List<CommonGoodsResponse.CommonGoods> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setOnItemClick(@Nullable OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public interface OnItemClickListener {

        /**
         * Callback method to be invoked when an item in this RecyclerView has
         * been clicked.
         *
         * @param position The position of the view in the adapter.
         */
        void onItemClick(View itemView, int position, CommonGoodsResponse.CommonGoods itemData);
    }

    private int itemType;

    public void setItemType(int type) {
        this.itemType = type;
    }


    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        LayoutInflater from = LayoutInflater.from(mContext);
        if (itemType == LINEAR_ITEM_TYPE) {
            itemView = from.inflate(R.layout.item_linear_goods_layout, parent, false);
        } else {
            itemView = from.inflate(R.layout.item_grid_goods_layout, parent, false);
        }


        return new BaseViewHolder(itemView);
    }

    @Override
    public int getItemViewType(int position) {
        return itemType;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder helper, int position) {
        CommonGoodsResponse.CommonGoods item = list.get(position);

        View itemView = helper.getConvertView();
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(itemView, position, item);
            }
        });

        if (itemType == LINEAR_ITEM_TYPE) {
            ImageView ivGoodsPic = helper.getView(R.id.iv_goods_pic);
            Glide.with(mContext).load(item.image).into(ivGoodsPic);
            helper.setText(R.id.tv_name, item.title);
            helper.setText(R.id.tv_coupon_price, "优惠券\t￥" + item.coupon_value);
            helper.setText(R.id.tv_sale_number, "销量\t" + item.sell_num);

            StringBuilder sb = new StringBuilder("￥");
            sb.append(item.coupon_value).append("劵后");
            Spannable strPrice = StringUtil.setInnerTextColorAndSize(sb.toString(), "劵后", mContext.getString(R.string.font_less_gray), 12);
            helper.setText(R.id.tv_price, strPrice);

        } else {
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
            }
        }
    }
}
