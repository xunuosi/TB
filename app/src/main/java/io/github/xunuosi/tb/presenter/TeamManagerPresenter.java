package io.github.xunuosi.tb.presenter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.github.xunuosi.tb.R;
import io.github.xunuosi.tb.data.db.DaoSession;
import io.github.xunuosi.tb.model.AppConstant;
import io.github.xunuosi.tb.model.bean.Team;
import io.github.xunuosi.tb.views.activity.TMDetailActivity;
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

    /**
     * @param position:点击时操作的Item的位置
     */
    public void editTeam(Context context, int position) {
        if (position != -1) {
            Team actionTeam = teams.get(position);
            gotoActivityWithTeam(TMDetailActivity.getCallIntent(context), actionTeam);
        }
    }

    private void gotoActivityWithTeam(Intent intent, Team team) {
        if (intent != null) {
            intent.putExtra(AppConstant.Team.BEAN, team);
            view().gotoActivity(intent);
        }
    }

    public void deleteTeam(int position) {
        if (position != -1) {
            try {
                view().changeDialogState(true, R.string.attention_sending_data);
                Team actionTeam = teams.get(position);
                model.getTeamDao().delete(actionTeam);
            } catch (Exception e) {
                e.printStackTrace();
                deleteError(e.getMessage());
                return;
            }
            view().delAdapterData(position);
            view().changeDialogState(false, null);
        }
    }

    private void deleteError(String message) {

    }
}
