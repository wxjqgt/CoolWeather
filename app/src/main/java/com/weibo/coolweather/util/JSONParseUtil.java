package com.weibo.coolweather.util;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.weibo.coolweather.model.HeFengWeather;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by weibo on 2017/9/8.
 */

public final class JSONParseUtil {

    public static HeFengWeather parseToWeatherModel(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            String heWeatherContent = jsonObject.getJSONArray("HeWeather").getString(0);
            HeFengWeather heFengWeather = new Gson().fromJson(heWeatherContent,HeFengWeather.class);
            return heFengWeather;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
