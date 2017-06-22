package io.github.xunuosi.tb.presenter;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.github.xunuosi.tb.R;
import io.github.xunuosi.tb.data.db.DaoSession;
import io.github.xunuosi.tb.model.AppConstant;
import io.github.xunuosi.tb.model.bean.Team;
import io.github.xunuosi.tb.views.view.IChooseBattleActivityView;
import io.github.xunuosi.tb.views.view.IChooseTeamActivityView;

/**
 * Created by admin on 2017/6/3.
 *
 */

public class ChooseBattlePresenter extends BasePresenter<IChooseBattleActivityView, DaoSession> {

    private List<Team> teams = new ArrayList<>();

    @Inject
    public ChooseBattlePresenter(IChooseBattleActivityView view, DaoSession daoSession) {
        bindView(view);
        setModel(daoSession);
    }

    public void gotoActivity(Intent intent) {
//        if (!checkDataIsEmpty()) {
//            if (intent != null) {
//                intent.putExtra(AppConstant.Team.TEAM_ID, teams.get(position).getTeamId());
//                intent.putExtra(AppConstant.Team.TEAM_NAME, teams.get(position).getName());
//                view().gotoActivity(intent);
//            }
//        } else {
//            view().showErrorToastMsg(R.string.attention_team_is_empty);
//        }
    }


    @Override
    protected void updateView() {

    }


    /**
     * 检查用户是否正确选择球队
     */
    private boolean checkDataIsEmpty(int position) {
        if (teams.size() == 0) {
            teams = model.getTeamDao().loadAll();
        }
        if (teams == null || teams.size() == 0) {
            return true;
        }
        return TextUtils.isEmpty(teams.get(position).getName().trim());
    }

    public void checkoutData(int htItemPosition, int vtItemPosition) {
        if (htItemPosition == vtItemPosition) {
            view().showErrorToastMsg(R.string.attention_select_same_team);
            return;
        }
        if (checkDataIsEmpty(htItemPosition) || checkDataIsEmpty(vtItemPosition)) {
            view().showErrorToastMsg(R.string.attention_team_is_empty);
            return;
        }

    }
}
