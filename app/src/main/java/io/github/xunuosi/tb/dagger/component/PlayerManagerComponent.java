package io.github.xunuosi.tb.dagger.component;

import dagger.Component;
import io.github.xunuosi.tb.dagger.PerActivity;
import io.github.xunuosi.tb.dagger.module.ActivityModule;
import io.github.xunuosi.tb.dagger.module.PlayerManagerModule;
import io.github.xunuosi.tb.views.activity.PlayerManagerActivity;

/**
 * Created by admin on 2017/6/3.
 *
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, PlayerManagerModule.class})
public interface PlayerManagerComponent extends ActivityComponent {

    void inject(PlayerManagerActivity playerManagerActivity);

}
