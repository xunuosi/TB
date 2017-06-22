package io.github.xunuosi.tb.dagger.module;

import android.app.Activity;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;
import io.github.xunuosi.tb.dagger.PerActivity;
import io.github.xunuosi.tb.data.db.DaoSession;
import io.github.xunuosi.tb.model.bean.Team;
import io.github.xunuosi.tb.presenter.ChooseBattlePresenter;
import io.github.xunuosi.tb.presenter.ChooseTeamPresenter;
import io.github.xunuosi.tb.views.view.IChooseBattleActivityView;
import io.github.xunuosi.tb.views.view.IChooseTeamActivityView;

/**
 * Created by admin on 2017/6/3.
 *
 */
@Module
public class ChooseBattleModule {
    private IChooseBattleActivityView view;

    public ChooseBattleModule(IChooseBattleActivityView view) {
        this.view = view;
    }

    @Provides
    @PerActivity
    IChooseBattleActivityView provideCBattleView() {
        return this.view;
    }

    @Provides
    @PerActivity
    ChooseBattlePresenter provideChooseTeamPresenter(IChooseBattleActivityView view, DaoSession daoSession) {
        return new ChooseBattlePresenter(view, daoSession);
    }

    @Provides
    @PerActivity
    ArrayAdapter<String> provideArrayAdapter(Activity activity,DaoSession daoSession) {
        List<String> mList = new ArrayList<>();
        for (Team bean : daoSession.getTeamDao().loadAll()) {
            mList.add(bean.getName());
        }
        return new ArrayAdapter<>(activity, android.R.layout.simple_spinner_item, mList);
    }
}
