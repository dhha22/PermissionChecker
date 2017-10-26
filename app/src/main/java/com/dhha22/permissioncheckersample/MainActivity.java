package com.dhha22.permissioncheckersample;

import android.Manifest;
import android.content.Intent;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.dhha22.permissionchecker.PermissionsChecker;
import com.dhha22.permissionchecker.listener.PermissionListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new PermissionsChecker(this)
                .setListener(permissionListener)
                .addPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .addPermission(Manifest.permission.CAMERA)
                .checkPermission();
    }

    private PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void denyPermission(List<String> permissions) {
            Toast.makeText(MainActivity.this, "deny permission", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void grantPermission() {
            Toast.makeText(MainActivity.this, "grant permission", Toast.LENGTH_SHORT).show();
        }
    };
}
