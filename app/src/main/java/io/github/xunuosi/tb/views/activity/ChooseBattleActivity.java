package io.github.xunuosi.tb.views.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.github.xunuosi.tb.R;
import io.github.xunuosi.tb.dagger.component.DaggerChooseBattleComponent;
import io.github.xunuosi.tb.dagger.module.ChooseBattleModule;
import io.github.xunuosi.tb.model.AppConstant;
import io.github.xunuosi.tb.presenter.ChooseBattlePresenter;
import io.github.xunuosi.tb.utils.ToastUtil;
import io.github.xunuosi.tb.views.view.IChooseBattleActivityView;

/**
 * Created by admin on 2017/6/18.
 * 队伍对抗选择界面
 */

public class ChooseBattleActivity extends BaseActivity implements IChooseBattleActivityView
        , AdapterView.OnItemSelectedListener {

    @Inject
    Context context;
    @Inject
    ChooseBattlePresenter presenter;
    @Inject
    ArrayAdapter<String> mAdapter;

    @BindView(R.id.tv_title)
    AppCompatTextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.sp_cb_homeTeam)
    Spinner spCbHomeTeam;
    @BindView(R.id.sp_cb_visitingTeam)
    Spinner spCbVisitingTeam;

    public static Intent getCallIntent(Context context) {
        return new Intent(context, ChooseBattleActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.initializeInjector();
        super.onCreate(savedInstanceState);
    }

    private void initializeInjector() {
        DaggerChooseBattleComponent
                .builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .chooseBattleModule(new ChooseBattleModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
        changeDialogState(true, R.string.attention_loading_data);
        tvTitle.setText(R.string.text_choose_team);
        setSupportActionBar(toolbar);
        spCbHomeTeam.setAdapter(mAdapter);
        spCbHomeTeam.setOnItemSelectedListener(this);
        spCbVisitingTeam.setAdapter(mAdapter);
        spCbVisitingTeam.setOnItemSelectedListener(this);
        changeDialogState(false, null);
    }

    @Override
    protected void initData() {

    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_choose_battle;
    }

    @Override
    public void gotoActivity(Intent intent) {

    }

    @Override
    public void changeDialogState(boolean isShow, @Nullable Integer msgId) {

    }

    @Override
    public void showErrorToastMsg(int msgId) {
        String msg = getString(msgId);
        ToastUtil toastUtil = new ToastUtil(ChooseBattleActivity.this, R.layout.toast_center_error, msg);
        toastUtil.show();
    }

    @OnClick({R.id.im_back_arrow, R.id.im_add, R.id.btn_cb_entry, R.id.btn_cb_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.im_add:
                break;
            case R.id.btn_cb_entry:
                presenter.checkoutData(spCbHomeTeam.getSelectedItemPosition(),
                        spCbVisitingTeam.getSelectedItemPosition());
                break;
            case R.id.im_back_arrow:
            case R.id.btn_cb_cancel:
                finish();
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onDestroy() {
        presenter.unbindView();
        super.onDestroy();
    }
}
