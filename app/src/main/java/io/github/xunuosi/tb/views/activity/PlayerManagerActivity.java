package io.github.xunuosi.tb.views.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.layoutmanagers.ScrollSmoothLineaerLayoutManager;
import com.marshalchen.ultimaterecyclerview.swipe.SwipeItemManagerInterface;
import com.marshalchen.ultimaterecyclerview.ui.divideritemdecoration.HorizontalDividerItemDecoration;
import com.marshalchen.ultimaterecyclerview.ui.emptyview.emptyViewOnShownListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.github.xunuosi.tb.R;
import io.github.xunuosi.tb.dagger.component.DaggerPlayerManagerComponent;
import io.github.xunuosi.tb.dagger.module.PlayerManagerModule;
import io.github.xunuosi.tb.data.db.DaoSession;
import io.github.xunuosi.tb.model.AppConstant;
import io.github.xunuosi.tb.model.bean.Player;
import io.github.xunuosi.tb.presenter.PlayerManagerPresenter;
import io.github.xunuosi.tb.utils.LoadingUtil;
import io.github.xunuosi.tb.views.adapter.PMSwipeAdapter;
import io.github.xunuosi.tb.views.view.IPlayerManagerActivityView;

/**
 * Created by xns on 2017/6/2.
 *
 */

public class PlayerManagerActivity extends BaseActivity implements IPlayerManagerActivityView,
        emptyViewOnShownListener, PMSwipeAdapter.SwipeBtnListener {

    @BindView(R.id.im_back_arrow)
    ImageView mImBackArrow;
    @BindView(R.id.im_add)
    ImageView mImAdd;
    @BindView(R.id.iv_footer_add)
    ImageButton ivFooterAdd;
    @BindView(R.id.rv_player_manager)
    UltimateRecyclerView mRvPlayerManager;
    @BindView(R.id.tv_title)
    AppCompatTextView tvTitle;

    @Inject
    Context mContext;
    @Inject
    DaoSession session;
    @Inject
    PlayerManagerPresenter presenter;
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;

    private Intent intent;
    private Paint paint = new Paint();
    private boolean isLoading = false;
    private ScrollSmoothLineaerLayoutManager mLayoutManager;
    private PMSwipeAdapter<Player> mPMSwipeAdapter;

    public static Intent getCallIntent(Context context) {
        return new Intent(context, PlayerManagerActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.initializeInjector();
        super.onCreate(savedInstanceState);
        intent = getIntent();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.showView(intent, AppConstant.Action.INIT);
    }

    private void initializeInjector() {
        DaggerPlayerManagerComponent
                .builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .playerManagerModule(new PlayerManagerModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
        setSupportActionBar(mToolBar);
        tvTitle.setText(R.string.text_player_manager);

        mRvPlayerManager.setHasFixedSize(false);
        mPMSwipeAdapter = new PMSwipeAdapter<>(new ArrayList<Player>());
        mPMSwipeAdapter.setMode(SwipeItemManagerInterface.Mode.Single);
        mPMSwipeAdapter.setSwipBtnListener(this);
        mLayoutManager = new ScrollSmoothLineaerLayoutManager(this, LinearLayoutManager.VERTICAL, false, 500);

        mRvPlayerManager.setLayoutManager(mLayoutManager);
        enableEmptyViewPolicy();

//        mRvPlayerManager.setLoadMoreView(LayoutInflater.from(this)
//                .inflate(R.layout.custom_bottom_progressbar, null));
//        mAdapter.enableLoadMore(true);

        mRvPlayerManager.reenableLoadmore();
        mRvPlayerManager.setAdapter(mPMSwipeAdapter);

        paint.setStrokeWidth(5);
        paint.setColor(ContextCompat.getColor(mContext, R.color.colorGray));
        paint.setAntiAlias(true);
        paint.setPathEffect(new DashPathEffect(new float[]{25.0f, 25.0f}, 0));
        mRvPlayerManager.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).paint(paint).build());

        mRvPlayerManager.setDefaultOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.showView(null, AppConstant.Action.REFRESH);
            }
        });

        mRvPlayerManager.setOnLoadMoreListener(new UltimateRecyclerView.OnLoadMoreListener() {
            @Override
            public void loadMore(int itemsCount, int maxLastVisiblePosition) {
                if (!isLoading) {
                    presenter.showView(null, AppConstant.Action.LOAD_MORE);
                }
            }
        });


    }

    protected void enableEmptyViewPolicy() {
        mRvPlayerManager.setEmptyView(R.layout.empty_view_v2, UltimateRecyclerView.EMPTY_CLEAR_ALL, this);
    }


    @Override
    protected void initData() {

    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_playermanager;
    }

    @OnClick({R.id.im_back_arrow, R.id.im_add, R.id.iv_footer_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.im_back_arrow:
                finish();
                break;
            case R.id.im_add:
            case R.id.iv_footer_add:
                presenter.gotoActivity(PMDetailActivity.getCallIntent(mContext));
                break;
        }
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
            LoadingUtil.showProgressDialog(PlayerManagerActivity.this, msg);
        } else {
            LoadingUtil.closeProgressDialog();
        }
    }

    @Override
    public void changeRVState(boolean enable) {
        mRvPlayerManager.setRefreshing(enable);
    }

    @Override
    public void setRVLoadMoreState(boolean enable) {
        if (enable) {
            mRvPlayerManager.reenableLoadmore();
        } else {
            mRvPlayerManager.disableLoadmore();
        }
    }

    @Override
    public void showEmptyView(boolean enable) {
        if (enable) {
            mRvPlayerManager.showEmptyView();
        } else {
            mRvPlayerManager.hideEmptyView();
        }
    }

    @Override
    public void showView(List<Player> players) {
        mPMSwipeAdapter.setData(players);
        changeDialogState(false, null);
    }

    @Override
    public void showToast(int msgId) {
        String msg = getString(msgId);
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        presenter.unbindView();
        super.onDestroy();
    }

    @Override
    public void onEmptyViewShow(View mView) {
        TextView tv = (TextView) mView.findViewById(R.id.exp_section_title);
        if (tv != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("There is no data.");
            tv.setText(sb.toString());
        }
    }


    @Override
    public void onEditClick(Object bean) {
        presenter.editObject((Player) bean, PMDetailActivity.getCallIntent(mContext));
    }

    @Override
    public void onDeleteClick(Object bean, int position) {
        if (presenter.deleteObject((Player) bean)) {
            mPMSwipeAdapter.removeAt(position);
        }
        changeDialogState(false, null);
    }
}
