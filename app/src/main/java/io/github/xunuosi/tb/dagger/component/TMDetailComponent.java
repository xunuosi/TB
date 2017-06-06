package io.github.xunuosi.tb.dagger.component;

import dagger.Component;
import io.github.xunuosi.tb.dagger.PerActivity;
import io.github.xunuosi.tb.dagger.module.ActivityModule;
import io.github.xunuosi.tb.dagger.module.TMDetailModule;
import io.github.xunuosi.tb.views.activity.TMDetailActivity;

/**
 * Created by admin on 2017/6/3.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, TMDetailModule.class})
public interface TMDetailComponent extends ActivityComponent {

    void inject(TMDetailActivity tmDetailActivity);

}
