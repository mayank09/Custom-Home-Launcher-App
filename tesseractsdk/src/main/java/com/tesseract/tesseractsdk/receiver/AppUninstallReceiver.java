package com.tesseract.tesseractsdk.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.tesseract.tesseractsdk.util.NotificationUtil;

public class AppUninstallReceiver extends BroadcastReceiver {

    private static final String TAG = "AppUninstallReceiver";
    
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: App Uninstalled");
        NotificationUtil.showNotification("Tesseract Launcher", "An app is just uninstalled from the device", context);
    }
}
