package com.linghang.rongtoudai;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;
import com.facebook.drawee.backends.pipeline.Fresco;

import constans.PersonalConstans;

/**
 * Created by caixiao on 2015/7/22 0022.
 */
public class BaseApplication extends Application {

    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        getVersion();
        Fresco.initialize(mContext);
        SDKInitializer.initialize(mContext);
    }

    private void getVersion() {
        try {
            PackageInfo packageInfo = this.getPackageManager().getPackageInfo(this.getPackageName(), 0);
            PersonalConstans.versionCode = packageInfo.versionCode;
            PersonalConstans.versionName = packageInfo.versionName;
            Log.i("BaseApplication", "versionName:" + String.valueOf(PersonalConstans.versionName));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTerminate() {
        Log.i("BaseApplication", "onTerminate");
        super.onTerminate();
    }
}
