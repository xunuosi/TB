package io.github.xunuosi.tb.utils;

import android.content.Context;

/**
 * Created by admin on 2017/6/18.
 * 屏幕相关的类
 */

public class ScreenUtil {

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }
}
