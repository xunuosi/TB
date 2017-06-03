package io.github.xunuosi.tb.dagger.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import io.github.xunuosi.tb.dagger.module.ApplicationModule;
import io.github.xunuosi.tb.view.activity.BaseActivity;
import io.github.xunuosi.tb.view.activity.MatchHomeActivity;
import io.github.xunuosi.tb.view.activity.PlayerManagerActivity;
import io.github.xunuosi.tb.view.activity.TeamManagerActivity;

/**
 * Created by xns on 2017/6/1.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);
    void inject(MatchHomeActivity matchHomeActivity);
    void inject(PlayerManagerActivity playerManagerActivity);
    void inject(TeamManagerActivity teamManagerActivity);
    Context context();
}
