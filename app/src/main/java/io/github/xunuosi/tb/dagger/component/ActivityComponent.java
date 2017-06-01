package io.github.xunuosi.tb.dagger.component;

import android.app.Activity;

import dagger.Component;
import io.github.xunuosi.tb.dagger.PerActivity;
import io.github.xunuosi.tb.dagger.module.ActivityModule;

/**
 * Created by xns on 2017/6/1.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
interface ActivityComponent {
    Activity activity();
}
