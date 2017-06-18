package io.github.xunuosi.tb.views.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.ui.divideritemdecoration.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.github.xunuosi.tb.R;
import io.github.xunuosi.tb.dagger.component.DaggerTeamManagerComponent;
import io.github.xunuosi.tb.dagger.module.TeamManagerModule;
import io.github.xunuosi.tb.model.AppConstant;
import io.github.xunuosi.tb.model.bean.Team;
import io.github.xunuosi.tb.presenter.TeamManagerPresenter;
import io.github.xunuosi.tb.utils.LoadingUtil;
import io.github.xunuosi.tb.views.adapter.TeamManagerAdapter;
import io.github.xunuosi.tb.views.view.ITeamManagerActivityView;
import io.github.xunuosi.tb.views.widget.PopWindowUtil;

/**
 * Created by admin on 2017/6/3.
 * 球队管理界面
 */

public class TeamManagerActivity extends BaseActivity implements ITeamManagerActivityView, TeamManagerAdapter.PopWindowListener {
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
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.root_tm)
    ConstraintLayout rootTm;

    private PopWindowUtil popWindow;


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
        setSupportActionBar(mToolBar);
        tvTitle.setText(R.string.text_team_manager);

        mAdapter.setListener(this);
        mAdapter.setData(new ArrayList<Team>());
        rvTm.setLayoutManager(new LinearLayoutManager(this));
        rvTm.setHasFixedSize(false);
        rvTm.setAdapter(mAdapter);
        Paint paint = new Paint();
        paint.setStrokeWidth(5);
        paint.setColor(ContextCompat.getColor(mContext, R.color.colorGray));
        paint.setAntiAlias(true);
        paint.setPathEffect(new DashPathEffect(new float[]{25.0f, 25.0f}, 0));
        rvTm.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).paint(paint).build());

        rvTm.setDefaultOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.showView(AppConstant.Action.REFRESH);
            }
        });

        rvTm.setOnLoadMoreListener(new UltimateRecyclerView.OnLoadMoreListener() {
            @Override
            public void loadMore(int itemsCount, int maxLastVisiblePosition) {
                presenter.showView(AppConstant.Action.LOAD_MORE);
            }
        });

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.showView(AppConstant.Action.INIT);
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
        mAdapter.setData(teams);
        mAdapter.refresh();
        changeDialogState(false, null);
    }

    @Override
    public void changeRVState(boolean enable) {
        rvTm.setRefreshing(enable);
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
                finish();
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

    @Override
    public void showPopWindow(View v, int touchX, int touchY, int position) {
        popWindow = new PopWindowUtil(mContext, R.layout.layout_pop_menu, v);
        popWindow.setAdapterPosi(position);
        popWindow.setPresenter(presenter);
        popWindow.show(touchX, touchY);
    }

    @Override
    public void hidePopWindow() {
        popWindow.dismiss();
    }
}
