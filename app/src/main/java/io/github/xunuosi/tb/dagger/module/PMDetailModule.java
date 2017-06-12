package io.github.xunuosi.tb.dagger.module;

import dagger.Module;
import dagger.Provides;
import io.github.xunuosi.tb.dagger.PerActivity;
import io.github.xunuosi.tb.data.db.DaoSession;
import io.github.xunuosi.tb.presenter.PMDetailPresenter;
import io.github.xunuosi.tb.presenter.PlayerManagerPresenter;
import io.github.xunuosi.tb.views.view.IPMDetailActivityView;
import io.github.xunuosi.tb.views.view.IPlayerManagerActivityView;

/**
 * Created by admin on 2017/6/3.
 *
 */
@Module
public class PMDetailModule {
    private IPMDetailActivityView view;

    public PMDetailModule(IPMDetailActivityView view) {
        this.view = view;
    }

    @Provides
    @PerActivity
    IPMDetailActivityView provideTeamManagerView() {
        return this.view;
    }

    @Provides
    @PerActivity
    PMDetailPresenter providePlayerManagerPresenter(IPMDetailActivityView view, DaoSession daoSession) {
        return new PMDetailPresenter(view, daoSession);
    }
}
