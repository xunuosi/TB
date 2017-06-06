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
    private ITMDetailView.View view;

    public TMDetailModule(ITMDetailView.View view) {
        this.view = view;
    }

    @Provides
    @PerActivity
    ITMDetailView.View provideITMDetailView() {
        return this.view;
    }

    @Provides
    @PerActivity
    TMDetailPresenter provideITMDetailPresenter(ITMDetailView.View view, DaoSession daoSession) {
        return new TMDetailPresenter(view, daoSession);
    }
}
