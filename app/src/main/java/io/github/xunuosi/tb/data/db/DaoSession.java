package io.github.xunuosi.tb.data.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import io.github.xunuosi.tb.model.bean.Player;
import io.github.xunuosi.tb.model.bean.Team;

import io.github.xunuosi.tb.data.db.PlayerDao;
import io.github.xunuosi.tb.data.db.TeamDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig playerDaoConfig;
    private final DaoConfig teamDaoConfig;

    private final PlayerDao playerDao;
    private final TeamDao teamDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        playerDaoConfig = daoConfigMap.get(PlayerDao.class).clone();
        playerDaoConfig.initIdentityScope(type);

        teamDaoConfig = daoConfigMap.get(TeamDao.class).clone();
        teamDaoConfig.initIdentityScope(type);

        playerDao = new PlayerDao(playerDaoConfig, this);
        teamDao = new TeamDao(teamDaoConfig, this);

        registerDao(Player.class, playerDao);
        registerDao(Team.class, teamDao);
    }
    
    public void clear() {
        playerDaoConfig.clearIdentityScope();
        teamDaoConfig.clearIdentityScope();
    }

    public PlayerDao getPlayerDao() {
        return playerDao;
    }

    public TeamDao getTeamDao() {
        return teamDao;
    }

}
