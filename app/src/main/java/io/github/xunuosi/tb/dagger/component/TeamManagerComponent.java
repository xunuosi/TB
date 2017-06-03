package io.github.xunuosi.tb.dagger.component;

import dagger.Component;
import io.github.xunuosi.tb.dagger.PerActivity;
import io.github.xunuosi.tb.dagger.module.ActivityModule;
import io.github.xunuosi.tb.dagger.module.TeamManagerModule;
import io.github.xunuosi.tb.view.activity.TeamManagerActivity;

/**
 * Created by admin on 2017/6/3.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, TeamManagerModule.class})
public interface TeamManagerComponent extends ActivityComponent {

    void inject(TeamManagerActivity teamManagerActivity);

}
