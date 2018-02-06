package com.dataoke.client.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

/**
 * @author ddllxy
 * @date 2018/2/6 0006
 */

public class ShareUtil {

    /**
     * 分享文字内容
     *
     * @param dialogTitle 分享对话框标题
     * @param content     分享内容（文字）
     */
    public static void shareText(Context context, String dialogTitle, String content) {
        if (TextUtils.isEmpty(content)) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_TEXT, content);

        // 设置弹出框标题
        if (!TextUtils.isEmpty(dialogTitle)) {  // 自定义标题
            context.startActivity(Intent.createChooser(intent, dialogTitle));
        } else { // 系统默认标题
            context.startActivity(intent);
        }
    }

    /**
     * 分享图片和文字内容
     *
     * @param dialogTitle 分享对话框标题
     * @param content     分享内容（文字）
     * @param uri         图片资源URI
     */
    public static void shareImg(Context context, String dialogTitle, String content,
                                Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_STREAM, uri);

        if (!TextUtils.isEmpty(content)) {
            intent.putExtra(Intent.EXTRA_TEXT, content);
        }

        // 设置弹出框标题
        if (!TextUtils.isEmpty(dialogTitle)) { // 自定义标题
            context.startActivity(Intent.createChooser(intent, dialogTitle));
        } else { // 系统默认标题
            context.startActivity(intent);
        }
    }
}
