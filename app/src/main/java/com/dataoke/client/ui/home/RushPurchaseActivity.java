package com.dataoke.client.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.dataoke.client.R;
import com.dataoke.client.adapter.FragmentTabAdapter;
import com.dataoke.client.ui.base.BaseActivity;
import com.dataoke.client.ui.base.BaseFragment;
import com.dataoke.client.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wpd on 2018/2/3.
 */

public class RushPurchaseActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    List<BaseFragment> fragments;
    String[] titles = new String[5];

    public static void startRushPurchaseActivity(Context context) {
        context.startActivity(new Intent(context, RushPurchaseActivity.class));
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return new BasePresenter();
    }

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        ivBg.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.defa));
        tvTitle.setText("咚咚抢-每日三场 限时疯抢");
        titles[0] = "昨日";
        titles[1] = "9:00";
        titles[2] = "13:00";
        titles[3] = "19:00";
        titles[4] = "预告";

        fragments = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            fragments.add(RushPurchaseFragment.newInstance());
        }
        viewPager.setAdapter(new FragmentTabAdapter(getSupportFragmentManager(), fragments, titles));
        viewPager.setOffscreenPageLimit(1);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_rush_purchase_layout;
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        onBackPressed();
    }
}
