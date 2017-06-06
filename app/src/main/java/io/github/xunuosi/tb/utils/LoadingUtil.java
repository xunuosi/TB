package io.github.xunuosi.tb.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;

import io.github.xunuosi.tb.R;
import io.github.xunuosi.tb.views.widget.MyProgressDialog;


/**
 * Created by tcloud on 12/30/16.
 */
public class LoadingUtil {

    private static ProgressDialog processDia;
    private static MyProgressDialog progressDialog;

    /**
     * for 空白页
     * @param context
     * @param processTips
     */
    public static void showProgressDialog(Context context, String processTips) {
        if (progressDialog == null) {
            if (TextUtils.isEmpty(processTips)) {
                processTips = "正在加载...";
            }
            progressDialog = new MyProgressDialog(context, processTips, R.drawable.loading);
            progressDialog.show();
        }
    }

    public static void closeProgressDialog() {
        if (progressDialog != null) {
            if (progressDialog.isShowing()) {
                progressDialog.cancel();
            }
            progressDialog = null;
        }
    }

    /**
     * for 有内容
     *
     * @param context
     */
    public static void showLoadingDialog(Context context, String message, boolean isCancelable) {
        if (processDia == null) {
            processDia = new ProgressDialog(context, R.style.loading_dialog);
            //点击提示框外面是否取消提示框
            processDia.setCanceledOnTouchOutside(false);
            //点击返回键是否取消提示框
            processDia.setCancelable(isCancelable);
            processDia.setIndeterminate(true);
            processDia.setMessage(message);
            processDia.show();
        }
    }

    /**
     * 关闭加载对话框
     */
    public static void closeLoadingDialog() {
        if (processDia != null) {
            if (processDia.isShowing()) {
                processDia.cancel();
            }
            processDia = null;
        }
    }
}
