package io.github.xunuosi.tb.presenter;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;

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
    private String tName;
    private final int QUERY_LIMIT = 10;

    @Inject
    public PlayerManagerPresenter(IPlayerManagerActivityView view, DaoSession daoSession) {
        bindView(view);
        setModel(daoSession);
    }

    public void gotoActivity(Intent intent) {
        if (intent != null) {
            intent.putExtra(AppConstant.Team.TEAM_ID, teamId);
            intent.putExtra(AppConstant.Team.TEAM_NAME, tName);
            view().gotoActivity(intent);
        }
    }


    @Override
    protected void updateView() {

    }

    public void showView(@Nullable Intent intent, int action) {

        switch (action) {
            case AppConstant.Action.INIT:
                if (intent != null) {
                    initData2Show(intent);
                }
                break;
            case AppConstant.Action.REFRESH:
                refreshData2Show();
                break;
            case AppConstant.Action.LOAD_MORE:
                view().changeDialogState(true, R.string.attention_loading_data);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        loadMoreData2Show();
                    }
                }, 1000);
                break;
        }
    }

    private void loadMoreData2Show() {
        List<Player> mList = getData(null);
        if (mList == null || mList.size() == 0) {
            view().setRVLoadMoreState(false);
        } else {
            players.addAll(mList);
            view().showLoadMoreView(players);
        }
        view().changeDialogState(false, null);
    }

    private void refreshData2Show() {
        view().setRVLoadMoreState(true);
        initData2Show(null);
        view().changeRVState(false);
    }

    private void initData2Show(@Nullable Intent intent) {
        view().changeDialogState(true, R.string.attention_loading_data);
        players.clear();
        if (intent != null) {
            players.addAll(getData(intent));
        } else {
            players.addAll(getData(null));
        }

        if (players != null && players.size() != 0) {
            view().showEmptyView(false);
            view().showRefreshView(players);
        } else {
            view().showEmptyView(true);
            view().changeDialogState(false, R.string.attention_loading_data_error);
        }
    }

    private List<Player> getData(@Nullable Intent intent) {
        if (intent != null) {
            teamId = intent.getLongExtra(AppConstant.Team.TEAM_ID, -1);
            tName = intent.getStringExtra(AppConstant.Team.TEAM_NAME);
        }

        if (teamId != -1) {
            QueryBuilder<Player> queryBuilder = model.getPlayerDao().queryBuilder();
            queryBuilder.where(PlayerDao.Properties.TeamId.eq(teamId));
            List<Player> list;
            list = queryBuilder.limit(QUERY_LIMIT).offset(players.size()).list();
            return list;
        } else {
            return null;
        }
    }
}
