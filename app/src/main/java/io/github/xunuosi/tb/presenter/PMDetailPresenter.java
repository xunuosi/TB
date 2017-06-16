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
    private final int ACTION_TYPE_SAVE = 0;
    private final int ACTION_TYPE_EDIT= 1;
    // 记录当前操作是修改还是保存
    private int actionType;
    private Long teamId;
    private String teamName;
    private int position;
    private int num;
    private String name;
    private String role;
    private String sex = "男";
    private String cardNum;
    private Player bean;


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
        bean = intent.getParcelableExtra(AppConstant.Player.BEAN);
        if (bean != null) {
            actionType = ACTION_TYPE_EDIT;
            teamId = bean.getId();
            teamName = bean.getTeamName();
            view().showView(teamName, bean.getName(), bean.getPosition(), String.valueOf(bean.getNum()));
        } else {
            actionType = ACTION_TYPE_SAVE;
            teamId = intent.getLongExtra(AppConstant.Team.TEAM_ID, -1);
            teamName = intent.getStringExtra(AppConstant.Team.TEAM_NAME);
            view().showView(teamName);
        }

        view().changeDialogState(false, null);
    }

    /**
     * 记录Spinner View选择信息
     * @param position:Spinner View选择的下标值
     */
    public void saveItemSelected(int position) {
        this.position = position;
    }

    public void saveOrEditPlayer(String name, String tName, String num) {
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
        view().changeDialogState(true, R.string.attention_sending_data);
        this.name = name;
        this.num = Integer.valueOf(num);
        this.role = view().getSpinnerValue(position);
        this.cardNum = teamId + "-" + name.hashCode() + "-" + num;
        checkoutAction();
    }

    /**
     * 判断当前操作是新增保存还是进行修改后的保存
     */
    private void checkoutAction() {
        switch (actionType) {
            case ACTION_TYPE_SAVE:
                savePlayer2DataBase();
                break;
            case ACTION_TYPE_EDIT:
                editPlayer2DataBase();
                break;
        }
    }

    /**
     * 判断数据库中是否存在该球员信息
     * @return true:exist false:Is not exist
     */
    private boolean checkoutDataIsExist() {
        try {
            QueryBuilder<Player> queryBuilder = model.getPlayerDao().queryBuilder();
            queryBuilder.whereOr(PlayerDao.Properties.Name.eq(name),
                    PlayerDao.Properties.Num.eq(num));
            queryBuilder.uniqueOrThrow();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private void editPlayer2DataBase() {
        if (checkoutDataIsExist()) {
//            bean.setId(id);
            bean.setName(name);
            bean.setNum(num);
            bean.setPosition(role);
            model.getPlayerDao().update(bean);
            view().showToastMsg(R.string.attention_save_success);
        } else {
            view().showErrorToastMsg(R.string.attention_fail);
        }
        view().changeDialogState(false, null);
    }

    /**
     * Check data to save DataBase
     */
    private void savePlayer2DataBase() {
        long rowNum = -1;
        if (checkoutDataIsExist()) {
            view().showErrorToastMsg(R.string.attention_save_player_error);
        } else {
            Player player = new Player();
            player.setName(name);
            player.setTeamName(teamName);
            player.setTeamId(teamId.intValue());
            player.setSex(sex);
            player.setPosition(role);
            player.setCardNum(cardNum);
            player.setNum(num);
            rowNum = model.getPlayerDao().insert(player);
        }
        if (rowNum != -1) {
            view().showToastMsg(R.string.attention_save_success);
            view().clear();
        } else {
            view().showErrorToastMsg(R.string.attention_save_player_error);
        }
        view().changeDialogState(false, null);
    }
}
