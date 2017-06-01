package io.github.xunuosi.tb.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

import butterknife.BindView;
import io.github.xunuosi.tb.R;

/**
 * Created by xns on 2017/6/1.
 * 赛事首页数据
 */

public class MatchHomeActivity extends BaseActivity {

    @BindView(R.id.rv_match_home)
    UltimateRecyclerView mRvMatchHome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_match_home;
    }
}
