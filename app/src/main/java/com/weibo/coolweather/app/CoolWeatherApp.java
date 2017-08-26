package com.weibo.coolweather.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by weixj on 2017/8/26.
 */

public class CoolWeatherApp extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
