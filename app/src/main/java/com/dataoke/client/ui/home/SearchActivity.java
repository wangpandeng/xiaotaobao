package com.dataoke.client.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.dataoke.client.R;
import com.dataoke.client.adapter.MultiGoodsAdapter;
import com.dataoke.client.model.response.CommonGoodsResponse;
import com.dataoke.client.model.response.SearchHotTagResponse;
import com.dataoke.client.ui.base.BaseActivity;
import com.dataoke.client.ui.base.BasePresenter;
import com.dataoke.client.ui.home.contract.SearchContract;
import com.dataoke.client.ui.home.presenter.SearchPresenter;
import com.dataoke.client.utils.DensityUtil;
import com.dataoke.client.utils.DividerGridItemDecoration;
import com.dataoke.client.utils.load.Callback;
import com.dataoke.client.utils.load.LoadSir;
import com.google.android.flexbox.FlexboxLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author ddllxy
 * @date 2018/2/2 0002
 */

public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchContract.IView {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.fbl_hot_search_tag)
    FlexboxLayout fblHotSearchTag;
    @BindView(R.id.fbl_history_tag)
    FlexboxLayout fblHistoryTag;
    @BindView(R.id.tv_clear_history)
    TextView tvClearHistory;
    @BindView(R.id.cb_show_type)
    CheckBox cbShowType;
    @BindView(R.id.ll_search_history_tag)
    LinearLayout llSearchHistoryTag;
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
    @BindView(R.id.refreshHeaderView)
    ClassicsHeader refreshHeaderView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.ll_main)
    LinearLayout llMain;
    @BindView(R.id.ll_main_parent)
    LinearLayout mainParentView;
    @BindView(R.id.iv_clear)
    ImageView ivClear;
    private List<CommonGoodsResponse.CommonGoods> searchDataList;
    private DividerGridItemDecoration gridItemDecor;
    private DividerItemDecoration linearItemDecor;
    private GridLayoutManager gridLayoutManager;
    private LinearLayoutManager linearLayoutManager;
    private MultiGoodsAdapter multiGoodsAdapter;

    public static void startSearchActivity(Context context) {
        Intent intent = new Intent(context, SearchActivity.class);
        context.startActivity(intent);
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return SearchPresenter.newInstance();
    }

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        mBaseLoadService = LoadSir.getInstanceLoadSir().register(this, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                mPresenter.getMoreSearchData();
                showSuccess();
            }
        });


        gridLayoutManager = new GridLayoutManager(SearchActivity.this, 2);
        gridItemDecor = new DividerGridItemDecoration(SearchActivity.this);
        gridItemDecor.setDrawable(ContextCompat.getDrawable(SearchActivity.this, R.drawable.shape_bg_gray_line));

        linearItemDecor = new DividerItemDecoration(SearchActivity.this, DividerItemDecoration.VERTICAL);
        linearItemDecor.setDrawable(ContextCompat.getDrawable(SearchActivity.this, R.drawable.shape_bg_gray_line));
        linearLayoutManager = new LinearLayoutManager(SearchActivity.this);

        mPresenter.getHotSearchTagData();

        cbShowType.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    multiGoodsAdapter.setItemType(MultiGoodsAdapter.GRID_ITEM_TYPE);
                    recyclerView.setLayoutManager(gridLayoutManager);
                    recyclerView.removeItemDecoration(linearItemDecor);
                    recyclerView.addItemDecoration(gridItemDecor);
                    multiGoodsAdapter.notifyDataSetChanged();
                } else {
                    multiGoodsAdapter.setItemType(MultiGoodsAdapter.LINEAR_ITEM_TYPE);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.removeItemDecoration(gridItemDecor);
                    recyclerView.addItemDecoration(linearItemDecor);
                    multiGoodsAdapter.notifyDataSetChanged();
                }
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_hot:
                        mPresenter.getSearchData();
                        break;
                    case R.id.rb_new:
                        mPresenter.getSearchData();
                        break;
                    case R.id.rb_sale_number:
                        mPresenter.getSearchData();
                        break;
                    case R.id.rb_price:
                        mPresenter.getSearchData();
                        break;
                    default:
                }
            }
        });
        refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPresenter.getMoreSearchData();
                refreshlayout.finishLoadmore(1000);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.getSearchData();
                refreshlayout.finishRefresh(1000);
            }
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String content = etSearch.getText().toString();
                if (!TextUtils.isEmpty(content)) {
                    ivClear.setVisibility(View.VISIBLE);
                } else {
                    ivClear.setVisibility(View.GONE);
                }
            }
        });

        ivClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.setVisibility(View.GONE);
                tvSearch.setVisibility(View.VISIBLE);
                llSearchHistoryTag.setVisibility(View.VISIBLE);
                llMain.setVisibility(View.GONE);
                cbShowType.setVisibility(View.GONE);
                ivClear.setVisibility(View.GONE);
                etSearch.setText("");
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_search_layout;
    }


    @OnClick({R.id.iv_back, R.id.tv_search, R.id.tv_clear_history})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_search:
                getSearchGoodsList(null);
                break;
            case R.id.tv_clear_history:
                break;
            default:
        }
    }

    private void getSearchGoodsList(String tag) {
        if (TextUtils.isEmpty(tag)) {
            tag = etSearch.getHint().toString();
            cbShowType.setChecked(true);
        }
        showLoading();
        mPresenter.getSearchData();
        showSuccess();

        recyclerView.setVisibility(View.VISIBLE);
        etSearch.setText(tag);
        tvSearch.setVisibility(View.GONE);
        llSearchHistoryTag.setVisibility(View.GONE);
        llMain.setVisibility(View.VISIBLE);
        cbShowType.setVisibility(View.VISIBLE);
    }

    @Override
    public void setHotSearchTagData(SearchHotTagResponse searchHotTagResponse) {
        List<SearchHotTagResponse.HotTagData> data = searchHotTagResponse.data;
        int size = 0;
        if (data != null) {
            size = data.size();
        }
        for (int i = 0; i < size; i++) {
            TextView cellTextView = getCellTag(data.get(i).name);
            fblHotSearchTag.addView(cellTextView);
            cellTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getSearchGoodsList(cellTextView.getText().toString());
                }
            });
        }
    }

    @Override
    public void setSearchData(CommonGoodsResponse searchData) {
        searchDataList = searchData.data;
        multiGoodsAdapter = new MultiGoodsAdapter(searchDataList, this);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(gridItemDecor);
        recyclerView.setAdapter(multiGoodsAdapter);
    }

    @Override
    public void setMoreSearchData(CommonGoodsResponse searchData) {
        multiGoodsAdapter.addData(searchData.data);
    }

    private TextView getCellTag(String name) {
        TextView cellTextView = new TextView(this);
        FlexboxLayout.LayoutParams params = new FlexboxLayout.LayoutParams(FlexboxLayout.LayoutParams.WRAP_CONTENT, FlexboxLayout.LayoutParams.WRAP_CONTENT);
        cellTextView.setTextSize(14);
        cellTextView.setTextColor(ContextCompat.getColor(this, R.color.tag_color));
        cellTextView.setBackgroundResource(R.drawable.shape_bg_less_gray_round_solid_5);
        params.setMargins(DensityUtil.dip2px(15), DensityUtil.dip2px(15), 0, 0);
        cellTextView.setPadding(DensityUtil.dip2px(8), DensityUtil.dip2px(3), DensityUtil.dip2px(8), DensityUtil.dip2px(3));
        cellTextView.setText(name);
        cellTextView.setLayoutParams(params);
        return cellTextView;
    }
}

