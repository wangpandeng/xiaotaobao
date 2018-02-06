package com.dataoke.client.ui.personal;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.dataoke.client.R;
import com.dataoke.client.ui.base.BaseActivity;
import com.dataoke.client.ui.base.BasePresenter;
import com.dataoke.client.utils.TitleUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author ddllxy
 * @date 2018/2/6 0006
 */

public class ServiceActivity extends BaseActivity {
    @BindView(R.id.tv_qq)
    TextView tvQq;
    @BindView(R.id.tv_call_phone)
    TextView tvCallPhone;
    @BindView(R.id.tv_phone_number)
    TextView tvPhoneNumber;
    @BindView(R.id.tv_qq_number)
    TextView tvQQNumber;

    public static void startServiceActivity(Context context) {
        context.startActivity(new Intent(context, ServiceActivity.class));
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        TitleUtil.setTitle(this, "客户服务");
        TitleUtil.setTitleBackListener(this);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_service_layout;
    }


    @SuppressLint("MissingPermission")
    @OnClick({R.id.tv_qq, R.id.tv_call_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_qq:
                String url = "mqqwpa://im/chat?chat_type=wpa&uin=" + tvQQNumber.getText().toString();
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                break;
            case R.id.tv_call_phone:
                RxPermissions rxPermissions = new RxPermissions(ServiceActivity.this);
                rxPermissions
                        .request(Manifest.permission.CALL_PHONE)
                        .subscribe(granted -> {
                            if (granted) {
                                //同意
                                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tvPhoneNumber.getText().toString()));
                                startActivity(intent);
                            } else {
                                //denied
                                showToast("请打开电话权限");
                            }
                        });
                break;
            default:
        }
    }
}
