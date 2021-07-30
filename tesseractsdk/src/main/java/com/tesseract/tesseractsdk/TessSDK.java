package com.tesseract.tesseractsdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.tesseract.tesseractsdk.model.TessAppInfo;
import com.tesseract.tesseractsdk.receiver.AppUninstallReceiver;
import com.tesseract.tesseractsdk.receiver.NewAppInstallReceiver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class TessSDK {

    private static final String TAG = "TessSDK";

    /**
     * To hold the Application Context
     */
    private static Context sAppContext;
    private static AppUninstallReceiver uninstallReceiver;
    private static NewAppInstallReceiver installReceiver;

    /**
     * Initialize the SDK
     *
     * @param context Application Context of the app.
     */
    public static void init(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context should not be null");
        }
        sAppContext = context;
        installReceiver = new NewAppInstallReceiver();
        uninstallReceiver = new AppUninstallReceiver();

        Log.i(TAG, "Tess SDK Initialized ");
    }

    public static Context getAppContext() {
        return sAppContext;
    }

    public static ArrayList<TessAppInfo> getAllPackages() {
        final PackageManager pm = TessSDK.getAppContext().getPackageManager();

        //get a list of all installed Packages which can be launched
        ArrayList<TessAppInfo> appList = new ArrayList<>();
        List<PackageInfo> packages = pm.getInstalledPackages(0);
        Log.d(TAG, String.valueOf(packages.size()));

        for (PackageInfo packageInfo : packages) {
            if (pm.getLaunchIntentForPackage(packageInfo.packageName) != null) {
                TessAppInfo app = new TessAppInfo();
                app.setAppName(packageInfo.applicationInfo.loadLabel(pm).toString());
                app.setPackageName(packageInfo.packageName);
                app.setVersionCode(packageInfo.versionCode);
                app.setVersionName(packageInfo.versionName);
                app.setLaunchIntent(pm.getLaunchIntentForPackage(packageInfo.packageName));
                app.setLaunchActivityName(pm.getLaunchIntentForPackage(packageInfo.packageName).resolveActivity(pm).getClassName());
                app.setAppIcon(packageInfo.applicationInfo.loadIcon(pm));
                appList.add(app);
            }
        }
        Log.d(TAG, "getAllPackages: " + appList.size());
        Collections.sort(appList);
        return appList;
    }

    public static void registerInstallBroadcast() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED);
        intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        intentFilter.addDataScheme("package");
        getAppContext().registerReceiver(installReceiver, intentFilter);
    }

    public static void registerUninstallBroadcast() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        intentFilter.addAction(Intent.ACTION_PACKAGE_FULLY_REMOVED);
        intentFilter.addDataScheme("package");
        getAppContext().registerReceiver(uninstallReceiver, intentFilter);
    }

    public static void unregisterInstallReceiver(){
        getAppContext().unregisterReceiver(installReceiver);
    }

    public static void unregisterUninstallReceiver(){
        getAppContext().unregisterReceiver(uninstallReceiver);
    }
}
