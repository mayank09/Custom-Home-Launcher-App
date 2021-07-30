package com.tesseract.customlauncher;

import android.app.Application;

import com.tesseract.tesseractsdk.TessSDK;

public class CustomLauncherApp  extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        TessSDK.init(getApplicationContext());
    }

}
