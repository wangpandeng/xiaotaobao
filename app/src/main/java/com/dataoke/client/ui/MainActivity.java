package com.dataoke.client.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.dataoke.client.R;
import com.dataoke.client.ui.base.BaseActivity;
import com.dataoke.client.ui.base.BaseFragment;
import com.dataoke.client.ui.base.BasePresenter;
import com.dataoke.client.ui.delivery.FreeDeliveryFragment;
import com.dataoke.client.ui.home.HomeFragment;
import com.dataoke.client.ui.personal.PersonalFragment;
import com.dataoke.client.utils.BottomNavigationViewHelper;

import butterknife.BindView;

/**
 * @author Administrator
 */
public class MainActivity extends BaseActivity<BasePresenter> {

    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.btn_navigation)
    BottomNavigationView btnNavigation;
    private BaseFragment[] mFragments = new BaseFragment[3];

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            mFragments[0] = HomeFragment.newInstance();
            mFragments[1] = FreeDeliveryFragment.newInstance();
            mFragments[2] = PersonalFragment.newInstance();
            getSupportDelegate().loadMultipleRootFragment(R.id.fl_content, 0, mFragments);
        } else {
            mFragments[0] = findFragment(HomeFragment.class);
            mFragments[1] = findFragment(FreeDeliveryFragment.class);
            mFragments[2] = findFragment(PersonalFragment.class);
        }

        BottomNavigationViewHelper.disableShiftMode(btnNavigation);
        btnNavigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_item_home:
                    showHideFragment(mFragments[0]);
                    return true;
                case R.id.menu_item_free_delivery:
                    showHideFragment(mFragments[1]);
                    return true;
                case R.id.menu_item_personal:
                    showHideFragment(mFragments[2]);
                    return true;
                default:

            }
            return true;
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return null;
    }

}
