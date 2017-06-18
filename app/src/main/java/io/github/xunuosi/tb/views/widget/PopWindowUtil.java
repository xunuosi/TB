package io.github.xunuosi.tb.views.widget;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import io.github.xunuosi.tb.R;
import io.github.xunuosi.tb.presenter.BasePresenter;
import io.github.xunuosi.tb.presenter.TeamManagerPresenter;
import io.github.xunuosi.tb.utils.ScreenUtil;

/**
 * Created by admin on 2017/6/18.
 *
 */

public class PopWindowUtil {

    private PopupWindow mWindow;
    private View parent;
    private Context context;
    private int[] mLocation = new int[2];

    private int mWidth;
    private int mHeight;

    private int adapterPosi;
    private BasePresenter presenter;

    public PopWindowUtil(final Context context, final int menuId, View parent) {
        this.context = context;
        this.parent = parent;
        // 解析弹出的菜单
        final View contentView = LayoutInflater.from(context).inflate(menuId, null);
        contentView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        mWidth = contentView.getMeasuredWidth();
        mHeight = contentView.getMeasuredHeight();
        // 通过PopWindow弹出
        mWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, false);
        mWindow.setClippingEnabled(false);
        mWindow.setFocusable(true);

        contentView.findViewById(R.id.tv_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presenter != null) {
                    ((TeamManagerPresenter) presenter).editTeam(context, adapterPosi);
                    dismiss();
                }
            }
        });
        contentView.findViewById(R.id.tv_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presenter != null) {
                    ((TeamManagerPresenter)presenter).deleteTeam(adapterPosi);
                }
            }
        });

    }


    /**
     * 显示弹窗的方法
     */
    public void show(int touchX, int touchY) {
        // 获取在当前窗口内的绝对坐标
        parent.getLocationInWindow(mLocation);

        // 得到当前字符段的左边X坐标+Y坐标
        int posX = touchX;
        int posY = touchY;
        int realY = touchY + mLocation[1] + mHeight;
        // 设置边界值
        if (posX <= 0) posX = 16;
        if ((posX + mWidth) > ScreenUtil.getScreenWidth(context)) {
            posX = ScreenUtil.getScreenWidth(context) - mWidth - 16;
        }
        if (realY > ScreenUtil.getScreenHeight(context) - mHeight) {
            posY = mHeight;
        }
        // 设置阴影效果
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWindow.setElevation(8f);
        }
        mWindow.showAsDropDown(parent, posX, -posY, Gravity.NO_GRAVITY);
    }

    public void dismiss() {
        if (isShowing()) {
            mWindow.dismiss();
        }
    }

    public boolean isShowing() {
        return mWindow.isShowing();
    }

    public void setPresenter(BasePresenter presenter) {
        this.presenter = presenter;
    }

    public void setAdapterPosi(int adapterPosi) {
        this.adapterPosi = adapterPosi;
    }

}
