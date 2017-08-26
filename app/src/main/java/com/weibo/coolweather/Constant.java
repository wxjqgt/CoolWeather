package com.weibo.coolweather;

/**
 * Created by weixj on 2017/8/26.
 */

public interface Constant {

    String WEATHER_API_URL = "http://guolin.tech/api";

    //中国所有的省份名称及ID
    String PROVINCE_URL = WEATHER_API_URL + "/china";

    //查询指定省内包含的所有城市,即在PROVINCE_URL后面加上省份的ID
    //example:String CITY_URL = PROVINCE_URL + "/16";

    //查询指定城市的所有县区，即在CITY_URL后面加上城市ID
    //String COUNTY_AREA_URL = CITY_URL + "/116";

    //String WEATHER_URL = WEATHER_API_URL + "/weather?cityid=县区的weatherid&key=申请key";

}
