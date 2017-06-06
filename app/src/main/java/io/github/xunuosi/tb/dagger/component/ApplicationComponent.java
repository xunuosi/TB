package io.github.xunuosi.tb.dagger.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import io.github.xunuosi.tb.dagger.module.ApplicationModule;
import io.github.xunuosi.tb.data.db.DaoSession;
import io.github.xunuosi.tb.views.activity.BaseActivity;
import io.github.xunuosi.tb.views.activity.MatchHomeActivity;

/**
 * Created by xns on 2017/6/1.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);
    void inject(MatchHomeActivity matchHomeActivity);

    Context context();
    DaoSession daoSeesion();
}
