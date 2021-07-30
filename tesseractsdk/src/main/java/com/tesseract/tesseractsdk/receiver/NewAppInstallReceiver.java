package com.tesseract.tesseractsdk.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.tesseract.tesseractsdk.TessSDK;
import com.tesseract.tesseractsdk.util.NotificationUtil;

public class NewAppInstallReceiver extends BroadcastReceiver {

    private static final String TAG = "NewAppInstallReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "New App Installed");
        NotificationUtil.showNotification("Tesseract Launcher", "A new app is just installed", context);
    }
}
