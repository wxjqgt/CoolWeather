package com.weibo.coolweather.model;

import java.util.List;

/**
 * Created by weixj on 2017/9/1.
 */

public class Weather {

    private List<HeFengWeather> heFengWeathers;

    @Override
    public String toString() {
        return "Weather{" +
                "heFengWeathers=" + heFengWeathers +
                '}';
    }

    public List<HeFengWeather> getHeFengWeathers() {
        return heFengWeathers;
    }

    public void setHeFengWeathers(List<HeFengWeather> heFengWeathers) {
        this.heFengWeathers = heFengWeathers;
    }
}
