package com.dhha22.permissionchecker.listener;

import java.util.List;

/**
 * Created by DavidHa on 2017. 10. 20..
 */

public interface PermissionListener {
    void denyPermission(List<String> permissions);
    void grantPermission();
}
