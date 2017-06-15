package io.github.xunuosi.tb.views.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.github.xunuosi.tb.R;
import io.github.xunuosi.tb.dagger.component.DaggerTMDetailComponent;
import io.github.xunuosi.tb.dagger.module.TMDetailModule;
import io.github.xunuosi.tb.presenter.TMDetailPresenter;
import io.github.xunuosi.tb.utils.LoadingUtil;
import io.github.xunuosi.tb.views.view.ITMDetailView;

/**
 * Created by admin on 2017/6/3.
 * 队伍管理的详情界面
 */

public class TMDetailActivity extends BaseActivity implements ITMDetailView {
    @Inject
    Context mContext;
    @Inject
    TMDetailPresenter presenter;
    @BindView(R.id.tv_title)
    AppCompatTextView tvTitle;
    @BindView(R.id.iv_item_tm_detail_avator)
    ImageView ivItemTmDetailAvator;
    @BindView(R.id.et_team_name)
    EditText etTeamName;
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;

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
        setSupportActionBar(mToolBar);
        tvTitle.setText(R.string.text_team_manager);
    }

    @Override
    protected void initData() {

    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_tm_detail;
    }

    @OnClick({R.id.im_back_arrow, R.id.im_add, R.id.ib_add_avator, R.id.btn_tm_detail_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.im_back_arrow:
                break;
            case R.id.ib_add_avator:
                break;
            case R.id.im_add:
            case R.id.btn_tm_detail_submit:
                presenter.addTeam(null, etTeamName.getText().toString().trim());
                break;
        }
    }

    @Override
    public void gotoActivity(Intent intent) {
        finish();
    }

    @Override
    public void changeDialogState(boolean isShow, @Nullable Integer msgId) {
        String msg = null;
        if (msgId != null) {
            msg = getString(msgId);
        }
        if (isShow) {
            LoadingUtil.showProgressDialog(TMDetailActivity.this, msg);
        } else {
            LoadingUtil.closeProgressDialog();
        }
    }

    @Override
    protected void onDestroy() {
        presenter.unbindView();
        super.onDestroy();
    }
}
