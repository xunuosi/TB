package io.github.xunuosi.tb.views.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.github.xunuosi.tb.R;
import io.github.xunuosi.tb.dagger.component.DaggerPMDetailComponent;
import io.github.xunuosi.tb.dagger.module.PMDetailModule;
import io.github.xunuosi.tb.presenter.PMDetailPresenter;
import io.github.xunuosi.tb.utils.LoadingUtil;
import io.github.xunuosi.tb.utils.ToastUtil;
import io.github.xunuosi.tb.views.view.IPMDetailActivityView;

/**
 * Created by admin on 2017/6/3.
 * 队伍管理的详情界面
 */

public class PMDetailActivity extends BaseActivity implements IPMDetailActivityView, AdapterView.OnItemSelectedListener {
    @Inject
    Context mContext;
    @Inject
    PMDetailPresenter presenter;
    @Inject
    ArrayAdapter<String> mAdapter;
    @BindView(R.id.im_back_arrow)
    ImageView imBackArrow;
    @BindView(R.id.im_add)
    ImageView imAdd;
    @BindView(R.id.tv_title)
    AppCompatTextView tvTitle;
    @BindView(R.id.iv_item_pm_detail_avator)
    ImageView ivItemPmDetailAvator;
    @BindView(R.id.et_pmd_name)
    EditText etPmdName;
    @BindView(R.id.et_pmd_tName)
    EditText etPmdTName;
    @BindView(R.id.sp_pmd_position)
    Spinner spPmdPosition;
    @BindView(R.id.et_pmd_num)
    EditText etPmdNum;
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;


    public static Intent getCallIntent(Context context) {
        return new Intent(context, PMDetailActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.initializeInjector();
        super.onCreate(savedInstanceState);
    }

    private void initializeInjector() {
        DaggerPMDetailComponent
                .builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .pMDetailModule(new PMDetailModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
        setSupportActionBar(mToolBar);
        tvTitle.setText(R.string.text_player_manager);
        // Init Spinner
        spPmdPosition.setAdapter(mAdapter);
        spPmdPosition.setOnItemSelectedListener(PMDetailActivity.this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            presenter.initData2Show(intent);
        }
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_pm_detail;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unbindView();
    }

    @OnClick({R.id.btn_pmd_submit, R.id.im_back_arrow, R.id.im_add, R.id.iv_item_pm_detail_avator})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.im_back_arrow:
                finish();
                break;
            case R.id.btn_pmd_submit:
            case R.id.im_add:
                presenter.saveOrEditPlayer(etPmdName.getText().toString().trim(),
                        etPmdTName.getText().toString().trim(),
                        etPmdNum.getText().toString().trim());
                break;
            case R.id.iv_item_pm_detail_avator:
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
            LoadingUtil.showProgressDialog(PMDetailActivity.this, msg);
        } else {
            LoadingUtil.closeProgressDialog();
        }
    }

    @Override
    public void showView(String... data) {
        if (data.length == 1) {
            etPmdTName.setText(data[0]);
        } else {
            etPmdTName.setText(data[0]);
            etPmdName.setText(data[1]);
            int position = mAdapter.getPosition(data[2]);
            spPmdPosition.setSelection(position, true);
            etPmdNum.setText(data[3]);
        }

    }

    @Override
    public void showErrorToastMsg(int msgId) {
        String msg = getString(msgId);
        ToastUtil toastUtil = new ToastUtil(PMDetailActivity.this, R.layout.toast_center_error, msg);
        toastUtil.show();
    }

    @Override
    public void showToastMsg(int msgId) {
        String msg = getString(msgId);
        Toast.makeText(PMDetailActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * Clear Interface Value
     */
    @Override
    public void clear() {
        etPmdNum.setText("");
        etPmdName.setText("");
    }

    @Override
    public String getSpinnerValue(int position) {
        String[] array = getResources().getStringArray(R.array.team_position);
        return array[position];
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        presenter.saveItemSelected(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
