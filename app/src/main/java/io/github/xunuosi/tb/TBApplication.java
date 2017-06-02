package io.github.xunuosi.tb;

import android.app.Application;
import android.content.Context;

import io.github.xunuosi.tb.dagger.component.ApplicationComponent;
import io.github.xunuosi.tb.dagger.component.DaggerApplicationComponent;
import io.github.xunuosi.tb.dagger.module.ApplicationModule;
import io.github.xunuosi.tb.utils.GreenDaoHelper;

/**
 * Created by admin on 2017/5/31.
 * Application
 */

public class TBApplication extends Application {
    private ApplicationComponent mApplicationComponent;
    private static Context instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        initDatabase();
    }

    public static Context getContext() {
        return instance;
    }

    private void initDatabase() {
        GreenDaoHelper.initDatabase();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.mApplicationComponent;
    }
}
