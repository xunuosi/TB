package io.github.xunuosi.tb.view.views;

import android.content.Intent;

/**
 * Created by admin on 2017/6/3.
 *
 */

public interface ITeamManagerActivityView {

    interface View {
        void gotoActivity(Intent intent);
    }

    interface Presenter {
        void gotoActivity(Intent intent);
    }
}
