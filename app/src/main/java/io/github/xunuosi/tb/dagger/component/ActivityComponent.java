package io.github.xunuosi.tb.dagger.component;

import android.app.Activity;

import dagger.Component;
import io.github.xunuosi.tb.dagger.PerActivity;
import io.github.xunuosi.tb.dagger.module.ActivityModule;
import io.github.xunuosi.tb.view.activity.BaseActivity;
import io.github.xunuosi.tb.view.activity.MatchHomeActivity;
import io.github.xunuosi.tb.view.activity.PlayerManagerActivity;
import io.github.xunuosi.tb.view.activity.TMDetailActivity;
import io.github.xunuosi.tb.view.activity.TeamManagerActivity;

/**
 * Created by xns on 2017/6/1.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
//    void inject(TeamManagerActivity teamManagerActivity);
    void inject(PlayerManagerActivity playerManagerActivity);
    void inject(TMDetailActivity tmDetailActivity);

    Activity activity();
}
