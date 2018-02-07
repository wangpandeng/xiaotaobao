package com.dataoke.client.ui.personal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.EditText;
import android.widget.TextView;

import com.dataoke.client.R;
import com.dataoke.client.ui.base.BaseActivity;
import com.dataoke.client.ui.base.BasePresenter;
import com.dataoke.client.utils.TitleUtil;

import butterknife.BindView;
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

    public static void startSuggestActivity(Context context) {
        context.startActivity(new Intent(context, SuggestActivity.class));
    }


    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        TitleUtil.setTitle(this, "意见反馈");
        TitleUtil.setTitleBackListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_suggest_layout;
    }


    @OnClick(R.id.tv_submit)
    public void onViewClicked() {
        showToast("提交成功");
        finish();
    }
}
