package com.dataoke.client.ui.delivery;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.dataoke.client.R;
import com.dataoke.client.adapter.FragmentTabAdapter;
import com.dataoke.client.ui.base.BaseFragment;
import com.dataoke.client.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Administrator
 * @date 2018/1/23 0023
 */

public class FreeDeliveryFragment extends BaseFragment {

    @BindView(R.id.tabLayout_title)
    TabLayout tabLayoutTitle;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private List<BaseFragment> list;
    private List<String> titles;

    public static FreeDeliveryFragment newInstance() {
        return new FreeDeliveryFragment();
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void onFragmentCreate(View view, Bundle savedInstanceState) {
        list = new ArrayList<>(2);
        titles = new ArrayList<>(2);
        list.add(DetailFreeDeliveryFragment.newInstance(DetailFreeDeliveryFragment.NINE_FREE_DELIVERY));
        list.add(DetailFreeDeliveryFragment.newInstance(DetailFreeDeliveryFragment.NINETEEN_FREE_DELIVERY));
        titles.add("9.9包邮");
        titles.add("19.9包邮");

        viewPager.setAdapter(new FragmentTabAdapter(getChildFragmentManager(), list, titles));
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(1);
        tabLayoutTitle.setupWithViewPager(viewPager);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_free_delivery_layout;
    }

}
