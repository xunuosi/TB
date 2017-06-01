package io.github.xunuosi.tb;

import android.app.Application;

import io.github.xunuosi.tb.dagger.component.ApplicationComponent;
import io.github.xunuosi.tb.dagger.component.DaggerApplicationComponent;
import io.github.xunuosi.tb.dagger.module.ApplicationModule;

/**
 * Created by admin on 2017/5/31.
 */

public class TBApplication extends Application {
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.mApplicationComponent;
    }
}
