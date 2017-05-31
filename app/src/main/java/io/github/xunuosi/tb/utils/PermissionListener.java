package io.github.xunuosi.tb.utils;

import java.util.List;

/**
 * Created by admin on 2017/5/31.
 * 权限获取的监听接口
 */

public interface PermissionListener {

    void onGranted();

    void onDenied(List<String> deniedPermisson);
}
