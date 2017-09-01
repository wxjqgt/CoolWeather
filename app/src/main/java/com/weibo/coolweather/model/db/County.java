package com.weibo.coolweather.model.db;

import com.weibo.coolweather.model.Area;

/**
 * Created by weixj on 2017/8/29.
 */

public class County extends Area{

    private String countyName;
    private String weather_id;
    private int cityid;

    public String getWeather_id() {
        return weather_id;
    }

    public void setWeather_id(String weather_id) {
        this.weather_id = weather_id;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public int getCityId() {
        return cityid;
    }

    public void setCityId(int cityId) {
        this.cityid = cityId;
    }
}
