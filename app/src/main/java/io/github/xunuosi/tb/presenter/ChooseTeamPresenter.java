package io.github.xunuosi.tb.presenter;

import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.github.xunuosi.tb.R;
import io.github.xunuosi.tb.data.db.DaoSession;
import io.github.xunuosi.tb.model.bean.Team;
import io.github.xunuosi.tb.views.view.IChooseTeamActivityView;

/**
 * Created by admin on 2017/6/3.
 *
 */

public class ChooseTeamPresenter extends BasePresenter<IChooseTeamActivityView, DaoSession> {

    private List<Team> teams = new ArrayList<>();

    @Inject
    public ChooseTeamPresenter(IChooseTeamActivityView view, DaoSession daoSession) {
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

    public void initData2Show() {
        view().changeDialogState(true, R.string.attention_loading_data);
        teams = model.getTeamDao().loadAll();
        if (teams != null || teams.size() != 0) {
            view().showView(teams);
        }
    }
}
