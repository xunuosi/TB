package io.github.xunuosi.tb.view.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import io.github.xunuosi.tb.R;


/**
 * Created by tcloud on 12/30/16.
 */
public class MyProgressDialog extends ProgressDialog {

    private AnimationDrawable mAnimation;
    private ImageView mImageView;
    private TextView mTextView;
    private String loadingTip;
    private int resid;

    /**
     * @param context 上下文对象
     * @param loadingTip 显示文字提示信息内容
     */
    public MyProgressDialog(Context context, String loadingTip, int resid) {
        this(context, R.style.loading_dialog, resid, loadingTip);
    }

    public MyProgressDialog(Context context, int theme, int resid, String loadingTip) {
        super(context, theme);
        this.resid = resid;
        this.loadingTip = loadingTip;
        //点击提示框外面是否取消提示框
        setCanceledOnTouchOutside(false);
        //点击返回键是否取消提示框
        setCancelable(true);
        setIndeterminate(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_progress);

        mTextView = (TextView) findViewById(R.id.loadingTv);
        mImageView = (ImageView) findViewById(R.id.loadingIv);

        mImageView.setBackgroundResource(resid);
        // 通过ImageView对象拿到背景显示的AnimationDrawable
        mAnimation = (AnimationDrawable) mImageView.getBackground();

        mImageView.post(new Runnable() {
            @Override
            public void run() {
                mAnimation.start();
            }
        });
        mTextView.setText(loadingTip);
    }
}
