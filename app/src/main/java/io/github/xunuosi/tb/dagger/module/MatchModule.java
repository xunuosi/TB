package io.github.xunuosi.tb.dagger.module;

import dagger.Module;
import dagger.Provides;
import io.github.xunuosi.tb.dagger.PerActivity;
import io.github.xunuosi.tb.data.db.DaoSession;
import io.github.xunuosi.tb.presenter.MatchPresenter;
import io.github.xunuosi.tb.views.view.IMatchActivityView;

/**
 * Created by admin on 2017/6/3.
 *
 */
@Module
public class MatchModule {
    private IMatchActivityView view;

    public MatchModule(IMatchActivityView view) {
        this.view = view;
    }

    @Provides
    @PerActivity
    IMatchActivityView provideCBattleView() {
        return this.view;
    }

    @Provides
    @PerActivity
    MatchPresenter provideChooseTeamPresenter(IMatchActivityView view, DaoSession daoSession) {
        return new MatchPresenter(view, daoSession);
    }
}
