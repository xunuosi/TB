package io.github.xunuosi.tb.dagger.module;

import dagger.Module;
import dagger.Provides;
import io.github.xunuosi.tb.dagger.PerActivity;
import io.github.xunuosi.tb.data.db.DaoSession;
import io.github.xunuosi.tb.presenter.TeamManagerPresenter;
import io.github.xunuosi.tb.view.views.ITeamManagerActivityView;

/**
 * Created by admin on 2017/6/3.
 */
@Module
public class TeamManagerModule {
    private ITeamManagerActivityView.View view;

    public TeamManagerModule(ITeamManagerActivityView.View view) {
        this.view = view;
    }

    @Provides
    @PerActivity
    ITeamManagerActivityView.View provideTeamManagerView() {
        return this.view;
    }

    @Provides
    @PerActivity
    TeamManagerPresenter provideTeamManagerPresenter(ITeamManagerActivityView.View view, DaoSession daoSession) {
        return new TeamManagerPresenter(view, daoSession);
    }
}
