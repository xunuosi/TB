package io.github.xunuosi.tb.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Toast;

import org.greenrobot.greendao.query.QueryBuilder;

import javax.inject.Inject;

import io.github.xunuosi.tb.R;
import io.github.xunuosi.tb.data.db.DaoSession;
import io.github.xunuosi.tb.data.db.TeamDao;
import io.github.xunuosi.tb.model.AppConstant;
import io.github.xunuosi.tb.model.bean.Team;
import io.github.xunuosi.tb.views.activity.TeamManagerActivity;
import io.github.xunuosi.tb.views.view.ITMDetailView;

/**
 * Created by admin on 2017/6/3.
 *
 */

public class TMDetailPresenter extends BasePresenter<ITMDetailView, DaoSession> {
    private final int ACTION_TYPE_SAVE = 0;
    private final int ACTION_TYPE_EDIT = 1;
    private Context context;
    private Team team;
    private int actionType;

    @Inject
    public TMDetailPresenter(ITMDetailView view, DaoSession daoSession) {
        context = (Context) view;
        bindView(view);
        setModel(daoSession);
    }

    @Override
    protected void updateView() {

    }

    public void saveOrEditTeam(@Nullable String avatorUrl, String tName) {
        if (checkData(avatorUrl, tName)) {
            switch (actionType) {
                case ACTION_TYPE_SAVE:
                    addTeam(avatorUrl, tName);
                    break;
                case ACTION_TYPE_EDIT:
                    editTeam(avatorUrl, tName);
                    break;
            }
        }
    }

    private void editTeam(@Nullable String avatorUrl, String tName) {
        view().changeDialogState(true, R.string.attention_sending_data);
        team.setName(tName);
        model.getTeamDao().update(team);
        gotoActivity(TeamManagerActivity.getCallIntent(context));
        view().changeDialogState(false, null);
    }

    private void addTeam(@Nullable String avatorUrl, String tName) {
        view().changeDialogState(true, R.string.attention_sending_data);
        Team bean = new Team();
        bean.setName(tName);
        bean.setTeamId(tName.hashCode());
        try {
            model.getTeamDao().insert(bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        gotoActivity(TeamManagerActivity.getCallIntent(context));
        view().changeDialogState(false, null);
    }

    private boolean checkData(String avatorUrl, String tName) {
        if (TextUtils.isEmpty(tName)) {
            String msg = context.getString(R.string.attention_tName_empty);
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            return false;
        }
        QueryBuilder<Team> qb = model.getTeamDao().queryBuilder();
        qb.where(TeamDao.Properties.Name.eq(tName));
        try {
            qb.uniqueOrThrow();
        } catch (Exception e) {
            return true;
        }
        String msg = context.getString(R.string.attention_tName_is_existed);
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        return false;
    }

    public void gotoActivity(Intent intent) {
        if (intent != null) {
            view().gotoActivity(intent);
        }
    }

    public void initShow(Intent intent) {
        if (intent != null) {
            team = intent.getParcelableExtra(AppConstant.Team.BEAN);
            if (team != null) {
                actionType = ACTION_TYPE_EDIT;
                view().show(team);
            } else {
                actionType = ACTION_TYPE_SAVE;
            }
        }
    }
}
