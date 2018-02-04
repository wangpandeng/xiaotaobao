package com.dataoke.client.ui.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dataoke.client.R;
import com.dataoke.client.constant.JsonConstant;
import com.dataoke.client.model.response.RushPurchaseResponse;
import com.dataoke.client.ui.base.BaseFragment;
import com.dataoke.client.ui.base.BasePresenter;
import com.dataoke.client.ui.detail.DetailActivity;
import com.dataoke.client.utils.JsonUtils;
import com.dataoke.client.utils.StringUtil;
import com.dataoke.client.utils.load.Callback;
import com.dataoke.client.utils.load.LoadSir;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.List;

import butterknife.BindView;

/**
 * Created by wpd on 2018/2/3.
 */

public class RushPurchaseFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    public static RushPurchaseFragment newInstance() {
        return new RushPurchaseFragment();
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return new BasePresenter();
    }

    @Override
    public void onFragmentCreate(View view, Bundle savedInstanceState) {
        mBaseLoadService = LoadSir.getInstanceLoadSir().register(refreshLayout, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                showSuccess();
            }
        });

        RushPurchaseResponse response = getData();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        RushPurchaseAdapter adapter = new RushPurchaseAdapter(R.layout.item_rush_purchase, response.data.list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                RushPurchaseResponse.RushPurchase itemModel = (RushPurchaseResponse.RushPurchase) adapter.getItem(position);
                DetailActivity.startDetailActivity(getContext(), itemModel.goods_id);
            }
        });
        showSuccess();

        refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(1000);
            }

            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(1000);
                refreshlayout.finishLoadmoreWithNoMoreData();
            }
        });
    }

    private RushPurchaseResponse getData() {
        return JsonUtils.jsonStrToObject(JsonConstant.RUSH_PURCHASE_JSON, RushPurchaseResponse.class);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_rush_purchase_layout;
    }

    class RushPurchaseAdapter extends BaseQuickAdapter<RushPurchaseResponse.RushPurchase, BaseViewHolder> {

        public RushPurchaseAdapter(int layoutResId, @Nullable List<RushPurchaseResponse.RushPurchase> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, RushPurchaseResponse.RushPurchase item) {
            ImageView imageView = helper.getView(R.id.iv_purchase_pic);
            Glide.with(getActivity()).load(item.image).into(imageView);

            helper.setText(R.id.tv_name, item.title);

            StringBuilder sb = new StringBuilder("￥");
            sb.append(item.price).append("劵后");
            Spannable strPrice = StringUtil.setInnerTextColorAndSize(sb.toString(), "劵后", mContext.getString(R.string.font_less_gray), 12);
            helper.setText(R.id.tv_price, strPrice);

            helper.setText(R.id.tv_coupon_price, item.coupon_value);
            helper.setText(R.id.tv_buyer_number, item.sell_num);

            helper.setText(R.id.tv_tips, item.sf_wenan);
        }
    }
}
