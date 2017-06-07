package io.github.xunuosi.tb.dagger.module;

import dagger.Module;
import dagger.Provides;
import io.github.xunuosi.tb.dagger.PerActivity;
import io.github.xunuosi.tb.data.db.DaoSession;
import io.github.xunuosi.tb.presenter.PlayerManagerPresenter;
import io.github.xunuosi.tb.views.view.IPlayerManagerActivityView;

/**
 * Created by admin on 2017/6/3.
 *
 */
@Module
public class PlayerManagerModule {
    private IPlayerManagerActivityView view;

    public PlayerManagerModule(IPlayerManagerActivityView view) {
        this.view = view;
    }

    @Provides
    @PerActivity
    IPlayerManagerActivityView provideTeamManagerView() {
        return this.view;
    }

    @Provides
    @PerActivity
    PlayerManagerPresenter providePlayerManagerPresenter(IPlayerManagerActivityView view, DaoSession daoSession) {
        return new PlayerManagerPresenter(view, daoSession);
    }
}
