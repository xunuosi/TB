package io.github.xunuosi.tb.dagger.component;

import dagger.Component;
import io.github.xunuosi.tb.dagger.PerActivity;
import io.github.xunuosi.tb.dagger.module.ActivityModule;
import io.github.xunuosi.tb.dagger.module.PMDetailModule;
import io.github.xunuosi.tb.dagger.module.PlayerManagerModule;
import io.github.xunuosi.tb.views.activity.PMDetailActivity;
import io.github.xunuosi.tb.views.activity.PlayerManagerActivity;

/**
 * Created by admin on 2017/6/3.
 *
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, PMDetailModule.class})
public interface PMDetailComponent extends ActivityComponent {

    void inject(PMDetailActivity pmDetailActivity);

}
