package com.dhha22.permissionchecker;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.dhha22.permissionchecker.listener.PermissionListener;

import java.util.ArrayList;

/**
 * Created by DavidHa on 2017. 10. 20..
 */

public class PermissionsChecker {
    private final Context context;
    private final ArrayList<String> permissionList = new ArrayList<>();
    private PermissionListener permissionListener;

    private PermissionsChecker() {
        this.context = null;
    }

    public PermissionsChecker(Context context) {
        this.context = context;
    }

    public PermissionsChecker setListener(PermissionListener listener) {
        permissionListener = listener;
        return this;
    }

    public PermissionsChecker addPermission(String permission) {
        permissionList.add(permission);
        return this;
    }

    public void checkPermission() {
        if(context != null) {
            Intent intent = new Intent(context, PermissionCheckerActivity.class);
            intent.putStringArrayListExtra("permissionList", permissionList);
            PermissionCheckerActivity.listener = permissionListener;
            context.startActivity(intent);
        }
    }

    public void goAppSetting() {
        if(context != null) {
            Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.parse("package:" + context.getPackageName()));
            context.startActivity(intent);
        }
    }

}
