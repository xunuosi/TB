package io.github.xunuosi.tb.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.github.xunuosi.tb.R;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.tv_match)
    TextView tvMatch;
    @BindView(R.id.tv_street)
    TextView tvStreet;
    @BindView(R.id.tv_bull)
    TextView tvBull;

    @Override
    public int setLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_match, R.id.tv_street, R.id.tv_bull})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_match:
                Intent intent = new Intent(HomeActivity.this, MatchHomeActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_street:
                break;
            case R.id.tv_bull:
                break;
        }
    }
}
