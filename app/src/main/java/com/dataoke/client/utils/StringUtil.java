package com.dataoke.client.utils;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ddllxy
 * @date 2018/1/25 0025
 */

public class StringUtil {
    /**
     * 更改textView中局部文字颜色
     *
     * @param tv
     * @param text      显示的完整文字
     * @param innerText 要改变文字的颜色文案
     * @param color
     */
    public static void setInnerTextColor(TextView tv, String text, String innerText, String color) {
        if (TextUtils.isEmpty(innerText) || tv == null || TextUtils.isEmpty(text)) {
            return;
        }
        Pattern pt = Pattern.compile(innerText);
        Matcher mc = pt.matcher(text);
        if (mc.find()) {
            int start = mc.start();
            int end = mc.end();
            Spannable span = new SpannableString(text);
            span.setSpan(new ForegroundColorSpan(Color.parseColor(color)), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv.setText(span);
        } else {
            tv.setText(text);
        }
    }

    /**
     * 更改textView中局部文字颜色
     *
     * @param text      显示的完整文字
     * @param innerText 要改变文字的颜色文案
     * @param color
     */
    public static Spannable setInnerTextColor(String text, String innerText, String color) {
        if (TextUtils.isEmpty(innerText) || TextUtils.isEmpty(text)) {
            return null;
        }
        Pattern pt = Pattern.compile(innerText);
        Matcher mc = pt.matcher(text);
        if (mc.find()) {
            int start = mc.start();
            int end = mc.end();
            Spannable span = new SpannableString(text);
            span.setSpan(new ForegroundColorSpan(Color.parseColor(color)), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return span;
        } else {
            return null;
        }
    }

    /**
     * 更改textView中局部文字颜色
     *
     * @param text      显示的完整文字
     * @param innerText 要改变文字的颜色文案
     * @param color
     * @param size
     */
    public static Spannable setInnerTextColorAndSize(String text, String innerText, String color, int size) {
        if (TextUtils.isEmpty(innerText) || TextUtils.isEmpty(text)) {
            return null;
        }
        Pattern pt = Pattern.compile(innerText);
        Matcher mc = pt.matcher(text);
        if (mc.find()) {
            int start = mc.start();
            int end = mc.end();
            Spannable span = new SpannableString(text);
            span.setSpan(new ForegroundColorSpan(Color.parseColor(color)), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            span.setSpan(new AbsoluteSizeSpan(size, true), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return span;
        } else {
            return null;
        }
    }


    /**
     * 更改textView中局部文字字体大小
     *
     * @param text      显示的完整文字
     * @param innerText 要改变文字的颜色文案
     */
    public static Spannable setInnerTextSize(String text, String innerText, int size) {
        if (TextUtils.isEmpty(innerText) || TextUtils.isEmpty(text)) {
            return null;
        }
        Pattern pt = Pattern.compile(innerText);
        Matcher mc = pt.matcher(text);
        if (mc.find()) {
            int start = mc.start();
            int end = mc.end();
            Spannable span = new SpannableString(text);
            span.setSpan(new AbsoluteSizeSpan(size, true), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return span;
        }
        return null;
    }
}
