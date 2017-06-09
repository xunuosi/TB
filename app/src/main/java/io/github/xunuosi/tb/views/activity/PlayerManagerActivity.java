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
import android.support.v7.widget.Toolbar;
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
import io.github.xunuosi.tb.dagger.component.DaggerPlayerManagerComponent;
import io.github.xunuosi.tb.dagger.module.PlayerManagerModule;
import io.github.xunuosi.tb.data.db.DaoSession;
import io.github.xunuosi.tb.model.bean.Player;
import io.github.xunuosi.tb.presenter.PlayerManagerPresenter;
import io.github.xunuosi.tb.utils.LoadingUtil;
import io.github.xunuosi.tb.views.adapter.PlayerManagerAdapter;
import io.github.xunuosi.tb.views.view.IPlayerManagerActivityView;

/**
 * Created by xns on 2017/6/2.
 */

public class PlayerManagerActivity extends BaseActivity implements IPlayerManagerActivityView {

    @BindView(R.id.im_back_arrow)
    ImageView mImBackArrow;
    @BindView(R.id.im_add)
    ImageView mImAdd;
    @BindView(R.id.tool_bar_pm)
    Toolbar toolBar;
    @BindView(R.id.iv_footer_add)
    ImageButton ivFooterAdd;
    @BindView(R.id.rv_player_manager)
    UltimateRecyclerView mRvPlayerManager;
    @BindView(R.id.tv_title)
    AppCompatTextView tvTitle;

    @Inject
    PlayerManagerAdapter<Player> mAdapter;
    @Inject
    Context mContext;
    @Inject
    DaoSession session;
    @Inject
    PlayerManagerPresenter presenter;

    private Paint paint = new Paint();

    public static Intent getCallIntent(Context context) {
        return new Intent(context, PlayerManagerActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.initializeInjector();
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent != null) {
            presenter.initData2Show(intent);
        }
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
        tvTitle.setText(R.string.text_player_manager);
    }

    @Override
    protected void initData() {

    }

    private void addItem() {
//        Player player = new Player();
//        player.setName("马克" + i);
//        player.setNum(i);
//        player.setPosition("组织后卫");
//        player.setSex("男");
//        player.setTeamId(1);
//        player.setTeamName("Elder" + i);
//        player.setCardNum(String.valueOf(i));
//        mAdapter.insert(player, 0);
//        session.getPlayerDao().insert(player);

    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_playermanager;
    }

    @OnClick({R.id.im_back_arrow, R.id.im_add, R.id.iv_footer_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.im_back_arrow:

                break;
            case R.id.im_add:
            case R.id.iv_footer_add:
                presenter.gotoActivity(TMDetailActivity.getCallIntent(mContext));
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
    public void showView(List<Player> players) {
        mRvPlayerManager.setLayoutManager(new LinearLayoutManager(mContext));
        mRvPlayerManager.setHasFixedSize(false);
        mRvPlayerManager.setAdapter(mAdapter);
        mAdapter.setData(players);

        paint.setStrokeWidth(5);
        paint.setColor(ContextCompat.getColor(mContext, R.color.colorGray));
        paint.setAntiAlias(true);
        paint.setPathEffect(new DashPathEffect(new float[]{25.0f, 25.0f}, 0));
        mRvPlayerManager.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).paint(paint).build());

        changeDialogState(false, null);
    }

    @Override
    protected void onDestroy() {
        presenter.unbindView();
        super.onDestroy();
    }
}
