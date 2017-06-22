package io.github.xunuosi.tb.dagger.component;

import dagger.Component;
import io.github.xunuosi.tb.dagger.PerActivity;
import io.github.xunuosi.tb.dagger.module.ActivityModule;
import io.github.xunuosi.tb.dagger.module.ChooseBattleModule;
import io.github.xunuosi.tb.dagger.module.ChooseTeamModule;
import io.github.xunuosi.tb.views.activity.ChooseBattleActivity;
import io.github.xunuosi.tb.views.activity.ChooseTeamActivity;

/**
 * Created by admin on 2017/6/3.
 *
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, ChooseBattleModule.class})
public interface ChooseBattleComponent extends ActivityComponent {

    void inject(ChooseBattleActivity chooseBattleActivity);

}
