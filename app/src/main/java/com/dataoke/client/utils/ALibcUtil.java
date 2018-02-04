package com.dataoke.client.utils;

import android.app.Activity;
import android.content.Context;

import com.alibaba.baichuan.android.trade.AlibcTrade;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeCallback;
import com.alibaba.baichuan.android.trade.model.AlibcShowParams;
import com.alibaba.baichuan.android.trade.model.OpenType;
import com.alibaba.baichuan.android.trade.page.AlibcBasePage;
import com.alibaba.baichuan.android.trade.page.AlibcDetailPage;
import com.alibaba.baichuan.android.trade.page.AlibcMyCartsPage;
import com.alibaba.baichuan.android.trade.page.AlibcMyOrdersPage;
import com.alibaba.baichuan.trade.biz.context.AlibcResultType;
import com.alibaba.baichuan.trade.biz.context.AlibcTradeResult;
import com.alibaba.baichuan.trade.biz.core.taoke.AlibcTaokeParams;
import com.alibaba.baichuan.trade.biz.login.AlibcLogin;
import com.alibaba.baichuan.trade.biz.login.AlibcLoginCallback;

import java.util.HashMap;

/**
 * @author ddllxy
 * @date 2018/2/1 0001
 * 阿里百川工具类
 */

public class ALibcUtil {
    private ALibcUtil() {

    }

    public static void Login(Context context) {
        AlibcLogin alibcLogin = AlibcLogin.getInstance();

        alibcLogin.showLogin(new AlibcLoginCallback() {
            @Override
            public void onSuccess(int i) {
                LogUtil.i("登陆成功");
            }

            @Override
            public void onFailure(int code, String msg) {
                LogUtil.i("登陆失败");
            }
        });
    }


    public static void loginOut(Context context) {
        AlibcLogin alibcLogin = AlibcLogin.getInstance();

        alibcLogin.logout(new AlibcLoginCallback() {
            @Override
            public void onSuccess(int i) {
                LogUtil.i("退出成功");
            }

            @Override
            public void onFailure(int i, String s) {
                LogUtil.i("退出失败");
            }
        });
    }

    /**
     * @param activity
     * @param goods_id 显示商品详情页
     */
    public static void showDetail(Activity activity, String goods_id, boolean isTaoke) {
        AlibcBasePage alibcBasePage = new AlibcDetailPage(goods_id);
        AlibcShowParams alibcShowParams = new AlibcShowParams(OpenType.Native, false);

        HashMap<String, String> exParams = new HashMap<>(16);
        exParams.put("isv_code", "appisvcode");
        exParams.put("xiaotaobao", "小淘宝责任有限公司");//自定义参数部分，可任意增删改

        AlibcTaokeParams alibcTaokeParams = new AlibcTaokeParams(); // 若非淘客taokeParams设置为null即可
        if (isTaoke) {
            alibcTaokeParams = new AlibcTaokeParams(); // 若非淘客taokeParams设置为null即可
            alibcTaokeParams.adzoneid = "57328044";
            alibcTaokeParams.pid = "mm_26632322_6858406_23810104";
            alibcTaokeParams.subPid = "mm_26632322_6858406_23810104";
            alibcTaokeParams.extraParams = new HashMap<>();
            alibcTaokeParams.extraParams.put("taokeAppkey", "23373400");
        } else {
            alibcTaokeParams = null;
        }
        AlibcTrade.show(activity, alibcBasePage, alibcShowParams, alibcTaokeParams, exParams, new DemoTradeCallback());
    }

    /**
     * 显示订单
     *
     * @param activity
     * @param isAllOrder
     */
    public static void showOrder(Activity activity, boolean isAllOrder) {
        int orderType = 0;//订单页面参数，仅在H5方式下有效
        AlibcShowParams alibcShowParams = new AlibcShowParams(OpenType.Native, false);
        HashMap<String, String> exParams = new HashMap<>(16);
        exParams.put("isv_code", "appisvcode");
        exParams.put("xiaotaobao", "小淘宝责任有限公司");//自定义参数部分，可任意增删改

        AlibcBasePage alibcBasePage = new AlibcMyOrdersPage(orderType, isAllOrder);
        AlibcTrade.show(activity, alibcBasePage, alibcShowParams, null, exParams, new DemoTradeCallback());
    }

    /**
     * @param activity 显示我的购物车
     */
    public static void showCart(Activity activity) {
        AlibcShowParams alibcShowParams = new AlibcShowParams(OpenType.Native, false);
        HashMap<String, String> exParams = new HashMap<>(16);
        exParams.put("isv_code", "appisvcode");
        exParams.put("xiaotaobao", "小淘宝责任有限公司");//自定义参数部分，可任意增删改
        AlibcBasePage alibcBasePage = new AlibcMyCartsPage();
        AlibcTrade.show(activity, alibcBasePage, alibcShowParams, null, exParams, new DemoTradeCallback());
    }

    static class DemoTradeCallback implements AlibcTradeCallback {

        @Override
        public void onTradeSuccess(AlibcTradeResult tradeResult) {
            //当addCartPage加购成功和其他page支付成功的时候会回调
            LogUtil.i("onTradeSuccess", "onTradeSuccess");
            if (tradeResult.resultType.equals(AlibcResultType.TYPECART)) {
                //加购成功
                ToastUtil.show("加购成功");
            } else if (tradeResult.resultType.equals(AlibcResultType.TYPEPAY)) {
                //支付成功
                ToastUtil.show("支付成功,成功订单号为" + tradeResult.payResult.paySuccessOrders);
            }
        }

        @Override
        public void onFailure(int errCode, String errMsg) {
            ToastUtil.show("电商SDK出错,错误码=" + errCode + " / 错误消息=" + errMsg);
            LogUtil.i("onFailure", "onTradeSuccess");
        }
    }
}
