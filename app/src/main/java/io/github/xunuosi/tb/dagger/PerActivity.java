package io.github.xunuosi.tb.dagger;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by xns on 2017/6/1.
 */
@Scope
@Retention(RUNTIME)
public @interface PerActivity {
}
