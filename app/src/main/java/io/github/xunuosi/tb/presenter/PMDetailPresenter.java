package io.github.xunuosi.tb.presenter;

import android.content.Intent;
import android.text.TextUtils;

import org.greenrobot.greendao.query.QueryBuilder;


import javax.inject.Inject;

import io.github.xunuosi.tb.R;
import io.github.xunuosi.tb.data.db.DaoSession;
import io.github.xunuosi.tb.data.db.PlayerDao;
import io.github.xunuosi.tb.model.AppConstant;
import io.github.xunuosi.tb.model.bean.Player;
import io.github.xunuosi.tb.views.view.IPMDetailActivityView;

/**
 * Created by admin on 2017/6/3.
 *
 */

public class PMDetailPresenter extends BasePresenter<IPMDetailActivityView, DaoSession> {

    private Long teamId;
    private String teamName;
    private int position;
    private int num;
    private String name;
    private String role;
    private String sex = "男";
    private String cardNum;

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
        view().changeDialogState(true, R.string.attention_sending_data);
        if (TextUtils.isEmpty(name)) {
            view().showErrorToastMsg(R.string.attention_name_is_empty);
            return;
        } else if (TextUtils.isEmpty(tName)) {
            view().showErrorToastMsg(R.string.attention_team_is_empty);
            return;
        } else if (TextUtils.isEmpty(num)) {
            view().showErrorToastMsg(R.string.attention_num_is_empty);
            return;
        }
        this.name = name;
        this.num = Integer.valueOf(num);
        this.role = view().getSpinnerValue(position);
        this.cardNum = teamId + "-" + name.hashCode() + "-" + num;
        savePlayer2DataBase();
    }

    /**
     * Check data to save DataBase
     */
    private void savePlayer2DataBase() {
        QueryBuilder<Player> queryBuilder = model.getPlayerDao().queryBuilder();
        queryBuilder.whereOr(PlayerDao.Properties.Name.eq(name),
                PlayerDao.Properties.Name.eq(name));
        Player bean = queryBuilder.unique();
        if (bean == null) {
            bean = new Player();
            bean.setName(name);
            bean.setTeamName(teamName);
            bean.setTeamId(teamId.intValue());
            bean.setSex(sex);
            bean.setPosition(role);
            bean.setCardNum(cardNum);
            model.getPlayerDao().insert(bean);
            view().showToastMsg(R.string.attention_save_success);
            view().clear();
        } else {
            view().showErrorToastMsg(R.string.attention_save_player_error);
        }
        view().changeDialogState(false, null);
    }
}
