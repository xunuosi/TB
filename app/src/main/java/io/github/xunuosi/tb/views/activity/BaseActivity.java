package io.github.xunuosi.tb.views.activity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import io.github.xunuosi.tb.TBApplication;
import io.github.xunuosi.tb.dagger.component.ApplicationComponent;
import io.github.xunuosi.tb.dagger.module.ActivityModule;
import io.github.xunuosi.tb.utils.ActivityCollector;
import io.github.xunuosi.tb.utils.PermissionListener;

/**
 * Created by admin on 2017/5/31.
 *
 */

public abstract class BaseActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;

    public static List<String> listPermission;
    public static PermissionListener mListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        ButterKnife.bind(this);
        this.getApplicationComponent().inject(this);
        ActivityCollector.addActivity(this);
        initData();
        initViews();
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((TBApplication) getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    protected abstract void initViews();

    protected abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    public static void addPermissions(String[] permisses, PermissionListener listener) {
        listPermission = new ArrayList<>();
        mListener = listener;
        for (String per : permisses) {
            if (ContextCompat.checkSelfPermission(ActivityCollector.getTopActivity(), per) != PackageManager.PERMISSION_GRANTED) {
                listPermission.add(per);
            }
        }
        if (!listPermission.isEmpty()) {
            ActivityCompat.requestPermissions(ActivityCollector.getTopActivity(),listPermission.toArray(new String[listPermission.size()]),REQUEST_CODE);
        } else {
            mListener.onGranted();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0) {
                List<String> deniedList = new ArrayList<>();
                for (int i=0;i<permissions.length;i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        deniedList.add(permissions[i]);
                    }
                }
                if (!deniedList.isEmpty()) {
                    mListener.onDenied(deniedList);
                } else {
                    mListener.onGranted();
                }
            }
        }
    }

    @LayoutRes
    public abstract int setLayoutId();
}
