package com.dataoke.client.ui.personal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.dataoke.client.R;
import com.dataoke.client.ui.base.BaseFragment;
import com.dataoke.client.ui.base.BasePresenter;
import com.dataoke.client.utils.ALibcUtil;
import com.dataoke.client.utils.ShareUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的
 *
 * @author ddllxy
 * @date 2018/1/23 0023
 */

public class PersonalFragment extends BaseFragment<BasePresenter> {


    @BindView(R.id.tv_mine_order)
    TextView tvMineOrder;
    @BindView(R.id.tv_mine_cart)
    TextView tvMineCart;
    @BindView(R.id.tv_mine_foot)
    TextView tvMineFoot;
    @BindView(R.id.tv_fankui)
    TextView tvFankui;
    @BindView(R.id.tv_service)
    TextView tvService;
    @BindView(R.id.tv_clear_cache)
    TextView tvClearCache;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.tv_update)
    TextView tvUpdate;

    public static PersonalFragment newInstance() {
        return new PersonalFragment();
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return new BasePresenter();
    }

    @Override
    public void onFragmentCreate(View view, Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_personal_layout;
    }


    @OnClick({R.id.tv_mine_order, R.id.tv_mine_cart, R.id.tv_mine_foot, R.id.tv_fankui, R.id.tv_service, R.id.tv_clear_cache, R.id.tv_share, R.id.tv_update})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_mine_order:
                ALibcUtil.showOrder(getActivity(), true);
                break;
            case R.id.tv_mine_cart:
                ALibcUtil.showCart(getActivity());
                break;
            case R.id.tv_mine_foot:
                HistoryActivity.startHistoryActivity(getContext());
                break;
            case R.id.tv_fankui:
                SuggestActivity.startSuggestActivity(getContext());
                break;
            case R.id.tv_service:
                ServiceActivity.startServiceActivity(getContext());
                break;
            case R.id.tv_clear_cache:
                showToast("清空缓存成功");
                break;
            case R.id.tv_share:
                ShareUtil.shareText(getContext(), "分享给", "小淘宝下载地址：www.baidu.com");
                break;
            case R.id.tv_update:
                showToast("已经是最高版本了");
                break;
            default:
        }
    }
}
