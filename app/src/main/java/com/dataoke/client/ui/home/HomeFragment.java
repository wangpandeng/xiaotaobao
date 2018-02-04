package com.dataoke.client.ui.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.dataoke.client.R;
import com.dataoke.client.adapter.FragmentTabAdapter;
import com.dataoke.client.ui.base.BaseFragment;
import com.dataoke.client.ui.base.BasePresenter;
import com.dataoke.client.ui.home.contract.HomeContract;
import com.dataoke.client.ui.home.presenter.HomePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author ddllxy
 * @date 2018/1/23 0023
 */

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.IView {
    @BindView(R.id.tv_search_text)
    TextView tvSearchText;
    @BindView(R.id.fl_search)
    FrameLayout flSearch;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.vp_fragment)
    ViewPager viewPager;

    private List<BaseFragment> mFragments;
    String[] titles = new String[]{"新上", "女装", "男装", "内衣", "母婴", "化妆品", "居家", "鞋包", "美食", "文体车品", "数码家电"};

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    public void onFragmentCreate(View view, Bundle savedInstanceState) {
        mFragments = new ArrayList<>();
        int size = titles.length;
        for (int i = 0; i < size; i++) {
            if (i == 0) {
                mFragments.add(NewGoodsFragment.newInstance());
            } else {
                mFragments.add(OtherGoodsFragment.newInstance());
            }
        }
        //这里要用getChildFragmentManager()自己的manager
        viewPager.setAdapter(new FragmentTabAdapter(getChildFragmentManager(), mFragments, titles));
        viewPager.setOffscreenPageLimit(1);
        tabLayout.setupWithViewPager(viewPager);


    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_layout;
    }


    @OnClick(R.id.fl_search)
    public void onViewClicked() {
        SearchActivity.startSearchActivity(getContext());
    }
}
