package io.github.xunuosi.tb;

import android.app.Application;

/**
 * Created by admin on 2017/5/31.
 */

public class TBApplication extends Application {
    private static TBApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static TBApplication getInstance() {
        return instance;
    }
}
