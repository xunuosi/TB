package io.github.xunuosi.tb.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Toast;

import javax.inject.Inject;

import io.github.xunuosi.tb.R;
import io.github.xunuosi.tb.data.db.DaoSession;
import io.github.xunuosi.tb.model.bean.Team;
import io.github.xunuosi.tb.views.activity.TeamManagerActivity;
import io.github.xunuosi.tb.views.view.ITMDetailView;

/**
 * Created by admin on 2017/6/3.
 *
 */

public class TMDetailPresenter extends BasePresenter<ITMDetailView, DaoSession> {
    private Context context;

    @Inject
    public TMDetailPresenter(ITMDetailView view, DaoSession daoSession) {
        context = (Context) view;
        bindView(view);
        setModel(daoSession);
    }

    @Override
    protected void updateView() {

    }

    public void addTeam(@Nullable String avatorUrl, String tName) {
        if (checkData(avatorUrl, tName)) {
            view().changeDialogState(true, R.string.attention_sending_data);
            Team bean = new Team();
            bean.setName(tName);
            try {
                model.getTeamDao().insert(bean);
            } catch (Exception e) {
                e.printStackTrace();
            }
            view().changeDialogState(false, null);
            gotoActivity(TeamManagerActivity.getCallIntent((Context) view()));
        }
    }

    private boolean checkData(String avatorUrl, String tName) {
        if (TextUtils.isEmpty(tName)) {
            String msg = context.getString(R.string.attention_tName_empty);
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void gotoActivity(Intent intent) {
        if (intent != null) {
            view().gotoActivity(intent);
        }
    }
}
