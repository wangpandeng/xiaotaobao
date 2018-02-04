package com.dataoke.client.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dataoke.client.R;
import com.dataoke.client.adapter.CaseListAdapter;
import com.dataoke.client.adapter.HotGoodsAdapter;
import com.dataoke.client.adapter.MainGoodsAdapter;
import com.dataoke.client.model.response.BannerResponse;
import com.dataoke.client.model.response.CaseResponse;
import com.dataoke.client.model.response.CommonGoodsResponse;
import com.dataoke.client.model.response.HotGoodsResponse;
import com.dataoke.client.ui.base.BaseFragment;
import com.dataoke.client.ui.base.BasePresenter;
import com.dataoke.client.ui.detail.DetailActivity;
import com.dataoke.client.ui.home.contract.NewCommodityContract;
import com.dataoke.client.ui.home.presenter.NewCommodityPresenter;
import com.dataoke.client.utils.DividerGridItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import butterknife.BindView;

/**
 * 新上——商品列表
 *
 * @author ddllxy
 * @date 2018/1/24 0024
 */

public class NewGoodsFragment extends BaseFragment<NewCommodityPresenter> implements NewCommodityContract.IView {
    @BindView(R.id.banner_view)
    Banner bannerView;
    @BindView(R.id.rv_hot_commodity)
    RecyclerView rvHotCommodity;
    @BindView(R.id.tv_commodity_amount)
    TextView tvCommodityAmount;
    @BindView(R.id.rv_main_commodity)
    RecyclerView rvCommodity;
    @BindView(R.id.rv_case)
    RecyclerView rvCase;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;
    private MainGoodsAdapter mainGoodsAdapter;
    private CaseListAdapter caseAdapter;
    private HotGoodsAdapter hotGoodsAdapter;

    public static NewGoodsFragment newInstance() {
        return new NewGoodsFragment();
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return NewCommodityPresenter.newInstance();
    }

    @Override
    public void onFragmentCreate(View view, Bundle savedInstanceState) {
        rvCommodity.setNestedScrollingEnabled(false);
        rvCommodity.setHasFixedSize(true);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        DividerGridItemDecoration decor = new DividerGridItemDecoration(getContext());
        decor.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.shape_bg_gray_line));
        rvCommodity.addItemDecoration(decor);
        rvCommodity.setLayoutManager(manager);
        mainGoodsAdapter = new MainGoodsAdapter(R.layout.item_grid_goods_layout, null);
        rvCommodity.setAdapter(mainGoodsAdapter);


        LinearLayoutManager hotLayoutManager = new LinearLayoutManager(getContext());
        hotLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvHotCommodity.setLayoutManager(hotLayoutManager);
        hotGoodsAdapter = new HotGoodsAdapter(R.layout.item_hot_goods_layout, null);
        rvHotCommodity.setAdapter(hotGoodsAdapter);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 4);
        layoutManager.setOrientation(GridLayoutManager.VERTICAL);
        caseAdapter = new CaseListAdapter(R.layout.item_case_layout, null);
        rvCase.setLayoutManager(layoutManager);
        rvCase.setAdapter(caseAdapter);

        smartRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPresenter.getMoreMainCommodityData();
                refreshlayout.finishLoadmore(1000);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.getBannerData();
                mPresenter.getCaseData();
                mPresenter.getHotCommodityData();
                mPresenter.getMainCommodityData();
                refreshlayout.finishRefresh(1000);
            }
        });

        caseAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position == 0) {
                    RushPurchaseActivity.startRushPurchaseActivity(getContext());
                } else if (position == 1) {
                    RecommendActivity.startRecommendActivity(getContext());
                } else if (position == 2) {
                    RankingListActivity.startRecommendActivity(getContext());
                } else {
                    NoticeActivity.startRecommendActivity(getContext());
                }
            }
        });

        hotGoodsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HotGoodsResponse.HotCommodity hotCommodity = (HotGoodsResponse.HotCommodity) adapter.getItem(position);
                DetailActivity.startDetailActivity(getContext(), hotCommodity.goods_id);
            }
        });

        mainGoodsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CommonGoodsResponse.CommonGoods commonGoods = (CommonGoodsResponse.CommonGoods) adapter.getItem(position);
                DetailActivity.startDetailActivity(getContext(), commonGoods.goods_id);
            }
        });
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.getBannerData();
        mPresenter.getCaseData();
        mPresenter.getHotCommodityData();
        mPresenter.getMainCommodityData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_new_goods_layout;
    }


    @Override
    public void setBanner(BannerResponse banner) {
        bannerView.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {

                Glide.with(NewGoodsFragment.this).load(((BannerResponse.Banner) path).image).into(imageView);
            }
        }).setImages(banner.data).start();

        bannerView.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                getActivity().startActivity(new Intent(getActivity(), DetailActivity.class));
            }
        });
    }

    @Override
    public void setCaseData(CaseResponse caseData) {
        caseAdapter.setNewData(caseData.data);
    }


    @Override
    public void setHotCommodityData(HotGoodsResponse hotCommodityData) {
        hotGoodsAdapter.setNewData(hotCommodityData.data);
    }

    @Override
    public void setMainCommodityData(CommonGoodsResponse mainCommodityData) {
        mainGoodsAdapter.setNewData(mainCommodityData.data);
    }

    @Override
    public void setMoreMainCommodityData(CommonGoodsResponse mainCommodityData) {
        mainGoodsAdapter.addData(mainCommodityData.data);
    }
}
