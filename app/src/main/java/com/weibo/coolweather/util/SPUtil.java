package com.weibo.coolweather.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.weibo.coolweather.app.CoolWeatherApp;


/**
 * Created by weibo on 2017/9/8.
 */

public final class SPUtil {

    public static void putString(String key, String value) {
        SharedPreferences.Editor editor = getSP(CoolWeatherApp.getContext()).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getString(String key) {
        return getSP(CoolWeatherApp.getContext()).getString(key,"");
    }

    private static SharedPreferences getSP(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp;
    }

}
