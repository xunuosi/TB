package io.github.xunuosi.tb.views.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.github.xunuosi.tb.R;
import io.github.xunuosi.tb.utils.PermissionListener;

public class HomeActivity extends BaseActivity implements PermissionListener {

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
        checkAppPermission();
    }

    private void checkAppPermission() {
        addPermissions(new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE},this);
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

    @Override
    public void onGranted() {

    }

    @Override
    public void onDenied(List<String> deniedPermisson) {
        Toast.makeText(this, "未同意某些权限可能导致某些功能不能使用", Toast.LENGTH_SHORT).show();
    }
}
