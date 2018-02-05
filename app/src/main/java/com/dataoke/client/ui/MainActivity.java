package com.dataoke.client.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.widget.FrameLayout;

import com.dataoke.client.MyApplication;
import com.dataoke.client.R;
import com.dataoke.client.ui.base.BaseActivity;
import com.dataoke.client.ui.base.BaseFragment;
import com.dataoke.client.ui.base.BasePresenter;
import com.dataoke.client.ui.delivery.FreeDeliveryFragment;
import com.dataoke.client.ui.home.HomeFragment;
import com.dataoke.client.ui.personal.PersonalFragment;
import com.dataoke.client.utils.BottomNavigationViewHelper;

import java.util.Iterator;
import java.util.LinkedList;

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
    private static final long DIFF_DEFAULT_BACK_TIME = 2000;
    private long mBackTime = -1;

    public static void startMainActivity(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

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

    @Override
    public void onBackPressedSupport() {
        long nowTime = System.currentTimeMillis();
        long diff = nowTime - mBackTime;
        if (diff >= DIFF_DEFAULT_BACK_TIME) {
            mBackTime = nowTime;
            showToast("再按一次退出程序");
        } else {
            LinkedList<Activity> activityList = MyApplication.activities;
            if (null != activityList && activityList.size() > 0) {
                Iterator iterator = activityList.iterator();
                while (iterator.hasNext()) {
                    Activity activity = (Activity) iterator.next();
                    activity.finish();
                }
            }
            System.exit(0);
        }
//        super.onBackPressedSupport();

    }
}
