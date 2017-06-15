package io.github.xunuosi.tb.views.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.github.xunuosi.tb.R;
import io.github.xunuosi.tb.dagger.component.DaggerChooseTeamComponent;
import io.github.xunuosi.tb.dagger.module.ChooseTeamModule;
import io.github.xunuosi.tb.data.db.DaoSession;
import io.github.xunuosi.tb.presenter.ChooseTeamPresenter;
import io.github.xunuosi.tb.utils.LoadingUtil;
import io.github.xunuosi.tb.utils.ToastUtil;
import io.github.xunuosi.tb.views.view.IChooseTeamActivityView;

/**
 * Created by xns on 2017/6/9.
 * 选择队伍界面
 */

public class ChooseTeamActivity extends BaseActivity implements IChooseTeamActivityView, AdapterView.OnItemSelectedListener {

    @Inject
    Context mContext;
    @Inject
    ChooseTeamPresenter presenter;
    @Inject
    ArrayAdapter<String> mAdapter;
    @Inject
    DaoSession mDaoSession;
    @BindView(R.id.im_add)
    ImageView mImAdd;
    @BindView(R.id.tv_title)
    AppCompatTextView mTvTitle;
    @BindView(R.id.spinner)
    AppCompatSpinner mSpinner;
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;

    public static Intent getCallIntent(Context context) {
        return new Intent(context, ChooseTeamActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.initializeInjector();
        super.onCreate(savedInstanceState);

    }

    private void initializeInjector() {
        DaggerChooseTeamComponent
                .builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .chooseTeamModule(new ChooseTeamModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
        changeDialogState(true, R.string.attention_loading_data);
        setSupportActionBar(mToolBar);
        mTvTitle.setText(R.string.text_choose_team);
        mImAdd.setVisibility(View.INVISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Init Spinner
                mSpinner.setAdapter(mAdapter);
                mSpinner.setOnItemSelectedListener(ChooseTeamActivity.this);
                changeDialogState(false, null);
            }
        }, 1000);
    }

    @Override
    protected void initData() {

    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_choose_team;
    }

    @Override
    public void gotoActivity(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void changeDialogState(boolean isShow, @Nullable Integer msgId) {
        String msg = null;
        if (msgId != null) {
            msg = getString(msgId);
        }
        if (isShow) {
            LoadingUtil.showProgressDialog(ChooseTeamActivity.this, msg);
        } else {
            LoadingUtil.closeProgressDialog();
        }
    }

    @Override
    public void showErrorToastMsg(int msgId) {
        String msg = getString(msgId);
        ToastUtil toastUtil = new ToastUtil(ChooseTeamActivity.this, R.layout.toast_center_error, msg);
        toastUtil.show();
    }

    @Override
    protected void onDestroy() {
        presenter.unbindView();
        super.onDestroy();
    }

    @OnClick({R.id.im_back_arrow, R.id.btn_confirm_edit_ct, R.id.btn_cancel_ct})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.im_back_arrow:
                finish();
                break;
            case R.id.btn_confirm_edit_ct:
                presenter.gotoActivity(PlayerManagerActivity.getCallIntent(mContext));
                break;
            case R.id.btn_cancel_ct:
                finish();
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        presenter.saveItemSelected(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
