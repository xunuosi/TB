package io.github.xunuosi.tb.model;

/**
 * Created by xns on 2017/6/1.
 * App的全局常量
 */

public interface AppConstant {

    interface Team {
        String BEAN = "team_bean";
        String TEAM_ID = "team_id";
        String TEAM_NAME = "team_name";
    }

    interface Player {
        String BEAN = "player_bean";
    }

    interface Action {
        int INIT = 0;
        int REFRESH = 1;
        int LOAD_MORE = 2;
    }
}
