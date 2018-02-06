package com.dataoke.client.ui.personal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.EditText;
import android.widget.TextView;

import com.dataoke.client.R;
import com.dataoke.client.ui.base.BaseActivity;
import com.dataoke.client.ui.base.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author ddllxy
 * @date 2018/2/6 0006
 */

public class SuggestActivity extends BaseActivity {
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_suggest_layout;
    }


    @OnClick(R.id.tv_submit)
    public void onViewClicked() {
    }
}
