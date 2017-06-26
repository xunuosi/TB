package io.github.xunuosi.tb.dagger.component;

import dagger.Component;
import io.github.xunuosi.tb.dagger.PerActivity;
import io.github.xunuosi.tb.dagger.module.ActivityModule;
import io.github.xunuosi.tb.dagger.module.ChooseBattleModule;
import io.github.xunuosi.tb.dagger.module.MatchModule;
import io.github.xunuosi.tb.views.activity.ChooseBattleActivity;
import io.github.xunuosi.tb.views.activity.MatchActivity;

/**
 * Created by admin on 2017/6/3.
 *
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, MatchModule.class})
public interface MatchComponent extends ActivityComponent {

    void inject(MatchActivity matchActivity);

}
