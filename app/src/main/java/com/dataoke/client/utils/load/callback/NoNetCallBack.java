package com.dataoke.client.utils.load.callback;


import com.dataoke.client.R;
import com.dataoke.client.utils.load.Callback;

/**
 * @author ddllxy
 * @date 2018/1/29 0029
 */

public class NoNetCallBack extends Callback {
    @Override
    protected int onCreateView() {
        return R.layout.layout_no_net;
    }
}
