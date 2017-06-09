package io.github.xunuosi.tb.views.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.github.xunuosi.tb.R;
import io.github.xunuosi.tb.dagger.component.DaggerChooseTeamComponent;
import io.github.xunuosi.tb.dagger.module.ChooseTeamModule;
import io.github.xunuosi.tb.data.db.DaoSession;
import io.github.xunuosi.tb.model.bean.Team;
import io.github.xunuosi.tb.presenter.ChooseTeamPresenter;
import io.github.xunuosi.tb.views.view.IChooseTeamActivityView;

/**
 * Created by xns on 2017/6/9.
 * 选择队伍界面
 */

public class ChooseTeamActivity extends BaseActivity implements IChooseTeamActivityView {
    @Inject
    Context mContext;
    @Inject
    ChooseTeamPresenter presenter;
    @Inject
    ArrayAdapter<String> mAdapter;
    @Inject
    List<Team> mTeamList;
    @Inject
    DaoSession mDaoSession;
    @BindView(R.id.im_add)
    ImageView mImAdd;
    @BindView(R.id.tv_title)
    AppCompatTextView mTvTitle;
    @BindView(R.id.spinner)
    AppCompatSpinner mSpinner;

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
        mTvTitle.setText(R.string.text_choose_team);
        mImAdd.setVisibility(View.GONE);
        // Init Spinner
        mSpinner.setAdapter(mAdapter);
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

    }

    @Override
    public void changeDialogState(boolean isShow, @Nullable Integer msgId) {

    }

    @Override
    public void showView(List<Team> teams) {

    }

    @OnClick({R.id.im_back_arrow, R.id.btn_confirm_edit_ct, R.id.btn_cancel_ct})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.im_back_arrow:
                break;
            case R.id.btn_confirm_edit_ct:
                break;
            case R.id.btn_cancel_ct:
                break;
        }
    }
}
