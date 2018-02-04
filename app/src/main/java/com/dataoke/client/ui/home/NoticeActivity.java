package com.dataoke.client.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dataoke.client.R;
import com.dataoke.client.adapter.MainGoodsAdapter;
import com.dataoke.client.constant.JsonConstant;
import com.dataoke.client.model.response.CommonGoodsResponse;
import com.dataoke.client.ui.base.BaseActivity;
import com.dataoke.client.ui.base.BasePresenter;
import com.dataoke.client.ui.detail.DetailActivity;
import com.dataoke.client.utils.DividerGridItemDecoration;
import com.dataoke.client.utils.JsonUtils;
import com.dataoke.client.utils.TitleUtil;
import com.dataoke.client.utils.load.Callback;
import com.dataoke.client.utils.load.LoadSir;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import butterknife.BindView;

/**
 * Created by wpd on 2018/2/3.
 */

public class NoticeActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private MainGoodsAdapter mainGoodsAdapter;

    public static void startRecommendActivity(Context context) {
        context.startActivity(new Intent(context, NoticeActivity.class));
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        TitleUtil.setTitle(this, "小编推荐");
        TitleUtil.setTitleBackListener(this);
        CommonGoodsResponse commonGoodsResponse = JsonUtils.jsonStrToObject(JsonConstant.RANKINGLIST_JSON, CommonGoodsResponse.class);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        DividerGridItemDecoration decor = new DividerGridItemDecoration(this);
        decor.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_bg_gray_line));
        recyclerView.addItemDecoration(decor);
        recyclerView.setLayoutManager(manager);
        mainGoodsAdapter = new MainGoodsAdapter(R.layout.item_grid_goods_layout, commonGoodsResponse.data);
        mainGoodsAdapter.setShowNotice(true);
        recyclerView.setAdapter(mainGoodsAdapter);

        mBaseLoadService = LoadSir.getInstanceLoadSir().register(refreshLayout, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                showSuccess();
            }
        });

        showSuccess();
        refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(1000);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(1000);
                refreshlayout.finishLoadmoreWithNoMoreData();
            }
        });
        mainGoodsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CommonGoodsResponse.CommonGoods itemModel = (CommonGoodsResponse.CommonGoods) adapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putBoolean("notice",true);
                bundle.putString("goodsId",itemModel.goods_id);
                DetailActivity.startDetailActivity(NoticeActivity.this, bundle);
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_recommend_layout;
    }
}
