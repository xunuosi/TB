package io.github.xunuosi.tb.presenter;

import android.content.Intent;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.github.xunuosi.tb.R;
import io.github.xunuosi.tb.data.db.DaoSession;
import io.github.xunuosi.tb.data.db.PlayerDao;
import io.github.xunuosi.tb.model.AppConstant;
import io.github.xunuosi.tb.model.bean.Player;
import io.github.xunuosi.tb.views.view.IPlayerManagerActivityView;

/**
 * Created by admin on 2017/6/3.
 *
 */

public class PlayerManagerPresenter extends BasePresenter<IPlayerManagerActivityView, DaoSession> {

    private List<Player> players = new ArrayList<>();
    private Long teamId;

    @Inject
    public PlayerManagerPresenter(IPlayerManagerActivityView view, DaoSession daoSession) {
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

    public void initData2Show(Intent intent) {
        view().changeDialogState(true, R.string.attention_loading_data);
        teamId = intent.getLongExtra(AppConstant.Team.TEAM_ID, -1);
        if (teamId != -1) {
            QueryBuilder<Player> queryBuilder = model.getPlayerDao().queryBuilder();
            queryBuilder.where(PlayerDao.Properties.TeamId.eq(teamId));
            players = queryBuilder.list();
            if (players != null && players.size() != 0) {
                view().showView(players);
            } else {
                view().changeDialogState(false, null);
            }
        } else {
            view().changeDialogState(false, null);
        }
    }
}
