package io.github.xunuosi.tb.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
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
import io.github.xunuosi.tb.model.bean.Player;
import io.github.xunuosi.tb.view.adapter.PlayerManagerAdapter;

/**
 * Created by xns on 2017/6/2.
 */

public class PlayerManagerActivity extends BaseActivity {

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

    @Inject
    PlayerManagerAdapter<Player> mAdapter;
    @Inject
    Context mContext;
    private List<Player> mPlayerList;
    private int i = 0;

    public static Intent getCallIntent(Context context) {
        return new Intent(context, PlayerManagerActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.initializeInjector();
        super.onCreate(savedInstanceState);

    }

    private void initializeInjector() {
        getApplicationComponent().inject(this);
    }

    @Override
    protected void initViews() {
        mRvPlayerManager.setLayoutManager(new LinearLayoutManager(mContext));
        mRvPlayerManager.setHasFixedSize(false);
        mRvPlayerManager.setNestedScrollingEnabled(false);
        mAdapter.setData(mPlayerList);

        mRvPlayerManager.setAdapter(mAdapter);
        Paint paint = new Paint();
        paint.setStrokeWidth(5);
        paint.setColor(ContextCompat.getColor(mContext, R.color.colorGray));
        paint.setAntiAlias(true);
        paint.setPathEffect(new DashPathEffect(new float[]{25.0f, 25.0f}, 0));
        mRvPlayerManager.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).paint(paint).build());
    }

    @Override
    protected void initData() {
        mPlayerList = new ArrayList<>();
    }

    private void addItem() {
        Player player = new Player();
        player.setId(i);
        player.setName("马克" + i);
        player.setNum(i);
        player.setPosition("组织后卫");
        i++;
        mAdapter.insert(player, 0);
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
                addItem();
                break;
            case R.id.iv_footer_add:
                addItem();
                break;
        }
    }
}
