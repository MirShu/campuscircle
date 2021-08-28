package com.campuscircle.app.base;

import android.app.Application;
import android.content.Context;

import com.campuscircle.app.utils.AppUtils;
import com.campuscircle.app.gdtmob.Constants;
import com.campuscircle.app.utils.AppsDataSetting;
import com.qq.e.comm.managers.GDTADManager;

import org.xutils.BuildConfig;
import org.xutils.x;

public class BaseAplication extends Application {
    public static Context mContext;
    public static AppsDataSetting appsDataSetting;

    @Override
    public void onCreate() {
        super.onCreate();
        appsDataSetting = AppsDataSetting.getInstance();
        mContext = getApplicationContext();
        getDeviceInformation();
        GDTADManager.getInstance().initWith(getApplicationContext(), Constants.APPID);
        x.Ext.init(this);
        AppsDataSetting.getInstance().init(getApplicationContext());
    }

    private void getDeviceInformation() {
        String getAppVersionName = AppUtils.getAppVersionName(mContext);
        String getDeviceName = AppUtils.getDeviceName();
        String getSystemModel = AppUtils.getSystemModel();
        String getDeviceBrand = AppUtils.getDeviceBrand();
        String getDeviceId = AppUtils.getDeviceId();
        String getDeviceLanguage = AppUtils.getDeviceLanguage();

    }

    public static AppsDataSetting appsDataSetting() {
        return appsDataSetting;
    }


    public static Context getContext() {
        return mContext;
    }
}
