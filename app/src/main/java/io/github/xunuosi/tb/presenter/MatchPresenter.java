package io.github.xunuosi.tb.presenter;

import android.content.Intent;
import android.os.Parcelable;

import javax.inject.Inject;

import io.github.xunuosi.tb.data.db.DaoSession;
import io.github.xunuosi.tb.model.AppConstant;
import io.github.xunuosi.tb.model.bean.Team;
import io.github.xunuosi.tb.views.view.IMatchActivityView;

/**
 * Created by admin on 2017/6/26.
 *
 */

public class MatchPresenter extends BasePresenter<IMatchActivityView, DaoSession> {
    private long hTeamId;
    private long vTeamId;

    @Inject
    public MatchPresenter(IMatchActivityView view, DaoSession daoSession) {
        bindView(view);
        setModel(daoSession);
    }

    @Override
    protected void updateView() {

    }


    public void initToShow(Intent intent) {
        Team hTeamBean = null;
        Team vTeamBean = null;
        if (intent != null) {
            hTeamBean = intent.getParcelableExtra(AppConstant.Team.TEAM_TYPE_HOME);
            vTeamBean = intent.getParcelableExtra(AppConstant.Team.TEAM_TYPE_VISITING);
        }

        if (hTeamBean != null && vTeamBean != null) {
            view().test(hTeamBean.getName(), vTeamBean.getName());
        }
    }
}
