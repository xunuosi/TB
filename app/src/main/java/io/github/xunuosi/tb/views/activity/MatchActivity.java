package io.github.xunuosi.tb.views.activity;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import io.github.xunuosi.tb.R;
import io.github.xunuosi.tb.dagger.component.DaggerMatchComponent;
import io.github.xunuosi.tb.dagger.module.MatchModule;
import io.github.xunuosi.tb.presenter.MatchPresenter;
import io.github.xunuosi.tb.views.view.IMatchActivityView;

/**
 * Created by admin on 2017/6/26.
 * 记录比赛数据的界面
 */

public class MatchActivity extends BaseActivity implements IMatchActivityView {

    @Inject
    Context context;
    @Inject
    MatchPresenter presenter;
    @BindView(R.id.tv_home)
    TextView tvHome;
    @BindView(R.id.tv_visiting)
    TextView tvVisiting;

    public static Intent getCallIntent(Context context) {
        return new Intent(context, MatchActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.initializeInjector();
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        setStatusBarHide();
    }

    private void setStatusBarHide() {
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    private void initializeInjector() {
        DaggerMatchComponent
                .builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .matchModule(new MatchModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        presenter.initToShow(intent);
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_match;
    }

    @Override
    public void gotoActivity(Intent intent) {

    }

    @Override
    public void changeDialogState(boolean isShow, @Nullable Integer msgId) {

    }

    @Override
    public void showErrorToastMsg(int msgId) {

    }

    @Override
    public void test(String name, String name1) {
        tvHome.setText(name);
        tvVisiting.setText(name1);
    }
}
