package io.github.xunuosi.tb.dagger.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import io.github.xunuosi.tb.TBApplication;
import io.github.xunuosi.tb.dagger.module.ApplicationModule;

/**
 * Created by xns on 2017/6/1.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(TBApplication application);

    Context context();
}
