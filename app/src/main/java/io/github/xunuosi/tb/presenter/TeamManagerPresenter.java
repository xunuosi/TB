package io.github.xunuosi.tb.presenter;

import android.content.Intent;

import javax.inject.Inject;

import io.github.xunuosi.tb.data.db.DaoSession;
import io.github.xunuosi.tb.view.views.ITeamManagerActivityView;

/**
 * Created by admin on 2017/6/3.
 */

public class TeamManagerPresenter implements ITeamManagerActivityView.Presenter {

    private ITeamManagerActivityView.View view;
    private DaoSession daoSession;

    @Inject
    public TeamManagerPresenter(ITeamManagerActivityView.View view, DaoSession daoSession) {
        this.view = view;
        this.daoSession = daoSession;
    }

    @Override
    public void gotoActivity(Intent intent) {
        if (intent != null) {
            view.gotoActivity(intent);
        }
    }
}
