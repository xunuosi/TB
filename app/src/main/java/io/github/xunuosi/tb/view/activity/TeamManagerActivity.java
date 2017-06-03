package io.github.xunuosi.tb.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.github.xunuosi.tb.R;
import io.github.xunuosi.tb.dagger.component.DaggerTeamManagerComponent;
import io.github.xunuosi.tb.dagger.module.TeamManagerModule;
import io.github.xunuosi.tb.model.bean.Team;
import io.github.xunuosi.tb.presenter.TeamManagerPresenter;
import io.github.xunuosi.tb.view.adapter.TeamManagerAdapter;
import io.github.xunuosi.tb.view.views.ITeamManagerActivityView;

/**
 * Created by admin on 2017/6/3.
 * 球队管理界面
 */

public class TeamManagerActivity extends BaseActivity implements ITeamManagerActivityView.View {

    @Inject
    TeamManagerAdapter<Team> mAdapter;
    @Inject
    Context mContext;
    @Inject
    TeamManagerPresenter presenter;

    @BindView(R.id.im_back_arrow)
    ImageView imBackArrow;
    @BindView(R.id.im_add)
    ImageView imAdd;
    @BindView(R.id.tv_title)
    AppCompatTextView tvTitle;
    @BindView(R.id.iv_footer_add_tm)
    ImageButton ivFooterAddTm;
    @BindView(R.id.rv_tm)
    UltimateRecyclerView rvTm;
    private List<Team> mTeamList;

    public static Intent getCallIntent(Context context) {
        return new Intent(context, TeamManagerActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.initializeInjector();
        super.onCreate(savedInstanceState);

    }

    private void initializeInjector() {
        DaggerTeamManagerComponent
                .builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .teamManagerModule(new TeamManagerModule(this))
                .build()
                .inject(this);
    }


    @Override
    protected void initViews() {
        tvTitle.setText(R.string.text_team_manager);
    }

    @Override
    protected void initData() {

    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_teammanager;
    }

    @Override
    public void gotoActivity(Intent intent) {
        startActivity(intent);
    }

    @OnClick({R.id.im_back_arrow, R.id.im_add, R.id.iv_footer_add_tm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.im_back_arrow:
                break;
            case R.id.im_add:
            case R.id.iv_footer_add_tm:
                presenter.gotoActivity(TMDetailActivity.getCallIntent(mContext));
                break;
        }
    }
}
