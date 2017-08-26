package com.weibo.coolweather.app;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import org.litepal.LitePal;

/**
 * Created by weixj on 2017/8/26.
 */

public class CoolWeatherApp extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        Logger.addLogAdapter(new AndroidLogAdapter());
        LitePal.initialize(this);
    }

    public static Context getContext() {
        return context;
    }
}
