package com.dhha22.permissionchecker;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.dhha22.permissionchecker.listener.PermissionListener;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by DavidHa on 2017. 10. 20..
 */

public class PermissionsChecker implements Serializable {
    private final Context context;
    private final ArrayList<String> permissionList = new ArrayList<>();


    public PermissionsChecker(Context context) {
        this.context = context;
    }

    public PermissionsChecker setListener(PermissionListener listener){
        PermissionCheckerActivity.listener = listener;
        return this;
    }


    public PermissionsChecker addPermission(String permission){
        permissionList.add(permission);
        return this;
    }


    public void checkPermission() {
        Intent intent = new Intent(context, PermissionCheckerActivity.class);
        intent.putStringArrayListExtra("permissionList", permissionList);
        context.startActivity(intent);
    }

    public void goAppSetting() {
        Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        context.startActivity(intent);
    }

}