package com.tesseract.tesseractsdk.model;

import android.content.Intent;
import android.graphics.drawable.Drawable;

public class TessAppInfo implements Comparable<TessAppInfo> {
    private String appName;
    private String packageName;
    private String launchActivityName;

    public Intent getLaunchIntent() {
        return launchIntent;
    }

    public void setLaunchIntent(Intent launchIntent) {
        this.launchIntent = launchIntent;
    }

    private Intent launchIntent;
    private String versionName;
    private int versionCode;
    private Drawable appIcon;

    public TessAppInfo(String appName, String packageName, String launchActivityName, Intent launchIntent, String versionName, int versionCode, Drawable appIcon) {
        this.appName = appName;
        this.packageName = packageName;
        this.launchActivityName = launchActivityName;
        this.launchIntent = launchIntent;
        this.versionName = versionName;
        this.versionCode = versionCode;
        this.appIcon = appIcon;
    }

    public TessAppInfo() {

    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getLaunchActivityName() {
        return launchActivityName;
    }

    public void setLaunchActivityName(String launchActivityName) {
        this.launchActivityName = launchActivityName;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    @Override
    public int compareTo(TessAppInfo tessAppInfo) {
        return this.getAppName().compareTo(tessAppInfo.getAppName());
    }
}
