package io.github.xunuosi.tb.views.view;

import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by admin on 2017/6/3.
 *
 */

public interface IChooseBattleActivityView {

        void gotoActivity(Intent intent);

        void changeDialogState(boolean isShow, @Nullable Integer msgId);

        void showErrorToastMsg(int msgId);
}
