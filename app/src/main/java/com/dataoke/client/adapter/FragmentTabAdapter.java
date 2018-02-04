package com.dataoke.client.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.dataoke.client.ui.base.BaseFragment;

import java.util.List;

/**
 * @author ddllxy
 * @date 2018/1/23 0023
 */

public class FragmentTabAdapter extends BaseFragmentPagerAdapter {
    public List<BaseFragment> fragments;
    public List<String> listTitles;
    public String[] strTitles;

    public FragmentTabAdapter(FragmentManager fm, List<BaseFragment> fragments, List<String> titles) {
        super(fm);
        this.fragments = fragments;
        this.listTitles = titles;
    }

    public FragmentTabAdapter(FragmentManager fm, List<BaseFragment> fragments, String[] titles) {
        super(fm);
        this.fragments = fragments;
        this.strTitles = titles;
    }

    public FragmentTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (listTitles != null) {
            return listTitles.get(position);
        } else if (strTitles != null) {
            return strTitles[position];
        }
        return "";
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }
}
