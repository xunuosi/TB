package io.github.xunuosi.tb.views.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import io.github.xunuosi.tb.R;

/**
 * Created by admin on 2017/6/3.
 * 队伍管理的详情界面
 */

public class PMDetailActivity extends BaseActivity {
    @Inject
    Context mContext;



    public static Intent getCallIntent(Context context) {
        return new Intent(context, PMDetailActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.initializeInjector();
        super.onCreate(savedInstanceState);
    }

    private void initializeInjector() {
//        DaggerTMDetailComponent
//                .builder()
//                .applicationComponent(getApplicationComponent())
//                .activityModule(getActivityModule())
//                .tMDetailModule(new TMDetailModule(this))
//                .build()
//                .inject(this);
    }

    @Override
    protected void initViews() {
//        tvTitle.setText(R.string.text_player_manager);
    }

    @Override
    protected void initData() {

    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_pm_detail;
    }


//    @Override
//    public void gotoActivity(Intent intent) {
//        finish();
//    }
//
//    @Override
//    public void changeDialogState(boolean isShow, @Nullable Integer msgId) {
//        String msg = null;
//        if (msgId != null) {
//            msg = getString(msgId);
//        }
//        if (isShow) {
//            LoadingUtil.showProgressDialog(PMDetailActivity.this, msg);
//        } else {
//            LoadingUtil.closeProgressDialog();
//        }
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        presenter.unbindView();
    }
}
