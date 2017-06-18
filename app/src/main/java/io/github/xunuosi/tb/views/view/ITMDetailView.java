package io.github.xunuosi.tb.views.view;

import android.content.Intent;
import android.support.annotation.Nullable;

import io.github.xunuosi.tb.model.bean.Team;

/**
 * Created by admin on 2017/6/3.
 *
 */

public interface ITMDetailView {

    void gotoActivity(Intent intent);

    void changeDialogState(boolean isShow, @Nullable Integer msgId);

    void show(Team team);
}
