package com.dataoke.client.ui.personal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.dataoke.client.R;
import com.dataoke.client.adapter.MainGoodsAdapter;
import com.dataoke.client.constant.JsonConstant;
import com.dataoke.client.model.response.CommonGoodsResponse;
import com.dataoke.client.ui.base.BaseActivity;
import com.dataoke.client.ui.base.BasePresenter;
import com.dataoke.client.utils.DividerGridItemDecoration;
import com.dataoke.client.utils.JsonUtils;
import com.dataoke.client.utils.TitleUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author ddllxy
 * @date 2018/2/6 0006
 */

public class HistoryActivity extends BaseActivity {
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private MainGoodsAdapter mainGoodsAdapter;

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    public static void startHistoryActivity(Context context) {
        context.startActivity(new Intent(context, HistoryActivity.class));
    }

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        TitleUtil.setTitleBackListener(this);
        TitleUtil.setTitle(this, "我的足迹");
        TitleUtil.setRightImage(this, R.drawable.delete);

        GridLayoutManager manager = new GridLayoutManager(this, 2);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        DividerGridItemDecoration decor = new DividerGridItemDecoration(this);
        decor.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_bg_gray_line));
        recyclerView.addItemDecoration(decor);
        recyclerView.setLayoutManager(manager);
        mainGoodsAdapter = new MainGoodsAdapter(R.layout.item_grid_goods_layout, JsonUtils.jsonStrToObject(JsonConstant.MAIN_COMMODITY_JSON, CommonGoodsResponse.class).data);
        recyclerView.setAdapter(mainGoodsAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_history_layout;
    }


    @OnClick(R.id.iv_right)
    public void onViewClicked() {
        mainGoodsAdapter.setNewData(null);
    }
}
