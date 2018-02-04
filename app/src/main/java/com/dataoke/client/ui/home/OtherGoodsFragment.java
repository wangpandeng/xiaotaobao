package com.dataoke.client.ui.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dataoke.client.R;
import com.dataoke.client.adapter.MainGoodsAdapter;
import com.dataoke.client.model.response.CommonGoodsResponse;
import com.dataoke.client.ui.base.BaseFragment;
import com.dataoke.client.ui.base.BasePresenter;
import com.dataoke.client.ui.detail.DetailActivity;
import com.dataoke.client.ui.home.contract.OtherCommodityContract;
import com.dataoke.client.ui.home.presenter.OtherCommodityPresenter;
import com.dataoke.client.utils.DensityUtil;
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

public class OtherGoodsFragment extends BaseFragment<OtherCommodityPresenter> implements OtherCommodityContract.IView {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.iv_category)
    ImageView ivCategory;
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
    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rb_hot_top)
    RadioButton rbHotTop;
    @BindView(R.id.rb_new_top)
    RadioButton rbNewTop;
    @BindView(R.id.rb_sale_number_top)
    RadioButton rbSaleNumberTop;
    @BindView(R.id.rb_price_top)
    RadioButton rbPriceTop;
    @BindView(R.id.radioGroup_top)
    RadioGroup radioGroupTop;
    @BindView(R.id.ll_main)
    LinearLayout linearMain;
    @BindView(R.id.fl_content_view)
    FrameLayout flContentView;
    private int ivHeight;
    private MainGoodsAdapter adapter;

    public static OtherGoodsFragment newInstance() {
        return new OtherGoodsFragment();
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return OtherCommodityPresenter.newInstance();
    }

    @Override
    public void onFragmentCreate(View view, Bundle savedInstanceState) {
        mBaseLoadService = LoadSir.getInstanceLoadSir().register(refreshLayout, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                mPresenter.getOtherCommodityData();
                showSuccess();
            }
        });


        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        DividerGridItemDecoration decor = new DividerGridItemDecoration(getContext());
        decor.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.shape_bg_gray_line));
        recyclerView.addItemDecoration(decor);
        recyclerView.setLayoutManager(manager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        adapter = new MainGoodsAdapter(R.layout.item_grid_goods_layout, null);
        recyclerView.setAdapter(adapter);


        ivHeight = DensityUtil.dip2px(80);

        setOnCheckChangeListener();
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > oldScrollY) {
                    //向下滑动
                    if (scrollY >= ivHeight) {
                        radioGroupTop.setVisibility(View.VISIBLE);
                    }
                } else if (scrollY < oldScrollY) {
                    //向上滑动
                    if (scrollY <= ivHeight) {
                        radioGroupTop.setVisibility(View.GONE);
                    }
                }
            }
        });

        refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPresenter.getMoreOtherCommodityData();
                refreshlayout.finishLoadmore(1000);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.getOtherCommodityData();
                refreshlayout.finishRefresh(1000);
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CommonGoodsResponse.CommonGoods data = (CommonGoodsResponse.CommonGoods) adapter.getItem(position);
                DetailActivity.startDetailActivity(getContext(), data.goods_id);
            }
        });
    }

    private void setOnCheckChangeListener() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_hot:
                        rbHotTop.setChecked(true);
                        mPresenter.getOtherCommodityData();
                        break;
                    case R.id.rb_new:
                        rbNewTop.setChecked(true);
                        mPresenter.getOtherCommodityData();
                        break;
                    case R.id.rb_sale_number:
                        rbSaleNumberTop.setChecked(true);
                        mPresenter.getOtherCommodityData();
                        break;
                    case R.id.rb_price:
                        rbPriceTop.setChecked(true);
                        mPresenter.getOtherCommodityData();
                        break;
                    default:
                }
            }
        });

        radioGroupTop.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_hot_top:
                        rbHot.setChecked(true);
                        mPresenter.getOtherCommodityData();
                        break;
                    case R.id.rb_new_top:
                        rbNew.setChecked(true);
                        mPresenter.getOtherCommodityData();
                        break;
                    case R.id.rb_sale_number_top:
                        rbSaleNumber.setChecked(true);
                        mPresenter.getOtherCommodityData();
                        break;
                    case R.id.rb_price_top:
                        rbPrice.setChecked(true);
                        mPresenter.getOtherCommodityData();
                        break;
                    default:
                }
            }
        });
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.getOtherCommodityData();
        showSuccess();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_other_commodity_layout;
    }

    @Override
    public void setOtherCommodityData(CommonGoodsResponse data) {
        adapter.setNewData(data.data);
        ivCategory.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_category));
        nestedScrollView.smoothScrollTo(0, 0);
    }

    @Override
    public void setMoreOtherCommodityData(CommonGoodsResponse data) {
        adapter.addData(data.data);
        refreshLayout.finishLoadmore(1000);
    }
}
