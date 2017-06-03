package io.github.xunuosi.tb.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import io.github.xunuosi.tb.R;
import io.github.xunuosi.tb.dagger.component.DaggerTMDetailComponent;
import io.github.xunuosi.tb.dagger.module.TMDetailModule;
import io.github.xunuosi.tb.presenter.TMDetailPresenter;
import io.github.xunuosi.tb.view.views.ITMDetailView;

/**
 * Created by admin on 2017/6/3.
 * 队伍管理的详情界面
 */

public class TMDetailActivity extends BaseActivity implements ITMDetailView.View {
    @Inject
    Context mContext;
    @Inject
    TMDetailPresenter presenter;

    public static Intent getCallIntent(Context context) {
        return new Intent(context, TMDetailActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.initializeInjector();
        super.onCreate(savedInstanceState);
    }

    private void initializeInjector() {
        DaggerTMDetailComponent
                .builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .tMDetailModule(new TMDetailModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_tm_detail;
    }
}
