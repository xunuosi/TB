package io.github.xunuosi.tb.presenter;

import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.github.xunuosi.tb.R;
import io.github.xunuosi.tb.data.db.DaoSession;
import io.github.xunuosi.tb.model.AppConstant;
import io.github.xunuosi.tb.model.bean.Team;
import io.github.xunuosi.tb.views.view.ITeamManagerActivityView;

/**
 * Created by admin on 2017/6/3.
 *
 */

public class TeamManagerPresenter extends BasePresenter<ITeamManagerActivityView, DaoSession> {

    private List<Team> teams = new ArrayList<>();

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

    private List<Team> getData() {
        return model.getTeamDao().loadAll();
    }

    private void initData2Show() {
        view().changeDialogState(true, R.string.attention_loading_data);
        teams = getData();
        if (teams != null || teams.size() != 0) {
            view().showView(teams);
        }
    }

    private void refreshData2Show() {
        view().changeDialogState(true, R.string.attention_loading_data);
        teams.clear();
        teams.addAll(getData());
        if (teams != null || teams.size() != 0) {
            view().changeRVState(false);
            view().showView(teams);
        }
    }

    public void showView(int action) {
        switch (action) {
            case AppConstant.Action.INIT:
                initData2Show();
                break;
            case AppConstant.Action.REFRESH:
                refreshData2Show();
                break;
            case AppConstant.Action.LOAD_MORE:

                break;
        }
    }
}
