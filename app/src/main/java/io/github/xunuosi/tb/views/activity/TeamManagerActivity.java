package io.github.xunuosi.tb.views.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.ui.divideritemdecoration.HorizontalDividerItemDecoration;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.github.xunuosi.tb.R;
import io.github.xunuosi.tb.dagger.component.DaggerTeamManagerComponent;
import io.github.xunuosi.tb.dagger.module.TeamManagerModule;
import io.github.xunuosi.tb.model.bean.Team;
import io.github.xunuosi.tb.presenter.TeamManagerPresenter;
import io.github.xunuosi.tb.utils.LoadingUtil;
import io.github.xunuosi.tb.views.adapter.TeamManagerAdapter;
import io.github.xunuosi.tb.views.view.ITeamManagerActivityView;

/**
 * Created by admin on 2017/6/3.
 * 球队管理界面
 */

public class TeamManagerActivity extends BaseActivity implements ITeamManagerActivityView {

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
    protected void onStart() {
        super.onStart();
        presenter.initData2Show();
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_teammanager;
    }

    @Override
    public void gotoActivity(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void showView(List<Team> teams) {
        rvTm.setLayoutManager(new LinearLayoutManager(mContext));
        rvTm.setHasFixedSize(false);
        mAdapter.setData(teams);
        rvTm.setAdapter(mAdapter);

        changeDialogState(false, null);

        Paint paint = new Paint();
        paint.setStrokeWidth(5);
        paint.setColor(ContextCompat.getColor(mContext, R.color.colorGray));
        paint.setAntiAlias(true);
        paint.setPathEffect(new DashPathEffect(new float[]{25.0f, 25.0f}, 0));
        rvTm.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).paint(paint).build());
    }

    @Override
    public void changeDialogState(boolean isShow, @Nullable Integer msgId) {
        String msg = null;
        if (msgId != null) {
            msg = getString(msgId);
        }
        if (isShow) {
            LoadingUtil.showProgressDialog(TeamManagerActivity.this, msg);
        } else {
            LoadingUtil.closeProgressDialog();
        }
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

    @Override
    protected void onDestroy() {
        presenter.unbindView();
        super.onDestroy();
    }

}
