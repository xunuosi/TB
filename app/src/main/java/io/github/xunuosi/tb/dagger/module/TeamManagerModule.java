package io.github.xunuosi.tb.dagger.module;

import dagger.Module;
import dagger.Provides;
import io.github.xunuosi.tb.dagger.PerActivity;
import io.github.xunuosi.tb.data.db.DaoSession;
import io.github.xunuosi.tb.presenter.TeamManagerPresenter;
import io.github.xunuosi.tb.views.view.ITeamManagerActivityView;

/**
 * Created by admin on 2017/6/3.
 *
 */
@Module
public class TeamManagerModule {
    private ITeamManagerActivityView view;

    public TeamManagerModule(ITeamManagerActivityView view) {
        this.view = view;
    }

    @Provides
    @PerActivity
    ITeamManagerActivityView provideTeamManagerView() {
        return this.view;
    }

    @Provides
    @PerActivity
    TeamManagerPresenter provideTeamManagerPresenter(ITeamManagerActivityView view, DaoSession daoSession) {
        return new TeamManagerPresenter(view, daoSession);
    }
}
