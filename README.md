# PermissionChecker

## Including in your project
You must download the [permission-checker.aar](https://github.com/dhha22/PermissionChecker/raw/efdf50ab82481deb309e5d0b3b37fbefa10dc470/permission-checker.aar) and save in your project `app/libs` folder.

and you just need to add the following dependency to your `build.gradle`.
```java
allprojects {
    repositories {
        flatDir {
            dirs 'libs'
        }
    }
}

dependencies {
    compile 'com.dhha22.permissionchecker:permission-checker:0.1.1-beta@aar'
}

```

## Usage
### Step 1

Declare PermissionsChecker class where Runtime permission is required

```java
 new PermissionsChecker(this)
                .setListener(permissionListener)
                .addPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .addPermission(Manifest.permission.CAMERA)
                .checkPermission();
```

### Step 2

Set listener if you want to get callback value 
```java
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

```
