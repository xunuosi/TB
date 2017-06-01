package io.github.xunuosi.tb.dagger.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import io.github.xunuosi.tb.dagger.PerActivity;

/**
 * Created by xns on 2017/6/1.
 */
@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @PerActivity
    Activity activity() {
        return this.mActivity;
    }
}
