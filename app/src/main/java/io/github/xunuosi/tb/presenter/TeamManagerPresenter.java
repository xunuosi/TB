package io.github.xunuosi.tb.presenter;

import android.content.Intent;

import javax.inject.Inject;

import io.github.xunuosi.tb.data.db.DaoSession;
import io.github.xunuosi.tb.views.view.ITeamManagerActivityView;

/**
 * Created by admin on 2017/6/3.
 */

public class TeamManagerPresenter extends BasePresenter<ITeamManagerActivityView, DaoSession> {

    @Inject
    public TeamManagerPresenter(ITeamManagerActivityView view, DaoSession daoSession) {
        bindView(view);
        setModel(daoSession);
    }

    public void gotoActivity(Intent intent) {
        if (intent != null) {
            view().gotoActivity(intent);
        }
    }


    @Override
    protected void updateView() {

    }
}
