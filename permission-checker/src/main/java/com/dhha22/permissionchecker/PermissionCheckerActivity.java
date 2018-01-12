package com.dhha22.permissionchecker;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.dhha22.permissionchecker.listener.PermissionListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by DavidHa on 2017. 10. 20..
 */

public final class PermissionCheckerActivity extends Activity {
    private static final String TAG = "PermissionCheckerActivi";
    private static final int PERMISSION_REQUEST_CODE = 0;
    private ArrayList<String> permissionList;
    public static PermissionListener listener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        permissionList = intent.getStringArrayListExtra("permissionList");
        if (findDenyPermission()) {
            requestPermissions();
        } else {
            Log.d(TAG, "grant permission");
            if (listener != null) {
                listener.grantPermission();
            }
            finish();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "on request permission result");
        List<String> denyPermissions = getDenyPermissions(permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE && denyPermissions.size() == 0) {
            Log.d(TAG, "grant permission");
            if (listener != null) {
                listener.grantPermission();
            }
            finish();
        } else {
            Log.d(TAG, "deny permission");
            if (listener != null) {
                listener.denyPermission(denyPermissions);
            }
            finish();
        }
    }

    private void requestPermissions() {
        Log.d(TAG, "request permissions");
        Object[] objects = permissionList.toArray();
        String[] permissions = Arrays.copyOf(objects, objects.length, String[].class);
        ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE);
    }

    private boolean findDenyPermission() {
        Log.d(TAG, "find deny permission");
        boolean hasDenyPermission = false;
        for (String permission : permissionList) {
            if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {
                hasDenyPermission = true;
            }
        }
        return hasDenyPermission;
    }

    private List<String> getDenyPermissions(String[] permissions, @NonNull int[] grantResults) {
        List<String> denyPermissions = new ArrayList<>();
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                denyPermissions.add(permissions[i]);
            }
        }
        return denyPermissions;
    }

    @Override
    protected void onDestroy() {
        listener = null;
        Log.d(TAG, "on destroy");
        super.onDestroy();
    }
}
