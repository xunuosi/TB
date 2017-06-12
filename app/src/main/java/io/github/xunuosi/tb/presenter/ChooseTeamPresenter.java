package io.github.xunuosi.tb.presenter;

import android.content.Intent;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.github.xunuosi.tb.R;
import io.github.xunuosi.tb.data.db.DaoSession;
import io.github.xunuosi.tb.model.AppConstant;
import io.github.xunuosi.tb.model.bean.Team;
import io.github.xunuosi.tb.views.view.IChooseTeamActivityView;

/**
 * Created by admin on 2017/6/3.
 *
 */

public class ChooseTeamPresenter extends BasePresenter<IChooseTeamActivityView, DaoSession> {

    private List<Team> teams = new ArrayList<>();
    private int position;

    @Inject
    public ChooseTeamPresenter(IChooseTeamActivityView view, DaoSession daoSession) {
        bindView(view);
        setModel(daoSession);
    }

    public void gotoActivity(Intent intent) {
        if (!checkDataIsEmpty()) {
            if (intent != null) {
                intent.putExtra(AppConstant.Team.TEAM_ID, teams.get(position).getId());
                view().gotoActivity(intent);
            }
        } else {
            view().showErrorToastMsg(R.string.attention_team_is_empty);
        }
    }


    @Override
    protected void updateView() {

    }

    /**
     * 记录Spinner View选择信息
     * @param position:Spinner View选择的下标值
     */
    public void saveItemSelected(int position) {
        this.position = position;
    }

    /**
     * 检查用户是否正确选择球队
     */
    private boolean checkDataIsEmpty() {
        if (teams.size() == 0) {
            teams = model.getTeamDao().loadAll();
        }
        if (teams == null || teams.size() == 0) {
            return true;
        }
        return TextUtils.isEmpty(teams.get(position).getName().trim());
    }
}
