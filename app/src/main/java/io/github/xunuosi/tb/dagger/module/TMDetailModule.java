package io.github.xunuosi.tb.dagger.module;

import dagger.Module;
import dagger.Provides;
import io.github.xunuosi.tb.dagger.PerActivity;
import io.github.xunuosi.tb.data.db.DaoSession;
import io.github.xunuosi.tb.presenter.TMDetailPresenter;
import io.github.xunuosi.tb.views.view.ITMDetailView;

/**
 * Created by admin on 2017/6/3.
 *
 */
@Module
public class TMDetailModule {
    private ITMDetailView view;

    public TMDetailModule(ITMDetailView view) {
        this.view = view;
    }

    @Provides
    @PerActivity
    ITMDetailView provideITMDetailView() {
        return this.view;
    }

    @Provides
    @PerActivity
    TMDetailPresenter provideITMDetailPresenter(ITMDetailView view, DaoSession daoSession) {
        return new TMDetailPresenter(view, daoSession);
    }
}
