package com.weibo.coolweather;

/**
 * Created by weixj on 2017/8/26.
 */

public interface Constant {

    String WEATHER_DATA = "weather_data";
    String LAST_WEATHER_ID = "last_weather_id";
    String SP_NAME = "CoolWeather";

    interface URL {
        String BING_PIC = "http://guolin.tech/api/bing_pic";
        String WEATHER_API_URL = "http://guolin.tech/";
        String KEY = "55725f617c3b41788c138e29270d328c";

        //中国所有的省份名称及ID
        String PROVINCE_URL = "api/china/";

        //查询指定省内包含的所有城市,即在PROVINCE_URL后面加上省份的ID
        //example:String CITY_URL = PROVINCE_URL + "/16";

        //查询指定城市的所有县区，即在CITY_URL后面加上城市ID
        //String COUNTY_AREA_URL = CITY_URL + "/116";

        //查询指定县区的天气
        //String WEATHER_URL = WEATHER_API_URL + "/weather?cityid=县区的weatherid&key=申请key";
    }

     enum  AREA_LEVEL {
        LEVEL_PROVINCE,
        LEVEL_CITY,
        LEVEL_COUNTY
    }

}
