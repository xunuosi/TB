package io.github.xunuosi.tb.presenter;

import android.content.Intent;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.github.xunuosi.tb.R;
import io.github.xunuosi.tb.TBApplication;
import io.github.xunuosi.tb.data.db.DaoSession;
import io.github.xunuosi.tb.model.AppConstant;
import io.github.xunuosi.tb.model.bean.Team;
import io.github.xunuosi.tb.views.activity.MatchActivity;
import io.github.xunuosi.tb.views.view.IChooseBattleActivityView;

/**
 * Created by admin on 2017/6/3.
 */

public class ChooseBattlePresenter extends BasePresenter<IChooseBattleActivityView, DaoSession> {

    private List<Team> teams = new ArrayList<>();
    private int htItemPosition;
    private int vtItemPosition;

    @Inject
    public ChooseBattlePresenter(IChooseBattleActivityView view, DaoSession daoSession) {
        bindView(view);
        setModel(daoSession);
    }

    public void gotoActivity(Intent intent) {
        if (intent != null) {
            intent.putExtra(AppConstant.Team.TEAM_TYPE_HOME, teams.get(htItemPosition));
            intent.putExtra(AppConstant.Team.TEAM_TYPE_VISITING, teams.get(vtItemPosition));
            view().gotoActivity(intent);
        }
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
        if (checkDataIsEmpty(htItemPosition) || checkDataIsEmpty(vtItemPosition)) {
            view().showErrorToastMsg(R.string.attention_team_is_empty);
            return;
        }
        if (htItemPosition == vtItemPosition) {
            view().showErrorToastMsg(R.string.attention_select_same_team);
            return;
        }
        this.htItemPosition = htItemPosition;
        this.vtItemPosition = vtItemPosition;
        gotoActivity(MatchActivity.getCallIntent(TBApplication.getContext()));
    }
}
