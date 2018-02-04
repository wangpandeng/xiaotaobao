package com.dataoke.client.utils.load.callback;

import com.dataoke.client.R;
import com.dataoke.client.utils.load.Callback;


/**
 * Description:TODO
 * Create Time:2017/9/4 10:22
 * Author:KingJA
 * Email:kingjavip@gmail.com
 * @author Administrator
 */

public class EmptyCallback extends Callback {

    @Override
    protected int onCreateView() {
        return R.layout.layout_empty;
    }

}
