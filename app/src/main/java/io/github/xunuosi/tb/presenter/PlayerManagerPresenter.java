package io.github.xunuosi.tb.presenter;

import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.github.xunuosi.tb.R;
import io.github.xunuosi.tb.data.db.DaoSession;
import io.github.xunuosi.tb.model.bean.Player;
import io.github.xunuosi.tb.views.view.IPlayerManagerActivityView;

/**
 * Created by admin on 2017/6/3.
 *
 */

public class PlayerManagerPresenter extends BasePresenter<IPlayerManagerActivityView, DaoSession> {

    private List<Player> players = new ArrayList<>();

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

    public void initData2Show() {
        view().changeDialogState(true, R.string.attention_loading_data);
        players = model.getPlayerDao().loadAll();
        if (players != null || players.size() != 0) {
            view().showView(players);
        }

    }
}
