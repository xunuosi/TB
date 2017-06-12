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
import io.github.xunuosi.tb.views.view.IPMDetailActivityView;
import io.github.xunuosi.tb.views.view.IPlayerManagerActivityView;

/**
 * Created by admin on 2017/6/3.
 *
 */

public class PMDetailPresenter extends BasePresenter<IPMDetailActivityView, DaoSession> {

    private List<Player> players = new ArrayList<>();
    private Long teamId;
    private String teamName;
    private int position;

    @Inject
    public PMDetailPresenter(IPMDetailActivityView view, DaoSession daoSession) {
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
        teamName = intent.getStringExtra(AppConstant.Team.TEAM_NAME);

        view().showView(teamName);
        view().changeDialogState(false, null);
    }

    /**
     * 记录Spinner View选择信息
     * @param position:Spinner View选择的下标值
     */
    public void saveItemSelected(int position) {
        this.position = position;
    }

    public void savePlayer(String name, String tName, String num) {
    }
}
