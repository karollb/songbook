package pl.edu.wszib.songbook.permissions;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class StoragePermission {
    private static final int STORAGE_PERMISSION_CODE = 101;

    public static int getSTORAGE_PERMISSION_code() {
        return STORAGE_PERMISSION_CODE;
    }

    public static void checkPermission(final Context context, final Activity activity) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }

    }
}
