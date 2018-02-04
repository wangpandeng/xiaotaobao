package com.dataoke.client.ui.delivery;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dataoke.client.R;
import com.dataoke.client.adapter.MainGoodsAdapter;
import com.dataoke.client.model.response.CommonGoodsResponse;
import com.dataoke.client.ui.base.BaseFragment;
import com.dataoke.client.ui.delivery.contract.DetailFreeDeliveryContract;
import com.dataoke.client.ui.delivery.presenter.DetailFreeDeliveryPresenter;
import com.dataoke.client.ui.detail.DetailActivity;
import com.dataoke.client.utils.DividerGridItemDecoration;
import com.dataoke.client.utils.load.Callback;
import com.dataoke.client.utils.load.LoadSir;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import butterknife.BindView;

/**
 * @author
 * @date 2018/1/29 0029
 */

public class DetailFreeDeliveryFragment extends BaseFragment<DetailFreeDeliveryPresenter> implements DetailFreeDeliveryContract.IView {

    private static String mFlag;
    public static final String NINE_FREE_DELIVERY = "1000";//9.9包邮
    public static final String NINETEEN_FREE_DELIVERY = "2000";//19.9包邮
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rb_hot)
    RadioButton rbHot;
    @BindView(R.id.rb_new)
    RadioButton rbNew;
    @BindView(R.id.rb_sale_number)
    RadioButton rbSaleNumber;
    @BindView(R.id.rb_price)
    RadioButton rbPrice;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    private MainGoodsAdapter goodsAdapter;

    public static BaseFragment newInstance(String flag) {
        mFlag = flag;
        return new DetailFreeDeliveryFragment();
    }

    @NonNull
    @Override
    public DetailFreeDeliveryPresenter initPresenter() {
        return new DetailFreeDeliveryPresenter();
    }

    @Override
    public void onFragmentCreate(View view, Bundle savedInstanceState) {
        mBaseLoadService = LoadSir.getInstanceLoadSir().register(refreshLayout, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                mPresenter.getData();
                showSuccess();
            }
        });


        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        DividerGridItemDecoration decor = new DividerGridItemDecoration(getContext());
        decor.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.shape_bg_gray_line));
        recyclerView.addItemDecoration(decor);
        recyclerView.setLayoutManager(manager);
        goodsAdapter = new MainGoodsAdapter(R.layout.item_grid_goods_layout, null);
        recyclerView.setAdapter(goodsAdapter);

        goodsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CommonGoodsResponse.CommonGoods itemModel = (CommonGoodsResponse.CommonGoods) adapter.getItem(position);
                DetailActivity.startDetailActivity(getContext(), itemModel.goods_id);
            }
        });
        refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPresenter.getMoreData();
                refreshlayout.finishLoadmore(1000);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.getData();
                refreshlayout.finishRefresh(1000);
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_hot:
                        mPresenter.getData();
                        break;
                    case R.id.rb_new:
                        mPresenter.getData();
                        break;
                    case R.id.rb_sale_number:
                        mPresenter.getData();
                        break;
                    case R.id.rb_price:
                        mPresenter.getData();
                        break;
                    default:
                }
            }
        });
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.getData();
        showSuccess();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_detail_free_delivery_layout;
    }

    @Override
    public void setCommodityData(CommonGoodsResponse commonGoodsResponse) {
        goodsAdapter.setNewData(commonGoodsResponse.data);
    }

    @Override
    public void setMoreCommodityData(CommonGoodsResponse commonGoodsResponse) {
        goodsAdapter.addData(commonGoodsResponse.data);
    }

}
