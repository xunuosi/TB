package io.github.xunuosi.tb.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.github.xunuosi.tb.R;
import io.github.xunuosi.tb.view.adapter.SimpleAdapter;

/**
 * Created by xns on 2017/6/1.
 * 赛事首页数据
 */

public class MatchHomeActivity extends BaseActivity implements SimpleAdapter.itemOnClickListener{

    @BindView(R.id.rv_match_home)
    UltimateRecyclerView mRvMatchHome;

    private List<String> data;
    @Inject
    SimpleAdapter<String> mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViews() {
        mRvMatchHome.setHasFixedSize(false);
        mAdapter.setData(data);
        mRvMatchHome.setAdapter(mAdapter);
        mRvMatchHome.setLayoutManager(new LinearLayoutManager(MatchHomeActivity.this));

    }

    @Override
    protected void initData() {
        data = Arrays.asList("赛事技术统计", "赛事管理", "球队管理", "球员管理");
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_match_home;
    }


    @Override
    public void onItemClick(int position) {
        String s;
        s = data.get(position);
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
