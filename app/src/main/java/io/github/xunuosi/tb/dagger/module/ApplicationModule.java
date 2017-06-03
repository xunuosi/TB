package io.github.xunuosi.tb.dagger.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.github.xunuosi.tb.TBApplication;
import io.github.xunuosi.tb.data.db.DaoSession;
import io.github.xunuosi.tb.utils.GreenDaoHelper;

/**
 * Created by xns on 2017/6/1.
 * 全局Module
 */
@Module
public class ApplicationModule {
    private final TBApplication mApplication;

    public ApplicationModule(TBApplication application) {
        this.mApplication = application;
    }

    @Provides @Singleton
    Context provideApplicationContext() {
        return this.mApplication;
    }

    @Provides @Singleton
    DaoSession provideDaoSession() {
       return GreenDaoHelper.getDaoSession();
    }
}
